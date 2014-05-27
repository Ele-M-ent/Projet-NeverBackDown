package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{

	Image imgFond, imgBullet, imgShip;
	
	private boolean isBullet = false;
	private int posXBullet;
	private int posYBullet;
	
	private int posXShip;
	
	//##############################################################################################
	//						CONSTRUCTEUR
	//##############################################################################################*/
	
	public GamePanel() {
		super();
		
		this.posXBullet = -50;
		this.posYBullet = -50;

		try {
			imgFond = ImageIO.read(new File("img/fond_etoile.png"));
			imgBullet = ImageIO.read(new File("img/shotUp.gif"));
			imgShip = ImageIO.read(new File("img/ship.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//---------------------------------------------------------------------------------------------//
	public GamePanel(int posXBullet, int posYBullet) {
		super();
		this.posXBullet = posXBullet;
		this.posYBullet = posYBullet;
		
		try {
			imgFond = ImageIO.read(new File("img/fond_etoile.png"));
			imgBullet = ImageIO.read(new File("img/shotUp.gif"));
			imgShip = ImageIO.read(new File("img/ship.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//##############################################################################################
	//						PEINDRE LE PANNEAU
	//##############################################################################################*/

	/**
	 * Methode issue de l'héritage de JPanel
	 */

	public void paintComponent(Graphics g)
	{
		g.drawImage(imgFond, 0, 0, this.getWidth(), this.getHeight(),this);
		g.drawImage(imgShip, posXShip, this.getHeight()-45, this);
		if(isBullet)
			g.drawImage(imgBullet, posXBullet, posYBullet, this);
	}
	
	public void newBullet(int setPosXShip)
	{
		setPosXBullet(setPosXShip+11);
		setPosYBullet(this.getHeight()-68);
		Thread t = new Thread(new Runnable() 
		{
			public void run() 
			{
				isBullet = true;
				while (posYBullet>-20) 
				{	
					setPosYBullet(posYBullet-3);
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				isBullet = false;
				repaint();
			}
		});
		t.start();

	}

	public synchronized boolean isBullet() {
		return isBullet;
	}

	public synchronized int getPosXBullet() {
		return posXBullet;
	}

	public synchronized int getPosYBullet() {
		return posYBullet;
	}

	public synchronized int getPosXShip() {
		return posXShip;
	}

	public synchronized void setBullet(boolean isBullet) {
		this.isBullet = isBullet;
	}

	public synchronized void setPosXBullet(int posXBullet) {
		this.posXBullet = posXBullet;
	}

	public synchronized void setPosYBullet(int posYBullet) {
		this.posYBullet = posYBullet;
	}

	public synchronized void setPosXShip(int posXShip) {
		this.posXShip = posXShip;
	}
	
	//##############################################################################################
	//						ACCESSEUR
	//##############################################################################################*/


	//##############################################################################################
	//						MODIFICATEUR
	//##############################################################################################*/
	


}
