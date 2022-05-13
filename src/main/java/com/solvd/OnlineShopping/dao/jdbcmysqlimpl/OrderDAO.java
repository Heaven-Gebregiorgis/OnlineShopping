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
import com.solvd.OnlineShopping.dao.IOrderDAO;
import com.solvd.OnlineShopping.model.Order;
import com.solvd.OnlineShopping.model.User;


public class OrderDAO implements IOrderDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Orders WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Orders values (?,?,?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Orders set 'order_detail'=?, 'total_price'=?, 'placed_at'=?, 'user_id'=?, Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Orders Where id = ?";
	private static final String GETUSERSQL= "SELECT * FROM Heaven_Gebregiorgis.Orders WHERE user_id=?";

	@Override
	public Order getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Order order = new Order();
		
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			order.setId(rs.getInt(1));
			order.setOrderDetail(rs.getString(2));
			order.setTotalPrice(rs.getBigDecimal(3));
			order.setPlacedAt(rs.getDate(4));
			order.setConfirmationNumber(rs.getString(5));
			order.setUser((User)rs.getObject(6));
		
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve order");
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
		return order;
			
	}

	@Override
	public void saveEntity(Order entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getOrderDetail());
			pr.setBigDecimal(3, entity.getTotalPrice());
			pr.setDate(4, entity.getPlacedAt());
			pr.setString(5, entity.getConfirmationNumber());
			pr.setObject(6, entity.getUser());
		
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save order");
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
	public void updateEntity(Order entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getOrderDetail());
			pr.setBigDecimal(3, entity.getTotalPrice());
			pr.setDate(4, entity.getPlacedAt());
			pr.setString(5, entity.getConfirmationNumber());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update order");
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
			LOGGER.error("Failed to delete the order");
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
	public List<Order> getOrdersByConfirmationNumber(String cn) {
	//to be implemented
		return null;
	}

	public List<Order> getOrdersByUserId(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Order order = new Order();
		List<Order> list= new ArrayList<Order>();
		
		try (PreparedStatement pr = con.prepareStatement(GETUSERSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
		if(rs.next()) {
			order.setId(rs.getInt(1));
			order.setOrderDetail(rs.getString(2));
			order.setTotalPrice(rs.getBigDecimal(3));
			order.setPlacedAt(rs.getDate(4));
			order.setConfirmationNumber(rs.getString(5));
			order.setUser((User)rs.getObject(6));
		
			list.add(order);
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve order");
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
