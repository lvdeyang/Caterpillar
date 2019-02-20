define([
    'text!' + window.APPPATH + 'error/page-error.html',
    'config',
    'commons',
    'vue',
    'element-ui',
    'bvc2-header'
], function(tpl, config, commons, Vue){

    var pageId = 'page-error';

    var init = function(p){
        var code = parseInt(p.code);
        var message = decodeURI(p.message);

        var status = null;
        var defaultMessage = null;
        if(code === 200){
            status = 'success';
            defaultMessage = '操作成功！';
        }else if(code>=400 && code<500){
            status = 'warning';
            if(code === 404){
                defaultMessage = '访问的地址不存在！';
            }else if(code === 403){
                defaultMessage = '请求被服务器拒绝！';
            }else{
                defaultMessage = '业务异常！';
            }
        }else if(code >= 500){
            status = 'error';
            defaultMessage = '服务器异常！';
        }

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        var v_groupList = new Vue({
            el:'#' + pageId + '-wrapper',
            data:{
                header:commons.getHeader(),
                status:status,
                defaultMessage:defaultMessage,
                message:message,
                code:code
            },
            methods:{
                goHome:function(){
                    window.location.hash = '#/' + config.redirect.home
                }
            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId + '/:code/:message',
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;
});