package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Parking;
import modele.Utilisateur;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteParking.ParkingDeleteById;
import modele.dao.requetes.requeteParking.ParkingSeGarer;
import modele.dao.requetes.requeteParking.ParkingSelectAll;
import modele.dao.requetes.requeteParking.ParkingSelectById;
import modele.dao.requetes.requeteUtilisateur.CreateUtilisateur;

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
		Requete<Parking> req = new ParkingSelectById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, id.toString());
		ResultSet res = stUtil.executeQuery();
		if (res.next()) {
			return Optional.of(this.createInstance(res));
		} else {
			return Optional.empty();
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

	public void seGarerParking(int id_parking, String immat, LocalDateTime heureArrivée, int place, byte borne) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Parking> req = new ParkingSeGarer();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		req.parametres(
			stUtil,
			String.valueOf(id_parking),
			immat,
			heureArrivée.format(formatter),
			null,
			String.valueOf(place),
			String.valueOf(borne)
		);
		stUtil.executeUpdate();
	}

}
