package modele.dao.requetes.requeteZone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneDeleteById extends Requete<Zone> {

    

    @Override
	public String requete()  {
		return "delete zone where id_zone = ?";
		
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}