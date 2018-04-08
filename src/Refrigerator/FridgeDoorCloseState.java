package Refrigerator;

/**
* Represents the state of the refrigerator when the door is closed. When the
* refrigerator has its door closed, the run method of this class is called. After
* that, when an event occurs, the handle method is invoked.
*/
public class FridgeDoorCloseState extends RefrigeratorState implements
	RefrigeratorDoorOpenListener {
	private static FridgeDoorCloseState instance;
	
	private FridgeDoorCloseState() {
	}
	
	/**
	 * returns the instance Singleton type
	 * 
	 * @return this object
	 */
	public static FridgeDoorCloseState instance () {
		if(instance == null) {
			return instance = new FridgeDoorCloseState();
		} else {
			return instance;
		}
	}
	
	/**
	 * initialize the state
	 * 
	 */
	@Override
	public void run() {
		// Initialize state
		// ClassNameManager.instance().addMethodNameRequestListener(instance);
		
		// Uncomment this when FridgeDoorOpenManager is implemented
//		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		display.turnRefrigeratorLightOff();
	}
	
	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		// Remove states initialized in the run() method and in the class

		// Uncomment this when FridgeDoorOpenManager is implemented
//		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
	}

	@Override
	public void doorOpened(RefrigeratorDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}
	
	// Uncomment this method when FridgeDoorOpenEvent is created
//	@Override
//	public void doorOpened(FridgeDoorOpenEvent event) {
//		context.changeCurrentState(FridgeDoorOpenState.instance());
//	}
}
