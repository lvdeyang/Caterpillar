/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-menu-body',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons'

], function(header, body, juicer, context, page, $, commons){

    var PAGENAME = 'menu';

    var prepare_body = juicer(body);

    var _unsaved = false;

    var doable = function(){
        if(_unsaved === true){
            commons.messanger.info('当前有未保存的栏目，请先保存!');
            return false;
        }
        return true;
    }

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:876px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'栏目管理工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'菜单'
                }
            ]
        }));

        var _uri = 'menu/get/' + context.model.user.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_body.render({
                menus:data.menus
            }));
            $content.append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_menu = $table.DataTable({
                "order":[
                    [3, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 4]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=menu]').each(function(){
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
                selector:'input[name=menu]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=menu]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=menu]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_menu.row.add([
                    '-',
                    '<input type="text" class="form-control menu-name" placeholder="菜单名称" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control menu-summary" placeholder="栏目简介" required style="width:100%; border-radius:4px;"/>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_menu.rows()[0].length-1;

                var $tr = $(_table_menu.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                $($cells[3]).addClass('center');

                $($cells[4]).addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'menu/add/' + context.model.user.id;

                    commons.ajax.post(_uri, {

                        name:$tr.find('.menu-name').val(),
                        summary:$tr.find('.menu-summary').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_menu.rows()[0].length-1;

                        //删除编辑行
                        _table_menu.rows(_last_index).remove();

                        //新增数据行
                        _table_menu.row.add([
                            '<input type="checkbox" name="menu" class="flat-red" value="'+data.menu.id+'"/>',
                            data.menu.name,
                            data.menu.summary,
                            data.menu.updateTime,
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑栏目"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除栏目"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_menu.rows()[0].length-1;

                        var $tr = $(_table_menu.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=menu]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[3]).addClass('center');

                        $($cells[4]).addClass('operation').addClass('center');


                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_menu.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _menuIds = [];
                $table.find('input[name=menu]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _menuIds.push($checkbox.val());
                    }
                });

                var _uri = 'menu/remove/' + context.model.user.id

                commons.ajax.delete(_uri, {

                    menuIds:_menuIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_menuIds.length+'条数据。');

                    _table_menu.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
