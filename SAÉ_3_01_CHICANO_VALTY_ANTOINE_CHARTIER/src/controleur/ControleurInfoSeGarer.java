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
import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.InfoSeGarerParking;

public class ControleurInfoSeGarer implements ActionListener {

    private InfoSeGarerParking vue;
	private FenetrePrincipal fenetrePrincipal;

    public ControleurInfoSeGarer(InfoSeGarerParking vue, FenetrePrincipal fenetrePrincipal) {
        this.vue = vue;
        this.fenetrePrincipal = fenetrePrincipal;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Valider":
				Parking parking = (Parking) vue.getDeroulantSelectionParking().getModel().getSelectedItem();
				Vehicule vehicule = (Vehicule) vue.getChoixVehicule().getModel().getSelectedItem();
				int place = 0;
				byte borne = 0;
				LocalDateTime heureArrivée = LocalDateTime.now();
				try {
					new DaoParking().seGarerParking(parking.getId_parking(), vehicule.getImmatriculation(), heureArrivée, place, borne);
					JOptionPane.showMessageDialog(null, "Le stationnement est validé !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
					fenetrePrincipal.refresh();
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

}
