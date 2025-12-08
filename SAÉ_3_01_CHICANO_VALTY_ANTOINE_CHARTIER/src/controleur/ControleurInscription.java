package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Inscription;
import main.java.vue.PopUpInscriptionValide;
import main.java.vue.SeConnecter;

public class ControleurInscription implements ActionListener{
	
	public ControleurInscription(Inscription vue) {
		this.vue = vue;
	}

	Inscription vue;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Valider")) {
			System.out.println(vue.getMailField().getText()+""+ vue.getPrenomField().getText()+ " "+ vue.getNomField().getText() +" "+ vue.getTelField().getText());
			if (!vue.getMailField().getText().isEmpty() && !vue.getAdresseField().getText().isEmpty()
					&& !vue.getNomField().getText().isEmpty() && !vue.getPrenomField().getText().isEmpty() && !vue.getTelField().getText().isEmpty()) {
				new PopUpInscriptionValide();
				
			}
			
		} else if (b.getText().equals("Connectez-vous")) {
			vue.dispose();
			System.out.println("se connecter");
			SeConnecter.main(null);
		}
	}

}
