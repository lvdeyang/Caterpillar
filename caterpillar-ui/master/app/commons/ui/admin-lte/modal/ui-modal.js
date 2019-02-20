/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.APPPATH + 'commons/ui/admin-lte/modal/tpl/ui-modal.html',
	'juicer',
	'bootstrap',
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

	var PLUGINNAME = 'alt-modal';

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
		type:null,
		status:null,
		title:'',
		height:'400',
		close:'关闭',
		save:'确定'
	};


	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		createAndShow:function(conf){

			var _default = $.extend(true, {}, modal._default);
			conf = $.extend(true, _default, conf);

			var $modal = $(prepare_tpl.render(conf));

			$('body').append($modal);

			$modal.modal('show');

			$modal.on('hidden.bs.modal', function(){
				publics.close($(this));
			});

			return $modal;

		},

		getContent:function($modal){
			return $modal.find('.modal-body');
		},

		close:function($modal){
			$modal.modal('hide');
			$modal.remove();
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