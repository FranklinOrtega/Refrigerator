package Refrigerator;

import java.util.Observable;

public abstract class CoolingUnitDisplay extends Observable{
	protected static RefrigeratorContext context;
	protected static CoolingUnitDisplay instance;
	
	protected CoolingUnitDisplay() {
		instance = this;
		context = RefrigeratorContext.instance();
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
		context.initialize();
	}
	
	/**
	 * Refrigerator display indicator methods
	 */
	
	public abstract void turnRefrigeratorLightOn();
	
	public abstract void turnRefrigeratorLightOff();
	
	public abstract void setRefrigeratorCooling();
	
	public abstract void setRefrigeratorIdle();
	
	public abstract void setRefrigeratorTemp(int temperature);
	
	/**
	 * Freezer display indicator methods
	 */
	
	public abstract void turnFreezerLightOn();
	
	public abstract void turnFreezerLightOff();
	
	public abstract void setFreezerCooling();
	
	public abstract void setFreezerIdle();
	
	public abstract void setFreezerTemp(int temperature);
}
