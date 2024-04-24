package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * BrakeCommand is the command that is used when the user intends to slow the ant down.
 */
public class BrakeCommand extends Command implements ActionListener {

	private GameWorld gw; 

	/**
	 * Default Constructor
	 * @param gw
	 */
	public BrakeCommand (GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}

	/**
	 * ActionPerformed is the method called when the user wants to slow the ant down. GameWorld's
	 * brake command is called and the user is notified.
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("Brake command is invoked...");
		gw.brake();
	} 
}