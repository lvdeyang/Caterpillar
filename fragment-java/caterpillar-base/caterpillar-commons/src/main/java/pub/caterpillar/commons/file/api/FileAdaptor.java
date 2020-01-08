package pub.caterpillar.commons.file.api;


import java.io.InputStream;

/**
 * 文件接口
 *
 * @author zhangwei
 * @version 1.0.0
 * @create 2019/12/5 3:49 下午
 */
public interface FileAdaptor {
    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     * @return 桶名称
     */
    String createBucket(String bucketName);

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     */
    void deleteBucket(String bucketName);

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param storePath  路径
     * @param fileName   文件名称
     * @param is         流
     * @return 文件
     */
    String uploadObject(String bucketName, String storePath, String fileName, InputStream is);


    /***
     * 删除某个文件
     * @param bucketName 桶名称
     * @param storePath 文件路径  export/
     * @param fileName 文件名  aaa.txt
     */
    void deleteFile(String bucketName, String storePath, String fileName);

    String getUrl(String bucketName,String key,int expiredSeconds);
    /***
     * 下载文件
     * @param storePath  文件路径 如 nono_oil/  没有则传入 "" 不能传null
     * @param fileName 如 003.jpg
     * @return 返回下载的绝对路径
     */
    String getAbsolutePath(String storePath, String fileName);

    /***
     *  读取文件路径的根  以便业务端去拼接地址
     * @param needHttps 需要https的地址
     * @return 如 http://cdimages.51autogo.com/
     */
    String getFileRoot(boolean needHttps);
}
