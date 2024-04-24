package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * LeftCommand is used when the user wants to turn the ant left
 */
public class LeftCommand extends Command implements ActionListener {
	
	private GameWorld gw; 

	/**
	 * Default Constructor
	 * @param gw
	 */
	public LeftCommand (GameWorld gw) {
		
		super("Left");
		this.gw = gw;
	}
	
	/**
	 * actionPerformed is called when the user wants to turn left. Gameworld's 
	 * turn left is called
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Left command is invoked...");
		gw.headingLeft();
	} 
		
}
