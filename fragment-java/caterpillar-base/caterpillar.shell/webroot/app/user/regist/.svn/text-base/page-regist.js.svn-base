/**
 * Created by lvdeyang on 2017/7/7.
 */
define([

    'text!html-regist',
    'juicer',
    'context',
    'page',
    'bootstrap'

], function(regist, juicer, context, page, $){

    var commons = commons;

    var PAGENAME = 'regist';

    var prepare_error = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-alert" style="color:#dd4b39;"></i> ${message}</div>');

    var prepare_success = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-ok" style="color:#00a65a;"></i> $${message}</div>');

    var _timeout = null;

    var interval = null;

    var _init = function(){

        context.setContent($(juicer(regist).render(window)));

        var $message = $('#message');

        $('#checkbox-accept').on('click', function(){
            var $checkbox = $(this);
            var $button_regist = $('#button-regist');
            if($checkbox.is(':checked')){
                $button_regist.removeAttr('disabled');
            }else{
                $button_regist.attr('disabled', true);
            }
        });

        var _uri = 'user/do/regist';

        $('#button-regist').on('click', function(){

            var $button = $(this);
            $button.attr('disabled', true);

            if(_timeout){
                $message.empty();
                clearTimeout(_timeout);
                _timeout = null;
            }

            commons.ajax.post(_uri, {

                username:$('#username').val(),
                email:$('#email').val(),
                password:$('#password').val(),
                confirmPass:$('#confirmPass').val(),
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

                var time = 3;

                $message.empty().append(prepare_success.render({
                    message:'注册成功，'+time+'s后自动跳转登录。<a id="link-user-login" style="cursor:pointer;">手动跳转</a>'
                }));

                interval = setInterval(function(){
                    time = time - 1;
                    $message.empty().append(prepare_success.render({
                        message:'注册成功，'+time+'s后自动跳转登录。<a id="link-user-login" style="cursor:pointer;">手动跳转</a>'
                    }));
                    if(time === 0){
                        clearInterval(interval);
                        interval = null;
                        window.location.href = window.BASEPATH + 'user/home#/login';
                    }
                }, 1000);

            }, [200, 403, 404]);

        });

        $message.on('click', '#link-user-login', function(){
            if(interval){
                clearInterval(interval);
                interval = null;
            }
            window.location.href = window.BASEPATH + 'user/home#/login';
        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});