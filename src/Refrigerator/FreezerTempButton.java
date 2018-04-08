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
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	// uncomment this method when other classes are implemented
//	public void inform(CoolingUnitDisplay display) {
//		FreezerTempManager.instance()
//				.processEvent(new FreezerTempEvent(display));
//	}
	// Delete this method when other classes are implemented
	public void inform(CoolingUnitDisplay display) {
		RefrigeratorDoorCloseManager.instance()
				.processEvent(new RefrigeratorDoorCloseEvent(display));
	}
}
