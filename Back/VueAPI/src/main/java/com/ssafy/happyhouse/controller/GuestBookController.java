package com.ssafy.happyhouse.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.happyhouse.model.service.GuestBookService;
import com.ssafy.util.PageNavigation;

//방명록 처리용 controller
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	private static final Logger logger = LoggerFactory.getLogger(GuestBookController.class);

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private GuestBookService guestBookService;

	
	
	@GetMapping("/register")
	public String register() {
		return "guestbook/write";
	}

	@PostMapping("/register")
	@Transactional
	public String register(GuestBookDto guestBookDto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		//MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		//guestBookDto.setUserId(memberDto.getUserId());
		guestBookDto.setUser_id("ssafy");
		guestBookService.registerArticle(guestBookDto);
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글작성 성공!!!");
		return "redirect:/guestbook/list";
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();

		String spp = map.get("spp"); // size per page (페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		List<GuestBookDto> list = guestBookService.listArticle(map);
		PageNavigation pageNavigation = guestBookService.makePageNavigation(map);
		mav.addObject("articles", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("guestbook/list");
		return mav;
	}

	@GetMapping("/modify")
	public ModelAndView modify(@RequestParam("no") int no) throws Exception {
		ModelAndView mav = new ModelAndView();
		GuestBookDto guestBookDto = guestBookService.getArticle(no);
		mav.addObject("article", guestBookDto);
		mav.setViewName("guestbook/modify");
		return mav;
	}

	@PostMapping("/modify")
	public String modify(GuestBookDto guestBookDto, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		guestBookService.updateArticle(guestBookDto);
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글수정 성공!!!");
		return "redirect:/guestbook/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, Model model, RedirectAttributes redirectAttributes)
			throws Exception {
		guestBookService.deleteArticle(articleNo, servletContext.getRealPath("/upload"));
		redirectAttributes.addAttribute("pg", 1);
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		redirectAttributes.addFlashAttribute("msg", "글삭제 성공!!!");
		return "redirect:/guestbook/list";
	}

}
