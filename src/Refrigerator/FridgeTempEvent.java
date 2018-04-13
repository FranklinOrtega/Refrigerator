package Refrigerator;

import java.util.EventObject;

public class FridgeTempEvent extends EventObject{
	public FridgeTempEvent(Object source) {
		super(source);
	}
}
