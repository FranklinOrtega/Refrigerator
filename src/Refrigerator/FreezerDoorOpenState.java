package Refrigerator;

/**
 * Represents the state of the freezer being open. Extends FreezerState to incorporate
 * the run and leave methods all states require. Run method is called when a state is initialized,
 * and the leave method is called when the state is ended. Implements listener classes to register
 * and receive these events as they occur. This way we can change the state and modify the temperature
 * as need to be. When the freezer door open state is active, the compressor cannot start on. The temperature
 * equalizes once it reaches the room temp.
 */
public class FreezerDoorOpenState extends FreezerState implements
	FreezerDoorCloseListener, FreezerTimerRanOutListener{

	private static FreezerDoorOpenState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerDoorOpenState() {
	}
	
	/**
	 * Gets the singleton instance
	 * @return singleton instance
	 */
	public static FreezerDoorOpenState instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenState();
		}
		return instance;
	}
	
	/**
	 * Called when the event is registered as active from the FreezerContext.
	 * Also registers as a listener for with listener manager classes
	 */
	@Override
	public void run() {
		//set compressor to idle
		display.setFreezerIdle();
		display.turnFreezerLightOn();
		
		//change context's freezer rate to doorOpenLossRate
		FreezerContext.instance().setCurrentFreezerRate(
				FreezerContext.instance().getFreezerRateLossDoorOpen());
		
		//set timer to the context's updated rate
		FreezerTimer.instance().setTimeValue(
				FreezerContext.instance().getCurrentFreezerRate());
		
		//add doorOpenState to timerRanOut manager 
		FreezerTimerRanOutManager.instance().addFreezerTimerRanOutListener(instance);
		
		//add doorOpenState to doorClose manager
		FreezerDoorCloseManager.instance().addDoorCloseListener(instance);
	}
	
	/**
	 * Called when the event is ended and unregistered from the FreezerContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		//turn off the freezer light
		display.turnFreezerLightOff();
		
		//leave the doorClose manager
		FreezerDoorCloseManager.instance().removeDoorCloseListener(instance);
		
		//leave timeRanOut manager
		FreezerTimerRanOutManager.instance().removeFreezerTimerRanOutListener(instance); 
	}

	/**
	 * Called by the FreezerDoorCloseManager to change the state from door open
	 * to door closed.
	 */
	@Override
	public void doorClosed(FreezerDoorCloseEvent event) {
		context.changeCurrentState(FreezerDoorCloseState.instance());
	}

	/**
	 * Called by the FreezerTimerRanOut manager when the timer has ran out.
	 * Increments the temp of the freezer if temp has not reached the room temp.
	 * Resets the timer back to the current rate.
	 */
	@Override
	public void freezerTimerRanOut(FreezerTimerRanOutEvent event) {
		// check if freezer temp is less than the room temp
		if (context.getTemp() < context.getRoomTemp()) {
			context.setTemp(context.getTemp() + 1);
			display.updateCurrentFreezerTemp();
		}
		
		//reset the timer
		FreezerTimer.instance().addTimeValue(context.getCurrentFreezerRate());
	}
	
}
