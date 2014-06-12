package controleur;

import model.Model;
import model.Model.Move;


public class GameControleur 
{
	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	private Model gameModel ;
	private int leftBorder, rightBorder;
	
	//##############################################################################################
	//						CONSTRUCTEUR
	//##############################################################################################*/

	public GameControleur(Model gameModel, int leftBorder, int rightBorder) {
		super();
		this.gameModel = gameModel;
		this.leftBorder = leftBorder;
		this.rightBorder = rightBorder;
	}

	//##############################################################################################
	//						CONTROLE DU MOUVEMENT DU SOLDAT
	//##############################################################################################*/

	public void controleSoldierMove(Move m) 
	{
		if(m.equals(Move.left))
		{
			if( (gameModel.getMainSoldat().getXLeft()-5>leftBorder) && (gameModel.getMainSoldat().getXRight()-5<rightBorder) ) 
				gameModel.moveSoldat(m);
		}
		else
		{
			if( (gameModel.getMainSoldat().getXLeft()+5>leftBorder) && (gameModel.getMainSoldat().getXRight()+5<rightBorder) ) 
				gameModel.moveSoldat(m);
		}
	}
	
	//##############################################################################################
	//						LANCER UN SOLDAT
	//##############################################################################################*/
	
	public void launchSoldier()
	{
		if(!gameModel.isLaunchSoldat())
			gameModel.launchSoldat();
	}

}
