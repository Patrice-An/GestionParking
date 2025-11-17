package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.InfoSeGarerVoirie;

public class ControleurInfoSeGarerVoirie implements ActionListener{
	
	private InfoSeGarerVoirie vue;
	
	public ControleurInfoSeGarerVoirie(InfoSeGarerVoirie vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			vue.dispose();
			new ChoixSeGarer();
		}
		
	}

}
