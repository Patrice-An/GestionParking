package modele.dao.requetes.requeteVehicule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class VehiculeDeleteById extends Requete<Vehicule> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "delete from vehicule where immat = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
