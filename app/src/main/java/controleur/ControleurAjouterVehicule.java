package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.DaoVehicule;
import vue.AjouterVehicule;
import vue.App;
import vue.App.VUE;

public class ControleurAjouterVehicule implements ActionListener{
	
	private final  App app;
	private final AjouterVehicule vue;

	public ControleurAjouterVehicule(AjouterVehicule vue, App app) {
		this.app = app;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Enregistrer" -> {
                            String immat = vue.getImmatriculation();
                            float hauteur = vue.getHauteur();
                            int idUser = app.getUtilisateur().getid();
                            String designation = vue.getDesignation();
                            Type_vehicule type = vue.getTypeVehicule();
                            Vehicule vehicule = new Vehicule(immat, hauteur, idUser, designation, type);
                            try {
                                new DaoVehicule().create(vehicule);
                                app.getUtilisateur().addVehicule(vehicule);
                                app.getUtilisateur().getVehicules();
                                JOptionPane.showMessageDialog(null, "Le véhicule a bien été ajouté");
                                app.refresh();
                                app.changementVue(VUE.GESTIONVEHICULE);
                            } catch(SQLException eee) {
                                //empty
                            }
                }
			case "Annuler" -> app.changementVue(VUE.GESTIONVEHICULE);
			default -> {//aucun autre cas
                }
		}
	}
}
