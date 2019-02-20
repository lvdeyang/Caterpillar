package com.suma.easycode.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

public class EasyCodeUtil {
	public static IProject getCurrentProject(){    
		ISelectionService selectionService =     
	            Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();    
	    
	        ISelection selection = selectionService.getSelection();    
	    
	        IProject project = null;    
	        if(selection instanceof IStructuredSelection) {    
	            Object element = ((IStructuredSelection)selection).getFirstElement();    
	    
	            if (element instanceof IResource) {    
	                project= ((IResource)element).getProject();    
	            } else if (element instanceof IProject) {    
	                IJavaProject jProject =     
	                    ((JavaProject)element).getJavaProject();    
	                project = jProject.getProject();    
	            } else if (element instanceof IJavaElement) {    
	                IJavaProject jProject= ((IJavaElement)element).getJavaProject();    
	                project = jProject.getProject();    
	            }    
	        }     
	        return project;    
    }    
 
}
