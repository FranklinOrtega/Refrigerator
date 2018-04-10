package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeMinTempReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeMinTempReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeMinTempReachedManager() {
	}
	
	public static FridgeMinTempReachedManager instance() {
		if (instance == null) {
			instance = new FridgeMinTempReachedManager();
		}
		return instance;
	}
	
	public void addMinTempReachedListener(FridgeMinTempReachedListener listener) {
		listenerList.add(FridgeMinTempReachedListener.class, listener);
	}
	
	public void removeMinTempReachedListener(FridgeMinTempReachedListener listener) {
		listenerList.remove(FridgeMinTempReachedListener.class, listener);
	}
	
	public void processEvent(FridgeMinTempReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeMinTempReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeMinTempReachedListener) listeners[i]).minTempReached(event);
		}
	}

}
