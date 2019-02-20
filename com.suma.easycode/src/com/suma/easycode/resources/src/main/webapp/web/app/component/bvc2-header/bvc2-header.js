define([
    'text!' + window.APPPATH + 'component/bvc2-header/bvc2-header.html',
    'restfull',
    'vue',
    'element-ui'
], function(tpl, ajax, Vue){

    //组件名称
    var bvc2Header = 'bvc2-header';

    //进入个人中心
    var enterPersonalCenter = function(){
        console.log('个人中心');
        console.log(this);
    };

    //注销登录
    var doLogout = function(){
        console.log('注销登录');
        console.log(this);
    };

    Vue.component(bvc2Header, {
        props: ['links', 'user'],
        template: tpl,
        data:function(){
            return {
                commands:[enterPersonalCenter, doLogout]
            }
        },
        methods:{
            handleCommand:function(command){
                this.commands[parseInt(command)].apply(this);
            }

        }
    });

    return Vue;
});