package pub.caterpillar.fragment.core.module.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 模块功能菜单<br/>
 * <p>模块功能菜单</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:27:49
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "MODULE_MENU")
public class ModuleMenuPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 菜单名称 */
	private String name;
	
	/** 菜单链接除去basePath的部分 */
	private String uri;
	
	/** 上级菜单id */
	private String parentId;
	
	/** 所有上级菜单id /id0/id1/id2/id3 */
	private String parentIds;
	
	/** 是否可用 */
	private boolean enable;
	
	/** 隶属模块 */
	private ModulePO module;

	/** 菜单名称 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 菜单链接除去basePath的部分 */
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	/** 上级菜单id */
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 所有上级菜单id /id0/id1/id2/id3 */
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/** 是否可用 */
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/** 隶属模块 */
	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	public ModulePO getModule() {
		return module;
	}

	public void setModule(ModulePO module) {
		this.module = module;
	}
	
}
