package Refrigerator;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * ConfigLoader allows the user to get and set key value pairs from the specified
 * config file. fileName is the name of the config file located in the root directory
 * of the application.
 */
public class ConfigLoader {

	private Properties prop = new Properties();
	private String fileName = null;
	
	public ConfigLoader(String fileName) {
		setFile(fileName);
	}
	
	/**
	 * Sets the fileName to work with new a new file. The file is located in the
	 * application's root working directory.
	 * @param fileName the name of the new file
	 */
	public void setFile(String fileName) {
		this.fileName = fileName;
		
		// if the config file doesn't exist, create one with the default values
		File file = new File(fileName);
		if (!file.exists()) {
			infoBox("<html>The config/preferences file for the refrigerator was not"
					+ " found. The config file should be placed at<br><br><b>"
					+ file.getAbsolutePath() + "</b><br><br>The program will now create a"
					+ " default one.</html>", "Config file not found");
			try {
				file.createNewFile();
				createDefaultConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets a string value associated with a key
	 * @param key the string key
	 * @return the string associated with the key if one is found, otherwise, null
	 */
	public String getValue(String key) {
		try {
			InputStream inputStream = new FileInputStream(fileName);
			prop.load(inputStream);
			String value = prop.getProperty(key);
			inputStream.close();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; // error occurred
	}
	
	/**
	 * Gets the value associated with a key as an integer. Throws up a dialog box
	 * if the associated value with the key is not of type string.
	 * @param key the string key
	 * @return the integer if one is found, otherwise, -1
	 */
	public int getValueAsInt(String key) {
		InputStream inputStream = null;
		try {
			// converts the string to an integer if valid integer
			inputStream = new FileInputStream(fileName);
			prop.load(inputStream);
			int value = Integer.valueOf(prop.getProperty(key));
			inputStream.close();
			return value;
		} catch (NumberFormatException e) {
			// the string retrieved from the key-value is not a valid integer
			
			// make sure the file is closed
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// display error message box
			infoBox("<html>There has been an error while trying to get a value from the "
					+ "configuation file.<br><br>"
					+ "The value associated with the key is not an integer.<br><br>"
					+ "Key in Question: <b>" + key + "</b></html>", "Error in loading configuation");
			return -1; // no valid integer retrieved -- error occurred
		} catch (IOException e) {
			e.printStackTrace();
			return -1; // loading error occurred
		}
	}
	
	/**
	 * Sets the value with a given key
	 * @param key the string key
	 * @param value the string value
	 */
	public void setValue(String key, String value) {
		try {
			OutputStream outputStream = new FileOutputStream(fileName);
			prop.setProperty(key, value);
			prop.store(outputStream, null); // save the property to the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a default config file with the default settings
	 * specified in the project instructions.
	 */
	public void createDefaultConfig() {
		setValue("FridgeLow", "37");
		setValue("FridgeHigh", "41");
		setValue("FreezerLow", "-9");
		setValue("FreezerHigh", "0");
		setValue("RoomLow", "50");
		setValue("RoomHigh", "75");
		setValue("FridgeRateLossDoorClosed", "30");
		setValue("FridgeRateLossDoorOpen", "2");
		setValue("FreezerRateLossDoorClosed", "10");
		setValue("FreezerRateLossDoorOpen", "1");
		setValue("FridgeCompressorStartDiff", "2");
		setValue("FreezerCompressorStartDiff", "1");
		setValue("FridgeCoolRate", "20");
		setValue("FreezerCoolRate", "30");
	}
	
	/**
	 * Shows a simple alert box
	 * @param infoMessage the message to display in the message box
	 * @param titleBar the titlebar's text
	 */
	private void infoBox(String infoMessage, String titleBar) {
		JLabel label = new JLabel(infoMessage);
		label.setFont(new Font("arial", Font.PLAIN, 14));
		JOptionPane.showMessageDialog(null, label, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
}
