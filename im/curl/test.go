package main

import "fmt"

type user struct {
	name  string
	email string
	ip    IP
}

func (u user) notify() {
	fmt.Println("name:", u.name, ". email:", u.email, " .ip:", u.ip)
}

func (u user) changeEmail(e string) {
	u.email = e
	u.ip[0] = "shabi"
}

type IP []string

func (ip IP) notify() {
	fmt.Println(ip)
}

func (ip IP) change() {
	ip[0] = "fuck"
}

func changeUser(u user) {
	u.name = "changeuser"
}
func changeUser2(u *user)  {
	u.name = "changeUser 2"
}
func changeIp(i IP) {
	i[0] = "changeip"
}

func newUser() *user {
	u := user{
		name:  "zhangsan",
		email: "lisi",
		ip:    nil,
	}
	r:=&u
	fmt.Println(&r)
	return &u
}

func newIp() IP {
	ip := IP{"123"}
	r:=&ip
	fmt.Println(&r)
	return ip
}

func main() {
	bill := user{name: "bill", email: "bill@old.com", ip: IP{"hello", "world"}}
	bill.notify()

	lisa := &user{
		name:  "lisa",
		email: "lisa@old.com",
		ip:    IP{"hello", "world"},
	}
	lisa.notify()

	bill.changeEmail("bill@newmail.com")
	bill.notify()

	lisa.changeEmail("lisa@newmail.com")
	lisa.notify()

	ip1 := IP{"hello", "world"}
	ip2 := &IP{"hello", "world"}

	ip1.notify()
	ip2.notify()

	ip1.change()
	ip2.change()
	ip1.notify()
	ip2.notify()

	changeUser(bill)
	bill.notify()
	changeIp(ip1)
	ip1.notify()
	changeUser2(&bill)
	bill.notify()

	fmt.Println("------------------")

	u := newUser()
	fmt.Println(u)

	fmt.Println("=====")
	ip := newIp()
	r2:=&ip
	fmt.Println(&r2)
}
