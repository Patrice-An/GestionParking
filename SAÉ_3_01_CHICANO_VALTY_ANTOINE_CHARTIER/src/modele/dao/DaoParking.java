package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Parking;
import modele.Utilisateur;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteParking.ParkingDeleteById;
import modele.dao.requetes.requeteParking.ParkingSelectAll;
import modele.dao.requetes.requeteUtilisateur.CreateUtilisateur;
import modele.dao.requetes.requeteUtilisateur.SelectUtilisateurById;

public class DaoParking implements Dao<Parking, Integer> {

    @Override
	public Parking createInstance(ResultSet curseur) throws SQLException {
		return new Parking(
			curseur.getInt(1),
            curseur.getString(2),
            curseur.getString(3),
            curseur.getFloat(4),
            curseur.getInt(5),
            curseur.getInt(6),
            curseur.getInt(7)
		);
	}

	@Override
	public void create(Parking parking) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new CreateUtilisateur();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(
			stUtil,
			parking.getAdresse(),
            parking.getNom(),
            String.valueOf(parking.getHauteur()),
            String.valueOf(parking.getPlaces_max()),
            String.valueOf(parking.getPlace_max_moto()),
            String.valueOf(parking.getPlace_max_electrique()));
		stUtil.executeUpdate();
	}
	
	@Override
	public Optional<Parking> findById(Integer id) throws SQLException {
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
	public List<Parking> findAll() throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Parking> req = new ParkingSelectAll();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		ResultSet resSet = stUtil.executeQuery();
		ArrayList<Parking> resList = new ArrayList<Parking>();
		while (resSet.next()) {
			resList.add(this.createInstance(resSet));
		}
		return resList;
	}

	@Override
	public void update(Parking parking) throws SQLException {
		//todo
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Parking> req = new ParkingDeleteById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, String.valueOf(id));
		int res = stUtil.executeUpdate();
		return res > 0;
	}

}
