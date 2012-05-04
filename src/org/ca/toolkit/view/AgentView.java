package org.ca.toolkit.view;

import java.awt.image.BufferedImage;
import java.awt.Point;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ca.toolkit.common.Constants;
import org.ca.toolkit.common.VirtualTime;
import org.ca.toolkit.common.Constants.AgentActivity;
import org.ca.toolkit.service.BrokerService;
import org.ca.toolkit.service.GeoTagService;


public class AgentView {

	static Logger log = LoggerFactory.getLogger(AgentView.class);
	
	public String identifier;
	public PlaceView desk;
	protected BufferedImage image;
	
	/** List of tasks of this agent */
	protected List<AgentTask> plan;
	/** Index to the next task to perform */
	protected int idxTask;
	/** Current agent location */ 
	public Point location;
	/** Final destination for this agent */
	public Point destination;
	/** A temporary destination is one of the points constituting the path between the current location and final destination*/
	protected Point tempoDest;
	/**  Part of the path this agent is walking throw, it's used to choose set correctly the temporar destination */
	protected int pathPart = 0;
	
	/** Current agent activity */
	AgentActivity activity;
	
	public AgentView() {
		image = getImage();
		plan = new ArrayList<AgentView.AgentTask>(); 
		idxTask = 0;
	}
	
	public AgentView(String id, PlaceView d) {
		image = getImage();
		plan = new ArrayList<AgentView.AgentTask>();
		idxTask = 0;
		
		identifier = id;
		desk = d;
	}
	
	/** Get the default image of an agent */
	public static BufferedImage getImage() {
		BufferedImage image = null;	
		try {
			image = ImageIO.read(new File(".\\res\\HumanYellow-38.11-7.png"));		
		}catch (IOException ioe) {			
			ioe.printStackTrace();		
		}
		return image;
	}
	
	/** plan for daily tasks of a worker */
	public void planWorker() {
		
	}
	
	/** plan for daily tasks of a secretary */
	public void planSecretary() {
		plan.add(new AgentTask(new VirtualTime(7, 45),  AgentActivity.AT_BOARD));
		plan.add(new AgentTask(new VirtualTime(8, 15),  AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_DOWN_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(8, 30),  AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(9, 0),   AgentActivity.GOING_2_BREAK, Constants.POINT_ROOM_BREAK_2));
		plan.add(new AgentTask(new VirtualTime(9, 5),   AgentActivity.IN_BREAK));
		plan.add(new AgentTask(new VirtualTime(9, 10),  AgentActivity.POST_TAG));
		plan.add(new AgentTask(new VirtualTime(9, 15),  AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_DOWN_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(9, 20),  AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(12, 5),  AgentActivity.GOING_2_BREAK, Constants.POINT_ROOM_BREAK_3));
		plan.add(new AgentTask(new VirtualTime(12, 10), AgentActivity.IN_BREAK));
		plan.add(new AgentTask(new VirtualTime(13, 25), AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_DOWN_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(13, 30), AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(15, 00), AgentActivity.GOING_2_TOILET, Constants.POINT_TOILET_DOWN));
		plan.add(new AgentTask(new VirtualTime(15, 5),  AgentActivity.IN_TOILET));
		plan.add(new AgentTask(new VirtualTime(15, 15), AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_DOWN_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(15, 20), AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(17, 30), AgentActivity.GOING_TO_BOARD, Constants.POINT_MAIN_GATE));
		plan.add(new AgentTask(new VirtualTime(17, 35), AgentActivity.AT_BOARD));
		plan.add(new AgentTask(new VirtualTime(17, 40), AgentActivity.RESTING));
	}
	
	/** plan for daily tasks for the boss */
	public void planBoss() {
		plan.add(new AgentTask(new VirtualTime(7, 0),   AgentActivity.AT_BOARD));
		plan.add(new AgentTask(new VirtualTime(8, 0),   AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_UP_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(8, 30),  AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(10, 0),  AgentActivity.GOING_2_BREAK, Constants.POINT_ROOM_BREAK_1));
		plan.add(new AgentTask(new VirtualTime(10, 5),  AgentActivity.IN_BREAK));
		plan.add(new AgentTask(new VirtualTime(10, 15), AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_UP_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(10, 20), AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(12, 0),  AgentActivity.GOING_2_BREAK, Constants.POINT_ROOM_BREAK_1));
		plan.add(new AgentTask(new VirtualTime(12, 5),  AgentActivity.IN_BREAK));
		plan.add(new AgentTask(new VirtualTime(13, 25), AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_UP_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(13, 30), AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(15, 15), AgentActivity.GOING_2_TOILET, Constants.POINT_TOILET_TOP));
		plan.add(new AgentTask(new VirtualTime(15, 20), AgentActivity.IN_TOILET));
		plan.add(new AgentTask(new VirtualTime(15, 25), AgentActivity.GOING_2_DESK, Constants.POINT_ROOM_UP_RIGHT_1));
		plan.add(new AgentTask(new VirtualTime(15, 30), AgentActivity.AT_DESK));
		plan.add(new AgentTask(new VirtualTime(18, 0),  AgentActivity.GOING_TO_BOARD, Constants.POINT_MAIN_GATE));
		plan.add(new AgentTask(new VirtualTime(18, 5),  AgentActivity.AT_BOARD));
		plan.add(new AgentTask(new VirtualTime(18, 10), AgentActivity.RESTING));
	}
	
	/**
	 * Perform the current task granted to this agent
	 * @param now current world time
	 * @return true if the agent location changed, and there is a need to redraw the world
	 */	 
	public boolean performTask(VirtualTime now) {
		boolean changed = false;
		AgentTask task = plan.get(idxTask);		
		if(task.time.equals(now)) {
			switch(task.activity) {			
			case AT_BOARD:
				location = new Point(Constants.POINT_MAIN_GATE);
			case AT_DESK:
			case IN_BREAK:
			case IN_TOILET:			
				destination = location;
				nextTask();
				break;
			case GOING_2_BREAK:
			case GOING_2_MEETING:
			case GOING_2_DESK:
			case GOING_2_TOILET:
			case GOING_TO_BOARD:
				switch(pathPart) {
				case 0: 
					destination = task.destination;
					pathPart = 1;
				case 1:
					tempoDest = Constants.roomToGate.get(location);
					pathPart = 5;
					break;
				case 2:
					tempoDest = Constants.roomToGate.get(destination);
					pathPart = 6;
					break;
				case 3:
					tempoDest = destination;
					pathPart = 7;
					break;
				default:
					break;
				}
				log.info(task.time + " - Activity ("+task.activity+") Path part (" + pathPart%4 + "): Current position (" + location.x + "," + location.y 
						+ ") going to destination (" + task.destination.x + "," + task.destination.y  
						+ ") having temporar destination (" + tempoDest.x +"," + tempoDest.y +")");
				changed = moveToDestination();
				if(changed)
					BrokerService.getInstance().publish(identifier, location);
				break;
			case POST_TAG:
				log.info("Agent " + identifier + " posting a message in room " + Constants.xyToRoom.get(location));
				GeoTagService.getInstance().postGetTag(location, "Message!");		
				nextTask();
				break;
			default:				
				location    = null;
				destination = null;
				tempoDest   = null;
				nextTask();
				changed = true;
			}
		}
		return changed;
	}
	
	/** Move the agent to the destination by updating with one or more steps its coordinate */
	public boolean moveToDestination() {
		boolean changed = false;
		if(location != null) {
			int deltaX = Math.abs(location.x - tempoDest.x);
			int deltaY = Math.abs(location.y - tempoDest.y);
			int alphaX = (deltaY!=0 ? Math.max(deltaX/deltaY, Math.min(deltaX, 1)):Math.min(deltaX, 1));
			int alphaY = (deltaX!=0 ? Math.max(deltaY/deltaX, Math.min(deltaY, 1)):Math.min(deltaY, 1));
			if(location.x < tempoDest.x) {
				location.x += alphaX;
				changed = true;
			} else if(location.x > tempoDest.x) {
				location.x -= alphaX;
				changed = true;
			}			
			if (location.y < tempoDest.y) {
				location.y += alphaY;
				changed = true;
			} else if (location.y > tempoDest.y) {
				location.y -= alphaY;
				changed = true;
			}
			if(location.equals(tempoDest)) 
				pathPart = (pathPart+1) % 4;
			if(atDestination()) 
				nextTask();			
		}
		return changed;
	}
	
	/** Check is this agent reached its final destination */
	public boolean atDestination() {
		if(location!=null && destination!=null && location.equals(destination)) 
			return true;
		return false;
	}
	
	/** Check if it is time to perform the task of this agent */
	public boolean hasTaskNow(VirtualTime now) {
		return plan.get(idxTask).time.equals(now);
	}
	
	/** Check if the agent is currently resting */
	public boolean isResting() {
		return (activity == AgentActivity.RESTING);
	}
	
	/** Initialize variable for executing next task */
	public void nextTask() {
		activity = plan.get(idxTask).activity;
		if(++idxTask == plan.size())
			idxTask = 0;					
		pathPart = 0;
	}
	
	/** Get a {@link String} describing this agent */
	public String toString() {
		return identifier + " next task at "+ plan.get(idxTask).toString();
	}
	
	/** Print the current plan of this agent */
	public void printPlan() {
		for(int idx=0; idx<plan.size(); idx++) {
			log.info(plan.get(idx).toString());
		}
	}
	
	/** This class model an agent task or a transition in the agent state (e.g. going break, at work) */
	public class AgentTask {
		public VirtualTime time;
		public AgentActivity activity;
		public Point destination;
		
		public AgentTask() {
			
		}		
		public AgentTask(VirtualTime t, AgentActivity a) {
			time = t;
			activity = a;
		}
		public AgentTask(VirtualTime t, AgentActivity a, Point d) {
			time = t;
			activity = a;
			destination = d;
		} 
		public String toString() {
			String str = time.toString() + " " + activity.toString();
			if(destination != null)
				str += " (" + destination.x + "," + destination.y + ")";
			return str;
		}
	}

}
