package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBDataSource {

	private static final String USER = "liam";
	private static final String PASSWORD = "VLL5292A";
	private static final String DATABASE = "/parking_database";
	private static final String IP = "[2a01:e0a:eab:bbd0:8989:3082:9757:3fb]";
	private static final String PORT = ":3306";
	private static final String URL = "jdbc:mariadb://" + IP + PORT + DATABASE;

	private static Connection conn;
	
	public static Connection getConnexion() throws SQLException
    {
        if (MariaDBDataSource.conn == null)
        {
        	MariaDBDataSource.conn = DriverManager.getConnection(
        			URL,
        			USER,
        			PASSWORD);
        }
        return MariaDBDataSource.conn;
    }
	
	public static void commit() throws SQLException
    {
		MariaDBDataSource.conn.commit();
    }
    
    public static void rollback() throws SQLException
    {
    	MariaDBDataSource.conn.rollback();
    }


    public static void deconnecter() throws SQLException
    {
    	MariaDBDataSource.conn.close();
    	MariaDBDataSource.conn = null;
    }
	
}
