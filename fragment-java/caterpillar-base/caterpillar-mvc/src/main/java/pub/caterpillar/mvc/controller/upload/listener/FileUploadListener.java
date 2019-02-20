package pub.caterpillar.mvc.controller.upload.listener;

import org.apache.commons.fileupload.ProgressListener;

import pub.caterpillar.mvc.controller.upload.bo.FileUploadStatusBO;

/**
 * 文件上传监听器
 * lvdeyang 2017年6月27日
 */
public class FileUploadListener implements ProgressListener {

	//存放文件状态
	private FileUploadStatusBO status;
	
	
	
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		
		
	}

}
