package Refrigerator;

public class FreezerDoorCloseButton extends GUIRefrigeratorButton {
	/**
	 * The button for freezer door close
	 * 
	 * @param string text of the button
	 */
	public FreezerDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	
	public void inform(CoolingUnitDisplay display) {
		FreezerDoorCloseManager.instance()
				.processEvent(new FreezerDoorCloseEvent(display));
	}
}
