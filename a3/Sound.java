package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.*;
import com.codename1.ui.Display;


public class Sound  {

	private Media m;
	
	public Sound(String file) {
		
		if (Display.getInstance().getCurrent() == null) {
			
			System.out.println("Create Sound objects after calling show() or other sound error");
			Display.getInstance().exitApplication();
		} 
		
		try {
			
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" +file);
			m = MediaManager.createMedia(is, "audio/mp3");
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
}

	public void play(){
		
		m.setTime(0);
		m.play();
	} 
} 