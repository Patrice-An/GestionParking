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

    private final InfoSeGarerParking vue;
	private final App app;

    public ControleurInfoSeGarer(InfoSeGarerParking vue, App app) {
        this.vue = vue;
        this.app = app;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Valider" -> {
                            if (app.getUtilisateur().getVehicules().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Aucun véhicule utilisable pour se garer", "Informations invalides", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            Parking parking = (Parking) vue.getDeroulantSelectionParking().getModel().getSelectedItem();
                            Vehicule vehicule = (Vehicule) vue.getChoixVehicule().getModel().getSelectedItem();
                            int place = 0;
                            byte borne = 0;
                            LocalDateTime heureArrivee = LocalDateTime.now();
                            try {
                                new DaoParking().seGarerParking(parking.getidParking(), vehicule.getImmatriculation(), heureArrivee, place, borne);
                                JOptionPane.showMessageDialog(null, "Le stationnement est validé !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
                                app.refresh();
                                app.changementVue(VUE.CHOIXSEGARER);
                            } catch(SQLException eee) {
                                //empty
                            }
            }
			case "Annuler" -> app.changementVue(VUE.CHOIXSEGARER);
			default -> {
				//aucun autre cas
            }
		}
	}

}
