package modele.dao.requetes.requeteTransport;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Transport;
import modele.dao.requetes.Requete;

public class TransportFindAll extends Requete<Transport> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "select * from transport";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
