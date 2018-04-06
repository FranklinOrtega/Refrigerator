package Refrigerator;

import java.util.EventListener;

public interface RefrigeratorMinTempReachedListener extends EventListener {
	public void minTempReached(RefrigeratorMinTempReachedEvent event);
}
