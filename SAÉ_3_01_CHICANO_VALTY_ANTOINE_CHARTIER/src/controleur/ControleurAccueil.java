package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.Accueil;
import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;

public class ControleurAccueil implements ActionListener{
	
	private Accueil vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurAccueil(Accueil vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Se Garer":
				fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
				break;
			case "Ajout Véhicule":
				fenetrePrincipal.ChangementVue(VUE.AJOUTERVEHICULE);
				break;
			case "Historique des Stationnements":
				fenetrePrincipal.ChangementVue(VUE.HISTORIQUESTATIONNEMENT);
				break;
			case "Gérer mes Véhicules":
				fenetrePrincipal.ChangementVue(VUE.GESTIONVEHICULE);
				break;
			case "Quitter l'application":
				fenetrePrincipal.dispose();
				break;
			case "Parkings disponibles":
				fenetrePrincipal.ChangementVue(VUE.LISTEPARKING);
				break;
		}}
	}
