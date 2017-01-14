/***********************************************************************
 * Module:  Document.java
 * Author:  Ognjen
 * Purpose: Defines the Class Document
 ***********************************************************************/

package model;

import java.io.Serializable;
import java.util.*;

import model.tree.GNode;

public class GeRuDocument extends GNode implements Serializable{

	static int newChildCount = 0;
	
	private static int getNewChildCount() {
		return newChildCount++;
	}
	
	public GeRuDocument(String name) {
		super(name);
	}
   
   public GNode addNewChild() {
		Page child = new Page("Page"+getNewChildCount());
		this.add(child);
		return child;
   }
}