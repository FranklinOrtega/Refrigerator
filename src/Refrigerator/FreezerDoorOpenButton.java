package Refrigerator;

public class FreezerDoorOpenButton extends GUIRefrigeratorButton {
	/**
	 * The button for freezer door open
	 * 
	 * @param string text of the button
	 */
	public FreezerDoorOpenButton(String string) {
		super(string);
	}

	/**
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	public void inform(CoolingUnitDisplay display) {
		FreezerDoorOpenManager.instance()
				.processEvent(new FreezerDoorOpenEvent(display));
	}
}
