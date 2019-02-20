/**
 * Created by lvdeyang on 2017/7/03.
 */
define([

    'text!html-hero',
    'text!html-tours',
    'juicer',
    'context',
    'page',
    'commons',
    'travel',
    'jquery'

], function(hero, tours, juicer, context, page, commons, travel, $){

    var PAGENAME = 'home';

    var _init = function(){

        var prepare_tours = juicer(tours);

        var $content = $('<div style="width:100%; min-height:874px;"></div>');
        context.setContent($content);

        $content.append(hero);

        var _uri = 'website/home/init';

        commons.ajax.get(_uri, null, function(data){

            var _sections = data.sections;

            for(var i=0,len=_sections.length-1; i<=len; i++){
                var $section = prepare_tours.render({
                    title:_sections[i].title,
                    summary:_sections[i].summary,
                    info:'#/info/',
                    bookButton:'立即订购',
                    moreButton:{
                        text:'查看更多'
                        //hash:'#/ccc'
                    },
                    items:_sections[i].products
                });

                $content.append($section);
            }

            travel.initBody();

            /*$content.on('click.book', '.button-book-now', function(e){
                e.stopPropagation();

                var $button = $(this);

                //走一个动画

                if(!context.model || !context.model.user || !context.model.user.id){
                    context.load('error', {
                        status:403,
                        message:'亲，您还没有登录呐！'
                    });
                    return;
                }

                var _uri = 'basket/add/' + context.model.user.id;

                commons.ajax.post(_uri, {

                    product:$button.siblings('.product-id').val()

                }, function(data){

                    //更新购物车数目
                    $('.button-shopping-cart').find('.label').text(data.basketNum);

                });


            });*/

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
