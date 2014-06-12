package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Grille<Type extends Troupe>
{
	private Hashtable<Integer, ArrayList<Type>> colonne = new Hashtable<Integer, ArrayList<Type>>();
	private ArrayList<Type> line = new ArrayList<Type>();
	private int nbLine, nbColone;
	private int posXGrid, posYGrid;
	private int taillePixel = 30;
	
	public Grille(Hashtable<Integer, ArrayList<Type>> colonne,
						ArrayList<Type> line, int nbLine, int nbColone, 
						int posXGrid, int posYGrid, int taillePixel) 
	{
		super();
		this.colonne = colonne;
		this.line = line;
		this.nbLine = nbLine;
		this.nbColone = nbColone;
		this.posXGrid = posXGrid;
		this.posYGrid = posYGrid;
		this.taillePixel = taillePixel;
	}

	public Hashtable<Integer, ArrayList<Type>> getColonne() {
		return colonne;
	}

	public ArrayList<Type> getLine() {
		return line;
	}	

	public int getNbLine() {
		return nbLine;
	}

	public int getNbColone() {
		return nbColone;
	}

	public int getPosXGrid() {
		return posXGrid;
	}

	public int getPosYGrid() {
		return posYGrid;
	}

	
	public void setColonne(Hashtable<Integer, ArrayList<Type>> colonne) {
		this.colonne = colonne;
	}

	public void setLine(ArrayList<Type> line) {
		this.line = line;
	}

	public void setNbLine(int nbLine) {
		this.nbLine = nbLine;
	}

	public void setNbColone(int nbColone) {
		this.nbColone = nbColone;
	}

	public void setPosXGrid(int posXGrid) {
		this.posXGrid = posXGrid;
	}

	public void setPosYGrid(int posYGrid) {
		this.posYGrid = posYGrid;
	}
	


}
