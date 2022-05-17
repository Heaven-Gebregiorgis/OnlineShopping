package com.solvd.OnlineShopping.connection;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MySQLConnectionPool {
	
	private static final Logger LOGGER = LogManager.getLogger(MySQLConnectionPool.class);
	
    private static final MySQLConnectionPool INSTANCE = new MySQLConnectionPool();

    static {
      
        INSTANCE.setDatabaseProperties("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/db.properties");
    }
	
	private String userName;
	private String password;
	private String url;
	private int maxPoolSize = 10;
	private int connNum = 0;
	Stack<Connection> freePool = new Stack<>();
	Set<Connection> occupiedPool = new HashSet<>();
	
	private MySQLConnectionPool() {}

	  public static MySQLConnectionPool getInstance() {
	        return INSTANCE;
	    }
    // Initialize Connection Credentials
    private void setDatabaseProperties(String filePath) {
        Properties p = new Properties();
        try (Reader fileReader = new FileReader(filePath)) {
            p.load(fileReader);
        }
        catch (IOException e) {
            LOGGER.error("Failed to set Database Properties. FilePath: " + filePath);
            return;
        }
	            url = String.valueOf(p.get("db.url"));
		        userName = String.valueOf(p.get("db.username"));
		        password = String.valueOf(p.get("db.password"));
		       @SuppressWarnings("unused")
			String driverclass = String.valueOf(p.get("db.driver"));
			

    }

	// Get an available connection from pool
	
	public synchronized Connection getAvailableConnection() {
		Connection conn = null;

		if (((freePool.size() == 0) && (connNum >= maxPoolSize))) {
			//throw new SQLException("The connection pool is full.");
			LOGGER.error("The connection pool is full.");
		}

		if (freePool.size() > 0) {
			conn = freePool.pop();
			occupiedPool.add(conn);
		}
	// If there is no free connection, create a new one.
		if (conn == null) {
			try {
				conn = createNewConnection();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return conn;
	}

	
	public synchronized void returnConnection(Connection conn)
			throws SQLException {
		if (conn == null) {
			 LOGGER.error("Attempting to return a closed or null Connection");
		}
		if (!occupiedPool.remove(conn)) {
			LOGGER.error(
					"The connection is returned already or it isn't for this pool");
		}
		freePool.push(conn);
	}


	private Connection createNewConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
		}
		  catch (SQLException e) {
	            LOGGER.warn("Failure to set up connection");
	        }
		 if (conn != null) {
		connNum++;
		occupiedPool.add(conn);
		 }
		return conn;
	
	}



}
	