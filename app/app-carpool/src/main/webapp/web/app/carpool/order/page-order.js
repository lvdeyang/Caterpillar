/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'order/tpl/new-order.html',
    'text!' + window.APPPATH + 'order/tpl/unpay-order.html',
    'text!' + window.APPPATH + 'order/tpl/success-order.html',
    'juicer',
    'context',
    'page',
    'restfull',
    window.APPPATH + 'driver/component/driver-modal',
    'alt-checkbox',
    'alt-messenger',
    'alt-tab',
    'alt-confirm'

], function(
		header, 
		tpl_new_order,
		tpl_unpay_order,
		tpl_success_order,
		juicer, 
		context, 
		page, 
		ajax, 
		DriverWindow,
		$){

    var prepire_header = juicer(header),
        prepire_tpl_new_order = juicer(tpl_new_order),
        prepire_tpl_unpay_order = juicer(tpl_unpay_order),
        prepire_tpl_success_order = juicer(tpl_success_order);

    var PAGENAME = 'order';

    var _init = function(){

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
                    icon:'#/order'
                }
            ]
        }));
        
        var tab = $.fn['alt-tab']('create', {
    		html:true,
    		tabs:[
		      {
		    	  id:'tab-order-new',
		    	  active:true,
		    	  title:'未接订单'
		      },{
		    	  id:'tab-order-unpay',
		    	  title:'未支付订单'
		      }/*,{
		    	  id:'tab-order-success',
		    	  title:'已经完成订单'
		      }*/
    		]
    	});
        
        tab = '<div style="padding:15px;">' + tab + '</div>';
        $content.append(tab);
        
        var $newOrder = $('#tab-order-new');
        var $unpayOrder = $('#tab-order-unpay');
        //var $successOrder = $('#tab-order-success');
        
        $content.find('a[data-toggle=tab]').on('show.bs.tab', function(){
        	var $button = $(this);
        	if($button.is('[href="#tab-order-new"]')){
        		newOrderShow.apply($newOrder);
        	}else if($button.is('[href="#tab-order-unpay"]')){
        		unpayOrderShow.apply($unpayOrder);
        	}else if($button.is('[href="#tab-order-success"]')){
        		alert(3);
        	}
        });
        
        newOrderShow.apply($newOrder);
    };
    
    var newOrderShow = function(){
    	var $newOrder = this;
    	
    	var uri = 'order/query/new/order/after/30/minutes';

        ajax.query(uri, null, function(data){

        	var $body = $(prepire_tpl_new_order.render({orders:data}));
        	$newOrder.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_order_new = $table.DataTable({
                order:[
                    [2, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[12]
                }],
                scrollX:true,
                scrollCollapse:true,
                paging:true,
                autoWidth:false,
                columns:[
                   	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"200px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"500px"},
                	{"width":"200px"},
                	{"width":"40px"}
                ]
            });
            
            new $.fn.dataTable.FixedColumns(_table_order_new, {
            	leftColumns:0,
                rightColumns:1
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();
            
            //派单
            $body.on('click.distribute', '.distribute', function(){
            	var $button = $(this);
            	var orderId = $button.data('id');
            	var orderUuid = $button.data('uuid');
            	var dWindow = new DriverWindow(
        			'driver/query/by/order', 
        			'single', 
        			function(drivers){
        				if(drivers.length <= 0){
        					$.fn['alt-messenger']('info', '您还没有选择司机！');
        				}else{
        					dWindow.close();
        					var $confirm = $.fn['alt-confirm']('createAndShow', {
                				status:'default',
                				title:'派单确认',
                				content:'您将要把订单（编号：'+orderUuid + "） 分派给司机（姓名：" + drivers[0].name + '，手机：'+ drivers[0].mobile +'）'
                			});
        					$confirm.find('.btn-save').on('click', function(){
        						$confirm.modal('hide');
        						uri = "order/admin/distribute/system/order/" + drivers[0].mobile + "/" + orderId
        						ajax.post(uri, null, function(data){
        							$.fn['alt-messenger']('success', '派单成功！');
        							var $begin = $tr = $button.closest('tr');
            						var index = 0;
            						while($begin.prev()[0]){
            							index = index + 1;
            							$begin = $begin.next();
            						}
            						_table_order_new.row(index).remove().draw();
        						});
        					});
        				}
        			});
            	dWindow.open(orderId);
            });
        });
    };
    
    var unpayOrderShow = function(){

    	var $unpayOrder = this;
    	
    	var uri = 'order/query/unpay/order';

        ajax.query(uri, null, function(data){

        	var $body = $(prepire_tpl_unpay_order.render({orders:data}));
        	$unpayOrder.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_order_unpay = $table.DataTable({
                order:[
                    [2, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[12]
                }],
                scrollX:true,
                scrollCollapse:true,
                paging:true,
                autoWidth:false,
                columns:[
                   	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"200px"},
                	{"width":"100px"},
                	{"width":"100px"},
                	{"width":"500px"},
                	{"width":"200px"},
                	{"width":"40px"}
                ]
            });
            
            new $.fn.dataTable.FixedColumns(_table_order_unpay, {
            	leftColumns:0,
                rightColumns:1
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();
            
            //派单
            $body.on('click.notice.pay', '.notice-pay', function(){
            	var $button = $(this);
            	var orderId = $button.data('id');
            	
            	uri = "order/notice/pay/" + orderId;
            	
            	ajax.post(uri, null, function(){
            		$.fn['alt-messenger']('success', '催单成功!');
            	});
            	
            });
        });
    
    };
    
    var successOrderShow = function(){
    	
    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
