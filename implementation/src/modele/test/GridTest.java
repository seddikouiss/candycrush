package modele.test;

import static org.junit.Assert.*;
import modele.FactoryLevel;
import modele.Grid;
import modele.IObject;
import modele.Partie;

import org.junit.Before;
import org.junit.Test;

public class GridTest{
	
	Grid grid ;
	@Before
	public void init() {
		grid = new Grid(8,7,new FactoryLevel().creerLevel("Level1", new Partie())) ;
	}
	
	@Test
	public void testGrid() throws ClassNotFoundException{	
		grid.updateGrid();
		
		for(int i = 0 ; i < grid.getRows();++i){
			for(int j = 0 ; j < grid.getColumns(); ++j){
				assertNotNull(grid.getGrid()[i][j]);
				assertTrue(grid.getGrid()[i][j] instanceof IObject) ;
			}
		}/*
		System.out.println(grid.getRows());
		for ( int i = 0; i< grid.getRows() ;++i)
		{
			for ( int j = 0; j< grid.getColumns() ;++j){
				if (grid.getGrid()[i][j] == null) System.out.print("null   ");
				else System.out.print(grid.getGrid()[i][j].getClass().getSimpleName() + "   ");
			}
			System.out.println();
		}
		*/
	}
	@Test
	public void test(){
		try {
			grid.fill();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(grid.getRows());
		for ( int i = 0; i< grid.getRows() ;++i)
		{
			for ( int j = 0; j< grid.getColumns() ;++j){
				if (grid.getGrid()[i][j] == null) System.out.print("null---   ");
				else System.out.print(grid.getGrid()[i][j].getClass().getSimpleName() + "   ");
			}
			System.out.println();
		}
		System.out.println("****************************************************************************");
		grid.removeAlignments();
		for ( int i = 0; i< grid.getRows() ;++i)
		{
			for ( int j = 0; j< grid.getColumns() ;++j){
				if (grid.getGrid()[i][j] == null) System.out.print("null---   ");
				else System.out.print(grid.getGrid()[i][j].getClass().getSimpleName() + "   ");
			}
			System.out.println();
		}
		System.out.println("****************************************************************************");
		grid.remonterCaseVide();
		for ( int i = 0; i< grid.getRows() ;++i)
		{
			for ( int j = 0; j< grid.getColumns() ;++j){
				if (grid.getGrid()[i][j] == null) System.out.print("null---   ");
				else System.out.print(grid.getGrid()[i][j].getClass().getSimpleName() + "   ");
			}
			System.out.println();
		}
		grid.remplireCaseVide();
		System.out.println("****************************************************************************");
		grid.remonterCaseVide();
		for ( int i = 0; i< grid.getRows() ;++i)
		{
			for ( int j = 0; j< grid.getColumns() ;++j){
				if (grid.getGrid()[i][j] == null) System.out.print("null---   ");
				else System.out.print(grid.getGrid()[i][j].getClass().getSimpleName() + "   ");
			}
			System.out.println();
		}

	}

}
