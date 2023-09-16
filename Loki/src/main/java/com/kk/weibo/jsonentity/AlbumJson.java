package com.kk.weibo.jsonentity;

import java.util.Date;

public class AlbumJson {

	private Boolean result;
	private Integer code;
	private String msg;
	private Date timestamp;
	private AlbumDetail data;

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

	public AlbumDetail getData() {
		return data;
	}

	public void setData(AlbumDetail data) {
		this.data = data;
	}

	public static class AlbumDetail {

		private Integer total;
		private Album[] album_list;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Album[] getAlbum_list() {
			return album_list;
		}

		public void setAlbum_list(Album[] album_list) {
			this.album_list = album_list;
		}

		public static class Album {
			private String album_id;
			private String uid;
			private Integer property;
			private Integer status;
			private Integer type;
			private String source;
			private Integer album_order;
			private String created_at;
			private String caption;
			private String description;
			private String cover_pic;
			private String cover_photo_id;
			private String question;
			private String answer;
			private String updated_at;
			private Date timestamp;
			private Date updated_at_int;
			private Boolean is_favorited;
			private Boolean is_private;
			private String thumb120_pic;
			private String thumb300_pic;
			private String sq612_pic;
			private String usort;
			private Count count;

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

			public Integer getProperty() {
				return property;
			}

			public void setProperty(Integer property) {
				this.property = property;
			}

			public Integer getStatus() {
				return status;
			}

			public void setStatus(Integer status) {
				this.status = status;
			}

			public Integer getType() {
				return type;
			}

			public void setType(Integer type) {
				this.type = type;
			}

			public String getSource() {
				return source;
			}

			public void setSource(String source) {
				this.source = source;
			}

			public Integer getAlbum_order() {
				return album_order;
			}

			public void setAlbum_order(Integer album_order) {
				this.album_order = album_order;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getCaption() {
				return caption;
			}

			public void setCaption(String caption) {
				this.caption = caption;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getCover_pic() {
				return cover_pic;
			}

			public void setCover_pic(String cover_pic) {
				this.cover_pic = cover_pic;
			}

			public String getCover_photo_id() {
				return cover_photo_id;
			}

			public void setCover_photo_id(String cover_photo_id) {
				this.cover_photo_id = cover_photo_id;
			}

			public String getQuestion() {
				return question;
			}

			public void setQuestion(String question) {
				this.question = question;
			}

			public String getAnswer() {
				return answer;
			}

			public void setAnswer(String answer) {
				this.answer = answer;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}

			public Date getTimestamp() {
				return timestamp;
			}

			public void setTimestamp(Date timestamp) {
				this.timestamp = timestamp;
			}

			public Date getUpdated_at_int() {
				return updated_at_int;
			}

			public void setUpdated_at_int(Date updated_at_int) {
				this.updated_at_int = updated_at_int;
			}

			public Boolean getIs_favorited() {
				return is_favorited;
			}

			public void setIs_favorited(Boolean is_favorited) {
				this.is_favorited = is_favorited;
			}

			public Boolean getIs_private() {
				return is_private;
			}

			public void setIs_private(Boolean is_private) {
				this.is_private = is_private;
			}

			public String getThumb120_pic() {
				return thumb120_pic;
			}

			public void setThumb120_pic(String thumb120_pic) {
				this.thumb120_pic = thumb120_pic;
			}

			public String getThumb300_pic() {
				return thumb300_pic;
			}

			public void setThumb300_pic(String thumb300_pic) {
				this.thumb300_pic = thumb300_pic;
			}

			public String getSq612_pic() {
				return sq612_pic;
			}

			public void setSq612_pic(String sq612_pic) {
				this.sq612_pic = sq612_pic;
			}

			
			public String getUsort() {
				return usort;
			}

			public void setUsort(String usort) {
				this.usort = usort;
			}

			public Count getCount() {
				return count;
			}

			public void setCount(Count count) {
				this.count = count;
			}

			public static class Count {

				private Integer photos;
				private Integer likes;
				private Integer comments;
				private Integer retweets;

				public Integer getPhotos() {
					return photos;
				}

				public void setPhotos(Integer photos) {
					this.photos = photos;
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

				public Integer getRetweets() {
					return retweets;
				}

				public void setRetweets(Integer retweets) {
					this.retweets = retweets;
				}

			}
		}
	}
}
