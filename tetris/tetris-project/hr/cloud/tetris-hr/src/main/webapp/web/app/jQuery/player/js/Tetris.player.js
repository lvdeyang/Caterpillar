/* ========================================================================
 * Tetris.player.js: version 1.0.0
 * 视频播放器插件脚本
 * dependency：Tetris.date.ext.js, jQuery.js
 * ========================================================================
 * created by lvdeyang
 * 2016年12月02日
 * ======================================================================== */

+function($){

	// 依赖检查
	// =========================

	if(!$) throw new Error('缺失依赖：jQuery.js， 建议版本：1.11.0');
	/*if(!Date.prototype['Tetris.version']) throw new Error('缺失依赖Tetris.date.ext.js，建议版本1.0.0');
	if(Date.prototype['Tetris.version'] < '1.0.0') throw new Error('当前Tetris.date.ext.js版本小于1.0.0，请更新版本');*/

	// 接口
	// =========================

	var INTERFACE = ['info', 'error', 'getStreamProportion', 'getDuration', 'getPlayTime', 'getStartTime'],
		INTERFACE_INFO = 'info',
		INTERFACE_ERROR = 'error',
		INTERFACE_GETSTREAMPROPORTION = 'getStreamProportion',
		INTERFACE_GETDURATION = 'getDuration',
		INTERFACE_GETPLAYTIME = 'getPlayTime',
		INTERFACE_GETSTARTTIME = 'getStartTime';

	// 默认实现
	// =========================

	var _info = _error = function(title, message){alert(message);},
		_getStreamProportion = function(){return '16:9';},
		_getDuration = _getPlayTime = _getStartTime = function(){return 0};

	// 事件
	// =========================

	var EVENT = ['onOsd', 'onRemove', 'onPlay', 'onPause', 'onResume', 'onStop', 'goPrev', 'goNext', 'goFast', 'goSlow', 'onSelected',
			 	  'unSelected', 'onDrop', 'layoutSet', 'printScreen', 'setFolder', 'openFolder', 'ptzctrl', 'forward', 'record', 'decodeBind', 'seek', 'onresize', 'volumeSet', 'fullScreen', 'exitFullScreen'],
		EVENT_OSD = 'onOsd',
		EVENT_REMOVE = 'onRemove',
		EVENT_PLAY = 'onPlay',
		EVENT_PAUSE = 'onPause',
		EVENT_RESUME = 'onResume',
		EVENT_STOP = 'onStop',
		EVENT_PREV = 'goPrev',
		EVENT_NEXT = 'goNext',
		EVENT_FAST = 'goFast',
		EVENT_SLOW = 'goSlow',
		EVENT_SELECTED = 'onSelected',
		EVENT_UNDELECTED = 'unSelected',
		EVENT_DROP = 'onDrop',
		EVENT_LAYOUTSET = 'layoutSet',
		EVENT_PRINTSCREEN = 'printScreen',
		EVENT_SETFOLDER = 'setFolder',
		EVENT_OPENFOLDER = 'openFolder',
		EVENT_PTZCTRL = 'ptzctrl',
		EVENT_FORWARD = 'forward',
		EVENT_RECORD = 'record',
		EVENT_DECODE_BIND = 'decodeBind',
		EVENT_SEEK = 'seek',
		EVENT_RESIZE = 'onresize',
		EVENT_VOLUMESET = 'volumeSet',
		EVENT_FULLSCREEN = 'fullScreen',
		EVENT_EXITFULLSCREEN = 'exitFullScreen';

	// 常量
	// =========================

	var PLUGINNAME = 'Tetris.player',
		OSD_BINDNAME = 'osd';
		LAYOUT_BINDNAME = 'layout',
		SRCDATA_BINDNAME = 'srcData',
		THEME_BINDNAME = 'theme',
		SIZE_BINDNAME = 'size',
		SPLITSLECT_BINDNAME = 'splitSelect',
		TIMEFORMAT_BINDNAME = 'timeFormat',
		SPLITNUM_BINDNAME = 'splitNum',
		SPLITID_BINDNAME = 'splitId',
		I18N_BINDNAME = 'i18n',
		STATUS_BINDNAME = 'status',
		FULLSCREENCONTAINER_BINDNAME = 'fullScreenContainer',
		AUTOPLAY_BINDNAME = 'autoPlay',
		STATUS_PLAY = 'play',
		STATUS_STOP = 'stop',
		STATUS_PAUSE = 'pause',
		STATUS_FAST = 'fast',
		STATUS_SLOW = 'slow',
		TIMER_BINDNAME = 'timer',
		TIMER_SEEK_BINDNAME = "timer_seek";

	// 国际化
	// =========================

	var i18n = {
		'zh_CN':{
			'info':{
				'play':{
					'title':'信息提示',
					'noSetting':'当前屏幕没有任何配置',
				},
				'remove':{
					'title':'信息提示',
					'noStop':'删除配置需要先停止播放',
				},
				'audio':{
					'title':'信息提示',
					'noStart':'您还没有播放视频',
				},
				'mute':{
					'title':'信息提示',
					'noStart':'您还没有播放视频',
				},
				'select':{
					'title':'信息提示',
					'noSrcData':'当前分屏没有元数据配置',
					'forbid':'当前播放器禁止选中分屏',
				},
				'unSelect':{
					'title':'信息提示',
					'noSrcData':'当前分屏没有元数据配置',
					'forbid':'当前播放器禁止选中分屏',
				},
				'layoutSet':{
					'title':'信息提示',
					'noSetting':'当前屏幕没有任何配置',
					'noSelected':'你还没有选择要操作的分屏'
				},
				'ptzctrl':{
					'title':'信息提示',
					'noSetting':'当前屏幕没有任何配置',
					'noSelected':'你还没有选择要操作的分屏'
				},
				'forward':{
					'title':'信息提示',
					'noSetting':'当前屏幕没有任何配置'
				},
				'record':{
					'title':'信息提示',
					'noSetting':'当前屏幕没有任何配置'
				},
				'fullScreen':{
					'title':'信息提示',
					'noStart':'您还没有播放视频',
					'noSetting':'您还没有配置要看的内容'
				},
				'exitFullScreen':{
					'title':'信息提示',
					'noStart':'您还没有播放视频',
					'noSetting':'您还没有配置要看的内容'
				},
				'goFast':{
					'title':'信息提示',
					'maxSpeed':'已达到最大播放速度8倍速！'
				},
				'goSlow':{
					'title':'信息提示',
					'minSpeed':'已达到最慢播放速度8倍速！'
				}
			}
		}
	}

	// 默认配置
	// =========================

	/*
	 srcList:[{
		 serialNum:1,
		 volume:'60%',
		 timeduration:'视频持续时长，单位秒，优先取这个值，值为0时调播放器接口',废弃
		 baseoffset:'基础偏移量，分片起始时间，seek的初始偏移量',单位：毫秒,
		 timeoffset:'时间偏移量，单位秒（事件参数），进度条偏移量+baseoffset',
		 startTime:'视频开始时间点，单位：毫秒',
		 totalTime:'视频总时长，单位：毫秒',
		 format:{
			 head:'',
			 bodyUp:'',
			 bodyDown:''
		 },
		 [other data]
	 },{}]
	 layout:[
		[width, height],
	 	[width, height],
	 ],
	 分屏的顺序是横着数的，数组第0位代表第一屏（主屏），第1位代表第二屏，以此类推
	 width和height以小数形式表示
	 */
	var defultOptions = {
		theme:'',					            //为player增加自定义皮肤类
		size:'',							    //为player增加自定义尺寸类
		class:'',								//为player增加类noOsd noForward noRecord noPlay noStop noFullScreen noPrev noNext noFast noSlow noPause noPrintScreen noLayoutSet noPtzctrl noProgress,以空格来分隔
		title:'',								//屏幕头显示文字
		autoPlay:true,						//设置数据后自动播放
		locale:'zh_CN',						//国际化
		splitSelect:false,					//屏幕选中效果  false[|singleSelect][|multipleSelect]
		timeFormat:'yyyy-MM-dd hh:mm:ss',	//播放器时间格式
		settings:{								//源设置
			split:{
				id:1,							//后台分屏id
				layout:[[1, 1]]				//分屏布局
			},
			srcList:[]
		},
		event:{
			onRemove:null,						//删除按钮点击     参数：$container[0], [data], complete, event
			onPlay:null,							//开始按钮点击     参数：$embed, [data], complete, event
			onPause:null,							//暂停按钮点击     参数：$embed, [data], complete, event
			onResume:null,						//继续按钮点击     参数：$embed, [data], complete, status, event
			onStop:null,							//停止按钮点击     参数：$embed, [data], complete, event
			goFast:null,							//快放按钮点击     参数：$embed, [data], speed, complete, status, event
			goSlow:null,							//慢放按钮点击     参数：$embed, [data], speed, complete, status, event
			onSelected:null,						//屏幕选中		   参数：data, complete, event
			unSelected:null,						//取消选中		   参数：data, complete, event
			onDrop:null,							//拖放			   参数：$embed, serialNum, oldData[|undefined], event
			layoutSet:null,						//屏幕设置按钮     参数：[data], event
			ptzctrl:null,							//云台按钮         参数：[data], $container, $embed.parent(), status, event
			forward:null,							//上屏按钮		   参数：[data], event
			seek:null,								//进度条定位       参数：$embed, time_ms, event
			onresize:null,						//视频尺寸重置     参数：$embed, event
			volumeSet:null,						//设置声音         参数：$embed, volume[0~100|true|false], event       返回值:1 成功；0 失败
			fullScreen:null,						//全屏			   参数：$embed complete
			exitFullScreen:null					//退出全屏		   参数：$embed complete
		},
		interface:{
			info:_info,								         //普通消息提示     参数：title, message
			error:_error,							         //错误消息提示     参数：title, message，ex
			getStreamProportion:_getStreamProportion,	 //获取视频流宽高比       activeX       参数：$embed
			getDuration:_getDuration,						 //获取视频总时长         activeX       参数：$embed    单位：毫秒
			getPlayTime:_getPlayTime,                      //获取当前视频播放的时间 activeX       参数：$embed    单位：毫秒
			getStartTime:_getStartTime 				     //获取视频开始时间点     activeX       参数：$embed    单位：毫秒
		}
	};

	// 共有方法
	// =========================

	var publics = {

		//版本
		version:function(){return '1.0.0';},

		//创建
		create:function(options){
			//配置数据拷贝
			var _defaultOptions = $.extend(true, {}, defultOptions),
				_options = $.extend(true, _defaultOptions, options);

			//配置校验
			privates.check(_options);

			var $container = $(this),
				_theme = _options.theme,
				_size = _options.size,
				_locale = _options.locale,
				_splitSelect = _options.splitSelect,
				_timeFormat = _options.timeFormat,
				_autoPlay = _options.autoPlay,
				_settings = _options.settings,
				_event = _options.event,
				_implament = _options.interface,
				$player = $(privates.getHtmlTemplate(_options));

			$container.addClass('tetris-player-wrapper');

			//创建dom
			$container.empty().append($player);

			//缓存数据
			$player.data(THEME_BINDNAME, _theme);
			$player.data(SIZE_BINDNAME, _size);
			$player.data(SPLITSLECT_BINDNAME, _splitSelect);
			$player.data(TIMEFORMAT_BINDNAME, _timeFormat);
			$player.data(I18N_BINDNAME, i18n[_locale]);
			$player.data(STATUS_BINDNAME, STATUS_STOP);
			$player.data(AUTOPLAY_BINDNAME, _autoPlay);

			//创建分屏
			publics.setLayout.apply($container[0], [_settings]);

			//缓存事件
			privates.cacheEvent.apply($container[0], [_event]);

			//缓存接口实现
			privates.cacheInterface.apply($container[0], [_implament]);

			//链式调用
			return $container;
		},

		//设置osd.id, osd.name
		setOsd:function(osd){
			var $container = $(this);
			var $player = $container.find('.player').first();
			$player.data(OSD_BINDNAME, osd);
			$player.find('.head .text').text(osd.name).attr('title', osd.name);
		},

		//获取osd
		getOsd:function(){
			var $container = $(this);
			var $player = $container.find('.player').first();
			return $player.data(OSD_BINDNAME);
		},

		//删除osd
		deleteOsd:function(){
			var $container = $(this);
			var $player = $container.find('.player').first();
			var osd = $player.data(OSD_BINDNAME);
			$player.removeData(OSD_BINDNAME);
			$player.find('.head .text').text('').removeAttr('title');
			return osd;
		},

		//设置layout
		setLayout:function(settings){
			var	$container = $(this),
				_defaultSettings = $.extend(true, {}, defultOptions.settings),
				_settings = $.extend(true, _defaultSettings, settings),
				_split = _settings.split,
				_splitId = _split.id,
				_layout = _split.layout,
				_srcList = _settings.srcList,
				$player = $container.find('.player').first(),
				$splitList = $player.find('.splitList').first(),
				_serialNum;

			$splitList.find('.wrapper').first().empty();

			for(var i=0; i<_layout.length; i++){
				_serialNum = i + 1;
				privates.generateSplitDom.apply($container[0], [_serialNum, _layout[i]]);
			}

			//设置数据
			publics.set.apply($container[0], [_srcList]);

			//缓存layout信息
			$splitList.data(LAYOUT_BINDNAME, _layout);
			$splitList.data(SPLITNUM_BINDNAME, _layout.length);
			$splitList.data(SPLITID_BINDNAME, _splitId);

			return $container;
		},

		//设置数据
		set:function(srcList, select){
			if(!srcList) return;

			if(!$.isArray(srcList)){
				srcList = [srcList];
			}

			if(srcList.length <= 0) return;

			var $container = $(this),
				$player = $container.find('.player'),
				_autoPlay = $player.data(AUTOPLAY_BINDNAME),
				$split = $player.find('.split'),
				_length = $split.length;

			$split.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					_tmpSrc, _tmpFormat,
					_oldSrc = $split.data(SRCDATA_BINDNAME),
					i,
					$dataInfo = $split.find('div').first(),
					$divList;

				for(i=0; i<srcList.length; i++){
					_tmpSrc = srcList[i];
					if(_index === parseInt(_tmpSrc.serialNum)){

						//缓存数据
						_tmpSrc = $.extend(true, (_oldSrc || {}), _tmpSrc);
						$split.data(SRCDATA_BINDNAME, _tmpSrc);

						//修改dom
						$dataInfo.empty().removeClass('noSetting').addClass('dataInfo');
						if(_tmpSrc.format.bodyUp) $dataInfo.append($('<div></div>').text((_tmpSrc.format&&_tmpSrc.format.bodyUp)?_tmpSrc.format.bodyUp:''));
						if(_tmpSrc.format.bodyDown) $dataInfo.append($('<div></div>').text((_tmpSrc.format&&_tmpSrc.format.bodyDown)?_tmpSrc.format.bodyDown:''));

						$divList = $dataInfo.find('div');
						if($divList.length === 1) $divList.addClass('only');

						//删除按钮只创建一次
						if(!_oldSrc){
							if(_length > 1){
								$split.prepend('<button class="remove"></button>');
							}
						}

						//选中效果
						if(select){
							publics.select.apply($container[0], [_index]);
						}
						break;
					}
				}
			});

			return $container;
		},

		//加入ActiveX控件, $embedWrapper:<div><embed /></div>
		setEmbed:function($embedWrapper){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$body = $player.find('.body').first(),
				$wrapper = $player.find('.body>.wrapper').first(),
				$embed = $embedWrapper.find('embed').first();

			$embedWrapper.css({'width':'100%', 'height':'100%'});
			$embed[0].width = '100%';
			$embed[0].height = '100%';
			$wrapper.append($embedWrapper);
		},

		//获取数据，return array
		get:function(serialNum){
			if(!serialNum) serialNum = [];
			if(!$.isArray(serialNum)) serialNum = [serialNum];

			var $container = $(this),
				$player = $container.find('.player').first(),
				$split = $player.find('.split'),
				_srcList = [];

			$split.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					_srcData = $split.data(SRCDATA_BINDNAME),
					i;

				if(!_srcData) return;

				if(serialNum.length <= 0){
					_srcList.push(_srcData);
				}else{
					for(i=0; i<serialNum.length; i++){
						if(_index === parseInt(serialNum[i])){
							_srcList.push(_srcData);
							return;
						}
					}
				}
			});

			return _srcList;
		},

		//获取播放器,返回:embedWrapper
		getEmbed:function(){
			var $container = $(this),
				$embed = $container.find('embed').first();
			return $embed.parent();
		},

		//删除
		remove:function(serialNum){
			if(!serialNum) serialNum = [];
			if(!$.isArray(serialNum)) serialNum = [serialNum];

			var $container = $(this),
				$player = $container.find('.player').first(),
				_event_remove = $player.data(EVENT_REMOVE),
				$split = $player.find('.split'),
				_srcList = [];

			$split.each(function(){
				var $split = $(this),
					_index = parseInt($split.attr('data_index')),
					_srcData = $split.data(SRCDATA_BINDNAME),
					i,
					$dataInfo = $split.find('div').first();;

				if(!_srcData) return;

				//修改dom
				var _complete = function(){
					//取消选中
					publics.unSelect.apply($container[0], [_index]);

					//删除数据
					_srcList.push($split.data(SRCDATA_BINDNAME));
					$split.data(SRCDATA_BINDNAME, null);

					//修改dom
					$split.find('.remove').first().remove();
					$dataInfo.empty().removeClass('dataInfo').addClass('noSetting').text('未设置');
				}

				if(serialNum.length <= 0){
					if(typeof _event_remove === 'function'){
						_event_remove.apply($split[0], [$container, _srcData, _complete]);
					}else{
						_complete();
					}
				}else{
					for(i=0; i<serialNum.length; i++){
						if(_index === parseInt(serialNum[i])){
							if(typeof _event_remove === 'function'){
								_event_remove.apply($split[0], [$container, _srcData, _complete]);
							}else{
								_complete();
							}
							return;
						}
					}
				}
			});

			//删除osd
			var osd = publics.deleteOsd.apply($container[0]);

			return {
				osd:osd,
				srcList:_srcList
			};
		},

		//全屏
		fullScreen:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				_i18n = $player.data(I18N_BINDNAME),
				fn_info = $player.data(INTERFACE_INFO),
				event_fullScreen = $player.data(EVENT_FULLSCREEN),
				event_resize = $player.data(EVENT_RESIZE),
				$embed = $player.find('embed').first(),
				$buttons = privates.getButtons.apply($container[0]),
				$fullScreenContainer = $('<div style="position:fixed; left:0; top:0; width:100%; height:100%; z-index:100000;"></div>');

			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.fullScreen.title, _i18n.info.fullScreen.noSetting);
				return;
			};

			var complete = function(){
				$buttons.$fullScreen.addClass('hidden');
				$buttons.$exitFullScreen.removeClass('hidden');
				$buttons.$forward.attr('disabled', true);
				$buttons.$ptzctrl.attr('disabled', true);
				$buttons.$record.attr('disabled', true);

				$fullScreenContainer.data(FULLSCREENCONTAINER_BINDNAME, $container);
				$player.addClass('max-size');
				$('body').append($fullScreenContainer);
				$fullScreenContainer.append($player);

				publics.resize.apply($fullScreenContainer[0]);
			};

			if(typeof event_resize === 'function'){
				event_resize.apply($container[0], [$embed]);
			}
			if(typeof event_fullScreen === 'function'){
				event_fullScreen.apply($container[0], [$embed, complete]);
			}else{
				complete();
			}
		},

		//退出全屏
		exitFullScreen:function(){
			var $fullScreenContainer = $(this),
				$container = $fullScreenContainer.data(FULLSCREENCONTAINER_BINDNAME),
				$player = $fullScreenContainer.find('.player').first(),
				_srcList = publics.get.apply($fullScreenContainer[0], []),
				_i18n = $player.data(I18N_BINDNAME),
				fn_info = $player.data(INTERFACE_INFO),
				event_exitfullScreen = $player.data(EVENT_EXITFULLSCREEN),
				event_resize = $player.data(EVENT_RESIZE),
				$embed = $fullScreenContainer.find('embed').first(),
				$buttons = privates.getButtons.apply($fullScreenContainer[0]);

			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.exitFullScreen.title, _i18n.info.exitFullScreen.noSetting);
				return;
			};

			var complete = function(){
				$buttons.$fullScreen.removeClass('hidden');
				$buttons.$exitFullScreen.addClass('hidden');
				$buttons.$forward.removeAttr('disabled');
				$buttons.$ptzctrl.removeAttr('disabled');
				$buttons.$record.removeAttr('disabled');
				$player.removeClass('max-size');
				$container.append($player);
				$fullScreenContainer.remove();
				publics.resize.apply($container[0]);
			};

			if(typeof event_resize === 'function'){
				event_resize.apply($container[0], [$embed]);
			}
			if(typeof event_exitfullScreen === 'function'){
				event_exitfullScreen.apply($container[0], [$embed, complete]);
			}else{
				complete();
			}
		},

		//选中一个分屏
		select:function(serialNum, e){
			if(!serialNum || isNaN(parseInt(serialNum))) return;

			var $container = $(this),
				$player = $container.find('.player').first(),
				_splitSelect = $player.data(SPLITSLECT_BINDNAME),
				fn_info = $player.data(INTERFACE_INFO),
				event_selected = $player.data(EVENT_SELECTED),
				_i18n = $player.data(I18N_BINDNAME),
				$splitList = $player.find('.split');

			if(!_splitSelect){
				fn_info(_i18n.info.select.title, _i18n.info.select.forbid);
				return;
			}

			serialNum = parseInt(serialNum);

			$splitList.each(function(){
				var $split = $(this),
					_splitClass = $split.attr('class') || '',
					_index = parseInt($split.attr('data_index')),
					_srcData = $split.data(SRCDATA_BINDNAME);

				if(!_srcData) return;

				if(serialNum === _index){
					//已经选中就不在选中了
					if(_splitClass.indexOf('selected') >= 0) return;

					var _complete = function(){
						if(_splitSelect === 'singleSelect'){
							$splitList.removeClass('selected');
							$split.addClass('selected');
						}else if(_splitSelect === 'multipleSelect'){
							$split.addClass('selected');
						}
					}

					if(typeof event_selected === 'function'){
						event_selected.apply($container[0], [_srcData, _complete, e]);
					}else{
						_complete();
					}
				}
			});
		},

		//取消选中一个分屏
		unSelect:function(serialNum, e){
			if(!serialNum || isNaN(parseInt(serialNum))) return;

			var $container = $(this),
				$player = $container.find('.player').first(),
				_splitSelect = $player.data(SPLITSLECT_BINDNAME),
				fn_info = $player.data(INTERFACE_INFO),
				event_unselected = $player.data(EVENT_UNDELECTED),
				_i18n = $player.data(I18N_BINDNAME),
				$splitList = $player.find('.split');

			if(!_splitSelect){
				fn_info(_i18n.info.unSelect.title, _i18n.info.unSelect.forbid);
				return;
			}

			serialNum = parseInt(serialNum);

			$splitList.each(function(){
				var $split = $(this),
					_splitClass = $split.attr('class'),
					_index = parseInt($split.attr('data_index')),
					_srcData = $split.data(SRCDATA_BINDNAME);

				if(!_srcData) return;

				if(serialNum === _index){
					if(_splitClass.indexOf('selected') < 0) return;

					var _complete = function(){
						$split.removeClass('selected');
					}

					if(typeof event_unselected === 'function'){
						event_unselected.apply($container[0], [_srcData, _complete, event]);
					}else{
						_complete();
					}
				}
			});
		},

		//获取选中的配置
		getSelected:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$splitList = $player.find('.split'),
				_srcList = [];

			$splitList.each(function(){
				var $split = $(this),
					_srcData = $split.data(SRCDATA_BINDNAME),
					_splitClass = $split.attr('class');

				if(_splitClass.indexOf('selected')>=0 && _srcData){
					_srcList.push(_srcData);
				}
			});

			return _srcList;
		},

		//获取当前分屏数量
		getSplitNum:function(){
			var $container = $(this),
				$splitList = $container.find('.splitList').first();
			return parseInt($splitList.data(SPLITNUM_BINDNAME));
		},

		//获取当前分屏id
		getSplitId:function(){
			var $container = $(this),
				$splitList = $container.find('.splitList').first();
			return parseInt($splitList.data(SPLITID_BINDNAME));
		},

		//播放
		play:function(autoPlayCheck, e) {
			var $container = $(this),
				$player = $container.find('.player').first(),
				_playerClass = $player.attr('class'),
				_srcList = publics.get.apply($container[0], []),
				_osd = publics.getOsd.apply($container[0], []),
				_i18n = $player.data(I18N_BINDNAME),
				fn_info = $player.data(INTERFACE_INFO),
				handler_play = $player.data(EVENT_PLAY),
				_splitNum;

			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.play.title, _i18n.info.play.noSetting);
				return;
			};

			//自动播放检验
			if(autoPlayCheck === true){
				_splitNum = parseInt(publics.getSplitNum.apply($container[0], []));
				if(_srcList.length < _splitNum) return;
			}

			//清除进度条任务
			privates.clearTimer.apply($container[0], []);

			var _complete = function(){
				var $buttons = privates.getButtons.apply($container[0], []),
					_srcData, _startTime, _totalTime, $startTime, $endTime;

				//禁止：开始、删除； 释放：停止
				if(_playerClass.indexOf('noPause') >= 0){
					$buttons.$play.attr('disabled', true);
				}else{
					$buttons.$play.addClass('hidden');
					$buttons.$pause.removeClass('hidden');
				}
				$buttons.$goFast.removeAttr('disabled');
				$buttons.$stop.removeAttr('disabled');
				$buttons.$remove.attr('disabled', true);
				$buttons.$goSlow.removeAttr('disabled');
				$buttons.$mute.addClass('hidden');
				$buttons.$audio.removeClass('hidden');
				$buttons.$speedModel.text('正常');
				$buttons.$speed.text(1);

				//播放标志
				$player.addClass('playing');
				$player.data(STATUS_BINDNAME, STATUS_PLAY);

				//点播模式走的是ActiveX接口
				if(_playerClass.indexOf('noProgress') < 0){
					//点播只会有一个元数据
					_srcData = _srcList[0];
					_srcData.baseoffset = _srcData.baseoffset || 0;

					//先seek一下
					publics.seek.apply($container[0], [_srcData.baseoffset]);

					//获取开始时间
					_startTime = privates.getStartTime.apply($container[0], []);

					//获取结束时间
					_totalTime = privates.getDuration.apply($container[0], []);
					if(_totalTime === -1){
						_totalTime = 31622400000;
					}

					//设置缓存
					publics.set.apply($container[0], [{
						'serialNum':1,
						'startTime':_startTime,
						'totalTime':_totalTime,
						'baseoffset':_srcData.baseoffset
					}]);

					//设置时间显示
					$startTime = $player.find('.startTime').first();
					$endTime = $player.find('.endTime').first();
					$startTime.text(privates.getFormatTime.apply($container[0], [_startTime + _srcData.baseoffset]));
					$endTime.text(privates.getFormatTime.apply($container[0], [_startTime + _totalTime]));

					//设置开启音量
					publics.volumeSet.apply($container[0], [true, e]);
					//设置音量为100
					publics.volumeSet.apply($container[0], [100, e]);

					//进度条置0
					privates.scrollTo.apply($container[0], [0]);

					//启动进度条
					privates.setTimer.apply($container[0], [1]);
				}

			}
			if(typeof handler_play === 'function'){
				//this绑定到对应的按钮上去
				handler_play.apply($container[0], [$player.find('embed').first(), _srcList, _osd, _complete, e]);
			}else{
				_complete();
			}
		},

		//停止, 提供一回调
		stop:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_playerClass = $player.attr('class'),
				_srcList = publics.get.apply($container[0], []),
				fn_info = $player.data(INTERFACE_INFO),
				handler_stop = $player.data(EVENT_STOP);

			//清除进度条任务
			privates.clearTimer.apply($container[0], []);

			//清除变速进度条
			privates.clearCusomTimer.apply($player.parent()[0], []);

			var _complete = function(){
				var $buttons = privates.getButtons.apply($container[0], []);

				//禁止：开始、删除； 释放：停止
				if(_playerClass.indexOf('noPause') >= 0){
					$buttons.$play.removeAttr('disabled');
				}else{
					$buttons.$play.removeClass('hidden');
					$buttons.$pause.addClass('hidden');
				}
				$buttons.$printScreen.attr('disabled', true);
				$buttons.$goPrev.attr('disabled', true);
				$buttons.$goNext.attr('disabled', true);
				$buttons.$goFast.attr('disabled', true);
				$buttons.$stop.attr('disabled', true);
				$buttons.$remove.removeAttr('disabled');
				$buttons.$goSlow.attr('disabled', true);
				$buttons.$mute.addClass('hidden');
				$buttons.$audio.removeClass('hidden');
				$buttons.$speedModel.text('停止');
				$buttons.$speed.text(0);

				//清除播放标志
				$player.removeClass('playing');
				$player.data(STATUS_BINDNAME, STATUS_STOP);
				privates.setSpeed.apply($container[0], [1]);

				//进度条置0
				privates.scrollTo.apply($container[0], [0]);
				//时间置到开始时间
				privates.startTimeTo($container[0], [0, true]);

				//移除拖拽样式
				publics.removeDragStatus.apply($container[0], []);

				if(typeof fn === 'function'){
					fn();
				}
			}

			if(typeof handler_stop === 'function'){
				//this绑定到对应的按钮上去
				return handler_stop.apply($container[0], [$player.find('embed').first(), _srcList, _complete, e]);
			}else{
				_complete();
			}
			return 0;
		},

		//暂停
		pause:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_parse = $player.data(EVENT_PAUSE);

			//清除1倍速进度条
			privates.clearTimer.apply($container[0], []);

			var _complete = fn || function(){};

			if(typeof handler_parse === 'function'){
				handler_parse.apply($container[0], [$player.find('embed').first(), _srcList, _complete, e]);
			}else{
				_complete();
			}
		},

		//恢复播放
		resume:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_status = $player.data(STATUS_BINDNAME),
				_srcList = publics.get.apply($container[0], []),
				handler_resume = $player.data(EVENT_RESUME);

			//清除变速进度条
			privates.clearCusomTimer.apply($container[0], []);

			//恢复进度条
			privates.setTimer.apply($container[0], [1]);

			var _complete = function(){
				var $buttons = privates.getButtons.apply($container[0], []);

				//按钮切换
				$buttons.$goPrev.attr('disabled', true);
				$buttons.$goNext.attr('disabled', true);
				$buttons.$printScreen.attr('disabled', true);
				$buttons.$goFast.removeAttr('disabled');
				$buttons.$play.addClass('hidden');
				$buttons.$pause.removeClass('hidden');
				$buttons.$goSlow.removeAttr('disabled');
				$buttons.$speedModel.text('正常');
				$buttons.$speed.text(1);

				$player.data(STATUS_BINDNAME, STATUS_PLAY);

				if(typeof fn === 'function'){
					fn();
				}
			}

			if(typeof handler_resume === 'function'){
				handler_resume.apply($container[0], [$player.find('embed').first(), _srcList, _complete, _status, e]);
			}else{
				_complete();
			}
		},

		//前一帧
		goPrev:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_prev = $player.data(EVENT_PREV);

			var _complete = function(){
				var _playTime = privates.getPlayTime.apply($container[0], []),
					_percent = privates.startTimeTo.apply($container[0], [_playTime, true]);

				//触发停止
				if(_percent === 0){
					publics.stop.apply($container[0], []);
					return;
				}

				//设置进度条
				privates.scrollTo.apply($container[0], [_percent]);
			}

			if(typeof handler_prev === 'function'){
				handler_prev.apply($container[0], [$player.find('embed').first(), _srcList, _complete, e]);
			}

		},

		//后一帧
		goNext:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_next = $player.data(EVENT_NEXT);

			var _complete = function(){
				var _playTime = privates.getPlayTime.apply($container[0], []),
					_percent = privates.startTimeTo.apply($container[0], [_playTime, true]);

				//触发停止
				if(_percent === 1){
					publics.stop.apply($container[0], []);
					return;
				}

				//设置进度条
				privates.scrollTo.apply($container[0], [_percent]);
			}

			if(typeof handler_next === 'function'){
				handler_next.apply($container[0], [$player.find('embed').first(), _srcList, _complete, e]);
			}
		},

		//快进，参数：倍数 max:3
		goFast:function(speed, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				 $buttons = privates.getButtons.apply($container[0], []);

			//先暂停再seek,要不然怪怪的
			publics.pause.apply($container[0], []);

			if(speed !== 1){

				//清除timer
				privates.clearTimer.apply($container[0], []);

				//创建变速进度条
				privates.setCustomTimer.apply($container[0], [speed]);

				//按钮切换
				$buttons.$pause.removeClass('hidden');
				$buttons.$play.addClass('hidden');
				$buttons.$goPrev.attr('disabled', true);
				$buttons.$goNext.attr('disabled', true);
				$buttons.$printScreen.attr('disabled', true);
				$buttons.$speedModel.text('快进');
				$buttons.$speed.text(speed);

				$player.data(STATUS_BINDNAME, STATUS_FAST);
			}else{
				//恢复播放
				publics.resume.apply($container[0], []);
			}
		},

		//快退，参数：倍数 max:3
		goSlow:function(speed, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$buttons;

			//先暂停再seek,要不然怪怪的
			publics.pause.apply($container[0], []);

			//清除变速进度条
			privates.clearCusomTimer.apply($container[0], []);

			if(speed !== 1){

				//清除1倍速进度条
				privates.clearTimer.apply($container[0], []);

				//创建变速进度条
				privates.setCustomTimer.apply($container[0], [speed]);

				//按钮切换
				$buttons = privates.getButtons.apply($container[0], []);
				$buttons.$pause.removeClass('hidden');
				$buttons.$play.addClass('hidden');
				$buttons.$goPrev.attr('disabled', true);
				$buttons.$goNext.attr('disabled', true);
				$buttons.$printScreen.attr('disabled', true);
				$buttons.$speedModel.text('快退');
				$buttons.$speed.text(-speed);

				$player.data(STATUS_BINDNAME, STATUS_SLOW);
			}else{
				//恢复播放
				publics.resume.apply($container[0], []);
			}
		},

		//设置声音
		volumeSet:function(volume, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$embed = $player.find('embed').first(),
				event_volumeSet = $player.data(EVENT_VOLUMESET);

			if(typeof event_volumeSet === 'function'){
				return event_volumeSet.apply($container[0], [$embed, volume, e]);
			}
			return 1;
		},

		//截屏
		printScreen:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_printScreen = $player.data(EVENT_PRINTSCREEN);

			if(typeof handler_printScreen === 'function'){
				handler_printScreen.apply($container[0], [$player.find('embed').first(), _srcList, e]);
			}
		},

		//设置文件夹
		setFolder:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_setFolder = $player.data(EVENT_SETFOLDER);

			if(typeof handler_setFolder === 'function'){
				handler_setFolder.apply($container[0], [$player.find('embed').first(), _srcList, e]);
			}
		},

		//打开文件夹
		openFolder:function(fn, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_srcList = publics.get.apply($container[0], []),
				handler_openFolder = $player.data(EVENT_OPENFOLDER);

			if(typeof handler_openFolder === 'function'){
				handler_openFolder.apply($container[0], [$player.find('embed').first(), _srcList, e]);
			}
		},

		//进度条定位, 精度：毫秒
		seek:function(time_ms, e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$embed = $player.find('embed').first(),
				event_seek = $player.data(EVENT_SEEK);

			if(!time_ms || isNaN(parseInt(time_ms))) return;
			if(typeof event_seek !== 'function') return;

			time_ms = parseInt(time_ms);
			if(typeof event_seek === 'function'){
				event_seek.apply($container[0], [$embed, time_ms, e]);
			}
			return $container;
		},

		//加入拖拽状态
		addDragStaturs:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_status = $player.data(STATUS_BINDNAME);

			if(STATUS_STOP !== _status){
				$player.addClass('duringDrag');
			}
		},

		//移除拖拽状态
		removeDragStatus:function(){
			var $container = $(this),
				$player = $container.find('.player').first();
			$player.removeClass('duringDrag');
		},
		
		//重绘
		resize:function(){
			privates.resize.apply(this);
		},

		//判断状态
		is:function(status){
			var $container = this;
			var $player = $container.find('.player').first();
			return $player.data(STATUS_BINDNAME) === status;
		},

		//禁用按钮
		disable:function(buttons){
			if(!buttons) return;
			if($.isArray(buttons) && buttons.length<=0) return;
			if(!$.isArray(buttons)) buttons = [buttons];
			var $container = $(this);
			var $buttons = privates.getButtons.apply($container[0]);
			for(var i=0; i<buttons.length; i++){
				$buttons['$'+buttons[i]].attr('disabled', true);
			}
		},

		//启用按钮
		enable:function(buttons){
			if(!buttons) return;
			if($.isArray(buttons) && buttons.length<=0) return;
			if(!$.isArray(buttons)) buttons = [buttons];
			var $container = $(this);
			var $buttons = privates.getButtons.apply($container[0]);
			for(var i=0; i<buttons.length; i++){
				$buttons['$'+buttons[i]].removeAttr('disabled');
			}
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
			var fn_info = options.interface.info,
				_settings = options.settings,
				_split = _settings.split,
				_layout = _split.layout,
				_class = options.class;

			//点播不支持分屏
			if(_class.indexOf('noProgress')<0 && _layout.length!==1){
				throw new Error('点播不支持分屏模式');
			}
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
			var _theme = options.theme?' '+options.theme:'',
				_size = options.size?' '+options.size:'',
				_class = options.class?' '+ options.class:'',
				_flag = {
					'noPrev':_class.indexOf('noPrev')>=0?true:false,
					'noNext':_class.indexOf('noNext')>=0?true:false,
					'noFast':_class.indexOf('noFast')>=0?true:false,
					'noSlow':_class.indexOf('noSlow')>=0?true:false,
					'noPause':_class.indexOf('noPause')>=0?true:false,
					'noLayoutSet':_class.indexOf('noLayoutSet')>=0?true:false,
					'noPtzctrl':_class.indexOf('noPtzctrl')>=0?true:false,
					'noOsd':_class.indexOf('noOsd')>=0?true:false,
					'noRecord':_class.indexOf('noRecord')>=0?true:false,
					'noDecodeBind':_class.indexOf('noDecodeBind')>=0?true:false,
					'noForward':_class.indexOf('noForward')>=0?true:false,
					'noProgress':_class.indexOf('noProgress')>=0?true:false,
					'noPrintScreen':_class.indexOf('noPrintScreen')>=0?true:false,
					'noPlay':_class.indexOf('noPlay')>=0?true:false,
					'noStop':_class.indexOf('noStop')>=0?true:false,
					'noFullScreen':_class.indexOf('noFullScreen')>=0?true:false,
				};
			return '<div class="tetris player'+_theme+_size+_class+'">'+
					 	'<div class="head">' +
							'<button style="float:left;" title="字幕" class="osd'+(_flag.noOsd?' hidden':'')+'">' +
								'<span style="display:inline-block; width:20px; height:20px; border:2px solid #fff; border-radius:100%; color:#fff; font-size:12px;">字</span>' +
							'</button>'+
							'<span class="text">'+options.title+'</span><button class="remove reset float_r"></button>' +
						'</div>'+
						'<div class="body">'+
							'<div class="wrapper">'+
								'<iframe  frameborder="no" border="0" allowtransparency="true"></iframe>'+
								'<div class="splitList">'+
									'<div class="wrapper"></div>'+
								'</div>'+
							'</div>'+
						'</div>'+
						'<div class="foot">'+
							'<div class="progressBar gradient'+(_flag.noProgress?' hidden':'')+'">'+
								'<span class="gradient"><button></button></span>'+
							'</div>'+
							'<div class="time'+(_flag.noProgress?' hidden':'')+'"><span class="startTime float_l"></span><span class="endTime float_r"></span></div>'+
							'<div class="opration">'+
								'<button title="声音恢复" class="audio float_l"></button>'+
								'<button title="声音消除" class="mute float_l hidden"></button>'+
								'<button title="上一帧" class="goPrev'+(_flag.noPrev?' hidden':'')+'" disabled></button>'+
								'<button title="慢放" class="goSlow'+(_flag.noSlow?' hidden':'')+'" disabled></button>'+
								'<button title="播放" class="play'+(_flag.noPlay?' hidden':'')+'"></button>'+
								'<button title="暂停" class="pause hidden"></button>'+
								'<button title="停止" class="stop'+(_flag.noStop?' hidden':'')+'" disabled></button>'+
								'<button title="快放" class="goFast'+(_flag.noFast?' hidden':'')+'" disabled></button>'+
								'<button title="下一帧" class="goNext'+(_flag.noNext?' hidden':'')+'" disabled></button>'+
								'<button title="全屏" class="fullScreen float_r'+(_flag.noFullScreen?' hidden':'')+'"></button>'+
								'<button title="退出全屏" class="exitFullScreen float_r hidden">'+
									'<span style="display:inline-block; width:21px; height:21px; border:2px solid #fff; border-radius:100%; position:relative; top:1px; overflow:hidden;">' +
										'<span class="icon-resize-small" style="color:#fff; font-size:16px; position:relative; top:1px; font-weight:700;"></span>' +
									'</span>' +
								'</button>'+
								'<button title="云台" class="ptzctrl float_r'+(_flag.noPtzctrl?' hidden':'')+'"></button>'+
								'<button title="上屏" class="forward float_r'+(_flag.noForward?' hidden':'')+'">' +
									'<span style="display:inline-block; width:20px; height:20px; border:2px solid #fff; border-radius:100%;">' +
										'<span class="icon-desktop" style="color:#fff; font-size:12px; position:relative; top:1px; left:.5px;"></span>' +
									'</span>' +
								'</button>'+
								'<button title="录制" class="record float_r'+(_flag.noRecord?' hidden':'')+'">' +
									'<span style="display:inline-block; width:20px; height:20px; border:2px solid #fff; border-radius:100%; position:relative;">' +
										'<span class="icon-save" style="color:#fff; font-size:13px; position:relative;"></span>' +
									'</span>' +
								'</button>'+
								'<button title="绑定设备" class="decodeBind float_r'+(_flag.noDecodeBind?' hidden':'')+'">' +
									'<span style="display:inline-block; width:20px; height:20px; border:2px solid #fff; border-radius:100%; position:relative;">' +
										'<span class="icon-link" style="color:#fff; font-size:14px; position:relative; top:1px;"></span>' +
									'</span>' +
								'</button>'+
								'<button title="屏幕设置" class="layoutSet float_r'+(_flag.noLayoutSet?' hidden':'')+'"></button>'+
								'<button title="设置截屏路径" class="setFolder float_r'+(_flag.noPrintScreen?' hidden':'')+'"></button>'+
								'<button title="打开截屏路径" class="openFolder float_r'+(_flag.noPrintScreen?' hidden':'')+'"></button>'+
								'<button title="截屏" class="printScreen float_r'+(_flag.noPrintScreen?' hidden':'')+'" disabled></button>'+
								'<span class="speedWrap float_r' +(_flag.noFast?' hidden':'')+'"><span class="speedModel">停止:</span>&#215;<span class="speed">0</span></span>'+
							'</div>'+
						'</div>'+
					'</div>';
		},

		//生成一个分屏dom对象, this:$container
		generateSplitDom:function(index, size){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$splitList = $player.find('.splitList').first(),
				_totalWidth = parseFloat($splitList.css('width')),
				_totalHeight = parseFloat($splitList.css('height')),
				_width, _height, $split, _size = [];

			_width = size[0];
			_height = size[1];
			_width = (typeof _width==='string' && _width.indexOf('%')>=0) ? parseFloat(_width)/100 : parseFloat(_width);
			_height = (typeof _height==='string' && _height.indexOf('%')>=0) ? parseFloat(_height)/100 : parseFloat(_height);

			//缓存的是小数，以后直接使用不用再转换了
			_size.push(_width);
			_size.push(_height);

			_width = _totalWidth * _width;
			_height = _totalHeight * _height;

			//生成html模板
			var _htmlTemplate = [];
			_htmlTemplate.push('<div class="split float_l" data_index="'+index+'">');

			if(parseInt(index) === 1){
				_htmlTemplate.push('<div class="noSetting">未设置</div>');
			}else{
				_htmlTemplate.push('<div class="noSetting">未设置</div>');
			}

			_htmlTemplate.push('<div class="dragArea"></div></div>');

			$split = $(_htmlTemplate.join('')).css({'width':_width, 'height':_height});
			$split.data(SIZE_BINDNAME, _size);

			$splitList.find('.wrapper').first().append($split);
			return $split;
		},

		//重绘尺寸
		resize:function(e){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$splitList = $player.find('.splitList').first(),

				event_resize = $player.data(EVENT_RESIZE);

			//重绘事件
			if(typeof event_resize === 'function'){
				event_resize.apply($container[0], [$player.find('embed').first(), e])
			}
			var _totalWidth = parseInt($splitList.css('width')),
				_totalHeight = parseInt($splitList.css('height'));
			//重绘分屏尺寸
			$splitList.find('.split').each(function(){
				var $split = $(this),
					_size = $split.data(SIZE_BINDNAME),
					_width = _size[0] * _totalWidth,
					_height = _size[1] * _totalHeight;
				$split.css({'width':_width, 'height':_height});
			});

			return $container;
		},

		//缓存事件
		cacheEvent:function(event){
			var $container = $(this),
				$player = $container.find('.player').first(),
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
				$player = $container.find('.player').first(),
				i, _implament;
			for(i in INTERFACE){
				_implament = implament[INTERFACE[i]];
				if(typeof _implament === 'function'){
					$player.data(INTERFACE[i], _implament);
				}
			}
			return $container;
		},

		//格式化时间（单位：毫秒）
		/*getFormatTime:function(milisecond){
			milisecond = parseInt(milisecond);
			if(!milisecond) return '';
			var $player = $(this).find('.player').first(),
				_timeFormat = $player.data(TIMEFORMAT_BINDNAME),
				_formatTime,
				_date = new Date();
			_date.setTime(milisecond);
			_formatTime = _date.format(_timeFormat);
			return _formatTime;
		},*/

		getFormatTime:function(milisecond){
			milisecond = parseInt(milisecond);
			if(!milisecond) return '00:00:00';
			var $player = $(this).find('.player').first(),
				_timeFormat = $player.data(TIMEFORMAT_BINDNAME),
				_formatTime,
				_date = new Date();

			var hours = parseInt(milisecond/(60*60*1000));
			var minutes = parseInt((milisecond-(60*60*1000*hours))/(60*1000));
			var seconds  = parseInt((milisecond-(60*60*1000*hours)-(60*1000)*minutes)/1000);

			hours = hours<10?'0'+hours:hours;
			minutes = minutes<10?'0'+minutes:minutes;
			seconds = seconds<10?'0'+seconds:seconds;

			return hours + ':' + minutes + ':' + seconds;
		},

		//获取开始时间（单位：毫秒）
		getStartTime:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				fn_getStartTime = $player.data(INTERFACE_GETSTARTTIME);
			return fn_getStartTime.apply($container[0], [$player.find('embed').first()]);
		},

		//获取视频总时长（单位：毫秒）
		getDuration:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				fn_getDuration = $player.data(INTERFACE_GETDURATION);
			return fn_getDuration.apply($container[0], [$player.find('embed').first()]);
		},

		//获取当前播放的时间（单位：毫秒）
		getPlayTime:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				fn_getPlayTime = $player.data(INTERFACE_GETPLAYTIME);
			return fn_getPlayTime.apply($container[0], [$player.find('embed').first()]);
		},

		//获取播放器上所有的按钮
		getButtons:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$buttons = {
					'$printScreen':$player.find('.printScreen').first(),
					'$setFolder':$player.find('.setFolder').first(),
					'$openFolder':$player.find('.openFolder').first(),
					'$play':$player.find('.play').first(),
					'$stop':$player.find('.stop').first(),
					'$remove':$player.find('.remove'),
					'$audio':$player.find('.audio').first(),
					'$mute':$player.find('.mute').first(),
					'$pause':$player.find('.pause').first(),
					'$goPrev':$player.find('.goPrev').first(),
					'$goNext':$player.find('.goNext').first(),
					'$goFast':$player.find('.goFast').first(),
					'$goSlow':$player.find('.goSlow').first(),
					'$speedModel':$player.find('.speedModel').first(),
					'$speed':$player.find('.speed').first(),
					'$fullScreen':$player.find('.fullScreen').first(),
					'$exitFullScreen':$player.find('.exitFullScreen').first(),
					'$ptzctrl':$player.find('.ptzctrl').first(),
					'$forward':$player.find('.forward').first(),
					'$record':$player.find('.record').first()
				}
			return $buttons;
		},

		//启动1倍速进度条timer
		setTimer:function(interval){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_timer = $player.data(TIMER_BINDNAME),
				_interval = 1000 * interval;

			privates.setSpeed.apply($container[0], [1]);

			if(_timer) clearInterval(_timer);

			_timer = setInterval(function(){
				var _playTime = privates.getPlayTime.apply($container[0], []),
					_percent = privates.startTimeTo.apply($container[0], [_playTime, true]);

				//触发停止
				if(_percent === 1){
					publics.stop.apply($container[0], []);
					return;
				}

				//设置进度条
				privates.scrollTo.apply($container[0], [_percent]);
			}, _interval);

			//缓存timer
			$player.data(TIMER_BINDNAME, _timer);
		},

		//清除1倍速进度条timer
		clearTimer:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_timer = $player.data(TIMER_BINDNAME);
			if(_timer && typeof _timer==='number'){
				clearInterval(_timer);
				$player.data(TIMER_BINDNAME, null);
			}
		},

		//启动变速进度条，参数：倍速
		setCustomTimer:function(speed){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_timer_seek = $player.data(TIMER_SEEK_BINDNAME),
				_interval = 1000;

			privates.setSpeed.apply($container[0], [speed]);

			if(_timer_seek) clearInterval(_timer_seek);

			//产生快进seek inverval
			_timer_seek = setInterval(function(){

				//拿取新的缓存数据
				var _srcData = publics.get.apply($container[0], [1])[0];

				//每秒向前seek speed*1000毫秒
				var _percent = privates.startTimeTo.apply($container[0], [_srcData.timeoffset + speed*1000, true]);

				//设置进度条
				privates.scrollTo.apply($container[0], [_percent]);

				if(_percent === 0){
					publics.resume.apply($container[0], []);
					return;
				}else if(_percent === 1){
					publics.stop.apply($container[0], []);
					return;
				}

				//拿取新的缓存数据
				_srcData = publics.get.apply($container[0], [1])[0];
				publics.seek.apply($container[0], [_srcData.timeoffset || 0, null]);
			}, _interval);

			//缓存timer
			$player.data(TIMER_SEEK_BINDNAME, _timer_seek);
		},

		//清除变速进度条
		clearCusomTimer:function(){
			var $container = $(this),
				$player = $container.find('.player').first(),
				_timer_seek = $player.data(TIMER_SEEK_BINDNAME);
			if(_timer_seek && typeof _timer_seek==='number'){
				clearInterval(_timer_seek);
				$player.data(TIMER_SEEK_BINDNAME, null);
			}
		},

		/**
		 *进度条定位:
		 *    percent:0~1之间的小数代表百分比inc参数失效,
		 * 	  percent大于1代表像素，inc为true代表在原有基础上增加,
		 * 	  percent:0, inc:true时代表获取当前进度百分比
		 * 	  返回：当前进度百分比,
		 */
		scrollTo:function(percent, inc){
			var $container = $(this),
				$player = $container.find('.player').first(),
				$progressBar = $player.find('.progressBar').first(),
				_totalWidth = parseInt($progressBar.css('width')),
				$button = $progressBar.find('button').first(),
				_baseWidth = parseInt($button.css('width')),
				$complete = $progressBar.find('span').first(),
				_completeWidth;

			if(percent>=0 && percent<=1 && arguments.length===1){
				//百分比计算
				_completeWidth = _baseWidth + (_totalWidth - _baseWidth) * percent;
			}else {
				if(inc === true){
					//在原有基础上增加
					_completeWidth = parseInt($complete.css('width')) + percent;
				}else if(inc === false){
					//直接定位
					_completeWidth = percent;
				}
			}
			if(_completeWidth < _baseWidth) _completeWidth = _baseWidth;
			if(_completeWidth > _totalWidth) _completeWidth = _totalWidth;

			$complete.css('width', _completeWidth);

			return (_completeWidth<=_baseWidth)?0:(_completeWidth-_baseWidth)/(_totalWidth-_baseWidth);
		},

		/**设置当前进度条播放时间
		 *    percent:0~1之间的小数代表百分比inc参数失效,
		 * 	  percent大于1代表时间点，inc为true代表在开始时间上增加,
		 * 	  返回：当前进度百分比,
		 */
		startTimeTo:function(percent, inc){
			var $container = $(this),
				$startTime = $container.find('.startTime').first(),
				_srcData = publics.get.apply($container[0], [1]),
				_totalTime, _startTime, _baseoffset, _currTime, _percent;

			if(!_srcData || _srcData.length<=0) return;

			_srcData = _srcData[0];

			_totalTime = _srcData.totalTime;
			_startTime = _srcData.startTime;
			_baseoffset = _srcData.baseoffset;

			//时间偏移量，计入基本偏移量(baseoffset不加入计算)
			if(percent>=0 && percent<=1 && arguments.length===1){
				//按百分比计算
				_currTime = parseInt(_baseoffset + (_totalTime-_baseoffset) * percent);
				_srcData.timeoffset = _currTime;
				_currTime = _currTime + _startTime;
			}else{
				if(inc === true){
					//在开始时间上增加
					_currTime = _startTime + percent;
					_srcData.timeoffset = percent;
				}else if(inc === false){
					//直接定位
					_currTime = percent;
					_srcData.timeoffset = percent - _startTime;
				}
			}

			if(_srcData.timeoffset < _baseoffset) _srcData.timeoffset = _baseoffset;
			if(_srcData.timeoffset > _totalTime) _srcData.timeoffset = _totalTime;

			//缓存数据
			publics.set.apply($container[0], [_srcData]);

			$startTime.text(privates.getFormatTime.apply($container[0], [_currTime]));

			return _srcData.timeoffset/_totalTime;
		},

		is:function(status){
			var $container = $(this),
				$player = $container.find('.player').first();
			if(status === $player.data(STATUS_BINDNAME)){
				return true;
			}else{
				return false;
			}
		},

		setSpeed:function(speed){
			var $container = $(this);
			var src = publics.get.apply($container[0], [1]);
			if(!src){
				src = [{
					'serialNum':1,
					'speed':speed
				}];
			}else{
				var src0 = src[0];
				src0.speed = speed;
				src = [src0];
			}
			publics.set.apply($container[0], src);
		}
	}

	// 事件
	// =========================

	//重绘
	$(window).bind('resize.tetris.player', function(evnet){
		setTimeout(function(){
			$('.tetris.player').each(function(){
				var $container = $(this).parent();
				privates.resize.apply($container[0], [evnet]);
			});
		}, 100);
	});

	//播放按钮
	$(document).on('click.tetris.player.play', '.tetris.player .play', function(event){
		var $play = $(this),
			$player = $play.closest('.player').first(),
			$container = $player.parent();
		if(!privates.is.apply($container[0], [STATUS_STOP])){
			publics.resume.apply($container[0], [null, event]);
		}else{
			publics.play.apply($container[0], [false, event]);
		}
	});

	//暂停按钮
	$(document).on('click.tetris.playter.pause', '.tetris.player .pause', function(event){
		var $pause = $(this),
			$player = $pause.closest('.player').first(),
			$container = $player.parent();
		publics.pause.apply($container[0], [function(){
			var $buttons = privates.getButtons.apply($container[0], []);

			//按钮切换
			$buttons.$printScreen.removeAttr('disabled');
			$buttons.$goPrev.removeAttr('disabled');
			$buttons.$goNext.removeAttr('disabled');
			//$buttons.$goFast.attr('disabled', true);
			$buttons.$play.removeClass('hidden');
			$buttons.$pause.addClass('hidden');
			//$buttons.$goSlow.attr('disabled', true);
			$buttons.$speedModel.text('暂停');
			$buttons.$speed.text(0);

			$player.data(STATUS_BINDNAME, STATUS_PAUSE);

			privates.setSpeed.apply($container[0], [0]);

			//清除变速进度条
			privates.clearCusomTimer.apply($player.parent()[0], []);
		}, event]);
	});

	//停止事件
	$(document).on('click.tetris.player.stop', '.tetris.player .stop', function(event) {
		var $stop = $(this),
			$player = $stop.closest('.player').first();
		publics.stop.apply($player.parent()[0], [null, event]);
	});

	//删除事件
	$(document).on('click.tetris.player.remove', '.tetris.player .remove', function(event){
		var $remove = $(this), $split, _index,
			$player = $remove.closest('.player').first(),
			fn_info = $player.data(INTERFACE_INFO),
			_i18n = $player.data(I18N_BINDNAME),
			_status = $player.data(STATUS_BINDNAME),
			_class = $remove.attr('class');

		if(_class.indexOf('reset') >= 0) return;
		if(STATUS_STOP !== _status){
			fn_info(_i18n.info.remove.title, _i18n.info.remove.noStop);
			return;
		}

		$split = $remove.parent();
		_index = parseInt($split.attr('data_index'));
		publics.remove.apply($player.parent()[0], [_index]);
	});

	//快进事件--seek办
	/*$(document).on('click.tetris.player.goFast', '.tetris.player .goFast', function(event){
		var $goFast = $(this),
			$player = $goFast.closest('.player').first(),
			$container = $player.parent(),
			 _srcData = publics.get.apply($container[0], [1]);

		if(!_srcData || _srcData.length<=0) return;
		_srcData = _srcData[0];

		var _speed = (!_srcData.speed || _srcData.speed<0)?1:_srcData.speed;
		_speed +=1;
		_speed = _speed>6?1:_speed;

		publics.goFast.apply($container[0], [_speed, event]);
	});*/

	$(document).on('click.tetris.player.goFast', '.tetris.player .goFast', function(event){
		var $goFast = $(this),
			$player = $goFast.closest('.player').first(),
			_i18n = $player.data(I18N_BINDNAME),
			fn_info = $player.data(INTERFACE_INFO),
			$container = $player.parent(),
			_srcData = publics.get.apply($container[0], [1]),
			_status = $player.data(STATUS_BINDNAME),
			event_goFast = $player.data(EVENT_FAST),
			$embed = $player.find('embed').first(),
			$buttons = privates.getButtons.apply($container[0], []);

		if(!_srcData || _srcData.length<=0) return;
		_srcData = _srcData[0];

		var _speed = (!_srcData.speed || _srcData.speed<1)?1:_srcData.speed;
		if(_speed === 8) {
			fn_info(_i18n.info.goFast.title, _i18n.info.goFast.maxSpeed);
			return;
		};
		_speed *=2;

		var complete = function(){
			$buttons.$pause.addClass('hidden');
			$buttons.$play.removeClass('hidden');
			$buttons.$goPrev.attr('disabled', true);
			$buttons.$goNext.attr('disabled', true);
			$buttons.$printScreen.attr('disabled', true);
			$buttons.$speedModel.text('快放');
			$buttons.$speed.text(_speed);
			$player.data(STATUS_BINDNAME, STATUS_FAST);
			privates.setSpeed.apply($container[0], [_speed]);
		};

		if(typeof event_goFast === 'function'){
			event_goFast.apply($container[0], [$embed, _speed, complete, _status, event]);
		}else{
			complete();
		}
	});

	//快退事件--seek版本
	/*$(document).on('click.tetris.player.goSlow', '.tetris.player .goSlow', function(event){
		var $goSlow = $(this),
			$player = $goSlow.closest('.player').first(),
			$container = $player.parent(),
			_srcData = publics.get.apply($container[0], [1]);

		if(!_srcData || _srcData.length<=0) return;
		_srcData = _srcData[0];

		var _speed = (!_srcData.speed||_srcData.speed>-1)?-1:_srcData.speed;
		_speed -=1;
		_speed = _speed<-6?1:_speed;

		publics.goSlow.apply($container[0], [_speed, event]);
	});*/

	//慢放事件
	$(document).on('click.tetris.player.goSlow', '.tetris.player .goSlow', function(event){
		var $goSlow = $(this),
			$player = $goSlow.closest('.player').first(),
			_i18n = $player.data(I18N_BINDNAME),
			fn_info = $player.data(INTERFACE_INFO),
			$container = $player.parent(),
			_srcData = publics.get.apply($container[0], [1]),
			_status = $player.data(STATUS_BINDNAME),
			event_goSlow = $player.data(EVENT_SLOW),
			$embed = $player.find('embed').first(),
			$buttons = privates.getButtons.apply($container[0], []);

		if(!_srcData || _srcData.length<=0) return;
		_srcData = _srcData[0];

		var _speed = (!_srcData.speed || _srcData.speed<0 || _srcData.speed>1)?1:_srcData.speed;
		if(_speed === 0.125){
			fn_info(_i18n.info.goSlow.title, _i18n.info.goSlow.minSpeed);
			return;
		}
		_speed /=2;

		var complete = function(){
			//按钮切换
			$buttons = privates.getButtons.apply($container[0], []);
			$buttons.$pause.addClass('hidden');
			$buttons.$play.removeClass('hidden');
			$buttons.$goPrev.attr('disabled', true);
			$buttons.$goNext.attr('disabled', true);
			$buttons.$printScreen.attr('disabled', true);
			$buttons.$speedModel.text('慢放');
			$buttons.$speed.text(_speed);
			$player.data(STATUS_BINDNAME, STATUS_SLOW);
			privates.setSpeed.apply($container[0], [_speed]);
		};

		if(typeof event_goSlow === 'function'){
			event_goSlow.apply($container[0], [$embed, _speed, complete, _status, event]);
		}else{
			complete();
		}
	});

	//前一帧
	$(document).on('click.tetris.player.goPrev', '.tetris.player .goPrev', function(event){
		var $goPrev = $(this),
			$player = $goPrev.closest('.player').first(),
			$container = $player.parent();
		publics.goPrev.apply($container[0], [null, event]);
	});

	//后一帧
	$(document).on('click.tetris.player.goNext', '.tetris.player .goNext', function(event){
		var $goNext = $(this),
			$player = $goNext.closest('.player').first(),
			$container = $player.parent();
		publics.goNext.apply($container[0], [null, event]);
	});

	//重置事件
	$(document).on('click.tetris.player.reset', '.tetris.player .reset', function(event){
		var $reset = $(this),
			$player = $reset.closest('.player').first();
		publics.remove.apply($player.parent()[0], []);
	});

	//打开文件夹
	$(document).on('click.tetris.player.openFolder', '.tetris.player .openFolder', function(event){
		var $openFolder = $(this),
			$player = $openFolder.closest('.player').first(),
			$container = $player.parent();
		publics.openFolder.apply($container[0], [null, event]);
	});

	//设置文件夹
	$(document).on('click.tetris.player.setFolder', '.tetris.player .setFolder', function(event){
		var $setFolder = $(this),
			$player = $setFolder.closest('.player').first(),
			$container = $player.parent();
		publics.setFolder.apply($container[0], [null, event]);
	});

	//截屏事件
	$(document).on('click.tetris.player.printScreen', '.tetris.player .printScreen', function(event){
		var $printScreen = $(this),
			$player = $printScreen.closest('.player').first(),
			$container = $player.parent();
		publics.printScreen.apply($container[0], [null, event]);
	});

	//声音恢复
	$(document).on('click.tetris.player.audio', '.tetris.player .mute', function(event){
		var $audio = $(this),
			$player = $audio.closest('.player'),
			_i18n = $player.data(I18N_BINDNAME),
			fn_info = $player.data(INTERFACE_INFO),
			_status = $player.data(STATUS_BINDNAME),
			$container = $player.parent(),
			$buttons = privates.getButtons.apply($container[0], []),
			_result = publics.volumeSet.apply($container[0], [true]);

		if(_status === STATUS_STOP){
			fn_info(_i18n.info.audio.title, _i18n.info.audio.noStart);
			return;
		}
		if(_result === 1){
			$buttons.$mute.addClass('hidden');
			$buttons.$audio.removeClass('hidden');
		}
	});

	//静音
	$(document).on('click.tetris.player.mute', '.tetris.player .audio', function(event){
		var $mute = $(this),
			$player = $mute.closest('.player'),
			_i18n = $player.data(I18N_BINDNAME),
			fn_info = $player.data(INTERFACE_INFO),
			_status = $player.data(STATUS_BINDNAME),
			$container = $player.parent(),
			$buttons = privates.getButtons.apply($container[0], []),
			_result = publics.volumeSet.apply($container[0], [false]);

		if(_status === STATUS_STOP){
			fn_info(_i18n.info.mute.title, _i18n.info.mute.noStart);
			return;
		}

		if(_result === 1){
			$buttons.$audio.addClass('hidden');
			$buttons.$mute.removeClass('hidden');
		}
	});

	//选中[|取消选中]
	$(document).on('click.tetris.player.split', '.tetris.player .split', function(event){
		var $split = $(this),
			_index = parseInt($split.attr('data_index')),
			_splitClass = $split.attr('class') || '',
			$player = $split.closest('.player').first(),
			_splitSelect = $player.data(SPLITSLECT_BINDNAME);

		if(!_splitSelect) return;

		if(_splitClass.indexOf('selected') >= 0){
			//取消选中
			publics.unSelect.apply($player.parent()[0], [_index, event]);
		}else{
			//选中
			publics.select.apply($player.parent()[0], [_index, event]);
		}
	});

	//分屏设置
	$(document).on('click.tetris.player.layoutSet', '.tetris.player .layoutSet', function(event){
		var $layoutSet = $(this),
			$player = $layoutSet.closest('.player').first(),
			fn_info = $player.data(INTERFACE_INFO),
			_i18n = $player.data(I18N_BINDNAME),
			$container = $player.parent(),
			$splitList = $player.find('.splitList').first(),
			_splitNum = parseInt($splitList.data(SPLITNUM_BINDNAME)),
			event_layoutSet = $player.data(EVENT_LAYOUTSET),
			_srcList;

		if(_splitNum === 1){
			_srcList = publics.get.apply($container[0], [1]);
			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.layoutSet.title, _i18n.info.layoutSet.noSetting);
				return;
			}
		}else{
			_srcList = publics.getSelected.apply($container[0], []);
			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.layoutSet.title, _i18n.info.layoutSet.noSelected);
				return;
			}
		}
		if(typeof event_layoutSet === 'function'){
			event_layoutSet.apply($layoutSet[0], [_srcList, event]);
		}
	});

	//云台
	$(document).on('click.tetris.player.ptzctrl', '.tetris.player .ptzctrl', function(event){
		var $layoutSet = $(this),
			$player = $layoutSet.closest('.player').first(),
			fn_info = $player.data(INTERFACE_INFO),
			_i18n = $player.data(I18N_BINDNAME),
			$container = $player.parent(),
			$splitList = $player.find('.splitList').first(),
			_splitNum = parseInt($splitList.data(SPLITNUM_BINDNAME)),
			event_ptzctrl = $player.data(EVENT_PTZCTRL),
			_srcList;

		if(_splitNum === 1){
			_srcList = publics.get.apply($container[0], [1]);
			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.ptzctrl.title, _i18n.info.ptzctrl.noSetting);
				return;
			}
		}else{
			_srcList = publics.getSelected.apply($container[0], []);
			if(!_srcList || _srcList.length<=0){
				fn_info(_i18n.info.ptzctrl.title, _i18n.info.ptzctrl.noSelected);
				return;
			}
		}

		var $embed = publics.getEmbed.apply($container[0]);

		if(typeof event_ptzctrl === 'function'){
			event_ptzctrl.apply($layoutSet[0], [_srcList, $container, $embed, $player.data(STATUS_BINDNAME), event]);
		}
	});

	//转发
	$(document).on('click.tetris.player.forward', '.tetris.player .forward', function(event){
		var $forward = $(this),
			$player = $forward.closest('.player').first(),
			fn_info = $player.data(INTERFACE_INFO),
			_i18n = $player.data(I18N_BINDNAME),
			$container = $player.parent(),
			event_forward = $player.data(EVENT_FORWARD),
			_srcList, _osd;

		_srcList = publics.get.apply($container[0], [1]);
		if(!_srcList || _srcList.length<=0){
			fn_info(_i18n.info.forward.title, _i18n.info.forward.noSetting);
			return;
		}

		_osd = publics.getOsd.apply($container[0]);

		if(typeof event_forward === 'function'){
			event_forward.apply($container[0], [_srcList, _osd, event]);
		}
	});

	//录制
	$(document).on('click.tetris.player.record', '.tetris.player .record', function(event){
		var $record = $(this),
			$player = $record.closest('.player').first(),
			fn_info = $player.data(INTERFACE_INFO),
			_i18n = $player.data(I18N_BINDNAME),
			$container = $player.parent(),
			event_record = $player.data(EVENT_RECORD),
			_srcList;

		_srcList = publics.get.apply($container[0], [1]);
		if(!_srcList || _srcList.length<=0){
			fn_info(_i18n.info.record.title, _i18n.info.record.noSetting);
			return;
		}

		if(typeof event_record === 'function'){
			event_record.apply($container[0], [_srcList, event]);
		}
	});

	//绑定设备
	$(document).on('click.tetris.player.decodeBind', '.tetris.player .decodeBind', function(event){
		var $decodeBind = $(this),
			$player = $decodeBind.closest('.player').first(),
			$container = $player.parent(),
			event_decodeBind = $player.data(EVENT_DECODE_BIND);

		if(typeof event_decodeBind === 'function'){
			event_decodeBind.apply($container[0], [event]);
		}
	});

	//osd
	$(document).on('click.tetris.player.osd', '.tetris.player .osd', function(event){
		var $osd = $(this),
			$player = $osd.closest('.player').first(),
			$container = $player.parent(),
			event_osd = $player.data(EVENT_OSD),
			_current_osd;

		_current_osd = $player.data(OSD_BINDNAME);

		if(typeof event_osd === 'function'){
			event_osd.apply($container[0], [_current_osd, event]);
		}
	});

	//全屏事件
	$(document).on('click.tetris.player.fullScreen', '.tetris.player .fullScreen', function(event){
		var $fullScreen = $(this),
			$player = $fullScreen.closest('.player').first();
		publics.fullScreen.apply($player.parent()[0], [event]);
	});

	//退出全屏事件
	$(document).on('click.tetris.player.exitFullScreen', '.tetris.player .exitFullScreen', function(event){
		var $exitFullScreen = $(this),
			$player = $exitFullScreen.closest('.player').first();
		publics.exitFullScreen.apply($player.parent()[0], [event]);
	});

	//监听esc事件
	$(document).on('keydown.tetris.player.esc.exitFullScreen', function(event){
		if(event.which == 27){
			$('.tetris.player.max-size').each(function(){
				var $player = $(this);
				publics.exitFullScreen.apply($player.parent()[0], []);
			});
		}
	});

	//进度条点击    TODO
	$(document).on('click.tetris.player.progress', '.tetris.player .progressBar', function(event){

	});

	//进度条拖拽
	$(document).on('mousedown.tetris.player.progress', '.tetris.player .progressBar>span>button', function(event){
		var $button = $(this),
			$player = $button.closest('.player').first(),
			$container = $player.parent(),
			_outerX = event.screenX;

		//清除timer
		privates.clearTimer.apply($container[0], []);

		//清除变速进度条
		privates.clearCusomTimer.apply($player.parent()[0], []);

		var _mouseMoveCallback = function(e){
			var _innerX = e.screenX,
				_changeX = _innerX - _outerX,
				_percent;

			//移动滚动条
			_percent = privates.scrollTo.apply($container[0], [_changeX, true]);

			//触发停止
			if(_percent === 1){
				publics.stop.apply($container[0], [null, e]);
				$(document).unbind('mousemove.progress', _mouseMoveCallback);
				$(document).unbind('mouseup.progress', _mouseupCallback);
				return;
			}

			_outerX = _innerX;

			//设置开始时间
			privates.startTimeTo.apply($container[0], [_percent]);

		}

		var _mouseupCallback = function(e){
			$(document).unbind('mousemove.progress', _mouseMoveCallback);
			$(document).unbind('mouseup.progress', _mouseupCallback);

			//拿取新的缓存数据
			var _srcData = publics.get.apply($container[0], [1]);
			if(!_srcData || _srcData.length<=0) return;

			_srcData = _srcData[0];

			publics.seek.apply($container[0], [_srcData.timeoffset || 0, e]);

			//恢复播放
			publics.resume.apply($container[0], []);
		}

		$(document).bind('mousemove.progress', _mouseMoveCallback);
		$(document).bind('mouseup.progress', _mouseupCallback);
	});

	//jQuery不支持拖拽event对象，所有拖拽事件用原生api
	document.addEventListener('dragover', function(event){
		//这个一定要有，否则drop不会触发
		var $target = $(event.target),
			_class = $target.attr('class') || '';
		event.preventDefault();
	});

	document.addEventListener('dragenter', function(event){
		var $target = $(event.target),
			_class = $target.attr('class') || '';

		if(_class && _class.indexOf('dragArea') >= 0){
			$target.parent().addClass('highLight');
		}
	});

	document.addEventListener('dragleave', function(event){
		var $target = $(event.target),
			_class = $target.attr('class') || '';

		if(_class && _class.indexOf('dragArea') >= 0){
			$target.parent().removeClass('highLight');
		}
	});

	document.addEventListener('drop', function(event){
		var $target = $(event.target),
			_class = $target.attr('class');

		//移除拖拽样式
		$('.tetris.player').each(function(){
			var $container = $(this).parent();
			publics.removeDragStatus.apply($container[0], []);
		});

		if(_class && _class.indexOf('dragArea') >= 0){
			var $split = $target.parent(),
				_index = $split.attr('data_index'),
				_srcData = $split.data(SRCDATA_BINDNAME),
				$player = $split.closest('.player').first(),
				event_drop = $player.data(EVENT_DROP);

			if(typeof event_drop === 'function'){
				event_drop.apply($player.parent()[0], [$player.find('embed').first(), _index, _srcData, event]);
			}

			$split.removeClass('highLight');

			//移除拖拽样式
			publics.removeDragStatus.apply($player.parent()[0], []);
		}
	});
}(jQuery);