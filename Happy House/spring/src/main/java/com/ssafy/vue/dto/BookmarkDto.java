package com.ssafy.vue.dto;

public class BookmarkDto {
	private int aptCode;
	private String userid;
	private String sidoName;
	private String gugunName;

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public BookmarkDto(int aptCode, String userid, String sidoName, String gugunName) {
		this.aptCode = aptCode;
		this.userid = userid;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
	}

}
