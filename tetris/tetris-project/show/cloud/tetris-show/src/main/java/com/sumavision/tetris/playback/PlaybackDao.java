package com.sumavision.tetris.playback;

import com.sumavision.tetris.orm.dao.BaseDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = PlaybackPo.class, idClass = long.class)

public interface PlaybackDao extends BaseDAO<PlaybackPo> {
    @Query(value = "select * from TETRIS_PLAYBACK where show_id =?", nativeQuery = true)
    PlaybackPo getPlayBackListByShowId(Long showId);
}
