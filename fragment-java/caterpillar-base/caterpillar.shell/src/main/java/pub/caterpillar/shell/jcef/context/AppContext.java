package pub.caterpillar.shell.jcef.context;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;

import pub.caterpillar.shell.jcef.window.JCEFWindow;

/**
 * App 上下文环境
 * lvdeyang 2017年8月2日
 */
public class AppContext {

	//项目根路径
	private String basePath;
	
	//页面根目录
	private String appfolder;
		
	//首页页面
	private String homepage;
	
	//壳窗体
	private JCEFWindow window;
	
	//JCEF实例
	private CefApp app;
	
	//JCEF客户端
	private CefClient client;
	
	//浏览器对象
	private CefBrowser browser;
	
	public String getBasePath() {
		return basePath;
	}

	public AppContext setBasePath(String basePath) {
		this.basePath = basePath;
		return this;
	}

	public String getAppfolder() {
		return appfolder;
	}

	public AppContext setAppfolder(String appfolder) {
		this.appfolder = appfolder;
		return this;
	}

	public String getHomepage() {
		return homepage;
	}

	public AppContext setHomepage(String homepage) {
		this.homepage = homepage;
		return this;
	}

	public JCEFWindow getWindow() {
		return window;
	}

	public AppContext setWindow(JCEFWindow window) {
		this.window = window;
		return this;
	}
	
	public CefApp getApp() {
		return app;
	}

	public AppContext setApp(CefApp app) {
		this.app = app;
		return this;
	}
	
	public CefClient getClient() {
		return client;
	}

	public AppContext setClient(CefClient client) {
		this.client = client;
		return this;
	}

	public CefBrowser getBrowser() {
		return browser;
	}

	public AppContext setBrowser(CefBrowser browser) {
		this.browser = browser;
		return this;
	}

	//单例模式
	private static AppContext instance;
	
	public static AppContext getInstance(){
		if(instance == null){
			instance = new AppContext();
		}
		return instance;
	}
	
}
