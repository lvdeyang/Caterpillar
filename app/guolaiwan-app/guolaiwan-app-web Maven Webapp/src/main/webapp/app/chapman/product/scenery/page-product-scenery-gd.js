/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-product-scenery-source-body',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons',
    'tree'

], function(header, body_source, juicer, context, page, $, commons){

    var PAGENAME = 'product-gd';

    var prepare_body_source = juicer(body_source);

    var _init = function(productId, productName){

        var $content = $('<div style="width:100%; min-height:876px;"></div>');
        context.setContent($content);

        //渲染页头
        $content.append(juicer(header).render({
            level_1:'景点管理工具',
            level_2:'版本：1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'商品',
                },{
                    name:'景点'
                },{
                    name:'图文详情'
                }
            ]
        }));

        var $wrapper = $('<div></div>');
        $content.append($wrapper);



    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
