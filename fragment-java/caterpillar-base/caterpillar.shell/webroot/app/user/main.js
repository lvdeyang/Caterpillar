/**
 * Created by lvdeyang on 2017/7/7.
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
        'bootstrap':'lib/bootstrap/js/bootstrap.min',
        'datatables':'lib/datatables/jquery.dataTables',
        'iCheck':'lib/iCheck/icheck.min',
        'datatables-bootstrap':'lib/datatables/dataTables.bootstrap.min',
        'context':'app/spa/context',
        'page':'app/spa/page',
        'commons':'app/commons/commons',
        'plugin':'app/commons/demo/plugin-demo',
        'html-alert':'app/chapman/component/alert.html',
        'html-callout':'app/chapman/component/callout.html',
        'html-picbox':'app/chapman/component/picbox.html',
        'html-loader':'app/chapman/component/loader.html',
        'html-modal':'app/chapman/component/modal.html',
        'html-checkgroup':'app/chapman/component/checkgroup.html',
        'page-login':'app/user/login/page-login',
        'page-regist':'app/user/regist/page-regist',
        'page-chapman':'app/user/chapman/page-chapman',
        'html-login':'app/user/login/component/login.html',
        'html-regist':'app/user/regist/component/regist.html',
        'html-chapman':'app/user/chapman/component/chapman.html'

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
            deps: ['jquery'],
            exports:'jQuery'
        }

    }

});

require([

    'context',
    'juicer',
    'page-login',
    'page-regist'

], function (context, juicer){

    //初始化ajax配置
    /*commons.ajax.init({
        authname:'GLWCATCHE001',
        timeouturi:'error',
        messanger:{
            error:function(){}
        }
    });*/

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

    //设置dom
    context.setContentDom($('body'));

    //设置模型
    context.setModel({});

    //更换验证码
    $(document).on('click.code', '#codeImage', function(){
        var $this = $(this);
        $this.attr('src', window.BASEPATH + 'verify/code/get/150/38?'+new Date().getTime());
    });

    //处理hash
    var hash = window.location.hash;
    if(hash){
        var args = hash.replace('#/', '').split('/');
        context.load.apply(context, args);
    }else{
        //跳转到首页
        window.location.href = window.location.href + '#/login';
    }

    //绑定hash事件
    $(document).on('click.hash', '[data-hash]', function(e){
        var $target = $(this);
        window.location.href = window.location.href.split('#')[0] + $target.data('hash');
    });

});
