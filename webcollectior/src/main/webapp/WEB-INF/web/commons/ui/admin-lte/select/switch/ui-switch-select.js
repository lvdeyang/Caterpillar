/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.BASEPATH + window.COMMONSPATH + 'ui/admin-lte/select/switch/tpl/ui-switch-select.html',
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

	var PLUGINNAME = 'alt-switch-select',
		NODE = '<li><span style="padding-left:${left}px;">' +
					'<i class="fa {@if checked===true}fa-check-square-o{@else}fa-square-o{@/if}"></i>' +
				    '<span class="data" data-hidden="${key}">${value}</span>' +
			        '{@if isLeaf!==true}' +
			            '<i class="fa {@if isClosed===true}fa-angle-left{@else}fa-angle-down{@/if} pull-right"></i>' +
			        '{@/if}' +
			   '</span></li>',
		LIST = '<li data-hidden="${key}">${text} <i class="fa fa-times pull-right remove"></i></li>';

	var prepare_node = juicer(NODE),
		prepare_list = juicer(LIST);

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

			var $select = $(prepare_tpl.render(conf));

			var $nodes = publics.packNode(conf.data);

			$select.find('.frame-tree').append($nodes);

			var $container = $(this);
			$container.append($select);

		},

		//获取当前的值
		val:function(v){
			var $container = $(this),
				$list = $container.find('.frame-list'),
				$nodes = $container.find('.frame-tree .data');
			if(!v){

				var data = [];

				$list.find('ul>li').each(function(){
					var $li = $(this);

					data.push({
						key:$li.data('hidden'),
						value:$li.text()
					});

				});

				return data;
			}else{

				if(!$.isArray(v) || v.length<=0){
					return;
				}

				for(var i in v){
					var key = v[i].key || v[i];

					$nodes.each(function(){
						var $span = $(this);
						if($span.data('hidden') == key){
							var $node = $span.closest('li');
							publics.check.apply($node[0]);
							return false;
						}
					});

				}

			}
		},

		packNode:function(data, level){

			if(typeof level === 'undefined'){
				level = 0;
				var $menu = $('<ul></ul>');
				for(var i in data){
					var $node = publics.packNode(data[i], level);
					$menu.append($node);
				}
				return $menu;
			}

			data.left = level * 30 + 5;
			data.checked = data.checked || false;
			data.isLeaf = (typeof data.isLeaf !== 'undefined')?data.isLeaf:((data.nodes && data.nodes.length>0)?false:true);
			data.isClosed = data.isClosed || true;

			var $node = $(prepare_node.render(data));

			if(!data.isLeaf){
				var $menu = data.isClosed ? $('<ul style="display:none;"></ul>') : $('<ul></ul>');
				$node.append($menu);
				if(data.nodes && data.nodes.length>0){
					for(var i in data.nodes){
						$menu.append(publics.packNode(data.nodes[i], level+1));
					}
				}
			}

			return $node;
		},

		check:function(doCheck){

			var $node = $(this),
				$select = $node.closest('.switch-select'),
				$data = $node.find('.data'),
				$check = $node.children('span').children('.fa-square-o'),
				$dropdown_menu = $node.closest('ul.dropdown-menu');

			$check.removeClass('fa-square-o').addClass('fa-check-square-o');

			$node.addClass('active');

			var $menu = $node.children('ul');

			//取值
			if(!$menu[0]){
				var key = $data.data('hidden'),
					text = $data.text();

				var $list = $(prepare_list.render({key:key, text:text}));

				$dropdown_menu.find('.frame-list ul').append($list);

			}

			if(doCheck !== false){
				//向下检查
				if($menu[0]){
					$menu.children('li').each(function(){
						var $li = $(this);
						if(!$li.is('.active')){
							publics.check.apply($li[0]);
						}
					});
				}
			}

			//向上检查
			var $nodes = $node.siblings('li');
			var all = true;
			$nodes.each(function(){
				var $li = $(this);
				if(!$li.is('.active')) all = false;
			});
			if(all){
				var $parent = $node.parent().closest('li');
				if(!$parent.is('.item-body') && !$parent.is('.active')){
					publics.check.apply($parent[0], [false]);
				}
			}

			privates.text.apply($select[0]);

		},

		uncheck:function(doCheck){

			var $node = $(this),
				$select = $node.closest('.switch-select'),
				$data = $node.find('.data'),
				$check = $node.children('span').children('.fa-check-square-o'),
				$dropdown_menu = $node.closest('ul.dropdown-menu');

			$check.removeClass('fa-check-square-o').addClass('fa-square-o');

			$node.removeClass('active');

			var $menu = $node.children('ul');

			if(!$menu[0]){
				//取值
				var key = $data.data('hidden'),
					text = $data.text();

				$dropdown_menu.find('li').each(function(){
					var $li = $(this);
					if($li.data('hidden') == key){
						$li.remove();
					}
				});
			}

			//向上检查
			var $parent = $node.parent().closest('li');
			if(!$parent.is('.item-body') && $parent.is('.active')){
				publics.uncheck.apply($parent[0], [false]);
			}

			if(doCheck !== false){
				//向下检查
				if($menu[0]){
					$menu.find('li').each(function(){
						var $li = $(this);
						if($li.is('.active')){
							publics.uncheck.apply($li[0], [false]);
						}
					});
				}
			}

			privates.text.apply($select[0]);

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

		//设置当前的文本
		text:function(){
			var $container = $(this),
				$input = $container.find('input.text'),
				$nodes = $container.find('.frame-list>ul>li');

			var _text = '';

			$nodes.each(function(){
				var $node = $(this);
				_text += ($node.text() + ',');
			});

			if(_text) _text = _text.substring(0, _text.length-1);

			$input.val(_text)
		}

	};

	// 事件
	// =========================

	$(document).on('click.switch.select.body', '.switch-select .dropdown-menu', function(e){
		var $menu = $(this);
		$menu.show();
		e.stopPropagation();
	});

	$(document).on('click.switch.select.node', '.switch-select .frame-tree ul li>span', function(){

		var $node = $(this),
			$menu = $node.siblings('ul');

		if($menu.is(':hidden')){
			var $i = $node.find('.fa-angle-left');
			$i.removeClass('fa-angle-left').addClass('fa-angle-down');
			$menu.slideDown();
		}else{
			var $i = $node.find('.fa-angle-down');
			$i.removeClass('fa-angle-down').addClass('fa-angle-left');
			$menu.slideUp();
		}

	});

	$(document).on('click.switch.select.check', '.switch-select .fa-square-o', function(e){

		var $check = $(this),
			$li = $check.closest('li');

		publics.check.apply($li[0]);

		e.stopPropagation();

	});

	$(document).on('click.switch.select.uncheck', '.switch-select .fa-check-square-o', function(e){

		var $check = $(this),
			$li = $check.closest('li');

		publics.uncheck.apply($li[0]);

		e.stopPropagation();

	});

	$(document).on('click.switch.select.list.remove', '.switch-select .frame-list .remove', function(e){

		var $remove = $(this),
			$li = $remove.closest('li'),
			$select = $li.closest('.switch-select'),
			$tree = $select.find('.frame-tree'),
			$spans = $tree.find('.data');

		var key = $li.data('hidden');

		$spans.each(function(){

			var $span = $(this),
				$li = $span.closest('li');

			if($span.data('hidden') == key){
				publics.uncheck.apply($li[0]);
			}

		});

		e.stopPropagation();

	});

	return $;

});