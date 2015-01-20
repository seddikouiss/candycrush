package modele.level;

import javax.swing.event.EventListenerList;

import modele.Grid;
import modele.Level;
import modele.Partie;

public class Level1 extends Level{
	
	public Level1(Partie partie) {
		super();
		this.niveau = 1;
		this.deplacementMax = 30;
		this.deplacementCourant = 0;
		this.partie = partie;
		grid = new Grid(10,10,this);
		listeners = new EventListenerList();
	}
}
