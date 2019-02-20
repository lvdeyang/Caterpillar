package com.suma.easycode.popup.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.suma.easycode.analyse.AnalysePoClass;
import com.suma.easycode.analyse.ParamBO;
import com.suma.easycode.dealtemp.DealTemp;
import com.suma.easycode.util.EasyCodeFileUtil;
import com.suma.easycode.util.EasyCodeUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class GenerateAction implements IObjectActionDelegate {

	private Shell shell;

	/**
	 * Constructor for Action1.
	 */
	public GenerateAction() {
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
		
        //获取相关路径
		String path = project.getLocationURI().toString().substring(6).replace("/", File.separator)+ File.separator +
				"src"+File.separator+"main"+File.separator+"java";

		String tempPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()
				.substring(1).replace("/", File.separator) + "src"+ File.separator+"com"+ File.separator+"suma"+
						File.separator+"easycode"+ File.separator + "template";
		
		String poDestPath=path+File.separator+"po" ;
		//解析po并处理
        File poFile=new File(poDestPath);
        for (File f :  poFile.listFiles()) {
        	
        	String[] names=f.getName().split("\\.");
        	String poPath="po."+names[0];
        	String pageName=names[0].replace("PO", "");
        	try {
				List<ParamBO> boList=AnalysePoClass.analysePo(poPath);
				String controllerDestPath = path+File.separator+"controller"+File.separator+ pageName+"Controller.java";
				DealTemp.dealTempController(names[0], tempPath, controllerDestPath, boList, project);
				
			} catch (SecurityException | NoSuchMethodException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

	}

}
