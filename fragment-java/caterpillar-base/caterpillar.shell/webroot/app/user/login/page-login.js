/**
 * Created by lvdeyang on 2017/7/7.
 */
define([

    'text!html-login',
    'juicer',
    'context',
    'page',
    'bootstrap',
    'plugin'

], function(login, juicer, context, page, $){

    var commons = commons;

    var PAGENAME = 'login';

    var prepare_error = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-alert" style="color:#dd4b39;"></i> ${message}</div>');

    var prepare_success = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-ok" style="color:#00a65a;"></i> $${message}</div>');

    var _timeout = null;

    var interval = null;

    var _init = function(){

        context.setContent($(juicer(login).render(window)));

        $('#demo').Demo001('create', {
            items:[
                {name:'张三'},
                {name:'李四'},
                {name:'王五'},
                {name:'朱六'}
            ]
        });

        var $message = $('#message');

        var _uri = 'user/do/login';

        $('#button-login').on('click', function(){

            var $button = $(this);
            $button.attr('disabled', true);

            if(_timeout){
                $message.empty();
                clearTimeout(_timeout);
                _timeout = null;
            }

            commons.ajax.post(_uri, {

                username:$('#username').val(),
                password:$('#password').val(),
                code:$('#code').val()

            }, function(data, status, message){

                if(status !== 200){
                    $message.append(prepare_error.render({message:message}));
                    _timeout = setTimeout(function(){
                        $message.empty();
                        _timeout = null;
                    }, 5000);
                    //更换验证码
                    $('#codeImage').click();
                    $button.removeAttr('disabled');
                    return;
                }

                commons.cache.doLogin(data.key);

                var time = 3;

                $message.empty().append(prepare_success.render({
                    message:'登录成功，'+time+'s后自动跳转首页。<a id="link-website-home" style="cursor:pointer;">手动跳转</a>'
                }));

                interval = setInterval(function(){
                    time = time - 1;
                    $message.empty().append(prepare_success.render({
                        message:'登录成功，'+time+'s后自动跳转首页。<a id="link-website-home" style="cursor:pointer;">手动跳转</a>'
                    }));
                    if(time === 0){
                        clearInterval(interval);
                        interval = null;
                        window.location.href = window.BASEPATH + 'website/home';
                    }
                }, 1000);

            }, [200, 403, 404]);

        });

        $message.on('click', '#link-website-home', function(){
            if(interval){
                clearInterval(interval);
                interval = null;
            }
            window.location.href = window.BASEPATH + 'website/home';
        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});