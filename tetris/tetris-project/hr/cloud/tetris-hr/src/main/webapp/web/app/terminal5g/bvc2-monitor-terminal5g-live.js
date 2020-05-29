define([
    'text!' + window.APPPATH + 'terminal5g/bvc2-monitor-terminal5g-live.html',
    'context',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'native-record-player',
    'player',
    'player-panel',
    'jquery.layout.auto',
    'date',
    'css!' + window.APPPATH + 'terminal5g/bvc2-monitor-terminal5g-live.css',
    'css!' + window.APPPATH + 'jQuery/player/css/Tetris.player.css',
    'css!' + window.APPPATH + 'jQuery/playerPanel/css/Tetris.playerPanel.css',
    'css!' + window.APPPATH + 'jQuery/jQuery.layout.auto/css/jQuery.layout.auto.css',
    'css!' + window.APPPATH + 'bvc2-layout-buttons/bvc2-layout-buttons.css'
], function(tpl, context, ajax, $, Vue){

	//组件名称
    var pluginName = 'bvc2-monitor-terminal5g-live';

    var app = context.getProp('app');

    var $webPlayers = [];
    
    Vue.component(pluginName, {
        props: [],
        template: tpl,
        data:function(){
            return {
                tree:{
                    data:[],
                    props:{
                        children:'children',
                        label:'name'
                    }
                },
                buttons:[
                    {
                        id:1,
                        name:'1分屏',
                        cell:{
                            column:1,
                            row:1
                        },
                        editable:false,
                        theme:'gray',
                        layout:[
                            [1, 1]
                        ],
                        active:false
                    },{
                        id:4,
                        name:'4分屏',
                        cell:{
                            column:2,
                            row:2
                        },
                        editable:false,
                        theme:'gray',
                        layout:[
                            [0.5, 0.5],[0.5, 0.5],
                            [0.5, 0.5],[0.5, 0.5]
                        ],
                        active:true
                    }
                ],
                dialog:{
                	
                }
            }
        },
        computed:{
            
        },
        watch:{
           
        },
        methods:{
            destroy:function(fn){
                //销毁播放器
                $('embed').each(function(){
                    var $plugin = $(this);
                    if($plugin.data('inited') === true){
                        app.unRegisterPlayer([{
                            code:$plugin.data('code'),
                            $embed:$plugin
                        }]);
           
                    }
                });
                //ajax.post('/monitor/live/remove/all/webplayer/live', null, fn);
                //app.removeDeviceLoop('terminal-live-encode-tree');
            },
            layoutChange:function(button){
                var self = this;
                console.log(button.active);
                if(button.active) return;
                for(var i=0; i<self.buttons.length; i++){
                    if(self.buttons[i]!==button && self.buttons[i].active){
                        self.buttons[i].active = false;
                    }
                }
                self.destroy(function(){
                    var doRegister = function(){
                        var $players = [];
                        for(var i=0; i<button.layout.length; i++){
                            var data = $webPlayers;
                            var $player = $('<div id="sip-player-'+(i+1)+'" style="width:100%; height:100%; position:absolute;">' +
                                '<embed width="100%" height="100%" style="z-index:0" type="application/media-suma-lightlive"/>' +
                                '</div>');
                            $player.find('embed')
                                .data('code', data[i].code)
                                .data('ip', data[i].ip)
                                .data('username', data[i].username)
                                .data('password', data[i].password)
                                .data('bundleId', data[i].bundleId)
                                .data('bundleType', data[i].bundleType)
                                .data('bundleName', data[i].bundleName)
                                .data('layerId', data[i].layerId)
                                .data('registerLayerIp', data[i].registerLayerIp)
                                .data('registerLayerPort', data[i].registerLayerPort)
                                .data('videoChannelId', data[i].videoChannelId)
                                .data('videoBaseType', data[i].videoBaseType)
                                .data('audioChannelId', data[i].audioChannelId)
                                .data('audioBaseType', data[i].audioBaseType);
                            $players.push($player);
                        }
                        $('#player-container')['Tetris.playerPanel']('setLayout', button);
                        $('#player-container')['Tetris.playerPanel']('setEmbed', $players);
                        //初始化播放器
                        for(var i=0; i<$players.length; i++){
                            var $player = $players[i];
                            var $plugin = $player.find('embed');
                            app.registerPlayer([{
                                code:$plugin.data('code'),
                                username:$plugin.data('username'),
                                password:$plugin.data('password'),
                                $embed:$plugin
                            }]);
                           
                            $plugin.data('inited', true);
                        }
                        button.active = true;
                    };
                    if($webPlayers.length>0){
                        doRegister();
                    }else{
                        //解决页面初始化点太快的问题
                        setTimeout(doRegister, 100);
                    }
                });
            },
            handleDragStart:function(data, e){
                if(data.type === 'BUNDLE'){
                    e.dataTransfer.setData('device', data.param);
                }else if(data.type === 'USER'){
                    e.dataTransfer.setData('user', data.param);
                }
            }
            
        },
        created:function(){
            var self = this;
            self.tree.data.splice(0, self.tree.data.length);
            var data= [{
	    		"id": "2401",
	    		"uuid": null,
	    		"name": "132根目录",
	    		"key": "FOLDER@@2401",
	    		"type": "FOLDER",
	    		"children": [{
	    			"id": "2403",
	    			"uuid": null,
	    			"name": "终端根目录",
	    			"key": "FOLDER@@2403",
	    			"type": "FOLDER",
	    			"children": [{
	    				"id": "7dd1fd061c844d59bdd508906a03c35c",
	    				"uuid": null,
	    				"name": "bq_enc",
	    				"key": "BUNDLE@@7dd1fd061c844d59bdd508906a03c35c",
	    				"type": "BUNDLE",
	    				"children": [],
	    				"screens": null,
	    				"param": "{\"realType\":\"BVC\",\"bundleId\":\"7dd1fd061c844d59bdd508906a03c35c\",\"openAudio\":false,\"bundleName\":\"bq_enc\",\"nodeUid\":\"stream-joiner-110\",\"bundleType\":\"jv210\",\"folderId\":2403,\"venusBundleType\":\"VenusTerminal\"}",
	    				"icon": "icon-facetime-video",
	    				"bundleStatus": "bundle-online",
	    				"style": "color:#E6A23C;",
	    				"webUrl": null,
	    				"layerWebUrl": null
	    			} , {
	    				"id": "fcdaf1f6d13a459d9281e58bacf2612b",
	    				"uuid": null,
	    				"name": "bq编码器111",
	    				"key": "BUNDLE@@fcdaf1f6d13a459d9281e58bacf2612b",
	    				"type": "BUNDLE",
	    				"children": [],
	    				"screens": null,
	    				"param": "{\"realType\":\"BVC\",\"bundleId\":\"fcdaf1f6d13a459d9281e58bacf2612b\",\"openAudio\":false,\"bundleName\":\"bq编码器111\",\"nodeUid\":\"stream-joiner-132\",\"bundleType\":\"jv210\",\"folderId\":2403,\"venusBundleType\":\"VenusTerminal\"}",
	    				"icon": "icon-facetime-video",
	    				"bundleStatus": "bundle-online",
	    				"style": "color:#0dcc19;",
	    				"webUrl": null,
	    				"layerWebUrl": null
	    			}],
	    			"screens": null,
	    			"param": "{\"parentPath\":\"/2401\",\"parentId\":2401}",
	    			"icon": "icon-folder-close",
	    			"bundleStatus": null,
	    			"style": null,
	    			"webUrl": null,
	    			"layerWebUrl": null
	    		}]
            }];
            for(var i=0; i<data.length; i++){
                self.tree.data.push(data[i]);
            }
            //app.addDeviceLoop('terminal-live-encode-tree', data);
            /*ajax.post('/monitor/device/find/institution/tree/2/false', null, function(data){
                if(data && data.length>0){
                    for(var i=0; i<data.length; i++){
                        self.tree.data.push(data[i]);
                    }
                    app.addDeviceLoop('terminal-live-encode-tree', data);
                }
            });*/
            var layout = null;
            for(var i=0; i<self.buttons.length; i++){
                if(self.buttons[i].active){
                    layout = self.buttons[i].layout;
                    break;
                }
            }
            if(window.SCOPE !== 'plugin'){
                self.buttons.push({
                    id:6,
                    name:'6分屏',
                    cell:{
                        column:3,
                        row:3
                    },
                    cellspan:[{
                        x:0,
                        y:0,
                        r:1,
                        b:1
                    }],
                    editable:false,
                    theme:'gray',
                    layout:[
                        [0.6599, 0.6599],         [0.34, 0.33],
                        [0.34, 0.33],
                        [0.33, 0.34],[0.33, 0.34],[0.34, 0.34]
                    ],
                    active:false
                });
                self.buttons.push({
                    id:9,
                    name:'9分屏',
                    cell:{
                        column:3,
                        row:3
                    },
                    editable:false,
                    theme:'gray',
                    layout:[
                        [0.33, 0.33],[0.33, 0.33],[0.34, 0.33],
                        [0.33, 0.33],[0.33, 0.33],[0.34, 0.33],
                        [0.33, 0.34],[0.33, 0.34],[0.34, 0.34]
                    ],
                    active:false
                });
                self.buttons.push({
                    id:16,
                    name:'16分屏',
                    cell:{
                        column:4,
                        row:4
                    },
                    editable:false,
                    theme:'gray',
                    layout:[
                        [0.25, 0.25],[0.25, 0.25],[0.25, 0.25],[0.25, 0.25],
                        [0.25, 0.25],[0.25, 0.25],[0.25, 0.25],[0.25, 0.25],
                        [0.25, 0.25],[0.25, 0.25],[0.25, 0.25],[0.25, 0.25],
                        [0.25, 0.25],[0.25, 0.25],[0.25, 0.25],[0.25, 0.25]
                    ],
                    active:false
                });
            }
            self.$nextTick(function(){
                $('#player-container')['Tetris.playerPanel']('create', {
                    split:{
                        layout:layout
                    },
                    player:{
                        event:{
                            onStop: function ($embed, srcList, complete) {
                                var $player = $(this);
                                var src = srcList[0];
                                if(src.type === 'device'){
                                    ajax.post('/monitor/live/stop/live/device/' + src.taskId, null, function(data, status){
                                        if(status !== 200) return;
                                        src.taskId = null;
                                        $player['Tetris.player']('set', src);
                                        complete();
                                    }, null, [403, 404, 408, 409, 500]);
                                }
                            },
                            onPlay: function ($embed, srcList, osd, complete) {
                                var $player = $(this);
                                var serial = $(this).attr('data_index');
                                var src = srcList[0];
                                if(src.type === 'device'){
                                   
                                }
                                
                            },
                            forward:function(srcList, osd){
                            	
                            },
                            onDrop: function ($embed, serialNum, oldData, event) {
                                var $player = $(this);
                                var $record = $player.find('.opration .record');
                                var deviceJSON = event.dataTransfer.getData('device');
                                if(deviceJSON){}
                             
                            },
                            onRemove:function($player, data, complete){
                                $player['Tetris.player']('enable', ['forward', 'ptzctrl']);
                                complete();
                            },
                            volumeSet:function($embed, volume){
                                if(typeof volume === 'number'){

                                }else if(volume === true){
                                    return $embed[0].I_SetSilence(false);
                                }else if(volume === false){
                                    return $embed[0].I_SetSilence(true);
                                }
                                return 1;
                            }
                        },
                        interface:{
                            info:function(title, message){
                                self.$message({
                                    type:'warning',
                                    message:title + '：' + message
                                });
                            }
                        }
                    },
                    interface:{
                        createPlayer:function(config){
                            config.class = 'noPrev noNext noFast noSlow noPause noPrintScreen noLayoutSet noProgress noRecord noDecodeBind noPtzctrl noOsd';
                            config.theme = 'dark';
                            $(this)['Tetris.player']('create', config);
                        },
                        setEmbed:function($player, $embed){
                            return $player['Tetris.player']('setEmbed', $embed);
                        },
                        getEmbed:function($player){
                            return $player['Tetris.player']('getEmbed');
                        }
                    }
                });

                /*ajax.post('/monitor/device/find/web/sip/players', null, function(data){
                    var $players = [];
                    if(data && data.length>0){
                        var endIndex = layout.length<data.length?layout.length:data.length;
                        for(var i=0; i<data.length; i++){
                            $webPlayers.push(data[i]);
                            var $player = $('<div id="sip-player-'+(i+1)+'" style="width:100%; height:100%; position:absolute;">' +
                                                '<embed width="100%" height="100%" style="z-index:0" type="application/media-suma-lightlive"/>' +
                                            '</div>');
                            $player.find('embed')
                                   .data('code', data[i].code)
                                   .data('ip', data[i].ip)
                                   .data('username', data[i].username)
                                   .data('password', data[i].password)
                                   .data('bundleId', data[i].bundleId)
                                   .data('bundleType', data[i].bundleType)
                                   .data('bundleName', data[i].bundleName)
                                   .data('layerId', data[i].layerId)
                                   .data('registerLayerIp', data[i].registerLayerIp)
                                   .data('registerLayerPort', data[i].registerLayerPort)
                                   .data('videoChannelId', data[i].videoChannelId)
                                   .data('videoBaseType', data[i].videoBaseType)
                                   .data('audioChannelId', data[i].audioChannelId)
                                   .data('audioBaseType', data[i].audioBaseType);
                            if(i < endIndex){
                                $players.push($player);
                            }
                        }
                    }
                    $('#player-container')['Tetris.playerPanel']('setEmbed', $players);
                    setTimeout(function(){
                        //初始化播放器
                        for(var i=0; i<$players.length; i++){
                            var $player = $players[i];
                            var $plugin = $player.find('embed');
                            app.registerPlayer([{
                                code:$plugin.data('code'),
                                username:$plugin.data('username'),
                                password:$plugin.data('password'),
                                $embed:$plugin
                            }]);
                            
                            $plugin.data('inited', true);
                        }
                    }, 100);
                });*/

                $('.bvc2-layout-button').each(function(){
                    var $button = $(this);
                    var id = $button.data('id');
                    for(var i=0; i<self.buttons.length; i++){
                        if(self.buttons[i].id == id){
                            $button.find('.bvc2-layout-button-icon')['layout-auto']('create', self.buttons[i]);
                            break;
                        }
                    }
                });
            });
        }
    });

    return Vue;
});