package Refrigerator;

import java.util.EventObject;

public class FreezerDoorOpenEvent extends EventObject{
	public FreezerDoorOpenEvent(Object source) {
		super(source);
	}
}
