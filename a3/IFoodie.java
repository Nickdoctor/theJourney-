package com.mycompany.a3;

/**
 * IFoodie is an interface class that is used for movable objects that need to use a food level. 
 * Ant is an example of one such object. 
 */
public interface IFoodie {

	/**
	 * The method setFoodConsumption is used for classes that implement IFoodie to
	 * ensure they are enabling some sort of food level feature.
	 */
	public void setFoodConsumption();
}
