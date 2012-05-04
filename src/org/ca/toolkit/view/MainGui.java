/**
 * 
 */
package org.ca.toolkit.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.File;

/**
 * @author hjwk9387
 *
 */
public class MainGui  extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3244739911399560563L;
	BufferedImage  image;
	  
	public MainGui() {
	
		try {		
			File directory = new File(".");
			File dir2 = new File("..");
			
			System.out.println("Current directory: " + directory.getCanonicalPath());
			System.out.println("Parent directory : " + dir2.getCanonicalPath());
			//BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));	 
			//String imageName=bf.readLine();
			String imageName = directory.getCanonicalPath() + "\\res\\background.png";
			System.out.println("File name : " + imageName);
			File input = new File(imageName);	  
			image = ImageIO.read(input);	  
		} catch (IOException ie) {	  
			System.out.println("Error:"+ie.getMessage());	  
		}
	  }

	  
	public void paint(Graphics g) {	  
		g.drawImage( image, 0, 0, null);	 
	}

	  
	static public void main(String args[]) throws Exception {	  
		JFrame frame = new JFrame("Display image");	  
		Panel panel = new MainGui();	  
		frame.getContentPane().add(panel);	  
		frame.setSize(755, 650);	  
		frame.setVisible(true);
	  }
}
