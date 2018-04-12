package Refrigerator;

import java.util.EventObject;

/*
 * Author: Vanessa Esaw
 * This event is to account for event in which system updates the temperature
 * based on the rate given. 
 * 
 * Note that it will: 
 * warm or cool the temperature, based on the state that registers with the manager for this event
 * rate of warming/cooling is based on the states 'initalized' conditions in the run method
 * */
public class FridgeTimerRanOutEvent extends EventObject {
	public FridgeTimerRanOutEvent(Object source) {
		super(source);
	}

}
