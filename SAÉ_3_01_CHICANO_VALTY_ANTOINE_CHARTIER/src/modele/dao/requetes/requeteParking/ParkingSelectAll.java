package modele.dao.requetes.requeteParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Parking;
import modele.dao.requetes.Requete;

public class ParkingSelectAll extends Requete<Parking> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "select * from parking";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {

	}

}
