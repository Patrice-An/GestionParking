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
	private FenetrePrincipal fenetre;
	private JTextField searchField;
	private JPanel listevehicules;
	private JPanel Listevéhicules;
	private Box verticalBox;
	public GestionVehicule(FenetrePrincipal fenetre) {
		setSize(new Dimension(500, 720));
		setMaximumSize(new Dimension(32767, 300));
		this.fenetre = fenetre;
		initialize();
	}


	/**
	 * Create the panel.
	 */
	public void initialize() {
		
		ControleurGestionVehicule controleur = new ControleurGestionVehicule(this, fenetre);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel Title = new JLabel("Gestion des véhicules");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Title, BorderLayout.NORTH);
		
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
		
		JPanel panel_7 = new JPanel();
		paneSearch.add(panel_7, BorderLayout.EAST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnAjout = new JButton("Ajouter un véhicule");
		btnAjout.addActionListener(controleur);
		panel_7.add(btnAjout, BorderLayout.EAST);
		
		JPanel marginBtwinBtn = new JPanel();
		marginBtwinBtn.setPreferredSize(new Dimension(100, 10));
		panel_7.add(marginBtwinBtn, BorderLayout.CENTER);
		
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
		BuildBox();
		
	}
	
	
	public void refresh() {
		listevehicules.removeAll();
		BuildBox();
		revalidate();
		repaint();
	}
	
	private void BuildBox() { 
		Set<Vehicule> listeVehicule = fenetre.getUtilisateur().getVehicules();
		ControleurGestionVehicule controleur = new ControleurGestionVehicule(this, fenetre);
		for (Vehicule vehiculeIt:listeVehicule) {
			verticalBox = Box.createVerticalBox();
			verticalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
			listevehicules.add(verticalBox);
				
			JTextField LabelDésignation = new JTextField(vehiculeIt.getDésignation());
			LabelDésignation.setHorizontalAlignment(SwingConstants.CENTER);
			LabelDésignation.setEnabled(false);
			verticalBox.add(LabelDésignation);
				
			JTextField LabelType = new JTextField(vehiculeIt.getType().toString());
			LabelType.setEnabled(false);
			LabelType.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(LabelType);
				
			JTextField LabelImmat = new JTextField(vehiculeIt.getImmatriculation());
			LabelImmat.setEnabled(false);
			LabelImmat.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(LabelImmat);
				
			JTextField LabelHauteur = new JTextField(Float.toString(vehiculeIt.getHauteur()) + " m");
			LabelHauteur.setEnabled(false);
			LabelHauteur.setHorizontalAlignment(SwingConstants.CENTER);
			verticalBox.add(LabelHauteur);
				
			JButton btnModifier = new JButton("Modifier");
			verticalBox.add(btnModifier);
			btnModifier.putClientProperty("vehicule", vehiculeIt);
			btnModifier.putClientProperty("désignation", LabelDésignation);
			btnModifier.putClientProperty("type", LabelType);
			btnModifier.putClientProperty("hauteur", LabelHauteur);
			btnModifier.addActionListener(controleur);
			
			JButton btnSupprimer = new JButton("Supprimer");
			verticalBox.add(btnSupprimer);
			btnSupprimer.putClientProperty("vehicule", vehiculeIt);
			btnSupprimer.addActionListener(controleur);
			Listevéhicules.add(verticalBox);
		}
	}

}
