/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!' + window.BASEPATH + window.PROJECTPATH + 'packing/user/tpl/tpl-packing-user-chapman-table.html',
    'juicer',
    'context',
    'page',
    'restfull',
    'alt-tab'

], function(header, chapman_table, juicer, context, page, ajax, $){

    var prepare_header = juicer(header);
    var prepare_chapman_table = juicer(chapman_table);

    var PAGENAME = 'packing-user';

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(prepare_header.render({
            level_1:'注册用户',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    hash:'#/home'
                },{
                    name:'挪车服务'
                },{
                    name:'注册用户'
                }
            ]
        }));

        //创建标签页
        var $tabWrapper = $('<section class="content" style="min-height:863px;"></section>');
        $content.append($tabWrapper);
        $tabWrapper['alt-tab']('create', {
            tabs:[
                {
                    id:'chapman-id',
                    title:'商户id注册',
                    content:'商户id注册',
                    active:true
                },{
                    id:'identification',
                    title:'绑定id注册',
                    content:'绑定id注册'
                }
            ]
        });

        //商户id注册用户列表
        ajax

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
