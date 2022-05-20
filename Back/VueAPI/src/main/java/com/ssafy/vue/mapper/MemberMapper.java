package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.MemberDto;

public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;

	public MemberDto userInfo(String userid) throws SQLException;

	int registerMember(MemberDto memberDto);

	int idCheck(String checkId);

	int deleteUser(String userid);

	int updateUser(MemberDto memberDto);
}