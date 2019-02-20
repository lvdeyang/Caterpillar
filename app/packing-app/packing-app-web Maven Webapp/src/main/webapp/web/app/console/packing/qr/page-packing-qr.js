/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'juicer',
    'context',
    'page',
    'restfull',
    'jquery'

], function(header, juicer, context, page, ajax, $){

    var PAGENAME = 'packing-qr';

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'二维码',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    hash:'#/home'
                },{
                    name:'挪车服务'
                },{
                    name:'二维码'
                }
            ]
        }));

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
