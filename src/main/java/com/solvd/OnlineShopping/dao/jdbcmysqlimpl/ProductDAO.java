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
import com.solvd.OnlineShopping.dao.IProductDAO;
import com.solvd.OnlineShopping.model.Category;
import com.solvd.OnlineShopping.model.Product;

import com.solvd.OnlineShopping.model.Vendor;
import com.solvd.OnlineShopping.model.enums.ProductStatus;

public class ProductDAO implements IProductDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Products WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Products values (?,?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Products set 'product_name'=?, 'brand'=?, 'size'=?, 'status'=?,'category'=?, 'vendor'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Products Where id = ?";
	private static final String GETPRODUCTSBYBRANDSQL= "SELECT * FROM Heaven_Gebregiorgis.Products WHERE brand = ?";

	@Override
	public Product getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Product product = new Product();
	
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			product.setId(rs.getInt(1));
			product.setProductName(rs.getString(2));
			product.setBrand(rs.getString(3));
			product.setSize(rs.getString(4));
			product.setStatus((ProductStatus)rs.getObject(5));
			product.setCategory((Category)rs.getObject(6));
			product.setVendor((Vendor)rs.getObject(7));
			
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve vendor");
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
		return product;
	}

	@Override
	public void saveEntity(Product entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getProductName());
			pr.setString(3, entity.getBrand());
			pr.setString(4, entity.getSize());
			pr.setObject(5, entity.getStatus());
			pr.setObject(6, entity.getCategory());
			pr.setObject(7, entity.getVendor());
		
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save product");
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
	public void updateEntity(Product entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			
			pr.setString(2, entity.getProductName());
			pr.setString(3, entity.getBrand());
			pr.setString(4, entity.getSize());
			pr.setObject(5, entity.getStatus());
			pr.setObject(6, entity.getCategory());
			pr.setObject(7, entity.getVendor());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update product");
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
			LOGGER.error("Failed to delete the product");
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
	public List<Product> getProductsByBrand(String brand) {
		List<Product> list= new ArrayList<Product>();
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		try(PreparedStatement pr = con.prepareStatement(GETPRODUCTSBYBRANDSQL)) {
		
		rs = pr.executeQuery();
		
		while(rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt(1));
			product.setProductName(rs.getString(2));
			product.setBrand(rs.getString(3));
			product.setSize(rs.getString(4));
			product.setStatus((ProductStatus)rs.getObject(5));
			product.setCategory((Category)rs.getObject(6));
			product.setVendor((Vendor)rs.getObject(7));
			list.add(product);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve product");
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

	@Override
	public List<Product> getItemsInCartByCartId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
