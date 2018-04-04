package Refrigerator;

public class RefrigeratorContext {
	private static CoolingUnitDisplay coolingUnitDisplay;
	private RefrigeratorState currentState;
	private static RefrigeratorContext instance;
	
	/**
	 * Private constructor to provide singleton instance
	 */
	private RefrigeratorContext() {
		coolingUnitDisplay = CoolingUnitDisplay.instance();
		// TODO: set the initial state to refrigerator door closed
	}
	
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}
	
	public void initialize() {
		// TODO: set the state to door closed state
	}
	
	/**
	 * Called to change states of the refrigerator from one to another.
	 * Called by the active state once a event is triggered to change the state.
	 * @param nextState the next state to set the refrigerator to
	 */
	public void changeCurrentState(RefrigeratorState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}
	
	public CoolingUnitDisplay getDisplay() {
		return coolingUnitDisplay;
	}
}
