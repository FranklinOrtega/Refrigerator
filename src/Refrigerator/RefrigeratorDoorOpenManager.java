package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class RefrigeratorDoorOpenManager {
	private EventListenerList listenerList = new EventListenerList();
	private static RefrigeratorDoorOpenManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private RefrigeratorDoorOpenManager() {
	}
	
	public static RefrigeratorDoorOpenManager instance() {
		if (instance == null) {
			instance = new RefrigeratorDoorOpenManager();
		}
		return instance;
	}
	
	public void addDoorOpenListener(RefrigeratorDoorOpenListener listener) {
		listenerList.add(RefrigeratorDoorOpenListener.class, listener);
	}
	
	public void removeDoorOpenListener(RefrigeratorDoorOpenListener listener) {
		listenerList.remove(RefrigeratorDoorOpenListener.class, listener);
	}
	
	public void processEvent(RefrigeratorDoorOpenEvent event) {
		EventListener[] listeners = listenerList.getListeners(RefrigeratorDoorOpenListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((RefrigeratorDoorOpenListener) listeners[i]).doorOpened(event);
		}
	}	
}
