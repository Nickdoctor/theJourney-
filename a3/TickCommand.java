package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * TickCommand is used when the user wants increase the count of tick by 1
 */
public class TickCommand extends Command implements ActionListener {

	private GameWorld gw; 

	/**
	 * Default constructor 
	 * @param gw
	 */
	public TickCommand (GameWorld gw) {
		
		super("Tick");
		this.gw = gw;
		
	}

	/**
	 * actionPerformed will call GameWorlds gameClockTick
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Tick Command is invoked...");
		gw.gameClockTick(gw.getElaspedTime());
	} 
}