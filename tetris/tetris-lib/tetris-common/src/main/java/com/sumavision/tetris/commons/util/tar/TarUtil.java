package com.sumavision.tetris.commons.util.tar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;  
  
/** 
 * TAR工具 
 *  
 * @author 史明</a> 
 * @since 1.0 
 */  
public abstract class TarUtil {  
  
    private static final String BASE_DIR = "";  
  
    // 符号"/"用来作为目录标识判断符  
    private static final String PATH = "/";  
    private static final int BUFFER = 1024;  
  
    private static final String EXT = ".tar";  
  
    /** 
     * 归档 
     *  
     * @param srcPath 
     * @param destPath 
     * @throws Exception 
     */  
    public static void archive(String srcPath, String destPath)  
            throws Exception {  
  
        File srcFile = new File(srcPath);  
  
        archive(srcFile, destPath);  
  
    }  
  
    /** 
     * 归档 
     *  
     * @param srcFile 
     *            源路径 
     * @param destPath 
     *            目标路径 
     * @throws Exception 
     */  
    public static void archive(File srcFile, File destFile) throws Exception {  
  
        TarArchiveOutputStream taos = new TarArchiveOutputStream(  
                new FileOutputStream(destFile));  
  
        archive(srcFile, taos, BASE_DIR);  
  
        taos.flush();  
        taos.close();
        
    }
    
    public static void archive(List<File> srcPaths, String dstPath) throws Exception{
    	
    	TarArchiveOutputStream taos = new TarArchiveOutputStream(  
                new FileOutputStream(new File(dstPath)));
    	
        if (srcPaths.size() < 1) {  
            TarArchiveEntry entry = new TarArchiveEntry(dstPath);  
  
            taos.putArchiveEntry(entry);  
            taos.closeArchiveEntry();  
        }  
  
        for (File file : srcPaths) {   
            archive(file, taos, ""); 
  
        }
        
        taos.flush();  
        taos.close();  
    }
  
    /** 
     * 归档 
     *  
     * @param srcFile 
     * @throws Exception 
     */  
    public static void archive(File srcFile) throws Exception {  
        String name = srcFile.getName();  
        String basePath = srcFile.getParent();  
        String destPath = basePath +File.separator +  name + EXT;  
        archive(srcFile, destPath);  
    }  
  
    /** 
     * 归档文件 
     *  
     * @param srcFile 
     * @param destPath 
     * @throws Exception 
     */  
    public static void archive(File srcFile, String destPath) throws Exception {  
        archive(srcFile, new File(destPath));  
    }  
  
    /** 
     * 归档 
     *  
     * @param srcPath 
     * @throws Exception 
     */  
    public static void archive(String srcPath) throws Exception {  
        File srcFile = new File(srcPath);  
  
        archive(srcFile);  
    }  
  
    /** 
     * 归档 
     *  
     * @param srcFile 
     *            源路径 
     * @param taos 
     *            TarArchiveOutputStream 
     * @param basePath 
     *            归档包内相对路径 
     * @throws Exception 
     */  
    private static void archive(File srcFile, TarArchiveOutputStream taos,  
            String basePath) throws Exception {  
        if (srcFile.isDirectory()) {  
            archiveDir(srcFile, taos, basePath);  
        } else {  
            archiveFile(srcFile, taos, basePath);  
        }  
    }  
  
    /** 
     * 目录归档 
     *  
     * @param dir 
     * @param taos 
     *            TarArchiveOutputStream 
     * @param basePath 
     * @throws Exception 
     */ 
    private static void archiveDir(File dir, TarArchiveOutputStream taos,  
            String basePath) throws Exception {  
  
        File[] files = dir.listFiles();  
  
        if (files.length < 1) {  
            TarArchiveEntry entry = new TarArchiveEntry(basePath  
                    + dir.getName() + PATH);  
  
            taos.putArchiveEntry(entry);  
            taos.closeArchiveEntry();  
        }  
  
        // 递归归档  
//        if (first) {
//        	for (File file : files) { 
//        		//不带文件目录
//        		archive(file, taos, basePath + PATH); 
//        		first = false;
//            } 
//		}else {
			for (File file : files) {  
    			//带文件目录
    			archive(file, taos, basePath + dir.getName() + PATH);
            }
//		}
    }  
  
    /** 
     * 数据归档 
     *  
     * @param data 
     *            待归档数据 
     * @param path 
     *            归档数据的当前路径 
     * @param name 
     *            归档文件名 
     * @param taos 
     *            TarArchiveOutputStream 
     * @throws Exception 
     */  
    private static void archiveFile(File file, TarArchiveOutputStream taos,  
            String dir) throws Exception {  
  
        /** 
         * 归档内文件名定义 
         *  
         * <pre> 
         * 如果有多级目录，那么这里就需要给出包含目录的文件名 
         * 如果用WinRAR打开归档包，中文名将显示为乱码 
         * </pre> 
         */  
        TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());
  
        entry.setSize(file.length());  
  
        taos.putArchiveEntry(entry);  
  
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(  
                file));  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = bis.read(data, 0, BUFFER)) != -1) {  
            taos.write(data, 0, count);  
        }  
  
        bis.close();  
  
        taos.closeArchiveEntry();  
    }  
  
    /** 
     * 解归档 
     *  
     * @param srcFile 
     * @throws Exception 
     */  
    public static void dearchive(File srcFile) throws Exception {  
        String basePath = srcFile.getParent();  
        String fileName = srcFile.getName();
        dearchive(srcFile, basePath + File.separator + fileName.substring(0, fileName.lastIndexOf(".")));  
    }  
  
    /** 
     * 解归档 
     *  
     * @param srcFile 
     * @param destFile 
     * @throws Exception 
     */  
    public static void dearchive(File srcFile, File destFile) throws Exception {  
  
        TarArchiveInputStream tais = new TarArchiveInputStream(  
                new FileInputStream(srcFile));  
        dearchive(destFile, tais);  
  
        tais.close();  
  
    }  
  
    /** 
     * 解归档 
     *  
     * @param srcFile 
     * @param destPath 
     * @throws Exception 
     */  
    public static void dearchive(File srcFile, String destPath)  
            throws Exception {  
        dearchive(srcFile, new File(destPath));  
  
    }  
  
    /** 
     * 文件 解归档 
     *  
     * @param destFile 
     *            目标文件 
     * @param tais 
     *            ZipInputStream 
     * @throws Exception 
     */  
    private static void dearchive(File destFile, TarArchiveInputStream tais)  
            throws Exception {  
  
        TarArchiveEntry entry = null;  
        while ((entry = tais.getNextTarEntry()) != null) {  
  
            // 文件  
            String dir = destFile.getPath() + File.separator + entry.getName();  
  
            File dirFile = new File(dir);  
  
            // 文件检查  
            fileProber(dirFile);  
  
            if (entry.isDirectory()) {  
                dirFile.mkdirs();  
            } else {  
                dearchiveFile(dirFile, tais);  
            }  
  
        }  
    }  
  
    /** 
     * 文件 解归档 
     *  
     * @param srcPath 
     *            源文件路径 
     *  
     * @throws Exception 
     */  
    public static void dearchive(String srcPath) throws Exception {  
        File srcFile = new File(srcPath);  
  
        dearchive(srcFile);  
    }  
  
    /** 
     * 文件 解归档 
     *  
     * @param srcPath 
     *            源文件路径 
     * @param destPath 
     *            目标文件路径 
     * @throws Exception 
     */  
    public static void dearchive(String srcPath, String destPath)  
            throws Exception {  
  
        File srcFile = new File(srcPath);  
        dearchive(srcFile, destPath);  
    }  
  
    /** 
     * 文件解归档 
     *  
     * @param destFile 
     *            目标文件 
     * @param tais 
     *            TarArchiveInputStream 
     * @throws Exception 
     */  
    private static void dearchiveFile(File destFile, TarArchiveInputStream tais)  
            throws Exception {  
  
        BufferedOutputStream bos = new BufferedOutputStream(  
                new FileOutputStream(destFile));  
  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = tais.read(data, 0, BUFFER)) != -1) {  
            bos.write(data, 0, count);  
        }  
  
        bos.close();  
    }  
  
    /** 
     * 文件探针 
     *  
     * <pre> 
     * 当父目录不存在时，创建目录！ 
     * </pre> 
     *  
     * @param dirFile 
     */  
    private static void fileProber(File dirFile) {  
  
        File parentFile = dirFile.getParentFile();  
        if (!parentFile.exists()) {  
  
            // 递归寻找上级目录  
            fileProber(parentFile);  
  
            parentFile.mkdir();  
        }  
  
    } 

    /** 
     * 读取文件为一个内存字符串,保持文件原有的换行格式 
     * 
     * @param file        文件对象 
     * @param charset 文件字符集编码 
     * @return 文件内容的字符串 
     */ 
    public static String file2String(File file, String charset) { 
        StringBuffer sb = new StringBuffer();
        LineNumberReader reader = null;
        try { 
            reader = new LineNumberReader(new BufferedReader(new InputStreamReader(new FileInputStream(file), charset))); 
            String line; 
            while ((line = reader.readLine()) != null) { 
                sb.append(line).append(System.getProperty("line.separator")); 
            } 
        } catch (UnsupportedEncodingException e) { 
        	e.printStackTrace();
        } catch (FileNotFoundException e) { 
        	e.printStackTrace();
        } catch (IOException e) { 
        	e.printStackTrace();
        } finally{
        	if(reader != null){
        		try {
					reader.close();
				} catch (IOException e) {					
				}
        	}
        }
        return sb.toString(); 
    } 
    
    /** 
     * 将字符串存储为一个文件，当文件不存在时候，自动创建该文件，当文件已存在时候，重写文件的内容，特定情况下，还与操作系统的权限有关。 
     * 
     * @param text字符串 
     * @param distFile 存储的目标文件 
     * @return 当存储正确无误时返回true，否则返回false 
     */ 
    public static boolean string2File(String text, File distFile) { 
        if (!distFile.getParentFile().exists()) 
        	distFile.getParentFile().mkdirs(); 
        boolean flag = true; 
        try { 
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(distFile), Charset.forName("UTF-8")));

			bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) { 
           flag = false; 
        } 
        return flag; 
    }
  
}  

