package Refrigerator;

import java.util.EventListener;

public interface FridgeMaxTempReachedListener extends EventListener {
	public void maxTempReached(FridgeMaxTempReachedEvent event);
}
