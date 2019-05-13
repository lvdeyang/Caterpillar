/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'fleet/tpl/tpl-fleet.html',
    'juicer',
    'context',
    'page',
    'restfull',
    'alt-checkbox',
    'alt-messenger'

], function(header, tpl, juicer, context, page, ajax, $){

    var prepire_header = juicer(header),
        prepire_tpl = juicer(tpl);

    var PAGENAME = 'fleet';

    var _init = function(){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                $.fn['alt-messenger']('info', '当前有未保存的车队，请先保存!');
                return false;
            }
            return true;
        }

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(prepire_header.render({
            level_1:'xxxxx',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'车队',
                    icon:'#/fleet'
                }
            ]
        }));

        var uri = '/fleet/query/all';

        ajax.query(uri, null, function(data){

            var $body = $(prepire_tpl.render({fleets:data}));
            $content.append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery = $table.DataTable({
                "order":[
                    [3, 'desc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 4]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=fleet]').each(function(){
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

            $.fn['alt-checkbox']('create', {
                selector:'[name=fleet]',
                context:$('#fleet-table'),
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=fleet]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=fleet]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            $table.on('click.edit', '.operation>.edit', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $name = $($cells[1]),
                	$mobile = $($cells[2]),
                    $oparation = $($cells[4]),
                    $edit = $oparation.find('.edit'),
                    $remove = $oparation.find('.remove');

                var $name_input = $('<input type="text" class="form-control fleet-name" placeholder="车队名" required style="width:100%; border-radius:4px;"/>').val($name.text().trim()).data('hidden', $name.text().trim());
                var $mobile_input = $('<input type="text" class="form-control fleet-mobile" placeholder="队长电话" required style="width:100%; border-radius:4px;"/>').val($mobile.text().trim()).data('hidden', $mobile.text().trim());
                
                var $save = $('<a class="edit-save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>'),
                    $cancel = $('<a class="edit-cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>');

                $edit.tooltip('hide').remove();
                $remove.tooltip('hide').remove();

                $name.text('').append($name_input);
                $mobile.text('').append($mobile_input);

                $oparation.append($save).append($cancel);

            });

            $table.on('click.edit.save', '.operation>.edit-save', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $checkbox = $($cells[0]).find('input'),
                    $name = $($cells[1]),
                    $mobile = $($cells[2]),
                    $updateTime = $($cells[3]),
                    $name_input = $name.find('input'),
                    $mobile_input = $mobile.find('input'),
                    $operation = $($cells[4]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var _uri = 'fleet/update/' + $checkbox.val();

                ajax.update(_uri, {

                    name:$name_input.val().trim(),
                    mobile:$mobile_input.val().trim()

                }, function(data, status){

                    if(status === 200){

                        $name_input.remove();
                        $mobile_input.remove();
                        $edit_save.tooltip('hide').remove();
                        $cancel.tooltip('hide').remove();
                        $name.text(data.name);
                        $mobile.text(data.mobile);
                        $updateTime.text(data.updateTime);

                        $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑车队"><i class="fa fa-edit"></i></a>')
                                  .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除车队"><i class="fa fa-trash-o"></i></a>');

                        $.fn['alt-messenger']('success', '修改成功');

                    }

                });

            });

            $table.on('click.edit.cancel', '.operation>.edit-cancel', function(){

                var $cancel = $(this),
                    $tr = $cancel.closest('tr'),
                    $cells = $tr.find('td');

                var $name = $($cells[1]),
                    $name_input = $name.find('input'),
                    $mobile = $($cells[2]),
                    $mobile_input = $mobile.find('input'),
                    $operation = $($cells[4]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var bak_name = $name_input.data('hidden');
                $name.empty().text(bak_name);
                
                var bak_mobile = $mobile_input.data('hidden');
                $mobile.empty().text(bak_mobile);

                $edit_save.tooltip('hide').remove();
                $cancel.tooltip('hide').remove();

                $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑车队"><i class="fa fa-edit"></i></a>')
                          .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除车队"><i class="fa fa-trash-o"></i></a>');

            });

            $table.on('click.remove', '.operation>.remove', function(){

                var $remove = $(this),
                    $tr = $remove.closest('tr'),
                    $checkbox = $tr.find('input[name=fleet]'),
                    _fleetId = $checkbox.val(),
                    _uri = 'fleet/delete/' + _fleetId;

                $tr.addClass('selected');

                ajax.remove(_uri, null, function(data, status){

                    if(status !== 200) $tr.removeClass('selected');

                    $.fn['alt-messenger']('success', '操作成功');

                    $remove.tooltip('hide');

                    _table_scenery.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                if(!doable()) return;

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_scenery.row.add([
                    '-',
                    '<input type="text" class="form-control fleet-name" placeholder="车队名称" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control fleet-mobile" placeholder="队长电话" required style="width:100%; border-radius:4px;"/>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery.rows()[0].length-1;

                var $tr = $(_table_scenery.row(_last_index).node());

                var $cells = $tr.find('td');

                var $operation = $($cells[4]);

                $($cells[0]).addClass('center');

                $($cells[3]).addClass('center');

                $operation.addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'fleet/add/';

                    ajax.add(_uri, {

                        name:$tr.find('.fleet-name').val(),
                        mobile:$tr.find('.fleet-mobile').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_scenery.rows()[0].length-1;

                        //删除编辑行
                        _table_scenery.rows(_last_index).remove();

                        //新增数据行
                        _table_scenery.row.add([
                            '<input type="checkbox" name="fleet" class="flat-red" value="'+data.id+'"/>',
                            data.name,
                            data.mobile,
                            data.updateTime,
                            '<a class="edit" data-placement="bottom" title="编辑车队"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除车队"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery.rows()[0].length-1;

                        var $tr = $(_table_scenery.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        $($cells[3]).addClass('center');

                        var $options = $($cells[4]);

                        //初始化复选框
                        $.fn['alt-checkbox']('create', {
                            selector:'[name=fleet]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $options.addClass('operation').addClass('center');

                        $tr.find('.operation>a').tooltip();

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_scenery.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _fleetIds = [];
                $table.find('input[name=fleet]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _fleetIds.push($checkbox.val());
                    }
                });

                var _uri = 'fleet/delete';

                ajax.remove(_uri, {

                	ids:_fleetIds

                }, function(data){

                    $.fn['alt-messenger']('success', '操作成功，当前删除：'+_fleetIds.length+'条数据。');

                    _table_scenery.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        });

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
