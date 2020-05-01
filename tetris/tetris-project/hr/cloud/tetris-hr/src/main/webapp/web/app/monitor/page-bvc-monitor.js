/**
 * Created by mr on 2020/4/29 0024.
 */
define([
    'text!' + window.APPPATH + 'monitor/page-bvc-monitor.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'monitor/page-bvc-monitor.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-bvc-monitor';

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
                menus: context.getProp('menus'),
                user: context.getProp('user'),
                groups: context.getProp('groups'),
                businessList:
                [
                   {"name":"研发五部周会会议","status":1},
                   {"name":"zhs指挥","status":0}
                ]
            },
            computed:{
            	
            },
            watch:{

            },
            methods:{
            	gotodetail:function(){
            		location.href="#/page-bvc-detail"
            	}
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
            }
        });

    };

    var destroy = function(){

    };

    var currpage = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return currpage;

});