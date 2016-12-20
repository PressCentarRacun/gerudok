package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.MainView;

public class Main {

	public static void main(String[] args) {
		MainView mainView = MainView.getInstance();
		mainView.setVisible(true);
		mainView.setLocation(100, 100);
		mainView.setSize(1500, 700);
	}

}