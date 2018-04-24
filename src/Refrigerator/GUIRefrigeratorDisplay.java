package Refrigerator;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Implementation of RefrigeratorDisplay. Has no conditionals.
 *
 */
public class GUIRefrigeratorDisplay extends CoolingUnitDisplay implements ActionListener {
	private static SimpleDisplay frame;

	/**
	 * Creates the frame and displays it.
	 */
	private GUIRefrigeratorDisplay() {
		frame = new SimpleDisplay();
		frameLayout();
		initialize();
	}

	// Window size and location
	private void frameLayout() {
		frame.setLocation(100, 30);
	}

	/**
	 * Inner class because the outer class extends GUIFridgeDisplay.
	 *
	 */
	private class SimpleDisplay extends JFrame {
		// GUI Refrigerator Buttons 
		private GUIRefrigeratorButton setRoomTempBttn = new RoomTempButton("Set room temp");
		private GUIRefrigeratorButton setFridgeTempBttn = new FridgeTempButton("Set desired fridge temp");
		private GUIRefrigeratorButton setFreezerTempBttn = new FreezerTempButton("Set desired freezer temp");
		private GUIRefrigeratorButton fridgeDoorOpener = new FridgeDoorOpenButton("Open fridge door");
		private GUIRefrigeratorButton fridgeDoorCloser = new FridgeDoorCloseButton("Close fridge door");
		private GUIRefrigeratorButton freezerDoorOpener = new FreezerDoorOpenButton("Open freezer door");
		private GUIRefrigeratorButton freezerDoorCloser = new FreezerDoorCloseButton("Close freezer door");
		
		// Labels
	    private JLabel roomTemp = new JLabel("Room temperature");
	    private JLabel desiredFridgeTemp = new JLabel("Desired fridge temp");
	    private JLabel desiredFreezerTemp = new JLabel("Desired freezer temp");
	    private JLabel status = new JLabel("Status");
	    private JLabel fridgeLight = new JLabel("Fridge light off");
	    private JLabel fridgeTemp = new JLabel("Temp = 			"); // Has no initial value, temp changes constantly
	    private JLabel fridgeState = new JLabel("Fridge cooling");
	    private JLabel freezerLight = new JLabel("Freezer light off");
	    private JLabel freezerTemp = new JLabel("			"); // Has no initial value, temp changes constantly
	    private JLabel freezerState = new JLabel("Freezer cooling");
	    private JLabel emptyGrid = new JLabel(""); // To fulfill empty grid spots
	    
	    // Text fields
	    private JTextField roomTempInput = new JTextField(); 
	    private JTextField fridgeTempInput = new JTextField();  
	    private JTextField freezerTempInput = new JTextField();  
	    
	    // Panels used for displaying Refrigerator Status
	    private JPanel mainPanel;
	    private JPanel topPanel; 
	    private JPanel middlePanel;
	    private JPanel bottomPanel;
		

		/**
		 * Set up the JFrame
		 */
		private SimpleDisplay() {
			super("Refrigerator");
			
			// Window size
			int windowWidth = 700;
			int windowHeight = 500;
			
			// Action for the close button.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Instantiate panels
			mainPanel = new JPanel()
			{
			    @Override
			    public Dimension getPreferredSize()
			    {
			        return new Dimension(windowWidth, windowHeight);
			    }
			};
			
			topPanel = new JPanel();
			middlePanel = new JPanel();
			bottomPanel = new JPanel();
			
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			mainPanel.setLayout(new GridLayout(3, 1)); 
			
			/* ***************** This is the original code ***********************
			topPanel.setLayout(new GridLayout(3,3,40,10));
			topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
			************************************ **/
			
			/* **** Testing Code for GridBagLayout Delete if it doesn't work ***** */
			
			final boolean shouldFill = true;
		    final boolean shouldWeightX = true;
		    final boolean RIGHT_TO_LEFT = false;
		 
	        if (RIGHT_TO_LEFT) {
	            topPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        }
		 

//			topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		        
		    topPanel.setLayout(new GridBagLayout());
		    GridBagConstraints gridBagConstrains = new GridBagConstraints();
//		    if (shouldFill) {
//		    //natural height, maximum width
//		    	gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
//		    }
//		    
		    if (shouldWeightX) {
		    gridBagConstrains.weightx = 0.1;
		    }
		    
		    /* Top Panel Labels */
		    // Label: Room Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.gridx = 0;
		    gridBagConstrains.gridy = 0; // First Row
		    roomTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		    roomTemp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		    topPanel.add(roomTemp, gridBagConstrains);

		    // Label: Fridge Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.gridx = 0;
		    gridBagConstrains.gridy = 1; // Second Row
		    desiredFridgeTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		    desiredFridgeTemp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		    topPanel.add(desiredFridgeTemp, gridBagConstrains);

		    // Label: Freezer Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.gridx = 0;
		    gridBagConstrains.gridy = 2; // Third Row
		    desiredFreezerTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		    desiredFreezerTemp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		    topPanel.add(desiredFreezerTemp, gridBagConstrains);
		    
		    /* Top Panel Inputs */
		    // JTextField: Room Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets text field height
		    gridBagConstrains.gridwidth = 1;
		    gridBagConstrains.gridx = 1;
		    gridBagConstrains.gridy = 0; // Third Row
		    topPanel.add(roomTempInput, gridBagConstrains);

		    // JTextField: Fridge Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets text field height
		    gridBagConstrains.gridx = 1;
		    gridBagConstrains.gridy = 1; // First Row
		    topPanel.add(fridgeTempInput, gridBagConstrains);

		    // JTextField: Freezer Temp
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets text field height
		    gridBagConstrains.gridwidth = 1;
		    gridBagConstrains.gridx = 1;
		    gridBagConstrains.gridy = 2; // Second Row
		    topPanel.add(freezerTempInput, gridBagConstrains);

		    
		    /* Top Panel Buttons */
		    // RoomTempBttn
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets button height
		    gridBagConstrains.ipadx = 50;
		    gridBagConstrains.weightx = 0;
		    gridBagConstrains.gridwidth = 0; 
		    gridBagConstrains.gridx = 2; // Sets the origin of the button
		    gridBagConstrains.gridy = 0; // First Row
		    setRoomTempBttn.setHorizontalAlignment(SwingConstants.LEFT);
		    topPanel.add(setRoomTempBttn, gridBagConstrains);
		    
		    // FridgeTempBttn
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets button height
		    gridBagConstrains.weightx = 0;
		    gridBagConstrains.gridwidth = 0; 
		    gridBagConstrains.gridx = 2; // Sets the origin of the button
		    gridBagConstrains.gridy = 1; // Second Row
		    setFridgeTempBttn.setHorizontalAlignment(SwingConstants.LEFT);
		    topPanel.add(setFridgeTempBttn, gridBagConstrains);
		    
		    // FreezerTempBttn
		    gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstrains.ipady = 10;  // Sets button height
		    gridBagConstrains.weightx = 0.0;
		    gridBagConstrains.gridwidth = 0; // Sets button width according to the gridLayout
		    gridBagConstrains.gridx = 2; // Sets the origin of the button
		    gridBagConstrains.gridy = 2; // Third Row
		    setFreezerTempBttn.setHorizontalAlignment(SwingConstants.LEFT);
		    topPanel.add(setFreezerTempBttn, gridBagConstrains);
		 
		    
			
		    /* ***************** End of Testing Code for GridBagLayout ************** */
			
			middlePanel.setLayout(new GridLayout(2,2,10,10));
			middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, windowWidth/2));
			
			bottomPanel.setLayout(new GridLayout(4,2,10,10));
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
			
			
			
			// Button setup
			int bttnWidth = 160;
			int bttnHeight = 36;
			// setRoomTempBttn.setMargin(new Insets(35,10,35,10));
			
			//  Initializing private variables with user inputs
			
			// Top Panel
//			topPanel.add(roomTemp);
//			topPanel.add(roomTempInput);
//			topPanel.add(setRoomTempBttn);
//			topPanel.add(desiredFridgeTemp);
//			topPanel.add(fridgeTempInput);
//			topPanel.add(setFridgeTempBttn);
//			topPanel.add(desiredFreezerTemp);
//			topPanel.add(freezerTempInput);
//			topPanel.add(setFreezerTempBttn);
			
			// Middle Panel, has refrigerator door buttons
			middlePanel.add(fridgeDoorOpener, BorderLayout.EAST);
			middlePanel.add(fridgeDoorCloser, BorderLayout.WEST);
			middlePanel.add(freezerDoorOpener, BorderLayout.EAST);
			middlePanel.add(freezerDoorCloser, BorderLayout.WEST);
			
			// Bottom Panel
			bottomPanel.add(status, BorderLayout.EAST);
			bottomPanel.add(emptyGrid, BorderLayout.WEST); // This is just to cover one spot of the Grid
			bottomPanel.add(fridgeLight, BorderLayout.EAST);
			bottomPanel.add(freezerLight, BorderLayout.WEST);
			bottomPanel.add(fridgeTemp, BorderLayout.EAST);
			bottomPanel.add(freezerTemp, BorderLayout.WEST);
			bottomPanel.add(fridgeState, BorderLayout.EAST);
			bottomPanel.add(freezerState, BorderLayout.WEST);
			
			// Add Panels to main panel
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(middlePanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			
			//Add main panel to Content Pane
			getContentPane().add(mainPanel);
			
			// Add Action Listeners to Buttons
			setRoomTempBttn.addActionListener(GUIRefrigeratorDisplay.this);
			setFridgeTempBttn.addActionListener(GUIRefrigeratorDisplay.this);
			setFreezerTempBttn.addActionListener(GUIRefrigeratorDisplay.this);
			fridgeDoorCloser.addActionListener(GUIRefrigeratorDisplay.this);
			fridgeDoorOpener.addActionListener(GUIRefrigeratorDisplay.this);
			freezerDoorOpener.addActionListener(GUIRefrigeratorDisplay.this);
			freezerDoorCloser.addActionListener(GUIRefrigeratorDisplay.this);
			
			pack();
			setVisible(true);
		}
	}
	
	/**
	 * Do the initializations to make the context an observer
	 */
	private void initialize() {
		fridgeContext.initialize();
		freezerContext.initialize();
	}

	/**
	 * No conditionals. Let the clicked button do the hard work.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		((GUIRefrigeratorButton) event.getSource()).inform(this);
	}


	@Override
	public void turnFridgeLightOn() {
		frame.fridgeLight.setText("Fridge Light On");
	}


	@Override
	public void turnFridgeLightOff() {
		frame.fridgeLight.setText("Fridge Light Off");
	}


	@Override
	public void setFridgeCooling() {
		frame.fridgeState.setText("Fridge Cooling");
	}


	@Override
	public void setFridgeIdle() {
		frame.fridgeState.setText("Fridge idle");
	}


	@Override
	public void setRoomTempInput(int temperature) {
		int low = fridgeContext.getRoomLow();
		int high = fridgeContext.getRoomHigh();
		if (temperature >= low && temperature <= high) {
			fridgeContext.setRoomTemp(temperature);
			freezerContext.setRoomTemp(temperature);
		} else {
			if (temperature == 500) { // 500 is returned when input is invalid
				JOptionPane.showMessageDialog(null, "Invalid entry please try again.");
			} else {
				JOptionPane.showMessageDialog(null, temperature 
						+ " is out of range.\nRoom temperature range is between " + low + " and " + high);
			}
		}
		frame.roomTempInput.setText("");
	}


	@Override
	public void setFridgeTemp(int temperature) {
		int low = fridgeContext.getFridgeLow();
		int high = fridgeContext.getFridgeHigh();
		if (temperature >= low && temperature <= high) {
			fridgeContext.setTemp(temperature);
		} else {
			if (temperature == 500) { // 500 is returned when input is invalid
				JOptionPane.showMessageDialog(null, "Invalid entry please try again.");
			} else {
				JOptionPane.showMessageDialog(null, temperature 
						+ " is out of range.\nFridge temperature range is between " + low + " and " + high);
			}
		}
		frame.fridgeTempInput.setText("");
		updateCurrentFridgeTemp();
	}
	
	
	/**
	 * Updates the fridge's current temperature in the gui
	 */
	@Override
	public void updateCurrentFridgeTemp() {
		frame.fridgeTemp.setText("Fridge temp " + fridgeContext.getTemp());
	}
	

	@Override
	public int getRoomTempInput() {
		String roomTempStr = frame.roomTempInput.getText();
		try {
			return Integer.valueOf(roomTempStr);
		} catch (NumberFormatException nfe) {
			return 500; // Return 500 because room temperature is never set to it
		}
	}
	
	
	@Override
	public int getFridgeTempInput() {
		try {
			return Integer.valueOf(frame.fridgeTempInput.getText());
		} catch (NumberFormatException nfe) {
			return 500; // Return 500 because fridge is never set to this number.
		}
	}
	
	
	@Override
	public int getFridgeTemp() {
		return fridgeContext.getTemp();
	}
	
	/* ************ Freezer Implementation **************** */
	

	@Override
	public int getFreezerTemp() {
		 return freezerContext.getTemp();
	}

	@Override
	public int getFreezerTempInput() {
		try {
			return Integer.valueOf(frame.freezerTempInput.getText());
		} catch (NumberFormatException nfe) {
			return 500; // Return 500 because freezer is never set to this number.
		}
	}
	

	@Override
	public void setFreezerTemp(int temperature) {
		int low = freezerContext.getFreezerLow();
		int high = freezerContext.getFreezerHigh();
		if (temperature >= low && temperature <= high) {
			freezerContext.setTemp(temperature);
		} else {
			if (temperature == 500) { // 500 is returned when input is invalid
				JOptionPane.showMessageDialog(null, "Invalid entry please try again.");
			} else {
				JOptionPane.showMessageDialog(null, temperature 
						+ " is out of range.\nFreezer temperature range is between " + low + " and " + high);
			}
		}
		frame.freezerTempInput.setText("");
		updateCurrentFreezerTemp();
	}
	
	@Override
	public void setFreezerCooling() {
		frame.freezerState.setText("Freezer Cooling");
	}


	@Override
	public void setFreezerIdle() {
		frame.freezerState.setText("Freezer idle");
	}
	
	@Override
	public void turnFreezerLightOn() {
		frame.freezerLight.setText("Freezer Light On");
	}


	@Override
	public void turnFreezerLightOff() {
		frame.freezerLight.setText("Freezer Light Off");
	}


	/**
	 * Updates the freezer's current temperature in the gui
	 */
	@Override
	public void updateCurrentFreezerTemp() {
		frame.freezerTemp.setText("Freezer temp " + freezerContext.getTemp());
	}
	
	/* ************ End of Freezer Implementation **************** */
	
	
	/**
	 * Starts the Refrigerator system
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		try {
			// Set Look & Feel on any platform
			UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());} 
	    catch (UnsupportedLookAndFeelException 
	    		| ClassNotFoundException 
	    		| InstantiationException 
	    		| IllegalAccessException e) {
	    	e.printStackTrace();
	    }
	

		CoolingUnitDisplay display = new GUIRefrigeratorDisplay(); //Create and show the GUI.
	}
	

}