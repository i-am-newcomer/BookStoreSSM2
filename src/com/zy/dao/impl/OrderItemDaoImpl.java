package com.zy.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zy.dao.OrderItemDao;
import com.zy.entity.OrderItem;

@Repository("orderItemDao")
public class OrderItemDaoImpl extends SqlSessionDaoSupport implements OrderItemDao{
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*根据order的id查询订单详情列表*/
	@Override
	public List<OrderItem> selectOrderItemByOId(int oid) {
		return getSqlSession().selectList("com.zy.entity.OrderItemMapper.selectOrderItemByOId", oid);
	}
	
	/*根据order的id和book的id查询指定的orderitem*/
	@Override
	public OrderItem selectOrderItemById(Map<String, Integer> idmap) {
		return getSqlSession().selectOne("com.zy.entity.OrderItemMapper.selectOrderItemById", idmap);
	}
	
	@Override
	public int insertOrderItem(OrderItem orderitem) {
		return getSqlSession().insert("com.zy.entity.OrderItemMapper.insertOrderItem", orderitem);
	}

	@Override
	public int updateOrderItem(OrderItem orderitem) {
		return getSqlSession().update("com.zy.entity.OrderItemMapper.updateOrderItem", orderitem);
	}

}
