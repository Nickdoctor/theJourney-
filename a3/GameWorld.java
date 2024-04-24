package com.mycompany.a3;
import java.util.ArrayList;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;


/**
 * The GameWorld class is used to set the initial state of the game which
 * includes setting up game objects and their values, creating an GameCollection
 * object that is used to store all game objects using the iterator design pattern
 * for use in the program, and to give logic to all commands the user can use. The GUI
 * will update the user with new values as they change with the game. This class follows 
 * the Observable/Observer design pattern.
 */
public class GameWorld extends Observable  { // "Data Model class (Observable)"

	private int height;		// Height of the GameWorld
	private int width;		// Width of the GameWorld
	private int gameClock;  // Stores how many "ticks" the user used to complete the game
	private int lives; 		// User has 3 lives to start with and loses 1 for each death
	private int numOfFlags; // Keeps track of how many flags (1-9) were created on start
	private boolean soundState = false; // Keeps track if sound is on (true) or off (false)
	private BGSound background;
	private Sound hitSound;
	private Sound flagSound;
	private Sound foodSound;
	private Flag f1; 		// All game objects are initialized here
	private Flag f2;
	private Flag f3;
	private Flag f4;
	private Ant a1;
	private Spider s1;
	private Spider s2;
	private FoodStation food1;
	private FoodStation food2;
	private GameObjectCollection theGameObjectCollection;	//Object that stores a collection of game objects
	private int elaspedTime;


	/**
	 * The method init is used to set up all game objects and set their values such
	 * as location, sequence number, or even color if needed. The objects are added
	 * to the collection here upon creation. All changes notify the observers.
	 */
	public void init() {

		theGameObjectCollection = new GameObjectCollection();	

		lives = 3; // Game Lives

		f1 = new Flag(200.0f, 200.0f); // Flag objects (4)
		f1.setSequenceNumber(1);
		f2 = new Flag(500.0f, 500.0f);
		f2.setSequenceNumber(2);
		f3 = new Flag(1000.0f, 800.0f);
		f3.setSequenceNumber(3);
		f4 = new Flag(100.0f, 1000.0f);
		f4.setSequenceNumber(4);
		numOfFlags = 4;
		theGameObjectCollection.add(f1);
		theGameObjectCollection.add(f2);
		theGameObjectCollection.add(f3);
		theGameObjectCollection.add(f4);

		a1 = Ant.getAnt(this); // Ant object (Always 1)
		a1.setLocationX(f1.getLocationX()); // Ant starts the game on the first flag
		a1.setLocationY(f1.getLocationY());
		theGameObjectCollection.add(a1);

		s1 = new Spider(getWidth(), getHeight()); // Spider objects (2)
		s2 = new Spider(getWidth(), getHeight());
		theGameObjectCollection.add(s1);
		theGameObjectCollection.add(s2);

		food1 = new FoodStation(1); // Food station objects
		food2 = new FoodStation(2);
		theGameObjectCollection.add(food1);
		theGameObjectCollection.add(food2);


		setChanged();
		notifyObservers();
	}

	/**
	 * The method accelerate is used to increase the speed of the ant when the user
	 * enters 'a' by 10. A few checks however are needed to make sure this method
	 * works. The ant can increase speed if it is under the max speed (starting max
	 * speed is 100) and the ant has a food level that is not 0 or less.
	 */
	public void accelerate() {

		if ((a1.getSpeed() < a1.getMaximunSpeed()) && (a1.getFoodLevel() > 0)) { // If speed is below max and ant has
			// food, increase the speed by 10
			a1.setSpeed(a1.getSpeed() + 20);
			System.out.println("New speed of ant = " + a1.getSpeed() + "\n");
		}
		if (a1.getSpeed() >= a1.getMaximunSpeed()) { // If ant is at or above max speed, no increase
			System.out.println("Max speed of ant reached, can not speed up = " + a1.getSpeed() + "\n");
		}
		if (a1.getFoodLevel() <= 0) { // If ant has no food left, no increase
			System.out.println("Ant is out of food level, speed can not be increased!\n");
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * The brake method does the opposite of the accelerate method, it will decrease
	 * the ant's speed by 10. The food level does not matter to slow the ant down,
	 * however the ant must be above speed 0 to decrease so the value does not go
	 * into the negatives.
	 */
	public void brake() {

		if (a1.getSpeed() > 0) { // If speed is above 0, decrease ant speed level by 10
			a1.setSpeed(a1.getSpeed() - 20);
			System.out.println("New speed of ant = " + a1.getSpeed() + "\n");
		}
		if (a1.getSpeed() <= 0) { // If speed is 0 or negative, prevent user from lowering the speed
			System.out.println("Ant's speed is at 0, can not decrease anymore = " + a1.getSpeed() + "\n");
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * The handingLeft method takes the ant's heading and subtracts it by 5 to have
	 * the ant "turn" 5 degrees to the left. A check is needed to see if the ant is
	 * at heading 0-4. This is done because at 0 degrees for example, if the ant
	 * would subtract 5 to its heading, it would end up in the negatives which does
	 * not make sense. To prevent this , heading is checked to be 0-4, if it is ,
	 * hard set the value, however 5-359 is free to just subtract 5.
	 */
	public void headingLeft() {

		switch (a1.getHeading()) { // Special edge case check
		case (4):
			a1.setHeading(359);
		break;
		case (3):
			a1.setHeading(358);
		break;
		case (2):
			a1.setHeading(357);
		break;
		case (1):
			a1.setHeading(356);
		break;
		case (0):
			a1.setHeading(355);
		break;
		default:
			a1.setHeading(a1.getHeading() - 20); // Default is to subtract 5 to current heading
			break;
		}
		System.out.println("Heading changed 20 degrees left = " + a1.getHeading() + "\n");
		setChanged();
		notifyObservers();
	}

	/**
	 * The handingRight method takes the ant's heading and adds 5 to have the ant
	 * "turn" 5 degrees to the right. A check is needed to see if the ant is at
	 * heading 355-359. This is done because at 355 degrees for example, if the ant
	 * would add 5 to its heading, it would end up being 360, which does not make
	 * sense. To prevent this , heading is checked to be 355-359, if it is , hard
	 * set the value, however 0-354 is free to just add 5.
	 */
	public void headingRight() {

		switch (a1.getHeading()) { // Special edge case check
		case (355):
			a1.setHeading(0);
		break;
		case (356):
			a1.setHeading(1);
		break;
		case (357):
			a1.setHeading(2);
		break;
		case (358):
			a1.setHeading(3);
		break;
		case (359):
			a1.setHeading(4);
		break;
		default:
			a1.setHeading(a1.getHeading() + 20); // Default is to add 5 to current heading
			break;
		}
		System.out.println("Heading changed 20 degrees right = " + a1.getHeading() + "\n");
		setChanged();
		notifyObservers();
	}

	/**
	 * The method foodConsumptionRate is to "turn on" food consumption for the ant.
	 * This means when turned on, for every game tick, the ant will have it's food
	 * level decrease by 10. If the user is to use the command again, it will turn
	 * food consumption off.
	 */
	public void foodConsumptionRate() {

		if (a1.getFoodConsumptionRate() <= 0) { // If the food consumption is off, turn it on and set it to 10

			a1.setFoodConsumption();
			System.out.print("Food consumption rate has been set at = " + a1.getFoodConsumptionRate() + "\n");
			System.out.print("If you want to disable food consumption for the ant, enter 'c' again\n");

		} else if (a1.getFoodConsumptionRate() > 0) { // If the food consumption is on, turn it off and set it to 0

			a1.setFoodConsumptionRate(0);
			System.out.print("Food consumption rate has been set at = " + a1.getFoodConsumptionRate() + "\n");
			System.out.print("If you want to enable food consumption for the ant, enter 'c' again\n");
		}
		setChanged();
		notifyObservers();
	}

	/*
	 * The method flagCollision is used to simulate a collision between the ant and
	 * the flag since there is no collision checking in this version of the game.
	 * This is done by taking in the flag number from the user and checking ants
	 * last reached flag. If the flag the user entered is one higher than the last
	 * flag reached ( user enters 2 and has reached 1) , the game will update the
	 * last flag reached and notify the user. If the previous flag is entered ,tell
	 * the user they already reached that flag. If a random flag is entered, tell
	 * the user wrong flag. If the user gets to the last flag, exit the game.
	 * 
	 * @param flag The flag number the user entered
	 */
	public void flagCollision(int flag) {

		if (flag - a1.getLastFlagReached() == 1) { // Correct flag entered

			flagSound.play();
			a1.setLastFlagReached(a1.getLastFlagReached() + 1);
			System.out.println("Next flag reached!\n" + "New flag goal = " + (flag + 1) + "\n");

		} else if (flag - a1.getLastFlagReached() <= 0) { // Previous flag entered

			System.out.println(
					"This flag has already been reached, current flag goal: " + (a1.getLastFlagReached() + 1) + "\n");
		} else {

			System.out.println("This is the wrong flag, current flag goal: " + (a1.getLastFlagReached() + 1) + "\n");// Incorrect flag entered
		}
		if (a1.getLastFlagReached() == numOfFlags) { // Last flag entered

			flagSound.play();
			System.out.println("Game over, you win!");
			System.out.println("Total time: " + gameClock);
			System.exit(0);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * The foodStationCollision method is used to pretend a collision between an ant
	 * and a food station occurs. A random station is picked then has the ant refill
	 * its food level equal to the size of the station. Then the food station fades
	 * color, loses its capacity, a new food station object is created and added to
	 * the collection, and the user is notified in console.
	 */
	public void foodStationCollision(int foodStationNumber) {

		foodSound.play();

		if ( foodStationNumber== 1) { // Food station 1 is picked

			System.out.println("Food station collided at: " + food1.getLocationX());
			a1.setFoodLevel(food1.getCapacity() + a1.getFoodLevel());
			food1.setCapacity(0);
			theGameObjectCollection.remove(food1);
			food1 = new FoodStation(1);
			theGameObjectCollection.add(food1);
			System.out.println("New food station made at: " + food1.getLocationX() + "\n");
		}

		if (foodStationNumber == 2) { // Food station 2 is picked at random

			System.out.println("Food 2 station collided at: " + food2.getLocationX());
			a1.setFoodLevel(food2.getCapacity() + a1.getFoodLevel());
			food2.setCapacity(0);
			theGameObjectCollection.remove(food2);
			food2 = new FoodStation(2);
			theGameObjectCollection.add(food2);
			System.out.println("New food 2 station made at: " + food2.getLocationX() + "\n");
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * The method SpiderCollision is used to pretend a collision between the ant and
	 * the spider has occurred. A few things change. First, we check if the ant is
	 * at 0 health and has a life to spare. If so, this means the ant has died and a
	 * life is taken away. Having problems with creating new ant objects leads me to
	 * just reset the same ant object back to its starting state. Next we check if
	 * the ant has health to lose, if so, lower the ant's health, lower the ant's
	 * max speed, and fade the ant's color. Next we check if there are no more lives
	 * yet, if so, close the game. Finally we check if the ant's speed needs to be
	 * adjusted since if we lower max speed, the ant needs to respect that value
	 * with it's current speed.
	 */
	public void spiderCollision() {

		hitSound.play();

		if (a1.getHealthLevel() <= 2 && lives > 0) { // Lose a life

			System.out.println("Ant has run out of health, -1 life");
			lives--;
			a1.reset();
		} else if (a1.getHealthLevel() >= 0) { // Normal hit on ant

			a1.setHealthLevel(a1.getHealthLevel() - 2);
			a1.setMaximunSpeed(a1.getMaximunSpeed() - 20);
			a1.setAntGreenColor(a1.getAntGreenColor() + 55);
			a1.setAntBlueColor(a1.getAntBlueColor() + 55);
			a1.setColor(ColorUtil.rgb(255, a1.getAntGreenColor(), a1.getAntBlueColor()));
		}
		if (lives <= 0) { // Game over if no more lives

			System.out.print("Game over, you failed!");
			System.exit(0);
		}
		if (a1.getSpeed() > a1.getMaximunSpeed()) { // Lower the speed of the ant if above new max

			a1.setSpeed(a1.getMaximunSpeed());
		}
		System.out.print("Spider Collided with ant, new health level: " + a1.getHealthLevel() + "\n");
		System.out.print("Spider Collided with ant, new Max speed : " + a1.getMaximunSpeed() + "\n");
		System.out.print("Spider Collided with ant, new speed level: " + a1.getSpeed() + "\n");

		setChanged();
		notifyObservers();
	}

	/**
	 * The method gameClockTick is used for when a tick has occurred which just
	 * means we simulate time passing. When this happens, all movable objects are
	 * adjusted to their new locations, the ant's food level is lowered by 10, and
	 * the game clock adds one.
	 */
	public void gameClockTick(int elapsedTime) {

		this.elaspedTime = elapsedTime;
		IIterator theElements = theGameObjectCollection.getIterator();

		while (theElements.hasNext()) {// Loop to make all possible movable objects use their move methods.

			GameObject gw = (GameObject) theElements.getNext();

			if (gw instanceof Movable) {
				Movable mObj = (Movable) gw;
				mObj.move(elapsedTime);
			}
		}

		System.out.println("New Location for ant: " + Math.round(a1.getLocationX() * 10.0) / 10.0 + ", "
				+ Math.round(a1.getLocationY() * 10.0) / 10.0);
		System.out.println("New Location for spider 1: " + Math.round(s1.getLocationX() * 10.0) / 10.0 + ", "
				+ Math.round(s1.getLocationY() * 10.0) / 10.0);
		System.out.println("New Location for spider 2: " + Math.round(s2.getLocationX() * 10.0) / 10.0 + ", "
				+ Math.round(s2.getLocationY() * 10.0) / 10.0);

		if (a1.getFoodLevel() <= 0) {

			System.out.println("Ant is out of food! Can not move!");
		} else if (a1.getFoodLevel() >=10) {

			a1.setFoodLevel(a1.getFoodLevel() - a1.getFoodConsumptionRate()); // Ant's food level is subtracted by 10
		} else {	// To prevent neg values

			a1.setFoodLevel(0);
		}

		theElements = theGameObjectCollection.getIterator();

		while(theElements.hasNext()) {

			ICollider  otherObj = (ICollider ) theElements.getNext();

			if (a1.collidesWith((GameObject) otherObj)) {

				a1.handleCollision((GameObject) otherObj);
			}
		}
		gameClock++;

		setChanged();
		notifyObservers();
	}

	public void createSounds() {
		background = new BGSound("background.mp3");
		hitSound = new Sound("hit.mp3");
		flagSound = new Sound("flag.mp3");
		foodSound = new Sound("food.mp3");
	}


	public void setObjectList(ArrayList<GameObject> objectList) {	// Change list to new list if needed

		theGameObjectCollection.setObjectList(objectList);
	}

	public ArrayList<GameObject> getObjectList() {					// Return object list if needed

		return theGameObjectCollection.getObjectList();
	}

	public void setSoundState() {									// Flip state of sound

		this.soundState = !this.soundState;

		if (soundState) {

			background.play();
		}
		if (!soundState) {

			background.pause();
		}
		setChanged();
		notifyObservers();
	}

	public int getGameClock() {										// The following are setters and getters, some 
		// may not be needed, however are here just in case
		return gameClock;
	}

	public void setGameClock(int gameClock) {

		this.gameClock = gameClock;
	}

	public int getLives() {

		return lives;
	}

	public void setLives(int lives) {

		this.lives = lives;
	}

	public boolean isSoundState() {

		return soundState;
	}

	public void setSoundState(boolean soundState) {

		this.soundState = soundState;

		if (soundState) {

			background.play();
		}
		if (!soundState) {

			background.pause();
		}
		setChanged();
		notifyObservers();
	}

	public int getHealth() {

		return a1.getHealthLevel();
	}

	public int getLastFlag() {

		return a1.getLastFlagReached();
	}

	public int getFoodLevel() {

		return a1.getFoodLevel();
	}

	public boolean getSoundState() {

		return soundState;
	}

	public int getHeight() {

		return height;
	}

	public void setHeight(int height) {

		this.height = height;
	}

	public int getWidth() {

		return width;
	}

	public void setWidth(int width) {

		this.width = width;
	}

	public int getElaspedTime() {

		return elaspedTime;
	}

	public void setElaspedTime(int elaspedTime) {

		this.elaspedTime = elaspedTime;
	}

	public GameObjectCollection getTheGameObjectCollection() {

		return theGameObjectCollection;
	}

	public void setTheGameObjectCollection(GameObjectCollection theGameObjectCollection) {

		this.theGameObjectCollection = theGameObjectCollection;
	}
}