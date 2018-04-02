package Refrigerator;

import java.util.EventListener;

public interface DoorOpenListener extends EventListener{
	public void doorOpened(DoorOpenEvent event);
}
