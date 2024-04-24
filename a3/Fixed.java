package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * The Fixed class is an abstract class that extends GamObject. It is used by flags and food stations.
 */
public abstract class Fixed extends GameObject implements ISelectable {

	private boolean isSelected;
	
	/**
	 * Default constructor
	 */
	public Fixed() {
		
	}
	
	/**
	 * Constructor to set fixed object's locations when created
	 * @param xLocation
	 * @param yLocation
	 */
	public Fixed (float xLocation, float yLocation) {
		super(xLocation,yLocation);
	}
	
	public void setLocationX(float x) {
		
		getLocation().setX(x);
	}

	/**
	 * @param location
	 */
	public void setLocationY(float y) {
		
		getLocation().setY(y);
	}
	
	//a way to mark an object as "selected" or not
	public void setSelected(boolean b) {
		
		isSelected = b;
	}

	//a way to test whether an object is selected
	public boolean isSelected(){
		
		return isSelected;
	}

	//a way to determine if a pointer is "in" an object
	//pPtrRelPrnt is pointer position relative to the parent origin
	//pCmpRelPrnt is the component position relative to the parent origin
	public abstract boolean contains (Point pPtrRelPrnt, Point pCmpRelPrnt);

	//a way to "draw" the object that knows about drawing
	//different ways depending on "isSelected"
	public abstract void draw(Graphics g, Point pCmpRelPrnt);

}
