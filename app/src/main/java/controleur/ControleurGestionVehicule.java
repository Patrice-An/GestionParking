package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modele.Vehicule;
import modele.dao.DaoVehicule;
import vue.App;
import vue.App.VUE;
public class ControleurGestionVehicule implements ActionListener {

	private final App app;
	public ControleurGestionVehicule(App app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Vehicule vehicule = (Vehicule) b.getClientProperty("vehicule");
		switch (b.getText()) {
			case "Ajouter un véhicule" -> {
                            app.ChangementVue(VUE.AJOUTERVEHICULE);
                            app.refresh();
                }
			case "Modifier" -> {
                            JTextField designation = (JTextField) b.getClientProperty("désignation");
                            JTextField type = (JTextField) b.getClientProperty("type");
                            JTextField hauteur = (JTextField) b.getClientProperty("hauteur");
                            
                            // Activation des champs
                            designation.setEnabled(true);
                            type.setEnabled(true);
                            hauteur.setEnabled(true);
                }
			case "Supprimer" -> {
                            try {
                                new DaoVehicule().deleteById(vehicule.getImmatriculation());
                                JOptionPane.showMessageDialog(null, "Le véhicule a bien été supprimé");
                                app.getUtilisateur().removeVehicule(vehicule);
                                app.refresh();
                            } catch (SQLException eee) {
                                JOptionPane.showMessageDialog(null, "Le véhicule est stationné et ne peux pas être supprimé");
                            }
                }
			default -> {//aucapp
                }
		}

	}

}
