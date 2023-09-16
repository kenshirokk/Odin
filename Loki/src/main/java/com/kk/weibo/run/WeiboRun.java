package com.kk.weibo.run;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kk.weibo.httpclient.LoginClient;
import com.kk.weibo.photo.PhotoDownloader;

public class WeiboRun {
	private static final Logger log = LoggerFactory.getLogger(WeiboRun.class);

	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
		Long loginStart = System.currentTimeMillis();
		//登陆
		LoginClient loginClient = new LoginClient(client);
		loginClient.login();
		log.info("login use: {} miles", System.currentTimeMillis() - loginStart);
		
		Long downloadStart = System.currentTimeMillis();
		//下载
		PhotoDownloader photoDownloader = new PhotoDownloader(client);
		photoDownloader.startDownload2();
		log.info("download use: {} miles", System.currentTimeMillis() - downloadStart);
		System.out.println("OK");
		
	}
}
