package pub.caterpillar.commons.mail;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pub.caterpillar.commons.loader.properties.PropertiesLoader;
import pub.caterpillar.commons.mail.bo.MailTemplateBO;
import pub.caterpillar.commons.util.wrapper.StringBuilderWrapper;

import com.alibaba.fastjson.JSONObject;

/**
 * javax.mail 发送邮件
 * lvdeyang 2017年4月13日
 */

public class MailUtil {

	//预定义的邮件服务类型
	public static final String MAILTYPE_REGISTER = "register"; 					//邮箱验证
	public static final String MAILTYPE_RETRIEVE = "retrieve";					//找回密码
	public static final String MAILTYPE_RECMMEND = "recommend";					//推荐信息
	
	//常量
	private static final String DEFAULTENCODEFORMAT = "UTF-8";					//默认的编码格式
	private static final String DEFAULTMEMTYPE = "text/html;charset=UTF-8";		//默认的MEM类型
	
	//发件人配置
	private String username;
	private String password;
	private String smtpHost;
	private String smtpPort;
	private String sender;
	
	//邮件模板
	private Map<String, MailTemplateBO> templateList;
	
	//邮件会话对象
	private Session session;
	
	//邮件传输对象
	private Transport conn;
	
	//单例--只初始化一个session
	private static MailUtil instance;
	
	private MailUtil() throws Exception{
		//解析各路属性
		JSONObject config = PropertiesLoader.getInstance().load("/properties/mail/mail.properties", JSONObject.class);
		this.setUsername(config.getString("username"))
		    .setPassword(config.getString("password"))
		    .setSmtpHost(config.getString("smtpHost"))
		    .setSmtpPort(config.getString("smtpPort"))
		    .setSender(config.getString("sender"))
		    .setTemplateList(new HashMap<String, MailTemplateBO>());
		
		int counter = 0;
		//解析邮件模板
		while(true){
			StringBuilderWrapper key_prefix = new StringBuilderWrapper().append("list[")
																		.append(counter)
																		.append("].");
			StringBuilderWrapper key_type = new StringBuilderWrapper().append(key_prefix.toString())
																	  .append("type");
			if(config.containsKey(key_type.toString())){
				MailTemplateBO template = new MailTemplateBO();
				template.setType(config.getString(key_type.toString()))
				        .setTile(config.getString(new StringBuilderWrapper().append(key_prefix.toString())
								        								    .append("title")
								        								    .toString()))
				        .setContent(config.getString(new StringBuilderWrapper().append(key_prefix.toString())
								        									   .append("content")
								        									   .toString()));
				this.getTemplateList().put(template.getType(), template);
				counter++;
			}else{
				break;
			}
		}
		initSession();
	}
	
	/**********
	 * publics
	 **********/
	
	//获取单例
	public static MailUtil getInstance() throws Exception{
		if(instance == null){
			instance = new MailUtil();
		}
		return instance;
	}
	
	//发送邮件
	public MailUtil send(String type, String receiveMail, Object...params) throws Exception{
		
		//创建邮件
		MimeMessage message = createMessage(type, receiveMail, params);
		
		//发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        this.conn.sendMessage(message, message.getAllRecipients());
        
        return this;
	}
	
	public MailUtil destroy() throws MessagingException{
		
		//销毁链接
		this.conn.close();
		
		return this;
	}
	
	/**************
	 * get and set
	 **************/
	
	public String getUsername() {
		return username;
	}
	
	public MailUtil setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public MailUtil setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getSmtpHost() {
		return smtpHost;
	}
	
	public MailUtil setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
		return this;
	}
	
	public String getSmtpPort() {
		return smtpPort;
	}
	
	public MailUtil setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
		return this;
	}
	
	public String getSender() {
		return sender;
	}
	
	public MailUtil setSender(String sender) {
		this.sender = sender;
		return this;
	}

	public Map<String, MailTemplateBO> getTemplateList() {
		return templateList;
	}

	public MailUtil setTemplateList(Map<String, MailTemplateBO> templateList) {
		this.templateList = templateList;
		return this;
	}
	
	/***********
	 * privates
	 ***********/
	//初始化链接
	private void initSession() throws MessagingException{
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();                       // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");      // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", this.getSmtpHost());   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");               // 需要请求认证
        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        props.setProperty("mail.smtp.port", this.getSmtpPort());
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", this.getSmtpPort());
        
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        this.session = Session.getDefaultInstance(props);
        this.session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        
        // 4. 根据 Session 获取邮件传输对象
        this.conn = this.session.getTransport();
        
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        // 
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        this.conn.connect(this.getUsername(), this.getPassword());
	}

	//创建一封邮件
	private MimeMessage createMessage(String type, String receiveMail, Object...params) throws Exception{
		
		MailTemplateBO template = this.getTemplateList().get(type);
		
		if(template == null) throw new Exception("系统当前未定义该类型邮件模板："+type);
		
		// 1. 创建一封邮件
        MimeMessage message = new MimeMessage(this.session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(this.getUsername(), this.sender, DEFAULTENCODEFORMAT));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

        // 4. Subject: 邮件主题
        message.setSubject(template.getTile(), DEFAULTENCODEFORMAT);

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(MessageFormat.format(template.getContent(), params), DEFAULTMEMTYPE);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
	}
}
