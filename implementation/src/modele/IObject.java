package modele;

public abstract class IObject {
	protected String image;
	protected int nbPoint ;

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setNbPoint(int nbPoint) {
		this.nbPoint=  nbPoint ;
		
	}
	
	public int getNbPoint() {
		return nbPoint;
	}

}
