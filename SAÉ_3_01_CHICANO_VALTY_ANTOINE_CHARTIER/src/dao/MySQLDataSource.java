package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe g�rant l'acc�s � la base de donn�es. Cette classe met en oeuvre le
 * patron singleton pour assurer l'unicit� des connexions � la base de donn�es.
 *
 * @author Millan
 */
public class MySQLDataSource
{
	private static final String NOM_SCHEMA = "parking_database";
    /**
     * Attribut de classe contenant la connexion courante.
     */
    private static Connection connexion = null;
    /**
     * Attibut contenant le login pour l'acc�s � la base de donn�es.
     */
    private static String login;
    /**
     * Attribut contenant le mot de passe �la base de donn�es.
     */
    private static String motDePasse;
    /**
     * Cha�ne de connexion � la base de donn�es. Le sch�ma de connexion contient
     * le sch�ma par d�faut. Dans notre cas le sch�ma se nomm scolariteiut.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/" +
    					MySQLDataSource.NOM_SCHEMA + "?serverTimezone=UTC";

    /**
     * Constructeur de la classe qui doit �tre priv� pour respecter le patron
     * singleton.
     */
    private MySQLDataSource()
    {
        // Volontairement vide
    }

    /**
     * M�thode permettant de fournir le login et le mot de passe pour acc�der
     * aux donn�es de la base de donn�es.
     *
     * @param pLogin login d'acc�s �la base de donn�es.
     * @param pMdp Mot de passe �la base de donn�es.
     */
    public static void creerAcces(String pLogin, String pMdp)
    {
        MySQLDataSource.login       = pLogin;
        MySQLDataSource.motDePasse  = pMdp;
        MySQLDataSource.connexion   = null ;
    }

    /**
     * M�thode de classe retournant la connexion courante. Si aucune connexion
     * existe, celle-ci est cr��e.
     * @return la connexion courante
     * @throws SQLException 
     */
    public static Connection getConnexion() throws SQLException
    {
        if (MySQLDataSource.connexion == null)
        {           
            MySQLDataSource.connexion = DriverManager.getConnection(
                    MySQLDataSource.URL, 
                    MySQLDataSource.login, 
                    MySQLDataSource.motDePasse);
            MySQLDataSource.connexion.setAutoCommit(true);
        }

        return MySQLDataSource.connexion;
    }

    /**
     * M�thode permettant la validation de la connexion courante.
     * @throws SQLException 
     */
    public static void commit() throws SQLException
    {
        MySQLDataSource.connexion.commit();
    }
    
    /**
     * M�thode permettant l'annulation de la connexion courante
     * @throws SQLException 
     */
    public static void rollback() throws SQLException
    {
        MySQLDataSource.connexion.rollback();
    }

    /**
     * M�thode fermant la connexion courante. <B> attention, cette m�thode
     * ne valide pas la connexion courante.</B>
     * @throws SQLException 
     */
    public static void deconnecter() throws SQLException
    {
        MySQLDataSource.connexion.close();
        MySQLDataSource.connexion = null;
    }
    
    
    public static void main(String[] args) {
    	System.out.println("Hello World");
    }
}
