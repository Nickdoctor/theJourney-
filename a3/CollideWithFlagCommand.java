package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * CollideWithFlagCommand is the command that is used when the user wants to simulate a flag collision.
 */
public class CollideWithFlagCommand extends Command implements ActionListener {

	private GameWorld gw; 
	private int flag;	//Flag Number 

	/**
	 * Default constructor
	 * @param gw
	 */
	public CollideWithFlagCommand (GameWorld gw) {
		super("Collide With Flag");
		this.gw = gw;
	}

	/**
	 * actionPerformed is the method that is called when the user wants to simulate a flag
	 * collision. This is done by having a dialog box appear for the user to enter a number
	 * into the box which is the flag number. If not empty and not a char, the dialog box
	 * accepts the input.
	 * @param ev ActionEvent 
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Collide With Flag Command is invoked...");
		
		TextField myTextField = new TextField();
		Command myOKCommand = new Command("OK");
		Command c = Dialog.show("Enter the flag", myTextField,myOKCommand );
		
		if (c == myOKCommand) {
			try {
				if (myTextField.getText() != "") {
					flag =Integer.parseInt(myTextField.getText());
					gw.flagCollision(flag);
				}
			} catch (NumberFormatException e) {
				System.out.println("Enter number, not char");
			} 
		} 
	}
}