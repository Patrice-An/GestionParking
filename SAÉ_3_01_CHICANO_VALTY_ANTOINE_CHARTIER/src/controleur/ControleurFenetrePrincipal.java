package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;

public class ControleurFenetrePrincipal implements ActionListener{

	private FenetrePrincipal vue;
	
	public ControleurFenetrePrincipal(FenetrePrincipal vue) {
		this.vue = vue;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("← Précédent")) {
			vue.ChangementVue(VUE.ACCUEIL);
		}
	}

}
