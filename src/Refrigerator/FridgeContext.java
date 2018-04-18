package Refrigerator;
/**
 * FridgeContext is the context class that manages the current state of the Fridge
 * in the Refrigerator system. It switches between the FridgeDoorOpenState, FridgeDoorClosedState,
 * and FridgeCoolingState based on the event that occurs within the GUIRefridgeratorDisplay,
 * or when the temperature is beyond the threshold
 * 
 **/
public class FridgeContext {
	private static CoolingUnitDisplay coolingUnitDisplay;
	private FridgeState currentState;
	private static FridgeContext instance;
	
	//Initializing default values
	/* 
	 * temp variable: This is where the current temperature of the fridge is stored. Whenever the value
	 * is updated by the DoorOpen DoorClosed or Cooling states, this value is modified.
	 * Temp value is also fetched by the gui display for updating the temperature 
	 */
	private int temp = 35;
	private int currentFridgeRate = 0;	
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
	
	/**
	 * Instance method to initialize fridgeContext object as a singleton*/
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
	
	/**
	 * Called to initialize the FridgeContext's variables based on data from config file
	 */
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
	
	/*Called to get temperature of fridge based on config file*/
	public int getTemp() {
		return temp;
	}
	
	/*Called to set temperature of fridge based on config file*/
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	/*Called to get fridge low temperature of context based on config file*/
	public int getFridgeLow() {
		return fridgeLow;
	}

	/*Called to get fridge high temperature of context based on config file*/
	public int getFridgeHigh() {
		return fridgeHigh;
	}
	
	/*Called to get room high temperature of context based on config file*/
	public int getRoomLow() {
		return roomLow;
	}

	/*Gets room high temperature of context based on config file*/
	public int getRoomHigh() {
		return roomHigh;
	}

	/* Called to change the ridge rate, based on if door is opened, closed, or compressor is on*/
	public void setCurrentFridgeRate(int rate) {
		this.currentFridgeRate = rate;
	}
	
	/*Called to get current fridge rate */
	public int getCurrentFridgeRate() {
		return currentFridgeRate;
	}

	/*Getters for the various fridge rates - used when changing states */
	/*Called to get the warming rate when fridge door is open */
	public int getFridgeRateLossDoorOpen() {
		return fridgeRateLossDoorOpen;
	}
	
	/*Called to get the warming rate when fridge door is closed*/
	public int getFridgeRateLossDoorClosed() {
		return fridgeRateLossDoorClosed;
	}
	/*Called to get the cooling rate when fridge compressor is on */
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
	
	/*Get temperature of room */
	public int getRoomTemp() {
		return roomTemp;
	}

	/*Called to set room temperature to temperature given by the GUI
	 * ensures room temperature is within bounds given by config file*/
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
