package Refrigerator;

import java.util.EventListener;

public interface FridgeThresholdReachedListener extends EventListener{
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event);

}
