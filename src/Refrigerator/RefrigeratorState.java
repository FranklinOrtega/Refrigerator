package Refrigerator;

public abstract class RefrigeratorState {
	protected static RefrigeratorContext context;
	protected static CoolingUnitDisplay display;
	
	protected RefrigeratorState() {
		context = RefrigeratorContext.instance();
		display = CoolingUnitDisplay.instance();
	}
	
	/**
	 * Called when a state is initialized. The state should register with its'
	 * necessary listener managers so that it's passed events.
	 */
	public abstract void run();
	
	/**
	 * Called when a state is disabled. This serves as a way of allowing the
	 * state to disable the listeners it's registered with. This disabling
	 * and leaving of states allows us to make sure only active events are
	 * catching the necessary listener events.
	 */
	public abstract void leave();
}
