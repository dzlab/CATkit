package org.ca.toolkit.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.ca.toolkit.control.SimulationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Example1 extends Component {

	private static final long serialVersionUID = -5770594287721220257L;
	static Logger log = LoggerFactory.getLogger(Example1.class);
	
	static SimulationHelper helper;
	BufferedImage background = WorldView.getImage();
	BufferedImage person = AgentView.getImage();
	
	public void paint(Graphics g) {				
		if(helper != null)
			helper.drawWorld();
	}
	
	public Example1() {				
		setPreferredSize(getPreferredSize());
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		if(background == null)
			return new Dimension(100, 100);
		else
			return new Dimension(background.getWidth(null), background.getHeight(null));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JPanel boxPane = new JPanel(new BorderLayout());
		//boxPane.setLayout(new BoxLayout(boxPane, BoxLayout.LINE_AXIS));
		//boxPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		//boxPane.add(Box.createVerticalGlue());
		Example1 ex = new Example1();
		//boxPane.add(ex);
		
		boxPane.add(Box.createRigidArea(new Dimension(10, 0)));
		Button btnStartStop = new Button("Start / Stop"); 		
		btnStartStop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent event) {
				log.info("Suspending / Resuming the simulation thread, current state = " + helper.stop);
				if(helper.stop == true)
					helper.unpause();
				else
					helper.pause();						
			}
		});
		//btnStartStop.setMaximumSize(new Dimension(100, 100));
		boxPane.add(btnStartStop, BorderLayout.PAGE_START);
				
		JScrollPane worldScrollPane = new JScrollPane(ex);

		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, worldScrollPane, boxPane);
		splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
		
		worldScrollPane.addAncestorListener(new AncestorListener() {			
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				splitPane.repaint();				
			}			
			@Override
			public void ancestorMoved(AncestorEvent arg0) {
				splitPane.repaint();					
			}			
			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				splitPane.repaint();					
			}
		});  
		
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
        f.add(splitPane, BorderLayout.CENTER);
		//f.add(boxPane);
		f.pack();
		f.setVisible(true);

		helper = new SimulationHelper(ex.getGraphics());
		
		helper.start();
		
		//layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Duke"));

	}
	
}
