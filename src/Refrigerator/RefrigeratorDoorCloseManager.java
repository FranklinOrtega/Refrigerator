package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class RefrigeratorDoorCloseManager {
	private EventListenerList listenerList = new EventListenerList();
	private static RefrigeratorDoorCloseManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private RefrigeratorDoorCloseManager() {
	}
	
	public static RefrigeratorDoorCloseManager instance() {
		if (instance == null) {
			instance = new RefrigeratorDoorCloseManager();
		}
		return instance;
	}
	
	public void addDoorCloseListener(RefrigeratorDoorCloseListener listener) {
		listenerList.add(RefrigeratorDoorCloseListener.class, listener);
	}
	
	public void removeDoorOpenListener(RefrigeratorDoorCloseListener listener) {
		listenerList.remove(RefrigeratorDoorCloseListener.class, listener);
	}
	
	public void processEvent(RefrigeratorDoorCloseEvent event) {
		EventListener[] listeners = listenerList.getListeners(RefrigeratorDoorCloseListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((RefrigeratorDoorCloseListener) listeners[i]).doorClosed(event);
		}
	}	
}
