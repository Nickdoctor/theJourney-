package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * The Flag class extends Fixed class. The flag must be collected in order for the user to win. Flags can not change color
 * after they are made and their locations are preset.
 */
public class Flag extends Fixed {

	private int sequenceNumber;	//Defines the flag number 

	/**
	 * Default constructor for flag, sets up color to blue, sets size to 10, sets locations to a default value if 
	 * client class forgets to set a location. 
	 */
	public Flag () {
		
		super.setColor(ColorUtil.CYAN);
		setSize(100);
		setLocationX(100);
		setLocationY(100); // defaults for flags, Location will not be random
	}
	
	/**
	 * Constructor to set flag locations on creation
	 * @param xLocation
	 * @param yLocation
	 */
	public Flag (float xLocation, float yLocation) {
	
		super(xLocation,yLocation);
		super.setColor(ColorUtil.BLUE);
		setSize(100);
		setLocationX(xLocation);
		setLocationY(yLocation); // defaults for flags, Location will not be random
	}

	/**
	 * @return sequenceNumber
	 */
	public int getSequenceNumber() {
		
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 */
	public void setSequenceNumber(int sequenceNumber) {

		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * Flags can not change color once they are made
	 */
	@Override
	public void setColor (int color) {
		
	}

	/**
	 * @return output
	 */
	@Override
	public String toString () {

		String sup = super.toString();
		String output = "Flag: " + sup + " size=" + getSize()+ " seqNum=" + getSequenceNumber(); 
		return output;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {		//Draw the flag to the screen if it is selected or not selected

		if (isSelected()) {
			
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
			g.drawString(Integer.toString(getSequenceNumber()), (x1) + (getSize()/4) +20, (y1) +  (getSize()/4));
			
		} else {
			
			g.setColor(getColor());
			
			int x1 = (int)getLocationX() +(int)pCmpRelPrnt.getX() -getSize()/2;
			int y1 = (int)getLocationY() +(int)pCmpRelPrnt.getY() - getSize()/2;
			int x2 = (int)getLocationX() +(int)pCmpRelPrnt.getX() +getSize()/2;
			int y2 = (int)getLocationY() +(int)pCmpRelPrnt.getY() - getSize()/2;
			int x3 = (int)getLocationX() +(int)pCmpRelPrnt.getX();
			int y3 = (int)getLocationY() +(int)pCmpRelPrnt.getY() + getSize()/2;
			
			int[] xPoints = {x1,x2,x3};
			int[] yPoints = {y1,y2,y3};
			
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(0);
			g.drawString(Integer.toString(getSequenceNumber()), (x1) + (getSize()/4) +20, (y1) +  (getSize()/4));
		}
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int px = (int) pPtrRelPrnt.getX()+40; // pointer location relative to
		int py = (int) pPtrRelPrnt.getY()+50; // parent's origin
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getLocationX());// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getLocationY());// to parent's origin
		
		if ( (px >= xLoc) && (px <= xLoc+this.getSize()) && (py >= yLoc) && (py <= yLoc+this.getSize())) { //Checls if user clicks on flag
			
			return true;
		} else {
			
			return false;
		}
	}
}
