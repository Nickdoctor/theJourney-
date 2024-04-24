package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * SoundCommand is used when the user wants to change the state of sound to ON/OFF
 */
public class SoundCommand extends Command implements ActionListener {

	private GameWorld gw; 
	
	/**
	 * Default constructor 
	 * @param gw
	 */
	public SoundCommand (GameWorld gw) {
		
		super("Sound on/off");
		this.gw = gw;
	}

	/**
	 * actionPerformed will call GameWorlds setSoundState
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Sound Command is invoked...");
		gw.setSoundState();
	}
}
