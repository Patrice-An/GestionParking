package modele.dao.requetes.requetezone;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneSeGarer extends Requete<Zone> {

    

    @Override
	public String requete()  {
		return "insert into garer values(?, 0, ?, ?, ?, ?, ?);";
		
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
		prSt.setString(3, id[2]);
		prSt.setString(4, id[3]);
		prSt.setString(5, id[4]);
		prSt.setString(6, id[5]);
	}
}