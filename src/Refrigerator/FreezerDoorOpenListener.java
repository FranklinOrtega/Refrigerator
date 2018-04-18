package Refrigerator;

import java.util.EventListener;

/**
* Listener class for the FreezerDoorOpen event. When 
* FreezerDoorOpen manager is called when the event occurs,
*  classes that implement this listener interface call their doorOpened() 
*  function to execute an appropriate action 
* */
public interface FreezerDoorOpenListener extends EventListener{
	public void doorOpened(FreezerDoorOpenEvent event);
}
