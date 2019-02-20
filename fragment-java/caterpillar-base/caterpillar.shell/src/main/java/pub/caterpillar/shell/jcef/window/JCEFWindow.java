package pub.caterpillar.shell.jcef.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.cef.CefApp;
import org.cef.CefApp.CefVersion;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.caterpillar.shell.jcef.context.AppContext;
import pub.caterpillar.shell.jcef.handler.AppHandler;
import pub.caterpillar.shell.jcef.handler.ClientSchemeHandler;

/**
 * JCEF主窗体
 * lvdeyang 2017年8月2日
 */
public class JCEFWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(JCEFWindow.class);
	
	//元数据
	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 

	//配置信息
	public Settings settings = null;
	
	public JCEFWindow(boolean osrEnabled, String [] args, Settings settings){
		
		//设置项目根路径
		AppContext context = AppContext.getInstance();
	    context.setBasePath(System.getProperty("user.dir"))
	    	   .setAppfolder("webroot")
	    	   .setHomepage("test.html")
	    	   .setWindow(this);
		
		this.settings = settings;
		
		//无边框样式
		//this.setUndecorated(true);
		
		//设置默认panel不可见
		Container defaultPane = this.getContentPane();
		
		//初始化jcef实例
		CefSettings cefSettings = new CefSettings();
		cefSettings.windowless_rendering_enabled = osrEnabled;
		cefSettings.background_color = cefSettings.new ColorType(255, 255, 255, 255);
	    CefApp app = CefApp.getInstance(args, cefSettings);
	    CefVersion version = app.getVersion();
	    logger.info("CEF版本:\n" + version);
	    
	    context.setApp(app);
	    
	    //加入事件
	    CefApp.addAppHandler(new AppHandler(args));
	    
	    CefClient client = app.createClient();
	    
	    //请求响应上下文环境
	    CefRequestContext requestContext = null;
	    
	    //首页
	    StringBuffer homeUrl = new StringBuffer();
	    homeUrl.append(ClientSchemeHandler.SCHEME);
	    homeUrl.append("://");
	    homeUrl.append(ClientSchemeHandler.DOMAIN);
	    homeUrl.append("/");
	    homeUrl.append(context.getHomepage());
	    CefBrowser browser = client.createBrowser(homeUrl.toString(), osrEnabled, false, requestContext);
	    defaultPane.add(browser.getUIComponent());
		
		//设置CEF上下文环境
		context.setClient(client).setBrowser(browser);
	}
	
	public void draw(){
		this.setBounds(this.settings.left, this.settings.top, this.settings.width, this.settings.height);
		this.setVisible(true);
	}
	
	//配置项
	public static class Settings{
		
		//窗体宽度
		private int width;
		
		//窗体高度
		private int height;
		
		//窗体x定位
		private int left;
		
		//窗体y定位
		private int top;
		
		private JCEFWindow.ColorType background;

		public int getWidth() {
			return width;
		}

		public Settings setWidth(int width) {
			this.width = width;
			return this;
		}

		public int getHeight() {
			return height;
		}

		public Settings setHeight(int height) {
			this.height = height;
			return this;
		}

		public int getLeft() {
			return left;
		}

		public Settings setLeft(int left) {
			this.left = left;
			return this;
		}

		public int getTop() {
			return top;
		}

		public Settings setTop(int top) {
			this.top = top;
			return this;
		}

		public JCEFWindow.ColorType getBackground() {
			return background;
		}

		public Settings setBackground(JCEFWindow.ColorType background) {
			this.background = background;
			return this;
		}
		
	}
	
	//常用配置
	public enum SettingsType{
		
		//全屏
		FULLSCREEN(new Settings().setLeft(0)
								 .setTop(0)
								 .setWidth(dimension.width/2)
								 .setHeight(dimension.height/2)
								 .setBackground(ColorType.WHITE));
		
		private Settings settings;
		
		private SettingsType(Settings settings){
			this.settings = settings;
		}
		
		public Settings getSettings(){
			return this.settings;
		}
		
	}
	
	//常用颜色
	public enum ColorType{
		
		//白色
		WHITE(new Color(255, 255, 255));
		
		private Color color;
		
		private ColorType(Color color){
			this.color = color;
		}
		
		public Color getColor(){
			return this.color;
		}
		
	}
	
}
