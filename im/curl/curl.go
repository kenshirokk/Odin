package main

import (
	"fmt"
	"runtime"
)


type user2 struct {
	name string
	age  int
}

type admin struct {
	user2
	level int
}

func (u *user2) notify() {
	fmt.Println(u)
}

func main() {
	ad := admin{
		user2:  user2{
			name: "admin",
			age:  10,
		},
		level: 5,
	}
	fmt.Println(ad)
	fmt.Println(ad.user2.name)
	fmt.Println(ad.name)
	ad.notify()
	ad.user2.notify()
	fmt.Println(ad.name == "admin")
	fmt.Println(runtime.NumCPU())
}
