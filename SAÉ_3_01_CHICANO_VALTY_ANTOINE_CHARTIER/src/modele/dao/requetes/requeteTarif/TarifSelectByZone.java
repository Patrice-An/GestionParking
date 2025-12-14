package modele.dao.requetes.requeteTarif;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Tarif;
import modele.dao.requetes.Requete;

public class TarifSelectByZone extends Requete<Tarif> {

    private String requete;

    @Override
	public String requete()  {
		this.requete = "select * from tarif " +
        "join tarif_zone on tarif_zone.id_tarif = tarif.id_tarif " +
        "join zone on zone.id_zone = tarif_zone.id_zone " +
        "where zone.id_zone = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
