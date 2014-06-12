package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Soldat extends Troupe
{
	
	public Soldat(int posX, int posY, Camps c) {
		super();
		this.posX = posX;
		this.posY = posY;
		
				//Gestion des images
		try {
			if(c.equals(Camps.allie))
				bimgCourante = ImageIO.read(new File("img/examples/mixed_metal.gif"));
			else
				bimgCourante = ImageIO.read(new File("img/examples/robed_skeleton_spellcast.gif"));
			
			
			for(int i=0 ; i<4; i++)
			{
				bimgs[i] = bimgCourante.getSubimage(0, i*(bimgCourante.getHeight()/4), bimgCourante.getWidth(), bimgCourante.getHeight()/4);
			}
			
			if(c.equals(Camps.allie))
				bimgCourante= bimgs[0];
			else
				bimgCourante= bimgs[2];
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sizeX= bimgCourante.getWidth();
		this.sizeY= bimgCourante.getHeight();
	}
}
