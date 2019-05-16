/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.BASEPATH + window.COMMONSPATH + 'ui/admin-lte/input/spinner/tpl/ui-spinner.html',
	'juicer',
	'jquery',

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

	var PLUGINNAME = 'alt-spinner',
		OPTIONSBINDNAME = 'options';

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
		init:0,
		step:1,
		max:10000,
		min:-10000,
		html:false
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(conf){

			conf = conf || {};

			var _default = $.extend(true, {}, defultOptions);
			conf = $.extend(true, _default, conf);

			var $spinner = $(prepare_tpl.render(conf));
			$spinner.data(OPTIONSBINDNAME, conf);

			var $container = $(this);
			if($container[0]){
				$container.data(OPTIONSBINDNAME, conf);
				$container.append($spinner);
			}else{
				return $spinner;
			}

		},

		//获取值
		val:function(){
			var $input = $(this).find('input');
			return parseInt($input.val());
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

	//加数
	$(document).on('click.spinner.add', '.ui-spinner .btn-add', function(){
    	var $add_btn = $(this);
		var $minus_btn = $add_btn.siblings('.btn-minus');
		var $spinner = $add_btn.closest('.ui-spinner');
		var $input = $spinner.find('input');

		var num = parseInt($input.val());

		var conf = $spinner.data(OPTIONSBINDNAME);

		if((num+conf.step)>=conf.max){
			num = conf.max;
			$add_btn.attr('disabled', true);
		}else{
			num = num+conf.step;
		}
		$minus_btn.removeAttr('disabled');

		$input.val(num);
	});

	//减数
	$(document).on('click.spinner.minus', '.ui-spinner .btn-minus', function(){
		var $minus_btn = $(this);
		var $add_btn = $minus_btn.siblings('.btn-add');
		var $spinner = $minus_btn.closest('.ui-spinner');
		var $input = $spinner.find('input');

		var num = parseInt($input.val());

		var conf = $spinner.data(OPTIONSBINDNAME);

		if((num-conf.step)<=conf.min){
			num = conf.min;
			$minus_btn.attr('disabled', true);
		}else{
			num = num-conf.step;
		}
		$add_btn.removeAttr('disabled');

		$input.val(num);
	});

	//失去焦点
	$(document).on('blur', '.ui-spinner input', function(){

		var $input = $(this);
		var num = parseInt($input.val());
		var $spinner = $input.closest('.ui-spinner');
		var conf = $spinner.data(OPTIONSBINDNAME);
		var $add_btn = $spinner.find('.btn-add');
		var $minus_btn = $spinner.find('.btn-minus');

		if(num > conf.max){
			num = conf.max;
			$add_btn.attr('disabled', true);
			$minus_btn.removeAttr('disabled');
		}else if(num < conf.min){
			num = conf.min;
			$minus_btn.attr('disabled', true);
			$add_btn.removeAttr('disabled');
		}else{
			$add_btn.removeAttr('disabled');
			$minus_btn.removeAttr('disabled');
		}

		$input.val(num);

	});

	return $;

});