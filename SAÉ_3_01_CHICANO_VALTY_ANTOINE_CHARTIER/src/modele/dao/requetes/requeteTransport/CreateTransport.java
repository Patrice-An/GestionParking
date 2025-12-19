package modele.dao.requetes.requeteTransport;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Transport;
import modele.dao.requetes.Requete;

public class CreateTransport extends Requete<Transport> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "insert into transport values(?, ?, ?)";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
		prSt.setString(3, id[2]);
	}

}