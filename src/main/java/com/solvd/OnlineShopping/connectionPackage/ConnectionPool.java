package com.solvd.OnlineShopping.connectionPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
	
	private static final String DB_USERNAME="db.username";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL ="db.url";
	private static final String DB_DRIVER_CLASS="db.driver";
	
	private static Properties properties = null;
	private static BasicDataSource dataSource;
	static{
		try {
			properties = new Properties();
			properties.load(new FileInputStream("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/db.properties"));
			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
			dataSource.setUrl(properties.getProperty(DB_URL));
			dataSource.setUsername(properties.getProperty(DB_USERNAME));
			dataSource.setPassword(properties.getProperty(DB_PASSWORD));
			
			dataSource.setMinIdle(10);
			dataSource.setMaxIdle(25);
			dataSource.setMaxTotal(100);
			
			LOGGER.info("connection succesful!");
		} catch (IOException e) {
			LOGGER.error("File not found");
		}
		
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
}
