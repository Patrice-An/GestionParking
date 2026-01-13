package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Parking;
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
				int heure = (Integer) vue.getHeuresComboBox().getModel().getSelectedItem();
				int minute = (Integer) vue.getMinutesComboBox().getModel().getSelectedItem();
				if (heure == 0 && minute == 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner une durée", "Informations invalides", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Parking parking = (Parking) vue.getDeroulantSelectionParking().getModel().getSelectedItem();
				int plane = 0;
				byte borne = 0;
				break;
			case "Annuler":
				fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
				break;
		}
	}

}
