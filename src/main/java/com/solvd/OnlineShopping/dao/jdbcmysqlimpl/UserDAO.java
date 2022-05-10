package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IUserDAO;
import com.solvd.OnlineShopping.model.CommunicationPreference;
import com.solvd.OnlineShopping.model.Setting;
import com.solvd.OnlineShopping.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO implements IUserDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
	
	@Override
	public User getEntityById(int id) {
		
		User user = new User();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Users where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
		if(rs.next()) {
			user.setId(rs.getInt(1));
			user.setLastName(rs.getString(2));
			user.setFirstName(rs.getString(3));
			user.setBirthday(rs.getDate(4));
			user.setEmail(rs.getString(5));
			user.setRegisteredOn(rs.getDate(6));
			user.setLastLogin(rs.getDate(7));
			user.setCommunicationPreferenceId((CommunicationPreference) rs.getObject(8));
			user.setSettingId((Setting) rs.getObject(9));
		
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve user");
		}
		return user;
		
	}

	@Override
	public void saveEntity(User entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Users values (?,?,?,?,?,?,?,?,?)");
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getLastName());
			pr.setString(3, entity.getFirstName());
			pr.setDate(4, entity.getBirthday());
			pr.setString(5, entity.getEmail());
			pr.setDate(6, entity.getRegisteredOn());
			pr.setDate(7, entity.getLastLogin());
			pr.setObject(8, entity.getCommunicationPreferenceId());
			pr.setObject(9, entity.getSettingId());
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save User");
		}
		
	}

	@Override
	public void updateEntity(User entity) {
		
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Users set 'last_name'=?, 'first_name'=?, 'birthday'=?, 'email'=?,'communication_preference_id'=?, 'setting_id'=? Where ('id' = ?)");

			
			pr.setString(2, entity.getLastName());
			pr.setString(3, entity.getFirstName());
			pr.setDate(4, entity.getBirthday());
			pr.setString(5, entity.getEmail());
			pr.setObject(8, entity.getCommunicationPreferenceId());
			pr.setObject(9, entity.getSettingId());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update User");
		}
	}
	

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Users Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the user");
		}
		
	}

	@Override
	public List<User> getUsersByDate(Date d) {
		List<User> list= new ArrayList<User>();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Users where registered_on = ?");
		ResultSet rs = pr.executeQuery();
		
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setLastName(rs.getString(2));
			user.setFirstName(rs.getString(3));
			user.setBirthday(rs.getDate(4));
			user.setEmail(rs.getString(5));
			user.setRegisteredOn(rs.getDate(6));
			user.setLastLogin(rs.getDate(7));
			user.setCommunicationPreferenceId((CommunicationPreference) rs.getObject(8));
			user.setSettingId((Setting) rs.getObject(9));
			list.add(user);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve user");
		}
		return list;
	}

}
