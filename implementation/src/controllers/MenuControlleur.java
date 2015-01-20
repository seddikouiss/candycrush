package controllers;

import modele.Zic;
import view.PanneauMenu;


public class MenuControlleur {
	PanneauMenu panMenu ;
	
	public MenuControlleur (){
		panMenu = new PanneauMenu(this) ;
		
		addListenersToModel() ;
	}
	
	private void addListenersToModel() {
		Zic.getInstance().addListener(panMenu);
	}
	
	public void playStopMusiqueNotifyed(){
		Zic.getInstance().startStop();
	}

	public PanneauMenu getPanMenu() {
		return panMenu;
	}

	public void setPanMenu(PanneauMenu panMenu) {
		this.panMenu = panMenu;
	}


}
