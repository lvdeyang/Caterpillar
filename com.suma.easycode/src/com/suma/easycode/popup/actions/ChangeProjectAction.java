package com.suma.easycode.popup.actions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.suma.easycode.util.EasyCodeFileUtil;
import com.suma.easycode.util.EasyCodeUtil;

public class ChangeProjectAction implements IObjectActionDelegate {

	private Shell shell;

	/**
	 * Constructor for Action1.
	 */
	public ChangeProjectAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		IProject project = EasyCodeUtil.getCurrentProject();
		// copy files to project
		try {
			String path = project.getLocationURI().toString().substring(6).replace("/", File.separator);

			String sourcePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().
					substring(1).replace("/", File.separator);
			
			

			if (sourcePath.indexOf(".jar") != -1) {
				// jar包处理
			} else {
				sourcePath=sourcePath+File.separator+"src"+File.separator+"com"+File.separator+
						"suma"+File.separator+"easycode";
				EasyCodeFileUtil.copyDir(sourcePath+File.separator+"resources"+File.separator+"src", path + File.separator + "src");
				EasyCodeFileUtil.copyFile(sourcePath+File.separator+"resources"+File.separator+"pom.xml", path + File.separator + "pom.xml");
				// refresh project
				project.refreshLocal(2, null);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get ORM config file

		// generate po dao controller js html just so so

		try {
			MessageDialog.openInformation(shell, "Easycode", project.getLocationURI().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

	}

}
