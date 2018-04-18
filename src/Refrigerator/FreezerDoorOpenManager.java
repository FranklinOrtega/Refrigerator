package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;
/**
 * Manager class for the FreezerDoorOpen event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FreezerDoorOpen manager is called when the event occurs, 
 * FreezerDoorOpenManager notifies the list of listeners by calling
 * their appropriate doorOpened() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FreezerDoorOpenManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FreezerDoorOpenManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerDoorOpenManager() {
	}
	
	public static FreezerDoorOpenManager instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FreezerDoorOpenManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addDoorOpenListener(FreezerDoorOpenListener listener) {
		listenerList.add(FreezerDoorOpenListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FreezerDoorOpenManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeDoorOpenListener(FreezerDoorOpenListener listener) {
		listenerList.remove(FreezerDoorOpenListener.class, listener);
	}
	
	/**
	 * Called when ThresholdReached event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their doorOpened() method to take appropriate action 
	 * */
	public void processEvent(FreezerDoorOpenEvent event) {
		EventListener[] listeners = listenerList.getListeners(FreezerDoorOpenListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FreezerDoorOpenListener) listeners[i]).doorOpened(event);
		}
	}
}
