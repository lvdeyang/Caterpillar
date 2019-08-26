define([
    'text!' + window.APPPATH + 'login/tpl/tpl-login.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    var init = function(p){

        var $login = document.getElementById('page-login');
        $login.innerHTML = tpl;

        var vm_login = new Vue({
            el:'#login-card',
            data:{
                username:p.username,
                password:p.password,
                role:'',
                rememberMe:true
            },
            methods:{
                doLogin:function(e){
                    alert(this.username + ' ' + this.password + ' '+this.role + ' ' + this.rememberMe);
                },
                doRegister:function(e){
                    alert('去注册！');
                }
            }
        });

    };

    var destroy = function(){

    };

    var login = {
        path:'/page-login/:username/:password',
        component:{
            template:'<div id="page-login"></div>'
        },
        init:init,
        destroy:destroy
    };

    return login;
});
