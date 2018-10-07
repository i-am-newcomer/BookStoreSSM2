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
	
	/*���ע����Ϣ*/
	public String register(User user, String confirm_pwd, String in_checkcode, String checkcode) {
		if(this.selectUserByEmail(user.getEmail())!=null) {
			return "�������ѱ�ע��";
		}
		else if(!user.getEmail().matches(".*@.*\\.com$")) {
			return "�����ʽ����ȷ";
		}
		else if(this.selectUserByPhone(user.getPhone())!=null) {
			return "���ֻ����ѱ�ע��";
		}
		else if(!user.getPhone().matches("^[0-9]{11}$")) {
			return "�ֻ��Ÿ�ʽ����ȷ";
		}
		else if(this.selectUserByName(user.getName())!=null) {
			return "���û����ѱ�ע��";
		}
		else if(user.getName().length()<4) {
			return "�û����������ٲ�С��4";
		}
		else if(user.getPwd().length()<6) {
			return "���볤�����ٲ�С��6";
		}
		else if(!user.getPwd().equals(confirm_pwd)) {
			return "�����ȷ�����벻һ��";
		}
		else if(!in_checkcode.equals(checkcode)) {
			return "��֤���������";
		}
		else if(this.insertUser(user)<0) {
			return "ע��ʧ��";
		}
		return "success";	
	}

	/*����¼��Ϣ*/
	@Override
	public Object login(String name, String pwd, String in_checkcode, String checkcode) {
		User user = null;
		if((this.selectUserByEmail(name)==null) && (this.selectUserByPhone(name)==null) && (this.selectUserByName(name)==null)) {
			return "�û���������";
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
			return "�������";
		}
		else if(!in_checkcode.equals(checkcode)) {
			return "��֤���������";
		}
		return user;
	}

}
