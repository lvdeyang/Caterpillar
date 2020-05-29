/* ========================================================================
 * zk_SipPlayer.js: version 1.0.0
 * 视频播放器插件
 * <embed class="" style="" type="application/media-suma-sip" id=""/>
 * ========================================================================
 * created by lvdeyang
 * 2016年7月11日
 * ======================================================================== */
+function($){
    // 常量
    // =========================
    var PLUGINNAME = 'zk_SipPlayer',
        OPTIONSCACHENAME = 'options',
        DISPLAYTYPE_ONE = 'one',                     //只显示远端主流
        DISPLAYTYPE_THREE = 'three',                 //三分屏布局
        DISPLAYTYPE_PIP = 'pip',                     //画中画布局
        DISPLAYTYPE_DEFAULT = DISPLAYTYPE_PIP,      //默认的布局类型
        STREAMNAME_REMOTEMAIN = 'remoteMain',       //远端主流
        STREAMNAME_REMOTESLIDES = 'remoteSlides',   //远端辅流
        STREAMNAME_SELFMAIN = 'selfMain',           //本地主流
        STREAMNAME_SELFSLIDES = 'selfSlides',       //本地辅流
        PIPSIZE = 0.25,                               //画中画小屏尺寸
        THREESIZE_SELFWIDTH = 0.5,                    //三分屏本地主流宽度
        THREESIZE_SELFHEIGHT = 0.5,                   //三分屏本地主流高度
        INTERVAL_ONCALL = 3000,                       //被叫监听响应灵敏度（单位：毫秒）
        SIPSTATUS_IDEL = 'idle',                     //空闲的
        SIPSTATUS_CALLING = 'calling',              //通话中
        SIPSTATUS_CALLED = 'called';                //被叫

    var BEGIN_PORT = 5060,                           //端口校验起点
        INVOKE_TIME = 0;                             //接口调用次数

    // 默认配置
    // =========================

    var defaultOptions = {
    	'id':'',	
        'playerCode':'',
        'username':'',
        'password':'',
        'clientIp':'',
        'clientPort':'5060',
        'localSipIp':'',
        'localSipPort':'',
        'localSipTcpPort':'',
        'registerServerCode':'',
        'registerServerSipIp':'',
        'registerServerSipPort':'',
        'type':'',
        'selectDevice':'',
        'videoParamIdx':'',					//视频清晰度参数
        'videoBitrate':'',					//视频码率
        'audioParamIdx':'', 				//音频清晰度参数
        'audioBitrate':'',  				//音频码率
        'display':{
            'type':'',                      //布局的类型，三分屏或画中画
            'content':null                  //具体布局信息
        },
        'callListener':{                    //被叫监听
            'interval':null,
            'onCall':null,
            'onHangup':null
        }
    }

    // 共有方法
    // =========================

    var publics = {
        //初始化+注册
        init:function(options){
            var $embed = $(this),
                $parent = $embed.parent(),
                _defaultOptions = $.extend(true, {}, defaultOptions),
                _options = $.extend(true, _defaultOptions, options),
                _formatParams = privates.formatParams(_options),
                _type = _options.type,
                _virOrReal = _options.virOrReal,
                _withEncoder = false,
                _selectDevice = _options.selectDevice,
                _videoParamIdx = _options.videoParamIdx,
                _videoBitrate = _options.videoBitrate,
                _audioParamIdx = _options.audioParamIdx,
                _audioBitrate = options.audioBitrate,
                _equipList, _vedioList, _audioList;

            if(!$parent[0]) throw new Error('embed标签需要做为子标签存在');

            //设置编码器以及初始化布局类型
            if(_type==='double-encoder-decoder' &&
                (_virOrReal==='web' || _virOrReal==='Mobile')){
                //终端设备  初始化三分屏
                _withEncoder = true;
                _options.display.type = DISPLAYTYPE_PIP;
            }else if(_type === 'encoder-decoder'){
                //编解码器  初始化为画中画
                _withEncoder = true;
                _options.display.type = DISPLAYTYPE_PIP;
            }else{
                //播放器初始化为单屏
                _options.display.type = DISPLAYTYPE_ONE;
            }

            //数据缓存
            $parent.data(OPTIONSCACHENAME, _options);
            //播放器初始化
            console.log('('+_formatParams.playerCode+','+ _formatParams.userName+','+ _formatParams.password+','+ _formatParams.registerAddr+','+ _formatParams.localAddr+','+_withEncoder+','+_videoParamIdx+','+_videoBitrate+','+_audioParamIdx+','+_audioBitrate+', 1)');
            try{
                $embed[0].I_Init(_formatParams.playerCode, _formatParams.userName, _formatParams.password, _formatParams.registerAddr, _formatParams.localAddr, _withEncoder, parseInt(_videoParamIdx), parseInt(_videoBitrate), parseInt(_audioParamIdx), parseInt(_audioBitrate), 1);
                //设置丢包缓存帧
                $embed[0].I_SetExternParam(true, 3);
            }catch(e){console.log(e);}

            //获取设备列表
            if(_withEncoder){
                try {
                    _equipList = $embed[0].I_EnumCapDevice();
                } catch(e){
                    console.log(e);
                    _equipList = $.toJSON({
                        'video':[],
                        'audio':[]
                    });
                }
                if(_equipList){
                	_equipList = $.parseJSON(_equipList);
                	_vedioList = _equipList.video;
                	_audioList = _equipList.audio;
                }
                if(typeof _selectDevice === 'function'){
                	_selectDevice(_vedioList, _audioList, function(videoName, audioName){
                		console.log('视频:'+videoName+'; 音频:'+audioName);
                        try {
                            $embed[0].I_SetCapDevice(videoName, audioName);
                        }catch(e){console.log(e);}
                	});
                }
            }

            //启动轮询
            if(_options.display.type && _options.display.type !== DISPLAYTYPE_ONE){
                privates.startCallListener.apply($embed[0], []);
            }
        },
        //释放资源
        clear:function(){
            var $embed = $(this),
                _result;
            console.log('反初始化');
            try {
                _result = $embed[0].I_Uninit();
            }catch(e){console.log(e);}
            return _result;
        },
        //手动注册
        register:function(){
            var $embed = $(this), _options,
                $parent = $embed.parent(),
                _result;

            //检验是否有数据缓存
            _options = $parent.data(OPTIONSCACHENAME);
            if(!_options){
                throw new Error('当前播放器还没有初始化，请先初始化');
            }
            console.log('注册');
            try {
                _result = $embed[0].I_Register();
            }catch(e){
                console.log(e);
            }
            return _result;
        },
        //手动注销
        unRegister:function(){
        	var $embed = $(this),
                _result;
            console.log('注销');
            try{
                _result = $embed[0].I_UnRegister();
            }catch(e){console.log(e);}
            return _result;
        },
        //获取播放器配置参数
        get:function(optionsName){
            if(!optionsName || typeof optionsName!=='string') return null;

            var $embed = $(this),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME);

            return _options[optionsName];
        },
        //获取视频流的宽高比
        getStreamProportion:function(){
            return '16:9';
        },

        //设置布局
        setDisplay:function(options){
            var $embed = $(this),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME),
                _displayType,
                _displayContent;
            if(typeof options === 'object'){
                if(options.displayType){
                    _displayType = options.displayType;
                }else if(options.display && typeof options.display==='object'){
                    _displayType = options.display.type || DISPLAYTYPE_DEFAULT;
                }else{
                    _displayType = DISPLAYTYPE_DEFAULT;
                }
            }else{
                _displayType = options || DISPLAYTYPE_DEFAULT;
            }

            //重新获取配置
            _displayContent = privates.getDisplayConfig.apply($embed[0], [_displayType]);

            //调用播放器
            return privates.setDisplay.apply($embed[0], [_displayContent, _displayType]);
        },

        //刷新布局，播放器尺寸重置时调用此方法做自适应
        refreshDisplay:function(){
            var $embed = $(this),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME),
                _displayType,
                _displayContent;

            if(!_options || !_options.display || typeof _options.display !== 'object') return;

            _displayType = _options.display.type;

            //重新获取配置
            _displayContent = privates.getDisplayConfig.apply($embed[0], [_displayType]);

            //调用播放器
            return privates.setDisplay.apply($embed[0], [_displayContent]);
        },

        //画面切换
        switchDisplay:function(stream1, stream2){
            var $embed = $(this),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME),
                _displayContent,
                _tmp,
                _display_s1, _display_s2;

            //当前插件还没有做布局设置
            if(!_options.display || typeof _options.display !== 'object') return;
            if(!_options.display.content || typeof _options.display.content !== 'object') return;

            _display_s1 = _options.display.content[stream1];
            if(!_display_s1 || (_display_s1.display&&_display_s1.display==='false'))
                throw new Error('不存在(不展示)的流：['+stream1+']不能进行画面切换');
            _display_s2 = _options.display.content[stream2];
            if(!_display_s2 || (_display_s2.display&&_display_s2.display==='false'))
                throw new Error('不存在(不展示)的流：['+stream2+']不能进行画面切换');

            //交换布局配置
            _tmp = _display_s1;
            _options.display.content[stream1] = _options.display.content[stream2];
            _options.display.content[stream2] = tmp;

            //调用播放器
            return privates.setDisplay.apply($embed[0], [_options.display.content]);
        },

        //静音
        silent:function(){
        	var $embed = $(this),
                _result;
            console.log('静音');
            try {
                _result = $embed[0].I_EnableAudio(0);
            }catch(e){console.log(e);}
            return _result;
        },
        //恢复声音
        voiced:function(){
            var $embed = $(this),
                _result;
            console.log('恢复音量');
            try{
                _result = $embed[0].I_EnableAudio(1);
            }catch(e){console.log(e);}
            return _result;
        },
        //设置音量
        volume:function(){
        	//TODO
        },
        //销毁--这个是老接口
        destroy:function(){
            var $embed = $(this),
            	_options = $embed.data(OPTIONSCACHENAME);
        	//endCall--这个接口小鲤鱼说不用调用
            //publics.endCall.apply($embed[0], []);
            //注销
            publics.unRegister.apply($embed[0], []);
            //反初始化
            publics.clear.apply($embed[0], []);
            //停止被叫监听--以下都不会被调用到
            if(_options && _options.callListener && _options.callListener.interval){
            	clearInterval(_options.callListener.interval);
            }
            if(_options && _options.callListener && typeof _options.callListener.hungup==='function'){
            	_options.callListener.hungup($embed);
            }
            $embed.data(OPTIONSCACHENAME, null);
        },
        //发起呼叫
        call:function(target){
            var $embed = $(this),
                targetNum = target.targetNum,
                targetAddr = target.targetAddr,
                targetPort = target.targetPort,
                _result = 0;
            console.log('calling '+targetNum+'@'+targetAddr+':'+targetPort);
            try{
                _result = $embed[0].I_BeginCall(targetNum+'@'+targetAddr+':'+targetPort);
            }catch(e){
                console.log(e);
            }
            return _result;
        },
        //挂断呼叫
        endCall:function(){
            var $embed = $(this),
                _result = 0;
            console.log('结束通话');
            try{
                _result = $embed[0].I_EndCall();
            }catch(e){console.log(e);}
            return _result;
        },
        
        //端口检验
        checkPort:function(port){
        	var $embed = $(this),
            _result = 0;
	        try{
	            _result = $embed[0].I_JudgePortUsed(port);
	            console.log('端口：'+port+'校验成功');
	            return _result;
	        }catch(e){console.log(e);}
        	return 2;
        },

        //获取可用端口
        queryUseablePort:function(ip){
            var $embed = $(this);
            try{
                /*var currentPort = BEGIN_PORT + INVOKE_TIME;
                while(true){
                    var _result = $embed[0].I_JudgePortUsed(currentPort);
                    INVOKE_TIME += 1;
                    if(_result === 1){
                        console.log('查端口调用'+INVOKE_TIME);
                        return currentPort;
                    }else{
                        currentPort += 1;
                    }
                }*/
                var _result = $embed[0].I_GetPortUnUsed(ip);
                return _result;
            }catch(e){
                console.log(e);
                return false;
            }
        }

    };

    // 插件入口
    // =========================

    //扩展到plugin
    //$.fn[window.PLUGINNAME]('extend', PLUGINNAME, publics);

    //扩展到jQuery
    $.fn[PLUGINNAME] = function(method,args){
        if(publics[method]===null || typeof publics[method]==='undefined' || publics[method]===''){
            throw new Error(method+'不是一个方法');
        }
        return publics[method].call(this,args);
    };

    // 私有方法
    // =========================

    var privates = {
        //参数格式化
        formatParams:function(options){
        	return {
        		'playerCode':options.playerCode,
        		'userName':options.username,
                'password':options.password,
        		'registerAddr':options.registerServerSipIp+':'+options.registerServerSipPort,
        		'localAddr':options.localSipIp+':'+options.localSipPort,
        	};
        },
        //获取布局配置数据
        getDisplayConfig:function(type){
            var $embed = $(this),
            	$parent = $embed.parent(),
                _width = parseInt($parent.css('width')) || parseInt($embed.css('width')) || parseInt($('body').css('width')),
                _height = parseInt($parent.css('height')) || parseInt($embed.css('height')) || parseInt($('body').css('height')),
                _displayContent = {},
                _displayType = type;
            
            if(DISPLAYTYPE_PIP === _displayType){
            	$embed[0].height = _height * 0.5 + 'px';
            	$embed.css('border', '1px solid #fff');
            	$parent.css('padding-top', _height * 0.25);
            }else{
            	$embed[0].height = _height;
            	$embed.css('border', '0');
            	$parent.css('padding-top', 0);
            }
            
            if(DISPLAYTYPE_ONE === _displayType){
                //只显示远端主流
                //远端主流
                _displayContent[STREAMNAME_REMOTEMAIN] = {
                    'display':true,
                    'top':0,
                    'left':0,
                    'width':_width,
                    'height':_height
                };
                //本地主流
                _displayContent[STREAMNAME_SELFMAIN] = {
                    'display':false,
                };
                //远端辅流
                _displayContent[STREAMNAME_REMOTESLIDES] = {
                		'display':false,
                };
                //本地辅流
                _displayContent[STREAMNAME_SELFSLIDES] = {
                    'display':false
                };
            }else if(DISPLAYTYPE_PIP === _displayType){
            	/*//画中画
                //远端主流
                _displayContent[STREAMNAME_REMOTEMAIN] = {
                    'display':true,
                    'top':0,
                    'left':0,
                    'width':_width,
                    'height':_height
                };
                //本地主流
                _displayContent[STREAMNAME_SELFMAIN] = {
                    'display':true,
                    'top':_height*(1-PIPSIZE),
                    'left':_width*(1-PIPSIZE),
                    'width':_width*PIPSIZE,
                    'height':_height*PIPSIZE
                };*/
            	//二分屏
                //远端主流
                _displayContent[STREAMNAME_REMOTEMAIN] = {
                    'display':true,
                    'top':0,
                    'left':0,
                    'width':_width*0.5,
                    'height':_height
                };
                //本地主流
                _displayContent[STREAMNAME_SELFMAIN] = {
                    'display':true,
                    'top':0,
                    'left':_width*0.5+1,
                    'width':_width*0.5-1,
                    'height':_height
                };
                //远端辅流
                _displayContent[STREAMNAME_REMOTESLIDES] = {
                    'display':false
                };
                //本地辅流
                _displayContent[STREAMNAME_SELFSLIDES] = {
                    'display':false
                };
            }else if(DISPLAYTYPE_THREE === _displayType){
                //三分屏
                //远端主流
                _displayContent[STREAMNAME_REMOTEMAIN] = {
                    'display':true,
                    'top':0,
                    'left':0,
                    'width':_width*(1-THREESIZE_SELFWIDTH),
                    'height':_height
                };
                //本地主流
                _displayContent[STREAMNAME_SELFMAIN] = {
                    'display':true,
                    'top':0,
                    'left': _displayContent[STREAMNAME_REMOTEMAIN].width+1,
                    'width':_width*THREESIZE_SELFWIDTH-1,
                    'height':_height*THREESIZE_SELFHEIGHT
                };
                //远端辅流
                _displayContent[STREAMNAME_REMOTESLIDES] = {
                    'display':true,
                    'top':_displayContent[STREAMNAME_SELFMAIN].height+1,
                    'left':_displayContent[STREAMNAME_REMOTEMAIN].width+1,
                    'width':_displayContent[STREAMNAME_SELFMAIN].width-1,
                    'height':_height*(1-THREESIZE_SELFHEIGHT)-1
                };
                //本地辅流
                _displayContent[STREAMNAME_SELFSLIDES] = {
                    'display':false
                };
            }
            return _displayContent;
        },
        //设置布局
        setDisplay:function(content, cacheType){
            var _displayContent = content,
                $embed = $(this),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME);

            if(!_options) return;
            
            //调用播放器
            console.log('播放器布局：'+$.toJSON(_displayContent));
            setTimeout(function(){
            	var _result = 0;
            	try{
            		_result = $embed[0].I_SetDisplay($.toJSON(_displayContent));
                }catch(e){console.log(e);}
        		if(_result === 1){
                    console.log('布局设置成功');
                    //更新缓存
                    _options.display = _options.display || {};
                    if(cacheType){
                        _options.display.type = cacheType;
                    }
                    _options.display.content = _displayContent;
                    $parent.data(OPTIONSCACHENAME, _options);
                }else{
                    console.log('布局设置失败');
                }
        	}, 1000);
        },
        //启动被叫监听
        startCallListener:function(){
            var $embed = $(this),
            	_embedId = $embed.attr('id'),
                $parent = $embed.parent(),
                _options = $parent.data(OPTIONSCACHENAME),
                _callListener,
                _interval,
                _handler,
                _hangup;

            if(!_options) return;

            _callListener = _options.callListener || {};
            _interval = _callListener.interval;
            _handler = _callListener.onCall;
            _hangup = _callListener.onHangup;

            if(_interval && typeof _interval==='number'){
                clearInterval(_interval);
            }
            _interval = setInterval(function(){
                var _status = {}, _sip,
                	e_emberd = document.getElementById(_embedId);
                try{
                    _status = e_emberd.I_GetStatus();
                }catch(e){
                    console.log(e);
                    _status = $.toJSON({"sip":{"status":"idle",}});
                    //清除监听
                    clearInterval(_interval);
                }
                _status = $.parseJSON(_status);
                _sip = _status.sip || {};
                console.log('当前状态：'+_sip.status);
                if(SIPSTATUS_CALLING===_sip.status || SIPSTATUS_CALLED===_sip.status){
                    if(typeof _handler === 'function'){
                        _handler($embed);
                    }
                }else if(SIPSTATUS_IDEL === _sip.status){
                    if(typeof _hangup === 'function'){
                        _hangup($embed);
                    }
                }
            }, INTERVAL_ONCALL);

            //更新缓存
            _options.callListener = _options.callListener || {};
            _options.callListener.interval = _interval;
            _options.callListener.onCall = _handler;
            $parent.data(OPTIONSCACHENAME, _options);
        }
    };
}(jQuery);