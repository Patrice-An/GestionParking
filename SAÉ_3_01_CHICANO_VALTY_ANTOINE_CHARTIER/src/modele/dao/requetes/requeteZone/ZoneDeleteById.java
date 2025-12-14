package modele.dao.requetes.requeteZone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneDeleteById extends Requete<Zone> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "delete zone where id_zone = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}