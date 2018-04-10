package Refrigerator;

public class FridgeDoorOpenState extends FridgeState implements
	FridgeDoorCloseListener{

	/*
	 * TODO implement necessary listener classes, register them with the
	 * listener managers in the run method, and unregister with them in
	 * the leave method.
	 * 
	 * Example: implement clockTick event, and add tick process method
	 */
	
	private static FridgeDoorOpenState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorOpenState() {
	}
	
	public static FridgeDoorOpenState instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenState();
		}
		return instance;
	}
	
	/**
	 * Called when the event is registered as active from the RefrigeratorContext.
	 * Also registers as a listener for with listener manager classes
	 */
	@Override
	public void run() {
		// ClassNameManager.instance().addMethodNameRequestListener(instance);
		FridgeDoorCloseManager.instance().addDoorCloseListener(instance);
		display.turnFridgeLightOn();
	}
	
	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FridgeDoorCloseManager.instance().addDoorCloseListener(instance);
	}

	@Override
	public void doorClosed(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorCloseState.instance());
	}
}
