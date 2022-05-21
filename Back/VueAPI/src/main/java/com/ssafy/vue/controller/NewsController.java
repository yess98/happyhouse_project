package com.ssafy.vue.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.NewsDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NewsController V1")
@RestController
@RequestMapping("/news")
public class NewsController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NewsDto>> newsCrawl() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		Date currentTime = new Date();
		List<NewsDto> list = new ArrayList<NewsDto>();
		String dTime = formatter.format(currentTime);
		String e_date = dTime;

		currentTime.setDate(currentTime.getDate() - 1);
		String s_date = formatter.format(currentTime);

		String query = "부동산";
		String s_from = s_date.replace(".", "");
		String e_to = e_date.replace(".", "");
		int page = 1;
		System.out.println("뉴스컨트롤러실행");
		while (page < 10) {
			String address = "https://search.naver.com/search.naver?where=news&query=" + query + "&sort=1&ds=" + s_date
					+ "&de=" + e_date + "&nso=so%3Ar%2Cp%3Afrom" + s_from + "to" + e_to + "%2Ca%3A&start="
					+ Integer.toString(page);
			// Document rawData = Jsoup.connect(address).timeout(5000).get();
			Document rawData = Jsoup.connect(address).get();
			System.out.println(address);
			Elements blogOption = rawData.select("div.news_wrap.api_ani_send");
			String realURL = "";
			String realTITLE = "";
			String imgsrc = "";
			
			for (Element option : blogOption) {
				realURL = option.select("a.news_tit").attr("href");
				realTITLE = option.select("a.news_tit").attr("title");
				imgsrc = option.select("img.thumb.api_get").attr("src");
				System.out.println(realTITLE);
				list.add(new NewsDto(realURL, realTITLE, imgsrc));
			}
			page += 10;
		}
		for (int i = 0; i < list.size(); i++) {
			NewsDto temp = list.get(i);
			System.out.println(temp.getTitle());
			System.out.println(temp.getUrl());
		}
		return new ResponseEntity<List<NewsDto>>(list, HttpStatus.OK);
	}

}
