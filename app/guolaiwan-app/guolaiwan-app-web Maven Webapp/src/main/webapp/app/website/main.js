/**
 * Created by lvdeyang on 2017/6/30.
 */

//建立映射
require.config({

    baseUrl: window.BASEPATH,

    paths: {
        'text':'lib/requireJS/plugins/text',
        'director':'lib/directorJS/director',
        'juicer':'lib/juicer/juicer-v0.6.5-min',
        'jquery': 'lib/travel/js/jquery.min',
        'json':'lib/jQuery/jquery.json',
        'iCheck':'lib/iCheck/icheck.min',
        'jquery-easing':'lib/travel/js/jquery.easing.1.3',
        'bootstrap':'lib/travel/js/bootstrap.min',
        'jquery-waypoints':'lib/travel/js/jquery.waypoints.min',
        'sticky':'lib/travel/js/sticky',
        'jquery-stellar':'lib/travel/js/jquery.stellar.min',
        'hoverIntent':'lib/travel/js/hoverIntent',
        'superfish':'lib/travel/js/superfish',
        'jquery-magnific-popup':'lib/travel/js/jquery.magnific-popup.min',
        'magnific-popup-options':'lib/travel/js/magnific-popup-options',
        'bootstrap-datepicker':'lib/travel/js/bootstrap-datepicker.min',
        'classie':'lib/travel/js/classie',
        'selectFx':'lib/travel/js/selectFx',
        'travel':'lib/travel/js/main',
        'context':'app/spa/context',
        'page':'app/spa/page',
        'datatables':'lib/datatables/jquery.dataTables',
        'datatables-bootstrap':'lib/datatables/dataTables.bootstrap.min',
        'html-alert':'app/chapman/component/alert.html',
        'html-callout':'app/chapman/component/callout.html',
        'html-picbox':'app/chapman/component/picbox.html',
        'html-loader':'app/chapman/component/loader.html',
        'html-modal':'app/chapman/component/modal.html',
        'html-checkgroup':'app/chapman/component/checkgroup.html',
        'commons':'app/commons/commons',
        'html-head':'app/website/component/head.html',
        'html-hero':'app/website/component/hero.html',
        'html-foot':'app/website/component/foot.html',
        'html-tours':'app/website/component/tours.html',
        'html-hero':'app/website/component/hero.html',
        'html-timeline':'app/website/component/timeline.html',
        'html-basket':'app/website/basket/component/basket.html',
        'html-order':'app/website/basket/component/order.html',
        'html-info-body':'app/website/info/component/body.html',
        'html-error':'app/website/error/component/error.html',
        'page-home':'app/website/home/page-home',
        'page-basket':'app/website/basket/page-basket',
        'page-info':'app/website/info/page-info',
        'page-error':'app/website/error/page-error'
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

        'jquery-easing':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'bootstrap':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'jquery-waypoints':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'sticky':{
            deps: ['jquery-waypoints'],
            exports: 'Sticky'
        },

        'jquery-stellar':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'hoverIntent':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'superfish':{
            deps: ['hoverIntent'],
            exports: 'jQuery'
        },

        'jquery-magnific-popup':{
            deps: ['jquery'],
            exports: 'jQuery'
        },

        'magnific-popup-options':{
            deps: ['jquery-magnific-popup'],
            exports: 'jQuery'
        },

        'bootstrap-datepicker':{
            deps: ['bootstrap'],
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
            deps: ['jquery'],
            exports:'jQuery'
        }

    }

});

require([

    'text!html-head',
    'text!html-foot',
    'context',
    'commons',
    'juicer',
    'travel',
    'jquery',
    'page-home',
    'page-basket',
    'page-info',
    'page-error'

    ], function (head, foot, context, commons, juicer, travel, $){

    //初始化ajax配置
    commons.ajax.init({
        authname:'GLWCATCHE001',
        timeouturi:'error',
        messanger:{
            error:function(message, status){
                context.load('error', {
                    status:status,
                    message:message
                })
            }
        }
    });

    //初始化messanger
    /*commons.messanger.init({
        type:'alert',
        timeout:3,
    });*/

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

    var prepare_head = juicer(head);

    var _uri = 'website/menus/get';

    commons.ajax.get(_uri, null, function(data){

        var $page = $('body').find('#fh5co-page');

        var $content = $('<div></div>');

        var $head = $(prepare_head.render({
            login:window.BASEPATH + 'user/home#/login',
            register:window.BASEPATH + 'user/home#/regist',
            menus:data.menus
        }));

        $page.append($head).append($content).append(foot);

        //初始化模板--菜单
        travel.initHead();

        //设置dom
        context.setContentDom($content);

        _uri = 'website/get/user/info';

        commons.ajax.get(_uri, null, function(data){

            if(data.user){

                //设置模型
                context.setModel({
                    user:data.user
                });

                $('.button-shopping-cart').find('.label').text(data.basketNum);

                var $login_scope = $('#login-scope');

                var $li = $login_scope.find('li');

                var $first = $($li[0]).empty().append('<a data-hash="#/user-detail" style="cursor:pointer;"><i class="icon-user3" style="top:3px;"></i> '+data.user.username+'</a>');

                if(data.chapman){
                    $first.append('<ul class="fh5co-sub-menu"><li><a href="#">个人中心</a></li><li><a class="use-href" href="' + window.BASEPATH + 'chapman/home">商户中心</a></li></ul>');
                }else{
                    $first.append('<ul class="fh5co-sub-menu"><li><a href="#">个人中心</a></li><li><a class="use-href" href="' + window.BASEPATH + 'user/home#/chapman">申请商户</a></li></ul>');
                }

                var $second = $($li[1]).empty().append('<a class="button-logout" style="cursor:pointer;"><i class="icon-log-out"></i> 注销</a>');

                $second.find('.button-logout').on('click', function(){

                    var _uri = 'user/do/logout';

                    commons.ajax.post(_uri, null, function(){
                        $first.empty().append('<a class="use-href" href="' + window.BASEPATH + 'user/home#/login"><i class="icon-login" style="top:3px;"></i> 登录</a>');
                        $second.empty().append('<a class="use-href" href="' + window.BASEPATH + 'user/home#/regist"><i class="icon-user-plus"></i> 注册</a>');

                        //登出
                        commons.cache.doLogout();

                        context.setModel($.extend(true, context.model, {user:null}));
                    });

                });

            }

            //处理a标签
            commons.attach.disableLink();

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

    });

});