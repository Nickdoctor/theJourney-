package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * SetFoodConsumptionCommand is used when the user wants to turn on food consumption in the ant
 */
public class SetFoodConsumptionCommand extends Command implements ActionListener {
	
	private GameWorld gw; 

	/**
	 * Default constructor 
	 * @param gw
	 */
	public SetFoodConsumptionCommand (GameWorld gw) {
		
		super("Set Food Consumption");
		this.gw = gw;
	}
	
	/**
	 * actionPerformed will call GameWorlds foodConsumptionRate 
	 * @param ev ActionEvent
	 */
	@Override
	public void actionPerformed (ActionEvent ev) {
		
		System.out.println("Set Food Consumption command is invoked...");
		gw.foodConsumptionRate();
	} 
}