package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GridSoldier;
import model.Troupe;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	Image imgFond, imgShip, imgEnemies;
	
	private Troupe troupe;
	private GridSoldier gs;
	
	BufferedImage[] bimgE = new BufferedImage[4];
	
	//##############################################################################################
	//						CONSTRUCTEUR
	//##############################################################################################*/
	
	public GamePanel(Troupe troupe, GridSoldier gs) {
		super();
		this.troupe = troupe;
		this.gs = gs;

		try {
			imgFond = ImageIO.read(new File("img/fond_etoile.png"));
			imgShip = troupe.getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	//##############################################################################################
	//						PEINDRE LE PANNEAU
	//##############################################################################################*/

	/**
	 * Methode issue de l'h�ritage de JPanel
	 */
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(imgFond, 0, 0, this.getWidth(), this.getHeight(),this);
		for(Troupe t : gs.getAllTroupes())
			g.drawImage(t.getImage(), t.getXLeft(), t.getYUp(), null);
		
		g.drawImage(imgShip, troupe.getXLeft(), troupe.getYUp(), this);
	}

	
	//##############################################################################################
	//						ACCESSEUR
	//##############################################################################################*/

	public Image getImgShip() {
		return imgShip;
	}

	public Image getImgEnemies() {
		return imgEnemies;
	}

	public Troupe getTroupe() {
		return troupe;
	}

	public GridSoldier getGs() {
		return gs;
	}

	
	//##############################################################################################
	//						MODIFICATEUR
	//##############################################################################################*/

	public void setImgShip(Image imgShip) {
		this.imgShip = imgShip;
	}

	public void setImgEnemies(Image imgEnemies) {
		this.imgEnemies = imgEnemies;
	}

	public void setTroupe(Troupe troupe) {
		this.troupe = troupe;
	}

	public void setGs(GridSoldier gs) {
		this.gs = gs;
	}
}
