package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.MemberDto;


public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public boolean regist(MemberDto memberDto) throws SQLException;
	public int duplicate(String userid) throws SQLException;
	public String find(String userid, String username) throws SQLException;
	public boolean updateInfo(MemberDto memberDto) throws SQLException;
	public boolean resign(String userid) throws SQLException;
}