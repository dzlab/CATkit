package org.ca.toolkit.control;

import java.lang.Thread;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ca.toolkit.common.Constants;
import org.ca.toolkit.common.Constants.Room;
import org.ca.toolkit.common.VirtualTime;
import org.ca.toolkit.common.Constants.PlaceName;
import org.ca.toolkit.common.Peer;
import org.ca.toolkit.view.AgentView;
import org.ca.toolkit.view.PlaceView;
import org.ca.toolkit.view.WorldView;
import org.ca.toolkit.service.GeoTagService;

public class SimulationHelper extends Thread {

	protected WorldView world;
	/** List of agents in the simulation environment */
	protected List<AgentView> agents;
	/** Current time of the world */
	protected VirtualTime now;
	/** Java2D area on which the world is drawn */
	protected Graphics graphics;
	
	/** Variable used to stop the thread */
	public boolean stop;
	
	/** This variable is set to true to indicate the need for redrawing the world */
	protected boolean redraw = true;
	
	static Logger log = LoggerFactory.getLogger(SimulationHelper.class);
	
	public SimulationHelper() {
		world = new WorldView();
		agents = new ArrayList<AgentView>();
		now = new VirtualTime(6, 50);
		stop = false;		
		
		initialize();
	}
	
	public SimulationHelper(Graphics g) {
		world = new WorldView();
		agents = new ArrayList<AgentView>();
		now = new VirtualTime(6, 50);
		graphics = g;
		stop = false;
		
		initialize();
	}
	
	/** Initialize world and plan for agent tasks */
	public void initialize() {
		PlaceView bossDesk = new PlaceView("Boss_Desk", Constants.POINT_ROOM_UP_RIGHT_1, PlaceName.DESK);
		AgentView boss = new AgentView("Boss", bossDesk);
		boss.planBoss();	
		agents.add(boss);
		
		PlaceView secretaryDesk = new PlaceView("Secretary_Desk", Constants.POINT_ROOM_DOWN_RIGHT_1, PlaceName.DESK);
		AgentView secretary = new AgentView("Secretary", secretaryDesk);
		secretary.planSecretary();	
		agents.add(secretary);
		
	}
	
	/** Called by thread method start() */
	public void run(){
		while(true) {
			if(stop) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}				
			boolean redrawn = doIteration();
			if(!redrawn) {
				drawWorld();			
				try {
					sleep(Constants.STEP);
				} catch (InterruptedException e) {
					log.error(e.getMessage());
				}
			}									
			now.increment(0, 1);
		}
	}			
	
	/** Check all agents to perform activities matching current time */
	public boolean doIteration() {
		boolean changed = false;
		for(Iterator<AgentView> it = agents.iterator(); it.hasNext(); ) {
			AgentView agent = it.next();
			log.info(now.toString() + " - Handling agent: " + agent.toString());
			if(agent.hasTaskNow(now)) {			
				do{				
					if(agent.performTask(now)) {									
						drawWorld();
						changed = true;
					}
				}while(!(agent.atDestination() || agent.isResting()));		
			}
		}
		return changed;
	}
	
	/** Draw the background and the different agents of the world*/
	public void drawWorld() {
		//log.info(now.toString() + " - Drawing world into graphics(" + graphics + ")");
		if(graphics != null) {
			graphics.drawImage(WorldView.getImage(), 0, 0, null);
			for(Iterator<AgentView> it = agents.iterator(); it.hasNext(); ) {
				AgentView agent = it.next();			
				if (agent.location != null)
					graphics.drawImage(AgentView.getImage(), agent.location.x, agent.location.y, null);
			}
			List<Point> geotags = GeoTagService.getInstance().getDisplayableMessages();
			if(geotags != null)
			for(Iterator<Point> it = geotags.iterator(); it.hasNext(); ) {
				Point point = it.next();								
				graphics.drawImage(GeoTagService.getImage(), point.x, point.y, null);
			}
		}		
	}	
	
	/** Called to suspend temporary the simulation */
	public synchronized void pause() {
		log.info(now.toString() + " - Suspending the main thread, is it stopped? " + stop);
		stop = true;
	}
	
	/** Called to resume the suspended simulation */
	public synchronized void unpause() {
		log.info(now.toString() + " - Resuming the main thread, is it stopped? " + stop);
		notify();
		stop = false;
	}
	
	/** Draw the background and the different agents of the world
	 * @param {@link java.awt.Graphics} where to draw the world
	 * */
	public void drawWorld(Graphics g) {
		g.drawImage(WorldView.getImage(), 0, 0, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MAIN_GATE.x, Constants.POINT_MAIN_GATE.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_1.x, Constants.POINT_MEETING_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_2.x, Constants.POINT_MEETING_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_3.x, Constants.POINT_MEETING_3.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_4.x, Constants.POINT_MEETING_4.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_5.x, Constants.POINT_MEETING_5.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_MEETING_6.x, Constants.POINT_MEETING_6.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_1.x, Constants.POINT_ROOM_BREAK_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_2.x, Constants.POINT_ROOM_BREAK_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_3.x, Constants.POINT_ROOM_BREAK_3.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_LEFT_1.x, Constants.POINT_ROOM_DOWN_LEFT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_LEFT_2.x, Constants.POINT_ROOM_DOWN_LEFT_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_LEFT_3.x, Constants.POINT_ROOM_DOWN_LEFT_3.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_LEFT_4.x, Constants.POINT_ROOM_DOWN_LEFT_4.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_1.x, Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_2.x, Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_3.x, Constants.POINT_ROOM_DOWN_MIDDLE_LEFT_3.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_1.x, Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_2.x, Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_2.y, null);g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_1.x, Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_3.x, Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_3.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_4.x, Constants.POINT_ROOM_DOWN_MIDDLE_RIGHT_4.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_RIGHT_1.x, Constants.POINT_ROOM_DOWN_RIGHT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_RIGHT_2.x, Constants.POINT_ROOM_DOWN_RIGHT_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_DOWN_RIGHT_3.x, Constants.POINT_ROOM_DOWN_RIGHT_3.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_UP_RIGHT_1.x, Constants.POINT_ROOM_UP_RIGHT_1.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_UP_MIDDLE_RIGHT_1.x, Constants.POINT_ROOM_UP_MIDDLE_RIGHT_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_UP_MIDDLE_RIGHT_2.x, Constants.POINT_ROOM_UP_MIDDLE_RIGHT_2.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_1.x, Constants.POINT_ROOM_BREAK_1.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_2.x, Constants.POINT_ROOM_BREAK_2.y, null);
		g.drawImage(AgentView.getImage(), Constants.POINT_ROOM_BREAK_3.x, Constants.POINT_ROOM_BREAK_3.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_TOILET_TOP.x, Constants.POINT_TOILET_TOP.y, null);
		
		g.drawImage(AgentView.getImage(), Constants.POINT_TOILET_DOWN.x, Constants.POINT_TOILET_DOWN.y, null);		
	}
}
