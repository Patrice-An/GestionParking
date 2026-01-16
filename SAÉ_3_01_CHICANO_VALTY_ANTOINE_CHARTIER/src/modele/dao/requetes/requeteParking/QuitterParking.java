package modele.dao.requetes.requeteParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Parking;
import modele.dao.requetes.Requete;

public class QuitterParking extends Requete<Parking> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "update garer set heureSortie = ? where id_parking = ? and heureArrivée = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
		prSt.setString(3, id[2]);
	}
}