package Refrigerator;

/*public class FridgeTimerRanOutListener {

}*/

import java.util.EventListener;

public interface FridgeTimerRanOutListener  extends EventListener {
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event);
}

