define(function(){

    var config = {

        //是否开启ajax debug模式
        ajax:{
            debug:false
        },

        //配置默认页面跳转
        redirect:{
            home:'',
            error:'page-error'
        },

        //路由loading延迟
        router:{
            loading:{
                timeout:300
            }
        },

        //页面标题
        page:{
            title:{
                base:'',
                sub:{
                    'page-error':'出错啦'
                },
                separator:'-'
            }
        },

        //导航条文字
        header:{
            text:['', ''],
            href:['', ''],
            icon:[]
        }

    };

    return config;
});
