package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.App.VUE;

public class ControleurSeGarer implements ActionListener{
	
	private final App app;
	public ControleurSeGarer(App app) {
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Parking" -> app.changementVue(VUE.INFOSEGARERPARKING);
			case "Voirie" -> app.changementVue(VUE.INFOSEGARERVOIRIE);
			default -> {
				//aucun autre cas
			}
		}
	}

}
