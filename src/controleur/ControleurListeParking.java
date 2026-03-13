package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.ListeParking;

public class ControleurListeParking implements ActionListener{
	
	private ListeParking vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurListeParking(ListeParking vue, FenetrePrincipal fenPrinc) {
		this.fenetrePrincipal = fenPrinc;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
		case "Rechercher":
			vue.repaint();
			break;
		}
	}

}
