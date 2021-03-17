package pub.caterpillar.commons.file.oss;


import com.aliyun.oss.*;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 阿里oss工具类
 *
 * @author zhangwei
 * @version 1.0.0
 * @create 2019/12/5 5:41 下午
 */
public class OSSUtils {

    protected static Log logger = LogFactory.getLog(OSSUtils.class);
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint="oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId="LTAI4G478x58BAygsS2jC6qn";
    private static String accessKeySecret="lr4WmEyCJWkayMIMTVUZTy2ADfc1Sq";
    public static String bucketName="glw-old-file";
    private static OSS ossclient = null;

    
    
    /**
     * 获取阿里云OSS客户端对象
     *
     * return ossClient
     */
    static {
        //设置超时机制和重试机制
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        ossclient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
    }


    /**
     * 创建存储空间
     * <p>
     * param ossClient OSS连接
     * param bucketName 存储空间
     * return
     */
    public static String createBucketName(String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossclient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossclient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     * <p>
     * param ossClient oss对象
     * param bucketName 存储空间
     */
    public static void deleteBucket(String bucketName) {
        ossclient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     * <p>
     * param ossClient oss连接
     * param bucketName 存储空间
     * param folder 模拟文件夹名如"qj_nanjing/"
     * return 文件夹名
     */
    public static String createFolder(String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossclient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossclient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossclient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }


    /**
     * 根据key删除OSS服务器上的文件
     * <p>
     * param ossClient oss连接
     * param bucketName 存储空间
     * param folder 模拟文件夹名 如"qj_nanjing/"
     * param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(String bucketName, String folder, String key) {
        ossclient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }


    /**
     * 上传图片至OSS
     * <p>
     * param ossClient oss连接
     * param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * param bucketName 存储空间
     * param storePath 模拟文件夹名 如"qj_nanjing/"
     * return String 返回的唯一MD5数字签名
     */
    public static String uploadObjectOSS(String storePath, File file, InputStream is) {
        String resultStr = null;
        createFolder(bucketName, storePath);
        try {
            // 文件名
            String fileName = file.getName();
            // 文件大小
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossclient.putObject(bucketName, storePath + fileName, is, metadata);
            // 解析结果
            resultStr = storePath + fileName;
            logger.info("putResult.getETag():" + putResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS
     * <p>
     * param ossClient oss连接
     * param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * param bucketName 存储空间
     * param storePath 模拟文件夹名 如"qj_nanjing/"
     * return String 返回的唯一MD5数字签名
     */
    public static String uploadObjectOSS(String storePath, String fileName, File file, InputStream is) {
        String resultStr = null;
        createFolder(bucketName, storePath);
        try {
          /*  // 文件名
            String fileName = file.getName();*/
            // 文件大小
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossclient.putObject(bucketName, storePath + fileName, is, metadata);
            // 解析结果
            resultStr = storePath + fileName;
            logger.info("putResult.getETag():" + putResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }
    
    public static String uploadObjectOSS1(String storePath, String fileName, Long fileSize, InputStream is) {
        String resultStr = null;
        createFolder(bucketName, storePath);
        try {
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossclient.putObject(bucketName, storePath + fileName, is, metadata);
            // 解析结果
            resultStr = storePath + fileName;
            logger.info("putResult.getETag():" + putResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }


    /**
     * 上传图片至OSS
     * <p>
     * param ossClient oss连接
     * param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * param bucketName 存储空间
     * param storePath 模拟文件夹名 如"qj_nanjing/"
     * return String 返回的唯一MD5数字签名
     */
    public static String uploadObjectOSS1(String storePath, String fileName, InputStream is) {
        String resultStr = null;
        createFolder(bucketName, storePath);
        try {
          /*  // 文件名
            String fileName = file.getName();*/
            // 文件大小

            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            // metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossclient.putObject(bucketName, storePath + fileName, is, metadata);
            // 解析结果
            resultStr = storePath + fileName;
            logger.info("putResult.getETag():" + putResult.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * <p>
     * param fileName 文件名
     * return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".png".equalsIgnoreCase(fileExtension)) {
            return "image/png";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        // 默认返回类型
        return "";
    }

}