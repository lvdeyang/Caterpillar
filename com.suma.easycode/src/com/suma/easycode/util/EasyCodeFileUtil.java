package com.suma.easycode.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.VariableDeclaration;

public class EasyCodeFileUtil {

	public static void copyDir(String sourcePath, String newPath) throws IOException {
		
		File file = new File(sourcePath);
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {
			String targetName = filePath[i];
			String fullPath = sourcePath + File.separator + filePath[i];
			File target = new File(fullPath);
			
			if (target.isDirectory()) {
				copyDir(fullPath, newPath + File.separator + filePath[i]);
			}

			if (target.isFile()) {
				String transName = targetName;
				if(targetName.endsWith("java.tpl")){
					transName = targetName.substring(0, targetName.length() - 4);
				}
				copyFile(fullPath, newPath + File.separator + transName);
			}

		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[2097152];
		int readByte = 0;
		while ((readByte = in.read(buffer)) != -1) {
			out.write(buffer, 0, readByte);
		}

		in.close();
		out.close();
	}

}
