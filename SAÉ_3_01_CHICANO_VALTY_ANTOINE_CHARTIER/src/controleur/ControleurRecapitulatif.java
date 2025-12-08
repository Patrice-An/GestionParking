package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.FenRecapitulatif;

public class ControleurRecapitulatif implements ActionListener {

	
	FenRecapitulatif vue;
	
	
	public ControleurRecapitulatif(FenRecapitulatif vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("Confirmer")) {
			vue.dispose();
		}
	}
	
}
