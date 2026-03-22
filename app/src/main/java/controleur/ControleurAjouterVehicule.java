package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import vue.AjouterVehicule;
import vue.App;
import vue.App.VUE;
import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.DaoVehicule;

public class ControleurAjouterVehicule implements ActionListener{
	
	private App App;
	private AjouterVehicule vue;

	public ControleurAjouterVehicule(AjouterVehicule vue, App App) {
		this.App = App;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Enregistrer":
				String immat = vue.getImmatriculation();
				float hauteur = vue.getHauteur();
				int idUser = App.getUtilisateur().getid();
				String designation = vue.getDésignation();
				Type_vehicule type = vue.getTypeVehicule();
				Vehicule vehicule = new Vehicule(immat, hauteur, idUser, designation, type);
				try {
					new DaoVehicule().create(vehicule);
					App.getUtilisateur().addVehicule(vehicule);
					App.getUtilisateur().getVehicules();
					JOptionPane.showMessageDialog(null, "Le véhicule a bien été ajouté");
					App.refresh();
					App.ChangementVue(VUE.GESTIONVEHICULE);
				} catch(SQLException eee) {
					//empty
				}
				break;
			case "Annuler":
				App.ChangementVue(VUE.GESTIONVEHICULE);
				break;
			default:
				break;
		}
	}
}
