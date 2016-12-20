/***********************************************************************
 * Module:  MainView.java
 * Author:  Ognjen
 * Purpose: Defines the Class MainView
 ***********************************************************************/

package view;

import controler.MainController;
import model.Model;
import view.tree.TreeView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainView extends JFrame {
	
	private ToolBarView toolBarView;
	private MenuBarView menuBarView;
	private DesktopView desktopView;
	private TreeView treeView;
	private MainController mainController;
	private Model model;
	
	// Singleton pattern implementation.
	public static MainView instance = null;

	private MainView() {
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
			instance.initialize();
		}
		return instance;
	}
	
	private void setLookAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    pack();
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
	
	private void initialize() {
		
		setLookAndFeel();
		
		// Retrieves the model and subscribes to changes.
		this.model = new Model();
		
		// Sets global layout properties.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GeRuDok");//TODO maybe smthing
		
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//this.setSize(500, 500);

		this.setSize(1500, 800);
		
		//this.setUndecorated(false);		
	    this.setLocationRelativeTo(null);

		//this.setResizable(true);
		// Adds three main panels.
		this.setLayout(new BorderLayout());

		//this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		
		//TODO
		//ResourceBundle bundle = ResourceBundle.getBundle("strings.MessageResources", Locale.getDefault());
		
		/*
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.GREEN);
		*/
		
		// Add menu bar
		this.menuBarView = new MenuBarView(this.model);
		this.setJMenuBar(menuBarView);
		
		// Add tool bar
		this.toolBarView = new ToolBarView(this.model);
		this.add(this.toolBarView, BorderLayout.PAGE_START);
		
		// Add Tree view
		this.treeView = new TreeView(this.model);
		this.add(this.treeView, BorderLayout.LINE_START);
		
		// Add Desktop view
		this.desktopView = new DesktopView(this.model.getWorkspace());
		this.add(this.desktopView, BorderLayout.CENTER);
		
		//this.add(panel);
		
		this.mainController = new MainController(this.model, this);


	}
}