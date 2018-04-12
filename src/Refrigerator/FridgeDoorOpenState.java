package Refrigerator;

public class FridgeDoorOpenState extends FridgeState implements
	FridgeDoorCloseListener, FridgeTimerRanOutListener/*, FridgeThresholdReachedListener*/{

	/*
	 * TODO implement necessary listener classes, register them with the
	 * listener managers in the run method, and unregister with them in
	 * the leave method.
	 * 
	 * Example: implement clockTick event, and add tick process method
	 */
	
	private static FridgeDoorOpenState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeDoorOpenState() {
	}
	
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
		//compresor should be off
		display.setFridgeIdle();//FridgeContext.instance().
		
		//change context's fridge rate to doorOpenLossRate
		FridgeContext.instance().setCurrentFridgeRate(
				FridgeContext.instance().getFridgeRateLossDoorOpen());
		
		//set timer to the context's updated rate
		FridgeTimer.instance().setTimeValue(
				FridgeContext.instance().getCurrentFridgeRate());
		
		// Add doorOpenedState to timerRanOut manager 
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		
		FridgeDoorCloseManager.instance().addDoorCloseListener(instance);
		display.turnFridgeLightOn();
	}
	
	/**
	 * Called when the event is ended and unregistered from the RefrigeratorContext
	 * class. Also unregisters with the listener manager classes.
	 */
	@Override
	public void leave() {
		//leave the doorClose manager
		FridgeDoorCloseManager.instance().removeDoorOpenListener(instance);//addDoorCloseListener(instance); //?doesnt it need to be removed form doorClose manager??
		//also leave timeRanOut manager
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance); 
		
		//leave the thresholdReachedManager
		//FridgeThresholdReachedManager.instance().removeFridgeThresholdReached(instance);
	}

	@Override
	public void doorClosed(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorCloseState.instance());
	}

	/*Implementation for doorOpen's fridgeTimerRanOut listener function
	 * When timer runs out, decrement the temperature by one and reset the timer to 
	 * start at the DoorOpen rate*/
	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		// TODO Auto-generated method stub
		context.setTemp(context.getTemp() + 1);
		display.DisplayCurrentFridgeTemp();
		
		/*if(context.getTemp() >= context.getFridgeThresholdTemp()) {
			FridgeThresholdReachedManager.instance().processEvent(
					new FridgeThresholdReachedEvent(instance));
			}*/
			
		/*else {*/
		//reset the timer
		//note that the currentFridgeRate should be same as the rate when the door is closed
		FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
		/*}*/
	}

	/*@Override
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event) {
		// TODO Auto-generated method stub
		//context.changeCurrentState(FridgeCoolingState.instance());
		
	}*/
	
	
	
	
	
}
