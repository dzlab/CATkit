/**
 * 
 */
package org.ca.toolkit.model;

/**
 * @author hjwk9387
 *
 */
public class ContextTransition {

	String _label;
	ContextState _source;
	ContextState _destination;
	
	public ContextTransition(String label, ContextState source, ContextState destination) {
		_label = label;
		_source = source;
		_destination = destination;
	}
	
	public void take(FSM fsm) {
		
	}
}
