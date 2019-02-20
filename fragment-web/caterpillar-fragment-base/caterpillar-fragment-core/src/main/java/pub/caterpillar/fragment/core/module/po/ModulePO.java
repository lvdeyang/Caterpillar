package pub.caterpillar.fragment.core.module.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 功能模块<br/>
 * <p>功能模块</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午3:18:36
 */
@Entity
@Table(name = AbstractBasePO.PREFIX + "MODULE")
public class ModulePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 模块名称 */
	private String name;
	
	/** 是否可用 */
	private boolean enable;
	
	/** 模块功能菜单 */
	private Set<ModuleMenuPO> menus;

	/** 模块名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 是否可用 */
	@Column(name = "ENABLE")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/** 模块功能菜单 */
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ModuleMenuPO> getMenus() {
		return menus;
	}

	public void setMenus(Set<ModuleMenuPO> menus) {
		this.menus = menus;
	}
	
}
