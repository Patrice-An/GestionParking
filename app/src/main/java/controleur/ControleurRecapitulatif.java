package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenRecapitulatif;
import vue.App;
import vue.App.VUE;

public class ControleurRecapitulatif implements ActionListener {

	
	FenRecapitulatif vue;
	private App App;
	
	public ControleurRecapitulatif(FenRecapitulatif vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("Confirmer")) {
			
			App.ChangementVue(VUE.ACCUEIL);
		}
	}
	
}
