package com.zline.zlogistics.biz.dal.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;


public class BaseDao extends SqlSessionTemplate{

	public BaseDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

}
