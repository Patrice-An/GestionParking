package main.java.vue;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurSeConnecter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;

public class SeConnecter extends JFrame {

	private JPanel contentPane;
	private JTextField champNomUtilisateur;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JSplitPane splitPane;
	private JButton btnInscrire;
	
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeConnecter frame = new SeConnecter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SeConnecter() {
		ControleurSeConnecter controler = new ControleurSeConnecter(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);

		lblNewLabel = new JLabel("SE CONNECTER");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);



		JLabel usernameLabel = new JLabel("Nom d'utilisateur :");

		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		contentPane.add(usernameLabel, gbc_usernameLabel);

		champNomUtilisateur = new JTextField();

		GridBagConstraints gbc_champNomUtilisateur = new GridBagConstraints();
		gbc_champNomUtilisateur.fill = GridBagConstraints.HORIZONTAL;
		gbc_champNomUtilisateur.insets = new Insets(0, 0, 5, 0);
		gbc_champNomUtilisateur.gridx = 2;
		gbc_champNomUtilisateur.gridy = 1;
		contentPane.add(champNomUtilisateur, gbc_champNomUtilisateur);

		JLabel passwordLabel = new JLabel("Mot de passe :");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 2;
		contentPane.add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		contentPane.add(passwordField, gbc_passwordField);

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridx = 2;
		gbc_splitPane.gridy = 3;
		splitPane.setDividerSize(0);
		contentPane.add(splitPane, gbc_splitPane);

		JButton boutonConnexion = new JButton("Se connecter");
		splitPane.setRightComponent(boutonConnexion);
		boutonConnexion.addActionListener(controler);

		btnInscrire = new JButton("S'inscrire");
		splitPane.setLeftComponent(btnInscrire);
		btnInscrire.addActionListener(controler);

		this.setLocationRelativeTo(null);
	}
	
	public String getEmail() {
		return champNomUtilisateur.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}
}