package com.kk.weibo;

import org.apache.http.impl.client.HttpClients;

import com.kk.weibo.httpclient.LoginClient;

public class TestLogin {

	public static void main(String[] args) {
		LoginClient client = new LoginClient(HttpClients.createDefault());
		client.login();
	}
}
