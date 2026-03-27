package modele.dao.requetes.requetevehicule;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class VehiculeExistePourUser extends Requete<Vehicule> {

    

    @Override
	public String requete()  {
		return "select * from vehicule where immat = ? and id_utilisateur = ?";
		
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
	}

}
