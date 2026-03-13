package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.GestionAbonnement;

public class ControleurAbonnement implements ActionListener{
	
	private FenetrePrincipal fenPrincipal;
	private GestionAbonnement vue;
	public ControleurAbonnement(FenetrePrincipal fenPrincipal, GestionAbonnement vue) {
		this.fenPrincipal = fenPrincipal;
		this.vue = vue;
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
