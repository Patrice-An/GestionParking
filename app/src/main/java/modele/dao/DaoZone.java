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

import modele.Zone;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteZone.CreateZone;
import modele.dao.requetes.requeteZone.ZoneDeleteById;
import modele.dao.requetes.requeteZone.ZoneSeGarer;
import modele.dao.requetes.requeteZone.ZoneSelectAll;
import modele.dao.requetes.requeteZone.ZoneSelectById;

public class DaoZone implements Dao<Zone, Integer> {

    @Override
	public Zone createInstance(ResultSet curseur) throws SQLException {
		return new Zone(curseur.getInt(1), curseur.getString(2));
	}

	@Override
	public void create(Zone zone) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Zone> req = new CreateZone();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(
			stUtil,
            String.valueOf(zone.getId()),
            zone.getCouleur()
		);
		stUtil.executeUpdate();
	}
	
	@Override	
	public Optional<Zone> findById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Zone> req = new ZoneSelectById();
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
	public List<Zone> findAll() throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Zone> req = new ZoneSelectAll();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());

		try {
			ResultSet resSet = stUtil.executeQuery();
			List<Zone> resList = new ArrayList<>();
			while (resSet.next()) {
				resList.add(this.createInstance(resSet));
			}
			return resList;
		} finally {
			stUtil.close();
		}

	}

	@Override
	public void update(Zone zone) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Zone> req = new ZoneDeleteById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, id.toString());
		int res = stUtil.executeUpdate();
		return res > 0;
	}

	public void seGarerZone(int idZone, String immat, LocalDateTime heureArrivee, LocalDateTime heureSortie, int place, byte borne) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Zone> req = new ZoneSeGarer();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		req.parametres(
			stUtil,
			String.valueOf(idZone),
			immat,
			heureArrivee.format(formatter),
			heureSortie.format(formatter),
			String.valueOf(place),
			String.valueOf(borne)
		);
		stUtil.executeUpdate();
	}

}
