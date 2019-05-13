/* ========================================================================
 * component : plugins-demo..js: version 1.0.0
 * describe  : 标准组件定义
 * dependency: jQuery.js 1.12.3, Tetris.js 2.0.0
 * ========================================================================
 * created by lvdeyang
 * 2016年12月20日
 * ======================================================================== */

define([

	'text!' + window.BASEPATH + window.APPPATH + 'car/component/tpl/tpl-car-modal.html',
	'juicer',
	'restfull',
	'iCheck',
	'alt-modal'

], function(tpl, juicer, ajax, $){

	var prepire_tpl = juicer(tpl);
	
	//显示窗体
	var open = function(checked){
		var cWindow = this;
		
        ajax.query(cWindow.uri, null, function(data){
        	
        	var table = prepire_tpl.render({cars:data, mode:'single'});
        	
        	var $modal = cWindow.$modal = $.fn['alt-modal']('createAndShow', {
    			title:'选择车辆',
    			height:'400',
    			close:'关闭',
    			save:'确定',
    			content:table
    		});
        	
        	var $table = cWindow.$table = $('#car-window-table');
        	
        	$table.DataTable({
        		searching: false,
        		"order":[
                     [3, 'desc']
                 ],
                 columnDefs:[{
                     bSortable:false,
                     aTargets:[0]
                 }],
                 paging:false
        	});
        	
        	//数据选中
        	$table.on('click', 'tr', function(){
        		var $tr = $(this);
        		var $tds = $tr.find('td');
        		var $input = $($tds[0]).find('input');
        		if($input.is('[type=checkbox]')){
        			$input.iCheck('toggle');
        		}else if($input.is('[type=radio]')){
        			$input.iCheck('check');
        		}
        	});
        	
        	//保存
        	$modal.on('click', '.btn-save', function(){
        		if(typeof cWindow.okClick === 'function'){
        			var carArr = [];
            		$table.find('tbody tr').each(function(){
            			var $tr = $(this);
            			var $tds = $tr.find('td');
            			
            			var $input = $($tds[0]).find('input');
            			if($input[0].checked === true){
            				var type = $($tds[1]).text();
            				carArr.push({
            					id:$input.val(),
            					type:type
            				});
            			}
            			
            		});
            		cWindow.okClick.apply($modal, [carArr]);
        		}
        	});
        	
        	//处理选中状态
        	$table.find('tbody tr').each(function(){
        		var $tr = $(this);
        		var $tds = $tr.find('td');
        		var $input = $($tds[0]).find('input');
        		var id = $input.val();
        		if($input.is('[type=checkbox]')){
        			if(checked && checked.length>0){
        				var finded = false;
        				for(var i=0; i<checked.length; i++){
        					if(checked[i] == id){
        						finded = true;
        						break;
        					}
        				}
        				if(finded){
        					$input.iCheck('check');
        				}
        			}
        		}else if($input.is('[type=radio]')){
        			if(id == checked){
        				$input.iCheck('check');
        			}
        		}
        	});
        	
        	//初始化iCheck
        	if(cWindow.mode === 'single'){
        		$table.find('input[type=radio]').iCheck({radioClass:'iradio_flat-red'});
        	}else{
        		$table.find('input[type=checkbox]').iCheck({checkboxClass: 'icheckbox_flat-red'});
        	}
        	
        });
	}
	
	//关闭窗体
	var close = function(){
		var cWindow = this;
		$.fn['alt-modal']('close', cWindow.$modal);
	}
	
	function CarWindow(uri, mode, okClick){
		this.okClick = okClick;
		this.uri = uri;
		this.mode = mode;
		this.open = open;
		this.close = close;
	}

	return CarWindow;

});