package pub.caterpillar.mvc.controller.upload;

import java.util.concurrent.ConcurrentHashMap;

import pub.caterpillar.mvc.controller.upload.bo.FileUploadStatusBO;

/**
 * 文件上传状态管理器
 * lvdeyang 2017年6月27日
 */
public class FileUploadStatusManager {

	//文件状态列表
	private ConcurrentHashMap<String, FileUploadStatusBO> statusQueue;
	
	//添加文件上传状态
	public void add(String key, FileUploadStatusBO status){
		this.statusQueue.put(key, status);
	}
	
	//获取一个文件的状态
	public FileUploadStatusBO get(String key){
		return this.statusQueue.get(key);
	}
	
}
