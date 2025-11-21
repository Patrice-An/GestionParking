package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.InfoSeGarerParking;
import main.java.vue.InfoSeGarerVoirie;

public class ControleurSeGarer implements ActionListener{
	
	private static ChoixSeGarer vue;

	public ControleurSeGarer(ChoixSeGarer choixSeGarer) {
		vue = choixSeGarer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getText() == "Voirie") {
			new InfoSeGarerVoirie();
		}
		if (btn.getText() == "Parking") {
			new InfoSeGarerParking();
		}
		
	}

}
