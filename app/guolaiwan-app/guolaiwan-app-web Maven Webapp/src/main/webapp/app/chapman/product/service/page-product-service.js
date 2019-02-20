/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'context',
    'page'

], function(context, page){

    var PAGENAME = 'product-service';

    var _init = function(){

        context.setContent($('<div style="width:100%; min-height:978px;">商品-服务</div>'));

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
