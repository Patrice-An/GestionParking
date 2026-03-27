package modele.dao.requetes.requetetarif;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Tarif;
import modele.dao.requetes.Requete;

public class TarifDeleteById extends Requete<Tarif> {

    @Override
	public String requete()  {
		return "delete from tarif where id_tarif = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
