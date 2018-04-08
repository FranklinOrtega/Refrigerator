package Refrigerator;

public class FridgeDoorOpenButton extends GUIRefrigeratorButton {
		/**
		 * The button for fridge door open
		 * 
		 * @param string
		 */
		public FridgeDoorOpenButton(String string) {
			super(string);
		}

		/**
		 * Creates a RefrigeratorDoorOpenEvent, so the interested states can get it.
		 * Call the context, so it can forward it through the event
		 */
		@Override
		public void inform(CoolingUnitDisplay display) {
			RefrigeratorDoorOpenManager.instance()
				.processEvent(new RefrigeratorDoorOpenEvent(display));
		}
}
