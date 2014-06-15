package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import model.Troupe.Camps;

public class GridSoldier extends EntiteGraphique implements Cloneable
{
	
	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	private Hashtable<Integer, ArrayList<Troupe>> colonne = new Hashtable<Integer, ArrayList<Troupe>>();
	private ArrayList<Troupe> line = new ArrayList<Troupe>();
	
	private Troupe s;
	
	private int nbLine, nbColone;
	
	private final int espacement = 1;
	
    // ##############################################################################################
    //							   CONSTRUCTEURS
    // ##############################################################################################

	public GridSoldier(int nbLine, int nbColone, int posX, int posY) 
	{
		super(posX,posY);
		
		this.nbLine = nbLine;
		this.nbColone = nbColone;
		
		line = new ArrayList<Troupe>();
		s = new Soldat(posX, posY, Camps.ennemy);
		
		for(int i=0; i<nbColone; i++)
		{
			for(int j=0; j<nbLine; j++)
				line.add(new Soldat(posX+(s.getSizeX()*espacement)*i, posY + (s.getSizeY()*espacement)*j, Camps.ennemy));
			colonne.put(i,line);
			line = new ArrayList<Troupe>();
		}
		
		this.sizeY = (s.getSizeY()+espacement) * (nbLine-1) + s.getSizeY(); 
		this.sizeX = (s.getSizeX()+espacement) * (nbColone-1) + s.getSizeX();
		
		this.setVitesse(5);
	}

	
    // ##############################################################################################
    //							     MOUVEMENT
    // ##############################################################################################

	public synchronized void upMove() 
	{
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		while(e.hasMoreElements())
		{
			Iterator<Troupe> i = e.nextElement().iterator();
			while(i.hasNext())
			{
				i.next().setPosY(i.next().getYUp() - vitesse );
			}
		}
		this.sizeX = s.getSizeX() * espacement * nbColone;
		this.sizeY = s.getSizeY() * espacement * nbLine;
	}
	
	public synchronized void downMove() 
	{
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		while(e.hasMoreElements())
		{
			Iterator<Troupe> i = e.nextElement().iterator();
			while(i.hasNext())
			{
				i.next().downMove();
			}
		}

		this.posY = this.posY + vitesse;
	}

	public synchronized void leftMove() 
	{
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		while(e.hasMoreElements())
		{
			Iterator<Troupe> i = e.nextElement().iterator();
			while(i.hasNext())
			{
				i.next().setPosX(i.next().getXLeft() - vitesse );
			}
		}
		this.sizeX = s.getSizeX() * espacement * nbColone;
		this.sizeY = s.getSizeY() * espacement * nbLine;
	}

	public synchronized void rightMove() 
	{
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		while(e.hasMoreElements())
		{
			Iterator<Troupe> i = e.nextElement().iterator();
			while(i.hasNext())
			{
				i.next().setPosX(i.next().getXLeft() + vitesse );
			}
		}
		this.sizeX = s.getSizeX() * espacement * nbColone;
		this.sizeY = s.getSizeY() * espacement * nbLine;
	}
		
	
	//##############################################################################################
	//							COLISION AVEC ALLIE
	//##############################################################################################*/

	public boolean isColisionWith(Troupe t)
	{
		if(t.getXRight() < this.posX)
			return false;
		else if( t.getXLeft()  > (this.posX + this.sizeX) )
			return false;
		else
		{
						//=================================================
						//	  Verification colision sur Enemy à gauche
						//=================================================
			
			ArrayList<Troupe> lt = colonne.get( (t.getXLeft() - this.posX)/(sizeX/nbColone) );
			if(!lt.isEmpty())
			{
				if( lt.get(lt.size() -1).getXLeft() < t.getXLeft() && lt.get(lt.size() -1).getXRight() > t.getXLeft() ) 
				{
					if(t.getYUp() < lt.get(lt.size() -1).getYDown() + posY)
					{
						lt.remove(lt.size() -1);
						return true;
					}
						
				}
			}
			
			lt = colonne.get( (t.getXRight() - this.posX)/((sizeX+espacement)/nbColone) );
			if(!lt.isEmpty())
			{
				if( lt.get(lt.size() -1).getXLeft() < t.getXRight() && lt.get(lt.size() -1).getXRight() > t.getXRight() ) 
				{
					if(t.getYUp() < lt.get(lt.size() -1).getYDown() + posY)
					{
							lt.remove(lt.size() -1);
							controlGridSize();
							return true;
					}
				}
			}
		}
		return false;
	}
	
    // ##############################################################################################
    //						CONTROLE TAILLE GRILLE
    // ##############################################################################################

	private void controlGridSize()
	{
			//Control de la taille X
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		int widthMax = e.nextElement().size();
		
		while(e.hasMoreElements())
		{
			ArrayList<Troupe> a = e.nextElement();
			if(a.size()>widthMax)
				widthMax = a.size();
		}
		nbLine = widthMax;
		
		this.sizeY = (s.getSizeY()+espacement) * (nbColone-1) + s.getSizeY(); 
		
			//Control de la taille Y
		nbColone = colonne.size();
		this.sizeX = (s.getSizeX()+espacement) * (nbColone-1) + s.getSizeX();
	}


	//##############################################################################################
	//						CONTROLEUR DE NULITE
	//##############################################################################################*/

	public boolean isEmpty(){
		Enumeration<ArrayList<Troupe>> e = colonne.elements();
		while(e.hasMoreElements())
		{
			if(!e.nextElement().isEmpty())
				return false;
		}
		return true;
	}
	
	
	//##############################################################################################
	//						Recuperer un clone de l'instance courante
	//##############################################################################################*/

	public Object clone() 
	{      
	    GridSoldier gs = null;
	    
		try {
			gs = (GridSoldier) super.clone();
			gs.colonne = (Hashtable<Integer, ArrayList<Troupe>>) colonne.clone();
			gs.line = ((ArrayList<Troupe>) line.clone());
		} 
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	    return gs;
  	}
	
	
	//##############################################################################################
	//						ACCESSEUR
	//##############################################################################################*/

	public ArrayList<Troupe> getAllTroupes()
	{
		ArrayList<Troupe> troupes = new ArrayList<Troupe>();
		Enumeration<ArrayList<Troupe>> h = colonne.elements();
		while(h.hasMoreElements())
		{
			troupes.addAll(h.nextElement());
		}
		return troupes;
	}

	public int getNbLine() {
		return nbLine;
	}

	public int getNbColone() {
		return nbColone;
	}
	
	
	//##############################################################################################
	//						DESCRIPTEUR
	//##############################################################################################*/

	public String toString() 
	{
		String str = "GRID : \n";
		Iterator<Troupe> a;
		Enumeration<ArrayList<Troupe>> h = colonne.elements();
		
		while(h.hasMoreElements())
		{
			a= h.nextElement().iterator();
			
			while(a.hasNext())
			{
				Troupe t = a.next();
				str += "\t " + t.getXLeft() + "\t " + t.getYUp() + "\n";
			}
		}
		str += "\n";
		return str;
	}
	
}
