/**
 * Created by lvdeyang on 2017/8/24.
 */
define([

    'context',
    'jquery',
    'json'

], function(context, $){

    //默认的消息提示
    var messenger = {

        info:function(content){
            alert(content);
        },

        success:this.info,

        warning:this.info,

        error:this.info

    };

    //默认的加载样式
    var loader = {

        loading:function(){console.log('loading');},

        end:function(){console.log('end');}

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
            authname:'BASENAME000001',
            timeouturi:'error',
            messenger:messenger,
            loader:loader
        },

        /**
         * conf.authname 登录标记名称
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
            conf = $.extend(true, ajax._default, conf);

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

                opt.request = {
                    uri:opt.url,
                    data:opt.data,
                    type:opt.type
                }

                //检测loading
                var $loading =  opt.$loading;

                //开始loading
                if($loading){
                    conf.loader.loading.apply($loading, []);
                }

                //扩展success回调
                var _success = opt && opt.success,
                    _error = opt && opt.error;

                var _opt = {

                    request: $.toJSON(opt.request),

                    onSuccess:function(data){

                        data = $.parseJSON(data);

                        var _status = data.status,
                            _data = data.data;

                        if(_status === ajax.TIMEOUT){
                            //超时重定向到错误页面
                            window.location.href = window.BASEPATH + conf.timeouturi;
                        }else if(_status === ajax.FORBIDDEN){
                            //校验失败
                            conf.messanger.error('服务器拒绝，拒绝原因：'+data.message, status);
                        }else if(_status === ajax.NOTFOUND){
                            //资源未找到
                            conf.messanger.error('资源未找到，服务器信息：'+data.message+'，原因可能是由于多个成员同时操作引起的数据不同步，请刷新页面获取最新数据。', status);
                        }else if(_status === ajax.CONFLICT){
                            //冲突
                            conf.messanger.error('发生冲突，服务器信息：'+data.message, status);
                        }else if(_status === ajax.ERROR){
                            //服务器异常
                            context.load('error', data);
                        }else if(_status !== ajax.SUCCESS){
                            context.load('error', {
                                status:'UnknownCode',
                                message:'前端未定义当前状态码：'+data.status
                            });
                        }

                        //回调
                        if(typeof _success==='function' && (_status===ajax.SUCCESS || _catch[_status]===true)){
                            _success(_data, _status, data.message);
                        }

                        //结束loading
                        if($loading){
                            conf.loader.end.apply($loading, []);
                        }

                    },

                    onFailure: function (XMLHttpRequest, textStatus, errorThrown) {
                        // 状态码
                        console.log(XMLHttpRequest.status);
                        // 状态
                        console.log(XMLHttpRequest.readyState);
                        // 错误信息
                        console.log(textStatus);

                        if(typeof _error === 'function'){
                            var $this = opt.context || $;
                            _error.apply($this, [XMLHttpRequest]);
                        }
                    }

                }

                window.cefQuery(_opt);
            };

        },

        //get
        get:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'GET',
                url:uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //post
        post:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'POST',
                url:uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //处理上传
        upload:function(uri, formData, callback, catchCodeArr){

            return $.ajax({
                type:'POST',
                url:uri,
                data: formData,
                processData:false,
                contentType:false,
                success:callback,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //update
        update:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'PUT',
                url:uri,
                data:$.toJSON(data),
                success:callback,
                $loading:$loading,
                dataType:dataType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //remove
        remove:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'DELETE',
                url:uri,
                data: $.toJSON(data),
                success:callback,
                $loading:$loading,
                dataType:dataType,
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

    return ajax;

});