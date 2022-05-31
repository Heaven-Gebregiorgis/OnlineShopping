package com.solvd.OnlineShopping.connection;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisUtil {

	private MyBatisUtil(){}
	private static final Logger LOGGER = LogManager.getLogger(MyBatisUtil.class);
	private static SqlSessionFactory sessionFactory;
	
	static {
		
		try {
			Reader reader= Resources.getResourceAsReader("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/mybatis_config.xml");
			 sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			LOGGER.error(e);
		}
	}
	
	    public static SqlSession getSqlSession(){
	        SqlSession sqlSession = sessionFactory.openSession();
	        return sqlSession;
	    }
}
