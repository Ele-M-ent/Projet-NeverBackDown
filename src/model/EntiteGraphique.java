package model;


public class EntiteGraphique 
{

	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	protected int posX, posY;
	protected int sizeX, sizeY;
	protected int vitesse = 7;
	
    // ##############################################################################################
    //							   CONSTRUCTEURS
    // ##############################################################################################

	public EntiteGraphique(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	
	
    // ##############################################################################################
    //							     MOUVEMENT
    // ##############################################################################################

	public synchronized void upMove() {
		this.posY = this.posY - vitesse;
	}

	public synchronized void downMove() {
		this.posY = this.posY + vitesse;
	}

	public synchronized void leftMove() {
		this.posX = this.posX - vitesse;
	}

	public synchronized void rightMove() {
		this.posX = this.posX + vitesse;
	}

	
	
	//##############################################################################################
	//						ACCESSEUR
	//##############################################################################################*/

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getXLeft() {
		return posX;
	}

	public int getXRight() {
		return posX + sizeX;
	}
		
	public int getYUp() {
		return posY;
	}
	public int getYDown() {
		return posY + sizeY;
	}
	
	public int getVitesse() {
		return vitesse;
	}
	

	//##############################################################################################
	//						MODIFICATEUR
	//##############################################################################################*/
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	


}
