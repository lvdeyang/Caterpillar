/**
 * Created by lvdeyang on 2017/7/7.
 */
define([

    'text!html-chapman',
    'juicer',
    'context',
    'page',
    'bootstrap',
    'commons'

], function(chapman, juicer, context, page, $, commons){

    var PAGENAME = 'chapman';

    var prepare_error = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-alert" style="color:#dd4b39;"></i> ${message}</div>');

    var prepare_success = juicer('<div style="font-size:13px; margin-top:1em;"><i class="glyphicon glyphicon-ok" style="color:#00a65a;"></i> $${message}</div>');

    var _timeout = null;

    var interval = null;

    var _init = function(){

        var _uri = 'website/get/user/info';

        commons.ajax.get(_uri, null, function(data){

            if(!data.user){
                if(data.chapman){
                    context.setContent($('<div style="width:100%; height:100%; display:table;"><div style="font-size:58px; font-weight:900; color:#BF985F; width:100%; height:100%; display:table-cell; vertical-align:middle; text-align:center;">您已经申请过商户，<a data-hash=' + window.BASEPATH + '"website/home">返回主页</a>!</div></div>'));
                    return;
                }
                context.setContent($('<div style="width:100%; height:100%; display:table;"><div style="font-size:58px; font-weight:900; color:#BF985F; width:100%; height:100%; display:table-cell; vertical-align:middle; text-align:center;">您还没有登录，请先<a data-hash="#/login">登录</a>!</div></div>'));
                return;
            }

            context.setModel({
                user:data.user
            });

            context.setContent($(juicer(chapman).render(window)));

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

            var _uri = 'chapman/regist/' + context.model.user.id;

            $('#button-regist').on('click', function(){

                var $button = $(this);
                $button.attr('disabled', true);

                if(_timeout){
                    $message.empty();
                    clearTimeout(_timeout);
                    _timeout = null;
                }

                commons.ajax.post(_uri, {

                    chapmanname:$('#chapmanname').val(),
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
                        message:'注册成功，'+time+'s后自动跳转商户页面。<a id="link-chapman-home" style="cursor:pointer;">手动跳转</a>'
                    }));

                    interval = setInterval(function(){
                        time = time - 1;
                        $message.empty().append(prepare_success.render({
                            message:'注册成功，'+time+'s后自动跳转商户页面。<a id="link-chapman-home" style="cursor:pointer;">手动跳转</a>'
                        }));
                        if(time === 0){
                            clearInterval(interval);
                            interval = null;
                            window.location.href = window.BASEPATH + 'chapman/home';
                        }
                    }, 1000);


                }, [200, 403, 404]);

            });

            $message.on('click', '#link-user-login', function(){
                if(interval){
                    clearInterval(interval);
                    interval = null;
                }
                window.location.href = window.BASEPATH + 'chapman/home';
            });

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});