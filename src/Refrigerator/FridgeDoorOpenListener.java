package Refrigerator;

import java.util.EventListener;

/**
* Listener class for the FridgeDoorOpen event. When 
* FridgeDoorOpen manager is called when the event occurs,
*  classes that implement this listener interface call their doorOpened() 
*  function to execute an appropriate action 
* */
public interface FridgeDoorOpenListener extends EventListener{
	public void doorOpened(FridgeDoorOpenEvent event);
}
