package view;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.MenuControlleur;
import event.CandyEvent;
import event.candyEvent.CandyEventPlayStopZic;
import listener.Observerateur;


public class PanneauMenu  extends JPanel implements ActionListener, Observerateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton playStop= new JButton(new ImageIcon("images/start.png"));
	
	MenuControlleur controlleur ;
	
	public PanneauMenu(MenuControlleur controlleur)
	{
		this.controlleur = controlleur ;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 54;
		gbc_lblNewLabel_1.gridy = 0;
		add(playStop, gbc_lblNewLabel_1);
		
		playStop.addActionListener(this);
		
		setBackground(Color.lightGray);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == playStop){
			controlleur.playStopMusiqueNotifyed();
		}
		
	}

	@Override
	public void updates(CandyEvent e) {
		if (e instanceof CandyEventPlayStopZic){
			if ((Boolean) e.getValue() == true){
				playStop.setIcon(new ImageIcon("images/stop.png"));
			}else{
				playStop.setIcon(new ImageIcon("images/start.png"));
			}
		}
	}

}
