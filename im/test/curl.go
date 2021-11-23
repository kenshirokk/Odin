package main

import (
	"fmt"
	"runtime"
)


type user struct {
	name string
	age  int
}

type admin struct {
	user
	level int
}

func (u *user) notify() {
	fmt.Println(u)
}

func main() {
	ad := admin{
		user:  user{
			name: "admin",
			age:  10,
		},
		level: 5,
	}
	fmt.Println(ad)
	fmt.Println(ad.user.name)
	fmt.Println(ad.name)
	ad.notify()
	ad.user.notify()
	fmt.Println(ad.name == "admin")

}
