package Refrigerator;

import java.util.EventListener;
/**
 * Listener class for the FridgeThresholdReached event. When 
 * FridgeThresholdReaches manager is called when the event occurs,
 *  classes that implement this listener interface call their fridgeThresholdReached() 
 *  function to execute an appropriate action 
 * */
public interface FridgeThresholdReachedListener extends EventListener{
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event);

}
