package main

import "fmt"

type user struct {
	name  string
	email string
}

func (u user) notify() {
	fmt.Println("name:", u.name, ". email:", u.email)
}

func (u *user) changeEmail(e string) {
	u.email = e
}

func main() {
	bill := user{name: "bill", email: "bill@old.com"}
	bill.notify()

	lisa := &user{
		name:  "lisa",
		email: "lisa@old.com",
	}
	lisa.notify()

	bill.changeEmail("bill@newmail.com")
	bill.notify()

	lisa.changeEmail("lisa@newmail.com")
	lisa.notify()
}
