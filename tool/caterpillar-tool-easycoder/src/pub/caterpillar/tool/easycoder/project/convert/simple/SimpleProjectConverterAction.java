package pub.caterpillar.tool.easycoder.project.convert.simple;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * һ��ת��web����<br/>
 * <p>��ϸ����</p>
 * <b>����:</b>lvdeyang<br/>
 * <b>�汾��</b>1.0<br/>
 * <b>���ڣ�</b>2018��9��27�� ����9:38:17
 */
public class SimpleProjectConverterAction implements IObjectActionDelegate {

	private Shell shell;
	
	@Override
	public void run(IAction action) {
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}
	
}
