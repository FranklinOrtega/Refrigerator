package Refrigerator;

public class FreezerDoorCloseButton extends GUIRefrigeratorButton {
	/**
	 * The button for fridge door open
	 * 
	 * @param string
	 */
	public FreezerDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	
//	public void inform(CoolingUnitDisplay display) {
//		FreezerDoorCloseManager.instance()
//				.processEvent(new FreezerDoorCloseEvent(display));
//	}
	// Change this method when other classes are implemented
	public void inform(CoolingUnitDisplay display) {
		RefrigeratorDoorCloseManager.instance()
				.processEvent(new RefrigeratorDoorCloseEvent(display));
	}
}
