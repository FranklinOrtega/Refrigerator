package Refrigerator;

public class FreezerTempButton extends GUIRefrigeratorButton {
	/**
	 * The button to set freezer temperature
	 * 
	 * @param string
	 */
	public FreezerTempButton(String string) {
		super(string);
	}

	/**
	 * Gets the value from the GUI freezer temperature button
	 * and sets the freezer temperature in the GUI display
	 */
	@Override
	public void inform(CoolingUnitDisplay display) {
		display.setFreezerTemp(display.getFreezerTempInput());
	}
}
