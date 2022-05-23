package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.dto.WordDto;
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
	public boolean registerMember(MemberDto memberDto) {
		return memberMapper.registerMember(memberDto) == 1;
	}

	@Override
	public int idCheck(String checkId) {
		return memberMapper.idCheck(checkId);
	}

	@Override
	@Transactional
	public boolean deleteUser(String userid) {
		return memberMapper.deleteUser(userid) == 1;
	}

	@Override
	@Transactional
	public boolean updateUser(MemberDto memberDto) {
		return memberMapper.updateUser(memberDto) == 1;
	}
	
	@Override
	public List<WordDto> selectWord() {
		return memberMapper.selectWord();
	}
}
