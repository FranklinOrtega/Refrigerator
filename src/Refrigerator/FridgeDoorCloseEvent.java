package Refrigerator;

import java.util.EventObject;

public class FridgeDoorCloseEvent extends EventObject{
	public FridgeDoorCloseEvent(Object source) {
		super(source);
	}
}
