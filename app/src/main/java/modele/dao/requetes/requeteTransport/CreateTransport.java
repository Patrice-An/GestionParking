package modele.dao.requetes.requeteTransport;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Transport;
import modele.dao.requetes.Requete;

public class CreateTransport extends Requete<Transport> {

    @Override
	public String requete()  {
		return "insert into transport values(?, ?, ?)";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
		prSt.setString(3, id[2]);
	}

}