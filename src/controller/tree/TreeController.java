/***********************************************************************
 * Module:  TreeController.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeController
 ***********************************************************************/

package controller.tree;

import model.Model;
import model.tree.GNode;
import view.tree.GPopupMenu;
import view.tree.TreeView;
import view.tree.WorkspaceTree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

/**
 * Controller for the TreeView.
 * 
 * @author Ognjen
 *
 */
public class TreeController {

	/**
	 * Reference to the main model.
	 */
	public Model model;
	/**
	 * Instance of the view for this controller.
	 */
	public TreeView treeView;

	/**
	 * Creates everything and sets view listener.
	 * 
	 * @param model
	 *            The main model.
	 * @param treeView
	 *            The view for this controller.
	 */
	public TreeController(Model model, TreeView treeView) {
		this.model = model;
		this.treeView = treeView;

		this.treeView.addTreeListener(new RightClickListener());
	}

	/**
	 * Opens the GPopupMenu on the selected node.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class RightClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				WorkspaceTree tree = treeView.getTree();
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());

				if (path == null)
					return;

				tree.setSelectionPath(path);

				GPopupMenu pm = new GPopupMenu(model, (GNode) tree.getLastSelectedPathComponent());
				pm.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
}