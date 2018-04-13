package Refrigerator;

public class FridgeContext {
	private static CoolingUnitDisplay coolingUnitDisplay;
	private FridgeState currentState;
	private static FridgeContext instance;
	
	/*
	 * This is where the current temperature of the fridge is stored. Whenever the value
	 * is updated by the FridgeCompressor or FridgeWarming classes, this value is modified.
	 * Also, this value is fetched by the gui display for updating the temperature
	 */
	private int temp = 35;
	
	/*
	 * This is where the timer and rate to be used by the fridge system are stored
	 * */
	//private int fridgeTimer = 0;
	private int currentFridgeRate = 0;
	
	//initalizing values based on the file until David implements config file integration
	private int FridgeLow = 37;
	private int FridgeHigh = 41;
	private int RoomLow = 50;
	private int RoomHigh = 75;
	private int roomTemp = 0; 
	private int FridgeRateLossDoorOpen = 1; //warming rate when door is opened
	private int FridgeRateLossDoorClosed = 10; 
	private int FridgeCoolRate = 5;
	private int FridgeCompressorStartDiff = 5;
	
	
	public int getRoomTemp() {
		return roomTemp;
	}

	public void setRoomTemp(int roomTemp) {
		if (roomTemp > RoomHigh || roomTemp < RoomLow) {
			
		} else {
			this.roomTemp = roomTemp;
		}
	}

	
	/*
	 * This variable is used to immediately compute the threshold temperature for when the compressor
	 * kicks in. This value is computed immediately as the inital temp + compressorStartDiff
	 * Must be computed immediately when system starts because the temperature changes dynamically*/
	private int threshholdTemp = temp + FridgeCompressorStartDiff;
	
	
	/**
	 * Private constructor to provide singleton instance
	 */
	private FridgeContext() {
		instance = this;
		coolingUnitDisplay = CoolingUnitDisplay.instance();
		currentState = FridgeDoorCloseState.instance();
	}
	
	public static FridgeContext instance() {
		if (instance == null) {
			instance = new FridgeContext();
		}
		return instance;
	}
	
	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FridgeDoorCloseState.instance());
	}
	
	/**
	 * Called to change states of the refrigerator from one to another.
	 * Called by the active state once a event is triggered to change the state.
	 * @param nextState the next state to set the refrigerator to
	 */
	public void changeCurrentState(FridgeState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}
	
	public CoolingUnitDisplay getDisplay() {
		return coolingUnitDisplay;
	}
	
	public int getTemp() {
		return temp;
	}
	
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public int getFridgeLow() {
		return FridgeLow;
	}

	public int getFridgeHigh() {
		return FridgeHigh;
	}

	public int getRoomLow() {
		return RoomLow;
	}

	public int getRoomHigh() {
		return RoomHigh;
	}

	/* Method to change the ridge rate, based on if door is opened, closed, or compressor is on*/
	public void setCurrentFridgeRate(int rate) {
		this.currentFridgeRate = rate;
	}
	
	/*Method to get current fridge rate */
	public int getCurrentFridgeRate() {
		return currentFridgeRate;
	}

	/*Methods for getting the various fridge rates - used when changing states */
	public int getFridgeRateLossDoorOpen() {
		return FridgeRateLossDoorOpen;
	}

	public int getFridgeRateLossDoorClosed() {
		return FridgeRateLossDoorClosed;
	}
	
	public int getFridgeCoolRate() {
		return FridgeCoolRate;
	}

	public int getFridgeThresholdTemp() {
		return threshholdTemp;
	}
	
	
	
	
}
