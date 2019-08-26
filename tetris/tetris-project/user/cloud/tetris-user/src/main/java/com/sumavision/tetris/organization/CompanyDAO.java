package com.sumavision.tetris.organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import com.sumavision.tetris.orm.dao.BaseDAO;

@RepositoryDefinition(domainClass = CompanyPO.class, idClass = Long.class)
public interface CompanyDAO extends BaseDAO<CompanyPO>{

	/**
	 * 分页查询公司<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 上午11:33:13
	 * @param Pageable page 分页信息
	 * @return Page<CompanyPO> 公司列表
	 */
	@Query(value = "from com.sumavision.tetris.organization.CompanyPO company order by company.updateTime desc")
	public Page<CompanyPO> findAllOrderByUpdateTimeDesc(Pageable page);
	
	/**
	 * 查询用户所在的公司<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年1月24日 下午2:45:45
	 * @param Long userId 用户id
	 * @return CompanyPO 公司数据
	 */
	@Query(value = "SELECT company.* FROM tetris_company company LEFT JOIN tetris_company_user_permission permission ON company.id=permission.company_id WHERE permission.user_id=?1", nativeQuery = true)
	public CompanyPO findByUserId(Long userId);
	
}
