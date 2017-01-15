/***********************************************************************
 * Module:  ProjectController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controller;

import model.Model;
import view.GeRuDocumentView;
import view.ProjectView;
import java.util.*;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import command.Invoker;
import command.TreeSelectCommand;

/** @pdOid bf767906-23cd-405d-a24d-dd73d7851411 */
public class ProjectController {
	private Model model;
	private ProjectView projectView;

	public ProjectController(Model model, ProjectView view) {
		this.model = model;
		this.projectView = view;
		projectView.attachFrameListener(new FrameListener());
		projectView.attachTabChangeListener(new TabChangeListener());
	}

	class TabChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			GeRuDocumentView documentView = (GeRuDocumentView) sourceTabbedPane.getSelectedComponent();
			if (documentView != null) {
				TreeSelectCommand command = new TreeSelectCommand(model, documentView.getDocument());
				Invoker.getInstance().executeCommand(command);
			}
		}

	}

	class FrameListener implements InternalFrameListener {

		@Override
		public void internalFrameActivated(InternalFrameEvent e) {
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, projectView.getProject()));
		}

		@Override
		public void internalFrameClosed(InternalFrameEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void internalFrameClosing(InternalFrameEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void internalFrameDeactivated(InternalFrameEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void internalFrameDeiconified(InternalFrameEvent e) {
			projectView.getProject().setOpened(true);
			model.getTreeModel().reload();

		}

		@Override
		public void internalFrameIconified(InternalFrameEvent e) {
			projectView.getProject().setOpened(false);
			model.getTreeModel().reload();
		}

		@Override
		public void internalFrameOpened(InternalFrameEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
