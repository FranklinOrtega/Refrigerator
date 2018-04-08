package Refrigerator;

import javax.swing.JButton;

public abstract class GUIRefrigeratorButton extends JButton {
		/**
		 * Create the button with the proper text
		 * 
		 * @param string
		 *            the text
		 */
		public GUIRefrigeratorButton(String string) {
			super(string);
		}

		/**
		 * Tell the listener that the button has been clicked.
		 * 
		 * @param context
		 *            the Microwave context
		 * @param display
		 *            the GUI
		 */
		public abstract void inform(CoolingUnitDisplay display);
}
