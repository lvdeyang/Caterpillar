package pub.caterpillar.fragment.core.system.conf.po;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 系统路径配置<br/>
 * <p>
 *     开发者要根据前台的目录结构来规划路径的类型，包括动静分离的路劲规划<br/>
 * </p>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月17日 下午1:41:04
 */
public class PathConfigPO extends AbstractBasePO{

	private static final long serialVersionUID = 1L;

	/** 一个路径的唯一标志，用于查询 */
	private String type;
	
	/** 路径，这里没有写死任何规定，由开发者自行设计 */
	private String path;

	/** 一个路径的唯一标志，用于查询 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** 路径，这里没有写死任何规定，由开发者自行设计 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
