package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	boolean registerMember(MemberDto memberDto);

	int idCheck(String checkId);

	boolean deleteUser(String userid);

	boolean updateUser(MemberDto memberDto);
}
