package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;
/**
 * Manager class for the FridgeDoorOpen event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FridgeDoorOpen manager is called when the event occurs, 
 * FridgeDoorOpenManager notifies the list of listeners by calling
 * their appropriate doorOpened() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FridgeDoorOpenManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeDoorOpenManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorOpenManager() {
	}
	
	public static FridgeDoorOpenManager instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FridgeDoorOpenManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.add(FridgeDoorOpenListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FridgeDoorOpenManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.remove(FridgeDoorOpenListener.class, listener);
	}
	
	/**
	 * Called when ThresholdReached event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their doorOpened() method to take appropriate action 
	 * */
	public void processEvent(FridgeDoorOpenEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeDoorOpenListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeDoorOpenListener) listeners[i]).doorOpened(event);
		}
	}
}
