package com.ssafy.vue.service;


import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean regist(MemberDto memberDto) throws Exception;
	public int duplicate(String userid) throws Exception;
	public String find(String userid, String username) throws Exception;
	public boolean updateInfo(MemberDto memberDto) throws Exception;
	public boolean resign(String userid) throws Exception;
}
