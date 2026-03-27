package modele.dao.requetes.requetezone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneSelectById extends Requete<Zone> {

    

    @Override
	public String requete()  {
		return "select * from zone where id_zone = ?";
		
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}