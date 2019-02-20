package com.guolaiwan.app.web.test;    
    
import javax.servlet.http.HttpSession;    
    
import org.apache.commons.fileupload.ProgressListener;    
    
public class UploadListener implements ProgressListener {    
        
    private HttpSession session=null;    
        
    public UploadListener (HttpSession session){    
        this.session=session;    
    }    
    /**  
     * 更新状态  
     * @param pBytesRead 读取字节总数  
     * @param pContentLength 数据总长度  
     * @param pItems 当前正在被读取的field号  
     */    
    public void update(long pBytesRead, long pContentLength, int pItems) {    
        FileUploadStatus fuploadStatus = UploadServlet.takeOutFileUploadStatusBean(this.session);    
        fuploadStatus.setUploadTotalSize(pContentLength);    
        //读取完成    
        if (pContentLength == -1) {    
            fuploadStatus.setStatus("完成对" + pItems + "个文件的读取：读取了 " + pBytesRead + "/"  + pContentLength+ " bytes.");    
            fuploadStatus.setReadTotalSize(pBytesRead);    
            fuploadStatus.setCurrentUploadFileNum(pItems);    
            fuploadStatus.setProcessEndTime(System.currentTimeMillis());    
            fuploadStatus.setProcessRunningTime(fuploadStatus.getProcessEndTime());    
        }else{//读取过程中    
               fuploadStatus.setStatus("当前正在处理第" + pItems+"个文件:已经读取了 " + pBytesRead + " / " + pContentLength+ " bytes.");    
               fuploadStatus.setReadTotalSize(pBytesRead);    
               fuploadStatus.setCurrentUploadFileNum(pItems);    
               fuploadStatus.setProcessRunningTime(System.currentTimeMillis());    
        }    
        //System.out.println("已经读取：" + pBytesRead);    
        UploadServlet.storeFileUploadStatusBean(this.session, fuploadStatus);    
    }    
    
}    