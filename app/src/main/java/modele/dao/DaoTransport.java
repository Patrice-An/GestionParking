package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modele.Transport;
import modele.Type_Transport;
import modele.Utilisateur;
import modele.dao.requetes.Requete;
import modele.dao.requetes.requetetransport.CreateTransport;
import modele.dao.requetes.requetetransport.TransportFindAll;
import modele.dao.requetes.requetetransport.TransportFindById;
import modele.dao.requetes.requeteutilisateur.EmailUnique;
import modele.dao.requetes.requeteutilisateur.UtilisateurConnection;
import modele.dao.requetes.requeteutilisateur.UtilisateurDeleteById;

public class DaoTransport implements Dao<Transport, Integer> {

    @Override
	public Transport createInstance(ResultSet curseur) throws SQLException {
		return new Transport(
			curseur.getInt(1),
			curseur.getString(2),
			curseur.getString(3),
			Type_Transport.valueOf(curseur.getString(4))
		);
	}

	@Override
	public void create(Transport transport) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Transport> req = new CreateTransport();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(
			stUtil,
			String.valueOf(transport.getId()),
			transport.getAdresse(),
			transport.getLigne(),
			transport.getType().name()
		);
		stUtil.executeUpdate();
	}
	
	@Override
	public Optional<Transport> findById(Integer id) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Transport> req = new TransportFindById();
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
	public List<Transport> findAll() throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Transport> req = new TransportFindAll();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		try {
			ResultSet resSet = stUtil.executeQuery();
			List<Transport> resList = new ArrayList<>();
			while (resSet.next()) {
				resList.add(this.createInstance(resSet));
			}
			return resList;
		} finally {
			stUtil.close();
		}
	}

	public Optional<Transport> utilisateurCanConnect(String email, String password) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new UtilisateurConnection();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, email, password);
		ResultSet resSet = stUtil.executeQuery();
		if (resSet.next()) {
			return Optional.of(createInstance(resSet));
		} else {
			return Optional.empty();
		}
	}

	public boolean emailUnique(String email) throws SQLException {
		Connection cn = AzureSQLDataSource.getConnexion();
		Requete<Utilisateur> req = new EmailUnique();
		PreparedStatement stUtil = cn.prepareStatement(req.requete());
		req.parametres(stUtil, email);
		ResultSet resSet = stUtil.executeQuery();
		return !resSet.next();
	}

	@Override
	public void update(Transport transport) throws SQLException {
		throw new UnsupportedOperationException();
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
