package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * @author nicol
 *
 */
public class PositionCommand extends Command implements ActionListener {
	private MapView mapView;
	
	public PositionCommand (MapView mapView) {
		
		super("Position");
		this.mapView = mapView;
	}
	
	public void actionPerformed(ActionEvent ev) {
		
		System.out.println("Position Command is invoked...");
		mapView.setPosition();
	} 
}
