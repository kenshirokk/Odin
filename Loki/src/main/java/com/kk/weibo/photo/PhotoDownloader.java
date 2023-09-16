package com.kk.weibo.photo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kk.weibo.httpclient.LoginClient;
import com.kk.weibo.jsonentity.AlbumJson;
import com.kk.weibo.jsonentity.AlbumJson.AlbumDetail.Album;
import com.kk.weibo.jsonentity.PhotoJson;
import com.kk.weibo.jsonentity.PhotoJson.PhotoDetail.Photo;
import com.kk.weibo.run.WeiboRun;
import com.kk.weibo.utils.JsonHelper;
import com.kk.weibo.weiboentity.WeiboInfo;

public class PhotoDownloader {
	private static final Logger log = LoggerFactory.getLogger(PhotoDownloader.class);
	
	private HttpClient client;
	private static Configuration config;
	private ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	private static final String WEIBO_URL = "http://weibo.com/208600010";
	private static final String DISK_PATH = "e:/weibo/";
	private static final Integer COUNT = 40;
	
	public PhotoDownloader(HttpClient client) {
		super();
		this.client = client;
	}

	public PhotoDownloader() {
	}

	/**
	 * 下载图片封装方法
	 */
	public void startDownload() {
		WeiboInfo weiboInfo = getWeiboInfo(get(WEIBO_URL));
		String dickPath = DISK_PATH + weiboInfo.getNickName() + "/";
		//创建主文件夹	d:/weibo/xxx/
		checkOrCreateDir(dickPath);
		AlbumJson albumJson = getAlbumInfo(weiboInfo.getWeiboId());
		for (Album album : albumJson.getData().getAlbum_list()) {
			String albumId = album.getAlbum_id();
			final String albumDir = dickPath + album.getCaption() + "/";
			checkOrCreateDir(albumDir);
			int photos = album.getCount().getPhotos();	//照片数量
			if(photos == 0) {
				continue;
			}
			int pages = photos % COUNT == 0 ? photos / COUNT : photos / COUNT + 1;
			String preUrl = getConfig().getString("getPhotosByAlbumId");	//获取照片模板URL
			for (int i = 1; i <= pages; i++) {	//循环这么多页
				String photosUrl = MessageFormat.format(preUrl, weiboInfo.getWeiboId(), albumId, COUNT, i, album.getType());	//一次活的COUNT数量的照片
				PhotoJson photoJson = getPhotoInfo(photosUrl);
				for (final Photo photo : photoJson.getData().getPhoto_list()) {
					if(photo == null) {
						log.error("photo is null url is: {}", photosUrl);
					}
					final String picUrl = photo.getPic_host() + "/large/" + photo.getPic_name();
					log.info(picUrl);
					executorService.execute(new Runnable() {
						
						public void run() {
							BufferedInputStream bis = null;
							BufferedOutputStream bos = null;
							try {
								URL url = new URL(picUrl);
								URLConnection connection = url.openConnection();
								connection.setConnectTimeout(1000 * 60);
								bis = new BufferedInputStream(connection.getInputStream());
								bos = new BufferedOutputStream(new FileOutputStream(albumDir + convertPicName(photo.getPic_name())));
								byte[] bytes = new byte[1024 * 50];
								int len = 0;
								while((len = bis.read(bytes)) != -1) {
									bos.write(bytes, 0, len);
								}
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									if (bis != null) {
										bis.close();
										bis = null;
									}
									if (bos != null) {
										bos.flush();
										bos.close();
										bos = null;
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					});
				}
			}
		}
		executorService.shutdown();
	}

	/**
	 * get请求返回的HTML源代码 转换乱码
	 * @param url
	 * 
	 * @return
	 */
	private String get(String url) {
		log.info("request url :{}", url);
		Long start = System.currentTimeMillis();
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
//		try {
//			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		log.info("request url {} end, use :{} miles", url, System.currentTimeMillis() - start);
		return result;
	}
	
	/**
	 * 解析微博个人主页  得到用户nickname
	 * @param result
	 * @return
	 */
	private WeiboInfo getWeiboInfo(String result) {
		WeiboInfo weiboInfo = new WeiboInfo();
		
		//$CONFIG['onick']='桃洛莎'
		Pattern pattern = Pattern.compile("(\\$CONFIG\\[\\'onick\\'\\]=\\')(.*)(\\')");
		Matcher matcher = pattern.matcher(result);
		if(matcher.find()) {
			weiboInfo.setNickName(matcher.group(2));
		}
		
		//$CONFIG['oid']='2636850803'
		pattern = Pattern.compile("(\\$CONFIG\\[\\'oid\\'\\]=\\')(.*)(\\')");
		matcher = pattern.matcher(result);
		if(matcher.find()) {
			weiboInfo.setWeiboId(matcher.group(2));
		}
		return weiboInfo;
	}
	
	/**
	 * 检查文件夹  如果不存在  就创建一个
	 * @param path
	 */
	private void checkOrCreateDir(String path) {
		File file = new File(path);
		if(!file.exists() || !file.isDirectory()) {
			log.info("directory not exist, gonna create directory: {}", path);
			file.mkdirs();
		}
	}
	
	private String convertPicName(String picName) {
		if(picName.indexOf(".") == -1) {
			return picName + ".jpg";
		}
		return picName;
	}
	
	/**
	 * 根据配置文件中的地址  活的所有相册的大致信息
	 * 相册数量
	 * 相册名称
	 * 相册中照片数量
	 * 通过这些数据循环下载
	 * @param weiboId
	 * @return
	 */
	private AlbumJson getAlbumInfo(String weiboId) {
		String url = getConfig().getString("getAllAlbums");
		url = MessageFormat.format(url, weiboId);
		AlbumJson albumJson = null;
		try {
			albumJson = JsonHelper.getAlbumJson(get(url));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return albumJson;
	}
	
	private PhotoJson getPhotoInfo(String url) {
		PhotoJson photoJson = null;
		try {
			photoJson = JsonHelper.getPhotoJson(get(url));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return photoJson;
	}
	
	private Configuration getConfig() {
		if(config == null) {
			try {
				config = new PropertiesConfiguration("config.properties");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		return config;
	}
	
	public void startDownload2() {
		WeiboInfo weiboInfo = getWeiboInfo(get(WEIBO_URL));
		String dickPath = DISK_PATH + weiboInfo.getNickName() + "/";
		//创建主文件夹	d:/weibo/xxx/
		checkOrCreateDir(dickPath);
		AlbumJson albumJson = getAlbumInfo(weiboInfo.getWeiboId());
		final CloseableHttpAsyncClient syclient = HttpAsyncClients.createDefault();
		for (Album album : albumJson.getData().getAlbum_list()) {
			syclient.start();
			String albumId = album.getAlbum_id();
			final String albumDir = dickPath + album.getCaption() + "/";
			checkOrCreateDir(albumDir);
			int photos = album.getCount().getPhotos();	//照片数量
			if(photos == 0) {
				continue;
			}
			int pages = photos % COUNT == 0 ? photos / COUNT : photos / COUNT + 1;
			String preUrl = getConfig().getString("getPhotosByAlbumId");	//获取照片模板URL
			for (int i = 1; i <= pages; i++) {	//循环这么多页
				String photosUrl = MessageFormat.format(preUrl, weiboInfo.getWeiboId(), albumId, COUNT, i, album.getType());	//一次活的COUNT数量的照片
				PhotoJson photoJson = getPhotoInfo(photosUrl);
				for (final Photo photo : photoJson.getData().getPhoto_list()) {
					final String picUrl = photo.getPic_host() + "/large/" + photo.getPic_name();
					log.info(picUrl);
					executorService.execute(new Runnable() {
						
						public void run() {
							BufferedInputStream bis = null;
							BufferedOutputStream bos = null;
							
							HttpGet get = new HttpGet(picUrl);
							Future<HttpResponse> future = syclient.execute(get, null);
							
							try {
								bis = new BufferedInputStream(future.get().getEntity().getContent());
								bos = new BufferedOutputStream(new FileOutputStream(albumDir + convertPicName(photo.getPic_name())));
								byte[] bytes = new byte[1024 * 50];
								int len = 0;
								while((len = bis.read(bytes)) != -1) {
									bos.write(bytes, 0, len);
								}
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (IllegalStateException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							} finally {
								try {
									if (bis != null) {
										bis.close();
										bis = null;
									}
									if (bos != null) {
										bos.flush();
										bos.close();
										bos = null;
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					});
				}
			}
		}
		executorService.shutdown();
		boolean res = true;
		while(res) {
			if(executorService.isTerminated()) {
				try {
					syclient.close();
					res = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

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
