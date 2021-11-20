package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"io/ioutil"
	"regexp"
	"strconv"
	"strings"
)

const CONN = "postgres://postgres:password@localhost/pictures?sslmode=disable"
const DIR = "K:/picture/comic/健身教練"

func main() {
	db, err := sql.Open("postgres", CONN)
	defer db.Close()
	if err != nil {
		fmt.Println("连接失败", err)
	}

	files, err := ioutil.ReadDir(DIR)
	if err != nil {
		fmt.Println("读取目录失败:", err)
	}
	re := regexp.MustCompile("[0-9]+")
	for _, file := range files {
		fileName := file.Name()

		path := DIR + "/" + fileName
		pa := "健身教練/" + fileName
		if strings.Contains(fileName, "logo") {
			continue
		}
		s := strings.Split(fileName, "_")
		a := re.FindString(s[0])
		b := re.FindString(s[1])
		aa := fmt.Sprintf("%04s", a)
		bb, _ := strconv.Atoi(b)
		picture := Picture{RootName: "健身教練", Name: aa, Number: bb, Deleted: false}
		_, err = db.Exec("insert into picture(root_name, name, number, deleted, path, Pa) "+
			"values($1,$2,$3,$4,$5,$6)",
			picture.RootName, picture.Name, picture.Number, picture.Deleted, path, pa)
		if err != nil {
			fmt.Println("插入失败", err)
		}
	}

	//_, err = db.Exec("insert into picture(root_name, name, number, deleted) values($1,$2,$3,$4)", picture.rootName,
	//	picture.name, picture.number, picture.deleted)
	//if err != nil {
	//	fmt.Println("插入失败", err)
	//}
}
