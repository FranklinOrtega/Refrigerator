package Refrigerator;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

public class FreezerTimerRanOutManager {
	private EventListenerList listenerList = new EventListenerList();
	private static FreezerTimerRanOutManager instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerTimerRanOutManager() {
	}
	
	public static FreezerTimerRanOutManager instance() {
		if (instance == null) {
			instance = new FreezerTimerRanOutManager();
		}
		return instance;
	}
	
	public void addFreezerTimerRanOutListener(FreezerTimerRanOutListener listener) {
		listenerList.add(FreezerTimerRanOutListener.class, listener);
	}
	
	public void removeFreezerTimerRanOut(FreezerTimerRanOutListener listener) {
		listenerList.remove(FreezerTimerRanOutListener.class, listener);
	}
	
	public void processEvent(FreezerTimerRanOutEvent event) {
		EventListener[] listeners = listenerList.getListeners(FreezerTimerRanOutListener.class);
		for (int i = 0; i < listeners.length; i++) {
			((FreezerTimerRanOutListener) listeners[i]).freezerTimerRanOut(event);
		}
	}


}




