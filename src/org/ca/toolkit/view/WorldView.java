package org.ca.toolkit.view;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.ca.toolkit.common.Constants;
import org.ca.toolkit.common.VirtualTime;



public class WorldView {

	protected BufferedImage image;
	protected List<WorldTask> plan;
	
	public WorldView() {
		image = getImage();
		plan = new ArrayList<WorldView.WorldTask>();
	}
	
	/** Get the default image of a world */
	public static BufferedImage getImage() {
		BufferedImage image = null;				
		try {		
			image = ImageIO.read(new File(".\\res\\background.png"));			
		}catch (IOException ioe) {			
			ioe.printStackTrace();		
		}
		return image;
	}

	/** This class model a world task (e.g. simulating night, day) */
	public class WorldTask {
		public VirtualTime time;
		public Constants.WorldActivity activity;
		
		public WorldTask() {
			
		}
	}
}
