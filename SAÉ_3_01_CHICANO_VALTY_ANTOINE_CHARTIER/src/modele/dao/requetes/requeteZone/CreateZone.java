package modele.dao.requetes.requeteZone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class CreateZone extends Requete<Zone> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "insert into zone values(?)";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
	
}