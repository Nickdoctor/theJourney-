package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.location.Location;
import com.codename1.ui.Graphics;

/**
 * The Spider class extends Movable class. The spider is what the player is trying to avoid. Making contact with the spider
 * causes the player to lose health. The spider moves randomly and is black in color. A random speed and size is assigned 
 * as well. 
 */
public class Spider extends Movable{

	private float width;
	private float height;
	/**
	 * Default constructor for the spider class. Sets size, location, speed, and heading 
	 * to a random value. Color is set to black using the parent setColor method.
	 */
	public Spider (float width, float height) {
		
		Random r = new Random();
		setSize(2 *(30 + r.nextInt(50)));
		setLocationX(r.nextInt((int) width));
		setLocationY(r.nextInt((int) height));
		super.setColor(ColorUtil.BLACK);
		setSpeed(60 + r.nextInt(100));
		setHeading(r.nextInt(359));
		this.width = width;
		this.height = height;
		
	}
	
	/**
	 * The method setColor is an override. Spiders are not allowed to change color after 
	 * being made.
	 */
	@Override
	public void setColor (int Color) {
		//Can not change color
	}
	
	/**
	 * The move method is an override. Spiders can not leave the play area, so checks are put into 
	 * place to ensure they can not leave. When close to the edge of the screen, they will turn around
	 * and head towards the center. The parents move method is still used. The spider's heading is then
	 * updated either 5 left or 5 right. 
	 */
	@Override
	public void move (int elapsedTime) { 
		
		if (getLocationX() >width) {			//Right side	
			
			setHeading(getHeading()+ 180);
			System.out.println("RIGHT DETECTED!!!!!!");
		}	
		if (getLocationX() <=1) {			//Left side
			
			setHeading(getHeading()-180);
			System.out.println("LEFT DETECTED!!!!!!");
		}
		if (getLocationY() >height) {			//bottom side
			
			setHeading(getHeading()+ 180);
			System.out.println("BOTTOM DETECTED!!!!!!");
		}
		if (getLocationY() <=1) {			//Top side
			
			setHeading(getHeading()- 180);
			System.out.println("TOP DETECTED!!!!!!");
		}
		
		super.move(elapsedTime);
		
		Random r= new Random();
		
		if (r.nextInt(2) == 1){				//Either right or left turn
			
			setHeading(getHeading() + r.nextInt(15));
		}
		if (r.nextInt(2) == 0) {
			
			setHeading(getHeading() - r.nextInt(15));
		}
	}
	
	/**
	 * @return output
	 */
	@Override
	public String toString () {
		
		String sup = super.toString();
		String output = "Spider: " + sup + " heading=" + getHeading() + " speed=" + getSpeed() +
			   " size=" + getSize();
		return output;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(getColor());
		
		int x1 = (int)getLocationX() +(int)pCmpRelPrnt.getX() -getSize()/2;
		int y1 = (int)getLocationY() +(int)pCmpRelPrnt.getY() - getSize()/2;
		int x2 = (int)getLocationX() +(int)pCmpRelPrnt.getX() +getSize()/2;
		int y2 = (int)getLocationY() +(int)pCmpRelPrnt.getY() - getSize()/2;
		int x3 = (int)getLocationX() +(int)pCmpRelPrnt.getX();
		int y3 = (int)getLocationY() +(int)pCmpRelPrnt.getY() + getSize()/2;
		
		int[] xPoints = {x1,x2,x3};
		int[] yPoints = {y1,y2,y3};
		
		g.drawPolygon(xPoints, yPoints, 3);
		g.setColor(0);
	}
}
