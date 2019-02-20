package pub.caterpillar.shell.jcef.handler;

import org.cef.CefApp.CefAppState;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefCommandLine;
import org.cef.callback.CefSchemeHandlerFactory;
import org.cef.callback.CefSchemeRegistrar;
import org.cef.handler.CefAppHandlerAdapter;
import org.cef.handler.CefResourceHandler;
import org.cef.network.CefRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.caterpillar.shell.jcef.context.AppContext;

public class AppHandler extends CefAppHandlerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AppHandler.class);
	
	public AppHandler(String[] args) {
		super(args);
	}

	@Override
	public void onBeforeCommandLineProcessing(String processType, CefCommandLine commandLine) {
		super.onBeforeCommandLineProcessing(processType, commandLine);
		commandLine.appendSwitch("ppapi-out-of-process");
		commandLine.appendSwitchWithValue("register-pepper-plugins", "F:\\ppapi\\bin\\ppSipPlayer.dll;application/media-suma-sip");
	}



	//注册自定义协议
	@Override
	public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
		if (registrar.addCustomScheme(ClientSchemeHandler.SCHEME, true, false, false))
			logger.info("自定义协议 " + ClientSchemeHandler.SCHEME + "://");
	}

	//上限文环境初始化
	@Override
	public void onContextInitialized() {
		AppContext.getInstance()
				  .getApp()
				  .registerSchemeHandlerFactory(ClientSchemeHandler.SCHEME, 
						  						ClientSchemeHandler.DOMAIN,
						  						new SchemeHandlerFactory());
	}

	//自定义协议工厂
	private class SchemeHandlerFactory implements CefSchemeHandlerFactory {
		
		@Override
		public CefResourceHandler create(CefBrowser browser, String schemeName, CefRequest request){
			if (schemeName.equals(ClientSchemeHandler.SCHEME)){
				return new ClientSchemeHandler();
			}
				
			return null;
		}
		
	}

	//CEF状态改变
	@Override
	public void stateHasChanged(CefAppState state) {
		logger.info("CefApp: " + state);
		if (state == CefAppState.TERMINATED){
			System.exit(0);
		}
	}
	
}
