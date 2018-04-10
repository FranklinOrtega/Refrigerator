package Refrigerator;

import java.util.EventObject;

public class FridgeMaxTempReachedEvent extends EventObject {
	public FridgeMaxTempReachedEvent(Object source) {
		super(source);
	}
}
