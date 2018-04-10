package Refrigerator;

import java.util.EventListener;

public interface FridgeMinTempReachedListener extends EventListener {
	public void minTempReached(FridgeMinTempReachedEvent event);
}
