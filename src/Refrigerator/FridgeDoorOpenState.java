package Refrigerator;

/**
 * Represents the state of the fridge being open. Extends FridgeState to incorporate
 * the run and leave methods all states require. Run method is called when a state is initialized,
 * and the leave method is called when the state is ended. Implements listener classes to register
 * and receive these events as they occur. This way we can change the state and modify the temperature
 * as need be. When the fridge door open state is active, the compressor cannot start on. The temperature
 * equalizes once it reaches the room temp.
 */
public class FridgeDoorOpenState extends FridgeState implements
	FridgeDoorCloseListener, FridgeTimerRanOutListener{

	private static FridgeDoorOpenState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorOpenState() {
	}
	
	/**
	 * Gets the singleton instance
	 * @return singleton instance
	 */
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
		//set compressor to idle
		display.setFridgeIdle();
		display.turnFridgeLightOn();
		
		//change context's fridge rate to doorOpenLossRate
		FridgeContext.instance().setCurrentFridgeRate(
				FridgeContext.instance().getFridgeRateLossDoorOpen());
		
		//set timer to the context's updated rate
		FridgeTimer.instance().setTimeValue(
				FridgeContext.instance().getCurrentFridgeRate());
		
		//add doorOpenState to timerRanOut manager 
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		
		//add doorOpenState to doorClose manager
		FridgeDoorCloseManager.instance().addDoorCloseListener(instance);
	}
	
	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		//turn off the fridge light
		display.turnFridgeLightOff();
		
		//leave the doorClose manager
		FridgeDoorCloseManager.instance().removeDoorCloseListener(instance);
		
		//leave timeRanOut manager
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance); 
	}

	/**
	 * Called by the FridgeDoorCloseManager to change the state from door open
	 * to door closed.
	 */
	@Override
	public void doorClosed(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorCloseState.instance());
	}

	/**
	 * Called by the FridgeTimerRanOut manager when the timer has ran out.
	 * Increments the temp of the fridge if temp has not reached the room temp.
	 * Resets the timer back to the current rate.
	 */
	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		// check if fridge temp is less than the room temp
		if (context.getTemp() < context.getRoomTemp()) {
			context.setTemp(context.getTemp() + 1);
			display.updateCurrentFridgeTemp();
		}
		
		//reset the timer
		FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
	}
	
}
