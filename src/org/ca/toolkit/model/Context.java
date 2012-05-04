package org.ca.toolkit.model;


import java.io.Serializable;

import javax.swing.event.EventListenerList;

public class Context implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private transient EventListenerList _listeners;
	protected State currentState;
	
	public enum State {} 
	
	public Context() {
		
		// initiating collections
		readResolve();
	}
	
	/** Method called at de-serialization. */
	private Context readResolve() {
		_listeners = new EventListenerList();
		return this;
	}
	
	/** Add an listener */
	public void addEventListener(ContextEventListener listener) {	
		_listeners.add(ContextEventListener.class, listener);
	}
	
	/** Remove an listener */
	public void removeEventListener(ContextEventListener listener) {
		_listeners.remove(ContextEventListener.class, listener);
	}
	
	/** Get all listeners of type {@link java.util.EventListener}*/
	public ContextEventListener[] getAllEventListener() {
		return _listeners.getListeners(ContextEventListener.class);
	}
	
	/** Notify all listeners about the occurrence of an event */
	protected void notifyAllEventListener() {
		for(ContextEventListener listener : getAllEventListener()) {
			ContextEvent event = new ContextEvent();
			listener.notifyListener(event);
		}
	}
}
