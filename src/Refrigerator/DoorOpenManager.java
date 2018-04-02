package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class DoorOpenManager {
	private EventListenerList listenerList = new EventListenerList();
	private static DoorOpenManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private DoorOpenManager() {
	}
	
	public static DoorOpenManager instance() {
		if (instance == null) {
			instance = new DoorOpenManager();
		}
		return instance;
	}
	
	public void addDoorOpenListener(DoorOpenListener listener) {
		listenerList.add(DoorOpenListener.class, listener);
	}
	
	public void removeDoorOpenListener(DoorOpenListener listener) {
		listenerList.remove(DoorOpenListener.class, listener);
	}
	
	public void processEvent(DoorOpenEvent event) {
		EventListener[] listeners = listenerList.getListeners(DoorOpenListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((DoorOpenListener) listeners[i]).doorOpened(event);
		}
	}	
}
