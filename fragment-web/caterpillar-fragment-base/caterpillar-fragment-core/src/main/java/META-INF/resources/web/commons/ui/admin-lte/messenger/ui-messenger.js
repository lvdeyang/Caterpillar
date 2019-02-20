/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.APPPATH + 'commons/ui/admin-lte/messenger/tpl/ui-alert.html',
	'text!' + window.APPPATH + 'commons/ui/admin-lte/messenger/tpl/ui-callout.html',
	'juicer',
	'jquery'

], function(html_alert, html_callout, juicer, $){

	var prepare_alert = juicer(html_alert);

	var prepare_callout = juicer(html_callout);

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

	var PLUGINNAME = 'alt-messenger',
		ALERT = 'alert',
		CALLOUT = 'callout';

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
	};

	// 默认配置
	// =========================

	var defultOptions = {
		type:'alert',
		timeout:5,
		icon:{
			info:'fa-info',
			success:'fa-check',
			warning:'fa-warning',
			error:'fa-ban'
		}
	};

	// static
	// =========================

	var $html = $('<div style="width:500px; position:fixed; right:10px; top:60px; z-index:10000;"><div class="messanger-wrapper" style="width:100%;height:auto;"></div></div>');
	$('body').append($html);

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//设置参数
		init:function(conf){
			if(conf) defultOptions = $.extend(true, defultOptions, conf);
		},

		//创建
		create:function(type, title, content, icon){
			var _timeout = defultOptions.timeout * 1000;

			var $messanger = null;

			if(defultOptions.type === ALERT){
				$messanger = $(prepare_alert.render({
					title:title,
					icon:icon,
					content:content,
					type:type
				}));
			}else if(defultOptions.type === CALLOUT){
				$messanger = $(prepare_callout.render({
					title:title,
					content:content,
					type:type
				}));
			}

			$html.find('.messanger-wrapper').first().append($messanger);

			var _timeout_id = setTimeout(function(){
				$messanger.remove();
			}, _timeout);

			$messanger.data('timeout', _timeout_id);
		},

		info:function(content){
			publics.create('info', '信息提示', content, defultOptions.icon.info);
		},

		success:function(content){
			publics.create('success', '操作成功', content, defultOptions.icon.success);
		},

		warning:function(content){
			publics.create('warning', '操作警告', content, defultOptions.icon.warning);
		},

		error:function(content){
			publics.create('danger', '发成错误', content, defultOptions.icon.error);
		}

	};

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
	 };

	// Tetris扩展入口
	// =========================

	//$.fn[window.Tetris](window.Tetris, 'extend', PLUGINNAME, publics);

	// 私有方法
	// =========================

	var privates = {

	};

	// 事件
	// =========================

	return $;

});