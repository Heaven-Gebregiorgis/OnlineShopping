package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.IMessageDAO;

import com.solvd.OnlineShopping.model.Message;
import com.solvd.OnlineShopping.model.User;

public class MessageDAO implements IMessageDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(MessageDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Messages WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Messages values (?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Messages set 'email'=?, 'subject'=?, 'detail'=?, 'message_date'=?,'user_id'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Messages Where id = ?";

	@Override
	public Message getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Message message = new Message();
		
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		
			pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			message.setId(rs.getInt(1));
			message.setEmail(rs.getString(2));
			message.setSubject(rs.getString(3));
			message.setBodyDetail(rs.getString(4));
			message.setMessageDate(rs.getDate(5));
			message.setUser((User) rs.getObject(6));
		
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
		return message;
	}

	@Override
	public void saveEntity(Message entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getEmail());
			pr.setString(3, entity.getSubject());
			pr.setString(4, entity.getBodyDetail());
			pr.setDate(5, entity.getMessageDate());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save Message");
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
	public void updateEntity(Message entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			pr.setString(2, entity.getEmail());
			pr.setString(3, entity.getSubject());
			pr.setString(4, entity.getBodyDetail());
			pr.setDate(5, entity.getMessageDate());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("Failed to update message");
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
			LOGGER.error("Failed to delete the message");
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
	public List<Message> getMessagesByUser(User u) {
		List<Message> list= new ArrayList<Message>();
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
	
		ResultSet rs = null;
		
		try(PreparedStatement pr = con.prepareStatement("Select * from Heaven_Gebregiorgis.Messages where user_id = ?")) {
		rs = pr.executeQuery();
		
		Message message = new Message();
		while(rs.next()) {
			
			message.setId(rs.getInt(1));
			message.setEmail(rs.getString(2));
			message.setSubject(rs.getString(3));
			message.setBodyDetail(rs.getString(4));
			message.setMessageDate(rs.getDate(5));
			message.setUser((User) rs.getObject(6));
			list.add(message);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve message");
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
