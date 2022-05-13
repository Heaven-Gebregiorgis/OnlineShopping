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
import com.solvd.OnlineShopping.dao.IAddressDAO;
import com.solvd.OnlineShopping.model.Address;


public class AddressDAO implements IAddressDAO {

	private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Addresses WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Addresses values (?,?,?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Addresses set 'house_number'=?, 'street'=?, 'apartment_number'=?, 'city'=?,'state'=?, 'postal_code'=?, 'country'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Addresses Where id = ?";		
	
	@Override
	public Address getEntityById(int id) {
		
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Address address = new Address();
		
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
			
		pr.setInt(1, id);
		rs = pr.executeQuery();
		while(rs.next()) {
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
            LOGGER.error("Failed to establish connection to Comments.");
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
		return address;
	}

	
	@Override
	public void saveEntity(Address entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			
			
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
		  finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        }
	}

	@Override
	public void updateEntity(Address entity) {
		
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {
			
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
			pr.executeUpdate("Use Heaven_Gebregiorgis;");
			pr.executeUpdate();
			
		} catch (SQLException e) {
			
			LOGGER.error("Failed to delete the address" + e.getMessage());
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
	public List<Address> getAddressesByCity(String city) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		Address address = new Address();
		ResultSet rs = null;
		List<Address> list= new ArrayList<Address>();
		
		try(PreparedStatement pr = con.prepareStatement("Select * from Heaven_Gebregiorgis.Addresses where city = ?")) {
		
			rs = pr.executeQuery();
		
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
		
		}
		} catch (SQLException e) {
			
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
		return list;
	}
	

}
