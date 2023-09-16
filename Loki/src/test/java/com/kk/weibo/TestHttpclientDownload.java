package com.kk.weibo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

public class TestHttpclientDownload {

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException, ExecutionException {
		String url = "http://ww2.sinaimg.cn/large/50868c3fjw8eakq2c9uvwj20u00u0abo.jpg";
		
		CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
		client.start();
		
		Future<HttpResponse> future = client.execute(new HttpGet(url), null);
		
		HttpResponse response = future.get();
		System.out.println(EntityUtils.toString(response.getEntity()));
	}
}
