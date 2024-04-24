/**
 * 
 */
package com.mycompany.a3;

/**
 * @author nicol
 *
 */
public interface ICollider {		//Interface for colliding objects
	
boolean collidesWith(GameObject otherObject);
void handleCollision(GameObject otherObject);
}
