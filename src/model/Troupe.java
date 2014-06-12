package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Troupe 
{

	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	protected int posX, posY;
	protected int sizeX, sizeY;

	protected BufferedImage bimgCourante;
	protected BufferedImage[] bimgs = new BufferedImage[4];
	
    // ##############################################################################################
    //							     MOUVEMENT
    // ##############################################################################################

	public synchronized void upMove() {
		this.posY = this.posY - 5;
	}

	public synchronized void downMove() {
		this.posY = this.posY + 5;
	}

	public synchronized void leftMove() {
		this.posX = this.posX - 5;
	}

	public synchronized void rightMove() {
		this.posX = this.posX + 5;
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
		return posX + sizeX/2;
	}
		
	public int getYUp() {
		return posY;
	}
	
	public int getYDown() {
		return posY - sizeY/2;
	}

	public Image getImage() {
		return bimgCourante.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
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

	

	//##############################################################################################
	//								DESCRIPTEUR
	//##############################################################################################*/

	public String toString()
	{
		return "Pos soldat : " + posX + " | " + posY + "\n";
	}

	
	//##############################################################################################
	//								Type de Camps
	//##############################################################################################*/
	
	public enum Camps {
		ennemy,
		allie;
	}

}
