package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Parking;
import modele.Vehicule;
import modele.dao.DaoParking;
import vue.App;
import vue.App.VUE;
import vue.InfoSeGarerParking;

public class ControleurInfoSeGarer implements ActionListener {

    private InfoSeGarerParking vue;
	private App App;

    public ControleurInfoSeGarer(InfoSeGarerParking vue, App App) {
        this.vue = vue;
        this.App = App;
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
				Parking parking = (Parking) vue.getDeroulantSelectionParking().getModel().getSelectedItem();
				Vehicule vehicule = (Vehicule) vue.getChoixVehicule().getModel().getSelectedItem();
				int place = 0;
				byte borne = 0;
				LocalDateTime heureArrivee = LocalDateTime.now();
				try {
					new DaoParking().seGarerParking(parking.getId_parking(), vehicule.getImmatriculation(), heureArrivee, place, borne);
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

}
