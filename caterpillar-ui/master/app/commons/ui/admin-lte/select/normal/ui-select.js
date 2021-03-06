/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.APPPATH + 'commons/ui/admin-lte/select/normal/tpl/ui-select.html',
	'juicer',
	'jquery',
	'string'

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

	var PLUGINNAME = 'alt-select';

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
		html:false,
		style:'',
		options:[]
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

			for(var i in conf.options){
				if(conf.options[i].selected){
					conf.active = conf.options[i];
					break;
				}
			}

			var _select = prepare_tpl.render(conf);

			if(conf.html === true){
				return _select;
			}else{
				var $container = $(this);
				$container.append($(_select));
			}

		},

		//获取当前的值
		val:function(v){
			var $container = $(this),
				$input = $container.find('input');
			if(!v){
				return $input.data('value');
			}else{
				var value = v.value;
				var text = v.text;
				var o_v = $input.data('value');
				$input.data('value', value);
				if(!value){
					$input.val('');
				}else{
					$input.val(text);
				}
				if(o_v != value){
					$container.trigger('ui-select.change');
				}
			}
		},

		//获取当前的文本
		text:function(){
			var $container = $(this),
				$input = $container.find('input');
			return $input.val();
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

	$(document).on('click.ui.select', '.ui-select>.dropdown-menu a', function(){
		var $link = $(this),
			$select = $link.closest('.ui-select'),
			$item = $link.parent(),
			$menu = $item.parent();

		$menu.find('li').removeClass('active');
		$item.addClass('active');
		publics.val.apply($select[0], [{
			text:($link.text()+'').trim(),
			value:($item.data('value')+'').trim()
		}]);

	});

	return $;

});