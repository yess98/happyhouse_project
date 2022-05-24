package com.ssafy.happyhouse.model.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.User;
import com.ssafy.happyhouse.model.mapper.UserRepo;

/**
 * 빈으로 등록될 수 있도록 @Service를 선언한다.
 *
 */
@Service
public class UserServiceImpl implements UserService {
	/**
	 * has a 관계로 사용할 UserRepo 타입의 repo를 선언한다.
	 */
	
	private UserRepo repo;
	
	/**
	 * 생성자를 통해 UserRepo를 주입받는다.
	 * UserRepo 타입의 빈을 주입받기 위해 @Autowired를 사용한다.
	 * @param repo
	 */
	@Autowired
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}

	@Override
	public User select(String id) {
		return repo.select(id);
	}

	@Override
	public int insert(User user) {
		return repo.insert(user);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return repo.update(user); 
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return repo.delete(id); 
	}

	@Override
	public int idCheck(String id) throws Exception {
		return repo.idCheck(id); 
	}

	@Override
	public User login(Map<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		return repo.login(map); 
	}

	@Override
	public List<User> listMember() throws Exception {
		// TODO Auto-generated method stub
		return repo.listMember();
	}

	@Override
	public List<User> searchMember(String key, String word) throws Exception {
		// TODO Auto-generated method stub
		return repo.searchMember(key,word); 
	}

}
