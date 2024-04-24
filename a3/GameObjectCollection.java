package com.mycompany.a3;
import java.util.ArrayList;

/**
 * GameObjectCollection sets up an ArrayList that holds all created GameObjects. This class
 * follows the Iterator design pattern
 */
public class GameObjectCollection implements ICollection {

	private ArrayList objectList;	//ArrayList for objects

	/**
	 * Default Constructor
	 */
	public GameObjectCollection () {

		objectList = new ArrayList<>();
	}

	/**
	 * add allows the client to add objects to the arraylist without knowing how it works
	 * @param newObject Object
	 */
	public void add(Object newObject) {

		objectList.add(newObject);
	}
	
	public void remove (Object oldObject) {
		
		objectList.remove(oldObject);
	} 

	/**
	 * getIterator returns a new object of type GameObjectCollectionIterator
	 */
	public IIterator getIterator() {

		return new GameObjectCollectionIterator();
	}

	/**
	 * The GameObjectCollectionIterator is needed to follow the iterator design pattern,
	 * it is for the client to find out if the collection has a next or to get a next in 
	 * the list
	 */
	private class GameObjectCollectionIterator implements IIterator {

		private int currentIndex;	//Index of the list

		/**
		 * Default Constructor
		 */
		public GameObjectCollectionIterator () {
			
			currentIndex = -1;	//Sets the index to -1 to make logic work in following methods
		}

		/**
		 * hasNext will check is the size of the list is non negative. If so it will check
		 * if the index is at the end of the list, if not return true.
		 */
		public boolean hasNext() {
			
			if (objectList.size() <=0) {
				return false;
			}
			if (currentIndex == objectList.size()-1) {
				
				return false;
			}
			return true;
		}

		/**
		 * getNext will increase the index by one, then return what the current index
		 * is of the list
		 */
		public Object getNext() {
			
			currentIndex++;
			return (objectList.get(currentIndex));
		}
	}

	/**
	 * getObjectList returns the list that is created in this object if needed
	 * @return
	 */
	public ArrayList getObjectList() {
		
		return objectList;
	}

	/**
	 * setObjectList will replace the list in the collection if needed (DO NOT USE)
	 * @param objectList
	 */
	public void setObjectList(ArrayList objectList) {
		
		this.objectList = objectList;
	}
}
