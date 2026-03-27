package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.Set;
import javax.swing.*;

import controleur.ControleurGestionVehicule;
import modele.Vehicule;

import javax.swing.border.LineBorder;
import java.awt.FlowLayout;


public class GestionVehicule extends JPanel {

	private static final long serialVersionUID = 1L;
	private final App fenetre;
	private JPanel listevehicules;
	public GestionVehicule(App fenetre) {
		setSize(new Dimension(500, 720));
		setMaximumSize(new Dimension(32767, 300));
		this.fenetre = fenetre;
		initialize();
	}


	/**
	 * Create the panel.
	 */
	private void initialize() {
		
			JTextField searchField;


		ControleurGestionVehicule controleur = new ControleurGestionVehicule(fenetre);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("Gestion des véhicules");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.NORTH);
		
		JPanel paneSearch = new JPanel();
		panel.add(paneSearch, BorderLayout.WEST);
		paneSearch.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		paneSearch.add(searchField, BorderLayout.WEST);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		paneSearch.add(btnSearch, BorderLayout.CENTER);
		
		JPanel marginTop = new JPanel();
		paneSearch.add(marginTop, BorderLayout.NORTH);
		
		JPanel panel7 = new JPanel();
		paneSearch.add(panel7, BorderLayout.EAST);
		panel7.setLayout(new BorderLayout(0, 0));
		
		JButton btnAjout = new JButton("Ajouter un véhicule");
		btnAjout.addActionListener(controleur);
		panel7.add(btnAjout, BorderLayout.EAST);
		
		JPanel marginBtwinBtn = new JPanel();
		marginBtwinBtn.setPreferredSize(new Dimension(100, 10));
		panel7.add(marginBtwinBtn, BorderLayout.CENTER);
		
		JPanel marginTopCenter = new JPanel();
		panel.add(marginTopCenter, BorderLayout.SOUTH);
		
		JPanel marginLeft = new JPanel();
		add(marginLeft, BorderLayout.WEST);
		
		JPanel marginRight = new JPanel();
		add(marginRight, BorderLayout.EAST);
		
		JPanel marginBottom = new JPanel();
		add(marginBottom, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		listevehicules = new JPanel();
		scrollPane.setViewportView(listevehicules);
		listevehicules.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buildBox();
		
	}
	
	public void refresh() {
		listevehicules.removeAll();
		buildBox();
		revalidate();
		repaint();
	}
	
	private void buildBox() { 
		Box verticalBox;
		Set<Vehicule> listeVehicule = fenetre.getUtilisateur().getVehicules();
		ControleurGestionVehicule controleur = new ControleurGestionVehicule(fenetre);
		for (Vehicule vehiculeIt:listeVehicule) {
			verticalBox = Box.createVerticalBox();
			verticalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
			listevehicules.add(verticalBox);
				
			JTextField labelDesignation = new JTextField(vehiculeIt.getDesignation());
			labelDesignation.setHorizontalAlignment(SwingConstants.CENTER);
			labelDesignation.setEnabled(false);
			verticalBox.add(labelDesignation);
				
			JTextField labelType = new JTextField(vehiculeIt.getType().toString());
			labelType.setEnabled(false);
			labelType.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(labelType);
				
			JTextField labelImmat = new JTextField(vehiculeIt.getImmatriculation());
			labelImmat.setEnabled(false);
			labelImmat.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(labelImmat);
				
			JTextField labelHauteur = new JTextField(Float.toString(vehiculeIt.getHauteur()) + " m");
			labelHauteur.setEnabled(false);
			labelHauteur.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(labelHauteur);
				
			JButton btnModifier = new JButton("Modifier");
			verticalBox.add(btnModifier);
			btnModifier.putClientProperty("vehicule", vehiculeIt);
			btnModifier.putClientProperty("désignation", labelDesignation);
			btnModifier.putClientProperty("type", labelType);
			btnModifier.putClientProperty("hauteur", labelHauteur);
			btnModifier.addActionListener(controleur);
			
			JButton btnSupprimer = new JButton("Supprimer");
			verticalBox.add(btnSupprimer);
			btnSupprimer.putClientProperty("vehicule", vehiculeIt);
			btnSupprimer.addActionListener(controleur);
			listevehicules.add(verticalBox);
		}
	}

}
