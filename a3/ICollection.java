package com.mycompany.a3;

/**
 * ICollection is a interface that allows classes to add objects to a list and 
 * get the iterator needed to feed into the list
 */
public interface ICollection {
	
	public void add(Object newObject);
	public IIterator getIterator();
}