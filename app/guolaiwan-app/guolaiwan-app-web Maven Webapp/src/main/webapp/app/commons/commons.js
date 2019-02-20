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
     * 复选框插件
     */
    var checkbox = {
        /**
         * conf.selector   string
         * conf.context    $
         * conf.doCheckall boolean
         */
        init:function(conf){
            var $checkbox = null;
            if(conf.context){
                $checkbox = conf.context.find(conf.selector);
            }else{
                $checkbox = $(conf.selector);
            }

            //初始化iCheck
            $checkbox.iCheck({
                checkboxClass: 'icheckbox_flat-red'
            });

            if(conf.doCheckall === true){

                //复选框全选
                $checkbox.on('ifChecked', function(){
                    var $this = $(this),
                        $checkbox_all = $('[name='+$this.attr('name')+']');
                    if($this.is('.manual')){
                        $this.removeClass('manual');
                        return;
                    }
                    if($this.is('.checkall')){
                        $checkbox_all.each(function(){
                            var $target = $(this);
                            if(!$target.is(':checked')){
                                $target.addClass('manual');
                                $target.iCheck('check');
                            }
                        });
                    }else{
                        var counter = 0;
                        var $checkall = null;
                        $checkbox_all.each(function(){
                            var $target = $(this);
                            if(!$target.is('.checkall') && $target.is(':checked')){
                                counter ++;
                            }else{
                                $checkall = $target;
                            }
                        });
                        if(counter == $checkbox_all.length-1){
                            if(!$checkall.is(':checked')){
                                $checkall.addClass('manual');
                                $checkall.iCheck('check');
                            }
                        }
                    }
                });

                $checkbox.on('ifUnchecked', function(){
                    var $this = $(this),
                        $checkbox_all = $('[name='+$this.attr('name')+']');
                    if($this.is('.manual')){
                        $this.removeClass('manual');
                        return;
                    }
                    if($this.is('.checkall')){
                        $checkbox_all.each(function(){
                            var $target = $(this);
                            if($target.is(':checked')){
                                $target.addClass('manual');
                                $target.iCheck('uncheck');
                            }
                        });
                    }else{
                        var $checkall = null;
                        $checkbox_all.each(function(){
                            var $target = $(this);
                            if($target.is('.checkall')){
                                $checkall = $target;
                                return false;
                            }
                        });
                        if($checkall.is(':checked')){
                            $checkall.addClass('manual');
                            $checkall.iCheck('uncheck');
                        }
                    }
                });

            }
        },

        check:function($checkbox){
            $checkbox.iCheck('check');
        },
        uncheck:function($checkbox){
            $checkbox.iCheck('uncheck');
        },

        group:{

            prepare_checkgroup:juicer(html_checkgroup),

            OPTIONSBINDNAME:'options',

            /**
             * conf.title    string
             * conf.selector string
             * conf.context  $
             * conf.column   number
             * conf.data     [{key:'',value:''}]
             */
            init:function(conf){

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
                conf.textShow = checkbox.group.getTextShow(conf.data);

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

                var $checkgroup = $(checkbox.group.prepare_checkgroup.render(conf));

                $input.val(conf.textShow).attr('data-toggle', 'modal').attr('data-target', '#'+conf.now);

                $parent.append($checkgroup);

                $input.data(checkbox.group.OPTIONSBINDNAME, conf);

                $checkgroup.find('.checkgroup-checkbox input').iCheck({
                    checkboxClass: 'icheckbox_flat-red'
                });

                //全选
                $checkgroup.on('click.checkall', '.checkgroup-action-checkall', function(){
                    var $checkall = $(this),
                        $modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
                        $input = $modal.prev(),
                        _options = $input.data(checkbox.group.OPTIONSBINDNAME),
                        _check = _options.interface.checkbox.check;

                    checkbox.group.proxy_check_toggle($modal.find('.checkgroup-checkbox input'), _check, 0);
                });

                //反选
                $checkgroup.on('click.reverse', '.checkgroup-action-reverse', function(){
                    var $checkall = $(this),
                        $modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
                        $input = $modal.prev(),
                        _options = $input.data(checkbox.group.OPTIONSBINDNAME),
                        _check = _options.interface.checkbox.check,
                        _uncheck = _options.interface.checkbox.uncheck;

                    $modal.find('.checkgroup-checkbox input').each(function(){
                        var $checkbox = $(this);
                        if($checkbox.is(':checked')){
                            checkbox.group.proxy_check_toggle($checkbox, _uncheck, 1);
                        }else{
                            checkbox.group.proxy_check_toggle($checkbox, _check, 0);
                        }
                    });
                });

                //撤销
                $checkgroup.on('click.revoke', '.checkgroup-action-revoke', function(){
                    var $checkall = $(this),
                        $modal = $checkall.closest('.Tetris.checkgroup.modal').first(),
                        $input = $modal.prev(),
                        _options = $input.data(checkbox.group.OPTIONSBINDNAME),
                        _uncheck = _options.interface.checkbox.uncheck;

                    checkbox.group.proxy_check_toggle($modal.find('.checkgroup-checkbox input'), _uncheck, 1);
                });

                //确定按钮
                $checkgroup.on('click.set', '.checkgroup-set', function(){
                    var $button = $(this),
                        $modal = $button.closest('.Tetris.checkgroup.modal').first(),
                        $input = $modal.prev(),
                        _checked, _textShow;

                    _checked = checkbox.group.getChecked.apply($modal.parent()[0], []);
                    _textShow = checkbox.group.getTextShow(_checked);
                    $input.val(_textShow);
                });

                //模态框关闭事件
                $checkgroup.on('hide.bs.modal.Tetris.checkgroup', function(){
                    var $modal = $(this),
                        $input = $modal.prev(),
                        _checked, _textShow;

                    _checked = checkbox.group.getChecked.apply($modal.parent()[0], []);
                    _textShow = checkbox.group.getTextShow(_checked);
                    $input.val(_textShow);
                });

                //模态框展示事件
                $checkgroup.on('show.bs.modal.Tetris.checkgroup', function(){
                    var $modal = $(this),
                        $input = $modal.prev(),
                        _options = $input.data(checkbox.group.OPTIONSBINDNAME),
                        _data = _options.data,
                        _check = _options.interface.checkbox.check,
                        _uncheck = _options.interface.checkbox.uncheck;

                    $modal.find('.checkgroup-checkbox input').each(function(){
                        var $checkbox = $(this);
                        if(_data[parseInt($checkbox.attr('data-index'))].checked === true){
                            checkbox.group.proxy_check_toggle($checkbox, _check, 0);
                        }else{
                            checkbox.group.proxy_check_toggle($checkbox, _uncheck, 1);
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

                var _options = $input.data(checkbox.group.OPTIONSBINDNAME),
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
                $input.data(checkbox.group.OPTIONSBINDNAME, _options);
                return _checked;
            }
        }

    };

    /**
     * 下拉列表
     */
    var select = {

        /**
         * conf.class
         * conf.style
         * conf.data   [{id[|key]:'', value[|name]:''}]
         */
        get:function(conf){

            var _select = '<select class="form-control '+((typeof conf['class'])==='string'?conf['class']:'')+'" style="'+((typeof conf.style)==='string'?conf.style:'')+'">';

            for(var i=0,len=conf.data.length-1; i<=len; i++){
                var _data = conf.data[i];
                _select += '<option value="'+(_data.id?_data.id:_data.key)+'">'+(_data.name?_data.name:_data.value)+'</option>';
            }

            _select += '</select>';

            return _select;
        }

    };

    /**
     * 图片灯箱
     * 不支持多个灯箱并存
     */
    var picbox = {

        prepare_picbox:juicer(html_picbox),

        _default:{
            waiting:true,
            img:false,
            name:''
        },

        //初始化
        init:function(conf){

            var _default = $.extend(true, {}, picbox._default);
            conf = $.extend(true, _default, conf);

            var $picbox = $(picbox.prepare_picbox.render(conf));

            $('body').append($picbox);

            if(conf.waiting === true){
                var $waiting = $picbox.find('.waiting');
                loader.loading.apply($waiting, []);
            }

            //绑定事件
            $picbox.on('click', 'span', function(){
                picbox.destroy();
            });

        },

        //设置
        set:function(conf){

            var _name = conf.name;
            var _img = conf.img;

            var $picbox = $('body').find('.picbox');
            var $waiting = $picbox.find('.waiting');
            var $name = $picbox.find('.picbox-name');

            if($waiting.is('.waiting')){
                loader.end.apply($picbox, []);
                $waiting.removeClass('waiting');
                $waiting.append('<img src="'+_img+'" />');
            }else{
                var $img = $waiting.find('img');
                if($img[0]){
                    $img.attr('src', _img);
                }else {
                    $waiting.append('<img src="'+_img+'" />');
                }
            }
            $name.text(_name);

        },

        //销毁
        destroy:function(){
            $('body').find('.picbox').remove();
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
     * modal
     */
    var modal = {

        prepare_modal:juicer(html_modal),

        _default:{
            type:null,
            status:null,
            title:'',
            height:'400',
            close:'关闭',
            save:'确定'
        },

        createAndShow:function(conf){

            var _default = $.extend(true, {}, modal._default);
            conf = $.extend(true, _default, conf);

            var $modal = $(modal.prepare_modal.render(conf));

            $body.append($modal);

            $modal.modal('show');

            $modal.on('hidden.bs.modal', function(){
                modal.close($(this));
            });

            return $modal;

        },

        getContent:function($modal){
            return $modal.find('.modal-body');
        },

        close:function($modal){
            $modal.modal('hide');
            $modal.remove();
        }

    };

    /**
     * input-spinner
     */
    var spinner = {

        /**
         * conf.selector   string
         * conf.context    $
         * conf.onPlus     fn
         * conf.onMinus    fn
         */
        init:function(conf){

            var $spiner = null;
            if(conf.context){
                $spiner = conf.context.find(conf.selector);
            }else{
                $spiner = $(conf.selector);
            }

            $spiner.on('click', '.input-group-addon', function(){
                var $button = $(this);
                if($button.is('.disabled')){
                    return;
                }
                var $input = $button.siblings('input');

                var _complete_plus = function(){
                    $input.val(parseInt($input.val())+1);
                    var $minus = $button.siblings('.minus');
                    $minus.removeClass('disabled');
                }

                var _compolete_minus = function(){
                    $input.val(parseInt($input.val())-1);
                    if($input.val() == 1){
                        $button.addClass('disabled');
                    }
                }

                if($button.is('.plus')){
                    if(typeof conf.onPlus === 'function'){
                        conf.onPlus.apply($button.closest('.input-spinner')[0], [parseInt($input.val())+1, _complete_plus]);
                    }else{
                        _complete_plus();
                    }
                }else if($button.is('.minus')){
                    if(typeof conf.onMinus === 'function'){
                        conf.onMinus.apply($button.closest('.input-spinner')[0], [parseInt($input.val())-1, _compolete_minus]);
                    }else{
                        _compolete_minus();
                    }
                }
            });
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
        picbox:picbox,
        messanger:messanger,
        loader:loader,
        modal:modal,
        attach:attach,
        cache:cache,
        spinner:spinner,
        select:select
    };
});