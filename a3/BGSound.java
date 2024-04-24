package com.mycompany.a3;
import java.io.InputStream;
import com.codename1.media.*;
import com.codename1.ui.Display;

public class BGSound implements Runnable{		//Looping sound that the player can turn on or off

	private Media m;	//Media Object
	
	public BGSound(String file) {	//Default Constructor, checks if sound file is there
		
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Create Sound objects after calling show() or other sound error");
			Display.getInstance().exitApplication();
		} 
		
		while(m==null) {
			
		try {			//Get input stream and make media object so sound can play
			
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" +file);
			m = MediaManager.createMedia(is, "background/mp3", this);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		}
	}

	public void pause(){		//Pause sound
		
		m.pause();
	} 
	public void play(){ 		//Play sound
		
		m.play();
	} 
	public void run() {			//Run sound
		
		m.setTime(0);
		m.play();}	
} 
