package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

/**
 * MapView is a observer of the GameWorld. Whenever change occurs to GameWorld,
 * a text version of the map is written out.
 */
public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	private Point pCmpRelPrnt;
	private Game game;
	private boolean position = false;
	/**
	 * Set the layout of mapView and make a red border
	 * Default Constructor
	 */

	public MapView (Game game) {
		
		this.setLayout(new FlowLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
		gw = new GameWorld();
		this.game = game;
	}

	/**
	 * update is called when the observable is changed.
	 * @param observable, arg   Observable Object
	 */
	@Override
	public void update(Observable observable, Object arg) {	// "views class" 
		
		gw = (GameWorld) observable;
		repaint();
		if (observable instanceof GameWorld) {
		
			for (int i=0; i<gw.getObjectList().size(); i++) {	//Loop to make all possible game objects use their toString methods.
				
				if (gw.getObjectList().get(i) instanceof GameObject) {
					
					GameObject mObj = (GameObject)gw.getObjectList().get(i);
					System.out.println(mObj);
				}
			}
		}
	}

	public void paint (Graphics g) { // Does this break iterator design??
		
		super.paint(g);
		pCmpRelPrnt = new Point (this.getX(), this.getY());
		
		for (int i=0; i<gw.getObjectList().size(); i++) {	//Loop to make all possible game objects use their toString methods.
			
			if (gw.getObjectList().get(i) instanceof GameObject) {
				
				GameObject mObj = (GameObject)gw.getObjectList().get(i);
				mObj.draw(g, pCmpRelPrnt);
			}
		}
	}
	
	public void pointerPressed(int x, int y) { 		//Called when user clicks mouse, checks if user clicked an object
		
		if (game.isPause()) {
			
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator theElements = gw.getTheGameObjectCollection().getIterator();
			
			if (position) {	//If in position mode
				theElements = gw.getTheGameObjectCollection().getIterator();
				
				while (theElements.hasNext()) {
					
					GameObject curObject = (GameObject) theElements.getNext();
					
					if(curObject instanceof ISelectable) {
						
						ISelectable IScurObject = (ISelectable) curObject;
						
						if(IScurObject.isSelected()) {
							position(IScurObject, x-350,y-50);	//Check all objects if they are set to selected and move them
						}
						else {	
						}
					}
				}
			}
			
			theElements = gw.getTheGameObjectCollection().getIterator();
			
			while (theElements.hasNext()) {	
				
				GameObject curObject = (GameObject) theElements.getNext();
				
				if(curObject instanceof ISelectable) {
					
					ISelectable IScurObject = (ISelectable) curObject;
					
					if(IScurObject.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						
						IScurObject.setSelected(true);	//If user clicks on selectable object, setSelect to true
					}
					else {
						
						IScurObject.setSelected(false);	//If user does not click on an object, setSelect to false
					}
				}
			}
		}
		repaint();
	}
	public void position(ISelectable curObj,int x, int y) {	//Moves selected objects to new location

		setPosition();
		GameObject curObject2 = (GameObject) curObj;
		System.out.println("OLD X = " + curObject2.getLocationX());
		System.out.println("OLD Y = " + curObject2.getLocationY());
		curObject2.setLocationX(x);
		curObject2.setLocationY(y);
		System.out.println("NEW X = " + curObject2.getLocationX());
		System.out.println("NEW Y = " + curObject2.getLocationY());
		
	}

	public boolean isPosition() {

		return position;
	}

	public void setPosition() {

		position = !position;
	}
}