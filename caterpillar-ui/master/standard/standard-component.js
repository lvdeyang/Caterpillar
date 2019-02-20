/* ========================================================================
 * component : Tetris..js: version 1.0.0
 * describe  : 标准Tetris组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

+function($){

	// 依赖检查
	// =========================

	if(!$) throw new Error('缺失依赖：jQuery.js， 建议版本：1.12.3');

	// 接口
	// =========================

	var INTERFACE = [];

	// 默认实现
	// =========================


	// 事件
	// =========================

	var EVENT = [];

	// 常量
	// =========================

	var PLUGINNAME = '',
		PROXYNAME = $.fn[window.Tetris](window.Tetris, 'getProxyName'),
		TPLURL = '';

	// 全局变量
	// =========================
	var _tplUrl = '';


	// 国际化
	// =========================

	var i18n = {
		'zh_CN':{
			'info':{

			}
		}
	}

	// 默认配置
	// =========================

	var defultOptions = {

	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(options){

		}

	}

	// jQuery扩展入口
	// =========================

	/*$.fn[PLUGINNAME] = function(){
		var arguments = arguments,
			i,
			_params = [],
			method = arguments[0];

		for(i=1; i<arguments.length; i++){
			_params.push(arguments[i]);
		}

		if(publics[method]===null || typeof publics[method]==='undefined' || publics[method]===''){
			throw new Error(method+'不是一个方法');
		}
		return publics[method].apply(this, _params);
	}*/

	// Tetris扩展入口
	// =========================

	$.fn[window.Tetris](window.Tetris, 'extend', PLUGINNAME, publics);

	// 私有方法
	// =========================

	var privates = {
		//配置校验
		check:function(options){

		},

		//获取html模板
		getHtmlTemplate:function(options){

		},

		//重绘尺寸
		resize:function(e){

		},

		//缓存事件
		cacheEvent:function(event){
			var $container = $(this),
				$player = $container.find('').first(),
				i, _handler;
			for(i in EVENT){
				_handler = event[EVENT[i]];
				if(typeof _handler === 'function'){
					$player.data(EVENT[i], _handler);
				}
			}
			return $container;
		},

		//缓存接口实现
		cacheInterface:function(implament){
			var $container = $(this),
				$player = $container.find('').first(),
				i, _implament;
			for(i in INTERFACE){
				_implament = implament[INTERFACE[i]];
				if(typeof _implament === 'function'){
					$player.data(INTERFACE[i], _implament);
				}
			}
			return $container;
		},
		//获取模板路径
		getTplUrl:function(){
			if(_tplUrl) return _tplUrl;
			_tplUrl = $.fn[window.Tetris](window.Tetris, 'basePath.get') + TPLURL;
			return _tplUrl;
		},
	}

	// 事件
	// =========================

	//延迟重绘
	$(window).bind('resize.tetris.', function(evnet){

	});

}(jQuery);