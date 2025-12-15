package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.InfoSeGarerVoirie;
import modele.Tarif;
import modele.Zone;
import modele.dao.DaoZone;

public class ControleurInfoSeGarerVoirie implements ActionListener{
	
	private InfoSeGarerVoirie vue;
	private FenetrePrincipal fenetrePrincipal;
	private List<Zone> zones;
	public ControleurVoirieTarif controleurVoirieTarif;

	public ControleurInfoSeGarerVoirie(InfoSeGarerVoirie vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
		this.controleurVoirieTarif = new ControleurVoirieTarif(this);
		try {
			this.zones = new DaoZone().findAll();
		} catch (SQLException e) {
			this.zones = new ArrayList<Zone>();
			e.printStackTrace();
		}
	}

	public class ControleurVoirieTarif implements ItemListener {

		private ControleurInfoSeGarerVoirie controleurParent;

		public ControleurVoirieTarif(ControleurInfoSeGarerVoirie controleurParent) {
			this.controleurParent = controleurParent;
		}

		public void itemStateChanged(ItemEvent arg0) {
			this.controleurParent.updateComboTarif();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
		}
	}

	public void updateComboTarif() {
		JComboBox<Zone> comboZone = vue.getComboZone();
		Zone zone = (Zone) comboZone.getModel().getSelectedItem();
		System.out.println(zone.toString());
		DefaultComboBoxModel<Tarif> tarifModel = new DefaultComboBoxModel<Tarif>();
		for (Tarif t : zone.getTarifs()) {
			tarifModel.addElement(t);
		}
		vue.getComboTarif().setModel(tarifModel);
	}

	public List<Zone> getZones() {
		return zones;
	}

}
