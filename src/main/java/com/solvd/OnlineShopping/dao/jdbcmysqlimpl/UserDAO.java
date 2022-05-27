package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.IUserDAO;
import com.solvd.OnlineShopping.model.CommunicationPreference;
import com.solvd.OnlineShopping.model.Setting;
import com.solvd.OnlineShopping.model.User;

public class UserDAO implements IUserDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Users WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Addresses Set id, last_name, first_name, birthday, email, registered_on, last-login, communication_preference_id, setting_id values (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Users set 'last_name'=?, 'first_name'=?, 'birthday'=?, 'email'=?,'communication_preference_id'=?, 'setting_id'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Users Where id = ?";	
	
	@Override
	public User getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		User user = new User();
		ResultSet rs = null;
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			user.setId(rs.getInt(1));
			user.setLastName(rs.getString(2));
			user.setFirstName(rs.getString(3));
			user.setBirthday(rs.getDate(4));
			user.setEmail(rs.getString(5));
			user.setRegisteredOn(rs.getDate(6));
			user.setLastLogin(rs.getDate(7));
			user.setCommunicationPreference((CommunicationPreference) rs.getObject(8));
			user.setSetting((Setting) rs.getObject(9));
		
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve user");
		}
	      finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	            }
	            catch (SQLException e) {
	                LOGGER.error("Failed to close ResultSet: " + e.getSQLState());
	            }
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        }
		return user;
		
	}

	@Override
	public void saveEntity(User entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getLastName());
			pr.setString(3, entity.getFirstName());
			pr.setDate(4, (java.sql.Date) entity.getBirthday());
			pr.setString(5, entity.getEmail());
			pr.setDate(6, (java.sql.Date) entity.getRegisteredOn());
			pr.setDate(7, (java.sql.Date) entity.getLastLogin());
			pr.setObject(8, entity.getCommunicationPreference());
			pr.setObject(9, entity.getSetting());
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save User");
		}
		  finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        }
	
		
	}

	@Override
	public void updateEntity(User entity) {
		
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			
			pr.setString(2, entity.getLastName());
			pr.setString(3, entity.getFirstName());
			pr.setDate(4, (java.sql.Date) entity.getBirthday());
			pr.setString(5, entity.getEmail());
			pr.setObject(8, entity.getCommunicationPreference());
			pr.setObject(9, entity.getSetting());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update User");
		}
		 finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
		  }
	}
	

	@Override
	public void removeEntity(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(REMOVEENTITYSQL)) {
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the user");
		}
		  finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
		  }
		
	}

	@Override
	public List<User> getUsersByDate(Date d) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		List<User> list= new ArrayList<User>();
		ResultSet rs = null;
		try(PreparedStatement pr = con.prepareStatement("Select * from Heaven_Gebregiorgis.Addresses where registered_on = ?")) {
		rs = pr.executeQuery();
		
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setLastName(rs.getString(2));
			user.setFirstName(rs.getString(3));
			user.setBirthday(rs.getDate(4));
			user.setEmail(rs.getString(5));
			user.setRegisteredOn(rs.getDate(6));
			user.setLastLogin(rs.getDate(7));
			user.setCommunicationPreference((CommunicationPreference) rs.getObject(8));
			user.setSetting((Setting) rs.getObject(9));
			list.add(user);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve user");
		}
		finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("Failed to close ResultSet: " + e.getSQLState());
            }
            try {
				pool.returnConnection(con);
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
        }
		return list;
	
	}

}
