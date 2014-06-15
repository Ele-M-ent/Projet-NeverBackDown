package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	ArrayList<GridSoldier> listeGrid;
	boolean lose = false;
	
	int posTroupeXInit, posTroupeYInit;
	
    // ##############################################################################################
    //							   CONSTRUCTEURS
    // ##############################################################################################

	public Model(int posTroupeXInit, int posTroupeYInit, GridSoldier g) 
	{
		this.mainAllie = new Soldat(posTroupeXInit, posTroupeYInit, Camps.allie);
		listeGrid = new ArrayList<GridSoldier>();
		this.posTroupeXInit = posTroupeXInit;
		this.posTroupeYInit = posTroupeYInit;
		
		Thread threadArriveEnnemys = new Thread(new ThreadArriveeEnnemys());
		threadArriveEnnemys.start();
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
			notyfyAllieChange();
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
						boolean isColision = false;
						while(!isColision && mainAllie.getYDown()>-5 ) 
						{
							for(GridSoldier grid : listeGrid)
							{
								isColision = grid.isColisionWith(mainAllie);
							}
								
							mainAllie.upMove();
							notyfyAllieChange();
							try {Thread.sleep(40);}   
							catch (InterruptedException e) {e.printStackTrace();}
						}
						launchTroupe = false;
						mainAllie.setPosX(posTroupeXInit);
						mainAllie.setPosY(posTroupeYInit);
						notyfyAllieChange();
					}
				});
		t.start();
	}
	

    // ##############################################################################################
    //							   Mouvement des Enemys
    // ##############################################################################################

	private class ThreadArriveeEnnemys implements Runnable
	{
		int compteur = 0;
		
		public void run() 
		{
			compteur = compteur + 10;
			GridSoldier grid = new GridSoldier(2, 2, 4*compteur, -20);
			
			listeGrid.add(grid);
			Thread threadMouvementEnnemys = new Thread(new ThreadMouvementEnnemys());
			threadMouvementEnnemys.start();
			
			
			while(!lose)
			{
				try {Thread.sleep(1000);} 
				catch (InterruptedException e) {e.printStackTrace();}
				compteur = compteur + 10;
				grid = new GridSoldier(2, 2, 60*compteur, -20);
				listeGrid.add(grid);
				notyfyEnnemyChange();
			}
			
		}
	}
	
    // ##############################################################################################
    //							   Arrivée des Enemys
    // ##############################################################################################

	private class ThreadMouvementEnnemys implements Runnable
	{
		public void run() 
		{
			while(!isGameLost())
			{
				for(GridSoldier gs : listeGrid)
				{
					gs.downMove();
				}
				try {Thread.sleep(1000);} 
				catch (InterruptedException e) {e.printStackTrace();}
				notyfyEnnemyChange();
			}
			
			if(isGameLost())
			{
				lose = true;
				String str = "Vous vous êtes fait bombarder !! \n"+
							"Vous marquez : " + "1000" + " points grâce à cette partie!";
				JOptionPane.showMessageDialog(null, str, "Félicitation ! ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
    // ##############################################################################################
    //							  Is Game Lost
    // ##############################################################################################

	private boolean isGameLost()
	{
		for(GridSoldier gs : listeGrid)
		{

			if(gs.getYDown()>posTroupeYInit-70)
				return true;
		}
		return false;
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


	public void notyfyAllieChange() {
		for(Observateur observateur : this.observateurList)
		{
			observateur.updateScreenAllie(mainAllie);
		}
		
	}

	public void notyfyEnnemyChange() {
		for(Observateur observateur : this.observateurList)
		{
			ArrayList<GridSoldier> lgs = new ArrayList<GridSoldier>();
			//System.out.println(listeGrid);
			
			for(GridSoldier g : listeGrid)
				lgs.add((GridSoldier)g.clone());
			
			observateur.updateScreenEnemys(lgs);
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

	
	public ArrayList<GridSoldier> getListeGrid() {
		return listeGrid;
	}





}


