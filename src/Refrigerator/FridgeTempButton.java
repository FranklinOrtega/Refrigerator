package Refrigerator;

public class FridgeTempButton extends GUIRefrigeratorButton {
	/**
	 * The button to set fridge temperature
	 * 
	 * @param string
	 */
	public FridgeTempButton(String string) {
		super(string);
	}

	/**
	 * Creates a FridgeTempEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	public void inform(CoolingUnitDisplay display) {
		display.setFridgeTemp(display.getFridgeTempInput());
	}
}
