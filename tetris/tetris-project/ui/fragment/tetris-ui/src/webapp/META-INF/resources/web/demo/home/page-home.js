define([
    'text!' + window.APPPATH + 'home/tpl/tpl-home.html',
    'vue',
    'demo'
], function(tpl, Vue){

    var init = function(){

        var $home = document.getElementById('page-home');
        console.log($home.innerHTML);
        $home.innerHTML = tpl;

        var vm09 = new Vue({
            el:'#my-component',
            data:{
                items:[
                    {
                        id:1,
                        name:'员工1',
                        sex:'0',
                        age:23
                    },{
                        id:2,
                        name:'员工2',
                        sex:'1',
                        age:20
                    },{
                        id:3,
                        name:'员工3',
                        sex:'0',
                        age:27
                    },{
                        id:4,
                        name:'员工4',
                        sex:'0',
                        age:24
                    },{
                        id:5,
                        name:'员工5',
                        sex:'1',
                        age:20
                    },{
                        id:6,
                        name:'员工6',
                        sex:'0',
                        age:23
                    }
                ]
            }
        });

    };

    var destroy = function(){

    };

    var home = {
        path:'/page-home',
        component:{
            template:'<div id="page-home"></div>'
        },
        init:init,
        destroy:destroy
    };

    return home;
});
