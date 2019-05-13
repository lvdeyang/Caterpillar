/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'route/tpl/tpl-route.html',
    'juicer',
    'context',
    'page',
    'restfull',
    window.APPPATH + 'fleet/component/fleet-modal',
    window.APPPATH + 'site/component/site-modal',
    'alt-checkbox',
    'alt-messenger'

], function(header, tpl, juicer, context, page, ajax, FleetWindow, SiteWindow, $){

    var prepire_header = juicer(header),
        prepire_tpl = juicer(tpl);

    var PAGENAME = 'route';

    var _init = function(){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                $.fn['alt-messenger']('info', '当前有未保存的路线，请先保存!');
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
                    name:'路线',
                    icon:'#/route'
                }
            ]
        }));

        var uri = '/route/query/all';

        ajax.query(uri, null, function(data){

            var $body = $(prepire_tpl.render({routes:data}));
            $content.append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery = $table.DataTable({
                "order":[
                    [6, 'desc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 7]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=route]').each(function(){
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
                selector:'[name=route]',
                context:$('#route-table'),
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=route]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=route]', function(){
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

                var $departure = $($cells[1]),
                	$destination = $($cells[2]),
                	$carpoolPrice = $($cells[3]),
                	$charterPrice = $($cells[4]),
                	$fleet = $($cells[5]),
                    $oparation = $($cells[7]),
                    $edit = $oparation.find('.edit'),
                    $remove = $oparation.find('.remove');

                var $departureName = $('<input type="text" class="form-control departure" placeholder="始发站" required readonly style="width:100%; border-radius:4px;"/>').val($departure.text().trim()).data('hidden', $departure.text().trim());
                var $departureId = $departure.find('.route-departure').attr('data-id', $departure.find('.route-departure').val());
                
                var $destinationName = $('<input type="text" class="form-control destination" placeholder="终点站" required readonly style="width:100%; border-radius:4px;"/>').val($destination.text().trim()).data('hidden', $destination.text().trim());
                var $destinationId = $destination.find('.route-destination').attr('data-id', $destination.find('.route-destination').val());
                
                var $carpoolPrice_input = $('<input type="text" class="form-control route-carpoolPrice" placeholder="拼车价格" required style="width:100%; border-radius:4px;"/>').val($carpoolPrice.text().trim()).data('hidden', $carpoolPrice.text().trim());
                var $charterPrice_input = $('<input type="text" class="form-control route-charterPrice" placeholder="包车价格" required style="width:100%; border-radius:4px;"/>').val($charterPrice.text().trim()).data('hidden', $charterPrice.text().trim());
                
                var $fleetName = $('<input type="text" class="form-control fleet" placeholder="车队" required readonly style="width:100%; border-radius:4px;"/>').val($fleet.text().trim()).data('hidden', $fleet.text().trim());
                var $fleetId = $fleet.find('.route-fleet').attr('data-id', $fleet.find('.route-fleet').val());
                
                var $save = $('<a class="edit-save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>'),
                    $cancel = $('<a class="edit-cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>');

                $edit.tooltip('hide').remove();
                $remove.tooltip('hide').remove();

                $departure.empty().append($departureName).append($departureId);
            	$destination.empty().append($destinationName).append($destinationId);
            	$carpoolPrice.empty().append($carpoolPrice_input);
            	$charterPrice.empty().append($charterPrice_input);
            	$fleet.empty().append($fleetName).append($fleetId);

                $oparation.append($save).append($cancel);
                
                //绑定事件
                $tr.find('.departure').on('click', departureWindow);
                $tr.find('.destination').on('click', destinationWindow);
                $tr.find('.fleet').on('click', fleetWindow);

            });

            $table.on('click.edit.save', '.operation>.edit-save', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $checkbox = $($cells[0]).find('input'),
	                $departure = $($cells[1]),
	            	$destination = $($cells[2]),
	            	$carpoolPrice = $($cells[3]),
	            	$charterPrice = $($cells[4]),
	            	$fleet = $($cells[5]),
                    $updateTime = $($cells[6]),
                    
                    $departure_input = $departure.find('.route-departure'),
                	$destination_input = $destination.find('.route-destination'),
                	$carpoolPrice_input = $carpoolPrice.find('.route-carpoolPrice'),
                	$charterPrice_input = $charterPrice.find('.route-charterPrice'),
                	$fleet_input = $fleet.find('.route-fleet'),
                    
                    $operation = $($cells[7]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var _uri = 'route/update/' + $checkbox.val();

                ajax.update(_uri, {

                	departureId:$departure_input.val(),
                	destinationId:$destination_input.val(),
                	carpoolPrice:$carpoolPrice_input.val().trim(),
                	charterPrice:$charterPrice_input.val().trim(),
                	fleetId:$fleet_input.val()

                }, function(data, status){

                    if(status === 200){

                        $edit_save.tooltip('hide').remove();
                        $cancel.tooltip('hide').remove();
                        $departure.text(data.departureName).append($departure_input.val(data.departureId));
                        $destination.text(data.destinationName).append($destination_input.val(data.destinationId));
    	            	$carpoolPrice.text(data.carpoolPrice);
    	            	$charterPrice.text(data.charterPrice);
    	            	$fleet.text(data.fleetName).append($fleet_input.val(data.fleetId));
                        $updateTime.text(data.updateTime);

                        $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑路线"><i class="fa fa-edit"></i></a>')
                                  .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除路线"><i class="fa fa-trash-o"></i></a>');

                        $.fn['alt-messenger']('success', '修改成功');

                    }

                });

            });

            $table.on('click.edit.cancel', '.operation>.edit-cancel', function(){

                var $cancel = $(this),
                    $tr = $cancel.closest('tr'),
                    $cells = $tr.find('td');

                var $departure = $($cells[1]),
                	$departureName = $departure.find('.departure'),
                	$departureId = $departure.find('.route-departure'), 
                	
                	$destination = $($cells[2]),
                	$destinationName = $destination.find('.destination'),
                	$destinationId = $destination.find('.route-destination'), 
                	
                	$carpoolPrice = $($cells[3]),
                	$carpoolPrice_input = $carpoolPrice.find('input'),
                	$charterPrice = $($cells[4]),
                	$charterPrice_input = $charterPrice.find('input'),
                	
                	$fleet = $($cells[5]),
                	$fleetName = $fleet.find('.fleet'),
                	$fleetId = $fleet.find('.route-fleet'), 
                	
                    $operation = $($cells[7]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var bak_departure = $departureName.data('hidden');
                var bak_departureId = $departureId.data('id');
                $departure.empty().text(bak_departure).append($departureId.val(bak_departureId));
                
                var bak_destination = $destinationName.data('hidden');
                var bak_destinationId = $destinationId.data('id');
                $destination.empty().text(bak_destination).append($destinationId.val(bak_destinationId));
                
                var bak_carpoolPrice = $carpoolPrice_input.data('hidden');
                $carpoolPrice.empty().text(bak_carpoolPrice);
                
                var bak_charterPrice = $charterPrice_input.data('hidden');
                $charterPrice.empty().text(bak_charterPrice);

                var bak_fleet = $fleetName.data('hidden');
                var bak_fleetId = $fleetId.data('id');
                $fleet.empty().text(bak_fleet).append($fleetId.val(bak_fleetId));
                
                $edit_save.tooltip('hide').remove();
                $cancel.tooltip('hide').remove();

                $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑路线"><i class="fa fa-edit"></i></a>')
                          .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除路线"><i class="fa fa-trash-o"></i></a>');

            });

            $table.on('click.remove', '.operation>.remove', function(){

                var $remove = $(this),
                    $tr = $remove.closest('tr'),
                    $checkbox = $tr.find('input[name=route]'),
                    _routeId = $checkbox.val(),
                    _uri = 'route/delete/' + _routeId;

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

                var tr = _table_scenery.row.add([
                    '-',
                    '<input type="text" class="form-control departure" placeholder="始发站" readonly style="width:100%; border-radius:4px;"/><input class="route-departure" type="hidden" />',
                    '<input type="text" class="form-control destination" placeholder="终点站" readonly style="width:100%; border-radius:4px;"/><input class="route-destination" type="hidden" />',
                    '<input type="text" class="form-control route-carpoolPrice" placeholder="拼车价格" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control route-charterPrice" placeholder="包车价格" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control fleet" placeholder="车队" readonly style="width:100%; border-radius:4px;"/><input class="route-fleet" type="hidden" />',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();
                
                var $tr = $(tr.node());
                
                //绑定事件
                $tr.find('.departure').on('click', departureWindow);
                $tr.find('.destination').on('click', destinationWindow);
                $tr.find('.fleet').on('click', fleetWindow);

                var _last_index = _table_scenery.rows()[0].length-1;

                $tr = $(_table_scenery.row(_last_index).node());

                var $cells = $tr.find('td');

                var $operation = $($cells[7]);

                $($cells[0]).addClass('center');

                $($cells[6]).addClass('center');

                $operation.addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'route/add/';

                    ajax.add(_uri, {

                    	departureId:$tr.find('.route-departure').val(),
            			destinationId:$tr.find('.route-destination').val(),
            			carpoolPrice:$tr.find('.route-carpoolPrice').val(),
            			charterPrice:$tr.find('.route-charterPrice').val(),
            			fleetId:$tr.find('.route-fleet').val()

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
                            '<input type="checkbox" name="route" class="flat-red" value="'+data.id+'"/>',
                            data.departureName+'<input class="route-departure" type="hidden" value="'+data.departureId+'"/>',
                            data.destinationName+'<input class="route-destination" type="hidden" value="'+data.destinationId+'"/>',
                            data.carpoolPrice,
                            data.charterPrice,
                            data.fleetName+'<input class="route-fleet" type="hidden" value="'+data.fleetId+'"/>',
                            data.updateTime,
                            '<a class="edit" data-placement="bottom" title="编辑路线"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除路线"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery.rows()[0].length-1;

                        var $tr = $(_table_scenery.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        $($cells[6]).addClass('center');

                        var $options = $($cells[7]);

                        //初始化复选框
                        $.fn['alt-checkbox']('create', {
                            selector:'[name=route]',
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
                var _routeIds = [];
                $table.find('input[name=route]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _routeIds.push($checkbox.val());
                    }
                });

                var _uri = 'route/delete';

                ajax.remove(_uri, {

                	ids:_routeIds

                }, function(data){

                    $.fn['alt-messenger']('success', '操作成功，当前删除：'+_routeIds.length+'条数据。');

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
    
    var departureWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var $tr = $input.closest('tr');
    	var $destination = $tr.find('.route-destination');
    	var filter = $destination.val();
    	var sWindow = new SiteWindow(
			'site/filter/site/' + (filter?filter:0), 
			'single', 
			function(sites){
	    		if(sites.length > 0){
	    			$input.val(sites[0].name.trim());
	    			$hidden.val(sites[0].id);
	    		}
	    		sWindow.close();
	    	});
    	sWindow.open($hidden.val());
    };
    
    var destinationWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var $tr = $input.closest('tr');
    	var $departure = $tr.find('.route-departure');
    	var filter = $departure.val();
    	var sWindow = new SiteWindow(
			'site/filter/site/' + (filter?filter:0), 
			'single', 
			function(sites){
	    		if(sites.length > 0){
	    			$input.val(sites[0].name.trim());
	    			$hidden.val(sites[0].id);
	    		}
	    		sWindow.close();
	    	});
    	sWindow.open($hidden.val());
    };
    
    var fleetWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var fWindow = new FleetWindow(
			'fleet/query/all', 
			'single', 
			function(fleets){
	    		if(fleets.length > 0){
	    			$input.val(fleets[0].name.trim());
	    			$hidden.val(fleets[0].id);
	    		}
	    		fWindow.close();
	    	});
    	fWindow.open($hidden.val());
    };
    
});
