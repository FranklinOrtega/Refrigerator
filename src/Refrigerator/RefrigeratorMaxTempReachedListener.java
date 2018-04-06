package Refrigerator;

import java.util.EventListener;

public interface RefrigeratorMaxTempReachedListener extends EventListener {
	public void maxTempReached(RefrigeratorMaxTempReachedEvent event);
}
