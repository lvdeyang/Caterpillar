package pub.caterpillar.commons.file.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * OSS
 *
 * @author zhangwei
 * @version 1.0.0
 * @create 2019/12/5 3:52 下午
 */
public class OssFileAdapter {
    /**
     * 地域节点
     */
    private String endpoint;
    /**
     * 桶名称
     */
    private String bucketName;
    /**
     * 过期秒数 （默认30分钟）
     */
    private int expiredSeconds = 1800;

    private OSS ossClient;

    public OssFileAdapter() {
    }

    public OssFileAdapter(String endpoint, String accessKeyId, String accessKeySecret) {
        this.endpoint = endpoint;
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
    }

    public OssFileAdapter(String bucketName, String endpoint, String accessKeyId, String accessKeySecret, int expiredSeconds) {
        this.bucketName = bucketName;
        this.endpoint = endpoint;
        this.expiredSeconds = expiredSeconds;
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
    }

    /**
     * 创建存储空间
     * <p>
     * param ossClient OSS连接
     * param bucketName 存储空间
     * return
     */
    public String createBucket(String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
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
    public void deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
    }


    /**
     * 创建模拟文件夹
     * <p>
     * param ossClient oss连接
     * param bucketName 存储空间
     * param folder 模拟文件夹名如"qj_nanjing/"
     * return 文件夹名
     */
    public String createFolder(String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    public void deleteFolder(String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，存在则删除
        if (ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.deleteObject(bucketName, keySuffixWithSlash);
        }
    }


    public String uploadObject(String storePath, String fileName, InputStream is) {
        return uploadObject(bucketName, storePath, fileName, is);
    }

    public String uploadObject(String bucketName, String storePath, String fileName, InputStream is) {
        String key = getAbsolutePath(storePath, fileName);
        ossClient.putObject(bucketName, key, is);
        return key;
    }

   

    public void deleteFile(String key) {
        ossClient.deleteObject(bucketName, key);
       
    }

    public void deleteFile(String storePath, String fileName) {
        deleteFile(bucketName, storePath, fileName);
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param bucketName 存储空间
     * @param storePath  模拟文件夹名 如"qj_nanjing/"
     * @param fileName   Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public void deleteFile(String bucketName, String storePath, String fileName) {
        String key = getAbsolutePath(storePath, fileName);
        ossClient.deleteObject(bucketName, key);
        
    }


    public String getUrl(String key) {
        return getUrl(bucketName, key, expiredSeconds);
    }

    public String getUrl(String bucketName, String key) {
        return getUrl(bucketName, key, expiredSeconds);
    }

    /**
     * 获得url链接
     *
     * @param bucketName     桶名称
     * @param key            文件地址
     * @param expiredSeconds 过期秒数
     * @return
     */
    public String getUrl(String bucketName, String key, int expiredSeconds) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + (expiredSeconds * 1000));
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


    public String getAbsolutePath(String sourcePath, String fileName) {
        sourcePath = getValidPath(sourcePath);
        return sourcePath + fileName;
    }

    public String getFileRoot(boolean needHttps) {
        return endpoint;
    }

    private String getValidPath(String sourcePath) {
        if (StringUtils.isBlank(sourcePath)) {
            sourcePath = "";
        } else if (!sourcePath.endsWith("/")) {
            sourcePath += "/";
        }
        return sourcePath;
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
