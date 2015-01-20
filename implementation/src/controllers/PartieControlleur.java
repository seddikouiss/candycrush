package controllers;

import modele.Partie;
import view.PanneauComposite;
import view.PanneauLateral;
import view.panneauComposite.GridView;
import view.panneauComposite.PanneauGameOver;
import view.panneauComposite.PanneauMenuPrincipal;
import view.panneauLateral.PartieView;
import view.panneauLateral.PlayerView;

public class PartieControlleur {
	
	private PanneauComposite panneauComposite ;
	private PanneauLateral panneauLateral ;
	private PlayerView playerView ;
	private PartieView partieView ;
	private PanneauMenuPrincipal panneauMenuPrincipal;
	private PanneauGameOver panneauGameOver ;
	private GridView gridView ;
	
	private Partie partie ;

	public PartieControlleur(Partie partie,GridView gridView){		
		this.partie = partie ;
		this.gridView =  gridView ;
		
		panneauMenuPrincipal = new PanneauMenuPrincipal(this);
		panneauGameOver = new PanneauGameOver(this) ;
		panneauComposite = new PanneauComposite(panneauMenuPrincipal,panneauGameOver,gridView) ;
		
		partieView = new PartieView() ;
		playerView = new PlayerView() ;
		panneauLateral = new PanneauLateral(playerView,partieView) ;
		addListenersToModel() ;
	}
	
	private void addListenersToModel() {
		partie.getPlayer().addListener(playerView);
		partie.addListener(partieView);
		partie.getLevel().addListener(panneauGameOver);
		partie.getLevel().addListener(partieView);
	}
	
	public void newPartieNotified(String nomJoueur) {
		if (nomJoueur.isEmpty()){
			panneauMenuPrincipal.nameIsEmpty() ;
		}else{
			panneauComposite.next();
			partie.startPartie(1, 0, nomJoueur);
			partieView.startTimer();
		}
	}
	public void gameOverNotyfed() {
		panneauComposite.next();
		
	}

	public PlayerView getPlayerView() {
		return playerView;
	}
	public void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}
	public PartieView getPartieView() {
		return partieView;
	}


	public void setPartieView(PartieView partieView) {
		this.partieView = partieView;
	}


	public PanneauLateral getPanneauLateral() {
		return panneauLateral;
	}

	public void setPanneauLateral(PanneauLateral panneauLateral) {
		this.panneauLateral = panneauLateral;
	}


	public PanneauComposite getPanneauComposite() {
		return panneauComposite;
	}

	public void setPanneauComposite(PanneauComposite panneauComposite) {
		this.panneauComposite = panneauComposite;
	}


	public PanneauMenuPrincipal getPanneauMenuPrincipal() {
		return panneauMenuPrincipal;
	}

	public void setPanneauMenuPrincipal(PanneauMenuPrincipal panneauMenuPrincipal) {
		this.panneauMenuPrincipal = panneauMenuPrincipal;
	}

	public PanneauGameOver getPanneauGameOver() {
		return panneauGameOver;
	}

	public void setPanneauGameOver(PanneauGameOver panneauGameOver) {
		this.panneauGameOver = panneauGameOver;
	}

	public GridView getGridView() {
		return gridView;
	}

	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}

	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}


}
