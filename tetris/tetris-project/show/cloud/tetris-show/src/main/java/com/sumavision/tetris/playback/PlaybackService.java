package com.sumavision.tetris.playback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PlaybackService
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 14:42
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PlaybackService {
    @Autowired
    private PlaybackDao playbackDao;

    /**
     * @描述 根据直播间ID查询所有回放
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public List<String> getPlayBackListByShowId(Long id) {
        PlaybackPo playbackPo = playbackDao.getPlayBackListByShowId(id);
        String path = playbackPo.getPath();
        List<String> list = getFile(path, 0);
        return list;
    }

    /**
     * @描述 根据回放的路径（包括后缀）删除该回放
     * @参数 path
     * @返回值
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public Boolean removePlayByPath(String path) {
        Boolean aBoolean = deleteFile(path);
        if (aBoolean) {
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws java.io.IOException
     */
    public static boolean deleteFile(String pathname) {
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }

    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    public static List<String> getFile(String path, int deep) {
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        List<String> fileList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字
                // System.out.println(array[i].getName());
                // 输出当前文件的完整路径
                //System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                System.out.println(array[i].getPath());
                fileList.add(array[i].getPath());
            } else if (array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                System.out.println(array[i].getName());
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(), deep + 1);
            }
        }
        return fileList;
    }

    public static void main(String[] args) {
        //getFile("D:\\迅雷下载", 0);
        deleteFile("D:\\迅雷下载\\大侦探皮卡丘.BD1280高清中英双字版.mp4");
    }
}

