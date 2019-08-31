package com.example.demo.mybatis.dao.second;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class SecondApiDao {
	protected static final String NAMESPACE = "second.";

	 @Autowired
	@Qualifier("secondarySqlSession")
	private SqlSession sqlSession;

	public String selectName(){
		return sqlSession.selectOne(NAMESPACE + "selectName");
	}
}