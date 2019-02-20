/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-layout-body-layout',
    'text!html-layout-body-page',
    'text!html-layout-body-section',
    'text!html-layout-body-filter',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons'

], function(html_header, html_layout, html_page, html_section, html_filter, juicer, context, page, $, commons){

    var PAGENAME = 'layout';

    var prepare_layout = juicer(html_layout);
    var prepare_page = juicer(html_page);
    var prepare_section = juicer(html_section);
    var prepare_filter = juicer(html_filter);

    //操作符
    var operator_type = [{key:'EQ', value:'='}, {key:'NE', value:'!='}, {key:'IN', value:'in'}, {key:'NIN', value:'not in'}];
    var operator_date = [{key:'LIKE', value:'like'},{key:'LT', value:'<'},{key:'GT', value:'>'},{key:'LE', value:'<='},{key:'GE', value:'>='},{key:'BETWEEN', value:'between'}];

    //初始化布局
    var _init_layout = function($content){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的布局，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'layout/get/' + context.model.user.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_layout.render(data));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_layout = $table.DataTable({
                "order":[
                    [5, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 6]
                }],
                paging:false
            });

            var toggle_status = function(layout){
                if(!layout) return;
                var rowIndexes = _table_layout.rows().indexes();
                for(var i=0,len=rowIndexes.length-1; i<=len; i++){
                    var _id = $(_table_layout.rows(rowIndexes[i]).data()[0][0]).val();
                    if(parseInt(_id) === layout.id){
                        _table_layout.cell(rowIndexes[i], 3).data(layout.enable);
                        _table_layout.cell(rowIndexes[i], 6).data(
                            '<a class="toggle" data-toggle="tooltip" data-placement="bottom" title="状态切换" style="margin-right:13px;"><i class="fa '+(layout.enable==='是'?'fa-toggle-on':'fa-toggle-off')+'"></i></a>'+
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑景点" style="margin-right:13px;"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除景点"><i class="fa fa-trash-o"></i></a>'
                        );
                        _table_layout.draw();
                    }
                }
            }

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=layout]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        hasSelected = true;
                        return false;
                    }
                });
                if(hasSelected){
                    $button_remove.removeAttr('disabled');
                }else{
                    $button_remove.attr('disabled', 'true');
                }
            }

            //初始化checkbox
            commons.checkbox.init({
                selector:'input[name=layout]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=layout]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=layout]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //页面列表
            $table.on('click.show_page', '.page-show', function(){
                var $tr = $(this).closest('tr');
                var $cells = $tr.find('td');
                var $checkbox = $($cells[0]);
                var $name = $($cells[1]);

                _init_page($content, {
                    id:$checkbox.find('[name=layout]').val(),
                    name:$name.find('a').text()
                });

            });

            //状态切换事件
            $table.on('click.toggle', '.toggle', function(){
                var $button = $(this);
                var $tr = $button.closest('tr');
                var $checkbox = $tr.find('[name=layout]');

                $button.tooltip('hide');

                _uri = 'layout/toggle/enable/' + context.model.user.id + '/' + $checkbox.val();

                commons.ajax.put(_uri, null, function(data){

                    toggle_status(data.layout);

                    toggle_status(data.oldEnable);

                });

            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_layout.row.add([
                    '-',
                    '<div class="col-lg-4 col-md-6 col-sm-8 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control layout-name" placeholder="布局名称" required style="width:100%; border-radius:4px;"/></div>',
                    '<select class="form-control layout-theme" style="border-rads:4px; width:100%;"><option value="travel">travel</option></select>',
                    '<select class="form-control layout-enable" style="border-rads:4px; width:100%;"><option value="TRUE">是</option><option value="FALSE">否</option></select>',
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control layout-remarks" placeholder="备注" required style="width:100%; border-radius:4px;"/></div>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_layout.rows()[0].length-1;

                var $tr = $(_table_layout.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                $($cells[3]).addClass('center');

                $($cells[5]).addClass('center');

                $($cells[6]).addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'layout/add/' + context.model.user.id;

                    commons.ajax.post(_uri, {

                        name:$tr.find('.layout-name').val(),
                        theme:$tr.find('.layout-theme').val(),
                        enable:$tr.find('.layout-enable').val(),
                        remarks:$tr.find('.layout-remarks').val(),

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_layout.rows()[0].length-1;

                        //删除编辑行
                        _table_layout.rows(_last_index).remove();

                        //新增数据行
                        _table_layout.row.add([
                            '<input type="checkbox" name="layout" class="flat-red" value="'+data.layout.id+'"/>',
                            '<a class="page-show">'+data.layout.name+'</a>',
                            data.layout.theme,
                            data.layout.enable,
                            data.layout.remarks,
                            data.layout.updateTime,
                            '<a class="toggle" data-toggle="tooltip" data-placement="bottom" title="状态切换" style="margin-right:13px;"><i class="fa '+(data.layout.enable==='是'?'fa-toggle-on':'fa-toggle-off')+'"></i></a>'+
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑景点" style="margin-right:13px;"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除景点"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_layout.rows()[0].length-1;

                        var $tr = $(_table_layout.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=layout]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[3]).addClass('center');

                        $($cells[5]).addClass('center');

                        $($cells[6]).addClass('operation').addClass('center');

                        toggle_status(data.oldEnable);

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_layout.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _layoutIds = [];
                $table.find('input[name=layout]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _layoutIds.push($checkbox.val());
                    }
                });

                var _uri = 'layout/remove/' + context.model.user.id;

                commons.ajax.delete(_uri, {

                    layoutIds:_layoutIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_layoutIds.length+'条数据。');

                    _table_layout.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    }

    //初始化页面
    var _init_page = function($content, layout){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的页面，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'layout/page/get/' + context.model.user.id + '/' + layout.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_page.render({
                layout:layout.name,
                pages:data.pages
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_page = $table.DataTable({
                "order":[
                    [4, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 5]
                }],
                paging:true
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=page]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        hasSelected = true;
                        return false;
                    }
                });
                if(hasSelected){
                    $button_remove.removeAttr('disabled');
                }else{
                    $button_remove.attr('disabled', 'true');
                }
            }

            //初始化checkbox
            commons.checkbox.init({
                selector:'input[name=page]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=page]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=page]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //版块列表
            $table.on('click.show_section', 'td .section-show', function(){
                var $source = $(this);
                $source.tooltip('hide');
                var $tr = $source.closest('tr');
                var $cells = $tr.find('td');
                var $checkbox = $($cells[0]);
                var $name = $($cells[1]);
                _init_section($content, layout, {
                    id:$checkbox.find('[name=page]').val(),
                    name:$name.text()
                })
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _uri = 'menu/get/' + context.model.user.id;

                commons.ajax.get(_uri, null, function(data){

                    if(!data.menus || data.menus.length<=0){
                        commons.messanger.info('当前系统中没有菜单，请先创建菜单！');
                        return;
                    }

                    var _menus = '<select class="form-control page-menu" style="border-radius:4px; width:100%;">';
                    for(var i= 0,len=data.menus.length-1; i<=len; i++){
                        _menus += '<option value="'+data.menus[i].id+'">'+data.menus[i].name+'</option>';
                    }
                    _menus += '</select>';

                    _unsaved = true;

                    var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                    _table_page.row.add([
                        '-',
                        '<div class="col-lg-6 col-md-8 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control page-name" placeholder="名称" required style="width:100%; border-radius:4px;"/></div>',
                        '<select class="form-control page-type" style="border-radius:4px; width:100%;"><option value="HOMEPAGE">首页</option><option value="INFO">详情</option></select>',
                        _menus,
                        date,
                        '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                        '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                    ]).draw();

                    var _last_index = _table_page.rows()[0].length-1;

                    var $tr = $(_table_page.row(_last_index).node());

                    var $cells = $tr.find('td');

                    $($cells[0]).addClass('center');

                    $($cells[4]).addClass('center');

                    $($cells[5]).addClass('operation').addClass('center');

                    //保存按钮
                    $tr.on('click.save', '.save', function(){

                        var $save = $(this);

                        var _uri = 'layout/page/add/' + context.model.user.id + '/' + layout.id;

                        commons.ajax.post(_uri, {

                            name:$tr.find('.page-name').val(),
                            type:$tr.find('.page-type').val(),
                            menuId:$tr.find('.page-menu').val()

                        }, function(data, status){

                            if(status === 403) {
                                return;
                            }

                            _unsaved = false;

                            $save.tooltip('hide');

                            var _last_index = _table_page.rows()[0].length-1;

                            //删除编辑行
                            _table_page.rows(_last_index).remove();

                            //新增数据行
                            _table_page.row.add([
                                '<input type="checkbox" name="page" class="flat-red" value="'+data.page.id+'"/>',
                                '<a class="section-show">'+data.page.name+'</a>',
                                data.page.type,
                                data.page.menuname+'<input type="hidden" value="${data.page.menuid}" />',
                                data.page.updateTime,
                                '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑页面"><i class="fa fa-edit"></i></a>'+
                                '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除页面"><i class="fa fa-trash-o"></i></a>'
                            ]).draw();

                            _last_index = _table_page.rows()[0].length-1;

                            var $tr = $(_table_page.row(_last_index).node());

                            var $cells = $tr.find('td');

                            var $checkbox = $($cells[0]);

                            //初始化复选框
                            commons.checkbox.init({
                                selector:'[name=page]',
                                content:$checkbox,
                                doCheckall:true
                            });

                            $($cells[4]).addClass('center');

                            $($cells[5]).addClass('operation').addClass('center');

                        }, 403);

                    });

                    //取消按钮
                    $tr.on('click.cancel', '.cancel', function(){
                        $(this).tooltip('hide');
                        _table_page.rows(_last_index).remove().draw();
                        _unsaved = false;
                    });

                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _pageIds = [];
                $table.find('input[name=page]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _pageIds.push($checkbox.val());
                    }
                });

                var _uri = 'layout/page/remove/' + context.model.user.id + '/' + layout.id;

                commons.ajax.delete(_uri, {

                    pageIds:_pageIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_pageIds.length+'条数据。');

                    _table_page.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    }

    //初始化版块
    var _init_section = function($content, layout, page){
        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的版块，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'layout/page/section/get/' + context.model.user.id + '/' + layout.id + '/' + page.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_section.render({
                layout:layout.name,
                page:page.name,
                sections:data.sections
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_section = $table.DataTable({
                "order":[
                    [6, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 7]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=section]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        hasSelected = true;
                        return false;
                    }
                });
                if(hasSelected){
                    $button_remove.removeAttr('disabled');
                }else{
                    $button_remove.attr('disabled', 'true');
                }
            }

            //初始化checkbox
            commons.checkbox.init({
                selector:'input[name=section]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=section]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=section]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //版块列表
            $table.on('click.show_filter', 'td .filter-show', function(){
                var $source = $(this);
                $source.tooltip('hide');
                var $tr = $source.closest('tr');
                var $cells = $tr.find('td');
                var $checkbox = $($cells[0]);
                var $name = $($cells[1]);
                _init_filter($content, layout, page, {
                    id:$checkbox.find('[name=section]').val(),
                    name:$name.text()
                })
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_section.row.add([
                    '-',
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control section-title" placeholder="版块标题" required style="width:100%; border-radius:4px;"/></div>',
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control section-summary" placeholder="版块简介" required style="width:100%; border-radius:4px;"/></div>',
                    '<select class="form-control section-type" style="border-radius:4px; width:100%;"><option value="TEXT">文本</option><option value="PRODUCT">商品</option><option value="CHAT">聊天室</option><option value="ADVERTISEMENT">广告</option></select>',
                    '<select class="form-control section-template" style="border-radius:4px; width:100%;"><option value="tours">tours</option></select>',
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control section-remarks" placeholder="版块备注" required style="width:100%; border-radius:4px;"/></div>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_section.rows()[0].length-1;

                var $tr = $(_table_section.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                $($cells[3]).addClass('center');

                $($cells[4]).addClass('center');

                $($cells[6]).addClass('center');

                $($cells[7]).addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'layout/page/section/add/' + context.model.user.id + '/' + layout.id + '/' + page.id;

                    commons.ajax.post(_uri, {

                        title:$tr.find('.section-title').val(),
                        summary:$tr.find('.section-summary').val(),
                        type:$tr.find('.section-type').val(),
                        template:$tr.find('.section-template').val(),
                        remarks:$tr.find('.section-remarks').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_section.rows()[0].length-1;

                        //删除编辑行
                        _table_section.rows(_last_index).remove();

                        //新增数据行
                        _table_section.row.add([
                            '<input type="checkbox" name="section" class="flat-red" value="'+data.section.id+'"/>',
                            '<a class="filter-show">'+data.section.title+'</a>',
                            data.section.summary,
                            data.section.type,
                            data.section.template,
                            data.section.remarks,
                            data.section.updateTime,
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑版块"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除版块"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_section.rows()[0].length-1;

                        var $tr = $(_table_section.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=section]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[3]).addClass('center');

                        $($cells[4]).addClass('center');

                        $($cells[6]).addClass('center');

                        $($cells[7]).addClass('operation').addClass('center');

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_section.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _sectionIds = [];
                $table.find('input[name=section]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _sectionIds.push($checkbox.val());
                    }
                });

                var _uri = 'layout/page/section/remove/' + context.model.user.id + '/' + layout.id + '/' + page.id;

                commons.ajax.delete(_uri, {

                    sectionIds:_sectionIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_sectionIds.length+'条数据。');

                    _table_section.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    }

    //初始化过滤器
    var _init_filter = function($content, layout, page, section){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的版块，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'layout/page/section/filter/get/' + context.model.user.id + '/' + layout.id + '/' + page.id + '/' + section.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_filter.render({
                layout:layout.name,
                page:page.name,
                section:section.name,
                filters:data.filters
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_filter = $table.DataTable({
                "order":[
                    [6, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 7]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=filter]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        hasSelected = true;
                        return false;
                    }
                });
                if(hasSelected){
                    $button_remove.removeAttr('disabled');
                }else{
                    $button_remove.attr('disabled', 'true');
                }
            }

            //初始化checkbox
            commons.checkbox.init({
                selector:'input[name=filter]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=filter]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=filter]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _uri = 'category/get/' + context.model.user.id;

                commons.ajax.get(_uri, null, function(data){

                    _unsaved = true;

                    var categories = data.categories;

                    if(!categories || categories.length<=0){
                        commons.messanger.info('当前系统中还没有类目，请先创建类目！');
                        return;
                    }

                    var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                    _table_filter.row.add([
                        '-',
                        '<select class="form-control filter-type" style="border-radius:4px; width:100%;"><option value="PRODUCTTYPE">商品类型</option><option value="TAG">标签</option><option value="TIME">上架时间</option><option value="NUM">数量</option></select>',
                        commons.select.get({class:'filter-operator', style:'border-radius:4px; width:100%;', data:operator_type}),
                        commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:categories}),
                        '',
                        '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control filter-remarks" placeholder="备注" required style="width:100%; border-radius:4px;"/></div>',
                        date,
                        '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                        '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                    ]).draw();

                    var _last_index = _table_filter.rows()[0].length-1;

                    var $tr = $(_table_filter.row(_last_index).node());

                    var $cells = $tr.find('td');

                    $($cells[0]).addClass('center');

                    var $filter_type = $($cells[1]);

                    var $operator = $($cells[2]);

                    var $value_1 = $($cells[3]);

                    var $value_2 = $($cells[4]);

                    $($cells[6]).addClass('center');

                    $($cells[7]).addClass('operation').addClass('center');

                    //初始化绑定事件
                    $operator.find('select').on('change', function(){

                        var $this = $(this);
                        var _operator = $this.val();

                        if(_operator==='EQ' || _operator==='NE'){
                            _table_filter.cell(_table_filter.rows()[0].length-1, 3).data(commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:data.categories}));
                        }else if(_operator==='IN' || _operator==='NIN'){
                            _table_filter.cell(_table_filter.rows()[0].length-1, 3).data('<input type="text" class="form-control filter-value_1" readonly  required style="width:100%; border-radius:4px;"/>');

                            commons.checkbox.group.init({
                                selector:'.filter-value_1',
                                context:$value_1,
                                title:'请选择商品类型',
                                column:3,
                                data:categories
                            });
                        }

                    });

                    //三级联动
                    $filter_type.on('change', 'select', function(){

                        var $this = $(this);
                        var _filter_type = $this.val();

                        if(_filter_type === 'PRODUCTTYPE'){

                            _uri = 'menu/category/get/' + context.model.user.id;

                            commons.ajax.get(_uri, null, function(data){

                                _table_filter.cell(_table_filter.rows()[0].length-1, 2).data(commons.select.get({class:'filter-operator', style:'border-radius:4px; width:100%;', data:operator_type}));
                                _table_filter.cell(_table_filter.rows()[0].length-1, 3).data(commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:data.categories}));

                                $operator.find('select').on('change', function(){

                                    var $this = $(this);
                                    var _operator = $this.val();

                                    if(_operator==='EQ' || _operator==='NE'){
                                        _table_filter.cell(_table_filter.rows()[0].length-1, 3).data(commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:data.categories}));
                                    }else if(_operator==='IN' || _operator==='NIN'){
                                        _table_filter.cell(_table_filter.rows()[0].length-1, 3).data('<input type="text" class="form-control filter-value_1" readonly  required style="width:100%; border-radius:4px;"/>');

                                        var $input = $value_1.find('input');
                                        commons.checkbox.group.init({
                                            selector:'.filter-value_1',
                                            context:$value_1,
                                            title:'请选择商品类型',
                                            column:3,
                                            data:data.categories
                                        });
                                    }

                                });

                            });

                        }else if(_filter_type === 'TAG'){

                            _uri = 'tag/get/' + context.model.user.id;

                            commons.ajax.get(_uri, null, function(data){

                                _table_filter.cell(_table_filter.rows()[0].length-1, 2).data(commons.select.get({class:'filter-operator', style:'border-radius:4px; width:100%;', data:operator_type}));
                                _table_filter.cell(_table_filter.rows()[0].length-1, 3).data(commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:data.tags}));

                                $operator.find('select').on('change', function(){

                                    var $this = $(this);
                                    var _operator = $this.val();

                                    if(_operator==='EQ' || _operator==='NE'){
                                        _table_filter.cell(_table_filter.rows()[0].length-1, 3).data(commons.select.get({class:'filter-value_1', style:'border-radius:4px; width:100%;', data:data.tags}));
                                    }else if(_operator==='IN' || _operator==='NIN'){
                                        _table_filter.cell(_table_filter.rows()[0].length-1, 3).data('<input type="text" class="form-control filter-value_1" readonly  required style="width:100%; border-radius:4px;"/>');

                                        var $input = $value_1.find('input');
                                        commons.checkbox.group.init({
                                            selector:'.filter-value_1',
                                            context:$value_1,
                                            title:'请选择商品类型',
                                            column:3,
                                            data:data.tags
                                        });
                                    }

                                });

                            });

                        }else if(_filter_type === 'TIME'){

                            _table_filter.cell(_table_filter.rows()[0].length-1, 2).data(commons.select.get({class:'filter-operator', style:'border-radius:4px; width:100%;', data:operator_date}));
                            _table_filter.cell(_table_filter.rows()[0].length-1, 3).data('<input type="text" class="form-control filter-value_1" data-inputmask="\'alias\': \'yyyy-mm-dd\'" data-mask required style="width:100%; border-radius:4px;"/>');
                            $value_1.find('input').inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});

                            $operator.on('change', 'select', function(){

                                var $this = $(this);
                                var _operator = $this.val();

                                if(_operator==='LIKE' || _operator==='LT' || _operator==='GT' || _operator==='LE' || _operator==='GE'){
                                    _table_filter.cell(_table_filter.rows()[0].length-1, 4).data('');
                                }else if(_operator === 'BETWEEN'){
                                    _table_filter.cell(_table_filter.rows()[0].length-1, 4).data('<input type="text" class="form-control filter-value_2" data-inputmask="\'alias\': \'yyyy-mm-dd\'" data-mask required style="width:100%; border-radius:4px;"/>');
                                    $value_2.find('input').inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
                                }

                            });

                        }else if(_filter_type === 'NUM'){

                            _table_filter.cell(_table_filter.rows()[0].length-1, 2).data('');
                            _table_filter.cell(_table_filter.rows()[0].length-1, 3).data('<input type="text" class="form-control filter-value_1" placeholder="查询前n条数据" required style="width:100%; border-radius:4px;"/>');

                        }

                    });

                    //保存按钮
                    $tr.on('click.save', '.save', function(){

                        var $save = $(this);

                        var _uri = 'layout/page/section/filter/add/' + context.model.user.id + '/' + layout.id + '/' + page.id + '/' +section.id;

                        var $value_1 = $tr.find('.filter-value_1');
                        var key_1 = '';
                        var value_1 = '';
                        if($value_1.is('input.checkgroup')){
                            var key_arr = commons.checkbox.group.getChecked($value_1);
                            for(var i=0,len=key_arr.length-1; i<=len; i++){
                                key_1 += key_arr[i].id;
                                if(i !== len){
                                    key_1 += ',';
                                }
                            }
                            value_1 = $value_1.val();
                        }else if($value_1.is('select')){
                            key_1 = $value_1.val();
                            $value_1.find('option').each(function(){
                                var $option = $(this);
                                var _val = $option.attr('value');
                                if(_val === key_1){
                                    value_1 = $option.text();
                                    return false;
                                }
                            });
                        }else{
                            value_1 = $value_1.val();
                        }

                        var value_2 = '';
                        var $value_2 = $tr.find('.filter-value_2');
                        if($value_2[0]){
                            value_2 = $value_2.val();
                        }

                        commons.ajax.post(_uri, {

                            remarks:$tr.find('.filter-remarks').val(),
                            type:$tr.find('.filter-type').val(),
                            operator:$tr.find('.filter-operator').val(),
                            key_1:key_1,
                            value_1:value_1,
                            value_2:value_2

                        }, function(data, status){

                            if(status === 403) {
                                return;
                            }

                            _unsaved = false;

                            $save.tooltip('hide');

                            var _last_index = _table_filter.rows()[0].length-1;

                            //删除编辑行
                            _table_filter.rows(_last_index).remove();

                            //新增数据行
                            _table_filter.row.add([
                                '<input type="checkbox" name="filter" class="flat-red" value="'+data.filter.id+'"/>',
                                data.filter.type,
                                data.filter.operator,
                                data.filter.value_1,
                                data.filter.value_2,
                                data.filter.remarks,
                                data.filter.updateTime,
                                '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑过滤器"><i class="fa fa-edit"></i></a>'+
                                '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除过滤器"><i class="fa fa-trash-o"></i></a>'
                            ]).draw();

                            _last_index = _table_filter.rows()[0].length-1;

                            var $tr = $(_table_filter.row(_last_index).node());

                            var $cells = $tr.find('td');

                            var $checkbox = $($cells[0]);

                            //初始化复选框
                            commons.checkbox.init({
                                selector:'[name=filter]',
                                content:$checkbox,
                                doCheckall:true
                            });

                            $($cells[1]).addClass('center');

                            $($cells[6]).addClass('center');

                            $($cells[7]).addClass('operation').addClass('center');

                        }, 403);

                    });

                    //取消按钮
                    $tr.on('click.cancel', '.cancel', function(){
                        $(this).tooltip('hide');
                        _table_filter.rows(_last_index).remove().draw();
                        _unsaved = false;
                    });
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _filterIds = [];
                $table.find('input[name=filter]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _filterIds.push($checkbox.val());
                    }
                });

                var _uri = 'layout/page/section/filter/remove/' + context.model.user.id + '/' + layout.id + '/' + page.id + '/' + section.id;

                commons.ajax.delete(_uri, {

                    filterIds:_filterIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_filterIds.length+'条数据。');

                    _table_filter.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    }


    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(html_header).render({
            level_1:'布局管理工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                }
            ]
        }));

        var $wrapper = $('<div></div>');
        $content.append($wrapper);

        //初始化布局列表
        _init_layout($wrapper);

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
