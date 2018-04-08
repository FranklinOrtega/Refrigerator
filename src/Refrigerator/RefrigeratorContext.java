package Refrigerator;

public class RefrigeratorContext {
	private static CoolingUnitDisplay coolingUnitDisplay;
	private RefrigeratorState currentState;
	private static RefrigeratorContext instance;
	
	/**
	 * Private constructor to provide singleton instance
	 */
	private RefrigeratorContext() {
		instance = this;
		coolingUnitDisplay = CoolingUnitDisplay.instance();
		currentState = RefrigeratorDoorCloseState.instance();

		// TODO: set the initial state to refrigerator door closed
	}
	
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}
	
	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		// TODO: set the state to door closed state
		instance.changeCurrentState(RefrigeratorDoorCloseState.instance());
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
