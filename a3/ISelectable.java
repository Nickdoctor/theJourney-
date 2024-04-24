package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * @author nicol
 *
 */
public interface ISelectable {		//Interface for selectable objects

public void setSelected(boolean b);
public boolean isSelected();
public boolean contains (Point pPtrRelPrnt, Point pCmpRelPrnt);
public void draw(Graphics g, Point pCmpRelPrnt);

}
