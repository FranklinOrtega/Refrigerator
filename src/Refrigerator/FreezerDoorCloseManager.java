package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;
/**
 * Manager class for the FreezerDoorClose event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FreezerDoorClose manager is called when the event occurs, 
 * FreezerDoorCloseManager notifies the list of listeners by calling
 * their appropriate doorClosed() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FreezerDoorCloseManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FreezerDoorCloseManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerDoorCloseManager() {
	}
	
	public static FreezerDoorCloseManager instance() {
		if (instance == null) {
			instance = new FreezerDoorCloseManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FreezerDoorCloseManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addDoorCloseListener(FreezerDoorCloseListener listener) {
		listenerList.add(FreezerDoorCloseListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FreezerDoorCloseManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeDoorCloseListener(FreezerDoorCloseListener listener) {
		listenerList.remove(FreezerDoorCloseListener.class, listener);
	}
	
	/**
	 * Called when FreezerDoorClose event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their doorClosed() method to take appropriate action 
	 * */
	public void processEvent(FreezerDoorCloseEvent event) {
		EventListener[] listeners = listenerList.getListeners(FreezerDoorCloseListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FreezerDoorCloseListener) listeners[i]).doorClosed(event);
		}
	}	
}
