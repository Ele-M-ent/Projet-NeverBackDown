package vue;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import model.GridSoldier;
import model.Model;
import model.Model.Move;
import model.Troupe;
import paternObservable.Observateur;
import controleur.GameControleur;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements AWTEventListener
{

	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################

	private Model gameModel;						//Instanciation du model 
	private GameControleur gameControleur;		//Création du contrôleur de notre model
	
	private GamePanel pan;
	
	public Fenetre()
	{
		this.setLocationRelativeTo(null);
		this.setTitle("Gérer vos conteneur");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600, 450);
	
		gameModel = new Model(this.getWidth()/2, this.getHeight()-100, null);
		gameModel.addObservateur(new Observateur()		
			{
				@Override
				public void updateScreen(Troupe s, GridSoldier gs) {
					pan.setTroupe(s);
					pan.setGs(gs);
					pan.repaint();
				}
			});
	
		gameControleur = new GameControleur(gameModel, 5, this.getWidth()-30);
		

		pan = new GamePanel(gameModel.getMainSoldat(), gameModel.getGrid());
	
		this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
		
		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.setVisible(true);
	}


	public void eventDispatched(AWTEvent event) {
		 if(event instanceof KeyEvent)
		 {
		      KeyEvent key = (KeyEvent)event;
		      if(key.getID()==KeyEvent.KEY_PRESSED){ //Handle key presses
		        System.out.println(key.getKeyChar());

		        switch(key.getKeyCode())
				{
					case 39 : 	gameControleur.controleSoldierMove(Move.right);
								break;
								
					case 37 : 	gameControleur.controleSoldierMove(Move.left);
								break;
								
					case 32 : 	gameControleur.launchSoldier();
								break;
				}
		        key.consume();
		      }
		    }
		
	}

}

