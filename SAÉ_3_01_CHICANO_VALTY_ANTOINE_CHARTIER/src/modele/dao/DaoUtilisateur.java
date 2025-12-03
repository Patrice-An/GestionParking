package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Utilisateur;
import modele.dao.requetes.Requete;
import modele.dao.requetes.RequeteSelectAllUtilisateur;
import modele.dao.requetes.RequeteSelectUtilisateurById;

public class DaoUtilisateur implements Dao<Utilisateur, Integer> {
	
	@Override
	public Utilisateur createInstance(ResultSet curseur) throws SQLException {
		curseur.next();
		LocalDate naissance = LocalDate.of(2000, 1, 1);
		return new Utilisateur(curseur.getString(1),
			curseur.getString(2),
			curseur.getString(3),
			curseur.getString(4),
			naissance);
	}

	@Override
	public void create(Utilisateur utilisateur) {

	}
	
	@Override
	public Optional<Utilisateur> findById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new RequeteSelectUtilisateurById();
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
		Requete<Utilisateur> req = new RequeteSelectAllUtilisateur();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		ResultSet resSet = stUtil.executeQuery();
		ArrayList<Utilisateur> resList = new ArrayList<Utilisateur>();
		while (resSet.next()) {
			resList.add(this.createInstance(resSet));
		}
		return resList;
	}

	@Override
	public void update(Utilisateur utilisateur) {

	}

	@Override
	public boolean deleteById(Integer id) {
		return true;
	}

}
