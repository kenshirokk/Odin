package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"golang.org/x/net/proxy"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"sync"
	"time"
)

var waitG = new(sync.WaitGroup)

func main() {
	file := "C:/Users/Kenshiro/Desktop/zinhyo1004.json"
	//file := "C:/Users/Kenshiro/Desktop/lililihe.json"
	bytes, _ := ioutil.ReadFile(file)

	var jsonObj map[string]interface{}
	json.Unmarshal(bytes, &jsonObj)

	urls := make([]string, 2, 4)
	i := jsonObj["GraphImages"].([]interface{})
	for _, i3 := range i {
		m := i3.(map[string]interface{})
		i2 := m["urls"].([]interface{})
		for _, i4 := range i2 {
			if i4 == nil || i4.(string) == "" || len(i4.(string)) == 0 {
				continue
			}
			urls = append(urls, i4.(string))
		}
	}

	now := time.Now()
	for i, url := range urls {
		//name := fmt.Sprintf("%s%d%s", "I:/GitHub/Odin/im/pic2/", i+1, ".jpg")
		name := fmt.Sprintf("%s%d%s", "I:/GitHub/Odin/im/pic3/", i+1, ".jpg")
		if url == "" || len(url) == 0 {
			fmt.Println("nil ===", i)
		} else {
			waitG.Add(1)
			go download(url, name)
		}
	}
	waitG.Wait()
	fmt.Printf("下载总时间:%v\n", time.Now().Sub(now))

}

func download(url string, name string) {
	socks5, _ := proxy.SOCKS5("tcp", "127.0.0.1:1080", nil, proxy.Direct)
	t := &http.Transport{}

	httpclient := &http.Client{Transport: t}
	t.Dial = socks5.Dial

	get, err := httpclient.Get(url)

	if err != nil {
		fmt.Println(err)
		return
	}

	create, _ := os.Create(name)
	wt := bufio.NewWriter(create)

	io.Copy(wt, get.Body)
	wt.Flush()
	defer get.Body.Close()
	defer create.Close()
	waitG.Done()

}
