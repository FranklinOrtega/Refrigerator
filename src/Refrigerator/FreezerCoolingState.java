package Refrigerator;

public class FreezerCoolingState extends FreezerState implements 
	FreezerTimerRanOutListener, FreezerDoorOpenListener, FreezerThresholdReachedListener {
	
	private static FreezerCoolingState instance;
	
	/**
	 * Private method to provide singleton instance
	 */
	private FreezerCoolingState() {
	}
	
	public static FreezerCoolingState instance() {
		if (instance == null) {
			instance = new FreezerCoolingState();
		}
		return instance;
	}
	
	@Override
	public void run() {
		//turn the compressor on
		display.setFreezerCooling();
		
		//change context's freezer rate to doorOpenLossRate
		FreezerContext.instance().setCurrentFreezerRate(
				FreezerContext.instance().getFreezerCoolRate());
		
		//set timer to the context's updated rate
		FreezerTimer.instance().setTimeValue(
				FreezerContext.instance().getCurrentFreezerRate());
		
		//add freezerCoolingState to doorOpen manager
		FreezerDoorOpenManager.instance().addDoorOpenListener(instance);
		
		//add freezerCoolingState to timerRanOut manager 
		FreezerTimerRanOutManager.instance().addFreezerTimerRanOutListener(instance);
		
		//add freezerCoolingState to freezerThresholdReached manager
		FreezerThresholdReachedManager.instance().addFreezerThresholdReachedListener(instance);
	}

	@Override
	public void leave() {
		//turn off the compressor
		display.setFreezerIdle();
		
		//leave doorOpen manager
		FreezerDoorOpenManager.instance().removeDoorOpenListener(instance); 
		//leave timeRanOut manager
		FreezerTimerRanOutManager.instance().removeFreezerTimerRanOutListener(instance);
		//leave freezerThresholdReached manager
		FreezerThresholdReachedManager.instance().removeFreezerThresholdReachedListener(instance);
	}

	@Override
	public void doorOpened(FreezerDoorOpenEvent event) {
		//change to the doorOpen state
		context.changeCurrentState(FreezerDoorOpenState.instance());
	}

	@Override
	public void freezerTimerRanOut(FreezerTimerRanOutEvent event) {
		//subtract from the timer
		context.setTemp(context.getTemp() - 1);
		display.updateCurrentFreezerTemp();
		
		//if the temp we set equals or is less than the cooling threshold temp,
		//change state to idle/door closed
		if (context.getTemp() <= context.getFreezerLowerThresholdTemp()) {
			FreezerThresholdReachedManager.instance().processEvent(
					new FreezerThresholdReachedEvent(instance));
		} else {
			//reset the timer
			FreezerTimer.instance().addTimeValue(context.getCurrentFreezerRate());
		}
	}
	
	@Override
	public void freezerThresholdReached(FreezerThresholdReachedEvent event) {
		// freezer has reached the minimum freezer temp
		// change the state to door closed state / compressor idle
		context.changeCurrentState(FreezerDoorCloseState.instance());
	}
}