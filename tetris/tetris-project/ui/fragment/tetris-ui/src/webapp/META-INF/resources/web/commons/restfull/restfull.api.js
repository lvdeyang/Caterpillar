/**
 * Created by lvdeyang on 2017/8/24.
 */
define([

    'storage',
    'router',
    'config',
    'jquery',
    'json'

], function(storage, router, config, $){

    var baseError = '#/' + config.redirect.error;

    //默认的消息提示
    var messenger = {
        info:function(content){
            alert(content);
        }
    };
    messenger['success'] = messenger['warning']
                          = messenger['error']
                          = messenger['info'];

    //默认的加载样式
    var loader = {

        loading:function(){console.log('loading');},

        end:function(){console.log('end');}

    };

    //成功处理
    var handleSuccess = function(data, _success, _catch, $loading){

        //获取ajax配置
        var conf = ajax._conf;

        var _status = data.status;

        if(_status === ajax.TIMEOUT){
            //超时重定向到错误页面
            window.location.href = window.BASEPATH + 'web/app/error/timeout.html';
        }else if(_status === ajax.FORBIDDEN){
            //校验失败
            conf.messenger.error('服务器拒绝，拒绝原因：'+data.message, status);
        }else if(_status === ajax.NOTFOUND){
            //资源未找到
            conf.messenger.error('资源未找到，服务器信息：'+data.message+'，原因可能是由于多个成员同时操作引起的数据不同步，请刷新页面获取最新数据。', status);
        }else if(_status === ajax.CONFLICT){
            //冲突
            conf.messenger.error('发生冲突，服务器信息：'+data.message, status);
        }else if(_status === ajax.ERROR){
            //服务器异常
            if(typeof _success==='function' && _catch[ajax.ERROR]){
                _success(null, _status, data.message);
            }else{
                window.location.hash = baseError + '/' + _status + '/' + encodeURI(data.message);
            }
        }else if(_status !== ajax.SUCCESS){
            window.location.hash = baseError + '/' + _status + '/' + encodeURI('前端未定义当前状态码：'+_status);
        }

        //回调
        if(typeof _success==='function' && (_status===ajax.SUCCESS || _catch[_status]===true)){
            //处理业务数据，返回object
            var bus = data.data;
            if(typeof bus !== 'object'){
                try{bus = $.parseJSON(bus);}catch(e){}
            }
            _success(bus, _status, data.message);
        }

        //结束loading
        if($loading){
            conf.loader.end.apply($loading, []);
        }

    };

    //处理GET请求中的中文问题
    var doUrlEncode = function(data){
        if(typeof data === 'object'){
            for(var i in data){
                if(typeof data[i] === 'string'){
                    data[i] = encodeURIComponent(data[i]);
                }else if(typeof data[i] === 'object'){
                    //这个地方递归
                    doUrlEncode(data[i]);
                }
            }
        }
    };

    /**
     * ajax 统一处理以及restfull api
     */
    var ajax = {

        /**
         * 服务端状态码
         */
        //执行成功
        SUCCESS:200,

        //服务器拒绝（校验不过）
        FORBIDDEN:403,

        //未找到资源
        NOTFOUND:404,

        //超时
        TIMEOUT:408,

        //冲突
        CONFLICT:409,

        //服务器异常
        ERROR:500,

        //ajax默认配置
        _default:{
            debug:false,
            jsonp:'jsonpQueue',
            login:null,
            authname:'BASENAME000001',
            sessionIdName:'BASENAME000002',
            timeouturi:'error',
            messenger:messenger,
            loader:loader
        },

        /**
         * conf.debug 是否开启debug模式
         * conf.jsonp jsonp请求的回调名称
         * conf.login:登录页面
         * conf.authname 登录标记名称
         * conf.sessionIdName sessionId名称
         * conf.timeouturi  登录页面uri
         * conf.messanger.info 信息提示
         * conf.messanger.success 成功提示
         * conf.messanger.warning 警告提示
         * conf.messanger.error 错误提示
         * conf.loader.loading 显示加载中
         * conf.loader.end     隐藏加载中
         */
        init:function(conf){

            //var _default = $.extend(true, {}, ajax._default);
            ajax._conf = conf = $.extend(true, ajax._default, conf);

            //jsonp回调队列
            var jsonpQueue = window[conf.jsonp] = [];

            //jQuery ajax 代理方法
            var _ajax = $.ajax;

            /**
             * 自定义参数：
             *    $loading: 指定loading样式的节点
             *    catch:    定义要补货的状态码
             */
            $.ajax = function(opt){

                var _catch = opt['catch'] || {'200': true};

                var beStream = false;
                //扩展opt.data加入登录认证--采取session优先的策略
                if(typeof opt.data === 'string'){
                    beStream = true;
                    opt.data = $.parseJSON(opt.data);
                }

                var _authentication = null;

                if(sessionStorage && sessionStorage.getItem(conf.authname)){
                    _authentication = sessionStorage.getItem(conf.authname);
                }else if(localStorage && localStorage.getItem(conf.authname)){
                    _authentication = localStorage.getItem(conf.authname);
                }

                if(sessionStorage){
                    opt.data = opt.data || {};
                    if(typeof opt.data.append === 'function'){
                        //判断是否是formData
                        opt.data.append(conf.authname, _authentication);
                    }else{
                        opt.data[conf.authname] = _authentication;
                    }
                }

                //content-type
                var contentType = opt.contentType;

                //检测loading
                var $loading =  opt.$loading;

                //开始loading
                if($loading){
                    conf.loader.loading.apply($loading, []);
                }

                //扩展success回调
                var _success = opt && opt.success,
                    _error = opt && opt.error;

                //请求异常处理
                var _opt = $.extend(opt, {
                    error:function(XMLHttpRequest, textStatus, errorThrown) {

                        if(XMLHttpRequest.status===401 && ajax._conf.login){
                            //从定向到登录
                            window.location.href = ajax._conf.login;
                            return;
                        }

                        if(XMLHttpRequest.status !== 200){
                            // 状态码
                            console.log(XMLHttpRequest.status);
                            // 状态
                            console.log(XMLHttpRequest.readyState);
                            // 错误信息
                            console.log(textStatus);

                            if(typeof _error === 'function'){
                                var $this = opt.context || $;
                                _error.apply($this, [XMLHttpRequest]);
                            }else{
                                //执行默认动作
                                //window.location.hash = baseError + '/' + XMLHttpRequest.status + '/' + encodeURI('服务器信息：'+textStatus);
                                //router.push({path:config.redirect.error, params:{code:XMLHttpRequest.status, message:encodeURI('服务器信息：'+textStatus)}});
                                window.location.href = window.BASEPATH + 'web/app/error/request-fail.html';
                            }
                        }
                    }
                });

                //加入oauth2令牌
                var _token = storage.getItem(ajax._conf.authname) || '';
                var _sessionId = storage.getItem(ajax._conf.sessionIdName) || '';

                //bvc兼容
                _opt.headers = {Authorization:_token};

                _opt.headers['tetris-001'] = _token;
                _opt.headers['tetris-002'] = _sessionId;

                //成功请求处理
                if(!conf.debug && opt.dataType!=='jsonp'){

                    if(beStream) _opt.data = $.toJSON(_opt.data);

                    //处理非debug模式以及ajax请求
                    _opt.success = function(data){
                        //处理返回值
                        if(typeof data !== 'object'){
                            try{data = $.parseJSON(data);}catch(e){}
                        }
                        handleSuccess(data, _success, _catch, $loading);
                    };

                }else{
                    //处理debug模式以及jsonp请求
                    _opt.dataType = 'jsonp';

                    _opt.type = 'GET';

                    //获取jsonp回调索引
                    var i;
                    if(jsonpQueue.length <= 0){
                        i = 0;
                    }else{
                        for(var j=0; j<jsonpQueue.length; j++){
                            if(!jsonpQueue[j]){
                                i = j;
                            }else if(j === jsonpQueue.length-1){
                                i = jsonpQueue.length;
                            }
                        }
                    }

                    //处理jsonp回调
                    jsonpQueue[i] = function(data){

                        //处理返回值
                        if(typeof data !== 'object'){
                            try{data = $.parseJSON(data);}catch(e){}
                        }

                        handleSuccess(data, _success, _catch, $loading);

                        //移除jsonp回调
                        delete jsonpQueue[i];
                    };

                    var _existError = _opt.error;
                    _opt.error = function(XMLHttpRequest, textStatus, errorThrown){
                        _existError(XMLHttpRequest, textStatus, errorThrown);
                        //移除jsonp回调
                        delete jsonpQueue[i];
                    };

                    //加入jsonp回调
                    _opt.data.jsonp = conf.jsonp + '[' + i + ']';

                    //加入jsonp的headers(这个头会提示不安全，所以不加了)
                    /*_opt.headers = $.extend(true, _opt.headers?_opt.headers:{}, {
                     'Access-Control-Request-Method':_opt.type
                     });*/

                }

                //设置返回格式
                _opt.accepts = {
                    json:"application/json;charset=utf-8",
                    text:"text/plain;charset=utf-8"
                };

                //处理GET请求中的中文问题
                if(_opt.type === 'GET'){
                    doUrlEncode(_opt.data);
                }

                //加入content-type
                _opt.contentType = contentType===false?false:(contentType || 'application/x-www-form-urlencoded; charset=UTF-8');

                return _ajax(_opt);
            };

        },

        //get
        get:function(uri, data, callback, contentType, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'GET',
                url:window.HOST + window.SCHEMA + uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                contentType:contentType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //post
        post:function(uri, data, callback, contentType, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'POST',
                url:window.HOST + window.SCHEMA + uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                contentType:contentType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //处理上传
        upload:function(uri, formData, callback, catchCodeArr){

            return $.ajax({
                type:'POST',
                url:window.HOST + window.SCHEMA + uri,
                data:formData,
                cache: false,
                processData: false,
                contentType: false,
                success:callback,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //update
        update:function(uri, data, callback, contentType, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'PUT',
                url:window.HOST + window.SCHEMA + uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                contentType:contentType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //remove
        remove:function(uri, data, callback, contentType, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'DELETE',
                url:window.HOST + window.SCHEMA + uri,
                data:$.toJSON(data),
                success:callback,
                $loading:$loading,
                dataType:dataType,
                contentType:contentType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //处理补货状态码
        doCatchCode:function(catchCodeArr){

            var _catch = {'200':true};

            if(!catchCodeArr) return _catch;

            if(!$.isArray(catchCodeArr) && catchCodeArr){
                catchCodeArr = [catchCodeArr];
            }

            for(var i= 0, len=catchCodeArr.length-1; i<=len; i++){
                _catch[catchCodeArr[i]] = true;
            }
            return _catch;

        }

    };

    //query
    ajax.query = ajax.get;

    //add
    ajax.add = ajax.post;

    //no error catch code
    ajax.NO_ERROR_CATCH_CODE = [
        ajax.SUCCESS,
        ajax.FORBIDDEN,
        ajax.NOTFOUND,
        ajax.TIMEOUT,
        ajax.CONFLICT
    ];

    //total catch code
    ajax.TOTAL_CATCH_CODE = [
        ajax.SUCCESS,
        ajax.FORBIDDEN,
        ajax.NOTFOUND,
        ajax.TIMEOUT,
        ajax.CONFLICT,
        ajax.ERROR
    ];

    return ajax;

});