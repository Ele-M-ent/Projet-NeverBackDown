package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre2 extends JFrame
{
	private Pan pan;
	
	public Fenetre2()
	{
		this.setLocationRelativeTo(null);
		this.setTitle("Gérer vos conteneur");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 400);
		
		//On crée deux conteneurs de couleurs différentes
		pan = new Pan();

		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
