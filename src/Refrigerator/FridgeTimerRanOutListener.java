package Refrigerator;

import java.util.EventListener;

public interface FridgeTimerRanOutListener  extends EventListener {
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event);
}
