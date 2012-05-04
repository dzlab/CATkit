/**
 * 
 */
package org.ca.toolkit.model;

/**
 * @author hjwk9387
 *
 */

import java.util.List;
import java.util.ArrayList;

public class StateContainer {

	String _name;
	List<ContextState> _states;
	
	public StateContainer() {
		
		initialize();
	}
	
	private void initialize() {
		_name = this.toString();
		_states = new ArrayList<ContextState>();
	}
	
	public List<ContextState> getStates() {
		return _states;
	}
	public void setStates(List<ContextState> states) {
		_states = states;
	}
	
	/** Add new state to this container */
	public void addState(ContextState state) {
		if(getStates() == null)
			setStates(new ArrayList<ContextState>());
		getStates().add(state);
	}
	
	/** get the state with the identifier given as parameter */
	public ContextState getState(Long id) {
		for(ContextState state: getStates()) {
			if(state.getID() == id)
				return state;
		}
		return null;
	}

}
