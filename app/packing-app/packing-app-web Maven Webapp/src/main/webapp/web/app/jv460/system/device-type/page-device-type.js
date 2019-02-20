/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!app/jv460/system/device-type/tpl/tpl-device-type.html',
    'juicer',
    'context',
    'page',
    'restfull',
    'checkbox',
    'select'

], function(header, tpl, juicer, context, page, ajax, $){

    var prepire_header = juicer(header),
        prepire_tpl = juicer(tpl);

    var PAGENAME = 'device-type';

    var _init = function(){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                $.fn.messenger('info', '当前有未保存的景点，请先保存!');
                return false;
            }
            return true;
        }

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(prepire_header.render({
            level_1:'可视化移动终端模板编辑工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'设备类型',
                    icon:'fa-home'
                }
            ]
        }));

        var uri = '/device/type/query/all';

        ajax.query(uri, null, function(data){

            var $body = $(prepire_tpl.render({types:data}));
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
                $table.find('input[name=device-type]').each(function(){
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

            $.fn.checkbox('create', {
                selector:'[name=device-type]',
                context:$('#device-type-table'),
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=device-type]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=device-type]', function(){
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
                    $hidden = $($cells[2]),
                    $oparation = $($cells[4]),
                    $edit = $oparation.find('.edit'),
                    $remove = $oparation.find('.remove');

                var options = [{key:'EXT9370', value:'EXT9370'}];
                for(var i in options){
                    if(options[i].key === $hidden.text().trim()){
                        options[i].selected = true;
                        break;
                    }
                }

                var $name_input = $('<input type="text" class="form-control device-type-name" placeholder="设备类型名称" required style="width:100%; border-radius:4px;"/>').val($name.text().trim()).data('hidden', $name.text().trim()),
                    $hidden_input = $($.fn.select('create', {html:true, style:'device-type-hidden', options:options})).data('hidden', $hidden.text().trim());

                var $save = $('<a class="edit-save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>'),
                    $cancel = $('<a class="edit-cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>');

                $edit.tooltip('hide').remove();
                $remove.tooltip('hide').remove();

                $name.text('').append($name_input);
                $hidden.text('').append($hidden_input);

                $oparation.append($save).append($cancel);

            });

            $table.on('click.edit.save', '.operation>.edit-save', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $checkbox = $($cells[0]).find('input'),
                    $name = $($cells[1]),
                    $hidden = $($cells[2]),
                    $updateTime = $($cells[3]),
                    $name_input = $name.find('input'),
                    $hidden_select = $hidden.find('.ui-select'),
                    $operation = $($cells[4]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var _uri = '/device/type/update/' + $checkbox.val();

                ajax.update(_uri, {

                    name:$name_input.val().trim(),
                    hidden:$hidden_select.select('val').trim()

                }, function(data, status){

                    if(status === 200){

                        $name_input.remove();
                        $hidden_select.remove();
                        $edit_save.tooltip('hide').remove();
                        $cancel.tooltip('hide').remove();
                        $name.text(data.name);
                        $hidden.text(data.hidden);
                        $updateTime.text(data.updateTime);

                        $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑设备类型"><i class="fa fa-edit"></i></a>')
                                  .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除设备类型"><i class="fa fa-trash-o"></i></a>');

                        $.fn.messenger('success', '修改成功');

                    }

                });

            });

            $table.on('click.edit.cancel', '.operation>.edit-cancel', function(){

                var $cancel = $(this),
                    $tr = $cancel.closest('tr'),
                    $cells = $tr.find('td');

                var $name = $($cells[1]),
                    $hidden = $($cells[2]),
                    $name_input = $name.find('input'),
                    $hidden_select = $hidden.find('.ui-select'),
                    $operation = $($cells[4]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var bak_name = $name_input.data('hidden');
                var bak_hidden = $hidden_select.data('hidden');
                $name.empty().text(bak_name);
                $hidden.empty().text(bak_hidden);

                $edit_save.tooltip('hide').remove();
                $cancel.tooltip('hide').remove();

                $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑设备类型"><i class="fa fa-edit"></i></a>')
                          .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除设备类型"><i class="fa fa-trash-o"></i></a>');

            });

            $table.on('click.remove', '.operation>.remove', function(){

                var $remove = $(this),
                    $tr = $remove.closest('tr'),
                    $checkbox = $tr.find('input[name=device-type]'),
                    _deviceTypeId = $checkbox.val(),
                    _uri = '/device/type/delete/' + _deviceTypeId;

                $tr.addClass('selected');

                ajax.remove(_uri, null, function(data, status){

                    if(status !== 200) $tr.removeClass('selected');

                    $.fn.messenger('success', '操作成功');

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
                    '<input type="text" class="form-control device-type-name" placeholder="设备类型名称" required style="width:100%; border-radius:4px;"/>',
                    $.fn.select('create', {html:true, style:'device-type-hidden', options:[{key:'EXT9370', value:'EXT9370'}]}),
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

                    var _uri = '/device/type/add/';

                    ajax.add(_uri, {

                        name:$tr.find('.device-type-name').val(),
                        hidden:$tr.find('.device-type-hidden').select('val')

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
                            '<input type="checkbox" name="device-type" class="flat-red" value="'+data.id+'"/>',
                            '<a data-toggle="tooltip" data-placement="bottom">' + data.name +'</a>',
                            '<a data-toggle="tooltip" data-placement="bottom">' + (String.prototype.isNull(data.hidden)?'':data.hidden) +'</a>',
                            data.updateTime,
                            '<a class="edit" data-placement="bottom" title="编辑设备类型"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除设备类型"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery.rows()[0].length-1;

                        var $tr = $(_table_scenery.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        $($cells[3]).addClass('center');

                        var $options = $($cells[4]);

                        //初始化复选框
                        $.fn.checkbox('create', {
                            selector:'[name=device-type]',
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
                var _deviceTypeIds = [];
                $table.find('input[name=device-type]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _deviceTypeIds.push($checkbox.val());
                    }
                });

                var _uri = '/device/type/delete';

                ajax.remove(_uri, {

                    ids:_deviceTypeIds

                }, function(data){

                    $.fn.messenger('success', '操作成功，当前删除：'+_deviceTypeIds.length+'条数据。');

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
