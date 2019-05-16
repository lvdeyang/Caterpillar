/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.BASEPATH + window.COMMONSPATH + 'ui/admin-lte/confirm/tpl/ui-confirm.html',
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

	var PLUGINNAME = 'alt-confirm';

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
		status:null,
		width:'300',
		height:'auto',
		title:'操作提示',
		content:'',
		close:'关闭',
		save:'确定'
	};


	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		createAndShow:function(conf){

			var _default = $.extend(true, {}, defultOptions);
			conf = $.extend(true, _default, conf);
			
			if(conf.status === 'info'){
				conf.footColor = '#00c0ef';
			}else if(conf.status === 'danger'){
				conf.footColor = '#dd4b39';
			}else if(conf.status === 'warning'){
				conf.footColor = '#f39c12';
			}else if(conf.status === 'success'){
				conf.footColor = '#00a65a';
			}

			var $confirm = $(prepare_tpl.render(conf));

			$('body').append($confirm);

			$confirm.modal('show');

			$confirm.on('hidden.bs.modal', function(){
				$(this).remove();
			});

			return $confirm;

		}

	};

	// jQuery扩展入口
	// =========================

	$.fn[PLUGINNAME] = function(){
		var i,
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