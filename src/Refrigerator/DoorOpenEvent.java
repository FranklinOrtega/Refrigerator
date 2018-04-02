package Refrigerator;

import java.util.EventObject;

public class DoorOpenEvent extends EventObject{
	public DoorOpenEvent(Object source) {
		super(source);
	}
}
