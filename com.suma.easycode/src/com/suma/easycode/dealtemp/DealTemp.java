package com.suma.easycode.dealtemp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;

import com.suma.easycode.analyse.ParamBO;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class DealTemp {

	public static void dealTempController(String beanName,String tempPath,String destPath,List<ParamBO> boList,IProject project){
		// copy files to project
		try {
          
			File file=new File(destPath);
			if(!file.exists()){
				file.createNewFile();
			}
			project.refreshLocal(2, null);
			
			Writer writer = new OutputStreamWriter(new FileOutputStream(destPath), "UTF-8");
			
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(tempPath));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding("UTF-8");
			Template controllTemplate = configuration.getTemplate("ControllerTemplate.java.tpl");
			Map<String, Object> paramMap = new HashMap<String, Object>();
            
			String litBeanName=beanName.substring(0,1).toLowerCase()+beanName.substring(1);
			paramMap.put("voPath", "vo."+beanName+"Vo");
			paramMap.put("daoPath","dao."+beanName+"Dao");
			paramMap.put("poPath", "po."+beanName+"Po");
			paramMap.put("controllerPath", "/"+litBeanName);
			paramMap.put("daoName", beanName+"Dao");
			paramMap.put("poName", beanName+"Po");
			paramMap.put("daoParaName", "conn_"+litBeanName);
			paramMap.put("voName", beanName+"Vo");
			paramMap.put("controllerClassName", beanName+"Controller");
			
			List<String> paramList = new ArrayList<String>();
			List<String> setParamList = new ArrayList<String>();
			for (ParamBO bo : boList) {
				paramList.add(bo.getType()+" "+bo.getName());
				String bigName=bo.getName().substring(0,1).toUpperCase()+bo.getName().substring(1);
				setParamList.add("po.set"+bigName+"("+bo.getName()+")");
			}
			paramMap.put("paramList", paramList);
			paramMap.put("setParamList", setParamList);
			
			controllTemplate.process(paramMap, writer);

			project.refreshLocal(2, null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
