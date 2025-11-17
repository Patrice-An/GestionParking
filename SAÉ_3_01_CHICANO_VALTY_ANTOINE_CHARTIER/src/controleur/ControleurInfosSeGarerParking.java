package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.InfoSeGarerParking;

public class ControleurInfosSeGarerParking implements ActionListener{
	
	private InfoSeGarerParking vue;
	
	public ControleurInfosSeGarerParking (InfoSeGarerParking vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			vue.dispose();

			
		}
		
	}

}
