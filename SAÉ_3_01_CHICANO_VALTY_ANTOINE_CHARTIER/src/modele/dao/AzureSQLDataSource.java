package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AzureSQLDataSource {
	
	private static final String USER = "parking-user@parkings";
	private static final String PASSWORD = "v%f1R(zrC.tK%hej";
	private static final String URL = "jdbc:sqlserver://parkings.database.windows.net:1433;database=parking;user="+USER+";password={"+PASSWORD+"};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	private static Connection conn;
	
	public static Connection getConnexion() throws SQLException
    {
        if (AzureSQLDataSource.conn == null)
        {
        	AzureSQLDataSource.conn = DriverManager.getConnection(
        			URL,
        			USER,
        			PASSWORD);
        }
        return AzureSQLDataSource.conn;
    }
	
	public static void commit() throws SQLException
    {
		AzureSQLDataSource.conn.commit();
    }
    
    public static void rollback() throws SQLException
    {
    	AzureSQLDataSource.conn.rollback();
    }

    public static void deconnecter() throws SQLException
    {
    	AzureSQLDataSource.conn.close();
    	AzureSQLDataSource.conn = null;
    }
}
