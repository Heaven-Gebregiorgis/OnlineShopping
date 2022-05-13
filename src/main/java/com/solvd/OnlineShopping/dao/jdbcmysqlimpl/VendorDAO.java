package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.IVendorDAO;
import com.solvd.OnlineShopping.model.Address;
import com.solvd.OnlineShopping.model.Vendor;

public class VendorDAO implements IVendorDAO  {
	
	private static final Logger LOGGER = LogManager.getLogger(VendorDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Addresses WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Vendors values (?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "\"Update Heaven_Gebregiorgis.Vendors set 'vendor_name'=?, 'contact_name'=?, 'email'=?, 'phone'=?,'address_id'=?, Where ('id' = ?)\"";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Vendors Where id = ?";

	@Override
	public Vendor getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Vendor vendor = new Vendor();
		
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			vendor.setId(rs.getInt(1));
			vendor.setVendorName(rs.getString(2));
			vendor.setContactName(rs.getString(3));
			vendor.setEmail(rs.getString(4));
			vendor.setPhone(rs.getString(5));
			vendor.setAddress((Address)rs.getObject(6));
		
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve address");
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
		return vendor;
	}

	@Override
	public void saveEntity(Vendor entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getVendorName());
			pr.setString(3, entity.getContactName());
			pr.setString(4, entity.getEmail());
			pr.setString(5, entity.getPhone());
			pr.setObject(6, entity.getAddress());
		
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save vendor");
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
	public void updateEntity(Vendor entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getVendorName());
			pr.setString(3, entity.getContactName());
			pr.setString(4, entity.getEmail());
			pr.setString(5, entity.getPhone());
			pr.setObject(6, entity.getAddress());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update vendor");
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
			LOGGER.error("Failed to delete the vendor");
		}
		 finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
		  }
		
	}

}
