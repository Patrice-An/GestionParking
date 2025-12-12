package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.InfoSeGarerParking;
import main.java.vue.InfoSeGarerVoirie;

public class ControleurSeGarer implements ActionListener{
	
	private ChoixSeGarer vue;
	
	public ControleurSeGarer(ChoixSeGarer vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Parking") {
			
		} else if (b.getText() == "Voirie") {
		
		}
	}

}
