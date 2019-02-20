/* ========================================================================
 * component : ui-loader.js: version 1.0.0
 * describe  : 加载样式
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'iCheck'

], function($){

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

	var PLUGINNAME = 'alt-checkbox';

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
		/**
		 * conf.selector   string
		 * conf.context    $
		 * conf.doCheckall boolean
		 */
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(conf){
			var $checkbox = null;
			if(conf.context){
				$checkbox = conf.context.find(conf.selector);
			}else{
				$checkbox = $(conf.selector);
			}

			//初始化iCheck
			$checkbox.iCheck({
				checkboxClass: 'icheckbox_flat-red'
			});

			if(conf.doCheckall === true){

				//复选框全选
				$checkbox.on('ifChecked', function(){
					var $this = $(this),
						$checkbox_all = $('[name='+$this.attr('name')+']');
					if($this.is('.manual')){
						$this.removeClass('manual');
						return;
					}
					if($this.is('.checkall')){
						$checkbox_all.each(function(){
							var $target = $(this);
							if(!$target.is(':checked')){
								$target.addClass('manual');
								$target.iCheck('check');
							}
						});
					}else{
						var counter = 0;
						var $checkall = null;
						$checkbox_all.each(function(){
							var $target = $(this);
							if(!$target.is('.checkall') && $target.is(':checked')){
								counter ++;
							}else{
								$checkall = $target;
							}
						});
						if(counter == $checkbox_all.length-1){
							if(!$checkall.is(':checked')){
								$checkall.addClass('manual');
								$checkall.iCheck('check');
							}
						}
					}
				});

				$checkbox.on('ifUnchecked', function(){
					var $this = $(this),
						$checkbox_all = $('[name='+$this.attr('name')+']');
					if($this.is('.manual')){
						$this.removeClass('manual');
						return;
					}
					if($this.is('.checkall')){
						$checkbox_all.each(function(){
							var $target = $(this);
							if($target.is(':checked')){
								$target.addClass('manual');
								$target.iCheck('uncheck');
							}
						});
					}else{
						var $checkall = null;
						$checkbox_all.each(function(){
							var $target = $(this);
							if($target.is('.checkall')){
								$checkall = $target;
								return false;
							}
						});
						if($checkall.is(':checked')){
							$checkall.addClass('manual');
							$checkall.iCheck('uncheck');
						}
					}
				});

			}
		},

		check:function($checkbox){
			$checkbox.iCheck('check');
		},

		uncheck:function($checkbox){
			$checkbox.iCheck('uncheck');
		}

	}

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