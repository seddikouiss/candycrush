package modele;

import java.util.Random;

import javax.swing.event.EventListenerList;

import listener.Observable;
import listener.Observerateur;
import event.FactoryCandyEvent;

public class Grid implements Observable{
	
	private int rows ,columns ;
	IObject grid[][] ;
    boolean marked[][];
	private EventListenerList listeners;
	private Level level ;


	public Grid(int rows,int columns,Level level){
		this.rows = rows;
		this.columns = columns ;
		this.level = level ;
		grid = new IObject[this.rows][this.columns];
		marked = new boolean[this.rows][this.columns];
		listeners = new EventListenerList();
	}
	private void fireNewDisposition(){
		Observerateur[] listenerList = (Observerateur[])listeners.getListeners(Observerateur.class);
		for(Observerateur listener : listenerList){
			listener.updates(FactoryCandyEvent.getInstance().creerCandyEvent("CandyEventNewDispositionGrid", this, grid) );
		}
	}
	public void updateGrid() {
		try {
			while(fill()) removeAlignments();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		fireNewDisposition();
	}
	public void updateGrid(int x1, int y1, int x2, int y2) {
		level.setDeplacementCourant(level.getDeplacementCourant() + 1);
		if(isValidSwap(x1, y1, x2, y2))
		{
			swap(y1, x1, y2, x2);
			try {
				while(removeAlignments())fill();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			fireNewDisposition();
		}
		
	}
    // échanger le contenu de deux cases
    private void swap(int x1, int y1, int x2, int y2) {
        IObject tmp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = tmp;
    }
    // remplir les cases vides 
    public void remplireCaseVide(){
        Random rand = new Random();
        FactoryIObject factory = new FactoryIObject();
    	// remplire les case vide(null)
    	for (int j = 0 ; j < columns ;++j){
    		for( int i = 0 ; (i < rows) &&(grid[i][j] == null);++i ){
    	    	int random = 1 + rand.nextInt(factory.getSizeMap() - 1) ;
    	    	try {
    				grid[i][j] = factory.creerIObject(factory.getNameIObjects()[random]);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    // Pour chaque colonne on remonte les null(case supprimer) vers le haut
    public boolean remonterCaseVide(){
    	boolean modified = false ;
    	for (int j = 0 ; j < columns ;++j){
    		for (int taille  = rows; taille-- > 0 ;){
    			for (int i = 0 ; i <= taille ; ++i){
    				if(i >= 1 && grid[i][j] == null) {
    					swap (i,j, i - 1 , j) ;
    					modified = true ;
    				}
    			}
    		}
    	}
    	return modified ;
    }
    public boolean fill() throws ClassNotFoundException {
    	boolean modified = remonterCaseVide();
    	remplireCaseVide();
    	return modified ;
    }
    // supprimer les alignements
    public boolean removeAlignments() {
        // passe 1 : marquer tous les alignements 
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(grid[i][j] != null && horizontalAligned(i, j)) {
                    marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
                    
                }
                if(grid[i][j] != null && verticalAligned(i, j)) {
                    marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
                }
            }
        }
        // passe 2 : supprimer les cases marquées // Et ajout du nombre de point
        boolean modified = false;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(marked[i][j]) {
                	level.getPartie().setScore(level.getPartie().getScore() + grid[i][j].getNbPoint() );
                    grid[i][j] = null;
                    marked[i][j] = false;
                    modified = true;
                }
            }
        }
        return modified;
    }
    // détermine si l'échange entre deux cases est valide
    //on donne des x y en repere mathematique, et on accede à la grille en terme ligne colonne(donc x c'est la colonne et y la ligne)
    private boolean isValidSwap(int x1, int y1, int x2, int y2) {
    	
        // il faut que les cases soient dans la grille
        if(x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1) {
        	return false;
        }
        // que les cases soient à côté l'une de l'autre
        if(Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1){
        	return false;
        }
        // et que les couleurs soient différentes
        if(grid[y1][x1].getClass().equals(grid[y2][x2].getClass())){
        	return false;
        }
        swap(y1, x1, y2, x2);
        // et on vérifie que ça créé un nouvel alignement
        boolean newAlignment = false;
        for(int i = 0; i < 3; i++) {
            newAlignment |= horizontalAligned(y1 , x1 - i);
            newAlignment |= horizontalAligned(y2 , x2 - i);
            newAlignment |= verticalAligned(y1 - i, x1 );
            newAlignment |= verticalAligned(y2 - i, x2 );
        }
        // puis on annule l'échange
        swap(y1, x1, y2, x2);
        return newAlignment ;
    }
    // est-ce qu'on a trois cases de la même couleur vers le bas depuis (i, j) ?
    boolean verticalAligned(int i, int j) {
        if(i < 0 || j < 0 || i >= rows - 2 || j >= columns||
                grid[i][j] == null ||grid[i + 1][j ] == null|| grid[i + 2][j ] == null) {
        	return false;
        }
        if(grid[i][j].getClass().equals(grid[i + 1][j].getClass()) && 
           grid[i][j].getClass().equals(grid[i + 2][j].getClass())) return true;
        return false;
    }
   
    // est-ce qu'on a trois cases de la même couleur vers le droite depuis (i, j) ?
    boolean horizontalAligned(int i, int j) {
        if(i < 0 || j < 0 || j >= columns - 2 || i >= rows||
           grid[i][j] == null ||grid[i][j + 1 ] == null|| grid[i][j + 2 ] == null) {
        	return false;
        }
        if(grid[i][j].getClass().equals(grid[i][j + 1].getClass()) &&
           grid[i][j].getClass().equals(grid[i][j + 2].getClass())) return true;
        return false;
    }
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public IObject[][] getGrid() {
		return grid;
	}

	public void setGrid(IObject[][] grid) {
		this.grid = grid;
	}
	@Override
	public void removeListener(Observerateur l) {
		listeners.remove(Observerateur.class, l);
		
	}
	@Override
	public void addListener(Observerateur l) {
		listeners.add(Observerateur.class, l);
		
	}
}
