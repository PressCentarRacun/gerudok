package model;

import java.io.File;
import java.io.Serializable;
import model.tree.GNode;

/**
 * Represents the root node in the WorkspaceTree that contains and manipulates
 * Projects. There is never more then one Workspace.
 * 
 * @author Ognjen Djuricic
 *
 */
@SuppressWarnings("serial")
public class Workspace extends GNode implements Serializable {

	/**
	 * Used for generating names for new children.
	 */
	private static int newChildCount = 0;

	/**
	 * The file of the workspace.
	 */
	private File workspaceFile;

	/**
	 * The single Workspace object.
	 */
	private static Workspace instance = null;

	/**
	 * Gets the newChildCount.
	 * 
	 * @return The newChildCount.
	 */
	private static int getNewChildCount() {
		return newChildCount++;
	}

	/**
	 * Default constructor.
	 */
	private Workspace() {
		super("Workspace");
	}

	/**
	 * Creates and gets the single instance of the Workspace.
	 * 
	 * @return The single Workspace object.
	 */
	public static Workspace getInstance() {
		if (instance == null) {
			instance = new Workspace();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	@Override
	public GNode addNewChild() {
		Project child = new Project("Project" + getNewChildCount());
		this.addChild(child);
		return child;
	}

	/**
	 * Gets the file representing the workspace.
	 * 
	 * @return Workspace file
	 */
	public File getWorkspaceFile() {
		return workspaceFile;
	}

	/**
	 * Sets the file representing the workspace.
	 * 
	 * @param workspaceFile
	 *            New workspace file
	 */
	public void setWorkspaceFile(File workspaceFile) {
		this.workspaceFile = workspaceFile;
	}
}