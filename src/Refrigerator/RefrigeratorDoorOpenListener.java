package Refrigerator;

import java.util.EventListener;

public interface RefrigeratorDoorOpenListener extends EventListener{
	public void doorOpened(RefrigeratorDoorOpenEvent event);
}
