package com.ssafy.vue.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean regist(MemberDto memberDto) throws Exception {
		return memberMapper.regist(memberDto);
	}

	@Override
	public int duplicate(String userid) throws SQLException {
		return memberMapper.duplicate(userid);
	}

	@Override
	public String find(String userid, String username) throws Exception {
		return memberMapper.find(userid, username);
	}

	@Override
	public boolean updateInfo(MemberDto memberDto) throws Exception {
		return memberMapper.updateInfo(memberDto);
	}

	@Override
	public boolean resign(String userid) throws SQLException {
		return memberMapper.resign(userid);
	}

}
