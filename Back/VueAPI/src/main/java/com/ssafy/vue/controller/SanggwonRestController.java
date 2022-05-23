package com.ssafy.vue.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.SanggwonDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.SanggwonService;

@RestController
@RequestMapping("/info")
@CrossOrigin("*")
public class SanggwonRestController {
	private final Logger logger = LoggerFactory.getLogger(SanggwonRestController.class);
	
	@Autowired
	private SanggwonService sanggwonService; 
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", sanggwonService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(sanggwonService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(sanggwonService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SanggwonDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SanggwonDto>>(sanggwonService.getDongInGugun(gugun), HttpStatus.OK);
	}
	@GetMapping("/type")
	public ResponseEntity<List<SanggwonDto>> type(@RequestParam("dongCode") String dongCode, @RequestParam("type") String type) throws Exception {
		return new ResponseEntity<List<SanggwonDto>>(sanggwonService.getSanggwonInDong(dongCode, type), HttpStatus.OK);
	}

}
