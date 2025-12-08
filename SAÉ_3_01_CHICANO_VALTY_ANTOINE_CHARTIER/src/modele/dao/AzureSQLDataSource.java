package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modele.Utilisateur;

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
	    
    public static void main(String[] args) throws SQLException {
		addUtilisateur();
    }

	private static void printEveryUtilisateur() throws SQLException {
		ArrayList<Utilisateur> resList = (ArrayList<Utilisateur>) new DaoUtilisateur().findAll();
		if (resList.isEmpty()) {
			System.out.println("List is empty");
		} else {
			for (Utilisateur utilisateur : resList) {
				System.out.println(utilisateur.toString());
			}
		}
	}

	private static void addUtilisateur() throws SQLException {
		Utilisateur newUser = new Utilisateur(10, "Seong", "Gi-Hun", "07", LocalDate.of(2001, 11, 9), "mail.mail.com", "password");
		new DaoUtilisateur().create(newUser);
	}
}
