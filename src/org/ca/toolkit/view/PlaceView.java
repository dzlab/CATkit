package org.ca.toolkit.view;

import java.awt.Point;

import org.ca.toolkit.common.Constants.PlaceName;



public class PlaceView {

	protected String identifier;
	protected Point point;	
	protected PlaceName name;
	
	public PlaceView() {
		
	}
	
	public PlaceView(String id, Point pt, PlaceName type) {
		identifier = id;
		point = pt;
		name = type;
	}
}
