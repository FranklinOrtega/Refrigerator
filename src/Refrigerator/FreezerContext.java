package Refrigerator;

/**
 * FreezerContext is the context class that manages the current state of the Freezer
 * in the Refrigerator system. It switches between the FreezerDoorOpenState, FreezerDoorClosedState,
 * and FreezerCoolingState based on the events that are triggered by the GUIRefridgeratorDisplay,
 * or when the temperature is beyond the threshold.
 */
public class FreezerContext {
	private static CoolingUnitDisplay coolingUnitDisplay;
	private FreezerState currentState;
	private static FreezerContext instance;
	
	/* 
	 * Initializing default values
	 * temp variable: This is where the current temperature of the freezer is stored. Whenever the value
	 * is updated by the DoorOpen DoorClosed or Cooling states, this value is modified.
	 * Temp value is also fetched by the GUI display for updating the temperature 
	 */
	private int temp = 70; // fridge temp initializes to the same as the room temp
	private int currentFreezerRate = 0;
	private int freezerLow = -9; // This is just rough value
	private int freezerHigh = 0; // This is just rough value
	private int roomLow = 50;
	private int roomHigh = 75;
	private int roomTemp = 70; //usual room temp (will be changed by GUI later)
	private int freezerRateLossDoorOpen = 1; //warming rate when door is opened
	private int freezerRateLossDoorClosed = 10;
	private int freezerCoolRate = 30;
	private int freezerCompressorStartDiff = 1;
	
	/**
	 * Private constructor to provide singleton instance
	 */
	private FreezerContext() {
		instance = this;
		loadConfigurationFromFile();
		coolingUnitDisplay = CoolingUnitDisplay.instance();
		currentState = FreezerDoorCloseState.instance();
	}
	
	/**
	 * Instance method to initialize freezerContext object as a singleton
	 */
	public static FreezerContext instance() {
		if (instance == null) {
			instance = new FreezerContext();
		}
		return instance;
	}
	
	/**
	 * Initializes the state of the freezer to door closed state
	 */
	public void initialize() {
		instance.changeCurrentState(FreezerDoorCloseState.instance());
	}
	
	/**
	 * Called to initialize the FreezerContext's variables based on data from config file
	 */
	public void loadConfigurationFromFile() {
		ConfigLoader loader = new ConfigLoader("properties.config");
		freezerLow = loader.getValueAsInt("FreezerLow");
		freezerHigh = loader.getValueAsInt("FreezerHigh");
		roomLow = loader.getValueAsInt("RoomLow");
		roomHigh = loader.getValueAsInt("RoomHigh");
		freezerRateLossDoorClosed = loader.getValueAsInt("FreezerRateLossDoorClosed");
		freezerRateLossDoorOpen = loader.getValueAsInt("FreezerRateLossDoorOpen");
		freezerCompressorStartDiff = loader.getValueAsInt("FreezerCompressorStartDiff");
		freezerCoolRate = loader.getValueAsInt("FreezerCoolRate");
	}
	
	/**
	 * Called to change states of the freezer from one to another.
	 * Called by the active state once an event is triggered to change the state.
	 * @param nextState the next state to set the freezer to
	 */
	public void changeCurrentState(FreezerState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}
	
	public CoolingUnitDisplay getDisplay() {
		return coolingUnitDisplay;
	}
	
	/*Called to get temperature of freezer based on config file*/
	public int getTemp() {
		return temp;
	}
	
	/*Called to set temperature of freezer based on config file*/
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	/*Called to get freezer low temperature of context based on config file*/
	public int getFreezerLow() {
		return freezerLow;
	}

	/*Called to get freezer high temperature of context based on config file*/
	public int getFreezerHigh() {
		return freezerHigh;
	}
	
	/*Called to get room high temperature of context based on config file*/
	public int getRoomLow() {
		return roomLow;
	}

	/*Gets room high temperature of context based on config file*/
	public int getRoomHigh() {
		return roomHigh;
	}

	/*Called to change the ridge rate, based on if door is opened, closed, or compressor is on*/
	public void setCurrentFreezerRate(int rate) {
		this.currentFreezerRate = rate;
	}
	
	/*Called to get current freezer rate */
	public int getCurrentFreezerRate() {
		return currentFreezerRate;
	}
	
	/*Called to get the warming rate when freezer door is open */
	public int getFreezerRateLossDoorOpen() {
		return freezerRateLossDoorOpen;
	}
	
	/*Called to get the warming rate when freezer door is closed*/
	public int getFreezerRateLossDoorClosed() {
		return freezerRateLossDoorClosed;
	}
	/*Called to get the cooling rate when freezer compressor is on */
	public int getFreezerCoolRate() {
		return freezerCoolRate;
	}

	/**
	 * Gets the upper threshold temperature (temp for when the cooling should kick ON)
	 * @return the threshold temp
	 */
	public int getFreezerUpperThresholdTemp() {
		return (freezerHigh + freezerCompressorStartDiff);
	}
	
	/**
	 * Gets the lower threshold temperature (temp for when the cooling should kick OFF)
	 * @return the threshold temp
	 */
	public int getFreezerLowerThresholdTemp() {
		return (freezerLow + freezerCompressorStartDiff);
	}
	
	/*Get temperature of room */
	public int getRoomTemp() {
		return roomTemp;
	}

	/*
	 * Called to set room temperature to temperature given by the GUI
	 * ensures room temperature is within bounds given by config file
	 */
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