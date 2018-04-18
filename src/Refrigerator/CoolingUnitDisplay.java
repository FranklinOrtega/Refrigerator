package Refrigerator;

/**
 * CoolingUnitDisplay has the basic outline for methods the gui display classes should extend.
 * It also keeps track of the fridge and freezer context classes as a reference to itself.
 */
public abstract class CoolingUnitDisplay{
	protected static FridgeContext fridgeContext;
	// TODO: add freezerContext
	protected static CoolingUnitDisplay instance;
	
	protected CoolingUnitDisplay() {
		instance = this;
		fridgeContext = FridgeContext.instance();
		// TODO: initialize Freezer context
	}
	
	/**
	 * Gets the singleton instance
	 */
	public static CoolingUnitDisplay instance() {
		return instance;
	}
	
	/**
	 * Initializes the fridge and freezer context
	 */
	public void initalize() {
		fridgeContext.initialize();
		// TODO: initialize Freezer context
	}
	
	/**
	 * Refrigerator display indicator methods
	 */
	
	public abstract void turnFridgeLightOn();
	
	public abstract void turnFridgeLightOff();
	
	public abstract void setFridgeCooling();
	
	public abstract void setFridgeIdle();
	
	public abstract void setFridgeTemp(int temperature);
	
	public abstract int getFridgeTemp();
	
	public abstract void updateCurrentFridgeTemp();
	
	public abstract int getRoomTempInput();
	
	public abstract int getFridgeTempInput();
	
	public abstract int getFreezerTempInput();
	
	public abstract void setRoomTempInput(int temperature);
	
	/**
	 * Freezer display indicator methods
	 */
	
	public abstract void turnFreezerLightOn();
	
	public abstract void turnFreezerLightOff();
	
	public abstract void setFreezerCooling();
	
	public abstract void setFreezerIdle();
	
	public abstract void setFreezerTemp(int temperature);
	
	public abstract int getFreezerTemp();
}
