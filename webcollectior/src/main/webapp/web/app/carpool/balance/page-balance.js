/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'balance/tpl/balance-order.html',
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
		tpl_balance_order,
		juicer, 
		context, 
		page, 
		ajax, 
		DriverWindow,
		$){

    var prepire_header = juicer(header),
        prepire_tpl_balance_order = juicer(tpl_balance_order);

    var PAGENAME = 'balance';

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
        
      
        
        var uri = 'order/query/balance/list';

        ajax.query(uri, null, function(data){

        	var $body = $(prepire_tpl_balance_order.render({orders:data}));
        	$content.append($body);

            var $table = $body.find('table');

            var _table_order_new = $table.DataTable({
                order:[
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[4]
                }],
                //scrollX:true,
                //scrollCollapse:true,
                paging:true,
                autoWidth:false,
                columns:[
                   	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"200px"},
                	{"width":"100px"},
                	{"width":"40px"}
                ]
            });
            
//            new $.fn.dataTable.FixedColumns(_table_order_new, {
//            	leftColumns:0,
//                rightColumns:1
//            });

         
            
            //派单
            //$body.on('click.distribute', '.distribute', function(){
            	
            //});
        });
        
        
     
    };
    
  

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
