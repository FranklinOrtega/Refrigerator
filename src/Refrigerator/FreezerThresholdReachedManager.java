package Refrigerator;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * Manager class for the FreezerThresholdReached event. 
 * In charge of maintaining list of states that are listeners to 
 * event of threshold temperature being reached.
 * 
 * When FreezerThresholdReached manager is called when the event occurs, 
 * FreezerThresholdReached notifies the list of listeners by calling
 * their appropriate freezerThresholdReached() method to carry out 
 * an appropriate action based on the state that is listening 
 * */
public class FreezerThresholdReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FreezerThresholdReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerThresholdReachedManager() {
	}
	
	public static FreezerThresholdReachedManager instance() {
		if (instance == null) {
			instance = new FreezerThresholdReachedManager();
		}
		return instance;
	}
	
	/**
	 * Called to register a listener to the FridgeThresholdReachedManager.
	 * Is called when state is 'initialized' when it calls its run() method 
	 * */
	public void addFreezerThresholdReachedListener(FreezerThresholdReachedListener listener) {
		listenerList.add(FreezerThresholdReachedListener.class, listener);
	}
	
	/**
	 * Called to unregister a listener to the FridgeThresholdReachedManager.
	 * Is called when a state transitions to another one, and calls its leave()
	 * method
	 * */
	public void removeFreezerThresholdReachedListener(FreezerThresholdReachedListener listener) {
		listenerList.remove(FreezerThresholdReachedListener.class, listener);
	}
	
	/**
	 * Called when ThresholdReached event occurs.
	 * Is called when state event occurs and manager notifies all listeners by calling
	 * their fridgeThresholdReached() method to take appropriate action 
	 * */
	public void processEvent(FreezerThresholdReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(FreezerThresholdReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FreezerThresholdReachedListener) listeners[i]).freezerThresholdReached(event);
		}
	}
}

