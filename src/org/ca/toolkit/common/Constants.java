package org.ca.toolkit.common;

import java.awt.Point;
import java.util.Map;
import java.util.HashMap;

public class Constants {

	/** Used by GeoTagService to determine the max distance between the message location and current agent location to be considered for notifying this agent about the nearby message */
	public static final int range = 10;
	
	/** Variable used to configure the simulation speed (for how long does the thread sleep) */
	public static final int STEP = 100;
	
	public enum PlaceName {MEETING_ROOM, DESK, TOILET}
	
	public enum Room {ROOM_MEETING, HALL, TOILET_TOP, TOILET_DOWN, 
		ROOM_DOWN_RIGHT, ROOM_DOWN_MIDDLE_RIGHT, ROOM_DOWN_MIDDLE_LEFT, ROOM_DOWN_LEFT,
		ROOM_TOP_RIGHT, ROOM_TOP_MDDILE_RIGHT, ROOM_BREAK}
	
	public enum AgentActivity {GOING_2_DESK, GOING_2_TOILET, GOING_2_MEETING, 
		GOING_2_BREAK, IN_BREAK, AT_DESK, IN_TOILET, ENTERING_TOILET,
		AT_BOARD, GOING_TO_BOARD, RESTING, LEAVING_WORK, 
		POST_TAG}
	
	public enum WorldActivity {IS_NIGHT, IS_DAY, GOING_2_DARK, GOING_2_BRIGHT}
	
	/** Main points where an agent could be placed*/
	
	/** Point related to the main gate or building entrance */
	public static final Point POINT_MAIN_GATE = new Point(700, 275);
	
	public static final Point POINT_IN_MIDDLE = new Point(300, 450);
	
	/** Points related to toilets */
	public static final Point POINT_TOILET_TOP = new Point(75, 300);
	public static final Point POINT_GATE_TOILET_TOP = new Point(150, 300);
	public static final Point POINT_TOILET_DOWN = new Point(75, 375);
	public static final Point POINT_GATE_TOILET_DOWN = new Point(150, 350);
	
	/** Points related to the meeting room */
	public static final Point POINT_MEETING_1 = new Point(150, 100);
	public static final Point POINT_MEETING_2 = new Point(100, 150);
	public static final Point POINT_MEETING_3 = new Point(250, 150);
	public static final Point POINT_MEETING_4 = new Point(200, 200);
	public static final Point POINT_MEETING_5 = new Point(100, 225);
	public static final Point POINT_MEETING_6 = new Point(250, 50);
	public static final Point POINT_GATE_MEETING = new Point(250, 250);
	
	/** Points related to the room left or the entrance or right down */
	public static final Point POINT_ROOM_DOWN_RIGHT_1 = new Point(650, 450);
	public static final Point POINT_ROOM_DOWN_RIGHT_2 = new Point(600, 550);
	public static final Point POINT_ROOM_DOWN_RIGHT_3 = new Point(550, 550);
	public static final Point POINT_GATE_ROOM_DOWN_RIGHT = new Point(550, 400);
		
	/** Points related to the room down middle right */
	public static final Point POINT_ROOM_DOWN_MIDDLE_RIGHT_1 = new Point(450, 450);
	public static final Point POINT_ROOM_DOWN_MIDDLE_RIGHT_2 = new Point(400, 450);
	public static final Point POINT_ROOM_DOWN_MIDDLE_RIGHT_3 = new Point(500, 500);
	public static final Point POINT_ROOM_DOWN_MIDDLE_RIGHT_4 = new Point(400, 500);
	public static final Point POINT_GATE_ROOM_DOWN_MIDDLE_RIGHT = new Point(500, 400);
	
	/** Points related to the room down middle left */
	public static final Point POINT_ROOM_DOWN_MIDDLE_LEFT_1 = new Point(250, 450);
	public static final Point POINT_ROOM_DOWN_MIDDLE_LEFT_2 = new Point(250, 500);
	public static final Point POINT_ROOM_DOWN_MIDDLE_LEFT_3 = new Point(300, 500);
	public static final Point POINT_GATE_ROOM_DOWN_MIDDLE_LEFT = new Point(350, 400);
	
	/** Points related to room down left */
	public static final Point POINT_ROOM_DOWN_LEFT_1 = new Point(100, 450);
	public static final Point POINT_ROOM_DOWN_LEFT_2 = new Point(50, 450);
	public static final Point POINT_ROOM_DOWN_LEFT_3 = new Point(50, 500);
	public static final Point POINT_ROOM_DOWN_LEFT_4 = new Point(150, 500);
	public static final Point POINT_GATE_ROOM_DOWN_LEFT = new Point(150, 400);
	
	/** Points related to room up right */
	public static final Point POINT_ROOM_UP_RIGHT_1 = new Point(650, 50);
	public static final Point POINT_GATE_ROOM_UP_RIGHT = new Point(550, 175);
		
	/** Points related to room up middle right */
	public static final Point POINT_ROOM_UP_MIDDLE_RIGHT_1 = new Point(450, 50);
	public static final Point POINT_ROOM_UP_MIDDLE_RIGHT_2 = new Point(450, 150);
	public static final Point POINT_GATE_ROOM_UP_MIDDLE_RIGHT = new Point(425, 175);
	
	/** Points related to break room */
	public static final Point POINT_ROOM_BREAK_1 = new Point(350, 50);
	public static final Point POINT_ROOM_BREAK_2 = new Point(350, 100);
	public static final Point POINT_ROOM_BREAK_3 = new Point(350, 150);
	public static final Point POINT_GATE_ROOM_BREAK = new Point(375, 175);
	
	/** A mapping between any places where an agent can stand in a given room and the corresponding gate */
	public static final Map<Point, Point> roomToGate = new HashMap<Point, Point>(); 
	static{
		// gate for break room
		roomToGate.put(POINT_ROOM_BREAK_1, POINT_GATE_ROOM_BREAK);
		roomToGate.put(POINT_ROOM_BREAK_2, POINT_GATE_ROOM_BREAK);
		roomToGate.put(POINT_ROOM_BREAK_3, POINT_GATE_ROOM_BREAK);
		// gate for down left room
		roomToGate.put(POINT_ROOM_DOWN_LEFT_1, POINT_GATE_ROOM_DOWN_LEFT);
		roomToGate.put(POINT_ROOM_DOWN_LEFT_2, POINT_GATE_ROOM_DOWN_LEFT);
		roomToGate.put(POINT_ROOM_DOWN_LEFT_3, POINT_GATE_ROOM_DOWN_LEFT);
		roomToGate.put(POINT_ROOM_DOWN_LEFT_4, POINT_GATE_ROOM_DOWN_LEFT);
		// gate for down middle left room
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_LEFT_1, POINT_GATE_ROOM_DOWN_MIDDLE_LEFT);
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_LEFT_2, POINT_GATE_ROOM_DOWN_MIDDLE_LEFT);
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_LEFT_3, POINT_GATE_ROOM_DOWN_MIDDLE_LEFT);
		// gate for down middle right room
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_1, POINT_GATE_ROOM_DOWN_MIDDLE_RIGHT);
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_2, POINT_GATE_ROOM_DOWN_MIDDLE_RIGHT);
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_3, POINT_GATE_ROOM_DOWN_MIDDLE_RIGHT);
		roomToGate.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_4, POINT_GATE_ROOM_DOWN_MIDDLE_RIGHT);
		// gate for up down right room
		roomToGate.put(POINT_ROOM_DOWN_RIGHT_1, POINT_GATE_ROOM_DOWN_RIGHT);
		roomToGate.put(POINT_ROOM_DOWN_RIGHT_2, POINT_GATE_ROOM_DOWN_RIGHT);
		roomToGate.put(POINT_ROOM_DOWN_RIGHT_3, POINT_GATE_ROOM_DOWN_RIGHT);
		// gate for up middle right room
		roomToGate.put(POINT_ROOM_UP_MIDDLE_RIGHT_1, POINT_GATE_ROOM_UP_MIDDLE_RIGHT);
		roomToGate.put(POINT_ROOM_UP_MIDDLE_RIGHT_2, POINT_GATE_ROOM_UP_MIDDLE_RIGHT);
		// gate for up right room
		roomToGate.put(POINT_ROOM_UP_RIGHT_1, POINT_GATE_ROOM_UP_RIGHT);
		// gate for toilets
		roomToGate.put(POINT_TOILET_DOWN, POINT_GATE_TOILET_DOWN);
		roomToGate.put(POINT_TOILET_TOP, POINT_GATE_TOILET_TOP);
		// gate for meeting room
		roomToGate.put(POINT_MEETING_1, POINT_GATE_MEETING);
		roomToGate.put(POINT_MEETING_2, POINT_GATE_MEETING);
		roomToGate.put(POINT_MEETING_3, POINT_GATE_MEETING);
		roomToGate.put(POINT_MEETING_4, POINT_GATE_MEETING);
		roomToGate.put(POINT_MEETING_5, POINT_GATE_MEETING);
		roomToGate.put(POINT_MEETING_6, POINT_GATE_MEETING);
		// gate of the building
		roomToGate.put(POINT_MAIN_GATE, POINT_MAIN_GATE);		
	};
	
	/** A mapping between any places where an agent can stand in a given room and the corresponding room name */
	public static final Map<Point, Room> xyToRoom = new HashMap<Point, Room>(); 
	static{
		xyToRoom.put(POINT_MEETING_1, Room.ROOM_MEETING);
		xyToRoom.put(POINT_MEETING_2, Room.ROOM_MEETING);
		xyToRoom.put(POINT_MEETING_3, Room.ROOM_MEETING);
		xyToRoom.put(POINT_MEETING_4, Room.ROOM_MEETING);
		xyToRoom.put(POINT_MEETING_5, Room.ROOM_MEETING);
		xyToRoom.put(POINT_MEETING_6, Room.ROOM_MEETING);
		
		xyToRoom.put(POINT_TOILET_DOWN, Room.TOILET_DOWN);
		xyToRoom.put(POINT_TOILET_TOP, Room.TOILET_TOP);
		
		xyToRoom.put(POINT_ROOM_BREAK_1, Room.ROOM_BREAK);
		xyToRoom.put(POINT_ROOM_BREAK_2, Room.ROOM_BREAK);
		xyToRoom.put(POINT_ROOM_BREAK_3, Room.ROOM_BREAK);
		
		xyToRoom.put(POINT_ROOM_DOWN_LEFT_1, Room.ROOM_DOWN_LEFT);
		xyToRoom.put(POINT_ROOM_DOWN_LEFT_2, Room.ROOM_DOWN_LEFT);
		xyToRoom.put(POINT_ROOM_DOWN_LEFT_3, Room.ROOM_DOWN_LEFT);
		xyToRoom.put(POINT_ROOM_DOWN_LEFT_4, Room.ROOM_DOWN_LEFT);
		
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_LEFT_1, Room.ROOM_DOWN_MIDDLE_LEFT);
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_LEFT_2, Room.ROOM_DOWN_MIDDLE_LEFT);
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_LEFT_3, Room.ROOM_DOWN_MIDDLE_LEFT);
		
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_1, Room.ROOM_DOWN_MIDDLE_RIGHT);
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_2, Room.ROOM_DOWN_MIDDLE_RIGHT);
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_3, Room.ROOM_DOWN_MIDDLE_RIGHT);
		xyToRoom.put(POINT_ROOM_DOWN_MIDDLE_RIGHT_4, Room.ROOM_DOWN_MIDDLE_RIGHT);
		
		xyToRoom.put(POINT_ROOM_DOWN_RIGHT_1, Room.ROOM_DOWN_RIGHT);
		xyToRoom.put(POINT_ROOM_DOWN_RIGHT_2, Room.ROOM_DOWN_RIGHT);
		xyToRoom.put(POINT_ROOM_DOWN_RIGHT_3, Room.ROOM_DOWN_RIGHT);
		
		xyToRoom.put(POINT_ROOM_UP_MIDDLE_RIGHT_1, Room.ROOM_TOP_MDDILE_RIGHT);
		xyToRoom.put(POINT_ROOM_UP_MIDDLE_RIGHT_2, Room.ROOM_TOP_MDDILE_RIGHT);
		
		xyToRoom.put(POINT_ROOM_UP_RIGHT_1, Room.ROOM_TOP_RIGHT);
		
	}
}
