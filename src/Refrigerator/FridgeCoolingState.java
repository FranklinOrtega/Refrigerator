package Refrigerator;

public class FridgeCoolingState extends FridgeState implements 
	FridgeTimerRanOutListener, FridgeDoorOpenListener, FridgeThresholdReachedListener {
	
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
	public void run() {
		//turn the compressor on
		display.setFridgeCooling();
		
		//change context's fridge rate to doorOpenLossRate
		FridgeContext.instance().setCurrentFridgeRate(
						FridgeContext.instance().getFridgeCoolRate());
		
		//set timer to the context's updated rate
		FridgeTimer.instance().setTimeValue(
						FridgeContext.instance().getCurrentFridgeRate());
		
		//add fridgeCoolingState to doorOpen manager
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//add fridgeCoolingState to timerRanOut manager 
		FridgeTimerRanOutManager.instance().addFridgeTimerRanOutListener(instance);
		
		//add fridgeCoolingState to fridgeThresholdReached manager
		FridgeThresholdReachedManager.instance().addFridgeThresholdReachedListener(instance);
	}

	@Override
	public void leave() {
		//turn off the compressor
		display.setFridgeIdle();
		
		//leave doorOpen manager
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance); 
		//leave timeRanOut manager
		FridgeTimerRanOutManager.instance().removeFridgeTimerRanOut(instance);
		//leave fridgeThresholdReached manager
		FridgeThresholdReachedManager.instance().removeFridgeThresholdReachedListener(instance);
	}

	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		//change to the doorOpen state
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}

	@Override
	public void fridgeTimerRanOut(FridgeTimerRanOutEvent event) {
		//subtract from the timer
		context.setTemp(context.getTemp() - 1);
		display.updateCurrentFridgeTemp();
		
		//if the temp we set equals or is less than the cooling threshold temp,
		//change state to idle/door closed
		if (context.getTemp() <= context.getFridgeLowerThresholdTemp()) {
			FridgeThresholdReachedManager.instance().processEvent(
					new FridgeThresholdReachedEvent(instance));
		} else {
			//reset the timer
			FridgeTimer.instance().addTimeValue(context.getCurrentFridgeRate());
		}
	}
	
	@Override
	public void fridgeThresholdReached(FridgeThresholdReachedEvent event) {
		// fridge has reached the minimum fridge temp
		// change the state to door closed state / compressor idle
		context.changeCurrentState(FridgeDoorCloseState.instance());
	}
}
