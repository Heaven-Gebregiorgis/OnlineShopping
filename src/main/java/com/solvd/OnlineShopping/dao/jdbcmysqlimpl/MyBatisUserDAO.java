package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MyBatisUtil;
import com.solvd.OnlineShopping.dao.IUserDAO;
import com.solvd.OnlineShopping.model.User;

public class MyBatisUserDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(MyBatisUserDAO.class);
	public User getUsersById(int userId){
	
		SqlSession session = null;
		User user = null;
		try{
			session = MyBatisUtil.getSqlSessionFactory().openSession();
			IUserDAO mapper = session.getMapper(IUserDAO.class);
			user = mapper.getEntityById(userId);
			session.commit();
			
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}

		finally {
			session.close();
		}
		return user;

	}

	
	public void saveUser(User user){
		SqlSession session = null;
				try{
					session = MyBatisUtil.getSqlSessionFactory().openSession();
					IUserDAO mapper = session.getMapper(IUserDAO.class);
					mapper.saveEntity(user);
					//session.insert("saveEntity", user);
					session.commit();
				}catch(Exception e) {
					LOGGER.error(e.getMessage());
				}
		finally {
			session.close();
		}
		
	}
	
	public void updateUser(User user){
		SqlSession session = null;
		try{
			session = MyBatisUtil.getSqlSessionFactory().openSession();
			IUserDAO mapper = session.getMapper(IUserDAO.class);
			mapper.updateEntity(user);
			session.commit();
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void deleteUser(int userId){
		SqlSession session = null;
		try{
			session = MyBatisUtil.getSqlSessionFactory().openSession();
			IUserDAO mapper = session.getMapper(IUserDAO.class);
			mapper.removeEntity(userId);
			session.commit();
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public List<User> getUsersByDate(Date d){

		SqlSession session = null;
		List<User> userList = null;
		try{
			session = MyBatisUtil.getSqlSessionFactory().openSession();
			IUserDAO mapper = session.getMapper(IUserDAO.class);
			userList = mapper.getUsersByDate(d);
			session.commit();
			
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}

		finally {
			session.close();
		}
		return userList;

	}
}
