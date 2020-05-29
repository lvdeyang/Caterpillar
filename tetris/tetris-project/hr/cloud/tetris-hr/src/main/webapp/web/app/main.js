require.config({
    baseUrl: window.BASEPATH,
    paths: {
        /* lib */
        'text':window.LIBPATH + 'frame/requireJS/plugins/text',
        'css':window.LIBPATH + 'frame/requireJS/plugins/css',
        'vue':window.LIBPATH + 'frame/vue/vue-2.5.16',
        'vue-router':window.LIBPATH + 'frame/vue/vue-router-3.0.1',
        'jquery':window.LIBPATH + 'frame/jQuery/jquery-2.2.3.min',
        'json':window.LIBPATH + 'frame/jQuery/jquery.json',
        'element-ui':window.LIBPATH + 'ui/element-ui/element-ui-2.4.3.min',

        'TweenLite':window.LIBPATH + 'TweenMax/cmd/TweenLite',
        'echarts':window.LIBPATH + 'echarts/echarts.min',
        /* commons */
        'context':window.COMMONSPATH + 'context/context',
        'page-wrapper':window.COMMONSPATH + 'page/page-wrapper',
        'router':window.COMMONSPATH + 'router/router',
        'date':window.COMMONSPATH + 'date/date.ext',
        'string':window.COMMONSPATH + 'string/string.ext',
        'storage':window.COMMONSPATH + 'storage/storage.ext',
        'restfull':window.COMMONSPATH + 'restfull/restfull.api',
        'file':window.COMMONSPATH + 'uploader/File',
        'uploader':window.COMMONSPATH + 'uploader/Uploader',
        'menu':window.COMMONSPATH + 'menu/menu',
        'bpmn-ext':window.COMMONSPATH + 'bpmn/ext/BpmnExtension',

        /* app */
        'config':window.APPPATH + 'config',
        'commons':window.APPPATH + 'commons',

        /* error */
        'page-error':window.APPPATH + 'error/page-error',

        /* component */
        'mi-frame':window.APPPATH + 'component/frame/frame',
        'mi-sub-title':window.APPPATH + 'component/sub-title/sub-title',
        'mi-folder-dialog':window.APPPATH + 'component/dialog/folder/folder-dialog',
        'mi-task-view':window.APPPATH + 'component/view/task/task-view',
        'mi-upload-dialog':window.APPPATH + 'component/dialog/upload/upload-dialog',
        'mi-lightbox':window.APPPATH + 'component/lightbox/lightbox',
        'mi-user-dialog':window.APPPATH + 'component/dialog/user/user-dialog',
        'mi-system-role-dialog':window.APPPATH + 'component/dialog/system-role/system-role-dialog',
        'mi-business-role-dialog':window.APPPATH + 'component/dialog/business-role/business-role-dialog',
        'mi-theme-dialog':window.APPPATH + 'component/dialog/theme/theme-dialog',
        'mi-tag-dialog':window.APPPATH + 'component/dialog/mims/tag/mims-tag',

        'native-record-player':window.APPPATH + 'jQuery/zk_Player/zk_RecordPlayer/js/zk_RecordPlayer',
        'native-sip-player':window.APPPATH + 'jQuery/zk_Player/zk_SipPlayer/js/zk_SipPlayer',
        'player':window.APPPATH + 'jQuery/player/js/Tetris.player',
        'player-panel':window.APPPATH + 'jQuery/playerPanel/js/Tetris.playerPanel',
        'jquery.layout.auto':window.APPPATH + 'jQuery/jQuery.layout.auto/js/jQuery.layout.auto',
        /* pages */
        'page-hr-org':window.APPPATH + 'org/page-hr-org',
        'page-hr-sche':window.APPPATH + 'sche/page-hr-sche',
        'page-hr-set':window.APPPATH + 'set/page-hr-set',
        'page-hr-device':window.APPPATH + 'device/page-hr-device',
        'page-hr-attend':window.APPPATH + 'set/page-hr-attend',
        'page-bvc-monitor':window.APPPATH + 'monitor/page-bvc-monitor',
        'page-bvc-detail':window.APPPATH + 'monitor/page-bvc-detail',
        'page-bvc-report':window.APPPATH + 'monitor/page-bvc-report',
        /*5g*/
        'bvc2-monitor-terminal5g-live':window.APPPATH + 'terminal5g/bvc2-monitor-terminal5g-live',
        'page-device5g':window.APPPATH + 'terminal5g/page-device5g'
    },
    shim:{
        'vue':{
            exports: 'Vue'
        },
        'vue-router':{
            deps: ['vue'],
            exports: 'VueRouter'
        },
        'element':{
            deps:['vue']
        },
        'jquery':{
        	exports:'jQuery'
        },
        'json':{
        	deps:['jquery'],
        	exports:'jQuery'
        },
        'native-record-player':{
            deps:['jquery'],
            exports:'jQuery'
        },
        'native-sip-player':{
            deps:['jquery'],
            exports:'jQuery'
        },
        'player':{
            deps:['jquery'],
            exports:'jQuery'
        },
        'player-panel':{
            deps:['player'],
            exports:'jQuery'
        }
    }
});

require([
    'storage',
    'vue',
    'router',
    'context',
    'menu',
    'config',
    'jquery',
    'restfull',
    'element-ui',
    'css!' + window.APPPATH + 'reset.css'
], function(storage, Vue, router, context, menuUtil, config, $, ajax){

    var app = null;

    //缓存token
    storage.setItem(config.ajax.header_auth_token, window.TOKEN);
    storage.setItem(config.ajax.header_session_id, window.SESSIONID);

    //初始化ajax
    ajax.init({
        login:config.ajax.login,
        authname:config.ajax.header_auth_token,
        sessionIdName:config.ajax.header_session_id,
        debug:config.ajax.debug,
        messenger:{
            info:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
            	app.$message({
                    message: message,
                    type: 'info'
                });
            },
            success:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'success'
                });
            },
            warning:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'warning'
                });
            },
            error:function(message, status){
                var app = context.getProp('app');
            	if(!app) return;
                app.$message({
                    message: message,
                    type: 'error'
                });
            }
        }
    });

    ajax.get('/prepare/app', null, function(appInfo){

        //初始化全局vue实例
        var app = new Vue({
            router:router,
            data:function(){
                return {
                    loading:false
                };
            },
            methods:{
                //players:[{code:'播放器号码', username:'用户名', password:'密码', $embed:'embed插件'}]
                registerPlayer:function(players){
                    var self = this;
                    console.log('播放器注册：');
                    console.log(players);
                    if(players && players.length>0){
                        for(var i=0; i<players.length; i++){
                            if(!players[i].code){
                                console.log('无效的播放器注册！');
                                console.log(players[i]);
                                continue;
                            }
                            self.$players.push(players[i]);
                            self.$sipPlugin[0].I_Register(players[i].code, players[i].username, players[i].password);
//                            self.$sipPlugin[0].I_Register(players[i].code, players[i].username, players[i].code);
                        }
                    }
                },
                unRegisterPlayer:function(players){
                    var self = this;
                    console.log('播放器注销：');
                    console.log(players);
                    if(players && players.length>0){
                        for(var i=0; i<players.length; i++){
                            if(!players[i].code){
                                console.log('无效的播放器注销！');
                                console.log(players[i]);
                                continue;
                            }
                            if(typeof players[i].$embed[0].I_Stop === 'function'){
                                players[i].$embed[0].I_Stop();
                            }
                            self.$sipPlugin[0].I_UnRegister(players[i].code);
                        }
                        for(var i=0; i<players.length; i++){
                            for(var j=0; j<self.$players.length; j++){
                                if(players[i].code === self.$players[j].code){
                                    self.$players.splice(j, 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            },
            created:function(){
                var self = this;
                self.$sipPlugin = $('<embed style="z-index:-1; position:absolute; width:0; height:0;" type="application/media-suma-sipclient"/>');
                self.$players = [];
                $('body').append(self.$sipPlugin);
                /*ajax.post('/monitor/device/find/web/sip/players', null, function(data){
                    if(data && data.length>0){
                        var player = data[0];
                        self.$nextTick(function(){
                            window.startPlay = function(code, url){
                                console.log('开始播放：'+code+'   '+url);
                                for(var i=0; i<self.$players.length; i++){
                                    if(self.$players[i].code === code){
                                        if(self.$players[i].$embed.data('targetType') === 'BVC'){
                                            console.log('音视频同轴处理：');
                                            console.log(url);
                                        }
                                        self.$players[i].$embed[0].I_Play(url);
                                        break;
                                    }
                                }
                            };
                            window.stopPlay = function(code){
                                console.log('停止播放：'+code);
                                for(var i=0; i<self.$players.length; i++){
                                    if(self.$players[i].code === code){
                                        self.$players[i].$embed[0].I_Stop();
                                        break;
                                    }
                                }
                            };
                            console.log('sip插件初始化:'+player.registerLayerIp+':'+player.registerLayerPort+'  '+player.ip+'  '+player.port);
                            self.$sipPlugin[0].I_Init(player.registerLayerIp+':'+player.registerLayerPort, player.ip);
                            self.$sipPlugin[0].pluginCallback = function(action, code, url){
                                console.log('插件回调：'+action + ' ' + code + ' ' +url);
                                if(action === 'start'){
                                    window.startPlay(code, url);
                                }else{
                                    window.stopPlay(code);
                                }
                            }
                        });
                    }else{
                       
                    }
                });*/
            }
        }).$mount('#app');

        appInfo.menus = appInfo.menus || [];
        
        //初始化上下文环境
        context.setProp('app', app)
               .setProp('router', router)
               .setProp('user', appInfo.user)
               .setProp('groups', appInfo.groups || [])
               .setProp('token', window.TOKEN);

        
        window.onbeforeunload = function(e){
        	//这个地方要用同步请求
        	$.ajax({
                url:window.HOST + window.SCHEMA + '/monitor/live/remove/all/webplayer/live',
                async: false
            });
            app.unRegisterPlayer(app.$players);
            console.log('sip插件销毁');
            app.$sipPlugin[0].I_UnInit();
        };
        
        //处理皮肤
        if(appInfo.user.themeUrl) $('head').prepend('<link rel="stylesheet" type="text/css" href="'+window.BASEPATH + appInfo.user.themeUrl+'"/>');

        //解析模板
        menuUtil.parseUrlTemplate(appInfo.menus);

        //缓存菜单
        context.setProp('menus', appInfo.menus);

        //获取活动页
        var activeMenu = menuUtil.getActiveMenu(appInfo.menus);

        //重置首页
        if(activeMenu){
            config.redirect.home = activeMenu.link.split('#/')[1];
        }

        //跳转首页
        if(!window.location.hash || window.location.hash==='#/') router.push('/' + config.redirect.home);

    });

});