package Refrigerator;

/**
 * Represents the state of the fridge being closed. Extends FridgeState to incorporate
 * the run and leave methods all states require. Run method is called when a state is initialized,
 * and the leave method is called when the state is ended. Implements listener classes to register
 * and receive these events as they occur. This way we can change the state and modify the temperature
 * as need be.
 */
public class FridgeDoorCloseState extends FridgeState implements
	FridgeDoorOpenListener, FridgeTimerRanOutListener, FridgeThresholdReachedListener {

	private static FridgeDoorCloseState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorCloseState() {
	}
	
	/**
	 * Gets the singleton instance
	 * @return singleton instance
	 */
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
		//updates the fridge temp in the gui
		display.updateCurrentFridgeTemp();
		
		/*
		 * Immediately check if the current fridge temp is >= threshold, then switch to compressor state
		 * Requires this class to be registered with the FridgeThresholdReachedManager to process the
		 * thresholdReached event. Accounts for the unique case of the threshold already been reached
		 * but the compressor not being able to activate due to the door being open.
		 */
		FridgeThresholdReachedManager.instance().addFridgeThresholdReachedListener(instance);
		if(context.getTemp() >= context.getFridgeUpperThresholdTemp()) {
			FridgeThresholdReachedManager.instance().processEvent(
					new FridgeThresholdReachedEvent(instance));
			/*
			 * return because the following code does not need to be initialized as the
			 * fridge state is going to change
			 */
			return;
		}
		
		//otherwise, go about the process for instantiating doorClosed state
		//compressor should be 'off' -- as in, in door closed state the compressor should be off
		display.setFridgeIdle();
		display.turnFridgeLightOff();
		
		//setting the context rate to the rate for door closed
		FridgeContext.instance().setCurrentFridgeRate(
				FridgeContext.instance().getFridgeRateLossDoorClosed());
		
		//set Fridge timer to start at value of rate
		FridgeTimer.instance().setTimeValue(
				FridgeContext.instance().getCurrentFridgeRate());
		
		//register as a listener of the door open event
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//register as a listener of the fridge timer ran out event
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
	}

	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext.
	 * Unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance);
		FridgeThresholdReachedManager.instance().removeFridgeThresholdReached(instance);
	}
	
	/**
	 * Called by the FridgeTimerRanOut manager. Increments the temp and updates it on the
	 * gui. Checks if the temp meets the upper threshold value, if so, creates a ThresholdReachedEvent.
	 * Resets the timer back to the current rate.
	 */
	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		context.setTemp(context.getTemp() + 1);
		display.updateCurrentFridgeTemp();
		
		//if the temperature we set equals or exceeds the threshold, change the state to cooling
		if(context.getTemp() >= context.getFridgeUpperThresholdTemp()) {
			FridgeThresholdReachedManager.instance().processEvent(
				new FridgeThresholdReachedEvent(instance));
		}
		else {
			//reset the timer
			FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
		}
	}

	/**
	 * Called by the FridgeDoorOpened manager. Changes the state of the fridge to the
	 * FridgeDoorOpenState.
	 */
	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}
	
	/**
	 * Called by FridgeThresholdReached manager. Changes the state of the fridge to the
	 * FridgeCoolingState because we've met the threshold temp.
	 */
	@Override
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event) {
		// fridge has reached the threshold value, activate cooling state
		context.changeCurrentState(FridgeCoolingState.instance());
	}
	
}
