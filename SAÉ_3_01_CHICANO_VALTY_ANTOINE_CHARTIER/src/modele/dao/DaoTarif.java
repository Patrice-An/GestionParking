package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Tarif;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requeteTarif.CreateTarif;
import modele.dao.requetes.requeteTarif.TarifDeleteById;
import modele.dao.requetes.requeteTarif.TarifSelectAll;
import modele.dao.requetes.requeteTarif.TarifSelectById;

public class DaoTarif implements Dao<Tarif, Integer> {

    @Override
	public Tarif createInstance(ResultSet curseur) throws SQLException {
		return new Tarif(curseur.getInt(1), curseur.getFloat(2), curseur.getInt(3));
	}

	@Override
	public void create(Tarif tarif) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Tarif> req = new CreateTarif();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(
			stUtil,
			String.valueOf(tarif.getId()),
            String.valueOf(tarif.getDurée()),
            String.valueOf(tarif.getMajoration())
		);
		stUtil.executeUpdate();
	}
	
	@Override	
	public Optional<Tarif> findById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Tarif> req = new TarifSelectById();
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
	public List<Tarif> findAll() throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Tarif> req = new TarifSelectAll();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		ResultSet resSet = stUtil.executeQuery();
		List<Tarif> resList = new ArrayList<Tarif>();
		while (resSet.next()) {
			resList.add(this.createInstance(resSet));
		}
		return resList;
	}

	@Override
	public void update(Tarif tarif) throws SQLException {
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Tarif> req = new TarifDeleteById();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, id.toString());
		int res = stUtil.executeUpdate();
		return res > 0;
	}

}
