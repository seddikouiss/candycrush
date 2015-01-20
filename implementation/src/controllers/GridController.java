package controllers;

import modele.Grid;
import view.panneauComposite.GridView;

public class GridController {

	private GridView view ;
	private Grid model ;
	
	private void addListenersToModel() {
		model.addListener(view);
	}
	
	
	public GridController (Grid g) throws ClassNotFoundException{
		model = g ;
		view = new GridView(model.getRows(), model.getColumns(),this) ;
		
		addListenersToModel() ;
		model.updateGrid() ;
	}
	public GridView getView() {
		return view;
	}
	public void setView(GridView view) {
		this.view = view;
	}
	public Grid getModel() {
		return model;
	}
	public void setModel(Grid model) {
		this.model = model;
	}
	public void swapNotify(int x1, int y1, int x2, int y2) {
		model.updateGrid(x1, y1, x2, y2);
	}


}
