/**
 * Created by lvdeyang on 2016/1/14.
 * 日期扩展
 */
(function(){
    Date.prototype.S = 's';
    Date.prototype.N = 'n';
    Date.prototype.H = 'h';
    Date.prototype.D = 'd';
    Date.prototype.W = 'w';
    Date.prototype.Q = 'q';
    Date.prototype.M = 'm';
    Date.prototype.Y = 'y';

    /**
     * 获取一个日期的相对日期
     * @param strInterval   s:秒，n:'分',h:'小时',d:'天',w:'星期',q:'季度',m:'月',y:'年'
     * @param Number      相对的数量
     * @returns {Date}  日期对象
     */
    Date.prototype.dateAdd = function (strInterval, Number) {
        var dtTmp = this;
        switch (strInterval) {
            case Date.prototype.S :return new Date(Date.parse(dtTmp) + (1000 * Number));
            case Date.prototype.N :return new Date(Date.parse(dtTmp) + (60000 * Number));
            case Date.prototype.H :return new Date(Date.parse(dtTmp) + (3600000 * Number));
            case Date.prototype.D :return new Date(Date.parse(dtTmp) + (86400000 * Number));
            case Date.prototype.W :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
            case Date.prototype.Q :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case Date.prototype.M :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case Date.prototype.Y :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        }
    }
    /**
     * 判断是否为闰年
     * @returns {boolean}
     */
    Date.prototype.isLeapYear = function() {
        return (0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0)));
    }
    /**
     * 将字符串解析为日期对象
     * @param dateStr   格式yyyy-MM-dd HH:mm:ss
     * @returns {Date}
     */
    Date.prototype.parse = function(dateStr){
        var dateStr = dateStr.split(' ');
        var dateArr = dateStr[0].split('-');
        var timeArr = dateStr[1].split(':');
        return new Date(dateArr[0],dateArr[1]-1,dateArr[2],timeArr[0],timeArr[1],timeArr[2]);
    }
    //---------------------------------------------------
    // 日期格式化
    // 格式 YYYY/yyyy/YY/yy 表示年份
    // MM/M 月份
    // W/w 星期
    // dd/DD/d/D 日期
    // hh/HH/h/H 时间
    // mm/m 分钟
    // ss/SS/s/S 秒
    //---------------------------------------------------
    Date.prototype.format = function(formatStr) {
        var str = formatStr;
        var Week = ['日','一','二','三','四','五','六'];

        str=str.replace(/yyyy|YYYY/,this.getFullYear());
        str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

        str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
        str=str.replace(/M/g,(this.getMonth()+1));

        str=str.replace(/w|W/g,Week[this.getDay()]);

        str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
        str=str.replace(/d|D/g,this.getDate());

        str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
        str=str.replace(/h|H/g,this.getHours());
        str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
        str=str.replace(/m/g,this.getMinutes());

        str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
        str=str.replace(/s|S/g,this.getSeconds());

        return str;
    }
    /**
     * 两个日期做差
     * @param minuend 格式yyyy-MM-dd HH:mm:ss
     * @param subtrahend 格式yyyy-MM-dd HH:mm:ss
     * @returns {Number} 单位：秒
     */
    Date.prototype.subtraction = function(minuend, subtrahend){
        minuend = Date.prototype.parse(minuend);
        subtrahend = Date.prototype.parse(subtrahend);
        return (minuend.getTime() - subtrahend.getTime())/1000;
    }
})();

define([

    'text!html-loader',
    'text!html-picbox',
    'text!html-alert',
    'text!html-callout',
    'text!html-modal',
    'text!html-checkgroup',
    'juicer',
    'context',
    'iCheck',
    'datatables',
    'json'

], function(html_loader, html_picbox, html_alert, html_callout, html_modal, html_checkgroup, juicer, context, $){

    var $body = $('body');

    //设置BASEPATH
    window.BASEPATH = window.BASEPATH || '/';

    /**
     * DataTables 语言环境
     * 默认设置成中文
     */
    $.fn.dataTable.defaults.oLanguage = $.extend(true, $.fn.dataTable.defaults.oLanguage, {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    });

    var cache = {

        doLogin:function(key, local){
            if(local===true && localStorage){
                localStorage.setItem(ajax._default.authname, key);
                return;
            }
            if(!local && sessionStorage){
                sessionStorage.setItem(ajax._default.authname, key);
            }
        },

        doLogout:function(local){
            if(local===true && localStorage && localStorage.getItem(ajax._default.authname)){
                localStorage.removeItem(ajax._default.authname);
            }
            if(!local && sessionStorage && sessionStorage.getItem(ajax._default.authname)) {
                sessionStorage.removeItem(ajax._default.authname);
            }
        }

     };

    /**
     * messanger
     */
    var messanger = {

        prepare_alert:juicer(html_alert),

        prepare_callout:juicer(html_callout),

        html:'<div style="width:500px; position:fixed; right:10px; top:60px; z-index:10000;"><div class="messanger-wrapper" style="width:100%;height:auto;"></div></div>',

        $wrapper:null,

        //alert样式
        ALERT:'alert',

        //callout样式
        CALLOUT:'callout',

        _default:{
            type:'alert',
            timeout:5,
            icon:{
                info:'fa-info',
                success:'fa-check',
                warning:'fa-warning',
                error:'fa-ban'
            }
        },

        init:function(conf){

            if(conf) messanger._default = $.extend(true, messanger._default, conf);

            var $html = $(messanger.html);
            messanger.$wrapper = $html.find('.messanger-wrapper').first();

            $('body').append($html);

        },

        info:function(content){
            messanger.create('info', '信息提示', content, messanger._default.icon.info);
        },

        success:function(content){
            messanger.create('success', '操作成功', content, messanger._default.icon.success);
        },

        warning:function(content){
            messanger.create('warning', '操作警告', content, messanger._default.icon.warning);
        },

        error:function(content){
            messanger.create('danger', '发成错误', content, messanger._default.icon.error);
        },

        create:function(type, title, content, icon){
            var _timeout = messanger._default.timeout * 1000;

            var $messanger = null;

            if(messanger._default.type === messanger.ALERT){
                $messanger = $(messanger.prepare_alert.render({
                    title:title,
                    icon:icon,
                    content:content,
                    type:type
                }));
            }else if(messanger._default.type === messanger.CALLOUT){
                $messanger = $(messanger.prepare_callout.render({
                    title:title,
                    content:content,
                    type:type
                }));
            }

            messanger.$wrapper.append($messanger);

            var _timeout_id = setTimeout(function(){
                $messanger.remove();
            }, _timeout);

            $messanger.data('timeout', _timeout_id);
        }

    };

    /**
     * loader
     */
    var loader = {

        prepare_loader:'<div class="overlay loading"><i class="fa fa-refresh fa-spin"></i></div>',

        wrapperClass:'overlay-wrapper',

        loading:function(){

            var $target = $(this);

            $target.addClass(loader.wrapperClass);

            var $loading = $target.find('.loading').first();

            //如果存在就不创建了
            if($loading[0]){
                $loading.show();
                return;
            }

            $target.append(loader.prepare_loader);

        },

        end:function(destroy){

            destroy = destroy || true;

            var $target = $(this);

            $target.removeClass(loader.wrapperClass);

            var $loading = $target.find('.loading').first();

            if(destroy === true){
                $loading.remove();
            }else{
                $loading.hide();
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
            authname:'BASENAME000001',
            timeouturi:'error',
            messanger:messanger,
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

                if(beStream) opt.data = $.toJSON(opt.data);

                //检测loading
                var $loading =  opt.$loading;

                //开始loading
                if($loading){
                    conf.loader.loading.apply($loading, []);
                }

                //扩展success回调
                var _success = opt && opt.success,
                    _error = opt && opt.error;

                var _opt = $.extend(opt, {
                    success:function(data, textStatus){

                        var _status = data.status;

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
                            _success(data, _status, data.message);
                        }

                        //结束loading
                        if($loading){
                            conf.loader.end.apply($loading, []);
                        }

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
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
                });

                return _ajax(_opt);
            };

        },

        //query
        get:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'GET',
                url:window.BASEPATH + uri,
                data:data,
                success:callback,
                $loading:$loading,
                dataType:dataType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //add
        post:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'POST',
                url:window.BASEPATH + uri,
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
                url:window.BASEPATH + uri,
                data: formData,
                processData:false,
                contentType:false,
                success:callback,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //update
        put:function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'PUT',
                url:window.BASEPATH + uri,
                data:$.toJSON(data),
                success:callback,
                $loading:$loading,
                dataType:dataType,
                'catch':ajax.doCatchCode(catchCodeArr)
            });

        },

        //remove
        'delete':function(uri, data, callback, catchCodeArr, $loading, dataType){

            return $.ajax({
                type:'DELETE',
                url:window.BASEPATH + uri,
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

    /**
     * 附加功能
     */
    var attach = {

        //清除a标签上的href属性
        disableLink:function(){
            $('a[href]').each(function(){
                var $a = $(this);
                if(!$a.is('.use-href')){
                    $a.removeAttr('href');
                }
            });
        }

    }

    return {
        ajax:ajax,
        checkbox:checkbox,
        messanger:messanger,
        loader:loader,
        attach:attach,
        cache:cache,
        spinner:spinner,
        select:select
    };
});