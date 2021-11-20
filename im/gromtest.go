package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"net/http"
)

type Gormm struct {
	ID   int
	Name string
	Age  int
}

func (Gormm) TableName() string {
	return "gorm"
}

const CONN2 = "host=localhost user=postgres password=password dbname=pictures port=5432 sslmode=disable"

func main() {
	db, err := gorm.Open(postgres.Open(CONN2), &gorm.Config{})
	if err != nil {
		fmt.Println("连接数据库出错:", err)
	}

	r := gin.Default()
	r.LoadHTMLGlob("view/*")

	r.GET("/", func(c *gin.Context) {
		ps := make([]Picture, 0)
		db.Order("name ASC, number ASC").Find(&ps)
		c.HTML(http.StatusOK, "index.html", gin.H{"res":ps})
	})
	r.Run(":8080")

}
