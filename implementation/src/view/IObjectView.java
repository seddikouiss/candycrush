package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class IObjectView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image ;
	private boolean selected = false;
    private boolean centrer = false;

	public IObjectView(String img){
		super();
		
		image = getToolkit().getImage(img);
		
	}
	public void select (){
		selected = true ;
		paintComponent(this.getGraphics()) ;
		
	}
	public void unSelect (){
		selected = false ;
		
		paintComponent(this.getGraphics()) ;
		
	}

	public void paintComponent(Graphics g) {
		
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (centrer) {
            width = image.getWidth(this);
            height = image.getHeight(this);
            x=((getWidth()-width)/2);
            y=((getHeight()-height)/2);
        } else {
            width = this.getWidth();
            height = this.getHeight();
        }
        if (selected){
        	g.drawImage(image, x, y , width, height, Color.darkGray,this);
        	//
        }
        else {
        	g.drawImage(image, x, y, width, height, Color.white,this) ;
        	
        }
	}
	public boolean isCentrer() {
		return centrer;
	}
	public void setCentrer(boolean centrer) {
		this.centrer = centrer;
	}
	
}
