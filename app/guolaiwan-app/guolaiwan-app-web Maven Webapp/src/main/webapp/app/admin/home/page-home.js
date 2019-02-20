/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'juicer',
    'context',
    'page'

], function(header, juicer, context, page){

    var PAGENAME = 'home';

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'模板管理工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                }
            ]
        }));

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
