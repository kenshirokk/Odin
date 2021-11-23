package main

import (
	"fmt"
	"net"
)

//119,119,119,46,98,97,105,100,117,46,99,111,109 1 187
//98,100,46,112,50,112,46,104,117,121,97,46,99,111,109 1 187
//115,116,97,116,119,117,112,46,104,117,121,97,46,99,111,109,1,187
func main() {

	//listen, _ := net.Listen("tcp", ":1088")
	//for {
	//	client, _ := listen.Accept()
	//	handerClient(client)
	//}
	b := []byte{1,187}
	u := string(b)
	fmt.Println(u)

	//str:=443
	//d2:=[]byte(str)
	//fmt.Println(d2)
}

func handerClient(client net.Conn) {
	defer client.Close()
	var buf [200]byte
	client.Read(buf[:])
	fmt.Println(buf)
	client.Write([]byte{0x05, 0x00})
	client.Read(buf[:])
	fmt.Println(buf)
}
