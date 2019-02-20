package pub.caterpillar.packing.business.admin.enumeration;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public enum AdminRoleType {

	//超级用户
	SUPER("超级用户"),
	
	//二维码用户
	QRCODE("二维码用户");
	
	private String name;
	
	private AdminRoleType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static AdminRoleType fromName(String name) throws Exception{
		if("超级用户".equals(name)){
			return SUPER;
		}else if("二维码用户".equals(name)){
			return QRCODE;
		}else{
			throw new BaseException(StatusCode.ERROR, new StringBufferWrapper().append("错误的管理员权限类型：").append(name).toString());
		}
	}
	
	public static AdminRoleType fromString(String s) throws Exception{
		if("SUPER".equals(s)){
			return SUPER;
		}else if ("QRCODE".equals(s)){
			return QRCODE;
		}else{
			throw new BaseException(StatusCode.ERROR, new StringBufferWrapper().append("错误的管理员权限类型：").append(s).toString());
		}
	}
	
}
