package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * CollideWithSpiderCommand is the command that is used when the user wants to simulate
 *  a collision with a spider
 */
public class CollideWithSpiderCommand extends Command implements ActionListener {

	private GameWorld gw; 

	/**
	 * Default Constructor
	 * @param gw GameWorld
	 */
	public CollideWithSpiderCommand (GameWorld gw) {
		super("Collide With Spider");
		this.gw = gw;
	}

	/**
	 * actionPerformed is what is called when the user wants to collide with a spider
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("Collide With Spider Command is invoked...");
		gw.spiderCollision();
	} 
}