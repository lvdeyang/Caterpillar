/* ========================================================================
 * component : ui-loader.js: version 1.0.0
 * describe  : 加载样式
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.APPPATH + 'commons/ui/admin-lte/loader/tpl/ui-loader.html',
	'juicer',
	'jquery'

], function(tpl, juicer, $){

	var prepare_tpl = juicer(tpl);

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

	var PLUGINNAME = 'alt-loader',
		WRAPPER_CLASS = 'overlay-wrapper';

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
		create:function(){
			publics.loading.apply(this);
		},

		loading:function(){

			var $container = $(this);

			$container.addClass(loader.wrapperClass);

			var $loading = $container.find('.loading').first();

			//如果存在就不创建了
			if($loading[0]){
				$loading.show();
				return;
			}

			$container.append(prepare_tpl.render());

		},

		end:function(destroy){

			destroy = destroy || true;

			var $container = $(this);

			$container.removeClass(loader.wrapperClass);

			var $loading = $container.find('.loading').first();

			if(destroy === true){
				$loading.remove();
			}else{
				$loading.hide();
			}

		}

	}

	// jQuery扩展入口
	// =========================

	$.fn[PLUGINNAME] = function(){
		var arguments = arguments,
		i,
		_params = [],
		method = arguments[0];

		for(i=1; i<arguments.length; i++) {
			_params.push(arguments[i]);
		}
		if(publics[method]===null || typeof publics[method]==='undefined' || publics[method]===''){
		 	 throw new Error(method+'不是一个方法');
		}
		return publics[method].apply(this, _params);
	 }

	// Tetris扩展入口
	// =========================

	//$.fn[window.Tetris](window.Tetris, 'extend', PLUGINNAME, publics);

	// 私有方法
	// =========================

	var privates = {

	}

	// 事件
	// =========================

	return $;

});