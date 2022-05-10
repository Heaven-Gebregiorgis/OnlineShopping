package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IAddressDAO;
import com.solvd.OnlineShopping.model.Address;


public class AddressDAO implements IAddressDAO {

	private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
	
	@Override
	public Address getEntityById(int id) {
		
		Address address = new Address();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Addresses where id=?");
		pr.setInt(1, id);
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
		ResultSet rs = pr.executeQuery();
		if(rs.next()) {
			address.setId(rs.getInt(1));
			address.setHouseNumber(rs.getInt(2));
			address.setStreet(rs.getString(3));
			address.setApartmentNumber(rs.getString(4));
			address.setCity(rs.getString(5));
			address.setState(rs.getString(6));
			address.setPostalCode(rs.getString(7));
			address.setCountry(rs.getString(8));
			
			
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve address");
		}
		return address;
	}

	@Override
	public void saveEntity(Address entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Addresses values (?,?,?,?,?,?,?,?)");
			
			pr.setInt(1, entity.getId());
			pr.setInt(2, entity.getHouseNumber());
			pr.setString(3, entity.getStreet());
			pr.setString(4, entity.getApartmentNumber());
			pr.setString(5, entity.getCity());
			pr.setString(6, entity.getState());
			pr.setString(7, entity.getPostalCode());
			pr.setObject(8, entity.getCountry());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save address");
		}
	}

	@Override
	public void updateEntity(Address entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Addresses set 'house_number'=?, 'street'=?, 'apartment_number'=?, 'city'=?,'state'=?, 'postal_code'=?, 'country'=? Where ('id' = ?)");

			
			pr.setInt(2, entity.getHouseNumber());
			pr.setString(3, entity.getStreet());
			pr.setString(4, entity.getApartmentNumber());
			pr.setString(5, entity.getCity());
			pr.setString(6, entity.getState());
			pr.setString(7, entity.getPostalCode());
			pr.setString(8, entity.getCountry());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update address");
		}
	}

	@Override
	public void removeEntity(int id) {
		try {
			Connection con = ConnectionPool.getDataSource().getConnection();
			String sql = "Delete from Addresses Where id = ?";
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.executeUpdate("Use Heaven_Gebregiorgis;");
			pr.executeUpdate();
			
		} catch (SQLException e) {
			
			LOGGER.error("Failed to delete the address");
		}
	
	}

	@Override
	public List<Address> getAddressesByCity(String city) {
		
		List<Address> list= new ArrayList<Address>();
		try {
		
			Connection con = ConnectionPool.getDataSource().getConnection();
			PreparedStatement pr = con.prepareStatement("Select * from Addresses where city = ?");
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
			ResultSet rs = pr.executeQuery();
		
		Address address = new Address();
		while(rs.next()) {
			
			address.setId(rs.getInt(1));
			address.setHouseNumber(rs.getInt(2));
			address.setStreet(rs.getString(3));
			address.setApartmentNumber(rs.getString(4));
			address.setCity(rs.getString(5));
			address.setState(rs.getString(6));
			address.setPostalCode(rs.getString(7));
			address.setCountry(rs.getString(8));
			
			list.add(address);
			System.out.println(list);
		}
		} catch (SQLException e) {
			
			LOGGER.error("Failed to retrieve address");
		}
		return list;
	}
	

}
