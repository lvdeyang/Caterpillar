package com.sumavision.tetris.commons.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class CopyFileUtil {
	public static void copyFileUsingFileChannels(String source, String dest) throws Exception {
		File sourceFile = new File(source);
		File destFile = new File(dest);
		copyFileUsingFileChannels(sourceFile, destFile);
	}
	
	@SuppressWarnings("resource")
	public static void copyFileUsingFileChannels(File source, File dest) throws Exception {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
}
