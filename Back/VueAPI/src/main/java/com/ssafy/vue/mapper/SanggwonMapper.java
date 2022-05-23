package com.ssafy.vue.mapper;

import java.sql.SQLException;

import java.util.List;

import com.ssafy.vue.dto.SanggwonDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface SanggwonMapper {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SanggwonDto> getDongInGugun(String gugun) throws SQLException; 
	List<SanggwonDto> getSanggwonInDong(String dongCode, String type) throws SQLException;
//	List<SanggwonSportDto> getSanggwonSportInDong(String dong) throws SQLException;
//	List<SanggwonEduDto2> getSanggwonEduInDong(String dong) throws SQLException;
//	List<SanggwonFoodDto> getSanggwonFoodInDong(String dong) throws SQLException;
//	List<SanggwonCultureDto> getSanggwonCultureInDong(String dong) throws SQLException;
//	List<SanggwonRetailDto> getSanggwonRetailInDong(String dong) throws SQLException;
//	List<SanggwonServiceDto> getSanggwonServiceInDong(String dong) throws SQLException;
	
}
