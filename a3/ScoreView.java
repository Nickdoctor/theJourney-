package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

/**
 * ScoreView is an observer to Gameworld. When GameWorld is changed, ScoreView
 * will update and the user can see the change in the north container
 */
public class ScoreView extends Container implements Observer {
	//Labels that will change will updates
	private Label timeLabel;
	private Label livesLabel;
	private Label flagLabel;
	private Label foodLabel;
	private Label healthLabel;
	private Label soundLabel;

	/**
	 * Default Constructor. Sets layout/style of the container and puts in labels
	 */
	public ScoreView () {

		this.setLayout(new FlowLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.GRAY));
		this.getAllStyles().setPadding(Component.LEFT, 400);
		this.getAllStyles().setPadding(Component.RIGHT, 1);
		
		timeLabel = new Label("Time: 0");
		this.addComponent(timeLabel);
		livesLabel = new Label("Lives Left: 3");
		this.addComponent(livesLabel);
		flagLabel = new Label("Last Flag Reached: 1");
		this.addComponent(flagLabel);
		foodLabel = new Label("Food Level: 100");
		this.addComponent(foodLabel);
		healthLabel = new Label("Health Level: 10");
		this.addComponent(healthLabel);
		soundLabel = new Label("Sound: OFF");
		this.addComponent(soundLabel);
	}

	/**
	 * update is called when the GameWorld is changed. The labels are updated
	 * to the corrected values.
	 */
	@Override
	public void update(Observable observable, Object arg) {	// "Views Class" 
		
		if (observable instanceof GameWorld) {
			
			GameWorld gw = (GameWorld) observable;
			timeLabel.setText("Time: " + gw.getGameClock());
			livesLabel.setText("Lives Left: " + gw.getLives());
			flagLabel.setText("Last Flag Reached: " + gw.getLastFlag());
			foodLabel.setText("Food Level: " + gw.getFoodLevel());
			healthLabel.setText("Health Level: " + gw.getHealth());
			
			if (gw.getSoundState()) {
				
				soundLabel.setText("Sound: ON");
			}else {
				
				soundLabel.setText("Sound: OFF");
			}
		}
		revalidate();	//Refresh screen
	}
}
