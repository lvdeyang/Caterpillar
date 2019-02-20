/* ========================================================================
 * component : Tetris.tree.js: version 1.0.0
 * describe  : 树形插件脚本
 * dependency：jQuery.js 1.12.3,  Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月13日
 * ======================================================================== */

+function($){

	// 依赖检查
	// =========================

	//if(!$) throw new Error('缺失依赖：jQuery.js， 建议版本：1.12.3');


	// 接口
	// =========================

	var INTERFACE = [];

	// 默认实现
	// =========================


	// 事件
	// =========================

	var EVENT = ['check', 'uncheck', 'click', 'open', 'close', 'drag'],
		EVENT_CHECK = 'check',
		EVENT_UNCHECK = 'uncheck',
		EVENT_CLICK = 'click',
		EVENT_OPEN = 'open',
		EVENT_CLOSE = 'close',
		EVENT_DRAG = 'drag';

	// 常量
	// =========================

	var PLUGINNAME = 'tree',
		THEME_BINDNAME = 'theme',
		SIZE_BINDNAME = 'size',
		LOCALE_BINDNAME = 'locale',
		NODESTATUS_DEFAULT = 'close',
		NODETYPE_FOLDER = 'folder',
		NODETYPE_FILE = 'file',
		NODESTATUS_OPEN = 'open',
		NODESTATUS_CLOSE = 'close',
		NODESTATUS_ACTIVE = 'active',
		ACTIVENODE = 'activeNode';

	// 国际化
	// =========================

	var i18n = {
		'zh_CN':{

		}
	}

	// 默认配置
	// =========================

	/*
		data[i]:{
		    'nodeContent':'',   				    //节点名称
		    'param':'',		  						//节点参数
		    'icon':'',			  					//节点图标，这个不是imge的路径而是一个类型标记，结合theme自定义css
		    'type':'folder',   						//节点类型：folder|file
		    'status':'open',   						//节点类型为folder的状态：open|close
		    'draggable':false,						//节点是否可拖拽true[|false]
		    'checkable':false,						//是否有多选框
		    'childrenList':[]  						//子节点
	    }
	 */

	var defaultData = {
		'nodeContent':'新建的节点',
		'param':'',
		'type':'file',
		'draggable':false,
		'checkable':false
	}

	var defultOptions = {
		'theme':'',					            //为player增加自定义皮肤类
		'size':'',							    	//为player增加自定义尺寸类
		'locale':'zh_CN',							//国际化
		'data':[],
		'event':{
			'check':null,							//节点展开    this：$node	参数：param, complete, event
			'uncheck':null,						//节点展开    this：$node	参数：param, complete, event
			'click':null,							//节点展开    this：$node	参数：param, complete, event
			'open':null,							//节点展开    this：$node	参数：param, complete, event
			'close':null,							//节点收起	  this：$node   参数：param, event
			'drag':null,
		},
		interface:{}
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(options){
			var	_defaultOptions = $.extend(true, {}, defultOptions),
				_opstions = $.extend(true, _defaultOptions, options),
				_theme = _opstions.theme,
				_size = _opstions.size,
				_locale = _opstions.locale,
				_event = _opstions.event,
				_interface = _opstions.interface,
				$container = $(this),
				$tree;

			//创建dom
			$tree = $(privates.getHtmlTemplate.apply($container[0], [_opstions]));
			$container.empty().append($tree);

			//缓存数据
			$tree.data(THEME_BINDNAME, _theme);
			$tree.data(SIZE_BINDNAME, _size);
			$tree.data(LOCALE_BINDNAME, _locale);

			//缓存事件
			privates.cacheEvent.apply($container[0], [_event]);

			//缓存接口
			privates.cacheInterface.apply($container[0], [_interface]);

			return $tree;

		},
		
		//获取被点击的节点
		getActive:function(){
			var $container = $(this),
				$tree = $container.find('.Tetris.tree').first();
			return $tree.data(ACTIVENODE);
		},

		//队首插入节点
		prepend:function(node){
			var $node = $(this),
				$ul = $node.children('ul').first(),
				$container = $node.closest('.Tetris.tree').first().parent(),
				$target;

			if(privates.hasClass.apply(this, ['file'])) return;
			if(privates.hasClass.apply(this, ['close'])) $ul.slideDown(function(){$node.removeClass('close').addClass('open')});

			if(node.param && node.nodeContent){
				//认为传来的是配置
				$target = $(privates.generateNodeHtml.apply($container[0], [node]));
			}else if(!$.isArray(node) && node[0]){
				//认为传来的是jQuery对象
				$target = node;
			}

			if(!$ul[0]){
				$ul = $('<ul></ul>');
				$node.append($ul);
			}

			$ul.prepend($target);
			return $target;
		},

		//队尾插入节点
		append:function(node){
			var $node = $(this),
				$ul = $node.children('ul').first(),
				$container = $node.closest('.Tetris.tree').first().parent(),
				$target;

			if(privates.hasClass.apply(this, ['file'])) return;
			if(privates.hasClass.apply(this, ['close'])) $ul.slideDown(function(){$node.removeClass('close').addClass('open')});

			if(node.param && node.nodeContent){
				//认为传来的是配置
				$target = $(privates.generateNodeHtml.apply($container[0], [node]));
			}else if(!$.isArray(node) && node[0]){
				//认为传来的是jQuery对象
				$target = node;
			}

			if(!$ul[0]){
				$ul = $('<ul></ul>');
				$node.append($ul);
			}

			$ul.append($target);
			return $target;
		},

		//删除节点
		remove:function(){
			var $node = $(this);
			$node.remove();
		},

		//清空节点
		empty:function(){
			var $node = $(this),
				$ul = $node.children('ul');
			$ul.remove();
		},

		//设置某个节点的数据--在旧的配置基础上进行覆盖
		set:function(nodeData){
			var	$node = $(this),
				_defaultData = publics.get.apply(this, []),
				_nodeData = $.extend(true, _defaultData, nodeData),
				$newNode,
				$container = $node.closest('.Tetris.tree').first().parent();

			$newNode = $(privates.generateNodeHtml.apply($container[0], [_nodeData]));

			//复制节点属性
			$node.attr('class', $newNode.attr('class'));
			$node.attr('data_param', $newNode.attr('data_param'));

			//获取$node的子节点
			var $children = publics.children.apply($node[0], [true]);
			publics.append.apply($newNode[0], [$children]);

			//html覆盖
			$node.html($newNode.html());

			return $container;
		},

		//根据一个节点提取原始配置数据
		get:function(){
			var $node = $(this),
				_class = $node.attr('class'),
				_type = _class.split(' ')[0],
				_status = _class.replace(_type+' ', ''),
				$content = $node.find('a').first(),
				$checkbox = $content.find('span.checkbox').first(),
				$icon = $content.find('span:not(.checkbox)').first(),
				_text = $content.text();
			return {
				nodeContent:_text,
				param:$node.attr('data_param'),
				type:_type,
				status:_status,
				icon:$icon.attr('class'),
				checkable:$checkbox[0]?true:false,
				draggable:$content.attr('draggable')==='true'?true:false
			}
		},

		//获取某个节点的所有后代节点
		posterity:function(node){
			var $node = $(this),
				$posterity = $node.find('li'),
				_paramList = [];
			if(node === true){
				return $posterity;
			}else{
				$posterity.each(function(){
					var $node = $(this),
						_param = $node.attr('data_param');
					_paramList.push(_param);
				});
				return _paramList;
			}
		},

		//获取某个节点的儿子节点
		children:function(node){
			var $node = $(this),
				$children = $node.children('ul').first().children('li'),
				_paramList = [];
			if(node === true){
				return $children;
			}else{
				$children.each(function(){
					var $node = $(this),
						_param = $node.attr('data_param');
					_paramList.push(_param);
				});
				return _paramList;
			}
		},

		//获取兄弟节点
		siblings:function(node){
			var $node = $(this),
				$siblings = $node.siblings(),
				_paramList = [];
			if(node === true){
				return $siblings;
			}else{
				$siblings.each(function(){
					var $node = $(this),
						_param = $node.attr('data_param');
					_paramList.push(_param);
				});
				return _paramList;
			}
		},

		//某个节点的父节点
		parent:function(node){
			var $node = $(this),
				$ul = $node.parent();

			if($ul.is('.Tetris')) return null;

			if(node === true){
				return $ul.parent();
			}else{
				return $ul.parent().attr('data_param');
			}
		},

		//查找--节点
		find:function(keyword, first){
			var $container = $(this),
				$tree = $container.find('.Tetris.tree').first(),
				$nodeList = $tree.find('li'),
				_result = [];
			$nodeList.each(function(){
				var $node = $(this),
					_param = $node.attr('data_param');
				if(_param.indexOf(keyword) >= 0){
					_result.push($node);
					//标志只取第一个
					if(first === true) return false;
				}
			});
			return _result;
		},

		//选中
		check:function(e, direction){
			var $node = $(this),
				$tree = $node.closest('.Tetris.tree').first(),
				event_check = $tree.data(EVENT_CHECK),
				$checkbox = $node.find('.checkbox').first(),
				$layer = $node.find('.layer').first();

			//向上检查
			if(!direction || direction==='parent'){
				var $siblings = publics.siblings.apply($node[0], [true]);
				var _check = true;
				$siblings.each(function(){
					if(!privates.isChecked.apply(this)){
						_check = false;
						return false;
					}
				});
				if(_check === true){
					var $parent = publics.parent.apply($node[0], [true]);
					if($parent!=null && !privates.isChecked.apply($parent[0])){
						publics.check.apply($parent[0], [e, 'parent']);
					}
				}
			}

			//向下检查
			if(!direction || direction==='children'){
				var $children = publics.children.apply($node[0], [true]);
				$children.each(function(){
					if(!privates.isChecked.apply(this)){
						publics.check.apply(this, [e, 'children']);
					}
				});
			}

			var complete = function(){$checkbox.addClass('checked'); $layer.addClass('highlight');}

			if(typeof event_check === 'function'){
				event_check.apply($node[0], [$node.attr('data_param'), complete, e]);
			}else{
				complete();
			}
		},

		//取消选中
		uncheck:function(e, direction){
			var $node = $(this),
				$tree = $node.closest('.Tetris.tree').first(),
				event_uncheck = $tree.data(EVENT_UNCHECK),
				$checkbox = $node.find('.checkbox').first(),
				$layer = $node.find('.layer').first();

			//向上检查
			if(!direction || direction==='parent'){
				var $parent = publics.parent.apply($node[0], [true]);
				if($parent!=null && privates.isChecked.apply($parent[0])){
					publics.uncheck.apply($parent[0], [e, 'parent']);
				}
			}

			//向下检查
			if(!direction || direction==='children'){
				var $children = publics.children.apply($node[0], [true]);
				$children.each(function(){
					if(privates.isChecked.apply(this)){
						publics.uncheck.apply(this, [e, 'children']);
					}
				});
			}

			var complete = function(){$checkbox.removeClass('checked'); $layer.removeClass('highlight');}

			if(typeof event_uncheck === 'function'){
				event_uncheck.apply($node[0], [$node.attr('data_param'), complete, e]);
			}else{
				complete();
			}
		},

		//点击  this:$node
		click:function(e){
			var $node = $(this),
				$link = $node.find('a').first(),
				_linkClass = $link.attr('class') || '',
				$tree, event_click, $linkList;

			if(_linkClass.indexOf(NODESTATUS_ACTIVE) >= 0) return;

			$tree = $node.closest('.Tetris.tree').first();
			$tree.data(ACTIVENODE, $node);
			event_click = $tree.data(EVENT_CLICK);

			var complete = function(){
				$tree.find('li>a').removeClass(NODESTATUS_ACTIVE);
				$link.addClass(NODESTATUS_ACTIVE);
			}

			if(typeof event_click === 'function'){
				event_click.apply($node[0], [$node.attr('data_param'), complete, e]);
			}else{
				complete();
			}
		},

		//节点展开  this:$node
		open:function(e){
			var $node = $(this),
				_class = $node.attr('class'),
				$ul, $tree, event_open;
			_class = _class || '';

			if(_class.indexOf(NODETYPE_FILE) >= 0) return;

			$tree = $node.closest('.Tetris.tree').first();
			event_open = $tree.data(EVENT_OPEN);
			$ul = $node.find('ul').first();

			var complete = function(){
				if(!$ul[0]){
					$node.removeClass(NODESTATUS_CLOSE).addClass(NODESTATUS_OPEN);
				}else{
					$ul.slideDown(200, function(){
						$node.removeClass(NODESTATUS_CLOSE).addClass(NODESTATUS_OPEN);
					});
				}
			}
			//展开
			if(typeof event_open === 'function'){
				event_open.apply($node[0], [$node.attr('data_param'), complete, e]);
			}else{
				complete();
			}
		},

		//节点关闭
		close:function(e){
			var $node = $(this),
				_class = $node.attr('class'),
				$ul, $tree, event_close;
			_class = _class || '';

			if(_class.indexOf(NODETYPE_FILE) >= 0) return;

			$tree = $node.closest('.Tetris.tree').first();
			event_close = $tree.data(EVENT_CLOSE);
			$ul = $node.find('ul').first();

			var complete = function(){
				if(!$ul[0]){
					$node.removeClass(NODESTATUS_OPEN).addClass(NODESTATUS_CLOSE);
				}else{
					$ul.slideUp(200, function(){
						$node.removeClass(NODESTATUS_OPEN).addClass(NODESTATUS_CLOSE);
					});
				}
			}
			//收起
			if(typeof event_close === 'function'){
				event_close.apply($node[0], [$node.attr('data_param'), complete, e]);
			}else{
				complete();
			}
		},

		//节点拖拽
		drag:function(e){
			var $node = $(this),
				$tree = $node.closest('.Tetris.tree').first(),
				event_drag = $tree.data(EVENT_DRAG);

			if(typeof event_drag === 'function'){
				event_drag.apply($node[0], [$node.attr('data_param'), e]);
			}
		},
		
		//判断节点状态
		is:function(target){
			if(target === 'empty'){
				return privates.isEmpty.apply(this, []);
			}else if(target === 'active'){
				return privates.isActive.apply(this, []);
			}else if(target === 'checked'){
				return privates.isChecked.apply(this, []);
			}else{
				return privates.hasClass.apply(this, [target]);
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

		for(i=1; i<arguments.length; i++){
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
		//配置校验
		check:function(options){},

		//获取html模板
		getHtmlTemplate:function(options){

			var $container = $(this),
				_theme = options.theme?' '+options.theme:'',
				_size = options.size?' '+options.size:'',
				_data = options.data,
				i,
				_html = [];
			_html.push('<ul class="Tetris tree'+_theme+_size+'">');

			for(i=0; i<_data.length; i++){
				_html.push(privates.generateNodeHtml.apply($container[0], [_data[i]]));
			}

			_html.push('</ul>');

			return _html.join('');
		},

		//获取一个节点的html模板
		generateNodeHtml:function(nodeData){
			var $container = $(this),
				nodeHtml = [],
				_nodeContent = nodeData.nodeContent,
				_param = nodeData.param,
				_icon = nodeData.icon || nodeData.type,
				_type = nodeData.type,
				_status = nodeData.status || NODESTATUS_DEFAULT,
				_draggable = nodeData.draggable || false,
				_checkable = nodeData.checkable,
				_childrenList = nodeData.childrenList,
				i;
			nodeHtml.push('<li class="'+_type+' '+_status+'" data_param="'+_param+'"><span class="treeIcon"></span><a class="link"'+(_draggable===true?' draggable="true"':'')+'>');

			if(_checkable === true) nodeHtml.push('<span class="checkbox"></span>');

			nodeHtml.push('<span class="'+_icon+'"></span>'+_nodeContent+'</a>' +
						  '<div class="layer"><div></div></div>');

			if($.isArray(_childrenList) && _childrenList.length>0){
				nodeHtml.push('<ul>');

				for(var i=0; i<_childrenList.length; i++){
					nodeHtml.push(privates.generateNodeHtml.apply($container[0], [_childrenList[i]]));
				}

				nodeHtml.push('</ul>');
			}

			nodeHtml.push('</li>');

			return nodeHtml.join('');
		},

		//重绘尺寸
		/*resize:function(e){},*/

		//缓存事件
		cacheEvent:function(event){
			var $container = $(this),
				$player = $container.find('.Tetris.tree').first(),
				i, _handler;
			for(i in EVENT){
				_handler = event[EVENT[i]];
				if(typeof _handler === 'function'){
					$player.data(EVENT[i], _handler);
				}
			}
			return $container;
		},

		//缓存接口实现
		cacheInterface:function(implament){
			var $container = $(this),
				$player = $container.find('.Tetris.tree').first(),
				i, _implament;
			for(i in INTERFACE){
				_implament = implament[INTERFACE[i]];
				if(typeof _implament === 'function'){
					$player.data(INTERFACE[i], _implament);
				}
			}
			return $container;
		},
		
		//判断节点是否为空
		isEmpty:function(){
			var $node = $(this),
				$ul = $node.find('ul').first();
			if($ul[0]) return false;
			return true;
		},
		
		//判断节点是否被点击
		isActive:function(){
			var $node = $(this),
				$content = $node.find('a').first();
			return $content.is('.active');
		},
		
		//判断节点是否被选中
		isChecked:function(){
			var $node = $(this),
			    $checkbox = $node.find('.checkbox').first();
			return $checkbox.is('.checked');
		},

		//判断是否有某个类
		hasClass:function(target){
			var $node = $(this);
			return $node.is('.'+target);
		}

	}

	// 事件
	// =========================

	//重绘
	/*$(window).bind('resize.Tetris.tree', function(evnet){});*/

	//展开收起事件
	$(document).on('click.Tetris.tree.slide', '.Tetris.tree span.treeIcon', function(event){
		event.stopPropagation();
		var $node = $(this).closest('li').first(),
			_class = $node.attr('class');

		_class = _class || '';
		if(_class.indexOf(NODESTATUS_OPEN) >= 0){
			//收起
			publics.close.apply($node, [event]);
		}else if(_class.indexOf(NODESTATUS_CLOSE) >= 0){
			//展开
			publics.open.apply($node, [event]);
		}
	});

	//节点点击事件
	$(document).on('click.Tetris.tree.node.click', '.Tetris.tree a', function(event){
		event.stopPropagation();
		var $link = $(this),
			$node = $link.closest('li').first();
		publics.click.apply($node[0], [event]);
	});

	//节点选中事件
	$(document).on('click.Tetris.tree.node.check', '.Tetris.tree .checkbox', function(event){
		event.stopPropagation();
		var $checkbox = $(this),
			_class = $checkbox.attr('class'),
			$node = $checkbox.closest('li').first();
		if(_class.indexOf('checked') >= 0){
			//取消选中
			publics.uncheck.apply($node[0], [event]);
		}else{
			//选中
			publics.check.apply($node[0], [event]);
		}
	});

	//拖拽
	document.addEventListener('dragstart', function(event){
		var $target = $(event.target), $node,
			$tree = $target.closest('.Tetris.tree').first(),
			_class = $target.attr('class');
		if($tree[0] && _class.indexOf('link')>=0) {
			$node = $target.parent();
			publics.drag.apply($node[0], [event]);
		}
	});

}(jQuery);