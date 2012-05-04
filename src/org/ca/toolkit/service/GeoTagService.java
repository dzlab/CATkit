/**
 * 
 */
package org.ca.toolkit.service;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

import org.ca.toolkit.common.Peer;
import org.ca.toolkit.common.Constants;
import org.ca.toolkit.common.Constants.Room;
import org.ca.toolkit.model.ContextEvent;
import org.ca.toolkit.model.ContextEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author hjwk9387
 *
 */
public class GeoTagService implements ContextEventListener {

	static Logger log = LoggerFactory.getLogger(GeoTagService.class);
	
	public static GeoTagService _instance;
	public static GeoTagService getInstance() {
		if(_instance == null)
			_instance = new GeoTagService();
		return _instance;
	}
	
	public List<Peer<Point, String>> _geotags;
	public HashMap<String, Room> hlLocation;
	
	private GeoTagService() {
		_geotags = new ArrayList<Peer<Point,String>>();
		hlLocation = new HashMap<String, Room>();
	}
	
	/** Get the default image of a geo-tag */
	public static BufferedImage getImage() {
		BufferedImage image = null;	
		try {
			image = ImageIO.read(new File(".\\res\\geotag.png"));		
		}catch (IOException ioe) {			
			ioe.printStackTrace();		
		}
		return image;
	}
	
	/** Called by an agent to post a message at its current location */
	public void postGetTag(Point location, String tag) {		
		_geotags.add(new Peer<Point, String>(new Point(location), tag));
		Room room = Constants.xyToRoom.get(location);
		BrokerService.getInstance().subscribe(getInstance(), room);
	}
	
	/** Agents call this method to check if nearby there is any posted message */
	public List<String> getTagForLocation(Point location) {
		List<String> tags = new ArrayList<String>();
		for(Iterator<Peer<Point, String>> it = _geotags.iterator(); it.hasNext(); ) {
			Peer<Point, String> p = it.next();
			if(Math.abs(p.key.x - location.x) <= Constants.range && Math.abs(p.key.y - location.y) <= Constants.range)
				tags.add(p.value);
		}
		return tags;
	}
	
	/** Called to remove a tag posted in a given location */
	public boolean removeTagbyLocation(Point location) {
		boolean done = false;
		
		for(Iterator<Peer<Point, String>> it = _geotags.iterator(); it.hasNext() && !done; ) {
			Peer<Point, String> p = it.next();
			if(Math.abs(p.key.x - location.x) <= Constants.range && Math.abs(p.key.y - location.y) <= Constants.range) {
				it.remove();
				done = true;
			}
		}		
		return done;
	}

	/** Called by {@link BrokerService} to notify this consumer about the availability of contextual information */
	@SuppressWarnings("unchecked")
	@Override
	public void notifyListener(ContextEvent e) {
		Peer<String, Room> peer = (Peer<String, Room>) e.data;
		log.info("A context event received for entity " + peer.key);
		log.info("location information is " + peer.value);
		hlLocation.put(peer.key, peer.value);		
	}

	/** @return {@link List} of messages to be posted on a given location */
	public List<Point> getDisplayableMessages() {
		List<Point> list = new ArrayList<Point>();
		for(Iterator<Peer<Point, String>> it = _geotags.iterator(); it.hasNext(); ) {
			Peer<Point, String> peer = it.next();
			Room room = Constants.xyToRoom.get(peer.key);
			if(hlLocation.containsValue(room) == true) {
				list.add(peer.key);
			}
		}
		if(list.isEmpty())
			return null;
		return list;		
	}
	
	public String toString() {
		return GeoTagService.class.getSimpleName();
	}
}
