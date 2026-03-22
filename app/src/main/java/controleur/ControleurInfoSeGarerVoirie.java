package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import vue.App;
import vue.App.VUE;
import vue.InfoSeGarerVoirie;
import modele.Tarif;
import modele.Vehicule;
import modele.Zone;
import modele.dao.DaoZone;

public class ControleurInfoSeGarerVoirie implements ActionListener {

	private InfoSeGarerVoirie vue;
	private App App;
	private List<Zone> zones;
	public ControleurVoirieTarif controleurVoirieTarif;

	public ControleurInfoSeGarerVoirie(InfoSeGarerVoirie vue, App App) {
		this.vue = vue;
		this.App = App;
		this.controleurVoirieTarif = new ControleurVoirieTarif(this);
		try {
			this.zones = new DaoZone().findAll();
		} catch (SQLException eee) {
			this.zones = new ArrayList<>();
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
		switch (b.getText()) {
			case "Valider":
				if (App.getUtilisateur().getVehicules().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Aucun véhicule utilisable pour se garer", "Informations invalides", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Vehicule vehicule = (Vehicule) vue.getComboVehicule().getModel().getSelectedItem();
				Zone zone = (Zone) vue.getComboZone().getModel().getSelectedItem();
				Tarif tarif = (Tarif) vue.getComboTarif().getSelectedItem();
				LocalDateTime heureArrivee = LocalDateTime.now();
				LocalDateTime heureSortie = LocalDateTime.now();
				heureSortie = heureSortie.plusHours((long)Math.floor(tarif.getDurée()));
				heureSortie = heureSortie.plusMinutes((long)((tarif.getDurée() - Math.floor(tarif.getDurée()))*60f));
				int place = 0;
				byte borne = 0;
				try {
					new DaoZone().seGarerZone(zone.getId(), vehicule.getImmatriculation(), heureArrivee, heureSortie, place, borne);
					JOptionPane.showMessageDialog(null, "Le stationnement est validé !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
					App.refresh();
					App.ChangementVue(VUE.CHOIXSEGARER);
				} catch(SQLException eee) {
					//empty
				}
				break;
			case "Annuler":
				App.ChangementVue(VUE.CHOIXSEGARER);
				break;
			default:
				break;
		}
	}

	public void updateComboTarif() {
		JComboBox<Zone> comboZone = vue.getComboZone();
		Zone zone = (Zone) comboZone.getModel().getSelectedItem();
		DefaultComboBoxModel<Tarif> tarifModel = new DefaultComboBoxModel<>();
		for (Tarif t : zone.getTarifs()) {
			tarifModel.addElement(t);
		}
		vue.getComboTarif().setModel(tarifModel);
	}

	public List<Zone> getZones() {
		return zones;
	}

}
