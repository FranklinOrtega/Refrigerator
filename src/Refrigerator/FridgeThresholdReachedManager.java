package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;
/**
 * Manager class for the FridgeThresholdReached event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FridgeThresholdReaches manager is called when the event occurs, 
 * FridgeThresholdManager notifies the list of listeners by calling
 * their appropriate fridgeThresholdReached() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FridgeThresholdReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeThresholdReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeThresholdReachedManager() {
	}
	
	public static FridgeThresholdReachedManager instance() {
		if (instance == null) {
			instance = new FridgeThresholdReachedManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FridgeThresholdReachedManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addFridgeThresholdReachedListener(FridgeThresholdReachedListener listener) {
		listenerList.add(FridgeThresholdReachedListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FridgeThresholdReachedManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeFridgeThresholdReached(FridgeThresholdReachedListener listener) {
		listenerList.remove(FridgeThresholdReachedListener.class, listener);
	}
	
	/**
	 * Called when ThresholdReached event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their fridgeThresholdReached() method to take appropriate action 
	 * */
	public void processEvent(FridgeThresholdReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeThresholdReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeThresholdReachedListener) listeners[i]).fridgeThresholdReached(event);
		}
	}
}
