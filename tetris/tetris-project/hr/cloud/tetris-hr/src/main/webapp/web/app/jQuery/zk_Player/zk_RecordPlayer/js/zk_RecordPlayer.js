/* ========================================================================
 * zk_RecordPlayer.js: version 1.0.0
 * 本地视频文件播放器插件
 * <embed width="850px" height="650px" type="application/media-suma-record" id="npplayer_plugin1"/>
 * ========================================================================
 * created by lvdeyang
 * 2016年11月22日
 * ======================================================================== */
+function($){
    // 常量
    // =========================
    var PLUGINNAME = 'zk_RecordPlayer',
        OPTIONSCACHENAME = 'options',
        FOLDERCACHENAME = 'ZKAPP-CACHE-FOLDERCACHENAME',
        LASTSCREEN = null,
        BASEFOLDER = localStorage.getItem(FOLDERCACHENAME);
     

    // 默认配置
    // =========================

    var defaultOptions = {
    	'url':''				//视频文件播放地址
    };

    // 共有方法
    // =========================

    var publics = {
    	//初始化
    	init:function(){
    		var $embed = $(this);
    		try{
    			$embed[0].I_Init();
    		}catch(e){console.log(e);}
    	},

		uninit:function(){
			var $embed = $(this);
			try{
				$embed[0].I_Uninit();
			}catch(e){console.log(e);}
		},

		//设置播放速度
		setSpeed:function(speed){
			var $embed = $(this);
			try{
				$embed[0].I_SetSpeed(speed);
			}catch(e){console.log(e);}
		},

    	//销毁
    	destroy:function(){
    		var $embed = $(this),
    			$parent = $embed.parent();
    		try{
    			$embed[0].I_Uninit();
    		}catch(e){console.log(e);}
    		$parent.remove();
    	},
    	
    	//开始播放
    	start:function(mediaAddr){
    		var $embed = $(this);
    		try{
				//做一个中文路径编码
				var components = mediaAddr.split('/');
				for(var i=3; i<components.length; i++){
					components[i] = encodeURIComponent(components[i]);
				}
				var encodeMediaAddr = components.join('/');
    			return $embed[0].I_Start(encodeMediaAddr);
    		}catch(e){console.log(e); return 0;}
    	},
    	
    	//停止播放
    	stop:function(){
    		var $embed = $(this);
    		try{
	    		return $embed[0].I_Stop();
	    	}catch(e){console.log(e); return 0;}
    	},
    	
    	//暂停播放
    	pause:function(){
    		var $embed = $(this);
    		try{
    			$embed[0].I_Pause();
    		}catch(e){console.log(e);}
    	},
    	
    	//恢复播放
    	resume:function(){
    		var $embed = $(this);
    		try{
    			$embed[0].I_Resume();
    		}catch(e){console.log(e);}
    	},
    	
    	//进度调整
    	seek:function(time_ms){
    		var $embed = $(this);
    		try{
    			//console.log($embed[0].I_Seek('+time_ms+'));
    			return $embed[0].I_Seek(time_ms);
    		}catch(e){console.log(e); return 0;}
    	},
    	
    	//恢复声音
    	enableAudio:function(){
    		var $embed = $(this);
    		try{
    			$embed.I_EnableAudio(enable);
    		}catch(e){console.log(e);}
    	},
    	
    	//设置音量  1到100之间的整数
    	volumnSet:function(vol){
    		var $embed = $(this);
    		try{
    			return $embed[0].I_VolumeSet(vol);
    		}catch(e){console.log(e); return 0;}
    	},
    	
    	//获取当前播放时间  毫秒
    	getPlayTime:function(){
    		var $embed = $(this);
    		try{
    			var time = $embed[0].I_GetPlaytime();
    			//console.log(time);
    			return time;
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//获取播放总时长  毫秒
    	getDuration:function(url){
    		var $embed = $(this);
    		try{
    			if(url){
    				var info = publics.getMediaInfo.apply(this, [url]);
    				return info.duration;
    			}else{
    				return $embed[0].I_GetDuration();
    			}
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//获取录制的开始时间 --这个接口做了点兼容
    	getStartTime:function(url){
    		var $embed = $(this);
    		try{
    			if(url){
    				var info = publics.getMediaInfo.apply(this, [url]);
    				return info.startTime;
    			}else{
    				//这个接口返回的是秒
        			return $embed[0].I_GetStartTime() * 1000;
    			}
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//获取媒体文件信息--这个接口不用播放就能获取
    	getMediaInfo:function(url){
    		var $embed = $(this);
    		try{
    			var info = $embed[0].I_GetMediaInfo(url);
    			
    			var $info = $(info);
    	        var $prog = $info.find('prog');
    	        var $video = $prog.find('video');
    	        var $audio = $prog.find('audio');

    	        return{
    	        	//时间是90K格式，返回毫秒
    	        	startTime:parseInt($prog.attr('start_time'))/90,
    	        	duration:parseInt($prog.attr('duration')),
    	        	codec:$video.attr('codec'),
    	        	width:$video.attr('width'),
    	        	height:$video.attr('height'),
    	        	fps:$video.attr('fps'),
    	        	bitrate:$video.attr('bitrate'),
    	        	codec:$audio.attr('codec'),
    	        	bitrate:$audio.attr('bitrate')
    	        }
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//截屏
    	printScreen:function(path){
    		var $embed = $(this);
    		try{
    			LASTSCREEN = path;
    			return $embed[0].I_CaptureStore(path);
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//设置截屏保存路径
    	setFolder:function(){
    		var $embed = $(this);
    		try{
    			BASEFOLDER = $embed[0].I_SelectFolder();
    			LASTSCREEN = BASEFOLDER;
				localStorage.setItem(FOLDERCACHENAME, BASEFOLDER);
				return BASEFOLDER;
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//获取截屏保存路径
    	getFolder:function(){
    		var $embed = $(this);
    		try{
    			if(!BASEFOLDER){
    				//先设置再打开
    				publics.setFolder.apply($embed[0]);
    			}
    			return BASEFOLDER;
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//选择截屏路径
    	openFolder:function(){
    		var $embed = $(this);
    		try{
    			if(!LASTSCREEN){
    				if(!BASEFOLDER){
        				//先设置再打开
        				publics.setFolder.apply($embed[0]);
        			}
    				LASTSCREEN = BASEFOLDER;
    			}
    			
    			return $embed[0].I_BrowseFolder(LASTSCREEN);
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	//上一帧
    	prevFrame:function(){
    		var $embed = $(this);
    		try{
    			return $embed[0].I_PrevFrame();
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//下一帧
    	nextFrame:function(){
    		var $embed = $(this);
    		try{
    			return $embed[0].I_NextFrame();
    		}catch(e){
    			console.log(e);
    			return 0;
    		}
    	},
    	
    	//下载--返回downloadId
    	downloadFile:function(options){
    		var $embed = $(this);
    		try{
    			if(options.startTime==null || typeof options.startTime=='undefined'){
    				//console.log('$embed[0].I_SaveVideo('+options.url+', '+options.path+')');
    				return $embed[0].I_SaveVideo(options.url, options.path);
    			}
    			//console.log('$embed[0].I_SaveVideo('+options.url+', '+options.path+', '+options.startTime+', '+options.endTime+')');
    			return $embed[0].I_SaveVideo(options.url, options.path, options.startTime, options.endTime);
    		}catch(e){
    			console.log(e);
    			return -1;
    		}
    	},
    	
    	//查询下载进度      I_GetSaveProgress
    	queryProgress:function(downloadId){
    		var $embed = $(this);
    		try{
    			return $embed[0].I_GetSaveProgress(downloadId);
    		}catch(e){
    			console.log(e);
    			return -1;
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
    	
    };
    
}(jQuery);