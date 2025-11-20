package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBQuickstart {

    public static void main(String[] args) {

        try {
            System.out.println("Connection à la base de donnée ...");
            Connection conn = null;
            conn = MariaDBDataSource.getConnexion();
            System.out.println("Connection réussie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}