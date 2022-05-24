package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.User;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
// RestController는 이 클래스 안의 모든 메서드들이 @ResponseBody형태일떄 쓴다.
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;

	// 넘겨줘야하는 데이터와 상태코드를 같이 넘기기 위해서 
	@GetMapping("/user")
	public ResponseEntity<?> userList() throws Exception {
		List <User> list = userService.listMember();
		System.out.println(list); 
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<User>>(list, HttpStatus.OK); 
		
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		
	}
	@GetMapping("/user/{key}/{word}")
	public ResponseEntity<?> searchList(@PathVariable("key") String key, @PathVariable("word") String word) throws Exception {
		List <User> list = userService.searchMember(key, word); 
		
		System.out.println(list); 
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<User>>(list, HttpStatus.OK); 
		
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		
	}
	@PostMapping("/user")
	public ResponseEntity<?> userRegister(@RequestBody User user ) throws Exception{
		userService.insert(user);
		return new ResponseEntity<List<User>>(userService.listMember(), HttpStatus.OK); 
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userid) throws Exception{
		User user = userService.select(userid); 
		if (user != null ) {
			return new ResponseEntity<User>(user,HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		}
		
	}
	@PutMapping("/user")
	public ResponseEntity<?> userModify(@RequestBody User user) throws Exception{
		userService.update(user);
		return new ResponseEntity<List<User>>(userService.listMember(),HttpStatus.OK); 
	}
	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid")String userid ) throws Exception{
		userService.delete(userid);
		return new ResponseEntity<List<User>>(userService.listMember(), HttpStatus.OK); 
	}
}
