/***********************************************************************
 * Module:  Workspace.java
 * Author:  Ognjen
 * Purpose: Defines the Class Workspace
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GeRuDokObserver;
import gerudok_observer.GeRuDokObserverNotification;
import gerudok_observer.ObserverList;
import model.tree.GNode;

/** @pdOid 9ad5960e-3097-4f1b-971a-2008a1df723c */
public class Workspace extends GNode {
	
	private static Workspace instance = null;
	private ObserverList observerList;
	
	private Workspace() {
		
		// TODO
		// TEST
		super("workspace 1");
	}
	
	public static Workspace getInstance() {
		if(instance == null) {
			instance = new Workspace();
			instance.setName("workspace 1");
			instance.observerList = new ObserverList();
		}
		return instance;
	}

	
	public void addObserver(GeRuDokObserver observer) {
		observerList.addObserver(observer);
	}
	
	public void addNewChild() {
		Project child = new Project("Project"+this.getNewChildCount());
		this.add(child);
		observerList.notifyObservers(GeRuDokObserverNotification.PROJECT_ADD, child);
		System.out.println("OBSERVER");
	}
	   
	
}