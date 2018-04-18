package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;
/**
 * Manager class for the FridgeDoorClose event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FridgeDoorClose manager is called when the event occurs, 
 * FridgeDoorCloseManager notifies the list of listeners by calling
 * their appropriate doorClosed() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FridgeDoorCloseManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeDoorCloseManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorCloseManager() {
	}
	
	public static FridgeDoorCloseManager instance() {
		if (instance == null) {
			instance = new FridgeDoorCloseManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FridgeDoorCloseManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.add(FridgeDoorCloseListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FridgeDoorCloseManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.remove(FridgeDoorCloseListener.class, listener);
	}
	
	/**
	 * Called when FridgeDoorClose event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their doorClosed() method to take appropriate action 
	 * */
	public void processEvent(FridgeDoorCloseEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeDoorCloseListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeDoorCloseListener) listeners[i]).doorClosed(event);
		}
	}	
}
