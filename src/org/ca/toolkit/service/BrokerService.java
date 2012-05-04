/**
 * 
 */
package org.ca.toolkit.service;

/**
 * @author hjwk9387
 *
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.ca.toolkit.model.Context;
import org.ca.toolkit.model.ContextEvent;
import org.ca.toolkit.model.ContextEventListener;
import org.ca.toolkit.common.Peer;
import org.ca.toolkit.common.Constants;
import org.ca.toolkit.common.Constants.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrokerService {

	static Logger log = LoggerFactory.getLogger(BrokerService.class);
	
	public static BrokerService _instance;
	public static BrokerService getInstance() {
		if(_instance == null)
			_instance = new BrokerService();
		return _instance;
	}
	
	protected HashMap<String, Point> rawLocation;
	protected HashMap<String, Room> highLevelLocation;	
	protected HashMap<Room, ArrayList<ContextEventListener>> listenersMap;
	protected ArrayList<ContextEventListener> listeners;
	
	private BrokerService() {
		rawLocation = new HashMap<String, Point>();	
		highLevelLocation = new HashMap<String, Room>();
		listenersMap = new HashMap<Room, ArrayList<ContextEventListener>>();
		listeners = new ArrayList<ContextEventListener>();
	}
	
	/** Called by raw context providers to report the current location of an agent
	 * @param identifier of the agent
	 * @param current location of the agent */
	public void publish(String identifier, Point xyLocation) {
		rawLocation.put(identifier, xyLocation);
		Room room = Constants.xyToRoom.get(xyLocation);
		highLevelLocation.put(identifier, room);	
		//notifyListeners(room, identifier);
		notifyAllListeners(room, identifier);
	}
	
	/** Add the subscriber to the list of listeners to room changes */
	public void subscribe(ContextEventListener listener, Room room) {
		log.info(listener.toString() + " subscribed to context event related to location=" + room);
		ArrayList<ContextEventListener> list = listenersMap.get(room); 
		if(list == null) {
			list = new ArrayList<ContextEventListener>();
			list.add(listener);			
		}else if(list.indexOf(listener) == -1){
			list.add(listener);
		}
		listenersMap.put(room, list);
		if(listeners.contains(listener) == false)
			listeners.add(listener);
	}
	
	/** Notify listeners about the room changes for an agent */
	public void notifyListeners(Room room, String agent) {
		ArrayList<ContextEventListener> list = listenersMap.get(room); 
		if(list != null) {
			log.info("Sending context event related to context (location=" + room + ") changes of entity " + agent);			
			for(Iterator<ContextEventListener> it = list.iterator(); it.hasNext(); ) {
				ContextEventListener listener = it.next();
				listener.notifyListener(new ContextEvent(new Peer<String, Room>(agent, room)));
			}
		}
	}
	
	public void notifyAllListeners(Room room, String agent) {
		log.info("Sending context event related to context (location=" + room + ") changes of entity " + agent);
		for(Iterator<ContextEventListener> it = listeners.iterator(); it.hasNext(); ) {
			ContextEventListener listener = it.next();
			listener.notifyListener(new ContextEvent(new Peer<String, Room>(agent, room)));
		}
	}

	public void publish(Context c) {
		
	}
		
	public Context request() {
		return null;
	}
	
}
