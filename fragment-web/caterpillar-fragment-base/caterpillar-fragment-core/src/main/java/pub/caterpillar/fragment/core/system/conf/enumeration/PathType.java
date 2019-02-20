package pub.caterpillar.fragment.core.system.conf.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * 主模块定义系统路径类型<br/>
 * <b>作者:</b>lvdeyang<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2018年4月17日 下午3:51:44
 */
public enum PathType {

	WEBBASIC("web页面根目录", "web/"),
	COMMONS("公共库根路径", "commons/"),
	LIB("外部库根路径", "lib/"),
	APP("模块根路径", "app/");
	
	/** 系统路径名称 */
	private String name;
	
	/** 系统路径 */
	private String path;
	
	private PathType(String name, String path){
		this.name = name;
		this.path = path;
	}
	
	/** 系统路径名称 */
	public String getName(){
		return this.name;
	}
	
	/** 系统路径 */
	public String getPath(){
		return this.path;
	}
	
	/**
	 * 获取枚举代码集合<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2018年4月17日 下午3:54:54
	 * @return strings 代码集合
	 */
	public static List<String> getStrings(){
		PathType[] types = PathType.values();
		List<String> strings = new ArrayList<String>();
		for(PathType type:types){
			strings.add(type.toString());
		}
		return strings;
	}
	
}
