package Refrigerator;

import java.util.EventObject;

public class FridgeDoorOpenEvent extends EventObject{
	public FridgeDoorOpenEvent(Object source) {
		super(source);
	}
}
