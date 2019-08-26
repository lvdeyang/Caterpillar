/* ========================================================================
 * component : jQuery.layout.auto.js: version 1.0.0
 * describe  : 自定义屏幕布局
 * dependency: jQuery.js 2.2.3
 * ========================================================================
 * created by lvdeyang
 * 2018年6月25日
 * ======================================================================== */

+function($){

    // 依赖检查
    // =========================

    if(!$) throw new Error('缺失依赖：jQuery.js， 建议版本：2.2.3');

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

    var PLUGINNAME = 'layout-auto',
        PROXYNAME = '',
        TPLURL = '';

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
    }

    // 默认配置
    // =========================

    var defultOptions = {
        cell:{
            column:1,
            row:1,
            style:''
        },
        cellspan:[/*{
            x:0,
            y:0,
            r:0,
            b:0
        }*/],
        name:'split_1',
        style:'',
        editable:true,
        event:{
            //drop:function(e){}
        }
    };

    /*缓存结构
    defultOptions.cells = [{
        $cell:null,
        relative:{
            x:0,
            y:0,
            w:1,
            h:1
        },
        absolute:{
            x:'',
            y:'',
            w:'',
            h:''
        }
    }];*/

    // 共有方法
    // =========================

    var publics = {

        //版本
        version:function(){return '1.0.0';},

        //创建
        create:function(options){

            var _default = $.extend(true, {}, defultOptions);
            options = $.extend(true, _default, options);
            options.area = 'area_' + new Date().getTime();
            var $container = $(this);
            var $table = $(privates.generateHtml(_default.cell)).data('options', options);
            $container.append($table);

            //初始化单元格坐标
            privates.initCells.apply($container);

            if(typeof options.event.drop === 'function'){
                //绑定拖拽事件
                $table.find('tbody td').each(function(){
                    var $td = $(this);
                    $td[0].addEventListener('drop', function(e){
                        var $this = $(this);
                        $this.removeClass('over');
                        options.event.drop.apply(this, [e]);
                    });
                    $td[0].addEventListener('dragover', function(e){
                        e.preventDefault();
                        var $this = $(this);
                        $this.addClass('over');
                    });
                    $td[0].addEventListener('dragleave', function(e){
                        var $this = $(this);
                        $this.removeClass('over');
                    });
                });
            }

            //初始化单元格合并
            if(options.cellspan && options.cellspan.length>0){
                var $trs = $table.find('tbody tr');
                for(var i=0; i<options.cellspan.length; i++){
                    var span = options.cellspan[i];
                    span = {
                        x:parseInt(span.x),
                        y:parseInt(span.y),
                        r:parseInt(span.r),
                        b:parseInt(span.b)
                    };
                    var $tr = $($trs[span.y]);
                    var $tds = $tr.find('td');
                    privates.cellspan.apply($tds[span.x], [span.r, span.b]);
                }
            }
        },

        //设置数据
        setData:function(data){
            var $cell = $(this);
            $cell.data('business-cache', data);
        },

        //清除数据
        clearData:function(){
            var $cell = $(this);
            $cell.text('&nbsp;');
            $cell.removeData('business-cache');
        },

        //单元格高亮
        highLight:function(){
            var $cell = $(this);
            $cell.addClass('high-light');
        },

        //取消单元格高亮
        disHighLight:function(){
            var $cell = $(this);
            $cell.removeClass('high-light');
        },

        //获取屏幕数据
        generateLayout:function(withoutData){
            var $container = $(this);
            var $table = $container.find('.layout-auto');
            var options = $table.data('options');
            var $trs = $table.find('tbody tr');
            var layouts = [];
            var serialNum = 1;
            for(var i=0; i<$trs.length; i++){
                var $tr = $($trs[i]);
                var $tds = $tr.find('td');
                for(var j=0; j<$tds.length; j++){
                    var $td = $($tds[j]);
                    if(!$td.is(':hidden')){
                        var cell = privates.queryCell.apply($td[0], [options]);
                        var colspan = parseInt($td.attr('colspan'));
                        colspan = isNaN(colspan)?1:colspan;
                        var rowspan = parseInt($td.attr('rowspan'));
                        rowspan = isNaN(rowspan)?1:rowspan;
                        var l = {
                            serialNum:serialNum,
                            x:cell.relative.x,
                            y:cell.relative.y,
                            w:privates.fractionsMultiply(cell.relative.w, colspan),
                            h:privates.fractionsMultiply(cell.relative.h, rowspan)
                        }
                        if(!withoutData){
                            l.data = $td.data('business-cache');
                        }
                        layouts.push(l);
                        serialNum += 1;
                    }
                }
            }
            return layouts;
        },

        //生成模板
        generateTpl:function(){
            var $container = $(this);
            var $table = $container.find('.layout-auto');
            var options = $table.data('options');
            var layout = publics.generateLayout.apply(this, [true]);
            return {
                basic:{
                    column:options.cell.column,
                    row:options.cell.row
                },
                cellspan:options.cellspan,
                layout:layout
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

    // 私有方法
    // =========================

    var privates = {
        //配置校验
        check:function(options){

        },

        //获取html模板
        getHtmlTemplate:function(options){

        },

        //重绘尺寸
        resize:function(e){

        },

        //缓存事件
        cacheEvent:function(event){
            var $container = $(this),
                $player = $container.find('').first(),
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
                $player = $container.find('').first(),
                i, _implament;
            for(i in INTERFACE){
                _implament = implament[INTERFACE[i]];
                if(typeof _implament === 'function'){
                    $player.data(INTERFACE[i], _implament);
                }
            }
            return $container;
        },

        //生成模板
        generateHtml:function(cell){
            var _style = cell.style;
            var column = parseInt(cell.column);
            var row = parseInt(cell.row);
            var html = '<table class="layout-auto"><thead><tr>';

            var perWidth = parseFloat(100/column);
            var lastWidth = 100 - (column-1)*perWidth;
            var perHight = parseFloat(100/row);
            var lastHeight = 100 - (row-1)*perHight;

            for(var i=0; i<column; i++){
                if(i === column-1){
                    html += '<td style="width:'+lastWidth+'%"></td>';
                }else{
                    html += '<td style="width:'+perWidth+'%"></td>';
                }
            }

            html += '</tr></thead>';

            for(var i=0; i<row; i++){
                if(i === row-1){
                    html += '<tr style="height:'+lastHeight+'%">';
                }else{
                    html += '<tr style="height:'+perHight+'%">';
                }
                for(var j=0; j<column; j++){
                    html += '<td class="cell '+_style+'" ondragstart="return false;">&nbsp;</td>';
                }
                html += '</tr>'
            }
            html += '</table>';
            return html;
        },

        //合并单元格--只合并colspan=1&rowspan=1的单元格
        cellspan:function(r, b, history){
            var $cell = $(this);

            //初始坐标
            var colspan = parseInt($cell.attr('colspan'));
            colspan = isNaN(colspan)?1:colspan;
            var rowspan = parseInt($cell.attr('rowspan'));
            rowspan = isNaN(rowspan)?1:rowspan;

            //获取基本信息
            var $table = $cell.closest('.layout-auto');
            var $trs = $table.find('tbody>tr');
            var _options = $table.data('options');

            //计算坐标
            var p = privates.cellPositionFromCache.apply($cell[0], [_options]);

            //校验
            if((p.l+colspan+r) > _options.cell.column) throw new Error('单元格合并横向越界');
            if((p.t+rowspan+b) > _options.cell.row) throw new Error('单元格合并纵向越界');

            for(var i=p.t; i<p.t+rowspan; i++){
                var $tr = $($trs[i]);
                var $tds = $tr.find('td');
                for(var j=p.l+colspan; j<p.l+colspan+r; j++){
                    $($tds[j]).hide().data('hidden-proxy', $cell);
                }
            }

            for(var i=p.t+rowspan; i<p.t+rowspan+b; i++){
                var $tr = $($trs[i]);
                var $tds = $tr.find('td');
                for(var j=p.l; j<p.l+colspan+r; j++){
                    $($tds[j]).hide().data('hidden-proxy', $cell);
                }
            }

            $cell.attr('colspan', colspan+r)
                 .attr('rowspan', rowspan+b);

            //记录操作历史
            if(history){
                _options.cellspan = _options.cellspan || [];
                _options.cellspan.push({
                    x: p.l,
                    y: p.t,
                    r:r,
                    b:b
                });
                $table.data('options', _options);
            }
        },

        //获取单元格坐标
        cellPosition:function(){
            var $cell = $(this);
            var $table = $cell.closest('.layout-auto');
            var $trs = $table.find('tbody>tr');

            var position = {l:0, t:0};
            for(var i=0; i<$trs.length; i++){
                var $tr = $($trs[i]);
                var $tds = $tr.find('td');
                var breakout = false;
                for(var j=0; j<$tds.length; j++){
                    if($cell[0] === $tds[j]){
                        position.l = j;
                        position.t = i;
                        breakout = true;
                        break;
                    }
                }
                if(breakout) break;
            }

            return position;
        },

        //显示扫过的单元格
        cellActive:function(beginCell, endCell, options){
            var cells = options.cells;
            var actives = [];
            for(var i=0; i<cells.length; i++){
                var cell = cells[i];
                $(cell.$cell).removeClass('active')
                              .removeClass('error');
                if(beginCell.relative.x<=cell.relative.x &&
                    beginCell.relative.y<=cell.relative.y &&
                    endCell.relative.x>=cell.relative.x &&
                    endCell.relative.y>=cell.relative.y){
                    var $_cell = $(cell.$cell);
                    if($_cell.is(':hidden')){
                        var $proxy = $_cell.data('hidden-proxy');
                        var contains = false;
                        for(var j=0; j<actives.length; j++){
                            if(actives[j].$cell === $proxy[0]){
                                contains = true;
                                break;
                            }
                        }
                        if(!contains){
                            for(var j=0; j<cells.length; j++){
                                if(cells[j].$cell === $proxy[0]){
                                    actives.push(cells[j]);
                                    break;
                                }
                            }
                        }
                    }else{
                        actives.push(cell);
                    }
                }
            }

            //判断beginCell是否是合并的
            var $_beginCell = $(beginCell.$cell);
            if($_beginCell.is(':hidden')){
                $_beginCell = $_beginCell.data('hidden-proxy');
            }

            //判断是否可以合并
            var isError = false;
            for(var i=0; i<actives.length; i++){
                if(actives[i].$cell !== $_beginCell[0]){
                    var $cell = $(actives[i].$cell);
                    var colspan = parseInt($cell.attr('colspan'));
                    var rowspan = parseInt($cell.attr('rowspan'));
                    if((!isNaN(colspan) && colspan>1) ||
                        (!isNaN(rowspan) && rowspan>1)){
                        isError = true;
                        break;
                    }
                }
            }
            var style = isError?'error':'active';
            for(var i=0; i<actives.length; i++){
                $(actives[i].$cell).addClass(style);
            }
        },

        //恢复单元格状态
        cellRecovery:function(options){
            var cells = options.cells;
            var isError = false;
            for(var i=0; i<cells.length; i++){
                var $cell = $(cells[i].$cell);
                if($cell.is('.error')){
                    $cell.removeClass('error');
                    if(!isError) isError = true;
                }else{
                    $cell.removeClass('active');
                }
            }
            return isError;
        },

        //从内存中获取坐标
        cellPositionFromCache:function(options){
            var cell = this;
            var cells = options.cells;
            for(var i=0; i<cells.length; i++){
                if(cells[i].$cell === cell){
                    return{
                        l:cells[i].relative.x,
                        t:cells[i].relative.y
                    }
                }
            }
        },

        //获取点所在的单元格
        pointInCell:function(options){
            var point = this;
            var cells = options.cells;
            var precells = [];
            for(var i=0; i<cells.length; i++){
                var cell = cells[i];
                if(cell.absolute.x<=point.x &&
                    cell.absolute.y<=point.y &&
                    cell.absolute.x+cell.absolute.w>=point.x &&
                    cell.absolute.y+cell.absolute.h>=point.y){
                   precells.push(cell);
                }
            }
            var target = precells[0];
            for(var i=0; i<precells.length; i++){
                var precell = precells[i];
                var $_cell = $(precell.$cell);
                var colspan = parseInt($_cell.attr('colspan'));
                colspan = isNaN(colspan)?1:colspan;
                var rowspan = parseInt($_cell.attr('rowspan'));
                rowspan = isNaN(rowspan)?1:rowspan;
                if(colspan===1 && rowspan===1){
                    target = precell;
                    break;
                }
            }
            return target;
        },

        //获取一个元素的位置
        getElementPosition:function(x, y){
            var element = this;
            var x = x || 0;
            var y = y || 0;
            if(!$(element).is('body') && element!==window){
                x = x + parseFloat(element.offsetLeft);
                y = y + parseFloat(element.offsetTop);
                var _position = privates.getElementPosition.apply(element.offsetParent, [x, y]);
            }else{
                return {
                    x:x,
                    y:y
                }
            }
            return _position;
        },

        //初始化布局
        initCells:function(){

            var $container = $(this);
            var $table = $container.find('table');
            var options = $table.data('options');

            options.cells = options.cells || [];
            var perW = '1/'+options.cell.column;
            var perH = '1/'+options.cell.row;

            $table.find('tbody td').each(function(){
                var $td = $(this);
                var w = $td[0].clientWidth;
                var h = $td[0].clientHeight;
                var p = privates.getElementPosition.apply($td[0]);

                var c_p = privates.cellPosition.apply($td);

                options.cells.push({
                    $cell:$td[0],
                    absolute:{
                        x:p.x,
                        y:p.y,
                        w:w,
                        h:h
                    },
                    relative:{
                        x:c_p.l,
                        y:c_p.t,
                        w:perW,
                        h:perH
                    }
                });
            });

            $table.data('options', options);
        },

        //查询一个单元格的配置
        queryCell:function(options){
            var cells = options.cells;
            for(var i=0; i<cells.length; i++){
                if(cells[i].$cell === this){
                    return cells[i];
                }
            }
        },

        //分数乘法
        fractionsMultiply:function(base, multiple){
            var fraction = base.split('/');
            var numerator = parseInt(fraction[0]);
            var denominator = parseInt(fraction[1]);
            return numerator*parseInt(multiple) + '/' + denominator;
        }

    }

    // 事件
    // =========================

    //延迟重绘
    $(window).bind('resize.tetris.', function(evnet){

    });

    //事件代理
    $(document).on('mousedown.layout.auto.cell', '.layout-auto .cell', function(e1){
        var $begin = $(this);
        var $table = $begin.closest('.layout-auto');
        var options = $table.data('options');
        if(!options.editable) return;
        var x0 = e1.clientX;
        var y0 = e1.clientY;
        var colspan = parseInt($begin.attr('colspan'));
        colspan = isNaN(colspan)?1:colspan;
        var rowspan = parseInt($begin.attr('rowspan'));
        rowspan = isNaN(rowspan)?1:rowspan;

        var $div = $('#' + options.area);
        if(!$div[0]){
            var $div = $('<div></div>')
                .attr('id', options.area)
                .css({
                'position':'fixed',
                'z-index':'1000',
                'border':'1px dashed #777',
                'left':x0 + 'px',
                'top':y0 + 'px',
                'width':0,
                'height':0
            });
            $('body').append($div);
        }

        var beginCell = privates.pointInCell.apply({x:x0, y:y0}, [options]);

        $(document).on('mousemove.layout.auto.cell', function(e2){
            var x1 = e2.clientX;
            var y1 = e2.clientY;
            var detaX = x1 - x0;
            var detaY = y1 - y0;

            detaX = detaX<0?0:detaX;
            detaY = detaY<0?0:detaY;
            $div.css({
                'width':detaX-5 + 'px',
                'height':detaY-5 + 'px'
            });

            var endCell = privates.pointInCell.apply({x:x1, y:y1}, [options]);
            if(endCell) privates.cellActive(beginCell, endCell, options);
        });

        $(document).on('mouseup.layout.auto.cell', function(e3){
            $div.remove();
            var isError = privates.cellRecovery(options);
            var endCell = privates.pointInCell.apply({x:e3.clientX, y:e3.clientY}, [options]);
            if(endCell && !isError){
                var $end = $(endCell.$cell);
                var p_b = privates.cellPosition.apply($begin);
                var p_e = privates.cellPosition.apply($end);
                var r = p_e.l-p_b.l-colspan+1;
                r = r<0?0:r;
                var b = p_e.t-p_b.t-rowspan+1;
                b = b<0?0:b;
                privates.cellspan.apply($begin, [r, b, true]);
            }
            $(document).unbind('mousemove.layout.auto.cell');
            $(document).unbind('mouseup.layout.auto.cell');
        });

    });
}(jQuery);
