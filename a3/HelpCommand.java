package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/*
 * HelpCommand is the used when the user wants to find out what keys are available to press
 * for the game
 */
public class HelpCommand extends Command implements ActionListener {
 
	/**
	 * Default Constructor
	 * @param gw
	 */
	public HelpCommand (GameWorld gw) {
		
		super("Help");
		
	}

	/**
	 * actionPerformed is the method that is called when the user presses help button.
	 * A dialog box appears that informs the user of all the keys they can use. They
	 * can then close the box
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Help Command is invoked...");
		Command myOKCommand = new Command("Ok");
		Command c = Dialog.show("Help Section","'a'-Accelerate\n'b'-Brake\n'l'-Turn Left\n"
				+ " 'r'-Turn Right\n 'c'-Set Food Consumption\n", myOKCommand);
		if (c == myOKCommand) {
		
		}
	}
}