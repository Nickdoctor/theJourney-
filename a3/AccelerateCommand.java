package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * AccelerateCommand is the command that is used when the user intends to accelerate.
 *
 */
public class AccelerateCommand extends Command implements ActionListener {

	private GameWorld gw; 

	/**
	 * Default constructor
	 * @param gw GameWorld
	 */
	public AccelerateCommand (GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}

	/**
	 * GameWorld's accelerate is called and the user is notified
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("Accelerate command is invoked...");
		gw.accelerate();
	} 
}