package com.zy.service;

import java.util.List;

import com.zy.entity.User;

public interface UserService {
	
public List<User> selectUserList();
	
	public User selectUserById(int id);
	
	public User selectUserByName(String name);
	
	public User selectUserByEmail(String email);
	
	public User selectUserByPhone(String phone);
	
	public int insertUser(User user);
	
	public int deleteUser(int id);
	
	public int updateUser(User user);
	
	public String register(User user, String confirm_pwd, String in_checkcode, String checkcode);
	
	public Object login(String name, String pwd, String in_checkcode, String checkcode);

}
