package com.kk.weibo;

import java.io.IOException;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestTT13Json {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
		client.start();
		client.close();
	}
}
