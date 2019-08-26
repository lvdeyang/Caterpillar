package com.sumavision.tetris.commons.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class GetFileSizeUtil {
	@SuppressWarnings("resource")
	public static Long getFileSize(File file) {
		FileChannel fileChannel = null;
		Long fileSize = 0l;
		try {
			if (file.exists() && file.isFile()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileChannel = fileInputStream.getChannel();
				fileSize = fileChannel.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fileChannel) {
				try {
					fileChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileSize;
	}
}
