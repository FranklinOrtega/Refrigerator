package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeMaxTempReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeMaxTempReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeMaxTempReachedManager() {
	}
	
	public static FridgeMaxTempReachedManager instance() {
		if (instance == null) {
			instance = new FridgeMaxTempReachedManager();
		}
		return instance;
	}
	
	public void addMaxTempReachedListener(FridgeMaxTempReachedListener listener) {
		listenerList.add(FridgeMaxTempReachedListener.class, listener);
	}
	
	public void removeMinTempReachedListener(FridgeMaxTempReachedListener listener) {
		listenerList.remove(FridgeMaxTempReachedListener.class, listener);
	}
	
	public void processEvent(FridgeMaxTempReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeMaxTempReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeMaxTempReachedListener) listeners[i]).maxTempReached(event);
		}
	}

}
