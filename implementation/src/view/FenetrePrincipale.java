package view;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Partie;
import controllers.GridController;
import controllers.MenuControlleur;
import controllers.PartieControlleur;
import view.PanneauMenu;

public class FenetrePrincipale extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPan;
	PanneauLateral panLat;
	PanneauMenu panMen;
	PanneauComposite panComp;
	
	public FenetrePrincipale(PanneauMenu panMen,
							 PanneauLateral panLat,
							 PanneauComposite panComp) throws ClassNotFoundException
	{
		mainPan= new JPanel();
		BorderLayout borderLayout = new BorderLayout();
		mainPan.setLayout(borderLayout);
		

		this.panLat= panLat;
		this.panMen= panMen;
		this.panComp= panComp;
		
		mainPan.add(panLat,BorderLayout.WEST);
		mainPan.add(panMen,BorderLayout.NORTH);
		mainPan.add(panComp,BorderLayout.CENTER);
		
		setContentPane(mainPan);

		setSize(800,600);
		setTitle("Candy Crush");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) throws ClassNotFoundException
	{
		Partie p = new Partie();
		GridController gridController = new GridController(p.getLevel().getGrid()) ;
		
		PartieControlleur partieCControlleur = new PartieControlleur(p,gridController.getView()) ;
		
		MenuControlleur menuControlleur = new MenuControlleur () ;
		new FenetrePrincipale(menuControlleur.getPanMenu(), partieCControlleur.getPanneauLateral(), 
								partieCControlleur.getPanneauComposite());
		
	}
	

}
