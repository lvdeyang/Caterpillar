/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.APPPATH + 'system/tpl/tpl-system-weixin.html',
    'juicer',
    'context',
    'page',
    'restfull',
    'alt-checkbox',
    'alt-messenger',
    'alt-tab'

], function(header, tpl_weixin, juicer, context, page, ajax, $){

    var prepire_header = juicer(header);
        prepire_tpl_weixin = juicer(tpl_weixin);

    var PAGENAME = 'system';

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
                    name:'系统',
                    icon:'#/system'
                }
            ]
        }));
        
        var tab = $.fn['alt-tab']('create', {
    		html:true,
    		tabs:[
		      {
		    	  id:'tab-weixin-config',
		    	  active:true,
		    	  title:'微信接入'
		      }
    		]
    	});
        
        tab = '<div style="padding:15px;">' + tab + '</div>';
        $content.append(tab);

        var uri = 'system/query/weixin/config';

        ajax.query(uri, null, function(data){
        	var $tab_weixin = $('#tab-weixin-config');
        	$tab_weixin.append(prepire_tpl_weixin.render(data));
        	
        	var flag = false;
        	
        	//修改按钮
        	$('#weixin-config-update').on('click', function(){
        		if(!flag){
        			$tab_weixin.find('.config-item').each(function(){
            			var $div = $(this);
            			var $label = $div.prev('label');
            			var _value = $div.text().trim();
            			_value = _value==='未设置'?'':_value;
            			$div.empty().append('<input type="text" class="form-control" id="'+$label.attr('for')+'" placeholder="'+$label.text()+'" value="'+_value+'">');
            		});
        			flag = true;
        		}
        	});
        	
        	//保存按钮
        	$('#weixin-config-save').on('click', function(){
        		if(flag){
        			var data = {};
            		$tab_weixin.find('.config-item input').each(function(){
            			var $input = $(this);
            			data[$input.attr('id').trim()] = $input.val().trim();
            		});
            		uri = 'system/set/weixin/config';
            		ajax.post(uri, data, function(data){
            			$tab_weixin.find('.config-item').each(function(){
            				var $div = $(this);
            				var $label = $div.prev('label');
            				var key = $label.attr('for');
            				$div.empty().text(data[key]);
            			});
            		});
            		flag = false;
        		}
        	});
        });

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
