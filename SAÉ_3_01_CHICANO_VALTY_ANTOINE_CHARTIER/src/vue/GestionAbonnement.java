package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import modele.Abonnement;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;

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
        DefaultComboBoxModel<Abonnement> abonnementModel = new DefaultComboBoxModel<Abonnement>();
        for (Abonnement abonnement : Abonnement.values()) {
            abonnementModel.addElement(abonnement);
        }
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout("", "[52px][][][][][][]", "[27px][][][][]"));
        
        comboAbonnement = new JComboBox<Abonnement>();
        panel.add(comboAbonnement, "cell 1 4 6 1,alignx left,aligny top");
        comboAbonnement.setModel(abonnementModel);
    }
    

    
}
