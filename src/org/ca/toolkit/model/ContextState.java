/**
 * 
 */
package org.ca.toolkit.model;

/**
 * @author hjwk9387
 *
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class ContextState {

	Long _ID;
	String _name;
	Map<String, ContextTransition> _outTransitions;
	
	public ContextState() {
		initialize();
	}
	
	private void initialize() {
		_outTransitions = new HashMap<String, ContextTransition>();
	}
	
	public void setID(Long id) {
		_ID = id;
	}
	public Long getID() {
		return _ID;
	}
	
	public void addTransition(String label, ContextState destination) {
		_outTransitions.put(label, new ContextTransition(label, this, destination));
	}

	public Collection<ContextTransition> getOutTransitions() {
		return _outTransitions.values();
	}
	/** Code executed when FSM is entering this state */
	public void enter(FSM fsm) {
		fsm.setCurrentState(this);
	}
	
	/***/
	public void execute(FSM fsm) {
		leave(fsm);
	}
	
	/***/
	public void leave(FSM fsm) {
		Collection<ContextTransition> transitions = getOutTransitions();
		Iterator<ContextTransition> it = transitions.iterator();
		if(it.hasNext()) {
			it.next().take(fsm);
		}
	}
}
