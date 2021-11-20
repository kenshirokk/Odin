package main

import (
	"fmt"
	"io"
	"net"
	"sync"
	"time"
)

type Server struct {
	Ip   string
	Port int
	//在线用户列表
	OnlineMap map[string]*User
	mapLock   sync.RWMutex

	//消息广播的channel
	Message chan string
}

//创建一个Server的接口
func NewServer(ip string, port int) *Server {
	server := &Server{
		Ip:        ip,
		Port:      port,
		OnlineMap: make(map[string]*User),
		Message:   make(chan string),
	}
	return server
}

//监听message广播消息channel的goroutine 一旦有消息就发送给全部的user
func (this *Server) ListenMessage() {
	for {
		msg := <-this.Message
		this.mapLock.Lock()
		for _, user := range this.OnlineMap {
			user.C <- msg
		}
		this.mapLock.Unlock()
	}
}

//广播消息
func (this *Server) BroadCast(user *User, msg string) {
	sendMsg := "[" + user.Addr + "]" + user.Name + ":" + msg
	this.Message <- sendMsg
}

func (this *Server) Handler(conn net.Conn) {
	//当前连接的业务
	//fmt.Println("连接建立成功")

	user := NewUser(conn, this)

	//用户上线
	user.Online()

	//监听用户是否活跃
	isLive := make(chan bool)

	//接受客户端发送的消息
	go func() {
		buf := make([]byte, 4096)
		for {
			read, err := conn.Read(buf)
			if read == 0 {
				user.Offline()
				return
			}

			if err != nil && err != io.EOF {
				fmt.Println("conn read err ", err)
			}

			//提取用户的消息. 去除 \r\n
			msg := string(buf[:read])
			user.DoMessage(msg)

			isLive <- true
		}

	}()

	//当前handler 阻塞
	for {
		select {
		case <-isLive:
			//用户活跃
		case <-time.After(time.Second * 10):
			//超时
			user.SendMsg("你被踢了")
			close(user.C)
			conn.Close()
			return
		}
	}
}

//启动服务器
func (this *Server) start() {
	//socker listen
	listen, err := net.Listen("tcp", fmt.Sprintf("%s:%d", this.Ip, this.Port))
	if err != nil {
		fmt.Println("net.Listen err: ", err)
		return
	}

	//close listen socket
	defer listen.Close()

	//启动监听message的goroutine
	go this.ListenMessage()

	for {
		//accept
		conn, err := listen.Accept()
		if err != nil {
			fmt.Println("listener conn err:", err)
			continue
		}

		//do hanlder
		go this.Handler(conn)
	}

}
