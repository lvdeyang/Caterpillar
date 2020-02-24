package com.sumavision.tetris.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName CameraQuery
 * @Description TODO
 * @Author yud
 * @Date 2019/12/24 13:11
 **/
@Component
public class CameraQuery {
    @Autowired
    private CameraDao cameraDao;

    public List<CameraPo> findAllCamera() {
        return cameraDao.findAll();
    }

    public CameraVo findById(Long id) throws Exception {
        CameraPo cameraPo = cameraDao.findOne(id);
        return new CameraVo().set(cameraPo);
    }

    public List<CameraPo> isOpen(Long id) {
        CameraPo cameraPo = cameraDao.findOne(id);
        Long userId = cameraPo.getUserId();
        List<CameraPo> list = cameraDao.findByUserId(userId);
        for (CameraPo po : list) {
            po.setIsInUse(0);
            cameraDao.save(po);
        }
        cameraPo.setIsInUse(1);
        cameraDao.save(cameraPo);
        return list;
    }
}
