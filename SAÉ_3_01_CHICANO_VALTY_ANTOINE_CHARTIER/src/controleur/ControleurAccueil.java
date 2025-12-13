package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.FenetrePrincipal;
import main.java.vue.FenetrePrincipal.VUE;

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
			case "Quitter l'application":
				fenetrePrincipal.dispose();
				break;
			case "Ajout Véhicule":
				fenetrePrincipal.ChangementVue(VUE.AJOUTERVEHICULE);
				break;
		}}
	}
