package com.mycompany.a3;

/**
 * IIterator is an interface that allows a class to have methods called getNext and 
 * hasNext. These are for working with a collection that follows the iterator design pattern.
 */
public interface IIterator {
	
	public boolean hasNext();
	public Object getNext();
}