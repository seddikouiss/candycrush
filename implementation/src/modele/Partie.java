package modele;

import javax.swing.event.EventListenerList;

import event.FactoryCandyEvent;
import listener.Observable;
import listener.Observerateur;
import modele.level.Level1;

public class Partie implements Observable{
	private int score;
	private Player player;
	private Level level;
	private EventListenerList listeners;
		
	public Partie() {
		super();
		this.player = Player.getInstance();
		listeners = new EventListenerList();
		startPartie(1,0,"toto");
	}
	@Override
	public void removeListener(Observerateur l) {
		listeners.remove(Observerateur.class, l);
		
	}
	@Override
	public void addListener(Observerateur l) {
		listeners.add(Observerateur.class, l);
		
	}
	private void nextLevel(int level){
	}
	public void startPartie(int level,int scoreInit,String nomPlayer){
		player.setNom(nomPlayer);
		setScore(scoreInit);
		if (level == 1) {
			FactoryLevel factory = new FactoryLevel();
			this.level = factory.creerLevel("Level1",this) ;
		}
		firePartie() ;
	}
	private void firePartie(){
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventNewScore", this, score));
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEvetNewLevel", this, level.getNiveau()));
		}

	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		if (level != null ){
			if (level.getNiveau() < (score / 50 + 1)){
				level.setNiveau(score / 50 + 1);
				level.setDeplacementCourant(0);
			}
		}
		firePartie() ;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
