package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeDoorCloseManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeDoorCloseManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorCloseManager() {
	}
	
	public static FridgeDoorCloseManager instance() {
		if (instance == null) {
			instance = new FridgeDoorCloseManager();
		}
		return instance;
	}
	
	public void addDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.add(FridgeDoorCloseListener.class, listener);
	}
	
	public void removeDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.remove(FridgeDoorCloseListener.class, listener);
	}
	
	public void processEvent(FridgeDoorCloseEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeDoorCloseListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeDoorCloseListener) listeners[i]).doorClosed(event);
		}
	}	
}
