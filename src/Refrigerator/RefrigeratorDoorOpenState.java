package Refrigerator;

public class RefrigeratorDoorOpenState extends RefrigeratorState implements
	RefrigeratorDoorCloseListener{

	/*
	 * TODO implement necessary listener classes, register them with the
	 * listener managers in the run method, and unregister with them in
	 * the leave method.
	 * 
	 * Example: implement clockTick event, and add tick process method
	 */
	
	private static RefrigeratorDoorOpenState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private RefrigeratorDoorOpenState() {
	}
	
	public static RefrigeratorDoorOpenState instance() {
		if (instance == null) {
			instance = new RefrigeratorDoorOpenState();
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
		RefrigeratorDoorCloseManager.instance().addDoorCloseListener(instance);
		display.turnRefrigeratorLightOn();
	}
	
	/**
	 * Called when the event is ended and un-registered from the RefrigeratorContext
	 * class. Also un-registers with the listener manager classes.
	 */
	@Override
	public void leave() {
		RefrigeratorDoorCloseManager.instance().addDoorCloseListener(instance);
	}

	@Override
	public void doorClosed(RefrigeratorDoorCloseEvent event) {
		context.changeCurrentState(RefrigeratorDoorCloseState.instance());
	}
}
