package Refrigerator;

/**
* Represents the state of the refrigerator when the fridge door is open.
* When the refrigerator has its door opened, the run method of this class 
* is called. After that, when an event occurs, the handle method is invoked.
*/
public class FridgeDoorOpenState extends RefrigeratorState implements
	RefrigeratorDoorCloseListener {
	private static FridgeDoorOpenState instance;
	
	private FridgeDoorOpenState() {
	}
	
	/**
	 * returns the instance, Singleton type
	 * 
	 * @return this object
	 */
	public static FridgeDoorOpenState instance () {
		if(instance == null) {
			return instance = new FridgeDoorOpenState();
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
		RefrigeratorDoorCloseManager.instance().addDoorCloseListener(instance);
		display.turnRefrigeratorLightOn();
	}
	
	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		// Remove states initialized states in the run() method and in the class 
		RefrigeratorDoorCloseManager.instance().addDoorCloseListener(instance);
	}

	@Override
	public void doorClosed(RefrigeratorDoorCloseEvent event) {
		context.changeCurrentState(RefrigeratorDoorCloseState.instance());
	}
}

