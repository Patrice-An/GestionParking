package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import vue.AjouterVehicule;
import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.DaoVehicule;

public class ControleurAjouterVehicule implements ActionListener{
	
	private FenetrePrincipal fenetrePrincipal;
	private AjouterVehicule vue;

	public ControleurAjouterVehicule(AjouterVehicule vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Enregistrer":
				String immat = vue.getImmatriculation();
				float hauteur = vue.getHauteur();
				int id_user = fenetrePrincipal.getUtilisateur().getid();
				String designation = vue.getDésignation();
				Type_vehicule type = vue.getTypeVehicule();
				Vehicule vehicule = new Vehicule(immat, hauteur, id_user, designation, type);
				try {
					new DaoVehicule().create(vehicule);
					fenetrePrincipal.getUtilisateur().addVehicule(vehicule);
					fenetrePrincipal.getUtilisateur().getVehicules();
					JOptionPane.showMessageDialog(null, "Le véhicule a bien été ajouté");
					fenetrePrincipal.refresh();;
					fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
				} catch(SQLException exception) {
					exception.printStackTrace();
				}
				break;
			case "Annuler":
				fenetrePrincipal.ChangementVue(VUE.GESTIONVEHICULE);
				break;
				
		}
	}
}
