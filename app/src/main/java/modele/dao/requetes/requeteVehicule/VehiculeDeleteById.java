package modele.dao.requetes.requetevehicule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class VehiculeDeleteById extends Requete<Vehicule> {

    

	@Override
	public String requete() {
		return "delete from vehicule where immat = ?";
		
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
