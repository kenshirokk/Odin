package com.kk.weibo.jsonentity;

import java.util.Date;

public class PhotoJson {

	private Boolean result;
	private Integer code;
	private String msg;
	private Date timestamp;
	private PhotoDetail data;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public PhotoDetail getData() {
		return data;
	}

	public void setData(PhotoDetail data) {
		this.data = data;
	}

	public static class PhotoDetail {

		private String album_id;
		private Integer total;
		private Photo[] photo_list;

		public String getAlbum_id() {
			return album_id;
		}

		public void setAlbum_id(String album_id) {
			this.album_id = album_id;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Photo[] getPhoto_list() {
			return photo_list;
		}

		public void setPhoto_list(Photo[] photo_list) {
			this.photo_list = photo_list;
		}

		public static class Photo {

			private String photo_id;
			private String album_id;
			private String uid;
			private String caption;
			private String caption_render;
			private String pic_host;
			private String pic_name;
			private Integer pic_type;
			private String pic_pid;
			private Boolean is_liked;
			private String source;
			private String from;
			private Integer type;
			private Boolean is_favorited;
			private Date timestamp;
			private String created_at;
			private String updated_at;
			private Boolean is_private;
			private Integer visible_type;
			private String latitude;
			private String longitude;
			private String tags;
			private String mid;
			private String visible;
			private String oid;
			private String feed_id;
			private Count count;
			
			/*	7 fields for head	*/
			private String status;
			private String property;
			private String pid;
			private String original_time;
			private String longtitude;
			private String geo_description;
			private String data;
			private Boolean is_cover;

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getProperty() {
				return property;
			}

			public void setProperty(String property) {
				this.property = property;
			}

			public String getPid() {
				return pid;
			}

			public void setPid(String pid) {
				this.pid = pid;
			}

			public String getOriginal_time() {
				return original_time;
			}

			public void setOriginal_time(String original_time) {
				this.original_time = original_time;
			}
			
			public String getLongtitude() {
				return longtitude;
			}

			public void setLongtitude(String longtitude) {
				this.longtitude = longtitude;
			}

			public String getGeo_description() {
				return geo_description;
			}

			public void setGeo_description(String geo_description) {
				this.geo_description = geo_description;
			}

			public String getData() {
				return data;
			}

			public void setData(String data) {
				this.data = data;
			}

			public Boolean getIs_cover() {
				return is_cover;
			}

			public void setIs_cover(Boolean is_cover) {
				this.is_cover = is_cover;
			}

			public String getPhoto_id() {
				return photo_id;
			}

			public void setPhoto_id(String photo_id) {
				this.photo_id = photo_id;
			}

			public String getAlbum_id() {
				return album_id;
			}

			public void setAlbum_id(String album_id) {
				this.album_id = album_id;
			}

			public String getUid() {
				return uid;
			}

			public void setUid(String uid) {
				this.uid = uid;
			}

			public String getCaption() {
				return caption;
			}

			public void setCaption(String caption) {
				this.caption = caption;
			}

			public String getCaption_render() {
				return caption_render;
			}

			public void setCaption_render(String caption_render) {
				this.caption_render = caption_render;
			}

			public String getPic_host() {
				return pic_host;
			}

			public void setPic_host(String pic_host) {
				this.pic_host = pic_host;
			}

			public String getPic_name() {
				return pic_name;
			}

			public void setPic_name(String pic_name) {
				this.pic_name = pic_name;
			}

			public Integer getPic_type() {
				return pic_type;
			}

			public void setPic_type(Integer pic_type) {
				this.pic_type = pic_type;
			}

			public String getPic_pid() {
				return pic_pid;
			}

			public void setPic_pid(String pic_pid) {
				this.pic_pid = pic_pid;
			}

			public Boolean getIs_liked() {
				return is_liked;
			}

			public void setIs_liked(Boolean is_liked) {
				this.is_liked = is_liked;
			}

			public String getSource() {
				return source;
			}

			public void setSource(String source) {
				this.source = source;
			}

			public String getFrom() {
				return from;
			}

			public void setFrom(String from) {
				this.from = from;
			}

			public Integer getType() {
				return type;
			}

			public void setType(Integer type) {
				this.type = type;
			}

			public Boolean getIs_favorited() {
				return is_favorited;
			}

			public void setIs_favorited(Boolean is_favorited) {
				this.is_favorited = is_favorited;
			}

			public Date getTimestamp() {
				return timestamp;
			}

			public void setTimestamp(Date timestamp) {
				this.timestamp = timestamp;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}

			public Boolean getIs_private() {
				return is_private;
			}

			public void setIs_private(Boolean is_private) {
				this.is_private = is_private;
			}

			public Integer getVisible_type() {
				return visible_type;
			}

			public void setVisible_type(Integer visible_type) {
				this.visible_type = visible_type;
			}

			
			public String getOid() {
				return oid;
			}

			public void setOid(String oid) {
				this.oid = oid;
			}

			public String getLatitude() {
				return latitude;
			}

			public void setLatitude(String latitude) {
				this.latitude = latitude;
			}

			public String getLongitude() {
				return longitude;
			}

			public void setLongitude(String longitude) {
				this.longitude = longitude;
			}

			public String getTags() {
				return tags;
			}

			public void setTags(String tags) {
				this.tags = tags;
			}

			
			public String getMid() {
				return mid;
			}

			public void setMid(String mid) {
				this.mid = mid;
			}

			
			public String getVisible() {
				return visible;
			}

			public void setVisible(String visible) {
				this.visible = visible;
			}

			
			public String getFeed_id() {
				return feed_id;
			}

			public void setFeed_id(String feed_id) {
				this.feed_id = feed_id;
			}

			public Count getCount() {
				return count;
			}

			public void setCount(Count count) {
				this.count = count;
			}

			public static class Count {
				private Integer clicks;
				private Integer retweets;
				private Integer likes;
				private Integer comments;

				public Integer getClicks() {
					return clicks;
				}

				public void setClicks(Integer clicks) {
					this.clicks = clicks;
				}

				public Integer getRetweets() {
					return retweets;
				}

				public void setRetweets(Integer retweets) {
					this.retweets = retweets;
				}

				public Integer getLikes() {
					return likes;
				}

				public void setLikes(Integer likes) {
					this.likes = likes;
				}

				public Integer getComments() {
					return comments;
				}

				public void setComments(Integer comments) {
					this.comments = comments;
				}

			}
		}
	}
}
