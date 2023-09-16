package com.kk.weibo.httpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kk.weibo.jsonentity.PreLoginJson;
import com.kk.weibo.utils.JsonHelper;

public class LoginClient {
	private static final Logger log = LoggerFactory.getLogger(LoginClient.class);
	
	private HttpClient client;
	private static Configuration config;

	public LoginClient(HttpClient client) {
		super();
		this.client = client;
	}

	public PreLoginJson preLogin() {
		log.info("preLogin Start");
		Long start = System.currentTimeMillis();
		String preLoginUrl = getConfig().getString("preloginUrl");
		String result = get(preLoginUrl + new Date().getTime());
		PreLoginJson preLoginJson = null;
		try {
			preLoginJson = JsonHelper.getPreLoginJson(result.substring(result.indexOf("{")));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("preLogin end");
		log.info("prelogin use :{} miles", System.currentTimeMillis() - start);
		return preLoginJson;
	}
	
	public void login() {
		
		PreLoginJson preLoginJson = preLogin();
		log.info("login start");
		Long start = System.currentTimeMillis();
		//prepare post
		HttpPost post = new HttpPost("http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.11)");
		setHeader(post);
		setNameValuePairs(post, preLoginJson);

		//登陆后的返回html  截取url部分
		String result = post(post);
		result = result.substring(result.indexOf("http://passport.weibo.com"), result.indexOf("code=0") + 6);
		get(result);		//登陆成功
		log.info("login end");
		log.info("login use :{} miles", System.currentTimeMillis() - start);
	}

	
	
	private String get(String url) {
		HttpGet get = new HttpGet(url);
		String result = null;
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		return result;
	}
	
	private String post(HttpPost post) {
		String result = null;
		try {
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 设置登陆post 头信息
	 * @param post
	 */
	private void setHeader(HttpPost post) {
		post.setHeader("Accept", getConfig().getString("Accept"));
		post.setHeader("Content-Type", getConfig().getString("Content-Type"));
		post.setHeader("Origin", getConfig().getString("Origin"));
		post.setHeader("Referer", getConfig().getString("Referer"));
		post.setHeader("User-Agent", getConfig().getString("User-Agent"));
	}
	
	/**
	 * 设置登陆post 请求参数
	 * @param post
	 * @param preLoginJson
	 */
	private void setNameValuePairs(HttpPost post, PreLoginJson preLoginJson) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("entry", "weibo"));
		nvps.add(new BasicNameValuePair("gateway", "1"));
		nvps.add(new BasicNameValuePair("from", ""));
		nvps.add(new BasicNameValuePair("savestate", "7"));
		nvps.add(new BasicNameValuePair("useticket", "1"));
		nvps.add(new BasicNameValuePair("pagerefer", "http://login.sina.com.cn/sso/logout.php?entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%252F"));
		nvps.add(new BasicNameValuePair("vsnf", "1"));
		//su
		try {
			nvps.add(new BasicNameValuePair("su", encodeUsername(getConfig().getString("username"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		nvps.add(new BasicNameValuePair("service", "miniblog"));
		//servertime
		nvps.add(new BasicNameValuePair("servertime", preLoginJson.getServertime().getTime()+""));
		//nonce
		nvps.add(new BasicNameValuePair("nonce", preLoginJson.getNonce().toString()));
		nvps.add(new BasicNameValuePair("pwencode", "rsa2"));
		//rsakv
		nvps.add(new BasicNameValuePair("rsakv", preLoginJson.getRsakv().toString()));
		//sp
		try {
			nvps.add(new BasicNameValuePair("sp", encodePassword(getConfig().getString("password"), preLoginJson.getServertime().getTime()+"", preLoginJson.getNonce().toString(), preLoginJson.getPubkey().toString())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		nvps.add(new BasicNameValuePair("encoding:", "UTF-8"));
		nvps.add(new BasicNameValuePair("prelt", "482"));
		nvps.add(new BasicNameValuePair("url", "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
		nvps.add(new BasicNameValuePair("returntype", "META"));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public Configuration getConfig() {
		if(config == null) {
			try {
				config = new PropertiesConfiguration("config.properties");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		return config;
	}
	
	/**
	 * 加密用户名
	 * @param username
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String encodeUsername(String username) throws UnsupportedEncodingException {
		return Base64.encodeBase64String(URLEncoder.encode(username, "UTF-8").getBytes());
	}
	
	/**
	 * 加密密码
	 * @param password
	 * @param servertime
	 * @param nonce
	 * @param rsaPubkey
	 * @return
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	private String encodePassword(String password, String servertime, String nonce, String rsaPubkey) throws ScriptException, FileNotFoundException, NoSuchMethodException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("javascript");
		FileReader reader = new FileReader(new File("src/main/resources/ssologin.js"));
		se.eval(reader);
		String pwd = null;
		if(se instanceof Invocable) {
			Invocable invocable = (Invocable) se;
			pwd = invocable.invokeFunction("getpass", password, servertime, nonce, rsaPubkey).toString();
		}
		return pwd;
	}
	
}
