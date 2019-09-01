package com.sumavision.tetris.commons.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * <p>
 * AES加密解密工具包
 * </p>
 * 
 * @author IceWee
 * @date 2012-5-18
 * @version 1.0
 */
public class AESUtil {

	  //加密方式
    public static String KEY_ALGORITHM = "AES";
    //数据填充方式
    String algorithmStr = "AES/CBC/PKCS7Padding";
    //避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
    //只在第一次调用decrypt()方法时才new 对象
    public static boolean initialized = false;

    /**
     * 
     * @param originalContent
     * @param encryptKey
     * @param ivByte
     * @return
     */
    public byte[] encrypt(byte[] originalContent, byte[] encryptKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            byte[] encrypted = cipher.doFinal(originalContent);
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     * @param content
     *            目标密文
     * @return
     * @throws Exception 
     * @throws InvalidKeyException 
     * @throws NoSuchProviderException
     */
    public byte[] decrypt(byte[] content, byte[] aesKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(aesKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错。**/
    public static void initialize() {
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
    
    public static void main(String[] args) throws Exception {

        byte[] key = new byte[16];
        new Random(0).nextBytes(key);
       
        
        byte[] iv = new byte[16];
        new Random(0).nextBytes(iv);
        
        String test="wo ai beijing tiananmen ,ru guo dang ni gan dao wu suo shi shi shi,qing duo kan yi ben shu.";
        AESUtil aes=new AESUtil();
        aes.initialize();
        byte[] result=aes.encrypt(test.getBytes(), key, iv);
        byte[] retResult=aes.decrypt(result, key, iv);
        System.out.println(new String(result));
        System.out.println(new String(retResult));
        
        //加密文件
        //读取源文件
        /*File file = new File("D:\\test.mp4");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        int buf_size = 1024;
        byte[] buffer = new byte[buf_size];
        int len = 0;
        while (-1 != (len = in.read(buffer, 0, buf_size))) {
            bos.write(buffer, 0, len);
        }
        byte[] resultfile=aes.encrypt(bos.toByteArray(), key, iv);
        bos.close();
        in.close();
        fis.close();
        
        //写文件
        BufferedOutputStream bufos = null;
        FileOutputStream fos = null;
        File fileaes = null;
        fileaes = new File("D:\\testAES.mp4");
        fos = new FileOutputStream(fileaes);
        bufos = new BufferedOutputStream(fos);
        bufos.write(resultfile);*/

        
        
        //解密文件
        
        /*File file1 = new File("D:\\testAES.mp4");
        FileInputStream fis1 = new FileInputStream(file1);
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream((int) file1.length());
        BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(file1));
        int buf_size1 = 1024;
        byte[] buffer1 = new byte[buf_size1];
        int len1 = 0;
        while (-1 != (len1 = in1.read(buffer1, 0, buf_size1))) {
            bos1.write(buffer1, 0, len1);
        }
        byte[] resultfile1=aes.decrypt(bos1.toByteArray(), key, iv);
        bos1.close();
        in1.close();
        fis1.close();*/
        
        //写文件
        /*BufferedOutputStream bufos1 = null;
        FileOutputStream fos1 = null;
        File fileaes1 = null;
        fileaes1 = new File("D:\\testReborn.mp4");
        fos1 = new FileOutputStream(fileaes1);
        bufos1 = new BufferedOutputStream(fos1);
        bufos1.write(resultfile1);*/
        
    }
    
}