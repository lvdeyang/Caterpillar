/**
 * Created by lvdeyang on 2017/7/03.
 */
define([

    'text!html-info-body',
    'text!html-timeline',
    'juicer',
    'context',
    'page',
    'commons',
    'travel',
    'jquery'

], function(info_body, timeline, juicer, context, page, commons, travel, $){

    var PAGENAME = 'info';

    var prepare_info_body = juicer(info_body);
    var prepare_timeline = juicer(timeline);

    var _init = function(productId){

        var $content = $('<div style="width:100%; min-height:874px;"></div>');
        context.setContent($content);

        var _uri = 'website/info/init/' + productId;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_info_body.render(data));

            $content.append($body);

            $content.append(timeline);

        });

        $content.on('mouseover', '.thumbnail', function(){

            var $div = $(this);
            $div.siblings().each(function(){
                $(this).find('.content').removeClass('active');
            });

            var $picture_thumbnail = $div.find('img');
            var $picture_show =  $div.siblings('.picture-show').find('img');
            $picture_show.attr('src', $picture_thumbnail.attr('src'));

            $picture_thumbnail.parent().addClass('active');

        });

        $content.on('click', '#add-basket', function(){

        });

        $content.on('click', '#pay-now', function(){

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
