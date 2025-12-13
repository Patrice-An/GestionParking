package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.java.vue.Accueil;
import main.java.vue.ChoixSeGarer;
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
		if (b.getText() == "Se Garer") {
			fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
		} 
		if (b.getText() == "Partir"){
			fenetrePrincipal.dispose();
		}
		if (b.getText().equals("Ajout Véhicule")) {
			fenetrePrincipal.ChangementVue(VUE.AJOUTERVEHICULE);
		}
	}}
