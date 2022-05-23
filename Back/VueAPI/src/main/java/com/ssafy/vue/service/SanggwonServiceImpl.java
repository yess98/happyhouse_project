package com.ssafy.vue.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.SanggwonDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.mapper.SanggwonMapper;
@Service
public class SanggwonServiceImpl implements SanggwonService {
	@Autowired
	private SanggwonMapper sanggwonMapper; 
	@Override
	public List<SidoGugunCodeDto> getSido() throws SQLException {
		// TODO Auto-generated method stub
		return sanggwonMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException {
		return sanggwonMapper.getGugunInSido(sido);
	}

	@Override
	public List<SanggwonDto> getDongInGugun(String gugun) throws SQLException {
		// TODO Auto-generated method stub
		return sanggwonMapper.getDongInGugun(gugun);
	}

	@Override
	public List<SanggwonDto> getSanggwonInDong(String dongCode, String type) throws SQLException {
		// TODO Auto-generated method stub
		return sanggwonMapper.getSanggwonInDong(dongCode,type);
	}

}
