package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pan extends JPanel 
{

	BufferedImage[] bi;
	Image image;
	int w,  h;
	
	public Pan() {
		try {
			image = ImageIO.read(new File("img/bob.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bi = this.splitImage(new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB), 8, 6);
		
	}
	
	
	public void paintComponent(Graphics g)
	{
		System.out.println(bi[0].getScaledInstance(w, h, BufferedImage.SCALE_DEFAULT));
		//g.drawImage(, this.getWidth()/2, this.getHeight()/2,this);
	}
	
	
	public  BufferedImage[] splitImage(BufferedImage img, int cols, int rows) 
	{
		w = img.getWidth()/cols;
		h = img.getHeight()/rows;
		int num = 0;
		BufferedImage imgs[] = new BufferedImage[w*h];
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++) 
			{
				imgs[num] = new BufferedImage(w, h, img.getType());
				
			}
		}
		return imgs;
	}

}
