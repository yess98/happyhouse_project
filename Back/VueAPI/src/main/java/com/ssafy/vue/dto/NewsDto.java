package com.ssafy.vue.dto;

public class NewsDto {
	
	String url;
	String title;
	String imgsrc;
	
	public NewsDto(String url, String title, String imgsrc) {
		super();
		this.url = url;
		this.title = title;
		this.imgsrc = imgsrc;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
