package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteVehicule.CreateVehicule;
import modele.dao.requetes.requeteVehicule.VehiculeDeleteById;
import modele.dao.requetes.requeteVehicule.VehiculeSelectAll;
import modele.dao.requetes.requeteVehicule.VehiculeSelectById;

public class DaoVehicule implements Dao<Vehicule, String> {

	@Override
	public Vehicule createInstance(ResultSet curseur) throws SQLException {
		return new Vehicule(
			curseur.getString(1),
			curseur.getFloat(2),
			curseur.getInt(3),
			curseur.getString(4),
			Type_vehicule.valueOf(curseur.getString(5)));
	}

	@Override
	public void create(Vehicule vehicule) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Vehicule> req = new CreateVehicule();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(
			stUtil,
			vehicule.getImmatriculation(),
			String.valueOf(vehicule.getHauteur()),
			String.valueOf(vehicule.getId_utilisateur()),
			vehicule.getDésignation(),
			vehicule.getType().name()
		);
		stUtil.executeUpdate();
	}
	
	@Override
	public Optional<Vehicule> findById(String id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Vehicule> req = new VehiculeSelectById();
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
		Requete<Vehicule> req = new VehiculeSelectAll();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		ResultSet resSet = stUtil.executeQuery();
		ArrayList<Vehicule> resList = new ArrayList<Vehicule>();
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
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Vehicule> req = new VehiculeDeleteById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, String.valueOf(id));
		int res = stUtil.executeUpdate();
		return res > 0;
	}
}
