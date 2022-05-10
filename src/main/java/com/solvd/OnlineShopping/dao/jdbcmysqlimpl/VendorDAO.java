package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IVendorDAO;
import com.solvd.OnlineShopping.model.Address;
import com.solvd.OnlineShopping.model.Vendor;

public class VendorDAO implements IVendorDAO  {
	
	private static final Logger LOGGER = LogManager.getLogger(VendorDAO.class);

	@Override
	public Vendor getEntityById(int id) {
		Vendor vendor = new Vendor();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Vendors where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
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
		return vendor;
	}

	@Override
	public void saveEntity(Vendor entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Users values (?,?,?,?,?,?)");
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
	}

	@Override
	public void updateEntity(Vendor entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Vendors set 'vendor_name'=?, 'contact_name'=?, 'email'=?, 'phone'=?,'address_id'=?, Where ('id' = ?)");

			
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
		
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Vendors Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the vendor");
		}
		
	}

}
