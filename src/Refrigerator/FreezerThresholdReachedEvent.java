package Refrigerator;

import java.util.EventObject;

public class FreezerThresholdReachedEvent extends EventObject {	
	public FreezerThresholdReachedEvent(Object source) {
		super(source);
	}
}