package com.ssafy.vue.dto;

public class ReserveDto {
	private int aptCode;
	private String userid;
	private String reserveDate;

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

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public ReserveDto(int aptCode, String userid, String reserveDate) {
		this.aptCode = aptCode;
		this.userid = userid;
		this.reserveDate = reserveDate;
	}

}
