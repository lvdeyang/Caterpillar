package pub.caterpillar.mvc.upload.listener;

import org.apache.commons.fileupload.ProgressListener;

import pub.caterpillar.mvc.upload.bean.FileUploadStatus;

/**
 * 文件上传监听器
 * lvdeyang 2017年6月27日
 */
public class FileUploadListener implements ProgressListener {

	//存放文件状态
	private FileUploadStatus status;
	
	
	
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		
		
	}

}
