/**
 * 
 */
package org.ca.toolkit.common;

/**
 * @author hjwk9387
 *
 */
public class VirtualTime {

	protected int hour;
	protected int minute;
	
	public VirtualTime() {
		hour = 0;
		minute = 0;
	}
	
	public VirtualTime(int h, int m) {
		hour = h;
		minute = m;
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int h) {
		hour = h;
	}
	
	public int getMinute() {
		return minute;
	}
	public void setMinute(int m) {
		minute = m;
	}
	
	/** Increment the time 
	 * @param h hours
	 * @param m minutes
	 * */
	public void increment(int h, int m) {
		hour += h;
		minute += m;
		if(minute == 60) {
			minute = 0;
			hour += 1;
		}
		if(hour == 24)
			hour = 0;
	}
	
	public boolean equals(VirtualTime time) {
		return (time.hour==hour && time.minute==minute);
	}
	public String toString() {
		return hour + ":" + minute;
	}
}
