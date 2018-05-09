package com.dbconnection;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBConnection {

	private static Context context = null;
	private static DBConnection dbConnection = null;
	private static DataSource dataSource = null;
	
	private DBConnection() throws Exception {
		getDataSource();
	}
	
	public static synchronized DBConnection getDBConnection() throws Exception {
		if(dbConnection==null)
			return new DBConnection();
		return dbConnection;
	}
	
	private static void getDataSource() throws Exception {
		context = new InitialContext();
		dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/MyLocalDB");
		
	}
	
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
}
