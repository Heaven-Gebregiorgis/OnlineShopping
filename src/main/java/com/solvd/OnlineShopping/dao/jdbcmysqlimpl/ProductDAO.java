package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IProductDAO;
import com.solvd.OnlineShopping.model.Category;
import com.solvd.OnlineShopping.model.Product;

import com.solvd.OnlineShopping.model.Vendor;
import com.solvd.OnlineShopping.model.enums.ProductStatus;

public class ProductDAO implements IProductDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

	@Override
	public Product getEntityById(int id) {
		Product product = new Product();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Products where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
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
		return product;
	}

	@Override
	public void saveEntity(Product entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Vendors values (?,?,?,?,?,?,?)");
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
		
	}

	@Override
	public void updateEntity(Product entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Products set 'product_name'=?, 'brand'=?, 'size'=?, 'status'=?,'category'=?, 'vendor'=? Where ('id' = ?)");

			
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
		
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Products Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the product");
		}
		
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		List<Product> list= new ArrayList<Product>();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Products where brand = ?");
		ResultSet rs = pr.executeQuery();
		
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
		return list;
	}

}
