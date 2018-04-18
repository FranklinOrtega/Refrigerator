package Refrigerator;

import java.util.EventListener;

/**
 * Listener class for the FreezerDoorClose event. When 
 * FreezerDoorClose manager is called when the event occurs,
 * classes that implement this listener interface call their doorClosed() 
 * function to execute an appropriate action.
 */
public interface FreezerDoorCloseListener extends EventListener{
	public void doorClosed(FreezerDoorCloseEvent event);
}
