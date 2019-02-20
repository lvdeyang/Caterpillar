package pub.caterpillar.tool.easycoder.project.convert.springboot.web;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @ClassName: SpringBootWebProjectConverterAction 
 * @Description: һ��ת��caterpillar-web������spring-boot|spring-cloud������
 * @author lvdeyang
 * @date 2018��9��27�� ����5:55:13 
 */
public class SpringBootWebProjectConverterAction implements IObjectActionDelegate {

	private Shell shell;
	
	@Override
	public void run(IAction action) {
		Shell dialog = new Shell(shell, SWT.APPLICATION_MODAL|SWT.CLOSE);
		dialog.setText("web������spring-boot|spring-cloud������ת����");
		dialog.setSize(600, 700);
		
		//spring-boot|spring-cloud����
		Group springbootConfig = new Group(dialog, SWT.NONE);
		springbootConfig.setText("spring-boot|spring-cloud ����");
		springbootConfig.setBounds(10, 20, 580, 200); 
		
		Label serviceIdLable = new Label(springbootConfig, SWT.NONE);
		serviceIdLable.setText("serviceId��");
		serviceIdLable.setBounds(10, 30, 70, 20);
		Text serviceIdText = new Text(springbootConfig, SWT.BORDER);
		serviceIdText.setBounds(80, 30, 300, 20); 
		
		Label profilesLable = new Label(springbootConfig, SWT.NONE);
		profilesLable.setText("profiles��");
		profilesLable.setBounds(390, 30, 70, 20);
		Text profilesText = new Text(springbootConfig, SWT.BORDER);
		profilesText.setBounds(460, 30, 110, 20); 
		
		//�򿪶Ի���
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}
	
}
