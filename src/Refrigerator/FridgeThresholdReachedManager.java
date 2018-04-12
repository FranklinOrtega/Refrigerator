package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

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
	
	public void addFridgeThresholdReachedListener(FridgeThresholdReachedListener listener) {
		listenerList.add(FridgeThresholdReachedListener.class, listener);
	}
	
	public void removeFridgeThresholdReached(FridgeThresholdReachedListener listener) {
		listenerList.remove(FridgeThresholdReachedListener.class, listener);
	}
	
	public void processEvent(FridgeThresholdReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeThresholdReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeThresholdReachedListener) listeners[i]).fridgeThresholdReached(event);
		}
	}


}
