package command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import files.WorkspaceFile;
import model.Model;
import model.Workspace;
import view.MainView;

/**
 * A command that switch workspace with other fajl with grdw extension.
 * 
 * @author Igor Bakovic
 *
 */
public class SwitchWorkspaceCommand extends Command {

	/**
	 * @param model
	 *            the main model
	 */
	public SwitchWorkspaceCommand(Model model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkspaceFile());
		jfc.setAcceptAllFileFilterUsed(false);

		Workspace workspace = Workspace.getInstance();
		if (jfc.showOpenDialog(MainView.getInstance()) == JFileChooser.APPROVE_OPTION) {
			workspace.clearChildren();
			model.getTreeModel().reload();
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(jfc.getSelectedFile())));
				String line;
				while ((line = br.readLine()) != null) {
					Invoker.getInstance().executeCommand(new LoadProjectCommand(model, line.trim()));
				}
				workspace.setWorkspaceFile(jfc.getSelectedFile());
				br.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
