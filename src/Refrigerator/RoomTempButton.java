package Refrigerator;

public class RoomTempButton extends GUIRefrigeratorButton {
	/**
	 * The button to set room temperature
	 * 
	 * @param string
	 */
	public RoomTempButton(String string) {
		super(string);
	}

	/**
	 * Creates a FreezerDoorOpenEvent, so the interested states can get it.
	 * Call the context, so it can forward it through the event
	 */
	@Override
	public void inform(CoolingUnitDisplay display) {
		display.setRoomTempInput(display.getRoomTempInput());
	}
}
