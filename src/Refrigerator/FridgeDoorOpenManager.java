package Refrigerator;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeDoorOpenManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeDoorOpenManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorOpenManager() {
	}
	
	public static FridgeDoorOpenManager instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenManager();
		}
		return instance;
	}
	
	public void addDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.add(FridgeDoorOpenListener.class, listener);
	}
	
	public void removeDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.remove(FridgeDoorOpenListener.class, listener);
	}
	
	public void processEvent(FridgeDoorOpenEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeDoorOpenListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeDoorOpenListener) listeners[i]).doorOpened(event);
		}
	}
}
