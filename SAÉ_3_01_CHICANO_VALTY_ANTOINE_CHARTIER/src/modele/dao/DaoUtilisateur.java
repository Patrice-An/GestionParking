package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Utilisateur;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteUtilisateur.CreateUtilisateur;
import modele.dao.requetes.requeteUtilisateur.SelectAllUtilisateur;
import modele.dao.requetes.requeteUtilisateur.SelectUtilisateurById;
import modele.dao.requetes.requeteUtilisateur.UtilisateurConnection;
import modele.dao.requetes.requeteUtilisateur.UtilisateurDeleteById;

public class DaoUtilisateur implements Dao<Utilisateur, Integer> {
	
	@Override
	public Utilisateur createInstance(ResultSet curseur) throws SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(curseur.getString(5), formatter);
		return new Utilisateur(
			curseur.getInt(1),
			curseur.getString(2),
			curseur.getString(3),
			curseur.getString(4),
			date,
			curseur.getString(6),
			curseur.getString(7));
	}

	@Override
	public void create(Utilisateur utilisateur) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new CreateUtilisateur();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = formatter.format(utilisateur.getNaissance());
		req.parametres(
			stUtil,
			utilisateur.getNom(),
			utilisateur.getPrenom(),
			utilisateur.getTel(),
			date,
			utilisateur.getMail(),
			utilisateur.getPassword());
		stUtil.executeUpdate();
	}
	
	@Override
	public Optional<Utilisateur> findById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new SelectUtilisateurById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, id.toString());
		ResultSet res = stUtil.executeQuery();
		if (res.next()) {
			return Optional.of(this.createInstance(res));
		} else {
			return Optional.of(null);
		}
	}
	
	@Override
	public List<Utilisateur> findAll() throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new SelectAllUtilisateur();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		ResultSet resSet = stUtil.executeQuery();
		ArrayList<Utilisateur> resList = new ArrayList<Utilisateur>();
		while (resSet.next()) {
			resList.add(this.createInstance(resSet));
		}
		return resList;
	}

	public boolean utilisateurCanConnect(String email, String password) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new UtilisateurConnection();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, email, password);
		ResultSet resSet = stUtil.executeQuery();
		return resSet.next();
	}

	@Override
	public void update(Utilisateur utilisateur) throws SQLException {
		//todo
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new UtilisateurDeleteById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, String.valueOf(id));
		int res = stUtil.executeUpdate();
		return res > 0;
	}
}