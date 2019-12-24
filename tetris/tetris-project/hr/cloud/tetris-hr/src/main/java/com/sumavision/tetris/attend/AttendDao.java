package com.sumavision.tetris.attend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = AttendPo.class, idClass = Long.class)
public interface AttendDao extends BaseDAO<AttendPo>{

	/**
		 * 查询全部考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月10日  o
		 * @return
		 */
	public List<AttendPo> findAll();
	/**
		 * 查询分页考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月10日  
		 * @return
	 */
	@Query(value = "SELECT * FROM TETRIS_ATTEND LIMIT ?1,?2", nativeQuery = true)
	public List<AttendPo> findBypage(int pageStart,int pageSize);
	
	
	/**
		 * 根据员工信息查询考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月10日  
		 * @param workerId
		 * @param workerId2
		 * @return
	 */
	@Query(value = "SELECT * FROM TETRIS_ATTEND WHERE WORKER_ID=?1 AND DEVICED_ID=?2", nativeQuery = true)
	public List<AttendPo> findByWorker(int workerId, int devicceId);

	/**
		 *  根据员工信息主键查询考勤<br/>
		 * <b>作者:</b>SJJ<br/>
		 * <b>版本：</b>1.0<br/>
		 * <b>日期：</b> 2019年12月11日  
		 * @param attendRecId
		 * @return
		 */
	@Query(value = "SELECT * FROM TETRIS_ATTEND WHERE ATTENDREC_ID=?1 ", nativeQuery = true)
	public AttendPo findById(Integer attendRecId);
	
	/**
	 * 按员工查询分页考勤<br/>
	 * <b>作者:</b>SJJ<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b> 2019年12月10日  
	 * @return
    */
	@Query(value = "SELECT * FROM TETRIS_ATTEND WHERE worker_no=?3 LIMIT ?1,?2", nativeQuery = true)
	public List<AttendPo> findBypageAndWorkNo(int pageStart,int pageSize,String workNo);
	
	@Query(value = "SELECT count(*) FROM TETRIS_ATTEND WHERE worker_no=?1", nativeQuery = true)
	public long countBypageAndWorkNo(String workNo);
	

}
