/***********************************************************************
 * Module:  MenuBarController.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarController
 ***********************************************************************/

package controller;

import model.Model;
import view.AboutDialog;
import view.MenuBarView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.Invoker;
import command.LoadProjectCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;

/** @pdOid 1189e529-58ea-4e27-92c3-a945264c24e8 */
public class MenuBarController {
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=MenuBarView assc=association2 mult=1..1 side=A */
   public MenuBarView menuBarView;
   public AboutDialog aboutDialog;
   
	public MenuBarController(Model model, MenuBarView menuBarView) {
		super();
		this.model = model;
		this.menuBarView = menuBarView;
		this.aboutDialog = new AboutDialog(null);
	}

	public ActionListener getSaveActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveProjectCommand(model));
			}
		};
	}
	
	public ActionListener getSaveAsActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveAsProjectCommand(model));
			}
		};
	}
	
	public ActionListener getLoadActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new LoadProjectCommand(model));
			}
		};
	}
	
	public ActionListener getAboutActionListener() {
		return new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutDialog.show();
			}
		};
	}
   
}