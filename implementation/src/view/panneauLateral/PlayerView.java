package view.panneauLateral;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import event.CandyEvent;
import event.candyEvent.CandyEventChangeNom;
import listener.Observerateur;

public class PlayerView extends JPanel implements Observerateur{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel nomPlayer= new JLabel("player : toto");
	
	public PlayerView()
	{
		add(nomPlayer);
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.lightGray));

	}
	public void changeNomPlayer(String nomJeur) {
		nomPlayer.setText("player : " +  nomJeur);
	}
	@Override
	public void updates(CandyEvent e) {
		if (e instanceof CandyEventChangeNom){
			changeNomPlayer ((String) e.getValue()) ;
		}
	}


}
