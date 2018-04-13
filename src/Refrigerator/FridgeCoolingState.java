package Refrigerator;

public class FridgeCoolingState extends FridgeState implements FridgeTimerRanOutListener, FridgeDoorOpenListener {
	
	private static FridgeCoolingState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FridgeCoolingState() {
	}
	
	public static FridgeCoolingState instance() {
		if (instance == null) {
			instance = new FridgeCoolingState();
		}
		return instance;
	}
	

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		//change to the doorOpen state
		context.changeCurrentState(FridgeDoorOpenState.instance());
		

	}

	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		//Subtract from the timer
		context.setTemp(context.getTemp() - 1);
		display.DisplayCurrentFridgeTemp();
			
		//reset the timer
		//note that the currentFridgeRate should be same as the rate when the door is closed
		FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());

	}

	@Override
	public void run() {
		//Turn the compressor on
		display.setFridgeCooling();
		
		//change context's fridge rate to doorOpenLossRate
		FridgeContext.instance().setCurrentFridgeRate(
						FridgeContext.instance().getFridgeCoolRate());
				
		//set timer to the context's updated rate
		FridgeTimer.instance().setTimeValue(
						FridgeContext.instance().getCurrentFridgeRate());
				
		// Add doorOpenedState to timerRanOut manager 
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
				
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		

	}

	@Override
	public void leave() {
		//Once we leave -- turn off the compressor
		display.setFreezerIdle();
		
		//unregister from managers
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance); 
		//also leave timeRanOut manager
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance);
		
	}

}
