package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import model.Troupe.Camps;

public class GridSoldier 
{
	// ##############################################################################################
    // 								ATTRIBUTS
    // ##############################################################################################
	
	private Hashtable<Integer, ArrayList<Troupe>> colonne = new Hashtable<Integer, ArrayList<Troupe>>();
	private ArrayList<Troupe> line = new ArrayList<Troupe>();
	
	private int posX, posY;
	private int sizeX, sizeY;
	
	private int nbLine, nbColone;
	
	private final int espacement = 1;
	
    // ##############################################################################################
    //							   CONSTRUCTEURS
    // ##############################################################################################

	public GridSoldier(int nbLine, int nbColone, int posX, int posY) 
	{
		super();
		this.posX = posX;
		this.posY = posY;
		this.nbLine = nbLine;
		this.nbColone = nbColone;
		
		line = new ArrayList<Troupe>();
		Troupe s = new Soldat(posX, posY, Camps.ennemy);
		
		for(int i=0; i<nbColone; i++)
		{
			for(int j=0; j<nbLine; j++)
				line.add(new Soldat(posX+(s.getSizeX()*espacement)*i, posY + (s.getSizeY()*espacement)*j, Camps.ennemy));
			colonne.put(i,line);
			line = new ArrayList<Troupe>();
		}
		
		this.sizeX = s.getSizeX()*espacement*nbColone;
		this.sizeY = s.getSizeY()*espacement*nbLine;
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
			
			lt = colonne.get( (t.getXRight() - this.posX)/(sizeX/nbColone) );
			if(!lt.isEmpty())
			{
				if( lt.get(lt.size() -1).getXLeft() > t.getXRight() && lt.get(lt.size() -1).getXRight() < t.getXRight() ) 
				{
					if(t.getYUp() < lt.get(lt.size() -1).getYDown() + posY)
					{
							lt.remove(lt.size() -1);
							return true;
					}
				}
			}
		}
		return false;
	}
	
	
	//##############################################################################################
	//							REMOVE SOLDIER
	//##############################################################################################*/

	public void removeSoldier(int l, int c)
	{
		this.colonne.get(c).remove(l);
	}

	
	//##############################################################################################
	//						ACCESSEUR
	//##############################################################################################*/

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

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

	//##############################################################################################
	//						MODIFICATEUR
	//##############################################################################################*/
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getNbLine() {
		return nbLine;
	}

	public int getNbColone() {
		return nbColone;
	}

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
