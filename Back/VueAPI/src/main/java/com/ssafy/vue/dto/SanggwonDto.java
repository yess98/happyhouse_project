package com.ssafy.vue.dto;

public class SanggwonDto {
	private String no;
	private String name;
	private String type;
	private String sidoname; 
	private String sigungucode; 
	private String sigunguname; 
	private String dongcode;
	private String doroname; 
	private String lon; 
	private String lat;
	private String dongname;
	
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSidoname() {
		return sidoname;
	}
	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}
	public String getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(String sigungucode) {
		this.sigungucode = sigungucode;
	}
	public String getSigunguname() {
		return sigunguname;
	}
	public void setSigunguname(String sigunguname) {
		this.sigunguname = sigunguname;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDoroname() {
		return doroname;
	}
	public void setDoroname(String doroname) {
		this.doroname = doroname;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
