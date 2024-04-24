package com.mycompany.a3;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;



/**
 * The parent of two abstract children; GameObject class is used as the highest parent on a hierarchy of different game objects.
 * Each object has a location, size, and color. The structure is as follows: 
 * 															    Game Object
 * 															  /             \
 * 														 Movable           Fixed
 * 														/    \            /     \
 * 													 Ant   Spider   Food Station Flag
 */
public abstract class GameObject implements IDrawable , ICollider { 

	private int size; 
	private Point location; 
	private int color;
	private List<GameObject> collisionList;

	/**
	 * Default constructor for GameObject, sets up a new object of type point 
	 */
	public GameObject () {

		location = new Point();
		collisionList =new ArrayList<GameObject>();
	}

	public GameObject (float xLocation, float yLocation) {

		location = new Point();
		location.setX(xLocation);
		location.setY(yLocation);
		collisionList =new ArrayList<GameObject>();
	}

	/**
	 * @return color
	 */
	public int getColor() {

		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(int color) {

		this.color = color;
	}

	/**
	 * @return size
	 */
	public int getSize() {

		return size;
	}

	/**
	 * @param size
	 */
	public void setSize (int size) {

		this.size = size;
	}

	/**
	 * @return location
	 */
	public Point getLocation() {

		return location;
	}

	/**
	 * @param location
	 */
	public void setLocationX(float location) {

		this.location.setX(location);
	}

	/**
	 * @param location
	 */
	public void setLocationY(float location) {

		this.location.setY(location);
	}

	/**
	 * @return location
	 */
	public float getLocationX() {

		return this.location.getX();
	}

	/**
	 * @return location
	 */
	public float getLocationY() {

		return this.location.getY();
	}
	/**
	 * @return String
	 */
	@Override
	public String toString () {

		return "loc=" +Math.round(location.getX()*10.0)/10.0+ "," + Math.round(location.getY()*10.0)/10.0 + " color= [" + ColorUtil.red(color)+ "," +
				ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]";
	}

	public boolean collidesWith(GameObject otherObject) {	//Checks for collition using box method

		Boolean collides = false;
		float thisX = getLocationX();
		float thisY = getLocationY();
		float otherX = otherObject.getLocationX();
		float otherY = otherObject.getLocationY();

		if (thisX < otherX + otherObject.getSize() && thisX + getSize() > otherX &&		//If one object is inside another
			thisY < otherY + otherObject.getSize() && thisY + getSize()  > otherY) {
			collides =true;
		}
		else {	//Remove objects from collision list if not colliding to prevent double checks

			if (collisionList.contains(otherObject)) {
				collisionList.remove(otherObject);
			}
		}
		if (collisionList.contains(otherObject)) { //Checking to ensure no double checks

			collides =false;
		}

		return collides;
	}
	public void handleCollision(GameObject otherObject) {		//Fixed will handle this

	}

	public List<GameObject> getCollisionList() {
		return collisionList;
	}

	public void setCollisionList(List<GameObject> collisionList) {
		this.collisionList = collisionList;
	}
	public void addToList (GameObject obj) {
		collisionList.add(obj);
	}

}