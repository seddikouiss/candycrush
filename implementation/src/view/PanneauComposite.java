package view;
import java.awt.CardLayout;

import javax.swing.JPanel;

import view.panneauComposite.GridView;
import view.panneauComposite.PanneauGameOver;
import view.panneauComposite.PanneauMenuPrincipal;


public class PanneauComposite extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private  CardLayout card ;
	public PanneauComposite(PanneauMenuPrincipal menu,PanneauGameOver gameOver,GridView jeux)
	{
		super();
				
		card = new CardLayout() ;
		setLayout(card);

		add(menu);
		add(jeux);
		add(gameOver);	
		
	}
	public void next (){
		card.next(this) ;
	}
}
