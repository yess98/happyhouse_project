package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.User;


public interface UserService {
	
	int insert(User user);
	User select(String id);
	int update (User user); 
	int delete (String id); 
	int idCheck(String id) throws Exception; 
	User login(Map<String , String > map) throws Exception; 
	List<User> listMember() throws Exception; 
	List <User> searchMember(String key, String word) throws Exception; 
}
