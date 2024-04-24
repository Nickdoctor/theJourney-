package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * CollideWithFoodStationCommand is the command that is used when the user wants to simulate a collision with
 * a food station.
 */
public class CollideWithFoodStationCommand extends Command implements ActionListener {

	private GameWorld gw; 

	/**
	 * Default Constructor
	 * @param gw
	 */
	public CollideWithFoodStationCommand (GameWorld gw) {
		super("Collide With Food Station");
		this.gw = gw;
	}

	/**
	 * actionPerformed is the method called when the user wants to collide with a food station. This is
	 * done by calling the GameWorld's method foodStaitonCollision.
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		System.out.println("Collide With FoodStation Command is invoked...");
		//gw.foodStationCollision();
	} 		
}