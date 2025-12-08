package modele.dao.requetes.requeteParking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Parking;
import modele.dao.requetes.Requete;

public class ParkingDeleteById extends Requete<Parking> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "delete from parking where id_parking = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
