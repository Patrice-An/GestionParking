package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.InfoSeGarerVoirie;
import modele.Tarif;
import modele.Vehicule;
import modele.Zone;
import modele.dao.DaoZone;

public class ControleurInfoSeGarerVoirie implements ActionListener {

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
		switch (b.getText()) {
			case "Valider":
				if (fenetrePrincipal.getUtilisateur().getVehicules().size() < 1) {
					JOptionPane.showMessageDialog(null, "Aucun véhicule utilisable pour se garer", "Informations invalides", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Vehicule vehicule = (Vehicule) vue.getComboVehicule().getModel().getSelectedItem();
				Zone zone = (Zone) vue.getComboZone().getModel().getSelectedItem();
				Tarif tarif = (Tarif) vue.getComboTarif().getSelectedItem();
				LocalDateTime heureArrivée = LocalDateTime.now();
				LocalDateTime heureSortie = LocalDateTime.now();
				heureSortie = heureSortie.plusHours((long)Math.floor(tarif.getDurée()));
				heureSortie = heureSortie.plusMinutes((long)((tarif.getDurée() - Math.floor(tarif.getDurée()))*60f));
				int place = 0;
				byte borne = 0;
				try {
					new DaoZone().seGarerZone(zone.getId(), vehicule.getImmatriculation(), heureArrivée, heureSortie, place, borne);
					JOptionPane.showMessageDialog(null, "Le stationnement est validé !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
					fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
				} catch(SQLException exception) {
					exception.printStackTrace();
				}
				break;
			case "Annuler":
				fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
				break;
		}
	}

	public void updateComboTarif() {
		JComboBox<Zone> comboZone = vue.getComboZone();
		Zone zone = (Zone) comboZone.getModel().getSelectedItem();
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
