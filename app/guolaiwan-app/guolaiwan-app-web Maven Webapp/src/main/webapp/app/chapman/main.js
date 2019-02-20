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
        'page-error':'app/chapman/error/page-error',
        'page-home':'app/chapman/home/page-home',
        'page-product-restaurant':'app/chapman/product/restaurant/page-product-restaurant',
        'page-product-scenery':'app/chapman/product/scenery/page-product-scenery',
        'page-product-scenery-detail':'app/chapman/product/scenery/page-product-scenery-detail',
        'page-product-scenery-source':'app/chapman/product/scenery/page-product-scenery-source',
        'page-product-scenery-gd':'app/chapman/product/scenery/page-product-scenery-gd',
        'page-product-scenery-timeline':'app/chapman/product/scenery/page-product-scenery-timeline',
        'page-product-scenery-schedule':'app/chapman/product/scenery/page-product-scenery-schedule',
        'page-product-service':'app/chapman/product/service/page-product-service',
        'page-shelve':'app/chapman/shelve/page-shelve',
        'page-source-audio':'app/chapman/source/audio/page-source-audio',
        'page-source-picture':'app/chapman/source/picture/page-source-picture',
        'page-source-video':'app/chapman/source/video/page-source-video',
        'page-statistics-order':'app/chapman/statistics/order/page-statistics-order',
        'page-statistics-user':'app/chapman/statistics/user/page-statistics-user',
        'html-error':'app/chapman/error/component/error.html',
        'html-alert':'app/chapman/component/alert.html',
        'html-callout':'app/chapman/component/callout.html',
        'html-picbox':'app/chapman/component/picbox.html',
        'html-loader':'app/chapman/component/loader.html',
        'html-modal':'app/chapman/component/modal.html',
        'html-checkgroup':'app/chapman/component/checkgroup.html',
        'html-header':'app/chapman/component/header.html',
        'html-sidebar':'app/chapman/component/sidebar.html',
        'html-foot':'app/chapman/component/foot.html',
        'html-rightbar':'app/chapman/component/rightbar.html',
        'html-content-header':'app/chapman/component/content-header.html',
        'html-source-picture-body':'app/chapman/source/picture/component/body.html',
        'html-source-picture-table':'app/chapman/source/picture/component/table.html',
        'html-source-picture-task':'app/chapman/source/picture/component/task.html',
        'html-product-scenery-body':'app/chapman/product/scenery/component/body-scenery.html',
        'html-product-scenery-detail-body':'app/chapman/product/scenery/component/body-detail.html',
        'html-product-scenery-source-body':'app/chapman/product/scenery/component/body-source.html',
        'html-shelve-body':'app/chapman/shelve/component/body.html',
        'html-shelve-table':'app/chapman/shelve/component/table.html',
        'html-statistics-order-table':'app/chapman/statistics/order/component/statistics-table.html',
        'html-statistics-order-chart':'app/chapman/statistics/order/component/statistics-chart.html'
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
    'commons',
    'juicer',
    'admin-lte',
    'admin-lte-layout',
    'page-error',
    'page-home',
    'page-product-restaurant',
    'page-product-scenery',
    'page-product-scenery-detail',
    'page-product-scenery-source',
    'page-product-scenery-gd',
    'page-product-scenery-timeline',
    'page-product-scenery-schedule',
    'page-product-service',
    'page-shelve',
    'page-source-audio',
    'page-source-picture',
    'page-source-video',
    'page-statistics-order',
    'page-statistics-user'], function (header, sidebar, foot, rightbar, context, commons, juicer, $, layout){

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

    commons.ajax.get(_uri, null, function(data){

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

    });

    //绑定hash事件
    $(document).on('click.hash', '[data-hash]', function(e){
        var $target = $(this);
        window.location.href = window.location.href.split('#')[0] + $target.data('hash');
    });

});


