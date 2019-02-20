/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'car/tpl/tpl-car.html',
    'juicer',
    'context',
    'page',
    'restfull',
    'alt-checkbox',
    'alt-messenger'

], function(header, tpl, juicer, context, page, ajax, $){

    var prepire_header = juicer(header),
        prepire_tpl = juicer(tpl);

    var PAGENAME = 'car';

    var _init = function(){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                $.fn['alt-messenger']('info', '当前有未保存的车辆，请先保存!');
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
                    name:'车辆',
                    icon:'#/car'
                }
            ]
        }));

        var uri = '/car/query/all';

        ajax.query(uri, null, function(data){

            var $body = $(prepire_tpl.render({cars:data}));
            $content.append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery = $table.DataTable({
                "order":[
                    [4, 'desc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 5]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=car]').each(function(){
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
                selector:'[name=car]',
                context:$('#car-table'),
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=car]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=car]', function(){
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

                var $type = $($cells[1]),
                	$plateNumber = $($cells[2]),
                	$seats = $($cells[3]),
                    $oparation = $($cells[5]),
                    $edit = $oparation.find('.edit'),
                    $remove = $oparation.find('.remove');

                var $type_input = $('<input type="text" class="form-control car-type" placeholder="车型" required style="width:100%; border-radius:4px;"/>').val($type.text().trim()).data('hidden', $type.text().trim());
                var $plateNumber_input = $('<input type="text" class="form-control car-plateNumber" placeholder="车牌号" required style="width:100%; border-radius:4px;"/>').val($plateNumber.text().trim()).data('hidden', $plateNumber.text().trim());
                var $seats_input = $('<input type="text" class="form-control car-seats" placeholder="座位数" required style="width:100%; border-radius:4px;"/>').val($seats.text().trim()).data('hidden', $seats.text().trim());
                
                var $save = $('<a class="edit-save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>'),
                    $cancel = $('<a class="edit-cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>');

                $edit.tooltip('hide').remove();
                $remove.tooltip('hide').remove();

                $type.text('').append($type_input);
                $plateNumber.text('').append($plateNumber_input);
                $seats.text('').append($seats_input);

                $oparation.append($save).append($cancel);

            });

            $table.on('click.edit.save', '.operation>.edit-save', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $checkbox = $($cells[0]).find('input'),
                    $type = $($cells[1]),
                    $plateNumber = $($cells[2]),
                    $seats = $($cells[3]),
                    $updateTime = $($cells[4]),
                    $type_input = $type.find('input'),
                    $plateNumber_input = $plateNumber.find('input'),
                    $seats_input = $seats.find('input'),
                    $operation = $($cells[5]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var _uri = 'car/update/' + $checkbox.val();

                ajax.update(_uri, {

                    type:$type_input.val().trim(),
                    plateNumber:$plateNumber_input.val().trim(),
                    seats:$seats_input.val().trim()

                }, function(data, status){

                    if(status === 200){

                    	$type_input.remove();
                    	$plateNumber_input.remove();
                    	$seats_input.remove();
                        $edit_save.tooltip('hide').remove();
                        $cancel.tooltip('hide').remove();
                        
                        $type.text(data.type);
                        $plateNumber.text(data.plateNumber);
                        $seats.text(data.seats);
                        $updateTime.text(data.updateTime);

                        $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑车辆"><i class="fa fa-edit"></i></a>')
                                  .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除车辆"><i class="fa fa-trash-o"></i></a>');

                        $.fn['alt-messenger']('success', '修改成功');

                    }

                });

            });

            $table.on('click.edit.cancel', '.operation>.edit-cancel', function(){

                var $cancel = $(this),
                    $tr = $cancel.closest('tr'),
                    $cells = $tr.find('td');

                var $type = $($cells[1]),
                    $type_input = $type.find('input'),
                    $plateNumber = $($cells[2]),
                    $plateNumber_input = $plateNumber.find('input'),
                    $seats = $($cells[3]),
                    $seats_input = $seats.find('input'),
                    $operation = $($cells[5]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var bak_type = $type_input.data('hidden');
                $type.empty().text(bak_type);
                
                var bak_plateNumber = $plateNumber_input.data('hidden');
                $plateNumber.empty().text(bak_plateNumber);

                var bak_seats = $seats_input.data('hidden');
                $seats.empty().text(bak_seats);
                
                $edit_save.tooltip('hide').remove();
                $cancel.tooltip('hide').remove();

                $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑车辆"><i class="fa fa-edit"></i></a>')
                          .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除车辆"><i class="fa fa-trash-o"></i></a>');

            });

            $table.on('click.remove', '.operation>.remove', function(){

                var $remove = $(this),
                    $tr = $remove.closest('tr'),
                    $checkbox = $tr.find('input[name=car]'),
                    _carId = $checkbox.val(),
                    _uri = 'car/delete/' + _carId;

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
                    '<input type="text" class="form-control car-type" placeholder="车辆" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control car-plateNumber" placeholder="车牌号" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control car-seats" placeholder="座位号" required style="width:100%; border-radius:4px;"/>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery.rows()[0].length-1;

                var $tr = $(_table_scenery.row(_last_index).node());

                var $cells = $tr.find('td');

                var $operation = $($cells[5]);

                $($cells[0]).addClass('center');

                $($cells[4]).addClass('center');

                $operation.addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'car/add/';

                    ajax.add(_uri, {

                        type:$tr.find('.car-type').val(),
                        plateNumber:$tr.find('.car-plateNumber').val(),
                        seats:$tr.find('.car-seats').val()

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
                            '<input type="checkbox" name="car" class="flat-red" value="'+data.id+'"/>',
                            data.type,
                            data.plateNumber,
                            data.seats,
                            data.updateTime,
                            '<a class="edit" data-placement="bottom" title="编辑车辆"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除车辆"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery.rows()[0].length-1;

                        var $tr = $(_table_scenery.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        $($cells[4]).addClass('center');

                        var $options = $($cells[5]);

                        //初始化复选框
                        $.fn['alt-checkbox']('create', {
                            selector:'[name=car]',
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
                var _carIds = [];
                $table.find('input[name=car]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _carIds.push($checkbox.val());
                    }
                });

                var _uri = 'car/delete';

                ajax.remove(_uri, {

                	ids:_carIds

                }, function(data){

                    $.fn['alt-messenger']('success', '操作成功，当前删除：'+_carIds.length+'条数据。');

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
