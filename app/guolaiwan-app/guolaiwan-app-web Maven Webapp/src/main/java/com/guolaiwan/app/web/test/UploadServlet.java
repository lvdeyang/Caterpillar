package com.guolaiwan.app.web.test;    
    
import java.io.*;    
import java.util.List;    
    
import javax.servlet.ServletException;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;    
import javax.servlet.http.HttpSession;    
    
import org.apache.commons.fileupload.FileItem;    
import org.apache.commons.fileupload.FileUploadException;    
import org.apache.commons.fileupload.disk.DiskFileItemFactory;    
import org.apache.commons.fileupload.servlet.*;    
    
/**  
 * Servlet implementation class for Servlet: UploadServlet  
 *  
 */    
 public class UploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {    
   static final long serialVersionUID = 1L;    
       
     public static final String UPLOAD_STATUS="UPLOAD_STATUS";    
     public static final String UPLOAD_DIR="/upload";    
       
    public UploadServlet() {    
        super();    
    }      
        
    /**  
     * 从文件路径中取出文件名  
     * @param filePath  
     * @return  
     */    
    private String takeOutFileName(String filePath){    
        int pos=filePath.lastIndexOf(File.separator);    
        if (pos>0){    
            return filePath.substring(pos+1);    
        }    
        else{    
            return filePath;    
        }    
    }    
        
    /**  
     * 从request中取出FileUploadStatus Bean  
     * @param request  
     * @return  
     */    
    public static FileUploadStatus takeOutFileUploadStatusBean(HttpSession session){    
        Object obj=session.getAttribute(UPLOAD_STATUS);    
        if (obj!=null){    
            return (FileUploadStatus)obj;    
        }    
        else{    
            return null;    
        }    
    }    
        
    /**  
     * 把FileUploadStatus Bean保存到session  
     * @param request  
     * @param uploadStatusBean  
     */    
    public static void storeFileUploadStatusBean(    
            HttpSession session,    
            FileUploadStatus uploadStatusBean){    
        session.setAttribute(UPLOAD_STATUS,uploadStatusBean);    
    }    
        
    /**  
     * 删除已经上传的文件  
     * @param request  
     */    
    private void deleteUploadedFile(HttpServletRequest request){    
        FileUploadStatus fUploadStatus=takeOutFileUploadStatusBean(request.getSession());    
        for(int i=0;i<fUploadStatus.getUploadFileUrlList().size();i++){    
            File uploadedFile = new File(request.getRealPath(UPLOAD_DIR)+    
                    File.separator+fUploadStatus.getUploadFileUrlList().get(i));    
            uploadedFile.delete();    
        }    
        fUploadStatus.getUploadFileUrlList().clear();    
        fUploadStatus.setStatus("删除已上传的文件");    
        storeFileUploadStatusBean(request.getSession(),fUploadStatus);    
    }    
        
    /**  
     * 上传过程中出错处理  
     * @param request  
     * @param errMsg  
     * @throws IOException   
     * @throws ServletException   
     */    
    private void uploadExceptionHandle(    
            HttpServletRequest request,    
            String errMsg) throws ServletException, IOException{    
        //首先删除已经上传的文件    
        deleteUploadedFile(request);    
        FileUploadStatus fUploadStatus=takeOutFileUploadStatusBean(request.getSession());    
        fUploadStatus.setStatus(errMsg);    
        storeFileUploadStatusBean(request.getSession(),fUploadStatus);    
    }    
        
    /**  
     * 初始化文件上传状态Bean  
     * @param request  
     * @return  
     */    
    private FileUploadStatus initFileUploadStatusBean(HttpServletRequest request){    
        FileUploadStatus fUploadStatus=new FileUploadStatus();    
        fUploadStatus.setStatus("正在准备处理");    
        fUploadStatus.setUploadTotalSize(request.getContentLength());    
        fUploadStatus.setProcessStartTime(System.currentTimeMillis());    
        fUploadStatus.setBaseDir(request.getContextPath()+UPLOAD_DIR);    
        return fUploadStatus;    
    }    
        
    /**  
     * 处理文件上传  
     * @param request  
     * @param response  
     * @throws IOException   
     * @throws ServletException   
     */    
    private void processFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{    
        DiskFileItemFactory factory = new DiskFileItemFactory();    
        //设置内存阀值，超过后写入临时文件    
        //factory.setSizeThreshold(10240000*5);    
        //设置临时文件存储位置    
        //factory.setRepository(new File(request.getRealPath("/upload/temp")));    
        ServletFileUpload upload = new ServletFileUpload(factory);    
        //设置单个文件的最大上传size    
        //upload.setFileSizeMax(10240000*5);    
        //设置整个request的最大size    
        //upload.setSizeMax(10240000*5);    
        //注册监听类    
        upload.setProgressListener(new UploadListener(request.getSession()));    
        //保存初始化后的FileUploadStatus Bean    
        storeFileUploadStatusBean(request.getSession(),initFileUploadStatusBean(request));    
    
        try {    
            List items = upload.parseRequest(request);    
            //处理文件上传    
            for(int i=0;i<items.size();i++){   
                FileItem item=(FileItem)items.get(i);    
    
                //取消上传    
                if (takeOutFileUploadStatusBean(request.getSession()).getCancel()){    
                    deleteUploadedFile(request);    
                    break;    
                }    
                //保存文件    
                else if (!item.isFormField() && item.getName().length()>0){    
                    String fileName=takeOutFileName(item.getName());    
                    File uploadedFile = new File(request.getRealPath(UPLOAD_DIR)+File.separator+fileName);   
                    item.write(uploadedFile);    
                    //更新上传文件列表    
                    FileUploadStatus fUploadStatus=takeOutFileUploadStatusBean(request.getSession());    
                    fUploadStatus.getUploadFileUrlList().add(fileName);    
                    storeFileUploadStatusBean(request.getSession(),fUploadStatus);    
                    Thread.sleep(500);    
                }    
            }    
            
        } catch (FileUploadException e) {    
            e.printStackTrace();    
            //uploadExceptionHandle(request,"上传文件时发生错误:"+e.getMessage());    
        } catch (Exception e) {    
            // TODO Auto-generated catch block    
            e.printStackTrace();    
            //uploadExceptionHandle(request,"保存上传文件时发生错误:"+e.getMessage());    
        }    
    }    
        
    /**  
     * 回应上传状态查询  
     * @param request  
     * @param response  
     * @throws IOException  
     */    
    private void responseFileUploadStatusPoll(HttpServletRequest request,HttpServletResponse response) throws IOException{    
        FileUploadStatus fUploadStatus=(FileUploadStatus)request.getSession().getAttribute(UPLOAD_STATUS);    
        //计算上传完成的百分比    
        long percentComplete = (long)Math.floor(((double) fUploadStatus.getReadTotalSize()/(double) fUploadStatus.getUploadTotalSize())*100.0);    
        System.out.println("com:"+percentComplete);    
        response.setContentType("text/xml");    
        response.setCharacterEncoding("UTF-8");    
        response.setHeader("Cache-Control", "no-cache");    
        if ( ((long)fUploadStatus.getReadTotalSize() == (long)fUploadStatus.getUploadTotalSize()) || (fUploadStatus.getCancel() == true)){    
        response.getWriter().write(fUploadStatus.getStatus().toString()+"success");    
        }else{    
            response.getWriter().write(fUploadStatus.getStatus().toString()+"<div class=\"prog-border\"><div class=\"prog-bar\" style=\"width: "    
                                + percentComplete + "%;\"></div></div>");    
        }    
    }    
    /**  
     * 处理取消文件上传  
     * @param request  
     * @param response  
     * @throws IOException  
     */    
    private void processCancelFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{    
        FileUploadStatus fUploadStatus=(FileUploadStatus)request.getSession().getAttribute(UPLOAD_STATUS);    
        fUploadStatus.setCancel(true);    
        request.getSession().setAttribute(UPLOAD_STATUS, fUploadStatus);    
        responseFileUploadStatusPoll(request,response);    
    
    }    
        
    /**  
     * 在上传文件列表中查找与文件名相关的id  
     * @param request  
     * @param fileName 文件名  
     * @return　找到返回id,否则返回-1  
     */    
    private int findFileIdInFileUploadedList(HttpServletRequest request,String fileName){    
        FileUploadStatus fileUploadStatus=takeOutFileUploadStatusBean(request.getSession());    
        for(int i=0;i<fileUploadStatus.getUploadFileUrlList().size();i++){    
            if (fileName.equals((String)fileUploadStatus.getUploadFileUrlList().get(i))){    
                return i;    
            }    
        }    
        return -1;    
    }    
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
        doPost(request,response);    
    }       
        
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);    
            
        if (isMultipart) {    
            processFileUpload(request,response);    
        }else{    
            request.setCharacterEncoding("UTF-8");    
                
            if (request.getParameter("uploadStatus")!=null){    
                responseFileUploadStatusPoll(request,response);    
            }    
            if (request.getParameter("cancelUpload")!=null){    
                processCancelFileUpload(request,response);    
            }    
        }    
            
    }                   
}  