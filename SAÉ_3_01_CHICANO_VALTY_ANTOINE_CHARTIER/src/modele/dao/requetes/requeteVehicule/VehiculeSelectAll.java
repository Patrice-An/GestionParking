package modele.dao.requetes.requeteVehicule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class VehiculeSelectAll extends Requete<Vehicule> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "select * from véhicule";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {

	}

}
