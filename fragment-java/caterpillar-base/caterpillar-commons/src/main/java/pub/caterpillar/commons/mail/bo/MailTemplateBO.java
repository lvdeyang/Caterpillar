package pub.caterpillar.commons.mail.bo;

/**
 * 邮件信息
 * lvdeyang 2017年4月13日
 */
public class MailTemplateBO {

	//邮件业务类型
	private String type;
	
	//邮件标题
	private String tile;
	
	//邮件内容模板
	private String content;

	public String getType() {
		return type;
	}

	public MailTemplateBO setType(String type) {
		this.type = type;
		return this;
	}

	public String getTile() {
		return tile;
	}

	public MailTemplateBO setTile(String tile) {
		this.tile = tile;
		return this;
	}

	public String getContent() {
		return content;
	}

	public MailTemplateBO setContent(String content) {
		this.content = content;
		return this;
	}
	
}
