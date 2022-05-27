package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.IDeliveryDAO;
import com.solvd.OnlineShopping.model.Address;
import com.solvd.OnlineShopping.model.Delivery;
import com.solvd.OnlineShopping.model.User;
import com.solvd.OnlineShopping.model.enums.DeliveryStatus;

public class DeliveryDAO implements IDeliveryDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Deliveries WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Deliveries values (?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Deliveries set 'delivery_status'=?, 'user_id'=?, 'address_id'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Addresses.Deliveries Where id = ?";
	
	@Override
	public Delivery getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Delivery deliver = new Delivery();
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
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
		return deliver;
	}

	@Override
	public void saveEntity(Delivery entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getDeliveryStatus());
			pr.setObject(3, entity.getUser());
			pr.setObject(4, entity.getAddress());
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
	public void updateEntity(Delivery entity) {

		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			pr.setObject(2, entity.getDeliveryStatus());
			pr.setObject(3, entity.getUser());
			pr.setObject(4, entity.getAddress());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update Delivery");
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
			LOGGER.error("Failed to delete the delivery");
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
	public List<Delivery> getDeliveriesByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
