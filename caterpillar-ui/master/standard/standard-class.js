/* ========================================================================
 * class     : Tetris..js version 1.0.0
 * describe  : 标准Tetris类定义文件格式
 * dependency：jQuery.js 1.12.3， Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */
+function($){

	// 依赖校验
	// =========================

	//if(!window.Tetris) throw new Error('缺失Tetris.js依赖，建议版本2.0.0');

	// 常量定义
	// =========================

	var PLUGINNAME = 'Cache',
		PROXYNAME = $.fn[window.Tetris](window.Tetris, 'getProxyName');

	// 接口
	// =========================

	var INTERFACE = [];

	// 默认实现
	// =========================

	// 全局单例
	// =========================

	var _instance;

	// 类定义
	// =========================

	var Class = function(prop0, prop1, prop2){
		this.prop0 = prop0;
		this.prop1 = prop1;
		this.prop2 = prop2;
	}

	// 版本
	// =========================

	Class.prototype.version = function(){
		return '1.0.0';
	}

	// 类方法
	// =========================

	Class.prototype.method = function(arg0, arg1, arg2){

	}

	// 整合到Tetris需要创建全局单例
	// =========================

	//_instance = new Class();

	// 整合到Tetris插件
	// =========================

	//$.fn[window.Tetris](window.Tetris, 'extend', PLUGINNAME, _instance);

	// 全局工厂
	// =========================

	/*var _classFactory = {
		create:function(options){
			return new Class(prop0, prop1, prop2);
		}
	};*/

	// 整合到Tetris插件
	// =========================

	//$.fn[window.Tetris](window.Tetris, 'extend', PLUGINNAME, _classFactory);

	// 基础插件代理扩展
	// =========================

	//$.fn[window.Tetris](window.Tetris, 'extend', PROXYNAME+'.customerProxyName', function(){});

	// 代理方法初始化
	// =========================

	//$.fn[window.Tetris](PROXYNAME, 'init', ['customerProxyName']);

}(jQuery);