package com.zy.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zy.dao.OrderDao;
import com.zy.entity.Order;

@Repository("orderDao")
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*根据用户id查询order*/
	@Override
	public Order selectOrderByCId(int cid) {
		return getSqlSession().selectOne("com.zy.entity.OrderMapper.selectOrderByCId", cid);
	}

	/*根据order的id查询order*/
	@Override
	public Order selectOrderById(int id) {
		return getSqlSession().selectOne("com.zy.entity.OrderMapper.selectOrderById", id);
	}

	@Override
	public int insertOrder(Order order) {
		return getSqlSession().insert("com.zy.entity.OrderMapper.insertOrder", order);
	}

	@Override
	public int updateOrder(Order order) {
		return getSqlSession().update("com.zy.entity.OrderMapper.updateOrder", order);
	}

}
