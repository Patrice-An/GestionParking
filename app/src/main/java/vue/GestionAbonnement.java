package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.ControleurAbonnement;
import modele.Abonnement;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Dimension;

public class GestionAbonnement extends JPanel {

    private App App;
    private ControleurAbonnement controleur;

    public GestionAbonnement(App App) {
    	setPreferredSize(new Dimension(500, 720));
        this.App = App;
        this.controleur = new ControleurAbonnement(this.App, this);
        setLayout(new BorderLayout(0, 0));
        
        JLabel title = new JLabel("Gestion abonnement");
        add(title, BorderLayout.NORTH);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultComboBoxModel<Abonnement> abonnementModel = new DefaultComboBoxModel<Abonnement>();
        for (Abonnement abonnement : Abonnement.values()) {
            abonnementModel.addElement(abonnement);
        }
        
        JPanel panel_1 = new JPanel();
        add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_1.add(panel);
        panel.setLayout(new MigLayout("", "[][][][][][][][][][]", "[][][][][][][][][]"));
        
        JCheckBox checkTisseo = new JCheckBox("Tisseo");
        checkTisseo.setFont(new Font("Tahoma", Font.PLAIN, 21));
        checkTisseo.setFocusable(false);
        checkTisseo.setSize(new Dimension(10, 10));
        panel.add(checkTisseo, "cell 6 1");
        
        JCheckBox checkSNCF = new JCheckBox("SNCF");
        checkSNCF.setFont(new Font("Tahoma", Font.PLAIN, 21));
        panel.add(checkSNCF, "cell 6 3");
        
        JCheckBox checkLio = new JCheckBox("LIO ARC");
        checkLio.setFont(new Font("Tahoma", Font.PLAIN, 21));
        panel.add(checkLio, "cell 6 5");
        
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 21));
        panel.add(btnEnregistrer, "cell 9 8");
        btnEnregistrer.addActionListener(controleur);
        
        JLabel lblNewLabel = new JLabel("Sélectionnez vos abonnements");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblNewLabel, BorderLayout.NORTH);
    }
    

    
}
