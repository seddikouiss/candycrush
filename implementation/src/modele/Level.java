package modele;

import javax.swing.event.EventListenerList;

import listener.Observable;
import listener.Observerateur;
import event.FactoryCandyEvent;


public abstract class Level implements Observable{
	protected int niveau;
	protected int deplacementMax;
	protected int deplacementCourant;
	protected Partie partie ;
	protected Grid grid ;
	protected EventListenerList listeners ;
	
	public Grid getGrid() {
		return grid;
	}
	protected void fireGameOver() {
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventGameOver", this, null) );
		}

	}
	protected void fireNbDeplacement() {
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventNbDeplacement", this, deplacementCourant) );
		}

	}
	protected void fireNbDeplacementMax() {
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventNbDeplacementMAX", this, deplacementMax) );
		}

	}
	public void setDeplacementCourant(int deplacementCourant) {
		
		this.deplacementCourant = deplacementCourant ;
		if(this.deplacementCourant >= deplacementMax){
			fireGameOver() ;
		}
		fireNbDeplacement();
		fireNbDeplacementMax();
	}
	@Override
	public void addListener(Observerateur l) {
		listeners.add(Observerateur.class, l);
		
	}

	@Override
	public void removeListener(Observerateur l) {
		listeners.remove(Observerateur.class, l);
		
	}


	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau ;
	}
	public int getDeplacementMax() {
		return deplacementMax;
	}
	public void setDeplacementMax(int deplacementMax) {
		this.deplacementMax = deplacementMax;
		
	}

	public int getDeplacementCourant() {
		return deplacementCourant;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

}
