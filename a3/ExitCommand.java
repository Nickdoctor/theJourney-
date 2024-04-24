package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * ExitCommand is the command that is used when the user wants exit the game
 */
public class ExitCommand extends Command implements ActionListener {

	/**
	 * Default Constructor
	 * @param gw
	 */
	public ExitCommand (GameWorld gw) {
		super("Exit");
		
	}

	/**
	 * actionPerformed is what is called when the user wants to exit the game. A dialog box
	 * is presented to the user which allows them to select yes or no to exit. Clicking yes
	 * calls the display.exitApplication() method, while selecting no does nothing.
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("Exit Command is invoked...");
		Command myYesCommand = new Command("Yes");
		Command myNoCommand = new Command("No");
		Command c = Dialog.show("Are you sure you want to exit?", "Exit?", myYesCommand, myNoCommand);
		if (c == myYesCommand) {
			Display.getInstance().exitApplication();
		}
		if (c == myNoCommand) {
			
		}	
	}
}
