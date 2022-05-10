package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.ITransactionDAO;
import com.solvd.OnlineShopping.model.Order;
import com.solvd.OnlineShopping.model.Transaction;
import com.solvd.OnlineShopping.model.Wallet;
import com.solvd.OnlineShopping.model.enums.TransactionStatus;

public class TransactionDAO implements ITransactionDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

	@Override
	public Transaction getEntityById(int id) {
		
		Transaction transaction = new Transaction();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Transactions where id = ?");
		pr.setInt(1, id);
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
		ResultSet rs = pr.executeQuery();
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
		return transaction;
	}

	@Override
	public void saveEntity(Transaction entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Transactions values (?,?,?,?");
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getOrder());
			pr.setObject(3, entity.getWallet());
			pr.setObject(4, entity.getStatus());

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save transaction");
		}
		
	}

	@Override
	public void updateEntity(Transaction entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Transactions set 'order_id'=?, 'wallet_id'=?, 'transactionStatus'=? Where ('id' = ?)");

			
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getOrder());
			pr.setObject(3, entity.getWallet());
			pr.setObject(4, entity.getStatus());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update transaction");
		}
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Transactions Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate("Use Heaven_Gebregiorgis;");
			pr.executeUpdate();
		} catch (SQLException e) {
		
			LOGGER.error("Failed to delete the transaction");
		}
	}

	@Override
	public List<Transaction> getTranactionsByStatus(TransactionStatus s) {
		// TODO Auto-generated method stub
		return null;
	}

}
