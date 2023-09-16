package com.kk.weibo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.weibo.jsonentity.AlbumJson;
import com.kk.weibo.jsonentity.AlbumJson.AlbumDetail.Album;

public class TestAlbumJson {

	public static void main(String[] args) throws Exception {
		File f = new File("src/main/resources/album.json");
		ObjectMapper m = new ObjectMapper();
		AlbumJson aj = m.readValue(f, AlbumJson.class);
		for (Album a : aj.getData().getAlbum_list()) {
			System.out.println(a.getAlbum_id());
		}
	}
}
