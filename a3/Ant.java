package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * The Ant class extends the movable class since it can update its location and also implements IFoodie since the ant
 * has a food level. The ant is what the player is controlling. It must avoid spiders, eat food, and reach all flags 
 * in the shortest amount of time. Ant implements the singleton design pattern.
 */

public class Ant extends Movable implements IFoodie{

	private static Ant theAnt; // maintain a single global reference to the ant
	private int maximunSpeed;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int antGreenColor;	// Used to save the state of ant's color when it is needed to fade when hit
	private int antBlueColor;
	private GameWorld gw;

	/**
	 * Default constructor for ant, uses super color since ant's do not have their own setColor method. Sets color, size, location,
	 * max speed, health level, last flag, heading, speed , and blue and green color.
	 */
	private Ant (GameWorld gw) {

		super.setColor(ColorUtil.rgb(255, 0, 0));
		setSize(25);
		setLocationX(100);
		setLocationY(100);
		this.maximunSpeed = 160;
		this.healthLevel= 10;
		this.lastFlagReached =1;
		setHeading(0);
		setSpeed(60);
		antBlueColor =0;
		antGreenColor =0;
		this.gw = gw;
	}

	/**
	 * Used for the singleton design pattern, allows ant to only exist once
	 * @return theAnt 
	 */
	public static Ant getAnt (GameWorld gw) {
		if (theAnt == null) {
			theAnt = new Ant(gw);
		}
		return theAnt;
	}

	/**
	 * @return maximunSpeed 
	 */
	public int getMaximunSpeed() {
		return maximunSpeed;
	}

	/**
	 * @param maximunSpeed
	 */
	public void setMaximunSpeed(int maximunSpeed) {
		this.maximunSpeed = maximunSpeed;
	}

	/**
	 * @return foodConsumptionRate
	 */
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	/**
	 * @param foodConsumptionRate
	 */
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	/**
	 * @return healthLevel
	 */
	public int getHealthLevel() {
		return healthLevel;
	}

	/**
	 * @param healthLevel
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * @return lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * @param lastFlagReached
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}

	/**
	 * Sets the food consumption to 10 which is used to turn on food consumption for clock ticks
	 */
	@Override
	public void setFoodConsumption() {
		// setting their food consumption rate and waiting for next clock tick
		foodConsumptionRate = 1;
	}

	/**
	 * @return antGreenColor
	 */
	public int getAntGreenColor() {
		return antGreenColor;
	}

	/**
	 * @param antGreenColor
	 */
	public void setAntGreenColor(int antGreenColor) {
		this.antGreenColor = antGreenColor;
	}

	/**
	 * @return antBlueColor
	 */
	public int getAntBlueColor() {
		return antBlueColor;
	}

	/**
	 * @param antBlueColor
	 */
	public void setAntBlueColor(int antBlueColor) {
		this.antBlueColor = antBlueColor;
	}

	/**
	 * Overrides the toString method so that ant objects are correctly printed for the user
	 * @return output Return output string to print
	 */
	@Override
	public String toString () {
		String sup = super.toString();
		String output = "Ant: "+ sup +"heading=" + getHeading() + " speed=" + getSpeed() +
				" size=" + getSize() + "\n     maxSpeed=" + getMaximunSpeed() + " foodConsumptionRate=" + getFoodConsumptionRate(); 
		return output;
	}

	/**
	 * Override the move method to check if the ant has a positive food level. If so, allow it to move.
	 */
	@Override
	public void move (int elapsedTime) {
		if (getFoodLevel() >0) {

			super.move(elapsedTime);
		} else {
			System.out.println("Ant is out of food level, can not move!!");
		}
	}
	/**
	 * The method reset is used to set the ant back to it's starting state. This is used for when the ant loses a life.
	 */
	public void reset() {

		super.setColor(ColorUtil.rgb(255, 0, 0));
		setLocationX(getLocationX());
		setLocationY(getLocationY());
		setHealthLevel(10);
		setHeading(0);
		setSpeed(60);
		setMaximunSpeed(160);
		setFoodLevel(1000);
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		g.setColor(getColor());
		g.fillArc((int)getLocationX() +(int)pCmpRelPrnt.getX() -getSize()/2, (int)getLocationY() + (int) pCmpRelPrnt.getY() - getSize()/2, 2*getSize(), 2*getSize(), 0, 360);
	}

	@Override
	public void handleCollision(GameObject otherObject) {

		if (otherObject instanceof Spider) {
			gw.spiderCollision();
		}

		if (otherObject instanceof FoodStation) {
			FoodStation foodStation = (FoodStation) otherObject;
			gw.foodStationCollision(foodStation.getFoodStationNumber());

		}
		if( otherObject instanceof Flag) {
			Flag flag = (Flag)otherObject; 
			gw.flagCollision(flag.getSequenceNumber());
		} 

		if (!getCollisionList().contains(otherObject)) {
			getCollisionList().add(otherObject);
		}	
	}
}