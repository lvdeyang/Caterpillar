define([
    'text!' + window.APPPATH + 'component/bvc2-tab-buttons/bvc2-tab-buttons.html',
    'restfull',
    'vue',
    'element-ui',
    'bvc2-dialog-set-audio'
], function(tpl, ajax, Vue){

    //组件名称
    var bvc2TabButtons = 'bvc2-tab-buttons';

    //icons
    var startSessionIcon = 'icon-play';
    var stopSessionIcon = 'icon-stop';
    var sessionRecordIcon = 'icon-facetime-video';
    var setVolumeIcon = 'icon-volume-up';
    var queryForwardIcon = 'icon-table';
    var saveAgendaIcon = 'icon-save';

    //style
    var hidden = 'display:none;';
    var show = 'display:inline';

    Vue.component(bvc2TabButtons, {
        props:['group'],
        template: tpl,
        data:function(){
            return {
                members:[],
                values:[],
                style:{
                    startSession:{
                        isLoading:false,
                        icon:startSessionIcon
                    },
                    stopSession:{
                        isLoading:false,
                        icon:stopSessionIcon
                    },
                    sessionRecord:{
                        isLoading:false,
                        icon:sessionRecordIcon
                    },
                    setVolume:{
                        isLoading:false,
                        icon:setVolumeIcon
                    },
                    queryForward:{
                        isLoading:false,
                        icon:queryForwardIcon
                    },
                    saveAgenda:{
                        isLoading:false,
                        icon:saveAgendaIcon
                    }
                }
            }
        },
        computed:{
            recordStatus:function(){
                if(this.group.isRecord !== true){
                    return 'primary';
                }else{
                    return 'danger';
                }
            },
            recordTip:function(){
                if(this.group.isRecord !== true){
                    return '开始录制';
                }else{
                    return '停止录制';
                }
            },
            startSessionDisplay:function(){
                if(this.style.startSession.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            },
            stopSessionDisplay:function(){
                if(this.style.stopSession.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            },
            sessionRecordDisplay:function(){
                if(this.style.sessionRecord.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            },
            setVolumeDisplay:function(){
                if(this.style.setVolume.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            },
            queryForwardDisplay:function(){
                if(this.style.queryForward.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            },
            saveAgendaDisplay:function(){
                if(this.style.saveAgenda.isLoading){
                    return hidden;
                }else{
                    return show;
                }
            }
        },
        methods:{
            startSession:function(){
                var buttons_instance = this;
                var session = buttons_instance.session;
                buttons_instance.style.startSession.isLoading = true;
                ajax.post('/device/group/start/' + buttons_instance.group.id, null, function(data){
                    buttons_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                    buttons_instance.style.startSession.isLoading = false;
                    for(var i in data){
                        buttons_instance.group[i] = data[i];
                    }
                });
            },
            stopSession:function(){
                var buttons_instance = this;
                buttons_instance.style.stopSession.isLoading = true;
                ajax.post('/device/group/stop/' + buttons_instance.group.id, null, function(data){
                    buttons_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                    buttons_instance.style.stopSession.isLoading = false;
                    for(var i in data){
                        buttons_instance.group[i] = data[i];
                    }
                });
            },
            sessionRecord:function(){
                var buttons_instance = this;
                buttons_instance.style.sessionRecord.isLoading = true;
                setTimeout(function(){
                    if(buttons_instance.group.isRecord === true){
                        ajax.post('/device/group/stop/record/scheme/' + buttons_instance.group.id, null, function(data){
                            //停止录制
                            buttons_instance.$message({
                                type:'success',
                                message:'操作成功！'
                            });
                            Vue.set(buttons_instance.group, 'isRecord', false);
                        });
                    }else{
                        ajax.post('/device/group/run/record/scheme/' + buttons_instance.group.id, null, function(data){
                            //开始录制
                            buttons_instance.$message({
                                type:'success',
                                message:'操作成功！'
                            });
                            Vue.set(buttons_instance.group, 'isRecord', true);
                        });
                    }
                    buttons_instance.style.sessionRecord.isLoading = false;
                }, 500);
            },
            setVolume:function(){
                var buttons_instance = this;
                var _values = buttons_instance.values;
                var _members = buttons_instance.members;

                _values.splice(0, _values.length);
                _members.splice(0, _members.length);

                ajax.get('/device/group/query/bundle/' + buttons_instance.group.id, null, function(data){
                    var bundles = data.bundles;
                    for(var i=0;i<bundles.length;i++){
                        var bundle = bundles[i];
                        if(bundle.param.indexOf( 'combineJv230') >= 0) continue;
                        _members.push(bundle);
                        var param = JSON.parse(bundle.param);
                        if(param.openAudio){
                            _values.push(bundle.id);
                        }
                    }
                });

                buttons_instance.$refs.setAudio.audioVisible = true;
            },
            queryForward:function(){
                var buttons_instance = this;
                var session = buttons_instance.session;
                buttons_instance.style.queryForward.isLoading = true;
                setTimeout(function(){
                    buttons_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                    buttons_instance.style.queryForward.isLoading = false;
                }, 500);
            },
            saveAgenda:function(){
                var buttons_instance = this;
                var session = buttons_instance.session;
                buttons_instance.style.saveAgenda.isLoading = true;
                setTimeout(function(){
                    buttons_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                    buttons_instance.style.saveAgenda.isLoading = false;
                }, 500);
            }
        }
    });

});