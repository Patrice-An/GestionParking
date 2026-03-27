package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.App.VUE;

public class ControleurRecapitulatif implements ActionListener {

	private App app;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("Confirmer")) {
			
			app.changementVue(VUE.ACCUEIL);
		}
	}
	
}
