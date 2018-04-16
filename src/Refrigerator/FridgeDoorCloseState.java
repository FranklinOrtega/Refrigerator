package Refrigerator;

public class FridgeDoorCloseState extends FridgeState implements
	FridgeDoorOpenListener, FridgeTimerRanOutListener, FridgeThresholdReachedListener {

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
		display.updateCurrentFridgeTemp();
		
		/*
		 * Immediately check if the current fridge temp is >= threshold, then switch to compressor state
		 * Requires this class to be registered with the FridgeThresholdReachedManager to process the
		 * thresholdReached event
		 */
		FridgeThresholdReachedManager.instance().addFridgeThresholdReachedListener(instance);
		if(context.getTemp() >= context.getFridgeUpperThresholdTemp()) {
			FridgeThresholdReachedManager.instance().processEvent(
					new FridgeThresholdReachedEvent(instance));
		}
		else {
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
			
			FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
			
			//adding listener to FridgeTimerRanOut manager
			FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		}
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
	
	/*Implementation for doorClosed's fridgeTimerRanOut listener function
	 * When timer runs out, decrement the temperature by one and reset the timer to 
	 * start at the DoorClosed rate*/
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

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}
	
	@Override
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event) {
		// fridge has reached the threshold value, activate cooling state
		context.changeCurrentState(FridgeCoolingState.instance());
	}
	
	
}
