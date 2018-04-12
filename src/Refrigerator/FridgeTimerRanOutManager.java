package Refrigerator;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

public class FridgeTimerRanOutManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FridgeTimerRanOutManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeTimerRanOutManager() {
	}
	
	public static FridgeTimerRanOutManager instance() {
		if (instance == null) {
			instance = new FridgeTimerRanOutManager();
		}
		return instance;
	}
	
	public void addFridgeTimerRanOutListener(FridgeTimerRanOutListener listener) {
		listenerList.add(FridgeTimerRanOutListener.class, listener);
	}
	
	public void removeFridgeTimerRanOut(FridgeTimerRanOutListener listener) {
		listenerList.remove(FridgeTimerRanOutListener.class, listener);
	}
	
	public void processEvent(FridgeTimerRanOutEvent event) {
		EventListener[] listeners = listenerList.getListeners(FridgeTimerRanOutListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FridgeTimerRanOutListener) listeners[i]).fridgeTimerRanOut(event);
		}
	}


}




