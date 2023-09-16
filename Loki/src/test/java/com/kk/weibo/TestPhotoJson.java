package com.kk.weibo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.weibo.jsonentity.PhotoJson;
import com.kk.weibo.jsonentity.PhotoJson.PhotoDetail.Photo;

public class TestPhotoJson {

	public static void main(String[] args) throws Exception {
		File f = new File("src/main/resources/tt.json");
		ObjectMapper m = new ObjectMapper();
		PhotoJson pj = m.readValue(f, PhotoJson.class);
		for (Photo a : pj.getData().getPhoto_list()) {
			System.out.println(a.getPhoto_id() + "\t" +a.getPic_host() + "\t" + a.getPic_name());
		}
	}
}
