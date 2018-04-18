
package Refrigerator;

import java.util.EventListener;
/**
 * Listener class for the FreezerThresholdReachedListener event. When 
 * FreezerThresholdReached manager is called when the event occurs,
 *  classes that implement this listener interface call their freezerThresholdReached() 
 *  function to execute an appropriate action 
 * */
public interface FreezerThresholdReachedListener extends EventListener{
	public void freezerThresholdReached(FreezerThresholdReachedEvent event);

}

