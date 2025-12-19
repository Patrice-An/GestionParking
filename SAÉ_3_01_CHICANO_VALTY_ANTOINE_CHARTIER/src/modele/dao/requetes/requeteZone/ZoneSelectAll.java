package modele.dao.requetes.requeteZone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneSelectAll extends Requete<Zone> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "select * from zone";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	}
}