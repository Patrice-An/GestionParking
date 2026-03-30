package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.ControleurAbonnement;
import modele.Abonnement;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Dimension;

public class GestionAbonnement extends JPanel {

    private final App app;
    private final transient ControleurAbonnement controleur;

    public GestionAbonnement(App app) {
        String policeTahoma = "Tahoma";
    	setPreferredSize(new Dimension(500, 720));
        this.app = app;
        this.controleur = new ControleurAbonnement(this.app);
        setLayout(new BorderLayout(0, 0));
        
        JLabel title = new JLabel("Gestion abonnement");
        add(title, BorderLayout.NORTH);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultComboBoxModel<Abonnement> abonnementModel = new DefaultComboBoxModel<>();
        for (Abonnement abonnement : Abonnement.values()) {
            abonnementModel.addElement(abonnement);
        }
        
        JPanel panel1 = new JPanel();
        add(panel1, BorderLayout.CENTER);
        panel1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel1.add(panel);
        panel.setLayout(new MigLayout("", "[][][][][][][][][][]", "[][][][][][][][][]"));
        
        JCheckBox checkTisseo = new JCheckBox("Tisseo");
        checkTisseo.setFont(new Font(policeTahoma, Font.PLAIN, 21));
        checkTisseo.setFocusable(false);
        checkTisseo.setSize(new Dimension(10, 10));
        panel.add(checkTisseo, "cell 6 1");
        
        JCheckBox checkSNCF = new JCheckBox("SNCF");
        checkSNCF.setFont(new Font(policeTahoma, Font.PLAIN, 21));
        panel.add(checkSNCF, "cell 6 3");
        
        JCheckBox checkLio = new JCheckBox("LIO ARC");
        checkLio.setFont(new Font(policeTahoma, Font.PLAIN, 21));
        panel.add(checkLio, "cell 6 5");
        
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font(policeTahoma, Font.PLAIN, 21));
        panel.add(btnEnregistrer, "cell 9 8");
        btnEnregistrer.addActionListener(controleur);
        
        JLabel lblNewLabel = new JLabel("Sélectionnez vos abonnements");
        lblNewLabel.setFont(new Font(policeTahoma, Font.PLAIN, 21));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(lblNewLabel, BorderLayout.NORTH);
    }
    

    
}
