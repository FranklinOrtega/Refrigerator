package Refrigerator;

import java.util.EventListener;

public interface FreezerTimerRanOutListener  extends EventListener {
	public void freezerTimerRanOut(FreezerTimerRanOutEvent event);
}
