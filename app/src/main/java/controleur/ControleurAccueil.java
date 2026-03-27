package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.App.VUE;

public class ControleurAccueil implements ActionListener{
	

	private final App app;
	
	public ControleurAccueil(App app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Se Garer" -> app.ChangementVue(VUE.CHOIXSEGARER);
			case "Historique des Stationnements" -> app.ChangementVue(VUE.HISTORIQUESTATIONNEMENT);
			case "Gérer mes Véhicules" -> app.ChangementVue(VUE.GESTIONVEHICULE);
			case "Parkings disponibles" -> app.ChangementVue(VUE.LISTEPARKING);
			case "Gestion abonnement" -> app.ChangementVue(VUE.GESTION_ABONNEMENT);
			case "Se déconnecter" -> {
                            app.ChangementVue(VUE.SECONNECTER);
                            this.app.setUtilisateur(null);
                }
			case "Quitter l'application" -> app.dispose();
			default -> { //aucun autre cas
			}
		}}
	}
