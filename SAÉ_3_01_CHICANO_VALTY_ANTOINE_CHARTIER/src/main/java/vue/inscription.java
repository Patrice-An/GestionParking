package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class inscription extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel title;
	private JLabel nomLabel;
	private JTextField nomField;
	private JLabel prenomLabel;
	private JTextField prenomField;
	private JLabel mailLabel;
	private JTextField mailField;
	private JLabel adresseLabel;
	private JTextField adresseField;
	private JLabel telLabel;
	private JTextField telField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inscription frame = new inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public inscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Inscription");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("Inscription");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title, BorderLayout.NORTH);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		
		nomLabel = new JLabel("Nom :");
		panel.add(nomLabel);
		
		nomField = new JTextField();
		panel.add(nomField);
		nomField.setColumns(10);
		
		prenomLabel = new JLabel("Prenom :");
		panel.add(prenomLabel);
		
		prenomField = new JTextField();
		prenomField.setColumns(10);
		panel.add(prenomField);
		
		mailLabel = new JLabel("Mail :");
		panel.add(mailLabel);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		panel.add(mailField);
		
		adresseLabel = new JLabel("Adresse :");
		panel.add(adresseLabel);
		
		adresseField = new JTextField();
		adresseField.setColumns(10);
		panel.add(adresseField);
		
		telLabel = new JLabel("Numéro de téléphone :");
		panel.add(telLabel);
		
		telField = new JTextField();
		telField.setColumns(10);
		panel.add(telField);
	}

}
