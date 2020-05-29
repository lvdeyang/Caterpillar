/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'terminal5g/page-device5g.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'bvc2-monitor-terminal5g-live',
    'element-ui',
    'date',
    'mi-frame'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-device5g';

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
                groups: context.getProp('groups')
               
            },
            computed:{

            },
            watch:{

            },
            methods:{
                
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

    var device5g = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return device5g;

});