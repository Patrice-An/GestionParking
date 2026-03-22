package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.Accueil;
import vue.App;
import vue.App.VUE;

public class ControleurAccueil implements ActionListener{
	
	private Accueil vue;
	private App App;
	
	public ControleurAccueil(Accueil vue, App App) {
		this.vue = vue;
		this.App = App;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Se Garer":
				App.ChangementVue(VUE.CHOIXSEGARER);
				break;
			case "Historique des Stationnements":
				App.ChangementVue(VUE.HISTORIQUESTATIONNEMENT);
				break;
			case "Gérer mes Véhicules":
				App.ChangementVue(VUE.GESTIONVEHICULE);
				break;
			case "Parkings disponibles":
				App.ChangementVue(VUE.LISTEPARKING);
				break;
			case "Gestion abonnement":
				App.ChangementVue(VUE.GESTION_ABONNEMENT);
				break;
			case "Se déconnecter":
				App.ChangementVue(VUE.SECONNECTER);
				this.App.setUtilisateur(null);
				break;
			case "Quitter l'application":
				App.dispose();
				break;
		}}
	}
