package modele.dao.requetes.requeteTarif;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Tarif;
import modele.dao.requetes.Requete;

public class TarifDeleteById extends Requete<Tarif> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "delete from tarif where id_tarif = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
