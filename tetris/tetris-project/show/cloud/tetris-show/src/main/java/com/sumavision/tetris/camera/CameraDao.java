package com.sumavision.tetris.camera;

import com.sumavision.tetris.orm.dao.BaseDAO;
import com.sumavision.tetris.show.LivePo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = CameraPo.class, idClass = long.class)

public interface CameraDao extends BaseDAO<CameraPo> {
    @Query(value = "select * from TETRIS_CAMERA where  show_id=?1 and type=1",nativeQuery = true)
    public List<CameraPo> findByShowID(Long showId);

    @Query(value = "select * from TETRIS_CAMERA where  user_id=?1",nativeQuery = true)
    public List<CameraPo> findByUserId(Long userId);
}
