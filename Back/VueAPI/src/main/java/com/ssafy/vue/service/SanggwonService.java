package com.ssafy.vue.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.SanggwonDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;


public interface SanggwonService {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SanggwonDto> getDongInGugun(String gugun) throws SQLException;
	List<SanggwonDto> getSanggwonInDong(String dongCode, String type) throws SQLException;
}
