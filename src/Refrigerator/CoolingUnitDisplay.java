package Refrigerator;

import java.util.Observable;

public abstract class CoolingUnitDisplay extends Observable{
	protected static FridgeContext fridgeContext;
	// TODO: add freezerContext
	protected static CoolingUnitDisplay instance;
	
	protected CoolingUnitDisplay() {
		instance = this;
		fridgeContext = FridgeContext.instance();
		// TODO: initialize Freezer context
	}
	
	public static CoolingUnitDisplay instance() {
		return instance;
	}
	
	/**
	 * For singleton
	 * 
	 * @return the object
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
	
	
	
	//van: adding method displayCurrentFridgeTemp 
	public abstract void  DisplayCurrentFridgeTemp();
	
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
