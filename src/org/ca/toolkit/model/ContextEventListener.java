package org.ca.toolkit.model;

import java.util.EventListener;

public interface ContextEventListener extends EventListener {
	
	void notifyListener(ContextEvent e);
	
	String toString();
}
