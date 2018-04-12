package Refrigerator;

public class FridgeDoorCloseState extends FridgeState implements
	FridgeDoorOpenListener, FridgeTimerRanOutListener, FridgeThresholdReachedListener {

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
		
		/*Immediately check if the current fridge temp is >= threshold, then swtich to compressor state*/
		//Note - this may not work!! Dont know the exact structure to implement this

		/*Note 2 needs to be added to the thresholdReached manager before threshold check
		 * in order for doorClosed state to process thresholdReached event if conditional below
		 * is true*/
		//adding listener to ThresholdRreached manager
		FridgeThresholdReachedManager.instance().addFridgeThresholdReachedListener(instance);
		if(context.getTemp() >= context.getFridgeThresholdTemp()) {
		FridgeThresholdReachedManager.instance().processEvent(
				new FridgeThresholdReachedEvent(instance));
				
		}
		/*else.. go about the process for instantiating doorClosed state*/		
		else {
				
		//compressor should be 'off' -- as in, in door closed state the compressor should be off
		display.setFridgeIdle();
			
		//setting the context rate to the rate for door closed
		FridgeContext.instance().setCurrentFridgeRate(
				FridgeContext.instance().getFridgeRateLossDoorClosed());
		
		//set Fridge timer to start at value of rate
		FridgeTimer.instance().setTimeValue(
				FridgeContext.instance().getCurrentFridgeRate());
		
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//adding listener to FridgeTimerRanOut manager
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		
		//adding listener to ThresholdRreached manager
		//FridgeThresholdReachedManager.instance().addFridgeThresholdReachedListener(instance);
		display.turnFridgeLightOff();
		}
	}
	
	/*Implementation for doorClosed's fridgeTimerRanOut listener function
	 * When timer runs out, decrement the temperature by one and reset the timer to 
	 * start at the DoorClosed rate*/
	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		context.setTemp(context.getTemp() + 1);
		display.DisplayCurrentFridgeTemp();
		
		//Note - this may not work!! Dont know the exact structure to implement this
		//if the temperature we set equals or exceeds the threshold, change the state to cooling
		if(context.getTemp() >= context.getFridgeThresholdTemp()) {
		FridgeThresholdReachedManager.instance().processEvent(
				new FridgeThresholdReachedEvent(instance));
		}
		
		else {
		//reset the timer
		//note that the currentFridgeRate should be same as the rate when the door is closed
		FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
		}
	}

	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance); //added
		FridgeThresholdReachedManager.instance().removeFridgeThresholdReached(instance);//added
	}

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}

	
	/*Added - should change the context to cooling*/
	@Override
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event) {
		// TODO Auto-generated method stub
		context.changeCurrentState(FridgeCoolingState.instance());
		
	}
	
	
}
