package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.App;
import vue.App.VUE;

public class ControleurAbonnement implements ActionListener{
	
	private final App fenPrincipal;

	public ControleurAbonnement(App fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Enregistrer")) {
			JOptionPane.showMessageDialog(null, "Vos abonnements ont bien été enregistrés !");
			fenPrincipal.ChangementVue(VUE.ACCUEIL);
		}	
	}
}
