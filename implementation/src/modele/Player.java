package modele;

import javax.swing.event.EventListenerList;

import listener.Observable;
import listener.Observerateur;
import event.FactoryCandyEvent;

public class Player implements Observable{
	private String nom;
	private static volatile Player instance = null;
	
	private EventListenerList listeners;

	private Player() {
		super();
		listeners = new EventListenerList() ;
	}
	public final static Player getInstance() {
		
		if (Player.instance == null) {
			synchronized(Player.class) {
				if (Player.instance == null) {
					Player.instance = new Player();
				}
			}
		}
		return Player.instance;
	}
	
	@Override
	public void addListener(Observerateur l) {
		listeners.add(Observerateur.class, l);
		
	}

	@Override
	public void removeListener(Observerateur l) {
		listeners.remove(Observerateur.class, l);
		
	}
	private void fireNewNom(String nom){
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventChangeNom", this, nom));
		}
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom.length() > 6 ){
			nom = nom.substring(0, 6);
		}
		this.nom = nom;
		fireNewNom(nom);
	}
}
