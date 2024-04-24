package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * RightCommand is used when the user wants to turn the ant right
 */
public class RightCommand extends Command implements ActionListener {
	
	private GameWorld gw; 

	public RightCommand (GameWorld gw) {
		
		super("Right");
		this.gw = gw;
	}
	/**
	 * actionPerformed is called when the user wants to turn right. Gameworld's 
	 * turn right is called
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Right command is invoked...");
		gw.headingRight();
	} 
}