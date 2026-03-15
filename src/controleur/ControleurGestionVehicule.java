package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modele.Vehicule;
import modele.dao.DaoVehicule;
import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.GestionVehicule;

public class ControleurGestionVehicule implements ActionListener {

	private FenetrePrincipal fenPrincipal;
	public ControleurGestionVehicule(GestionVehicule vue, FenetrePrincipal fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Vehicule vehicule = (Vehicule) b.getClientProperty("vehicule");
		switch (b.getText()) {
			case "Ajouter un véhicule":
				fenPrincipal.ChangementVue(VUE.AJOUTERVEHICULE);
				fenPrincipal.refresh();
				break;
			case "Rechercher":
				break;
			case "Modifier":
				JTextField designation = (JTextField) b.getClientProperty("désignation");
				JTextField type = (JTextField) b.getClientProperty("type");
				JTextField hauteur = (JTextField) b.getClientProperty("hauteur");

				// Activation des champs
				designation.setEnabled(true);
				type.setEnabled(true);
				hauteur.setEnabled(true);

				break;
			case "Supprimer" :
				try {
					new DaoVehicule().deleteById(vehicule.getImmatriculation()); 
					JOptionPane.showMessageDialog(null, "Le véhicule a bien été supprimé");
					fenPrincipal.getUtilisateur().removeVehicule(vehicule);
					fenPrincipal.refresh();
				} catch (SQLException _) {
					JOptionPane.showMessageDialog(null, "Le véhicule est stationné et ne peux pas être supprimé");
				}
				break;
			default:
				break;
		}

	}

}
