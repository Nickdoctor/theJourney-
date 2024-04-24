/**
 * 
 */
package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;



/**
 * @author nicol
 *
 */
public interface IDrawable {		//Interface for drawable objects
	
	public void draw(Graphics g, Point pCmpRelPrnt);
}
