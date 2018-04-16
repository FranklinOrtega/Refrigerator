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
	 */
	private int currentFridgeRate = 0;
	
	//Initializing default values
	private int fridgeLow = 37;
	private int fridgeHigh = 41;
	private int roomLow = 50;
	private int roomHigh = 75;
	private int roomTemp = 70; //usual room temp (will be changed by gui later)
	private int fridgeRateLossDoorOpen = 1; //warming rate when door is opened
	private int fridgeRateLossDoorClosed = 10;
	private int fridgeCoolRate = 5;
	private int fridgeCompressorStartDiff = 5;
	
	/**
	 * Private constructor to provide singleton instance
	 */
	private FridgeContext() {
		instance = this;
		loadConfigurationFromFile();
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
	 * Initializes the state of the refrigerator to door closed state
	 */
	public void initialize() {
		instance.changeCurrentState(FridgeDoorCloseState.instance());
	}
	
	public void loadConfigurationFromFile() {
		ConfigLoader loader = new ConfigLoader("properties.config");
		fridgeLow = loader.getValueAsInt("FridgeLow");
		fridgeHigh = loader.getValueAsInt("FridgeHigh");
		roomLow = loader.getValueAsInt("RoomLow");
		roomHigh = loader.getValueAsInt("RoomHigh");
		fridgeRateLossDoorClosed = loader.getValueAsInt("FridgeRateLossDoorClosed");
		fridgeRateLossDoorOpen = loader.getValueAsInt("FridgeRateLossDoorOpen");
		fridgeCompressorStartDiff = loader.getValueAsInt("FridgeCompressorStartDiff");
		fridgeCoolRate = loader.getValueAsInt("FridgeCoolRate");
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
		return fridgeLow;
	}

	public int getFridgeHigh() {
		return fridgeHigh;
	}

	public int getRoomLow() {
		return roomLow;
	}

	public int getRoomHigh() {
		return roomHigh;
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
		return fridgeRateLossDoorOpen;
	}

	public int getFridgeRateLossDoorClosed() {
		return fridgeRateLossDoorClosed;
	}
	
	public int getFridgeCoolRate() {
		return fridgeCoolRate;
	}

	/**
	 * Gets the upper threshold temperature (temp for when the cooling should kick ON)
	 * @return the threshold temp
	 */
	public int getFridgeUpperThresholdTemp() {
		return (fridgeHigh + fridgeCompressorStartDiff);
	}
	
	/**
	 * Gets the lower threshold temperature (temp for when the cooling should kick OFF)
	 * @return the threshold temp
	 */
	public int getFridgeLowerThresholdTemp() {
		return (fridgeLow + fridgeCompressorStartDiff);
	}
	
	public int getRoomTemp() {
		return roomTemp;
	}

	public boolean setRoomTemp(int roomTemp) {
		if (roomTemp > roomHigh || roomTemp < roomLow) {
			// new room temp is outside of the configuration settings
			return false;
		} else {
			this.roomTemp = roomTemp;
			return true;
		}
	}
	
	
}
