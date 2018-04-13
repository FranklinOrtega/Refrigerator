package Refrigerator;

import java.util.EventListener;

public interface FridgeTempListener extends EventListener{
	public void fridgeTemp(FridgeTempEvent event);
}
