package modele.dao.requetes.requetetransport;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Transport;
import modele.dao.requetes.Requete;

public class TransportFindById extends Requete<Transport> {

    @Override
	public String requete()  {
		return "select * from transport where id_transport = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
