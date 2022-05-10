package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IDeliveryDAO;
import com.solvd.OnlineShopping.model.Address;

import com.solvd.OnlineShopping.model.Delivery;

import com.solvd.OnlineShopping.model.User;
import com.solvd.OnlineShopping.model.enums.DeliveryStatus;

public class DeliveryDAO implements IDeliveryDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
	@Override
	public Delivery getEntityById(int id) {
		Delivery deliver = new Delivery();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Deliveries where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
		if(rs.next()) {
			deliver.setId(rs.getInt(1));
			deliver.setDeliveryStatus((DeliveryStatus)rs.getObject(2));
			deliver.setUser((User)rs.getObject(3));
			deliver.setAddress((Address) rs.getObject(4));
			
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve delivery");
		}
		return deliver;
	}

	@Override
	public void saveEntity(Delivery entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Deliveries values (?,?,?,?)");
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getDeliveryStatus());
			pr.setObject(3, entity.getUser());
			pr.setObject(4, entity.getAddress());
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save User");
		}
	}

	@Override
	public void updateEntity(Delivery entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Deliveries set 'delivery_status'=?, 'user_id'=?, 'address_id'=? Where ('id' = ?)");

			
			pr.setObject(2, entity.getDeliveryStatus());
			pr.setObject(3, entity.getUser());
			pr.setObject(4, entity.getAddress());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update Delivery");
		}
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Deliveries Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the delivery");
		}
	}

	@Override
	public List<Delivery> getDeliveriesByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
