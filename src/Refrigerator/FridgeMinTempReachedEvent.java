package Refrigerator;

import java.util.EventObject;

public class FridgeMinTempReachedEvent extends EventObject {
	public FridgeMinTempReachedEvent(Object source) {
		super(source);
	}
}
