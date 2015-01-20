package view.panneauComposite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controllers.PartieControlleur;
import event.CandyEvent;
import event.candyEvent.CandyEventGameOver;
import listener.Observerateur;

public class PanneauGameOver  extends JPanel implements Observerateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private PartieControlleur controlleur ;
	
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;

    public PanneauGameOver(PartieControlleur controlleur) {
    	this.controlleur = controlleur;

       try {                
          image = ImageIO.read(new File("images/gameOver.jpg"));
       } catch (IOException ex) {
            System.out.println("Image not Found");
       }
       setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        width = image.getWidth(this);
        height = image.getHeight(this);
        x=((getWidth()-width)/2);
        y=((getHeight()-height)/2);

        g.drawImage(image, x, y , width, height, Color.black,this);       
    }

	@Override
	public void updates(CandyEvent e) {
		if (e instanceof CandyEventGameOver){
			controlleur.gameOverNotyfed() ;
		}
	}

}
