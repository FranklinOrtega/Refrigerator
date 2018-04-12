package Refrigerator;

public class FridgeDoorCloseState extends FridgeState implements
	FridgeDoorOpenListener, FridgeTimerRanOutListener {

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
		//van: display the current temperature -- ensures temperature is displayed immediately as gui opens
		display.DisplayCurrentFridgeTemp();
		
		//setting the context rate to the rate for door closed
		FridgeContext.instance().setCurrentFridgeRate(
				FridgeContext.instance().getFridgeRateLossDoorClosed());
		
		//set Fridge timer to start at value of rate
		FridgeTimer.instance().setTimeValue(
				FridgeContext.instance().getCurrentFridgeRate());
		
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//adding listener to FridgeTimerRanOut manager
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		display.turnFridgeLightOff();
	}
	
	/*Implementation for doorClosed's fridgeTimerRanOut listener function
	 * When timer runs out, decrement the temperature by one and reset the timer to 
	 * start at the DoorClosed rate*/
	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		context.setTemp(context.getTemp() + 1);
		display.DisplayCurrentFridgeTemp();
		
		//reset the timer
		//note that the currentFridgeRate should be same as the rate when the door is closed
		FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
	}

	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance); //added
	}

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}
}
