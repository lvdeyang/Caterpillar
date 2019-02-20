require.config({
    baseUrl: window.BASEPATH,
    paths: {
        /* lib */
        'text':window.LIBPATH + 'frame/requireJS/plugins/text',
        'vue':window.LIBPATH + 'frame/vue/vue-2.5.16',
        'vue-router':window.LIBPATH + 'frame/vue/vue-router-3.0.1',
        'jquery':window.LIBPATH + 'frame/jQuery/jquery-2.2.3.min',
        'json':window.LIBPATH + 'frame/jQuery/jquery.json',
        'element-ui':window.LIBPATH + 'ui/element-ui/element-ui-2.4.3.min',

        /* commons */
        'context':window.COMMONSPATH + 'context/context',
        'page-wrapper':window.COMMONSPATH + 'page/page-wrapper',
        'router':window.COMMONSPATH + 'router/router',
        'date':window.COMMONSPATH + 'date/date.ext',
        'string':window.COMMONSPATH + 'string/string.ext',
        'storage':window.COMMONSPATH + 'storage/storage.ext',
        'restfull':window.COMMONSPATH + 'restfull/restfull.api',

        'config':window.APPPATH + 'config',
        'commons':window.APPPATH + 'commons',

        /* components */
        'bvc2-header':window.APPPATH + 'component/bvc2-header/bvc2-header',
        'bvc2-tab':window.APPPATH + 'component/bvc2-tab/bvc2-tab',
        'bvc2-tab-buttons':window.APPPATH + 'component/bvc2-tab-buttons/bvc2-tab-buttons',
        'bvc2-auto-layout':window.APPPATH + 'component/bvc2-auto-layout/bvc2-auto-layout',
        'bvc2-layout-buttons':window.APPPATH + 'component/bvc2-layout-buttons/bvc2-layout-buttons',
        'bvc2-layout-table-source':window.APPPATH + 'component/bvc2-layout-table-source/bvc2-layout-table-source',
        'bvc2-layout-table-destination':window.APPPATH + 'component/bvc2-layout-table-destination/bvc2-layout-table-destination',
        'bvc2-table-agenda':window.APPPATH + 'component/bvc2-table-agenda/bvc2-table-agenda',
        'bvc2-table-scheme':window.APPPATH + 'component/bvc2-table-scheme/bvc2-table-scheme',
        'bvc2-tree-meeting-member':window.APPPATH + 'component/bvc2-tree-meeting-member/bvc2-tree-meeting-member',
        'bvc2-tree-source-list':window.APPPATH + 'component/bvc2-tree-source-list/bvc2-tree-source-list',
        'bvc2-tree-member-channel-encode':window.APPPATH + 'component/bvc2-tree-member-channel-encode/bvc2-tree-member-channel-encode',
        'bvc2-system-nav-side':window.APPPATH + 'component/bvc2-system-nav-side/bvc2-system-nav-side',
        'bvc2-system-table-base':window.APPPATH + 'component/bvc2-system-table-base/bvc2-system-table-base',
        'bvc2-dialog-auto-layout':window.APPPATH + 'component/bvc2-dialog-auto-layout/bvc2-dialog-auto-layout',
        'bvc2-tab-agenda-or-scheme':window.APPPATH + 'component/bvc2-tab-agenda-or-scheme/bvc2-tab-agenda-or-scheme',
        'bvc2-dialog-set-forward':window.APPPATH + 'component/bvc2-dialog-set-forward/bvc2-dialog-set-forward',
        'bvc2-dialog-set-audio':window.APPPATH + 'component/bvc2-dialog-set-audio/bvc2-dialog-set-audio',
        'bvc2-transfer-source-target':window.APPPATH + 'component/bvc2-transfer-source-target/bvc2-transfer-source-target',
        'bvc2-transfer-audio-target':window.APPPATH + 'component/bvc2-transfer-audio-target/bvc2-transfer-audio-target',
        'bvc2-transfer-role-target':window.APPPATH + 'component/bvc2-transfer-role-target/bvc2-transfer-role-target',
        'bvc2-dialog-edit-role':window.APPPATH + 'component/bvc2-dialog-edit-role/bvc2-dialog-edit-role',

        /* jquery-components */
        'jquery.layout.auto':window.APPPATH + 'component/jQuery/jQuery.layout.auto/js/jQuery.layout.auto',

        /* pages */
        'page-error':window.APPPATH + 'error/page-error'

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
    'vue',
    'router',
    'context',
    'config',
    'restfull',
    'element-ui'
], function(Vue, router, context, config, ajax){

    //初始化ajax
    ajax.init({
        debug:config.ajax.debug
    });

    var app = new Vue({
        router:router,
        data:function(){
            return {
                loading:false
            };
        }
    }).$mount('#app');

    context.setProp('app', app)
           .setProp('router', router)
           .setProp('user', {
            id:1,
            name:'无敌帅气吕德阳',
            username:'lvdeyang',
            password:'******'
        });

});