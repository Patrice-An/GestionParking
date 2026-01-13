package modele.dao.requetes.requeteVehicule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class UpdateVehicule extends Requete<Vehicule> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "update véhicule set hauteur = ?, designation = ?, type = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
		prSt.setString(3, id[2]);
        
    }

}
