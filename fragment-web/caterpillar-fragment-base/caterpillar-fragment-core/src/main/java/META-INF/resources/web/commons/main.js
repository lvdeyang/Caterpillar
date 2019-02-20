/**
 * Created by lvdeyang on 2017/6/20.
 */

//建立映射
require.config({

    baseUrl: window.BASEPATH,

    paths: {
        'text':window.LIBPATH + 'frame/requireJS/plugins/text',
        'director':window.LIBPATH + 'frame/directorJS/director',
        'juicer':window.LIBPATH + 'frame/juicer/juicer-v0.6.5',
        'jquery':window.LIBPATH + 'frame/jQuery/jquery-2.2.3',
        'json':window.LIBPATH + 'frame/jQuery/jquery.json',
        'slimscroll':window.LIBPATH + 'ui/slimScroll/jquery.slimscroll.min',
        'bootstrap':window.LIBPATH + 'ui/bootstrap/js/bootstrap',
        'datatables':window.LIBPATH + 'ui/datatables/jquery.dataTables',
        'datatables-bootstrap':window.LIBPATH + 'ui/datatables/dataTables.bootstrap.min',
        'iCheck':window.LIBPATH + 'ui/iCheck/icheck.min',
        'chart':window.LIBPATH + 'reports/chartjs/Chart',
        'admin-lte':window.LIBPATH + 'template/admin-lte/js/app',
        'admin-lte-layout':window.LIBPATH + 'template/admin-lte/js/layout',
        
        'context':window.WEBBASIC + 'spa/context',
        'page':window.WEBBASIC + 'spa/page',

        'date':window.COMMONSPATH + 'date/date.ext',
        'string':window.COMMONSPATH + 'string/string.ext',
        'storage':window.COMMONSPATH + 'storage/storage.ext',
        'restfull':window.COMMONSPATH + 'restfull/restfull.api',
        'datatables-zkCN':window.COMMONSPATH + 'ui/admin-lte/datatables/ui-datatables-lang-zhCN',

        'tree':window.COMMONSPATH + 'ui/custom/tree/js/tree',

        'alt-loader':window.COMMONSPATH + 'ui/admin-lte/loader/ui-loader',
        'alt-messenger':window.COMMONSPATH + 'ui/admin-lte/messenger/ui-messenger',
        'alt-modal':window.COMMONSPATH + 'ui/admin-lte/modal/ui-modal',
        'alt-select':window.COMMONSPATH + 'ui/admin-lte/select/normal/ui-select',
        'alt-tree-select':window.COMMONSPATH + 'ui/admin-lte/select/tree/ui-tree-select',
        'alt-switch-select':window.COMMONSPATH + 'ui/admin-lte/select/switch/ui-switch-select',
        'alt-spinner':window.COMMONSPATH + 'ui/admin-lte/input/spinner/ui-spinner',
        'alt-checkbox':window.COMMONSPATH + 'ui/admin-lte/checkbox/ui-checkbox',
        'alt-tab':window.COMMONSPATH + 'ui/admin-lte/tab/ui-tab',

        'commons':window.COMMONSPATH + 'commons',

        'html-header':window.COMMONSPATH + 'pages/component/header.html',
        'html-sidebar':window.COMMONSPATH + 'pages/component/sidebar.html',
        'html-foot':window.COMMONSPATH + 'pages/component/foot.html',
        'html-rightbar':window.COMMONSPATH + 'pages/component/rightbar.html',
        'html-content-header':window.COMMONSPATH + 'pages/component/content-header.html',

        'page-error':window.COMMONSPATH + 'pages/error/page-error',
        'page-home':window.COMMONSPATH + 'pages/home/page-home'

    },

    shim:{

        'director':{
            deps: [],
            exports: 'Router'
        },

        'juicer':{
            deps: [],
            exports: 'juicer'
        },

        'json':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'tree':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'slimscroll':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'bootstrap':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'datatables':{
            deps: ['jquery'],
            exports:'jQuery'
        },

        'datatables-bootstrap':{
            deps: ['bootstrap', 'datatables'],
            exports:'jQuery'
        },

        'iCheck':{
            deps:['jquery'],
            exports:'jQuery'
        },

        'chart':{
            exports:'Chart'
        },

        'admin-lte':{
            deps: ['json', 'slimscroll', 'datatables-bootstrap', 'iCheck'],
            exports: 'jQuery'
        }

    }

});

require([
    'text!html-header',
    'text!html-sidebar',
    'text!html-foot',
    'text!html-rightbar',
    'context',
    'restfull',
    'commons',
    'juicer',
    'admin-lte',
    'admin-lte-layout',
    'date',
    'string',
    'alt-messenger',
    'datatables-zkCN'
], function (header, sidebar, foot, rightbar, context, ajax, commons, juicer, $, layout){

    $('html').css('height', '100%');
    $('body').css('height', '100%');

    //初始化messenger
    $.fn['alt-messenger']('init',{
        type:'alert',
        timeout:3
    });

    //初始化ajax配置
    ajax.init({
        debug:true,
        authname:'GLWCATCHE001',
        timeouturi:'error',
        messanger:{
            info:function(msg, status){
                $.fn.messenger('info', msg);
            },
            error:function(msg, status){
                $.fn.messenger('error', msg);
            },
            warning:function(msg, status){
                $.fn.messenger('warning', msg);
            },
            success:function(msg){
                $.fn.messenger('success', msg);
            }
        }
    });

    //设置模板引擎
    juicer.set({
        'tag::operationOpen': '{@',    //操作标记（循环，判断）
        'tag::operationClose': '}',
        'tag::interpolateOpen': '${',  //插值转义标记
        'tag::interpolateClose': '}',
        'tag::noneencodeOpen': '$${',  //不转义标记
        'tag::noneencodeClose': '}',
        'tag::commentOpen': '{#',       //注释标记
        'tag::commentClose': '}',
        'cache':false,
        'strip':true,
        'errorhandling':true,
        'detection':true
    });

    var $wrapper = $('body>.wrapper');
    var $content = $('<div class="content-wrapper"></div>');
    var $rightbarBg = $('<div class="control-sidebar-bg"></div>');

    //设置dom
    context.setContentDom($content);

    /*var _uri = 'website/get/user/info';

    ajax.get(_uri, null, function(data){

        if(!data.user || !data.chapman || !data.root){
            $wrapper.append($content);
            context.load('error', {
                status:403,
                message:'超时，请重新登录'
            });
            return;
        }

        //设置模型
        context.setModel({
            user:data.user,
            chapman:data.chapman,
            root:data.root
        });

    });*/

    //处理path
    var path = {
        base:window.BASEPATH,
        app:window.BASEPATH + window.APPPATH,
        lib:window.BASEPATH + window.LIBPATH
    };

    $wrapper.append(juicer(header).render({path:path, user:{username:'测试账户'}}))
            .append(juicer(sidebar).render({path:path, chapman:{chapmanname:'测试商户'}}))
            .append($content)
            .append(juicer(foot).render({}))
            .append(juicer(rightbar).render({}))
            .append($rightbarBg);

    //初始化右边栏
    $.AdminLTE.controlSidebar.activate();

    //初始化皮肤编辑器
    layout.init_themeEditor();

    //处理hash
    var hash = window.location.hash;
    if(hash){
        var args = hash.replace('#/', '').split('/');
        context.load.apply(context, args);
    }else{
        //跳转到首页
        window.location.href = window.location.href + '#/home';
    }

    //绑定hash事件
    $(document).on('click.hash', '[data-hash]', function(e){
        var $target = $(this);
        window.location.href = window.location.href.split('#')[0] + $target.data('hash');
    });

});


