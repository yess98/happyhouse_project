package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.dto.WordDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	boolean registerMember(MemberDto memberDto);

	int idCheck(String checkId);

	boolean deleteUser(String userid);

	boolean updateUser(MemberDto memberDto);
	
	public List<WordDto> selectWord();
}
