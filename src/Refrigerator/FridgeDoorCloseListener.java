package Refrigerator;

import java.util.EventListener;

public interface FridgeDoorCloseListener extends EventListener{
	public void doorClosed(FridgeDoorCloseEvent event);
}
