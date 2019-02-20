package pub.caterpillar.fragment.core.module.base;

/**
 * 模块定义权限<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月18日 上午9:26:17
 */
public class CaterpillarRole {

	/** 权限uuid */
	private String uuid;
	
	/** 权限code，唯一标志 */
	private String code;
	
	/** 权限名称 */
	private String name;

	/** 权限uuid */
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** 权限code，唯一标志 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/** 权限名称 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
