package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

/**
 * myButton is a custom Button class. It is used to create buttons of my own style
 */
public class myButton extends Button {
	
	/**
	 * Default Constructor
	 * @param input String
	 */
	public myButton (String input) {
		
		super(input); // Button's Constructor 
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 0, 0));;
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		this.getAllStyles().setPadding(Component.TOP, 10);
		this.getAllStyles().setPadding(Component.BOTTOM, 10);
		this.getAllStyles().setPadding(Component.LEFT, 10);
		this.getAllStyles().setPadding(Component.RIGHT, 10);
	}
}
