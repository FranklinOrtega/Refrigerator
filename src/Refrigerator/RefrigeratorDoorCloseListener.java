package Refrigerator;

import java.util.EventListener;

public interface RefrigeratorDoorCloseListener extends EventListener{
	public void doorClosed(RefrigeratorDoorCloseEvent event);
}
