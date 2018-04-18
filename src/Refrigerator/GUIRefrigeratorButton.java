package Refrigerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

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
			super.setFont(new Font("Arial", Font.PLAIN, 13));
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
