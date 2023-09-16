package com.kk.weibo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

public class TestUsernameEnctypt {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String username = "shangbansharen@163.com";
		String ue = URLEncoder.encode(username, "UTF-8");
		System.out.println(ue);
		System.out.println(Base64.encodeBase64String(ue.getBytes()));
	}
}
