package pub.caterpillar.tool.easycoder.project.convert.simple;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 一键转换web工程<br/>
 * <p>详细描述</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年9月27日 上午9:38:17
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
