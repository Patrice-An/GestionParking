package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import modele.Abonnement;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class GestionAbonnement extends JPanel {

    private FenetrePrincipal fenetrePrincipal;
    private JComboBox<Abonnement> comboAbonnement;

    public GestionAbonnement(FenetrePrincipal fenetrePrincipal) {
        this.fenetrePrincipal = fenetrePrincipal;
        setLayout(new BorderLayout(0, 0));
        
        JLabel title = new JLabel("Gestion abonnement");
        add(title, BorderLayout.NORTH);
        title.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        comboAbonnement = new JComboBox<Abonnement>();
        DefaultComboBoxModel<Abonnement> abonnementModel = new DefaultComboBoxModel<Abonnement>();
        for (Abonnement abonnement : Abonnement.values()) {
            abonnementModel.addElement(abonnement);
        }
        comboAbonnement.setModel(abonnementModel);

        add(comboAbonnement, BorderLayout.CENTER);
    }
    

    
}
