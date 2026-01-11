package vue;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurSeConnecter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

public class SeConnecter extends JPanel {
	
	private JTextField champNomUtilisateur;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JButton btnInscrire;
	private FenetrePrincipal fenetrePrincipal;
		
	public SeConnecter(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		FlatDarculaLaf.setup();
		initialize();
	}

	private void initialize() {
		ControleurSeConnecter controler = new ControleurSeConnecter(this, fenetrePrincipal);
		setLayout(new MigLayout("", "[84px][331px][]", "[13px][19px][19px][23px][][][][][][][]"));

		lblNewLabel = new JLabel("SE CONNECTER");
		this.add(lblNewLabel, "cell 1 0,alignx center,aligny center");



		JLabel usernameLabel = new JLabel("Nom d'utilisateur :");
		this.add(usernameLabel, "cell 0 1,alignx center,aligny center");

		champNomUtilisateur = new JTextField();
		this.add(champNomUtilisateur, "cell 1 1 2 1,growx,aligny center");

		JLabel passwordLabel = new JLabel("Mot de passe :");
		this.add(passwordLabel, "cell 0 2,alignx center,aligny center");

		passwordField = new JPasswordField();
		this.add(passwordField, "cell 1 2 2 1,growx,aligny center");
						
								btnInscrire = new JButton("S'inscrire");
								btnInscrire.setPreferredSize(new Dimension(200, 50));
								add(btnInscrire, "flowx,cell 0 10,alignx left");
								btnInscrire.addActionListener(controler);
				
						JButton boutonConnexion = new JButton("Se connecter");
						boutonConnexion.setPreferredSize(new Dimension(200, 50));
						add(boutonConnexion, "cell 2 10,alignx right");
				boutonConnexion.addActionListener(controler);

	}
	
	public String getEmail() {
		return champNomUtilisateur.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}
}