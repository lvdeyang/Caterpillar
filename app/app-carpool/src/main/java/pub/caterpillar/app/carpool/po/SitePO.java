package pub.caterpillar.app.carpool.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.app.carpool.po.metadata.Metadata;
import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 站点数据<br/>
 * <p>站点数据</p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月24日 下午6:59:45
 */
@Entity
@Table(name = Metadata.PREFIX + "SITE")
public class SitePO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;
	
	/** 站点名称 */
	private String name;
	
	/** 站点名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
