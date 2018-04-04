package Refrigerator;

import java.util.EventObject;

public class RefrigeratorDoorOpenEvent extends EventObject{
	public RefrigeratorDoorOpenEvent(Object source) {
		super(source);
	}
}
