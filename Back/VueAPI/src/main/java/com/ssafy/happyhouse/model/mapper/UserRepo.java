package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import java.util.Map;

import com.ssafy.happyhouse.model.User;




public interface UserRepo {
	int insert(User user);
	User select(String id);
	int update (User user); 
	int delete (String id); 
	List<User> listMember() throws Exception;
	int idCheck(String checkId) throws Exception; 
	User login(Map<String, String> map) throws Exception;
	List <User> searchMember(String key, String word) throws Exception; 
}
