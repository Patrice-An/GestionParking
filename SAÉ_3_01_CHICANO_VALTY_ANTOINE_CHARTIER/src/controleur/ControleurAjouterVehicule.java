package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;

import main.java.vue.AjouterVehicule;
import main.java.vue.FenetrePrincipal;
import main.java.vue.FenetrePrincipal.VUE;
import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.DaoVehicule;

public class ControleurAjouterVehicule implements ActionListener{
	
	private AjouterVehicule vue;
	private FenetrePrincipal fenetrePrincipal;
	public ControleurAjouterVehicule(AjouterVehicule vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.vue = vue;
	}

	public void actionPerformedButton(JButton b) {
		if (b.getText().equals("Annuler")) {
			System.out.println("Action bouton");
		}
		System.out.println("Action bouton");
	}
	
	public void actionPerformedField(JTextField f) {
		System.out.println("action field");
		f.setText("");
		
		
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
				} catch(SQLException exception) {
					exception.printStackTrace();
				}
				break;
			case "Annuler":
				fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
				break;
		}
	}
}
