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
import modele.Vehicule;
import modele.dao.requetes.Requete;
import modele.dao.requetes.Utilisateur.CreateUtilisateur;
import modele.dao.requetes.Utilisateur.SelectAllUtilisateur;
import modele.dao.requetes.Utilisateur.SelectUtilisateurById;

public class DaoVehicule implements Dao<Utilisateur, String> {

	@Override
	public Vehicule createInstance(ResultSet curseur) throws SQLException {
		
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
	public Optional<Vehicule> findById(Integer id) throws SQLException {
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
	public List<Vehicule> findAll() throws SQLException {
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

	@Override
	public void update(Vehicule vehicule) throws SQLException {
		
	}

	@Override
	public boolean deleteById(String id) throws SQLException {
		return true;
	}
}
