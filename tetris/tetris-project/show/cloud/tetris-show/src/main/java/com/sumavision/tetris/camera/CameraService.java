package com.sumavision.tetris.camera;


import com.sumavision.tetris.capacity.server.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

/**
 * @ClassName CameraService
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 13:16
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CameraService {
    @Autowired
    private CameraQuery cameraQuery;

    @Autowired
    private CameraDao cameraDao;

    @Autowired
    private CapacityService capacityService;


    /**
     * @描述 查询List
     * @参数
     * @返回值 List<CameraVo>
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public List<CameraVo> findAllCamera() throws Exception {
        List<CameraPo> list = cameraQuery.findAllCamera();

        return this.generateRootattends(list);
    }

    /**
     * @描述 getByID
     * @参数 id
     * @返回值 CameraVo
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public CameraVo findById(Long id) throws Exception {
        return cameraQuery.findById(id);
    }


    public List<CameraVo> findByUserId(Long userId) throws Exception {
        List<CameraPo> list = cameraDao.findByUserId(userId);
        List<String> stringList = new ArrayList<>();
        if (list.size() != 0) {
            for (CameraPo cameraPo : list) {
                stringList.add(cameraPo.getUserId().toString() + cameraPo.getId().toString() + "");
            }
            // capacityService.createTask(stringList, list.get(0).getUserId());

            return generateRootattends(list);
        }
        if (list.size() == 0) {
            for (int i = 0; i < 6; i++) {
                CameraPo cameraPo = new CameraPo();
                cameraPo.setUserId(userId);
                cameraPo.setCameraName("机位" + (i + 1));
                cameraDao.save(cameraPo);
            }
        }
        List<CameraPo> cameraPoList = cameraDao.findByUserId(userId);

        for (CameraPo cameraPo : cameraPoList) {
            cameraPo.setRtmpUrl("https://www.guolaiwan.net/live/" + userId + cameraPo.getId());
            cameraPo.setHttpUrl("rtmp://www.guolaiwan.net/live/" + userId + cameraPo.getId());
            cameraPo.setUserId(userId);
            cameraPo.setIsInUse(0);
            cameraPo.setType(0);
            cameraPo.setUpdateTime(new Date());
            cameraDao.save(cameraPo);

        }

        return generateRootattends(cameraDao.findByUserId(userId));
    }

    /**
     * @描述 开播 开始推流~~
     * @参数 id
     * @返回值 void
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public void isOpen(Long id) throws Exception {
        List<CameraPo> cameraPoList = cameraQuery.isOpen(id);
        CameraPo cameraPo1 = cameraDao.findOne(id);
        List<String> list = new ArrayList<>();
        for (CameraPo cameraPo : cameraPoList) {
            list.add(cameraPo.getUserId().toString() + cameraPo.getId().toString() + "");
        }
        //推流 创建6个流
        //capacityService.createTask(list, cameraPoList.get(0).getUserId());

    }


    /**
     * @描述 停止该用户下的所有推流 设置type isInUse都为0
     * @参数 userid
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/13
     */
    public void stop(Long userId) throws Exception {
        List<CameraPo> cameraPoList = cameraDao.findByUserId(userId);
        List<String> list = new ArrayList<>();
        for (CameraPo cameraPo : cameraPoList) {
            cameraPo.setType(0);
            cameraPo.setIsInUse(0);
            cameraDao.save(cameraPo);
            list.add(cameraPo.getUserId().toString() + cameraPo.getId().toString() + "");
        }
        capacityService.deleteTask(list, cameraPoList.get(0).getUserId());
    }

    /**
     * @描述
     * @参数 id 机位Id
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/5
     */
    public void createRecordTask(long id) throws Exception {
//        CameraPo cameraPo = cameraDao.findOne(id);
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH-mm-ss");
//        String dateStr = sdf.format(date);
//        String pubName = "camera" + cameraPo.getUserId();
//        //用userId作为文件夹名称，保持唯一性；
//        String path = cameraPo.getUserId().toString() + "_" + dateStr;
//        //本地存储使用
//        File file = new File("D:\\live/" + cameraPo.getUserId() + path);
//        if (file.exists()) {
//            file.mkdir();
//        }
        //用户ID作为文件夹目录，日期作为二级目录
        String pubName="camera123456";
        //path  =  record  / userId / date (YYYY-MM-dd) /cameraId
        String path="record\\002\\";

        // OSSUtils.createFolder(OSSUtils.bucketName, "file/live/" + path);
        capacityService.createRecordTask(pubName, path);
    }

    /**
     * @描述 根据用户查询他所有的录制视频
     * @参数 userId
     * @返回值 List<String></>
     * @创建人 yud
     * @创建时间 2020/2/7
     */
    public List<String> getRecordByUser(long userId) {
        //这里path在更换到oss之后需要替换
        String path = "D:\\" + userId;
        List<String> fileList = getFiles(path);
        return fileList;
    }

    /**
     * @描述 根据路径删除一个或者多个录制文件
     * @参数 path
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/7
     */
    public void deleteRecordByPath(List<String> path) {
        for (String s : path) {
            File file = new File(s);
            deleteFile(file);
        }

    }


    /**
     * @描述 删除录制任务
     * @参数 id
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/6
     */
    public void deleteRecordTask(long id) throws Exception {
        CameraPo cameraPo = cameraDao.findOne(id);
        String pubName = "camera" + cameraPo.getUserId();
        capacityService.deleteRecordTask(pubName);
    }

    /**
     * @描述 save
     * @参数 cameraVo
     * @返回值 void
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public Object save(List<CameraVo> cameraVo) throws Exception {

        if (cameraVo.size() > 6) {
            return "No more than 6 seats";
        }
        for (CameraVo vo : cameraVo) {
            List<CameraPo> cameraVos = cameraDao.findByShowID(vo.getShowId());
            if (cameraVos.size() < 6) {
                vo.setType(0);
                CameraPo cameraPo = new CameraPo().set(vo);
                cameraPo.setUpdateTime(new Date());
                cameraPo.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                cameraDao.save(cameraPo);
            } else {
                return "A maximum of 6 seats are available";
            }
        }
        return "success";

    }

    /**
     * @描述 update
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public void update(CameraVo cameraVo) throws Exception {
        cameraDao.save(new CameraPo().set(cameraVo));
    }

    /**
     * @描述 根据ID删除
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2019/12/24
     */
    public void delete(Long id) {
        CameraPo cameraPo = cameraDao.findOne(id);
        cameraPo.setIsInUse(0);
        cameraDao.save(cameraPo);
    }

    /**
     * @描述 开始直播
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/21
     */
    public void changeIsInUse(Long id) throws Exception {
        CameraPo cameraPo = cameraDao.findOne(id);
        if (cameraPo.getIsInUse() == 0) {
            cameraPo.setIsInUse(1);
            cameraDao.save(cameraPo);
        } else if (cameraPo.getIsInUse() == 1) {
            cameraPo.setIsInUse(0);

            cameraDao.save(cameraPo);
        }
//        List<CameraPo> cameraPoList = cameraQuery.isOpen(id);
//
//        CameraPo cameraPo1 = cameraDao.findOne(id);
//        List<String> list = new ArrayList<>();
//        for (CameraPo po : cameraPoList) {
//            list.add(po.getUserId().toString() + po.getId().toString() + "");
//
//        }


    }

    /**
     * @描述
     * @参数 oldId  切换前的机位Id，newId 需要切换的机位Id
     * @返回值 CameraPo
     * @创建人 yud
     * @创建时间 2020/1/12
     */
    public CameraPo switchCamera(Long oldId, Long newId) throws Exception {
        if (oldId != 0) {
            CameraPo cameraPo = cameraDao.findOne(oldId);
            cameraPo.setType(0);
            cameraDao.save(cameraPo);
        }
        CameraPo cameraPo1 = cameraDao.findOne(newId);
        cameraPo1.setIsInUse(1);
        cameraPo1.setType(1);
        cameraDao.save(cameraPo1);
        //切换机位
        String[] strings = cameraPo1.getCameraName().split("机位");
        int index = Integer.parseInt(strings[1]);
        capacityService.switchTask(cameraPo1.getUserId(), index);

        return cameraPo1;
    }

    /**
     * @描述
     * @参数 开始推流
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/7
     */
    public Object createTask(long userId) throws Exception {
        List<CameraPo> list = cameraDao.findByUserId(userId);
        List<String> list1 = new ArrayList<>();
        for (CameraPo cameraPo : list) {
            list1.add("test" + cameraPo.getUserId().toString() + cameraPo.getId().toString());

        }
        capacityService.createTask(list1, list.get(0).getUserId());
        return null;
    }

    public Object findByShowId(Long showId) throws Exception {
        return generateRootattends(cameraDao.findByShowID(showId));
    }

    /**
     * @描述 测试创建流
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/31
     */
    public void testCeateTask() throws Exception {
        List list = new ArrayList();
        list.add("test9");
        list.add("test10");
        long userId = 123456;
        capacityService.createTask(list, userId);

    }

    /**
     * @描述 测试切换流
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2020/1/31
     */
    public void testSwtich(int index) throws Exception {
        long userId = 123456;
        capacityService.switchTask(userId, index);
    }

    /**
     * VO PO转换
     *
     * @param attends
     * @return
     * @throws Exception
     */
    private List<CameraVo> generateRootattends(Collection<CameraPo> attends) throws Exception {
        if (attends == null || attends.size() <= 0)
            return null;
        List<CameraVo> cameraVos = new ArrayList<CameraVo>();
        for (CameraPo attend : attends) {
            cameraVos.add(new CameraVo().set(attend));
        }

        return cameraVos;
    }

    /**
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

    /**
     * @描述
     * @参数
     * @返回值
     * @创建人 yud
     * @创建时间 2020/2/7
     */
    public static Boolean deleteFile(File file) {
        Boolean b = false;
        if (!file.exists()) return b;

        if (file.isFile() || file.list() == null) {
            b = file.delete();
            System.out.println("删除了" + file.getName());
            return b;
        } else {
            File[] files = file.listFiles();
            for (File a : files) {
                deleteFile(a);
            }
            b = file.delete();
            System.out.println("删除了" + file.getName());
            return b;
        }

    }


}
