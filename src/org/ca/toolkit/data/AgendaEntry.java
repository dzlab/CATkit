package org.ca.toolkit.data;

import java.util.List;
import java.util.ArrayList;

import org.ca.toolkit.model.Context;
import org.ca.toolkit.model.Entity;


public class AgendaEntry extends Context {
	
	private static final long serialVersionUID = -3165794386796252146L;
	
	// data related to an agenda entry
	protected String subject;
	protected long startTime;
	protected long endTime;
	protected String object;
	protected List<Entity> participants;
	protected State currentState;
	
	public enum State {
	    CREATED, UPCOMING, STARTED, ENDED, CANCELED 
	}
	
	//public class Event implements ContextEvent
	
	public AgendaEntry() {
		participants = new ArrayList<Entity>();
	}

	
}
