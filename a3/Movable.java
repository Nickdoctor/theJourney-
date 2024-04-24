package com.mycompany.a3;

/** 
 * The Movable class extends GameObject and is used by Ants and Spiders. Movable objects can update their location based
 * on speed and heading. Some movable objects will have a food level as well.
 */
public abstract class Movable extends GameObject {

	private int heading;
	private int speed;
	private int foodLevel;
	private float deltaX;
	private float deltaY;

	/**
	 * Default constructor for Movable. Sets food level to 100.
	 */
	public Movable () {
		
		this.foodLevel = 1000;
	}
	
	/**
	 * @return heading
	 */
	public int getHeading() {
		
		return heading;
	}
	
	/**
	 * @param heading
	 */
	public void setHeading(int heading) {
		
		this.heading = heading;
	}
	/**
	 * @return speed
	 */
	public int getSpeed() {
		
		return speed;
	}
	
	/**
	 * @param speed
	 */
	public void setSpeed(int speed) {
		
		this.speed = speed;
	}
	
	/**
	 * @return foodLevel
	 */
	public int getFoodLevel() {
		
		return foodLevel;
	}
	
	/**
	 * @param foodLevel
	 */
	public void setFoodLevel(int foodLevel) {
		
		this.foodLevel = foodLevel;
	}

	/**
	 * The method move is used to update movable objects based on their heading and speed. This is done by taking the cos 
	 * of 90- the current heading in radians * speed for the x direction and sin of 90- the current heading in radians 
	 * * speed for the y direction.
	 * @param elapsedTime 
	 */
	public void move (int elapsedTime) {
		
		deltaX = (float) Math.cos(Math.toRadians(90-getHeading())) * ((getSpeed() *elapsedTime)/1000);
		deltaY = (float) Math.sin(Math.toRadians(90-getHeading())) * ((getSpeed() *elapsedTime)/1000);
		setLocationX(getLocationX()+deltaX);
		setLocationY(getLocationY()+deltaY);
	}
}