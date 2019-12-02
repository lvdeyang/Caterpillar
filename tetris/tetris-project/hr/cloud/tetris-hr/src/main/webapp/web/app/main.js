require.config({
    baseUrl: window.BASEPATH,
    paths: {
        /* lib */
        'text':window.LIBPATH + 'frame/requireJS/plugins/text',
        'css':window.LIBPATH + 'frame/requireJS/plugins/css',
        'vue':window.LIBPATH + 'frame/vue/vue-2.5.16',
        'vue-router':window.LIBPATH + 'frame/vue/vue-router-3.0.1',
        'jquery':window.LIBPATH + 'frame/jQuery/jquery-2.2.3.min',
        'json':window.LIBPATH + 'frame/jQuery/jquery.json',
        'element-ui':window.LIBPATH + 'ui/element-ui/element-ui-2.4.3.min',

        'TweenLite':window.LIBPATH + 'TweenMax/cmd/TweenLite',

        /* commons */
        'context':window.COMMONSPATH + 'context/context',
        'page-wrapper':window.COMMONSPATH + 'page/page-wrapper',
        'router':window.COMMONSPATH + 'router/router',
        'date':window.COMMONSPATH + 'date/date.ext',
        'string':window.COMMONSPATH + 'string/string.ext',
        'storage':window.COMMONSPATH + 'storage/storage.ext',
        'restfull':window.COMMONSPATH + 'restfull/restfull.api',
        'file':window.COMMONSPATH + 'uploader/File',
        'uploader':window.COMMONSPATH + 'uploader/Uploader',
        'menu':window.COMMONSPATH + 'menu/menu',
        'bpmn-ext':window.COMMONSPATH + 'bpmn/ext/BpmnExtension',

        /* app */
        'config':window.APPPATH + 'config',
        'commons':window.APPPATH + 'commons',

        /* error */
        'page-error':window.APPPATH + 'error/page-error',

        /* component */
        'mi-frame':window.APPPATH + 'component/frame/frame',
        'mi-sub-title':window.APPPATH + 'component/sub-title/sub-title',
        'mi-folder-dialog':window.APPPATH + 'component/dialog/folder/folder-dialog',
        'mi-task-view':window.APPPATH + 'component/view/task/task-view',
        'mi-upload-dialog':window.APPPATH + 'component/dialog/upload/upload-dialog',
        'mi-lightbox':window.APPPATH + 'component/lightbox/lightbox',
        'mi-user-dialog':window.APPPATH + 'component/dialog/user/user-dialog',
        'mi-system-role-dialog':window.APPPATH + 'component/dialog/system-role/system-role-dialog',
        'mi-business-role-dialog':window.APPPATH + 'component/dialog/business-role/business-role-dialog',
        'mi-theme-dialog':window.APPPATH + 'component/dialog/theme/theme-dialog',
        'mi-tag-dialog':window.APPPATH + 'component/dialog/mims/tag/mims-tag',

        /* pages */
        'page-hr-org':window.APPPATH + 'org/page-hr-org'
    },
    shim:{
        'vue':{
            exports: 'Vue'
        },
        'vue-router':{
            deps: ['vue'],
            exports: 'VueRouter'
        },
        'element':{
            deps:['vue']
        },
        'jquery':{
        	exports:'jQuery'
        },
        'json':{
        	deps:['jquery'],
        	exports:'jQuery'
        }
    }
});

require([
    'storage',
    'vue',
    'router',
    'context',
    'menu',
    'config',
    'jquery',
    'restfull',
    'element-ui',
    'css!' + window.APPPATH + 'reset.css'
], function(storage, Vue, router, context, menuUtil, config, $, ajax){

    var app = null;

    //缓存token
    storage.setItem(config.ajax.header_auth_token, window.TOKEN);
    storage.setItem(config.ajax.header_session_id, window.SESSIONID);

    //初始化ajax
    ajax.init({
        login:config.ajax.login,
        authname:config.ajax.header_auth_token,
        sessionIdName:config.ajax.header_session_id,
        debug:config.ajax.debug,
        messenger:{
            info:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
            	app.$message({
                    message: message,
                    type: 'info'
                });
            },
            success:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'success'
                });
            },
            warning:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'warning'
                });
            },
            error:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'error'
                });
            }
        }
    });

    ajax.get('/prepare/app', null, function(appInfo){

        //初始化全局vue实例
        var app = new Vue({
            router:router,
            data:function(){
                return {
                    loading:false
                };
            }
        }).$mount('#app');

        appInfo.menus = appInfo.menus || [];
        
        //初始化上下文环境
        context.setProp('app', app)
               .setProp('router', router)
               .setProp('user', appInfo.user)
               .setProp('groups', appInfo.groups || [])
               .setProp('token', window.TOKEN);

        //处理皮肤
        if(appInfo.user.themeUrl) $('head').prepend('<link rel="stylesheet" type="text/css" href="'+window.BASEPATH + appInfo.user.themeUrl+'"/>');

        //解析模板
        menuUtil.parseUrlTemplate(appInfo.menus);

        //缓存菜单
        context.setProp('menus', appInfo.menus);

        //获取活动页
        var activeMenu = menuUtil.getActiveMenu(appInfo.menus);

        //重置首页
        if(activeMenu){
            config.redirect.home = activeMenu.link.split('#/')[1];
        }

        //跳转首页
        if(!window.location.hash || window.location.hash==='#/') router.push('/' + config.redirect.home);

    });

});