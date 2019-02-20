package pub.caterpillar.fragment.core.module.base;

import java.util.Set;

/**
 * 前端模块依赖<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月18日 上午9:32:02
 */
public class CaterpillarRequire {

	/** 唯一标识 */
	private String uuid;
	
	/** 依赖名称 */
	private String key;
	
	/** 依赖路径 */
	private String value;
	
	/** 非amd规范，在shim中定义依赖 */
	private Set<String> dependences;
	
	/** 非amd规范，在shim中定义导出对象 */
	private String exports;

	/** 唯一标识 */
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** 依赖名称 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/** 依赖路径 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/** 非amd规范，在shim中定义依赖 */
	public Set<String> getDependences() {
		return dependences;
	}

	public void setDependences(Set<String> dependences) {
		this.dependences = dependences;
	}

	/** 非amd规范，在shim中定义到处对象 */
	public String getExports() {
		return exports;
	}

	public void setExports(String exports) {
		this.exports = exports;
	}
	
}
