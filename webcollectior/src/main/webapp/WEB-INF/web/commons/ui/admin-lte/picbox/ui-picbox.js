/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.BASEPATH + window.COMMONSPATH + 'ui/admin-lte/picbox/tpl/ui-picbox.html',
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

	var PLUGINNAME = 'alt-picbox',
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
		waiting:true,
		img:false,
		name:''
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(conf){

			var _default = $.extend(true, {}, defultOptions);
			conf = $.extend(true, _default, conf);

			var $picbox = $(prepare_tpl.render(conf));

			$('body').append($picbox);

			if(conf.waiting === true){
				var $waiting = $picbox.find('.waiting');
				loader.loading.apply($waiting, []);
			}

			//绑定事件
			$picbox.on('click', 'span', function(){
				publics.destroy();
			});

		},

		//设置
		set:function(conf){

			var _name = conf.name;
			var _img = conf.img;

			var $picbox = $('body').find('.picbox');
			var $waiting = $picbox.find('.waiting');
			var $name = $picbox.find('.picbox-name');

			if($waiting.is('.waiting')){
				loader.end.apply($picbox, []);
				$waiting.removeClass('waiting');
				$waiting.append('<img src="'+_img+'" />');
			}else{
				var $img = $waiting.find('img');
				if($img[0]){
					$img.attr('src', _img);
				}else {
					$waiting.append('<img src="'+_img+'" />');
				}
			}
			$name.text(_name);

		},

		//销毁
		destroy:function(){
			$('body').find('.picbox').remove();
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