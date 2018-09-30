package com.zy.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zy.dao.BookDao;
import com.zy.entity.Book;

@Repository("bookDao")
public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao {
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Book selectBookById(int id) {
		return getSqlSession().selectOne("com.zy.entity.BookMapper.selectBookById", id);
	}

	@Override
	public int updateBookAmount(Book book) {
		return getSqlSession().update("com.zy.entity.BookMapper.updateBookAmount", book);
	}

	@Override
	public int updateBookPrice(Book book) {
		return getSqlSession().update("com.zy.entity.BookMapper.updateBookPrice", book);
	}

	@Override
	public int updateBookSalesVolume(Book book) {
		return getSqlSession().update("com.zy.entity.BookMapper.updateBookSalesVolume", book);
	}

}
