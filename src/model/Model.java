package model;

import java.util.ArrayList;

import model.Troupe.Camps;
import paternObservable.Observable;
import paternObservable.Observateur;

public class Model implements Observable
{
	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	Troupe mainAllie;
	boolean launchTroupe = false;
	GridSoldier grid;
	
	int posTroupeXInit, posTroupeYInit;
	
    // ##############################################################################################
    //							   CONSTRUCTEURS
    // ##############################################################################################

	public Model(int posTroupeXInit, int posTroupeYInit, GridSoldier g) 
	{
		this.mainAllie = new Soldat(posTroupeXInit, posTroupeYInit, Camps.allie);
		this.grid = new GridSoldier(3, 6, 40, 40);
		this.posTroupeXInit = posTroupeXInit;
		this.posTroupeYInit = posTroupeYInit;
	}
	
    // ##############################################################################################
    //							   BOUGER SOLDAT
    // ##############################################################################################

	public void moveSoldat(Move m)
	{
		if(!launchTroupe)
		{
			if(m.equals(Move.left))
				mainAllie.leftMove();
			else
				mainAllie.rightMove();
			notyfyObservateurs();
		}
	}
	
	
    // ##############################################################################################
    //							   LANCER SOLDAT
    // ##############################################################################################

	public void launchSoldat()
	{
		launchTroupe = true;
		
		Thread t = new Thread(new Runnable() 
				{
					@Override
					public void run() 
					{
						while(!grid.isColisionWith(mainAllie) && mainAllie.getYDown()>-5 ) 
						{
							mainAllie.upMove();
							notyfyObservateurs();
							try {Thread.sleep(40);}   
							catch (InterruptedException e) {e.printStackTrace();}
						}
						launchTroupe = false;
						mainAllie.setPosX(posTroupeXInit);
						mainAllie.setPosY(posTroupeYInit);
						notyfyObservateurs();
					}
				});
		t.start();
	}
	
	
    // ##############################################################################################
    //							  TYPE DE MOUVEMENT
    // ##############################################################################################

	public enum Move{
		up,
		down,
		left,
		right;
	}
	
	//###############################################################################################
	//							IMPLEMENTATION INTERFACE OBSERVABLE :
	//						Le GameModel est observable par le GamePanel
	//###############################################################################################
	
	private ArrayList<Observateur> observateurList = new ArrayList<Observateur>();
	

	public void addObservateur(Observateur obs)
	{
		this.observateurList.add(obs);
	}

	public void deleteObservateurs() 
	{
		this.observateurList = new ArrayList<Observateur>();	
	}

	public void notyfyObservateurs() 
	{	
		for(Observateur observateur : this.observateurList)
		{
			observateur.updateScreen(mainAllie, grid);
		}
	}

    // ##############################################################################################
    //							  ACCESSEUR
    // ##############################################################################################

	public Troupe getMainSoldat() {
		return mainAllie;
	}

	public boolean isLaunchSoldat() {
		return launchTroupe;
	}

	public GridSoldier getGrid() {
		return grid;
	}

}
