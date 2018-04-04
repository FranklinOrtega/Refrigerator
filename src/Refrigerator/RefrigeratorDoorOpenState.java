package Refrigerator;

public class RefrigeratorDoorOpenState extends RefigeratorState{

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
		
	}
	
	/**
	 * Called when the event is ended and un-registered from the RefrigeratorContext
	 * class. Also un-registers with the listener manager classes.
	 */
	@Override
	public void leave() {
		
	}
}
