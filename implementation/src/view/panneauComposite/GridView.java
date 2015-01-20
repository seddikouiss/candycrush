package view.panneauComposite;


import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JPanel;

import listener.Observerateur;

import controllers.GridController;
import event.CandyEvent;
import view.IObjectView;


public class GridView extends JPanel implements Observerateur,MouseListener,MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	private int nbCol,nbLine ;
	private GridController controller ;
	
    private int selectedX = -1, selectedY = -1; 
    private int swappedX = -1, swappedY = -1;

    private IObjectView bonbonSelected1,bonbonSelected2 ;
    
	public GridView(int nbLine,int nbCol,GridController controller){
		this.nbCol = nbCol;
		this.nbLine = nbLine;
		GridLayout grid = new GridLayout(this.nbLine,this.nbCol);
		this.controller = controller ;
		this.setLayout(grid);
		
		addMouseListener(this);
        addMouseMotionListener(this);
		
		fill();
		

	}
	private void fill(){		
		removeAll();
		for (int i = 0 ; i < nbLine; ++i){
			for (int j = 0 ; j < nbCol ;++j){
				if (controller.getModel().getGrid()[i][j] != null)
					add(new IObjectView(controller.getModel().getGrid()[i][j].getImage())) ;
				else add (new JPanel()) ;//debug
			}
		}
		repaint();
		revalidate();
	}

	public void swapObject(int x1,int y1 , int x2 ,int y2){
		controller.swapNotify(x1,y1,x2,y2);
	}
	
	@Override
	public void updates(CandyEvent e) {
		fill() ;
	}
	
	 
    public void mousePressed(MouseEvent e) { 
        // on appuie sur le bouton de la souris : récupérer les coordonnées de la première case
		int x = (e.getX() -2 )/ (this.getWidth()/nbCol) ;
		int y = (e.getY() -2 )/ (this.getHeight()/nbLine); ;
        selectedX = (x < nbCol) ? x : -1;
        selectedY = (y < nbLine) ? y : -1;
        
        if (getComponentAt(e.getX(),e.getY()) instanceof IObjectView) {
        	bonbonSelected1 = (IObjectView) getComponentAt(e.getX(),e.getY()) ;
     
        	bonbonSelected1.select();
        }
    }
    
    public void mouseMoved(MouseEvent e) { 
        // on bouge la souris : récupérer les coordonnées de la deuxième case
        if(selectedX != -1 && selectedY != -1) {
    		int x = (e.getX() -2 )/ (this.getWidth()/nbCol) ;
    		int y = (e.getY() -2 )/ (this.getHeight()/nbLine);
            swappedX = (x < nbCol) ? x : -1;
            swappedY = (y < nbLine) ? y : -1;

    		if(bonbonSelected2 !=null && bonbonSelected2 != bonbonSelected1) bonbonSelected2.unSelect();
            if (getComponentAt(e.getX(),e.getY()) instanceof IObjectView ) {
            	bonbonSelected2 = (IObjectView) getComponentAt(e.getX(),e.getY()) ;
            	bonbonSelected2.select();
            }
        }
    }
    public void mouseReleased(MouseEvent e) {
        // lorsque l'on relâche la souris il faut faire l'échange et cacher les cases
        if (bonbonSelected1 !=null) {
        	bonbonSelected1.unSelect();
        	bonbonSelected1 = null ;
        }
        if(bonbonSelected2 != null){
        	bonbonSelected2.unSelect();
        	bonbonSelected2 = null ;
        }

    	if(selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
        	swapObject(selectedX, selectedY, swappedX, swappedY);
        }
        
        selectedX = selectedY = swappedX = swappedY = -1;

    }
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e) ;
		
	}
	
}