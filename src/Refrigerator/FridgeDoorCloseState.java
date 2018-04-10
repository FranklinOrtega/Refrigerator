package Refrigerator;

public class FridgeDoorCloseState extends FridgeState implements
	FridgeDoorOpenListener {

	/*
	 * TODO implement necessary listener classes, register them with the
	 * listener managers in the run method, and unregister with them in
	 * the leave method.
	 * 
	 * Example: implement clockTick event, and add tick process method
	 */
	
	private static FridgeDoorCloseState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorCloseState() {
	}
	
	public static FridgeDoorCloseState instance() {
		if (instance == null) {
			instance = new FridgeDoorCloseState();
		}
		return instance;
	}
	
	/**
	 * Called when the event is registered as active from the RefrigeratorContext.
	 * Also registers as a listener for with listener manager classes
	 */
	@Override
	public void run() {
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		display.turnFridgeLightOff();
	}
	
	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
	}

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}
}
