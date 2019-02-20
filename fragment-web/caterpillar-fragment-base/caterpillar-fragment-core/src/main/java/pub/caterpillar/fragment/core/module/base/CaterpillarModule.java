package pub.caterpillar.fragment.core.module.base;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类型概述<br/>
 * <p>
 *     如何成为模块？<br/>
 *     ----1.想要成为模块集成该抽象类<br/>
 *     ----2.将实现类配置成Spring的bean<br/>
 *     ----3.服务器启动会按照顺序调用模块的初始化方法<br/>
 *     这个类都做了什么？<br/>
 *     ----1.提供统一的初始化抽象方法
 *     ----2.提供必要的数据结构
 *     ----3.提供配置文件的加载方法
 *     ----4.提供持久化方法
 * </p>
 * <b>作者:</b>Administrator<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月12日 下午1:23:42
 */
public abstract class CaterpillarModule implements Comparable<CaterpillarModule>{
	
	/** 模块uuid */
	private String uuid;
	
	/** 模块名称 */
	private String name;
	
	/** 模块加载顺序，值越小优先级越高 */
	private int index;
	
	/** 模块菜单列表 */
	private List<CaterpillarNode> nodes;
	
	/** 模块定义的权限 */
	private List<CaterpillarRole> roles;
	
	/** 模块的前端依赖 */
	private List<CaterpillarRequire> requires;
	
	/** 模块uuid */
	public String getUuid() {
		return uuid;
	}

	public CaterpillarModule setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	/** 模块名称 */
	public String getName() {
		return name;
	}

	public CaterpillarModule setName(String name) {
		this.name = name;
		return this;
	}

	/** 模块加载顺序，值越小优先级越高 */
	public int getIndex() {
		return index;
	}

	public CaterpillarModule setIndex(int index) {
		this.index = index;
		return this;
	}

	/** 模块菜单列表 */
	public List<CaterpillarNode> getNodes() {
		return nodes;
	}

	public CaterpillarModule setNodes(List<CaterpillarNode> nodes) {
		this.nodes = nodes;
		return this;
	}

	/** 模块定义的权限 */
	public List<CaterpillarRole> getRoles() {
		return roles;
	}

	public CaterpillarModule setRoles(List<CaterpillarRole> roles) {
		this.roles = roles;
		return this;
	}

	/** 模块的前端依赖 */
	public List<CaterpillarRequire> getRequires() {
		return requires;
	}

	public CaterpillarModule setRequires(List<CaterpillarRequire> requires) {
		this.requires = requires;
		return this;
	}
	
	/**
	 * 为模块设置配置<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午6:14:39
	 * @param config
	 */
	public void setConfig(JSONObject config){
		
		//模块uuid
		String uuid = config.getString("uuid");
		
		//模块名称
		String name = config.getString("name");
		
		//模块加载顺序
		int index = config.getIntValue("index");
		
		//模块菜单节点
		List<CaterpillarNode> nodes = JSON.parseObject(config.getString("nodes"), CaterpillarNode.class);
		
		//模块定义的权限
		List<CaterpillarRole> roles = JSON.parseObject(config.getString("roles"), CaterpillarRole.class);
		
		//模块前端依赖
		List<CaterpillarRequire> requires = JSON.parseObject(config.getString("requires"), CaterpillarRequire.class);
		
		this.setUuid(uuid)
			.setName(name)
			.setIndex(index)
			.setNodes(nodes)
			.setRoles(roles)
			.setRequires(requires);
	}
	
	/**
	 * 模块初始化方法<br/>
	 * <p>详细描述</p>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月12日 下午1:25:14
	 */
	public abstract void init();

	@Override
	public int compareTo(CaterpillarModule o) {
		return new Integer(this.getIndex()).compareTo(new Integer(o.getIndex()));
	}
	
}
