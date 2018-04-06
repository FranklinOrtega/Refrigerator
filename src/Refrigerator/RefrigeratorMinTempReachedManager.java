package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class RefrigeratorMinTempReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static RefrigeratorMinTempReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private RefrigeratorMinTempReachedManager() {
	}
	
	public static RefrigeratorMinTempReachedManager instance() {
		if (instance == null) {
			instance = new RefrigeratorMinTempReachedManager();
		}
		return instance;
	}
	
	public void addMinTempReachedListener(RefrigeratorMinTempReachedListener listener) {
		listenerList.add(RefrigeratorMinTempReachedListener.class, listener);
	}
	
	public void removeMinTempReachedListener(RefrigeratorMinTempReachedListener listener) {
		listenerList.remove(RefrigeratorMinTempReachedListener.class, listener);
	}
	
	public void processEvent(RefrigeratorMinTempReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(RefrigeratorMinTempReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((RefrigeratorMinTempReachedListener) listeners[i]).minTempReached(event);
		}
	}

}
