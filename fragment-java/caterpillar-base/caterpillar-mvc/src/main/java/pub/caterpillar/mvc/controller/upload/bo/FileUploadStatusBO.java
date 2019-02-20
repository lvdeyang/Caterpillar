package pub.caterpillar.mvc.controller.upload.bo;

/**
 * 文件上传状态
 * lvdeyang 2017年6月27日
 */
public class FileUploadStatusBO {

	//上传文件名
	private String filename;
	
	//上传总量    
    private long totalSize = 0; 
    
    //读取上传总量    
    private long readSize = 0;    
    
    //状态    
    private String status="";
	
    //处理起始时间    
    private long startTime = 0l;    
    
    //处理终止时间    
    private long endTime = 0l;  
    
    //处理执行时间    
    private long runningTime = 0l;
    
    //取消上传    
    private boolean cancel = false;
    
}
