package Refrigerator;

public class FridgeDoorCloseButton extends GUIRefrigeratorButton {
	/**
	 * The button for fridge door close
	 * 
	 * @param string the text of the button
	 */
	public FridgeDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Creates a RefrigeratorDoorCloseEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	public void inform(CoolingUnitDisplay display) {
		FridgeDoorCloseManager.instance()
				.processEvent(new FridgeDoorCloseEvent(display));
	}
}
