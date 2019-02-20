/* ========================================================================
 * component : ui-loader.js: version 1.0.0
 * describe  : 加载样式
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.APPPATH + 'commons/ui/admin-lte/checkbox/tpl/ui-checkgroup.html',
	'juicer',
	'bootstrap',
	'iCheck'

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

	var PLUGINNAME = 'alt-checkgroup',
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
	}

	// 默认配置
	// =========================

	var defultOptions = {
		/**
		 * conf.title    string
		 * conf.selector string
		 * conf.context  $
		 * conf.column   number
		 * conf.data     [{key:'',value:''}]
		 */
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		create:function(conf){

			var $input = null;
			if(conf.context){
				$input = conf.context.find(conf.selector);
			}else{
				$input = $(conf.selector);
			}
			var $parent = $input.parent();

			conf.interface = {
				checkbox:{
					check:checkbox.check,
					uncheck:checkbox.uncheck
				}
			};

			//默认分成三列
			conf.column = conf.column || 3;

			conf.now = new Date().getTime();

			//初始化文本
			conf.textShow = publics.getTextShow(conf.data);

			var _length, _rangeList = [], _colNum, i, _begin = 0,
				_col = parseInt(conf.column);

			//设置分列
			_length = conf.data.length;
			_colNum = _length%_col===0?_length/_col:parseInt(_length/_col)+1;
			for(i=0; i<_col; i++){
				if(_rangeList.length > 0){
					_begin = _rangeList[_rangeList.length-1][1];
				}
				if(i === _col-1){
					_rangeList.push([_begin, _length]);
				}else{
					_rangeList.push([_begin, _begin+_colNum]);
				}
			}
			conf.rangeList = _rangeList;

			var $checkgroup = $(prepare_tpl.render(conf));

			$input.val(conf.textShow).attr('data-toggle', 'modal').attr('data-target', '#'+conf.now);

			$parent.append($checkgroup);

			$input.data(OPTIONSBINDNAME, conf);

			$checkgroup.find('.checkgroup-checkbox input').iCheck({
				checkboxClass: 'icheckbox_flat-red'
			});

			//全选
			$checkgroup.on('click.checkall', '.checkgroup-action-checkall', function(){
				var $checkall = $(this),
					$modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
					$input = $modal.prev(),
					_options = $input.data(OPTIONSBINDNAME),
					_check = _options.interface.checkbox.check;

				publics.proxy_check_toggle($modal.find('.checkgroup-checkbox input'), _check, 0);
			});

			//反选
			$checkgroup.on('click.reverse', '.checkgroup-action-reverse', function(){
				var $checkall = $(this),
					$modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
					$input = $modal.prev(),
					_options = $input.data(OPTIONSBINDNAME),
					_check = _options.interface.checkbox.check,
					_uncheck = _options.interface.checkbox.uncheck;

				$modal.find('.checkgroup-checkbox input').each(function(){
					var $checkbox = $(this);
					if($checkbox.is(':checked')){
						publics.proxy_check_toggle($checkbox, _uncheck, 1);
					}else{
						publics.proxy_check_toggle($checkbox, _check, 0);
					}
				});
			});

			//撤销
			$checkgroup.on('click.revoke', '.checkgroup-action-revoke', function(){
				var $checkall = $(this),
					$modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
					$input = $modal.prev(),
					_options = $input.data(OPTIONSBINDNAME),
					_uncheck = _options.interface.checkbox.uncheck;

				publics.proxy_check_toggle($modal.find('.checkgroup-checkbox input'), _uncheck, 1);
			});

			//确定按钮
			$checkgroup.on('click.set', '.checkgroup-set', function(){
				var $button = $(this),
					$modal = $button.closest('.Tetris.checkgroup.modal').first(),
					$input = $modal.prev(),
					_checked, _textShow;

				_checked = publics.getChecked.apply($modal.parent()[0], []);
				_textShow = publics.getTextShow(_checked);
				$input.val(_textShow);
			});

			//模态框关闭事件
			$checkgroup.on('hide.bs.modal.Tetris.checkgroup', function(){
				var $modal = $(this),
					$input = $modal.prev(),
					_checked, _textShow;

				_checked = publics.getChecked.apply($modal.parent()[0], []);
				_textShow = publics.getTextShow(_checked);
				$input.val(_textShow);
			});

			//模态框展示事件
			$checkgroup.on('show.bs.modal.Tetris.checkgroup', function(){
				var $modal = $(this),
					$input = $modal.prev(),
					_options = $input.data(OPTIONSBINDNAME),
					_data = _options.data,
					_check = _options.interface.checkbox.check,
					_uncheck = _options.interface.checkbox.uncheck;

				$modal.find('.checkgroup-checkbox input').each(function(){
					var $checkbox = $(this);
					if(_data[parseInt($checkbox.attr('data-index'))].checked === true){
						publics.proxy_check_toggle($checkbox, _check, 0);
					}else{
						publics.proxy_check_toggle($checkbox, _uncheck, 1);
					}
				});

			});

		},

		//获取选中项目的显示文本
		getTextShow:function(items){
			var _textShow = '没有选中项',
				i,
				_checked = [];
			for(i=0; i<items.length; i++){
				if(items[i].checked === true){
					_checked.push(items[i]);
				}
			}
			if(_checked && _checked.length>0){
				_textShow = '';
				for(i=0; i<_checked.length; i++){
					_textShow += ((_checked[i].name || _checked[i].value)+', ');
				}
				_textShow = _textShow.substring(0, _textShow.length-2);
			}
			return _textShow;
		},

		//check[|uncheck] proxy
		proxy_check_toggle:function($checkbox, target, targetType){
			if(targetType === 0){
				$checkbox.each(function(){
					if(!$(this).is(':checked')) $(this).addClass('checkgroup-checkbox-passive');
				});
			}else {
				$checkbox.each(function(){
					if($(this).is(':checked')) $(this).addClass('checkgroup-checkbox-passive');
				});
			}
			target.apply(this, [$checkbox]);
		},

		//checkbox.change
		change:function(){
			var $checkbox = $(this);
			if($checkbox.is('.checkgroup-checkbox-passive')){
				$checkbox.removeClass('checkgroup-checkbox-passive');
			}else{
				$checkbox.change();
			}
		},

		//获取选中的项，该方法会同步缓存
		getChecked:function($input){
			var $modal = null;
			if($input){
				$modal = $input.next();
			}else{
				var $container = $(this);
				$modal = $container.find('.checkgroup.modal').first();
				$input = $modal.prev();
			}

			var _options = $input.data(OPTIONSBINDNAME),
				_checked = [], i;

			$modal.find('.checkgroup-checkbox input').each(function(){
				var $checkbox = $(this),
					_index;
				_index = parseInt($checkbox.data('index'));
				if($checkbox.is(':checked')){
					_checked.push(_options.data[_index]);
					_options.data[_index].checked = true;
				}else{
					_options.data[_index].checked = false;
				}
			});

			//缓存数据
			$input.data(OPTIONSBINDNAME, _options);
			return _checked;
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