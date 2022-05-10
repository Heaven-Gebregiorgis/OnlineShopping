package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;

import com.solvd.OnlineShopping.dao.interfaces.IMessageDAO;
import com.solvd.OnlineShopping.model.Message;
import com.solvd.OnlineShopping.model.User;

public class MessageDAO implements IMessageDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(MessageDAO.class);

	@Override
	public Message getEntityById(int id) {
		Message message = new Message();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Messages where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
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
		return message;
	}

	@Override
	public void saveEntity(Message entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Messages values (?,?,?,?,?,?)");
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
		
	}

	@Override
	public void updateEntity(Message entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Messages set 'email'=?, 'subject'=?, 'detail'=?, 'message_date'=?,'user_id'=? Where ('id' = ?)");

			pr.setString(2, entity.getEmail());
			pr.setString(3, entity.getSubject());
			pr.setString(4, entity.getBodyDetail());
			pr.setDate(5, entity.getMessageDate());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("Failed to update message");
		}
		
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Messages Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the message");
		}
		
	}

	@Override
	public List<Message> getMessagesByUser(User u) {
		List<Message> list= new ArrayList<Message>();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Messages where user_id = ?");
		ResultSet rs = pr.executeQuery();
		
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
		return list;
	}

}
