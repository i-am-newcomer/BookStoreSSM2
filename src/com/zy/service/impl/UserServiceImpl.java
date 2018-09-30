package com.zy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.UserDao;
import com.zy.entity.User;
import com.zy.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> selectUserList() {
		return userDao.selectUserList();
	}

	@Override
	public User selectUserById(int id) {
		return userDao.selectUserById(id);
	}
	
	@Override
	public User selectUserByName(String name) {
		return userDao.selectUserByName(name);
	}
	
	@Override
	public User selectUserByEmail(String email) {
		return userDao.selectUserByEmail(email);
	}
	
	@Override
	public User selectUserByPhone(String phone) {
		return userDao.selectUserByPhone(phone);
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public int deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	/*检查注册信息*/
	public String register(User user, String confirm_pwd) {
		if(this.selectUserByEmail(user.getEmail())!=null) {
			return "该邮箱已被注册";
		}
		else if(!user.getEmail().matches(".*@.*\\.com$")) {
			return "邮箱格式不正确";
		}
		else if(this.selectUserByPhone(user.getPhone())!=null) {
			return "该手机号已被注册";
		}
		else if(!user.getPhone().matches("^[0-9]{11}$")) {
			return "手机号格式不正确";
		}
		else if(this.selectUserByName(user.getName())!=null) {
			return "该用户名已被注册";
		}
		else if(user.getName().length()<4) {
			return "用户名长度至少不小于4";
		}
		else if(user.getPwd().length()<6) {
			return "密码长度至少不小于6";
		}
		else if(!user.getPwd().equals(confirm_pwd)) {
			return "密码和确认密码不一致";
		}
		else if(this.insertUser(user)<0) {
			return "注册失败";
		}
		return "success";	
	}

	/*检查登录信息*/
	@Override
	public Object login(String name, String pwd) {
		User user = null;
		if((this.selectUserByEmail(name)==null) && (this.selectUserByPhone(name)==null) && (this.selectUserByName(name)==null)) {
			return "用户名不存在";
		}
		else if(this.selectUserByEmail(name)!=null) {
			user = this.selectUserByEmail(name);
		}
		else if(this.selectUserByPhone(name)!=null) {
			user = this.selectUserByPhone(name);
		}
		else if(this.selectUserByName(name)!=null) {
			user = this.selectUserByName(name);
		}
		if(!user.getPwd().equals(pwd)) {
			return "密码错误";
		}
		return user;
	}

}
