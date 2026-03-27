package modele.dao.requetes.requeteparking;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Parking;
import modele.dao.requetes.Requete;

public class ParkingDeleteById extends Requete<Parking> {


	@Override
	public String requete() {
		return "delete from parking where id_parking = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
