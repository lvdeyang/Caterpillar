/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.PROJECTPATH + 'error/component/error.html',
    'juicer',
    'context',
    'page'

], function(header, error, juicer, context, page){

    var PAGENAME = 'error';

    var _default = {
        status:500,
        message:'发生异常啦！'
    };

    var _init = function(model){

        var _defaultError = $.extend(true, {}, _default);
        model = $.extend(true, _defaultError, model);

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'发生异常啦!',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                }
            ]
        }));

        $content.append(juicer(error).render(model));

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});