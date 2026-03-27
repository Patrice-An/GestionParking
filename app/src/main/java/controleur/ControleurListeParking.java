package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ListeParking;

public class ControleurListeParking implements ActionListener{
	
	private final  ListeParking vue;
	public ControleurListeParking(ListeParking vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Rechercher")) {
			vue.repaint();
		}
	}

}
