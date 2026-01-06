package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.GestionVehicule;

public class ControleurGestionVehicule implements ActionListener{

	private FenetrePrincipal fenPrincipal;
	private GestionVehicule vue;
	
	public ControleurGestionVehicule(GestionVehicule vue, FenetrePrincipal fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
		this.vue = vue;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Ajouter un véhicule" :
			fenPrincipal.ChangementVue(VUE.AJOUTERVEHICULE);
			break;
			case "Rechercher":
				break;
		}
		
	}

}
