/**
 * 
 */
package org.ca.toolkit.model;

/**
 * @author hjwk9387
 *
 */
public class FSM {

	private StateContainer _container;
	private ContextState _currentState;
	
	public FSM(StateContainer container) {
		this._container = container;
		this._currentState = container.getStates().get(0);
	}
	
	public void setCurrentState(ContextState state) {
		_currentState = state;
	}
	public ContextState getCurrentState() {
		return _currentState;
	}
	
	/** start the abstraction operation after reception of new context from corresponding provider */
	public void Start() {
		
	}
}
