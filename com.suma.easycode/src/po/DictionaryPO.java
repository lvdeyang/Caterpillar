package po;

import java.util.Set;


public class DictionaryPO{

	private static final long serialVersionUID = 1L;
	
	private String content;

	private String boId;
	
	private String code;
	
	private String parentBoId;
	
	private String ip;
	
	private String liveBoId;
	
	
	
	public String getLiveBoId() {
		return liveBoId;
	}

	public void setLiveBoId(String liveBoId) {
		this.liveBoId = liveBoId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentBoId() {
		return parentBoId;
	}

	public void setParentBoId(String parentBoId) {
		this.parentBoId = parentBoId;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	
}
