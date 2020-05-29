/* ========================================================================
 * Tetris.playerPanel.js: version 1.0.0
 * 视频播放器插件脚本
 * dependency: jQuery.js
 * ========================================================================
 * created by lvdeyang
 * 2016年12月04日
 * ======================================================================== */

+function($){

	// 依赖检查
	// =========================

	if(!$) throw new Error('缺失依赖：jQuery.js， 建议版本：1.11.0');

	// 接口
	// =========================

	var INTERFACE = ['createPlayer', 'getSelected', 'unSelect', 'set', 'get', 'setEmbed', 'getEmbed', 'fullScreen', 'exitFullScreen'],
		INTERFACE_CREATEPLAYER = 'createPlayer',
		INTERFACE_GETSELECTED = 'getSelected',
		INTERFACE_UNSELECT = 'unSelect',
		INTERFACE_SET = 'set',
		INTERFACE_GET = 'get',
		INTERFACE_SETEMBED = 'setEmbed',
		INTERFACE_GETEMBED = 'getEmbed',
		INTERFACE_FULLSCREEN = 'fullScreen',
		INTERFACE_EXITFULLSCREEN = 'exitFullScreen';

	// 默认实现
	// =========================

	var _createPlayer = _getSelected = _unSelect = _set = _get = _setEmbed = _getEmbed = _fullScreen = _exitFullScreen = function($split, config){console.log('默认的实现是空方法'); return null;}

	// 事件
	// =========================

	var EVENT = [];

	// 常量
	// =========================

	var PLUGINNAME = 'Tetris.playerPanel',
		SPLITID_BINDNAME = 'splitId',
		SPLITNUM_BINDNAME = 'splitNum',
		LAYOUT_BINDNAME = 'layout',
		PLAYER_BINDNAME = 'player',
		SIZE_BINDNAME = 'size',
		SINGLESTREAM = 'singleStream',
		MULTIPLESTREAM = 'multipleStream',
		MODE_BINDNAME = 'mode',
		FULLSCREENCONTAINER_BINDNAME = 'fullScreenContainer',
		FULLSCREENSPLIT_BINDNAME = 'fullScreenSplit',
		SINGLELAYOUT = [[1,1]];


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
		'theme':'',											//皮肤
		'size':'',												//尺寸
		'mode':MULTIPLESTREAM,									//singleStream[|multipleStream]
		'split':{
			'id':'1',									        //后台分屏id
			'layout':[[1, 1]]									//分屏布局
		},
		'player':{												//播放器的配置
			'class':'noFast noSlow noPause noProgress',
			'splitSelect':'singleSelect',
		},
		'interface':{
			'createPlayer':_createPlayer,						//创建播放器        		参数：$split, config
			'getSelected':_getSelected,						//获取播放器内选中的分屏    参数: $split
			'unSelect':_unSelect,								//取消选中					参数：$split
			'set':_set,											//设置数据					参数：$split, [data]
			'get':_get,											//获取数据					参数：$split
			'setEmbed':_setEmbed,								//加入ActiveX控件			参数：$split, $embed
			'getEmbed':_getEmbed,								//获取ActiveX控件			参数：$split
			'fullScreen':_fullScreen,							//全屏
			'exitFullScreen':_exitFullScreen					//退出全屏
		}
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(options){
			var _defaultOptions = $.extend(true, {}, defultOptions),
				_options = $.extend(true, _defaultOptions, options);

			var $container = $(this),
				$panel = $(privates.getHtmlTemplate(_options)),
				_split = _options.split,
				_mode = _options.mode,
				_player = _options.player;

			$container.append($panel);

			//缓存数据
			$panel.data(MODE_BINDNAME, _mode);
			$panel.data(SPLITID_BINDNAME, _split.id);
			$panel.data(LAYOUT_BINDNAME, _split.layout);
			$panel.data(PLAYER_BINDNAME, _player);

			//缓存接口
			privates.cacheInterface.apply($container[0], [_options.interface]);

			//设置布局
			publics.setLayout.apply($container[0], [_options.split]);

		},

		//设置layout
		setLayout:function(split, cacheSplit, player, cachePlayer){
			var cache = false;
			if(player) cache = true;

			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_totalWidth = parseInt($panel.css('width')),
				_totalHeight = parseInt($panel.css('height')),
				$wrapper = $panel.find('.wrapper').first(),
				_mode = $panel.data(MODE_BINDNAME),
				_splitId = split ? split.id : $panel.data(SPLITID_BINDNAME),
				_layout = split ? split.layout : $panel.data(LAYOUT_BINDNAME),
				player = player || $panel.data(PLAYER_BINDNAME),
				c_player, $split,
				i, _splitDesc, _width, _height, _size;

			//更新缓存
			if(cacheSplit !== false){
				$panel.data(SPLITID_BINDNAME, _splitId);
				$panel.data(SPLITNUM_BINDNAME, _layout.length);
				$panel.data(LAYOUT_BINDNAME, _layout);
			}

			//清除dom
			$wrapper.empty();

			//生成拷贝
			c_player = $.extend(true, {}, player);
			c_player.settings = c_player.settings || {};
			c_player.settings.split = c_player.settings.split || {};
			if(SINGLESTREAM === _mode){
				//单流模式
				c_player.settings.split.id = _splitId;
				c_player.settings.split.layout = _layout;
				_layout = SINGLELAYOUT;
			}else{
				c_player.settings.split.id = 1;
				c_player.settings.split.layout = SINGLELAYOUT;
			}

			for(i=0; i<_layout.length; i++){
				//初始化
				_size = [];

				_splitDesc = _layout[i];
				_width = _splitDesc[0];
				_height = _splitDesc[1];

				_width = (typeof _width==='string' && _width.indexOf('%')>=0) ? parseFloat(_width)/100 : parseFloat(_width);
				_height = (typeof _height==='string' && _height.indexOf('%')>=0) ? parseFloat(_height)/100 : parseFloat(_height);

				//缓存的是小数，以后直接使用不用再转换了
				_size.push(_width);
				_size.push(_height);

				_width = _totalWidth * _width;
				_height = _totalHeight * _height;

				$split = $('<div class="panel split float_l" data_index="'+(i+1)+'"></div>');
				$split.css({'width':_width, 'height':_height}).data(SIZE_BINDNAME, _size);

				$wrapper.append($split);
			}

			//创建播放器组件
			publics.setPlayers.apply($container[0], [c_player, cachePlayer]);

			return $container;
		},

		//加入ActiveX控件
		setEmbed:function(embedList){
			if(!embedList) return;
			if(!$.isArray(embedList)) embedList = [embedList];

			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				fn_setEmbed = $panel.data(INTERFACE_SETEMBED),
				$splitList = $panel.find('.panel.split');

			$splitList.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index'));
				if(_index <= embedList.length){
					if(embedList[_index-1][0]){
						fn_setEmbed.apply($container[0], [$split, embedList[_index-1]]);
					}
				}
			});
		},

		//获取ActiveX控件
		getEmbed:function(serialNum){

		},

		//设置播放器
		setPlayers:function(player, cache){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_mode = $panel.data(MODE_BINDNAME),
				_player = player || $panel.data(PLAYER_BINDNAME),
				_srcList = (_player.settings && _player.settings.srcList) || [],
				fn_createPlayer = $panel.data(INTERFACE_CREATEPLAYER),
				$splitList = $panel.find('.panel.split');

			//更新缓存
			if(cache !== false){
				$panel.data(PLAYER_BINDNAME, _player);
			}

			$splitList.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					i, cp_src, cp_player;

				if(SINGLESTREAM === _mode){
					//单流模式直接加入数据
					fn_createPlayer.apply($split[0], [_player]);
				}else if(MULTIPLESTREAM === _mode){
					//多流模式，重组数据
					for(i=0; i<_srcList.length; i++){
						if(_srcList[i] && parseInt(_srcList[i].serialNum) === _index){
							cp_src = $.extend(true, {}, _srcList[i]);
							cp_src.serialNum = 1;
							break;
						}
					}
					cp_player = $.extend(true, {}, _player);
					if(cp_src) {
						cp_player.settings = cp_player.settings;
						cp_player.settings.srcList = [cp_src];
					}else{
						if(cp_player.settings) cp_player.settings.srcList = [];
					}
					fn_createPlayer.apply($split[0], [cp_player]);
				}
			});
		},

		//获取播放器
		getPlayer:function(serialNum){

			//获取全部
			if(!serialNum) serialNum = [];
			if(!$.isArray(serialNum)) serialNum = [serialNum];

			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_mode = $panel.data(MODE_BINDNAME),
				_splitNum = $panel.data(SPLITNUM_BINDNAME),
				$splitList = $panel.find('.panel.split'),
				_playerList = [], i;

			if(MULTIPLESTREAM === _mode){
				//多流模式
				$splitList.each(function(){
					var $split = $(this),
						_index = parseInt($split.attr('data_index')),
						i;
					if(serialNum.length === 0){
						_playerList.push($split);
					}else{
						for(i=0; i<serialNum.length; i++){
							if(_index === parseInt(serialNum[i])){
								_playerList.push($split);
								break;
							}
						}
					}
				});
			}else if(SINGLESTREAM === _mode){
				//单流模式
				if(serialNum.length === 0){
					_playerList.push($splitList.first());
				}else{
					for(i=0; i<serialNum.length; i++){
						if(parseInt(serialNum[i]) <= parseInt(_splitNum)){
							_playerList.push($splitList.first());
							break;
						}
					}
				}
			}
			return _playerList;
		},

		//修改模式
		changeMode:function(mode){

			if(SINGLESTREAM!==mode && MULTIPLESTREAM!==mode) throw new Error('错误的参数mode：'+mode);

			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_player = $panel.data(PLAYER_BINDNAME),
				_oldList = [],
				cp_player;

			//旧模式下的数据列表
			_oldList = publics.get.apply($container[0], []);

			//构造player配置
			cp_player = $.extend(true, {}, _player);
			cp_player.settings = cp_player.settings || {};
			cp_player.settings.srcList = _oldList;

			//修改模式
			$panel.data(MODE_BINDNAME, mode);

			publics.setLayout.apply($container[0], [null, false, cp_player, false]);
		},

		//获取当前模式
		getMode:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first();
			return $panel.data(MODE_BINDNAME);
		},

		//获取选中的分屏
		getSelected:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_mode = $panel.data(MODE_BINDNAME),
				fn_getSelected = $panel.data(INTERFACE_GETSELECTED),
				$splitList = $panel.find('.panel.split'),
				_selectedList = [];

			$splitList.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					_srcList, i, c_src;

				_srcList = fn_getSelected.apply($container[0], [$split]);

				for(i=0; i<_srcList.length; i++){
					if(MULTIPLESTREAM === _mode){
						//多流模式重新构造序号
						c_src = $.extend(true, {}, _srcList[i]);
						c_src.serialNum = _index;
						_selectedList.push(c_src)
					}else{
						_selectedList.push(_srcList[i]);
					}
				}
			});
			return _selectedList
		},

		//获取所有数据
		get:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				$splitList = $panel.find('.panel.split'),
				fn_get = $panel.data(INTERFACE_GET),
				_mode = $panel.data(MODE_BINDNAME),
				_srcList = [];

			$splitList.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					_tmpList = fn_get.apply($container[0], [$split]),
					c_src, i;

				for(i=0; i<_tmpList.length; i++){
					if(MULTIPLESTREAM === _mode){
						c_src = $.extend(true, {}, _tmpList[i]);
						c_src.serialNum = _index;
						_srcList.push(c_src);
					}else{
						_srcList.push(_tmpList[i]);
					}
				}
			});

			return _srcList;
		},

		//设置数据(所有分屏都会重置)
		set:function(srcList){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_mode = $panel.data(MODE_BINDNAME),
				fn_set = $panel.data(INTERFACE_SET),
				$splitList = $panel.find('.panel.split');

			//构造player配置
			$splitList.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					i;

				if(SINGLESTREAM === _mode){
					fn_set.apply($container[0], [$split, srcList]);
				}else if(MULTIPLESTREAM === _mode){
					for(i=0; i<srcList.length; i++){
						if(parseInt(srcList[i].serialNum) === _index){
							srcList[i].serialNum = 1;
							fn_set.apply($container[0], [$split, [srcList[i]]]);
							break;
						}
					}
				}
			});
		},

		//取消选中方法
		unSelect:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_mode = $panel.data(MODE_BINDNAME),
				fn_unSelect = $panel.data(INTERFACE_UNSELECT),
				$splitList;

			//单流模式交给player自行处理
			if(SINGLESTREAM === _mode) return;

			$splitList = $panel.find('.panel.split');
			$splitList.each(function(){
				var $split = $(this);
				fn_unSelect.apply($container[0], [$split]);
			});

			return $container;
		},

		//获取分屏id
		getSplitId:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first();
			return $panel.data(SPLITID_BINDNAME);
		},

		//获取分屏数量
		getSplitNum:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first();
			return parseInt($panel.data(SPLITNUM_BINDNAME));
		},

		//全屏
		fullScreen:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				fn_getEmbed = $panel.data(INTERFACE_GETEMBED),
				fn_fullScreen = $panel.data(INTERFACE_FULLSCREEN),
				_splitNum = $panel.data(SPLITNUM_BINDNAME),
				$splitList = $panel.find('.panel.split'),
				$fullScreenContainer = $('<div style="position:absolute; width:100%; height:100%; z-index:100000;"></div>'),
				_totalWidth, _totalHeight, i;

			//创建dom获取尺寸
			$('body').append($fullScreenContainer);
			_totalWidth = parseFloat($fullScreenContainer.css('width'));
			_totalHeight = parseFloat($fullScreenContainer.css('height'));

			//加入全屏缓存
			$panel.data(FULLSCREENCONTAINER_BINDNAME, $fullScreenContainer);

			//这个地方要按顺序获取
			for(i=1; i<=_splitNum; i++){
				$splitList.each(function(){
					var $split = $(this),
						_index = parseInt($split.attr('data_index')),
						_size, _width, _height, $fullScreenSplit, $embedWrapper;

					if(_index === i){
						_size = $split.data(SIZE_BINDNAME);
						_width = _totalWidth * _size[0];
						_height = _totalHeight * _size[1];
						$embedWrapper = fn_getEmbed.apply($split[0], [$split]);
						$fullScreenSplit = $('<div></div>').css({'width':_width, 'height':_height, 'float':'left', 'border':'1px solid #eee', 'position':'relative'});

						//创建dom
						$fullScreenContainer.append($fullScreenSplit);

						//加入ActiveX控件
						$fullScreenSplit.append($embedWrapper);

						//缓存
						$split.data(FULLSCREENSPLIT_BINDNAME, $fullScreenSplit);

						return false;
					}
				});
			}

			fn_fullScreen.apply($container[0], []);
		},

		//退出全屏
		exitFullScreen:function(){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				fn_exitFullScreen = $panel.data(INTERFACE_EXITFULLSCREEN),
				fn_setEmbed = $panel.data(INTERFACE_SETEMBED),
				$fullScreenContainer = $panel.data(FULLSCREENCONTAINER_BINDNAME),
				$splitList;

			if(!$fullScreenContainer || !$fullScreenContainer[0]) return;

			$splitList = $panel.find('.panel.split');
			$splitList.each(function(){
				var $split = $(this),
					$fullScreenSplit = $split.data(FULLSCREENSPLIT_BINDNAME),
					$embed;

				if($fullScreenSplit && $fullScreenSplit[0]){
					//播放器归位
					$embed = $fullScreenSplit.find('embed').first();
					fn_setEmbed.apply($container[0], [$split, $embed.parent()]);
				}
				//清除缓存
				$split.data(FULLSCREENSPLIT_BINDNAME, null);
			});

			//清除缓存
			$panel.data(FULLSCREENCONTAINER_BINDNAME, null);

			//销毁dom
			$fullScreenContainer.remove();

			fn_exitFullScreen.apply($container[0]);
		}
	}

	// 插件入口
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

	// 私有方法
	// =========================

	var privates = {
		//配置校验
		check:function(options){

		},

		// 获取一个元素的位置
		getElementPosition:function(element,left,top){
			if(element!==null && typeof element!=='undefined' && element.id !== 'zk_Container'){
				left = left + parseFloat(element.offsetLeft);
				top = top + parseFloat(element.offsetTop);
				var _position = privates.getElementPosition(element.offsetParent, left, top);
			}else{
				return {
					"left":left,
					"top":top
				}
			}
			return _position;
		},

		//获取html模板
		getHtmlTemplate:function(options){
			var _theme = options.theme ? ' '+options.theme : '',
				_size = options.size ? ' '+options.size : '';
			return '<div class="tetris playerPanel'+_theme+_size+'"><div class="wrapper"></div></div>';
		},

		//重绘尺寸
		resize:function(e){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				_totalWidth = parseFloat($panel.css('width')),
				_totalHeight = parseFloat($panel.css('height')),
				$fullScreenContainer = $panel.data(FULLSCREENCONTAINER_BINDNAME),
				_fullScreenWidth,
				_fullScreenHeight,
				$aplitList = $container.find('.panel.split');

			if($fullScreenContainer && $fullScreenContainer[0]){
				_fullScreenWidth = parseFloat($fullScreenContainer.css('width'));
				_fullScreenHeight = parseFloat($fullScreenContainer.css('height'));
			}

			$aplitList.each(function(){
				var $split = $(this),
					$fullScreenSplit = $split.data(FULLSCREENSPLIT_BINDNAME),
					_size = $split.data(SIZE_BINDNAME),
					_width = _size[0] * _totalWidth,
					_height = _size[1] * _totalHeight;
				$split.css({'width':_width, 'height':_height});

				if($fullScreenSplit && $fullScreenSplit[0]){
					_width = _size[0] * _fullScreenWidth;
					_height = _size[1] * _fullScreenHeight;
					$fullScreenSplit.css({'width':_width, 'height':_height});
				}
			});
		},

		//缓存事件
		cacheEvent:function(event){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				i, _handler;
			for(i in EVENT){
				_handler = event[EVENT[i]];
				if(typeof _handler === 'function'){
					$panel.data(EVENT[i], _handler);
				}
			}
			return $container;
		},

		//缓存接口实现
		cacheInterface:function(implament){
			var $container = $(this),
				$panel = $container.find('.playerPanel').first(),
				i, _implament;
			for(i in INTERFACE){
				_implament = implament[INTERFACE[i]];
				if(typeof _implament === 'function'){
					$panel.data(INTERFACE[i], _implament);
				}
			}
			return $container;
		},

	}

	// 事件
	// =========================

	//延迟重绘
	$(window).bind('resize.tetris.playerPanel', function(event){
		setTimeout(function(){
			$('.tetris.playerPanel').each(function(){
				var $panel = $(this);
				privates.resize.apply($panel.parent()[0], [event]);
			});
		}, 50);
	});

	//监听esc事件
	$(document).on('keydown.tetris.playerPanel.esc.exitFullScreen', function(event){
		if(event.which == 27){
			$('.tetris.playerPanel').each(function(){
				var $panel = $(this);
				publics.exitFullScreen.apply($panel.parent()[0], []);
			});
		}
	});

}(jQuery);