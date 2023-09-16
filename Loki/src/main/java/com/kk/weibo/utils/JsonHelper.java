package com.kk.weibo.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.weibo.jsonentity.AlbumJson;
import com.kk.weibo.jsonentity.PhotoJson;
import com.kk.weibo.jsonentity.PreLoginJson;

public class JsonHelper {
	private static ObjectMapper mapper;

	public static ObjectMapper getMapper() {
		if(mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static PreLoginJson getPreLoginJson(String json) throws JsonParseException, JsonMappingException, IOException {
		return getMapper().readValue(json, PreLoginJson.class);
	}

	public static AlbumJson getAlbumJson(String json) throws JsonParseException, JsonMappingException, IOException {
		return getMapper().readValue(json, AlbumJson.class);
	}
	
	public static PhotoJson getPhotoJson(String json) throws JsonParseException, JsonMappingException, IOException {
		return getMapper().readValue(json, PhotoJson.class);
	}
	
}
