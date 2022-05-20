package com.ssafy.vue.dto;

public class NewsDto {
	
	String url;
	String title;
	public NewsDto(String url, String title) {
		super();
		this.url = url;
		this.title = title;
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
