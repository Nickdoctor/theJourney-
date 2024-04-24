package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * AboutCommand is issued when the about label is pressed by the user. The user is notified and
 * a dialog box is presented on the screen that explains information about the project they are using.
 * The user can close the box and no further action is taken.
 */
public class AboutCommand extends Command implements ActionListener {

	/**
	 * Default constructor
	 * @param gw GameWorld
	 */
	public AboutCommand (GameWorld gw) {
		
		super("About");
	}

	/** Method used when about is pressed, pressing OK exits the dialog box
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("About Command is invoked...");
		Command myOKCommand = new Command("Ok");
		Command c = Dialog.show("About Section","Nicolas Gugliemo\n Csc 133\n v3.0\n Fall 2023\n", myOKCommand);
		if (c == myOKCommand) {
		}
	}
}