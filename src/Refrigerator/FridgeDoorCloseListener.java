package Refrigerator;

import java.util.EventListener;

/**
 * Listener class for the FridgeDoorClose event. When 
 * FridgeDoorClose manager is called when the event occurs,
 *  classes that implement this listener interface call their doorClosed() 
 *  function to execute an appropriate action 
 * */
public interface FridgeDoorCloseListener extends EventListener{
	public void doorClosed(FridgeDoorCloseEvent event);
}
