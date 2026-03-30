package modele.dao.requetes.requeteTarif;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Tarif;
import modele.dao.requetes.Requete;

public class CreateTarif extends Requete<Tarif> {

    @Override
	public String requete()  {
		return "insert into tarif values(?, ?)";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
	}

}
