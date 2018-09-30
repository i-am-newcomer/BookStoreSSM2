package com.zy.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zy.dao.UserDao;
import com.zy.entity.User;

@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public List<User> selectUserList() {
		return getSqlSession().selectList("com.zy.entity.UserMapper.selectAllUser");
	}

	@Override
	public User selectUserById(int id) {
		return getSqlSession().selectOne("com.zy.entity.UserMapper.selectUserById", id);
	}
	
	@Override
	public User selectUserByName(String name) {
		return getSqlSession().selectOne("com.zy.entity.UserMapper.selectUserByName", name);
	}
	
	@Override
	public User selectUserByEmail(String email) {
		return getSqlSession().selectOne("com.zy.entity.UserMapper.selectUserByEmail", email);
	}
	
	@Override
	public User selectUserByPhone(String phone) {
		return getSqlSession().selectOne("com.zy.entity.UserMapper.selectUserByPhone", phone);
	}

	@Override
	public int insertUser(User user) {
		return getSqlSession().insert("com.zy.entity.UserMapper.insertUser", user);
	}

	@Override
	public int deleteUser(int id) {
		return getSqlSession().delete("com.zy.entity.UserMapper.deleteUser");
	}

	@Override
	public int updateUser(User user) {
		return getSqlSession().update("com.zy.entity.UserMapper.updateUser", user);
	}
	

}
