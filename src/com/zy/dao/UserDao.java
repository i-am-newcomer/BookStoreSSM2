package com.zy.dao;

import java.util.List;

import com.zy.entity.User;

public interface UserDao {
	
	public List<User> selectUserList();
	
	public User selectUserById(int id);
	
	public User selectUserByName(String name);
	
	public User selectUserByEmail(String email);
	
	public User selectUserByPhone(String phone);
	
	public int insertUser(User user);
	
	public int deleteUser(int id);
	
	public int updateUser(User user);
 
}
