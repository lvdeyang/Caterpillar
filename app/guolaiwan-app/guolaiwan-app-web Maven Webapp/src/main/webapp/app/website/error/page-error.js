/**
 * Created by lvdeyang on 2017/7/10.
 */
define([

    'text!html-error',
    'juicer',
    'context',
    'page',
    'commons',
    'travel',
    'jquery'

], function(error, juicer, context, page, commons, travel, $){

    var PAGENAME = 'error';

    var prepare_error = juicer(error);

    var _default = {
        status:500,
        message:'∑¢…˙“Ï≥£¿≤£°'
    }

    var _init = function(model){

        var _defaultError = $.extend(true, {}, _default);
        model = $.extend(true, _defaultError, model);

        var $content = $('<div style="width:100%;"></div>');
        context.setContent($content);

        $content.append(prepare_error.render(model));

        travel.initBody();

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
