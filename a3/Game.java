package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

/**
 * The game class extends the form class. Game is the controller class, creates a GameWorld, Mapview, and 
 * ScoreView object. The Gui, action events, and command objects are created. Play has been replaced and removed
 * from assignment #1. The form is shown , gw's height and width is assigned to mv's, and gw.init is called.
 */
public class Game extends Form implements Runnable {	// "Controller class"

	private GameWorld gw;	// GameWorld Object
	private MapView mv;		// MapView observer object
	private ScoreView sv;	// ScoreView observer object
	private int elapsedTime;		//Time set to 20 ms	
	private boolean pause;			//If pause if enabled	
	private UITimer timer;			//Timer object for animation
	private CheckBox soundCheckBox;		//Buttons and checkboxes
	private myButton accelerateMyButton;
	private myButton brakeMyButton;
	private myButton leftMyButton;
	private myButton rightMyButton;
	private myButton positionMyButton;
	private myButton pauseMyButton;
	private  AccelerateCommand myAccelerateCommand;		//Command objects
	private  BrakeCommand myBrakeCommand;
	private  LeftCommand myLeftCommand;
	private  RightCommand myRightCommand;
	private  SetFoodConsumptionCommand mySetFoodConsumptionCommand;
	private PauseCommand myPauseCommand;
	private AboutCommand myAboutCommand;
	private ExitCommand myExitCommand;
	private SoundCommand mySoundCommand;
	private PositionCommand myPositionCommand;
	private Toolbar myToolbar;		//Toolbar object

	/**
	 * Default constructor
	 */
	public Game () {

		pause =false;
		mv = new MapView(this);
		gw = new GameWorld ();	// Objects
		sv = new ScoreView();

		gw.addObserver(mv); // Center Container
		gw.addObserver(sv);	// North Container

		this.setLayout(new BorderLayout());	// Setting up the toolbar and title of the GUI
		myToolbar = new Toolbar();
		setToolbar(myToolbar);
		this.setTitle("The Journey Game");

		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));	//Setting formats for Containers
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container southContainer = new Container(new FlowLayout());

		southContainer.getAllStyles().setPadding(Component.LEFT, 750);	// Styling the southContainer
		southContainer.getAllStyles().setPadding(Component.RIGHT, 1);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.GRAY));

		soundCheckBox = new CheckBox("Sound on/off");	// Checkbox for sound
		mySoundCommand = new SoundCommand(gw);
		soundCheckBox.setCommand(mySoundCommand);
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		myToolbar.addComponentToSideMenu(soundCheckBox);

		accelerateMyButton = new myButton("Accelerate");		// Setting up the actionCommands, buttons (etc), and keyLiseners
		myAccelerateCommand = new AccelerateCommand(gw);
		accelerateMyButton.setCommand(myAccelerateCommand);
		addKeyListener('a', myAccelerateCommand);
		myToolbar.addCommandToSideMenu(myAccelerateCommand);

		brakeMyButton = new myButton("Brake");
		myBrakeCommand = new BrakeCommand(gw);
		brakeMyButton.setCommand(myBrakeCommand);
		addKeyListener('b', myBrakeCommand);

		leftMyButton = new myButton("Left");
		myLeftCommand = new LeftCommand(gw);
		leftMyButton.setCommand(myLeftCommand);
		addKeyListener('l', myLeftCommand);

		rightMyButton = new myButton("Right");
		myRightCommand = new RightCommand(gw);
		rightMyButton.setCommand(myRightCommand);
		addKeyListener('r', myRightCommand);

		mySetFoodConsumptionCommand = new SetFoodConsumptionCommand(gw);
		addKeyListener('c', mySetFoodConsumptionCommand);

		pauseMyButton = new myButton("Pause");
		myPauseCommand = new PauseCommand(this);
		pauseMyButton.setCommand(myPauseCommand);

		positionMyButton = new myButton("Position");
		positionMyButton.setEnabled(false);
		myPositionCommand = new PositionCommand(mv);
		positionMyButton.setCommand(myPositionCommand);

		myAboutCommand = new AboutCommand(gw);
		myToolbar.addCommandToSideMenu(myAboutCommand);

		myExitCommand = new ExitCommand(gw);
		myToolbar.addCommandToSideMenu(myExitCommand);

		HelpCommand myhelpCommand = new HelpCommand(gw);
		myToolbar.addCommandToRightBar(myhelpCommand);


		westContainer.add(accelerateMyButton);	// Adding the buttons (etc) to containers
		westContainer.add(leftMyButton);
		eastContainer.add(brakeMyButton);
		eastContainer.add(rightMyButton);
		southContainer.add(positionMyButton);
		southContainer.add(pauseMyButton);

		this.addComponent(BorderLayout.WEST, westContainer);	// Adding containers to main form
		this.addComponent(BorderLayout.EAST, eastContainer);
		this.addComponent(BorderLayout.SOUTH, southContainer);
		this.addComponent(BorderLayout.NORTH,sv);
		this.addComponent(BorderLayout.CENTER,mv);

		this.show();	// Display to screen

		gw.setHeight(mv.getHeight());	// Assigning GameWorld height and width
		gw.setWidth(mv.getWidth());

		gw.init();	// Calling GameWorld's init
		gw.createSounds();		//Create Sounds
		revalidate();

		elapsedTime=20;
		timer = new UITimer(this);		//Set up timer to 20ms
		timer.schedule(elapsedTime, true, this);
	}

	@Override
	public void run() {		//Run called when timer is ticked

		gw.gameClockTick(elapsedTime);

	}
	public void pause () {		//If the pause button is pressed

		pause = !pause;		//Flip pause

		if (pause) {		//If paused, disable all buttons and enable position

			timer.cancel();

			gw.setSoundState(false);
			positionMyButton.setEnabled(true);
			soundCheckBox.setSelected(false);
			soundCheckBox.setEnabled(false);
			accelerateMyButton.setEnabled(false);
			brakeMyButton.setEnabled(false);
			leftMyButton.setEnabled(false);
			rightMyButton.setEnabled(false);
			pauseMyButton.setText("Play");

			removeKeyListener('a', myAccelerateCommand);
			removeKeyListener('b', myBrakeCommand);
			removeKeyListener('l', myLeftCommand);
			removeKeyListener('r', myRightCommand);
			removeKeyListener('c', mySetFoodConsumptionCommand);

			myAccelerateCommand.setEnabled(false);
			myToolbar.removeCommand(myAccelerateCommand);
			myAboutCommand.setEnabled(false);
			myToolbar.removeCommand(myAboutCommand);
			myExitCommand.setEnabled(false);
			myToolbar.removeCommand(myExitCommand);
			revalidate();
		}
		else {	//If not in pause, disable position, enable all buttons

			timer.schedule(elapsedTime, true, this);

			gw.setSoundState(gw.getSoundState());
			positionMyButton.setEnabled(false);
			soundCheckBox.setEnabled(true);
			accelerateMyButton.setEnabled(true);
			brakeMyButton.setEnabled(true);
			leftMyButton.setEnabled(true);
			rightMyButton.setEnabled(true);
			pauseMyButton.setText("Pause");

			addKeyListener('a', myAccelerateCommand);
			addKeyListener('b', myBrakeCommand);
			addKeyListener('l', myLeftCommand);
			addKeyListener('r', myRightCommand);
			addKeyListener('c', mySetFoodConsumptionCommand);

			myAccelerateCommand.setEnabled(true);
			myToolbar.addCommandToSideMenu(myAccelerateCommand);
			myAboutCommand.setEnabled(true);
			myToolbar.addCommandToSideMenu(myAboutCommand);
			myExitCommand.setEnabled(true);
			myToolbar.addCommandToSideMenu(myExitCommand);
			revalidate();	
		}
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}