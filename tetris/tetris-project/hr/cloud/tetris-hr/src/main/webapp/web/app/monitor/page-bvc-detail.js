/**
 * Created by mr on 2020/4/29 0024.
 */
define([
    'text!' + window.APPPATH + 'monitor/page-bvc-detail.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'monitor/page-bvc-detail.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-bvc-detail';

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
                userlist:
                [
                   {
                	   "name":"黄靖宇",
                	   "role":"主席",
                	   "decodelist":[
                	      {"name":"5+?","status":1},
                	      {"name":"周圣云","status":0}
                	   ]
                   },
                   {
                	   "name":"5+?",
                	   "role":"成员",
                	   "decodelist":[
                   	      {"name":"周圣云","status":1}
                   	   ]
                   },
                   {
                	   "name":"周圣云",
                	   "role":"成员",
                	   "decodelist":[
                  	      {"name":"5+?","status":0}
                  	   ]
                   }
                ]
            },
            computed:{

            },
            watch:{

            },
            methods:{
            	gotoreport:function(){
            		location.href="#/page-bvc-report"
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