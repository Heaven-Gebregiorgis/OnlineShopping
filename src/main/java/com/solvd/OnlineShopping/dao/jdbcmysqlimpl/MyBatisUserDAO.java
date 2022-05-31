package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.util.Date;
import java.util.List;
import static com.solvd.OnlineShopping.connection.MyBatisUtil.getSqlSession;
import com.solvd.OnlineShopping.dao.IUserDAO;
import com.solvd.OnlineShopping.model.User;

public class MyBatisUserDAO {
	
	
	public User getUsersById(int userId){
	
			IUserDAO userMapper = getSqlSession().getMapper(IUserDAO.class);	
			return userMapper.getEntityById(userId);
}

	
	public void saveUser(User user){
			
					IUserDAO userMapper = getSqlSession().getMapper(IUserDAO.class);
					userMapper.saveEntity(user);
		
	}
	
	
	public void updateUser(User user){
		IUserDAO userMapper = getSqlSession().getMapper(IUserDAO.class);
			userMapper.updateEntity(user);
		
	}
	
	
	public void deleteUser(int userId){
		IUserDAO userMapper = getSqlSession().getMapper(IUserDAO.class);
		userMapper.removeEntity(userId);
	
	}
	
	public List<User> getUsersByDate(Date d){
		IUserDAO userMapper = getSqlSession().getMapper(IUserDAO.class);
			return userMapper.getUsersByDate(d);
	}
}
