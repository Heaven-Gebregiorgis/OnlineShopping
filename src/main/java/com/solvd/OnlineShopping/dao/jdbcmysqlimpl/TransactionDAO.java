package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.ITransactionDAO;
import com.solvd.OnlineShopping.model.Order;
import com.solvd.OnlineShopping.model.Transaction;
import com.solvd.OnlineShopping.model.Wallet;
import com.solvd.OnlineShopping.model.enums.TransactionStatus;

public class TransactionDAO implements ITransactionDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Transactions WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Transactions values (?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Transactions set 'order_id'=?, 'wallet_id'=?, 'transactionStatus'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Transactions Where id = ?";

	@Override
	public Transaction getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Transaction transaction = new Transaction();
	
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
		rs = pr.executeQuery();
		if(rs.next()) {
			transaction.setId(rs.getInt(1));
			transaction.setOrder((Order)rs.getObject(2));
			transaction.setWallet((Wallet)rs.getObject(3));
			transaction.setStatus((TransactionStatus)rs.getObject(4));
			
		}
		}
		catch (SQLException e) {
		
			LOGGER.error("Failed to retrieve transaction");
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
	      	  finally {
		            try {
						pool.returnConnection(con);
					} catch (SQLException e) {
						LOGGER.error(e.getMessage());
					}
		        }
	        }
		return transaction;
	}

	@Override
	public void saveEntity(Transaction entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getOrder());
			pr.setObject(3, entity.getWallet());
			pr.setObject(4, entity.getStatus());

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save transaction");
		}
        try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        
	}

	@Override
	public void updateEntity(Transaction entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {
		
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getOrder());
			pr.setObject(3, entity.getWallet());
			pr.setObject(4, entity.getStatus());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update transaction");
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
		
			LOGGER.error("Failed to delete the transaction");
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
	public List<Transaction> getTranactionsByStatus(TransactionStatus s) {
		// TODO Auto-generated method stub
		return null;
	}

}
