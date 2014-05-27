package vue;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame 
{

	private GamePanel pan;
	
	private int posXShip = 0;
	
	public Fenetre()
	{
		this.setLocationRelativeTo(null);
		this.setTitle("Gérer vos conteneur");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 400);
		
		//On crée deux conteneurs de couleurs différentes
		pan = new GamePanel(this.getWidth()/2,this.getHeight()/2);
		this.addKeyListener(new KeyListener() 
			{
			
				public void keyTyped(KeyEvent event) {}
				public void keyReleased(KeyEvent event) {}
				
				public void keyPressed(KeyEvent event)
				{
					//System.out.println(event.getKeyCode());
					switch(event.getKeyCode())
					{
						case 39 : 	posXShip += 10;
									pan.setPosXShip(posXShip);
									pan.repaint();
									break;
									
						case 37 : 	posXShip -= 10;
									pan.setPosXShip(posXShip);
									pan.repaint();
									break;
									
						case 32 : 	if(!pan.isBullet())
										pan.newBullet(posXShip);
									break;
									
					}
				}
			});
		//On le passe ensuite au content pane de notre objet Fenetre
		//placé au centre pour qu'il utilise tout l'espace disponible
		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.setVisible(true);
		//go();
	}

	private void go() 
	{
		int posY = 10;
		while (true) 
		{	
			posY ++;
			
			pan.setPosYBullet(posY);
			pan.repaint();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

