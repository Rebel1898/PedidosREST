package com.netmind.DAO;


import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DAO {
	
	protected Properties properties = null;
	
	protected static String url;
	protected static String user;
	protected static String password;
	protected static String dbdriver;
	protected static String poolresource;
	protected static DataSource dataSource;
	
	public DAO() {
		
		try {
			dataSource = this.getPoolResource ("jdbc/jwt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return this.properties;
	}

	public boolean loadProperties() {

		DAO.url 	 = properties.getProperty("url");
		DAO.user 	 = properties.getProperty("user");
		DAO.password = properties.getProperty("password");
		DAO.dbdriver = properties.getProperty("dbdriver");
		DAO.poolresource = properties.getProperty("pooldataSource");
		
		return true;
	}
	
	private DataSource getPoolResource(String poolresource) throws NamingException{
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource) envContext.lookup(poolresource);
		
		return dataSource;
	}

}