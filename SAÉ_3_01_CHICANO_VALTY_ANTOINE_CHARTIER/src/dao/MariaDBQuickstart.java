package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBQuickstart {

    // Database connection parameters
    static final String DB_URL = "jdbc:mariadb://[2a01:e0a:eab:bbd0:8989:3082:9757:3fb]:3306/parking_database";
    static final String USER = "liam";
    static final String PASS = "VLL5292A";

    public static void main(String[] args) {
        Connection conn = null;

        try {
            System.out.println("Connection à la base de donnée ...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection réussie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}