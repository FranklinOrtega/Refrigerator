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
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	// uncomment this method when other classes are implemented
//	public void inform(CoolingUnitDisplay display) {
//		FridgeTempManager.instance()
//				.processEvent(new FridgeTempEvent(display));
//	}
	// Delete this method when other classes are implemented
	public void inform(CoolingUnitDisplay display) {
		RefrigeratorDoorCloseManager.instance()
				.processEvent(new RefrigeratorDoorCloseEvent(display));
	}
}
