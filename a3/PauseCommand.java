package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * @author nicol
 *
 */
public class PauseCommand extends Command implements ActionListener {

	private Game game;
	
	public PauseCommand (Game game) {
		
		super("Pause");
		this.game = game;
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		System.out.println("Pause Command is invoked...");
		game.pause();
	} 
}
