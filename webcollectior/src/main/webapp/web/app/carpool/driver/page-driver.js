/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'driver/tpl/tpl-driver.html',
    'juicer',
    'context',
    'page',
    'restfull',
    window.APPPATH + 'fleet/component/fleet-modal',
    window.APPPATH + 'car/component/car-modal',
    window.APPPATH + 'route/component/route-modal',
    'alt-checkbox',
    'alt-messenger',
    'alt-confirm'

], function(header, tpl, juicer, context, page, ajax, FleetWindow, CarWindow, RouteWindow, $){

    var prepire_header = juicer(header),
        prepire_tpl = juicer(tpl);

    var PAGENAME = 'driver';
    
    var _init = function(){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                $.fn['alt-messenger']('info', '当前有未保存的司机，请先保存!');
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
                    name:'司机',
                    icon:'#/driver'
                }
            ]
        }));

        var uri = '/driver/query/all';

        ajax.query(uri, null, function(data){

            var $body = $(prepire_tpl.render({drivers:data}));
            $content.append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_driver = $table.DataTable({
                "order":[
                    [7, 'desc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 8]
                }],
                paging:false
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=driver]').each(function(){
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
                selector:'[name=driver]',
                context:$('#driver-table'),
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=driver]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=driver]', function(){
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
                	$car = $($cells[3]),
                	$fleet = $($cells[4]),
                	$route = $($cells[5]),
                	$revert = $($cells[6]),
                    $oparation = $($cells[8]),
                    $edit = $oparation.find('.edit'),
                    $key = $oparation.find('.key'),
                    $remove = $oparation.find('.remove');

                var $name_input = $('<input type="text" class="form-control driver-name" placeholder="车队名" required style="width:100%; border-radius:4px;"/>').val($name.text().trim()).data('hidden', $name.text().trim());
                var $mobile_input = $('<input type="text" class="form-control driver-mobile" placeholder="队长电话" required style="width:100%; border-radius:4px;"/>').val($mobile.text().trim()).data('hidden', $mobile.text().trim());
                var $carName = $('<input type="text" class="form-control car" placeholder="车辆" required readonly style="width:100%; border-radius:4px;"/>').val($car.text().trim()).data('hidden', $car.text().trim());
                var $carId = $car.find('.driver-car').attr('data-id', $car.find('.driver-car').val());
                var $fleetName = $('<input type="text" class="form-control fleet" placeholder="车队" required readonly style="width:100%; border-radius:4px;"/>').val($fleet.text().trim()).data('hidden', $fleet.text().trim());
                var $fleetId = $fleet.find('.driver-fleet').attr('data-id', $fleet.find('.driver-fleet').val());
                var $routeName = $('<input type="text" class="form-control route" placeholder="出发路线" required readonly style="width:100%; border-radius:4px;"/>').val($route.text().trim()).data('hidden', $route.text().trim());
                var $routeId = $route.find('.driver-route').attr('data-id', $route.find('.driver-route').val());
                var $revertName = $('<input type="text" class="form-control revert" placeholder="返回路线" required readonly style="width:100%; border-radius:4px;"/>').val($revert.text().trim()).data('hidden', $revert.text().trim());
                var $revertId = $revert.find('.driver-revert').attr('data-id', $revert.find('.driver-revert').val());
                
                var $save = $('<a class="edit-save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>'),
                    $cancel = $('<a class="edit-cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>');

                $edit.tooltip('hide').remove();
                $remove.tooltip('hide').remove();
                $key.tooltip('hide').remove();

                $name.text('').append($name_input);
                $mobile.text('').append($mobile_input);
                $car.text('').append($carName).append($carId);
                $fleet.text('').append($fleetName).append($fleetId);
                $route.text('').append($routeName).append($routeId);
                $revert.text('').append($revertName).append($revertId);

                $oparation.append($save).append($cancel);
                
                //绑定事件
                $tr.find('.fleet').on('click', fleetWindow);
                $tr.find('.car').on('click', carWindow);
                $tr.find('.route').on('click', routeWindow);
                $tr.find('.revert').on('click', routeWindow);

            });

            $table.on('click.edit.save', '.operation>.edit-save', function(){

                var $edit = $(this),
                    $tr = $edit.closest('tr'),
                    $cells = $tr.find('td');

                var $checkbox = $($cells[0]).find('input'),
                    $name = $($cells[1]),
                    $mobile = $($cells[2]),
                    $car = $($cells[3]),
                    $fleet = $($cells[4]),
                    $route = $($cells[5]),
                    $revert = $($cells[6]),
                    $updateTime = $($cells[7]),
                    $name_input = $name.find('input'),
                    $mobile_input = $mobile.find('input'),
                	$car_input = $car.find('.driver-car'),
                	$fleet_input = $fleet.find('.driver-fleet'),
                	$route_input = $route.find('.driver-route'),
                	$revert_input = $revert.find('.driver-revert'),
                    $operation = $($cells[8]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var _uri = 'driver/update/' + $checkbox.val();

                ajax.update(_uri, {

                    name:$name_input.val().trim(),
                    mobile:$mobile_input.val().trim(),
                    carId:$car_input.val(),
                    fleetId:$fleet_input.val(),
                    routeId:$route_input.val(),
                    revertId:$revert_input.val()

                }, function(data, status){

                    if(status === 200){

                        $name_input.remove();
                        $mobile_input.remove();
                        $edit_save.tooltip('hide').remove();
                        $cancel.tooltip('hide').remove();
                        $name.text(data.name);
                        $mobile.text(data.mobile);
                        $car.text(data.carType).append($car_input);
                        $fleet.text(data.fleetName).append($fleet_input);
                        $route.text(data.route).append($route_input);
                        $revert.text(data.revert).append($revert_input);
                        $updateTime.text(data.updateTime);

                        $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑司机"><i class="fa fa-edit"></i></a>')
                                  .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除司机"><i class="fa fa-trash-o"></i></a>');

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
                    
                    $car = $($cells[3]),
                    $carName = $car.find('.car'),
                    $carId = $car.find('.driver-car'),
                    
                    $fleet = $($cells[4]),
                    $fleetName = $fleet.find('.fleet'),
                    $fleetId = $fleet.find('.driver-fleet'),
                    
                    $route = $($cells[5]),
                    $routeName = $route.find('.route'),
                    $routeId = $route.find('.driver-route'),
                    
                    $revert = $($cells[6]),
                    $revertName = $revert.find('.revert'),
                    $revertId = $revert.find('.driver-revert'),
                    
                    $operation = $($cells[8]),
                    $edit_save = $operation.find('.edit-save'),
                    $cancel = $operation.find('.edit-cancel');

                var bak_name = $name_input.data('hidden');
                $name.empty().text(bak_name);
                
                var bak_mobile = $mobile_input.data('hidden');
                $mobile.empty().text(bak_mobile);
                
                var bak_car = $carName.data('hidden');
                var bak_carId = $carId.data('id');
                $car.empty().text(bak_car).append($carId.val(bak_carId));
                
                var bak_fleet = $fleetName.data('hidden');
                var bak_fleetId = $fleetId.data('id');
                $fleet.empty().text(bak_fleet).append($fleetId.val(bak_fleetId));
                
                var bak_route = $routeName.data('hidden');
                var bak_routeId = $routeId.data('id');
                $route.empty().text(bak_route).append($routeId.val(bak_routeId));
                
                var bak_revert = $revertName.data('hidden');
                var bak_revertId = $revertId.data('id');
                $revert.empty().text(bak_revert).append($revertId.val(bak_revertId));
                
                $edit_save.tooltip('hide').remove();
                $cancel.tooltip('hide').remove();

                $operation.append('<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑司机"><i class="fa fa-edit"></i></a>')
                		  .append('<a class="key" data-toggle="tooltip" data-placement="bottom" title="重置密码"><i class="fa fa-key"></i></a>')
                          .append('<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除司机"><i class="fa fa-trash-o"></i></a>');

            });
            
            $table.on('click.key', '.operation>.key', function(){
            	
            	var $key = $(this),
            		$tr = $key.closest('tr'),
            		$checkbox = $tr.find('input[type=checkbox]');
            	
            	var driverId = $checkbox.val();
            	
            	var _uri = 'driver/query/key/' + driverId;
            	
            	var $confirm = $.fn['alt-confirm']('createAndShow', {
            		content:'是否要重置该司机的密码？',
            		save:'确定'
            	});
            	
            });

            $table.on('click.remove', '.operation>.remove', function(){

                var $remove = $(this),
                    $tr = $remove.closest('tr'),
                    $checkbox = $tr.find('input[name=driver]'),
                    _driverId = $checkbox.val(),
                    _uri = 'driver/delete/' + _driverId;

                $tr.addClass('selected');

                ajax.remove(_uri, null, function(data, status){

                    if(status !== 200) $tr.removeClass('selected');

                    $.fn['alt-messenger']('success', '操作成功');

                    $remove.tooltip('hide');

                    _table_driver.rows('.selected').remove().draw(false);

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

                var tr = _table_driver.row.add([
                    '-',
                    '<input type="text" class="form-control driver-name" placeholder="司机姓名" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control driver-mobile" placeholder="手机号" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control car" placeholder="车辆" readonly style="width:100%; border-radius:4px;"/><input class="driver-car" type="hidden" />',
                    '<input type="text" class="form-control fleet" placeholder="车队" readonly style="width:100%; border-radius:4px;"/><input class="driver-fleet" type="hidden" />',
                    '<input type="text" class="form-control route" placeholder="路线" readonly style="width:100%; border-radius:4px;"/><input class="driver-route" type="hidden" />',
                    '<input type="text" class="form-control revert" placeholder="路线" readonly style="width:100%; border-radius:4px;"/><input class="driver-revert" type="hidden" />',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="key" data-toggle="tooltip" data-placement="bottom" title="重置密码"><i class="fa fa-key"></i></a>'+
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();
                
                var $tr = $(tr.node());
                
                //绑定事件
                $tr.find('.fleet').on('click', fleetWindow);
                $tr.find('.car').on('click', carWindow);
                $tr.find('.route').on('click', routeWindow);
                $tr.find('.revert').on('click', routeWindow);
                
                var _last_index = _table_driver.rows()[0].length-1;

                $tr = $(_table_driver.row(_last_index).node());

                var $cells = $tr.find('td');

                var $operation = $($cells[8]);

                $($cells[0]).addClass('center');

                $($cells[7]).addClass('center');

                $operation.addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'driver/add/';

                    ajax.add(_uri, {

                        name:$tr.find('.driver-name').val(),
                        mobile:$tr.find('.driver-mobile').val(),
                        carId:$tr.find('.driver-car').val(),
                        fleetId:$tr.find('.driver-fleet').val(),
                        routeId:$tr.find('.driver-route').val(),
                        revertId:$tr.find('.driver-revert').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_driver.rows()[0].length-1;

                        //删除编辑行
                        _table_driver.rows(_last_index).remove();

                        //新增数据行
                        _table_driver.row.add([
                            '<input type="checkbox" name="driver" class="flat-red" value="'+data.id+'"/>',
                            data.name,
                            data.mobile,
                            data.carType+'<input class="driver-car" type="hidden" value="'+data.carId+'" />',
                            data.fleetName+'<input class="driver-fleet" type="hidden" value="'+data.fleetId+'"/>',
                            data.route+'<input class="driver-route" type="hidden" value="'+data.routeId+'" />',
                            data.revert+'<input class="driver-revert" type="hidden" value="'+data.revertId+'" />',
                            data.updateTime,
                            '<a class="edit" data-placement="bottom" title="编辑司机"><i class="fa fa-edit"></i></a>'+
                            '<a class="key" data-toggle="tooltip" data-placement="bottom" title="重置密码"><i class="fa fa-key"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除司机"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_driver.rows()[0].length-1;

                        var $tr = $(_table_driver.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        $($cells[7]).addClass('center');

                        var $options = $($cells[8]);

                        //初始化复选框
                        $.fn['alt-checkbox']('create', {
                            selector:'[name=driver]',
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
                    _table_driver.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _driverIds = [];
                $table.find('input[name=driver]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _driverIds.push($checkbox.val());
                    }
                });

                var _uri = 'driver/delete';

                ajax.remove(_uri, {

                	ids:_driverIds

                }, function(data){

                    $.fn['alt-messenger']('success', '操作成功，当前删除：'+_driverIds.length+'条数据。');

                    _table_driver.rows('.selected').remove().draw(false);

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
    
    //车辆窗口
    var carWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var cWindow = new CarWindow(
			'car/query/free/car',
			'single', 
	    	function(cars){
	    		if(cars.length > 0){
	    			$input.val(cars[0].type.trim());
	    			$hidden.val(cars[0].id);
	    		}
	    		cWindow.close();
	    	});
    	cWindow.open($hidden.val());
    };
    
    //车队窗口
    var fleetWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var oldFleet = $hidden.val();
    	var $tr = $input.closest('tr');
    	var $route = $tr.find('.route');
    	var $route_hidden = $tr.find('.driver-route');
    	
    	var fWindow = new FleetWindow(
			'fleet/query/all', 
			'single', 
			function(fleets){
	    		if(fleets.length > 0){
	    			$input.val(fleets[0].name.trim());
	    			$hidden.val(fleets[0].id);
	    			
	    			//做联动
	    			if(fleets[0].id != oldFleet){
	    				$route.val('');
	    				$route_hidden.val('');
	    			}
	    			
	    		}
	    		fWindow.close();
	    	});
    	fWindow.open($hidden.val());
    };
    
    //路线窗口
    var routeWindow = function(){
    	var $input = $(this);
    	var $hidden = $input.next();
    	var $tr = $input.closest('tr');
    	var $fleetHidden = $tr.find('.driver-fleet');
    	var fleetId = $fleetHidden.val();
    	var rWindow = new RouteWindow(
			'route/query/by/fleet/' + (fleetId?fleetId:0),
			'single', 
	    	function(routes){
	    		if(routes.length > 0){
	    			$input.val(routes[0].route.trim());
	    			$hidden.val(routes[0].id);
	    		}
	    		rWindow.close();
	    	});
    	rWindow.open($hidden.val());
    };

});
