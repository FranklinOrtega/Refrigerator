package Refrigerator;

/**
 * Represents the state of the freezer being closed. Extends FreezerState to incorporate
 * the run and leave methods all states require. Run method is called when a state is initialized,
 * and the leave method is called when the state is ended. Implements listener classes to register
 * and receive these events as they occur. This way we can change the state and modify the temperature
 * as need be.
 */
public class FreezerDoorCloseState extends FreezerState implements
	FreezerDoorOpenListener, FreezerTimerRanOutListener, FreezerThresholdReachedListener {

	private static FreezerDoorCloseState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerDoorCloseState() {
	}
	
	/**
	 * Gets the singleton instance
	 * @return singleton instance
	 */
	public static FreezerDoorCloseState instance() {
		if (instance == null) {
			instance = new FreezerDoorCloseState();
		}
		return instance;
	}
	
	/**
	 * Called when the event is registered as active from the FreezerContext.
	 * Also registers as a listener for with listener manager classes
	 */
	@Override
	public void run() {
		//updates the freezer temp in the gui
		display.updateCurrentFreezerTemp();
		
		/*
		 * Immediately check if the current freezer temp is >= threshold, then switch to compressor state
		 * Requires this class to be registered with the FreezerThresholdReachedManager to process the
		 * thresholdReached event. Accounts for the unique case of the threshold already been reached
		 * but the compressor not being able to activate due to the door being open.
		 */
		FreezerThresholdReachedManager.instance().addFreezerThresholdReachedListener(instance);
		if(context.getTemp() >= context.getFreezerUpperThresholdTemp()) {
			FreezerThresholdReachedManager.instance().processEvent(
					new FreezerThresholdReachedEvent(instance));
			/*
			 * return because the following code does not need to be initialized as the
			 * freezer state is going to change
			 */
			return;
		}
		
		//otherwise, go about the process for instantiating doorClosed state
		//compressor should be 'off' -- as in, in door closed state the compressor should be off
		display.setFreezerIdle();
		display.turnFreezerLightOff();
		
		//setting the context rate to the rate for door closed
		context.setCurrentFreezerRate(
				context.getFreezerRateLossDoorClosed());
		
		//set freezer timer to start at value of rate
		FreezerTimer.instance().setTimeValue(
				context.getCurrentFreezerRate());
		
		//register as a listener of the door open event
		FreezerDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//register as a listener of the freezer timer ran out event
		FreezerTimerRanOutManager.instance().addFreezerTimerRanOutListener(instance);
	}

	/**
	 * Called when the event is ended and unregistered from the FreezerContext.
	 * Unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FreezerDoorOpenManager.instance().removeDoorOpenListener(instance);
		FreezerTimerRanOutManager.instance().removeFreezerTimerRanOutListener(instance);
		FreezerThresholdReachedManager.instance().removeFreezerThresholdReachedListener(instance);
	}
	
	/**
	 * Called by the FreezerTimerRanOut manager. Increments the temp and updates it on the
	 * gui. Checks if the temp meets the upper threshold value, if so, creates a ThresholdReachedEvent.
	 * Resets the timer back to the current rate.
	 */
	@Override
	public void freezerTimerRanOut(FreezerTimerRanOutEvent event) {
		context.setTemp(context.getTemp() + 1);
		display.updateCurrentFridgeTemp();
		
		//if the temperature we set equals or exceeds the threshold, change the state to cooling
		if(context.getTemp() >= context.getFreezerUpperThresholdTemp()) {
			FreezerThresholdReachedManager.instance().processEvent(
				new FreezerThresholdReachedEvent(instance));
		}
		else {
			//reset the timer
			FreezerTimer.instance().addTimeValue(context.getCurrentFreezerRate());
		}
	}

	/**
	 * Called by the FreezerDoorOpened manager. Changes the state of the freezer to the
	 * FreezerDoorOpenState.
	 */
	@Override
	public void doorOpened(FreezerDoorOpenEvent event) {
		context.changeCurrentState(FreezerDoorOpenState.instance());
	}
	
	/**
	 * Called by FreezerThresholdReached manager. Changes the state of the freezer to the
	 * FreezerCoolingState because we've met the threshold temp.
	 */
	@Override
	public void freezerThresholdReached(FreezerThresholdReachedEvent event) {
		// freezer has reached the threshold value, activate cooling state
		context.changeCurrentState(FreezerCoolingState.instance());
	}
	
}
