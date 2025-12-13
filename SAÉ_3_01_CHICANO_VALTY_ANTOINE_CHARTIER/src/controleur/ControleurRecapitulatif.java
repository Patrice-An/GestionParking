package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.FenRecapitulatif;
import main.java.vue.FenetrePrincipal;
import main.java.vue.FenetrePrincipal.VUE;

public class ControleurRecapitulatif implements ActionListener {

	
	FenRecapitulatif vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurRecapitulatif(FenRecapitulatif vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("Confirmer")) {
			
			fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
		}
	}
	
}
