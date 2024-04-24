package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import javafx.scene.shape.DrawMode;

/**
 * Public class FoodStation extends Fixed class. Food stations are used to fill up the ant's food level when 
 * it comes into contact with it. After an ant refills it's food level, the food station fades to light green
 * and becomes empty. A new food station spawns in.
 *
 */
public class FoodStation extends Fixed {

	private int capacity ;
	private static Random r = new Random();
	private int foodStationNumber ;

	/**
	 * Default constructor for food station. Sets a random size and capacity for the station.
	 * Location is random and the color is set using the parents setColor method. 
	 */
	public FoodStation (int foodStationNumber) {

		super((r.nextFloat()+ r.nextInt(999)),(r.nextFloat()+ r.nextInt(999)) );
		setSize( (int)(50 + r.nextInt(150))); // 10-50
		capacity = (int)getSize()/2;
		super.setColor( ColorUtil.GREEN);
		this.foodStationNumber = foodStationNumber;
	}

	/**
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return output
	 */
	@Override
	public String toString () {

		String sup = super.toString();
		String output = "FoodStation: " + sup + " size=" + getSize()+ " capacity=" + getCapacity();
		return output;	
	}

	public int getFoodStationNumber() {

		return foodStationNumber;
	}

	public void setFoodStationNumber(int foodStationNumber) {

		this.foodStationNumber = foodStationNumber;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {

		int px = (int) pPtrRelPrnt.getX()+40; // pointer location relative to
		int py = (int) pPtrRelPrnt.getY()+50; // parent's origin
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getLocationX());// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getLocationY());// to parent's origin

		if ( (px >= xLoc) && (px <= xLoc+this.getSize()) && (py >= yLoc) && (py <= yLoc+this.getSize())) {

			return true;
		} else {

			return false;
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {		//Draw object if user selects or does not select object

		int x = (int)getLocationX() +(int)pCmpRelPrnt.getX();
		int y = (int)getLocationY() +(int)pCmpRelPrnt.getY();

		if(isSelected()) {

			g.setColor(getColor());
			g.drawRect(x -getSize()/2, y -getSize()/2, getSize(), getSize());
			g.setColor(0);
			g.drawString(Integer.toString(getCapacity()), (x -getSize()/2) + (getSize()/4), (y -getSize()/2)  + (getSize()/4));
		} else {

			g.setColor(getColor());
			g.fillRect(x -getSize()/2, y -getSize()/2, getSize(), getSize());
			g.setColor(0);
			g.drawString(Integer.toString(getCapacity()), (x -getSize()/2) + (getSize()/4), (y -getSize()/2)  + (getSize()/4));
		}
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
}