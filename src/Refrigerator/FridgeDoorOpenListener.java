package Refrigerator;

import java.util.EventListener;

public interface FridgeDoorOpenListener extends EventListener{
	public void doorOpened(FridgeDoorOpenEvent event);
}
