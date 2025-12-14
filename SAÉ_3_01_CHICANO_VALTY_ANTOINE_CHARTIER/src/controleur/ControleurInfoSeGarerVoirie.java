package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.InfoSeGarerVoirie;
import modele.Zone;
import modele.dao.DaoZone;

public class ControleurInfoSeGarerVoirie implements ActionListener{
	
	private InfoSeGarerVoirie vue;
	private FenetrePrincipal fenetrePrincipal;
	private List<Zone> zones;

	public ControleurInfoSeGarerVoirie(InfoSeGarerVoirie vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
		try {
			this.zones = new DaoZone().findAll();
		} catch (SQLException e) {
			this.zones = new ArrayList<Zone>();
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
		}
	}

	public List<Zone> getZones() {
		return zones;
	}

}
