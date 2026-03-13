package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reservations extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Reservations() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel LabelTitre = new JLabel("Réserver");
		LabelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		add(LabelTitre, BorderLayout.NORTH);
		
		JPanel marginLeft = new JPanel();
		add(marginLeft, BorderLayout.WEST);
		
		JPanel marginRight = new JPanel();
		add(marginRight, BorderLayout.EAST);
		
		JPanel marginBottom = new JPanel();
		add(marginBottom, BorderLayout.SOUTH);
		
		JPanel paneCentre = new JPanel();
		add(paneCentre, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Place avec borne de recharge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Place moto");
		btnNewButton_1.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Place normale");
		btnNewButton_2.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Place pour personne en situation de handicap");
		btnNewButton_3.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton_3);

	}

}
