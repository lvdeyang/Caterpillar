/**
 * Created by lvdeyang on 2017/6/20.
 */

//建立映射
require.config({

    baseUrl: window.BASEPATH,

    paths: {
        'text':'lib/requireJS/plugins/text',
        'director':'lib/directorJS/director',
        'juicer':'lib/juicer/juicer-v0.6.5-min',
        'jquery': 'lib/jQuery/jquery-2.2.3.min',
        'json':'lib/jQuery/jquery.json',
        'tree':'lib/tree/js/tree',
        'slimscroll':'lib/slimScroll/jquery.slimscroll.min',
        'inputmask':'lib/input-mask/jquery.inputmask',
        'inputmask-date':'lib/input-mask/jquery.inputmask.date.extensions',
        'bootstrap':'lib/bootstrap/js/bootstrap.min',
        'datatables':'lib/datatables/jquery.dataTables',
        'datatables-bootstrap':'lib/datatables/dataTables.bootstrap.min',
        'iCheck':'lib/iCheck/icheck.min',
        'chart':'lib/chartjs/Chart',
        'admin-lte':'lib/admin-lte/js/app',
        'admin-lte-layout':'lib/admin-lte/js/layout',
        'context':'app/spa/context',
        'page':'app/spa/page',
        'commons':'app/commons/commons',
        'page-error':'app/admin/error/page-error',
        'page-home':'app/admin/home/page-home',
        'page-menu':'app/admin/menu/page-menu',
        'page-tag':'app/admin/tag/page-tag',
        'page-layout':'app/admin/layout/page-layout',
        'page-category':'app/admin/category/page-category',
        'html-error':'app/admin/error/component/error.html',
        'html-alert':'app/admin/component/alert.html',
        'html-callout':'app/admin/component/callout.html',
        'html-picbox':'app/admin/component/picbox.html',
        'html-loader':'app/admin/component/loader.html',
        'html-modal':'app/admin/component/modal.html',
        'html-checkgroup':'app/admin/component/checkgroup.html',
        'html-header':'app/admin/component/header.html',
        'html-sidebar':'app/admin/component/sidebar.html',
        'html-foot':'app/admin/component/foot.html',
        'html-rightbar':'app/admin/component/rightbar.html',
        'html-content-header':'app/admin/component/content-header.html',
        'html-menu-body':'app/admin/menu/component/body.html',
        'html-tag-body':'app/admin/tag/component/body.html',
        'html-layout-body-layout':'app/admin/layout/component/body-layout.html',
        'html-layout-body-page':'app/admin/layout/component/body-page.html',
        'html-layout-body-section':'app/admin/layout/component/body-section.html',
        'html-layout-body-filter':'app/admin/layout/component/body-filter.html',
        'html-category-body':'app/admin/category/component/body.html'
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

        'inputmask':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'inputmask-date':{
            deps: ['inputmask'],
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
            deps: ['json', 'slimscroll', 'datatables-bootstrap', 'iCheck', 'inputmask-date'],
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
    'commons',
    'juicer',
    'admin-lte',
    'admin-lte-layout',
    'page-error',
    'page-home',
    'page-menu',
    'page-tag',
    'page-layout',
    'page-category'
   ], function (header, sidebar, foot, rightbar, context, commons, juicer, $, layout){

    //初始化ajax配置
    commons.ajax.init({
        authname:'GLWCATCHE001',
        timeouturi:'error'
    });

    //初始化messanger
    commons.messanger.init({
        type:'alert',
        timeout:3,
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

    var _uri = 'website/get/user/info';

    /*commons.ajax.get(_uri, null, function(data){

        if(!data.user || !data.admin || !data.root){
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
            admin:data.admin,
            root:data.root
        });

    });*/

    context.setModel({
        user:{
            id:5,
            username:'lvdeyang'
        }
    });

    $wrapper.append(juicer(header).render(context.model))
        .append(juicer(sidebar).render(context.model))
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


