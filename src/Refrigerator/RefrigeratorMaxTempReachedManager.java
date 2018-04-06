package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class RefrigeratorMaxTempReachedManager {
	private EventListenerList listenerList = new EventListenerList();
	private static RefrigeratorMaxTempReachedManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private RefrigeratorMaxTempReachedManager() {
	}
	
	public static RefrigeratorMaxTempReachedManager instance() {
		if (instance == null) {
			instance = new RefrigeratorMaxTempReachedManager();
		}
		return instance;
	}
	
	public void addMaxTempReachedListener(RefrigeratorMaxTempReachedListener listener) {
		listenerList.add(RefrigeratorMaxTempReachedListener.class, listener);
	}
	
	public void removeMinTempReachedListener(RefrigeratorMaxTempReachedListener listener) {
		listenerList.remove(RefrigeratorMaxTempReachedListener.class, listener);
	}
	
	public void processEvent(RefrigeratorMaxTempReachedEvent event) {
		EventListener[] listeners = listenerList.getListeners(RefrigeratorMaxTempReachedListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((RefrigeratorMaxTempReachedListener) listeners[i]).maxTempReached(event);
		}
	}

}
