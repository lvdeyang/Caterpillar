define([
    'text!' + window.APPPATH + 'component/bvc2-auto-layout/bvc2-auto-layout.html',
    'restfull',
    'jquery.layout.auto',
    'vue',
    'element-ui',
    'bvc2-dialog-set-forward'
], function(tpl, ajax, $, Vue){

    //组件名称
    var bvc2AutoLayout = 'bvc2-auto-layout';

    var getLayoutContainer = function(el){
        return $(el).find('.bvc2-auto-layout-container');
    };

    Vue.component(bvc2AutoLayout, {
        props: ['group', 'config', 'video', 'members', 'roles'],
        template: tpl,
        data:function(){
            return {
                layout:'',
                mode:'combine',
                values:[],
                roleValues:[],
                options:{
                    columnOptions:[
                        {
                            value:1,
                            label:1
                        },{
                            value:2,
                            label:2
                        },{
                            value:3,
                            label:3
                        },{
                            value:4,
                            label:4
                        }
                    ],
                    rowOptions:[
                        {
                            value:1,
                            label:1
                        },{
                            value:2,
                            label:2
                        },{
                            value:3,
                            label:3
                        },{
                            value:4,
                            label:4
                        }
                    ],
                    modeOptions:[
                       /* {
                            value:'forward',
                            label:'转发'
                        },*/{
                            value:'combine',
                            label:'合屏'
                        }
                    ]
                },
                style:{
                    play:{
                        isLoading:false
                    },
                    forward:{
                        isLoading:false
                    },
                    startRecord:{
                        isLoading:false
                    },
                    stopRecord:{
                        isLoading:false
                    },
                    reset:{
                        isLoading:false
                    },
                    saveScheme:{
                        isLoading:false
                    }
                }
            }
        },
        watch:{
            layout:function(){
                this.refreshLayout();
            },
            video:function(){
                var layout_instance = this;
                layout_instance.refreshData();
            }
        },
        methods:{
            setLayout:function(layout){
                this.layout = layout;
            },

            refreshData:function(){
                var layout_instance = this;
                setTimeout(function(){
                    //向layout中设置数据
                    var srcs = layout_instance.video.srcs;
                    if(srcs && srcs.length>0){
                        var $container = getLayoutContainer(layout_instance.$el);
                        var cells = $container['layout-auto']('getCells');
                        for(var i=0; i<cells.length; i++){
                            var cell = cells[i];
                            var d = [];
                            for(var j=0; j<srcs.length; j++){
                                if(cell.serialNum == srcs[j].serialnum){
                                    d.push({
                                        id:srcs[j].memberChannelId,
                                        name:srcs[j].memberChannelName,
                                        //key:'CHANNEL@@' + srcs[j].memberChannelId,
                                        //type:'CHANNEL',
                                        //icon:'icon-exchange',
                                        param: $.toJSON({
                                            memberId:srcs[j].memberId,
                                            bundleId:srcs[j].bundleId,
                                            bundleName:srcs[j].bundleName,
                                            channelId:srcs[j].channelId,
                                            nodeUid:srcs[j].layerId,
                                            channelMemberId:srcs[j].memberChannelId,
                                            channelName:srcs[j].channelName
                                        })
                                    });
                                }
                            }
                            if(d.length > 0){
                                cell.$cell['layout-auto']('setData', d);
                                if(d.length === 1){
                                    cell.$cell.empty().append('<span style="color:#fff; font-size:14px;">'+d[0].name+'</span>');
                                }else{
                                    cell.$cell.empty().append('<span style="color:#fff; font-size:14px;">轮询（'+d.length+'）</span>')
                                }
                            }
                        }
                    }
                }, 0);
            },

            refreshLayout:function(){
                var $container = getLayoutContainer(this.$el);
                var options = this.layout;
                if(options){
                    $container['layout-auto']('create', {
                        cell:{
                            column:options.column,
                            row:options.row
                        },
                        cellspan:options.cellspan,
                        theme:'dark',
                        name:options.name,
                        editable:options.editable,
                        event:{
                            drop:function(e){
                                var data = $.parseJSON(e.dataTransfer.getData('data'));
                                var $cell = $(this);
                                var od = $cell['layout-auto']('getData');
                                if(od){
                                    od.push(data);
                                    $cell.empty().append('<span style="color:#fff; font-size:14px;">轮询（'+od.length+'）</span>');
                                }else{
                                    od = [data];
                                    $cell.empty().append('<span style="color:#fff; font-size:14px;">'+data.name+'</span>');
                                }
                                $cell['layout-auto']('setData', od);
                            }
                        }
                    });
                }else{
                    $container['layout-auto']('destroy');
                }
            },

            columnChange:function(column){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                //获取设置前的值
                var $container = getLayoutContainer(layout_instance.$el);
                var oldColumn = $container['layout-auto']('queryColumn');
                layout_instance.$confirm('此操作将清空屏幕上的配置，并且设置为：' + column + '行' + layout_instance.layout.row + '列, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            var newLayout = $.extend(true, {}, layout_instance.layout);
                            newLayout.column = column;
                            newLayout.cellspan = [];
                            layout_instance.layout = newLayout;
                        }else if(action === 'cancel'){
                            layout_instance.layout.column = oldColumn;
                        }
                        done();
                    }
                });
            },

            rowChange:function(row){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                //获取设置前的值
                var $container = getLayoutContainer(layout_instance.$el);
                var oldRow = $container['layout-auto']('queryRow');
                layout_instance.$confirm('此操作将清空屏幕上的配置，并且设置为：' + layout_instance.layout.column + '行' + row + '列, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            var newLayout = $.extend(true, {}, layout_instance.layout);
                            newLayout.row = row;
                            newLayout.cellspan = [];
                            layout_instance.layout = newLayout;
                        }else{
                            layout_instance.layout.row = oldRow;
                        }
                        done();
                    }
                });
            },

            play:function(){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                if(layout_instance.style.play.isLoading) return;
                layout_instance.style.play.isLoading = true;
                ajax.post('/scheme/run/' + layout_instance.group.id + '/' + layout_instance.video.id, null, function(data){
                    layout_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                });
            },

            forward:function(){
                var layout_instance = this;
                var _video = layout_instance.video;
                var _values = layout_instance.values;
                var _roleValues = layout_instance.roleValues;

                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }

                if(_values) _values.splice(0, _values.length);
                if(_roleValues) _roleValues.splice(0, _roleValues.length);

                if(_video.dsts){
                    for(var i=0;i<_video.dsts.length;i++){
                        if(_video.dsts[i].type === 'CHANNEL'){
                            //已选设备value
                            _values.push(_video.dsts[i].memberChannelId);
                        }else if(_video.dsts[i].type === 'ROLE'){
                            //已选角色value
                            _roleValues.push(_video.dsts[i].roleId + '-' + _video.dsts[i].roleChannelType);
                        }
                    }
                }

                layout_instance.$refs.setForward.dialogVisible = true;
            },

            startRecord:function(){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                if(layout_instance.style.startRecord.isLoading) return;
                layout_instance.style.startRecord.isLoading = true;
                ajax.post('/agenda/start/record/' + layout_instance.group.id + '/' + layout_instance.video.id, null, function(){
                    layout_instance.style.startRecord.isLoading = false;
                    layout_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                });
            },

            stopRecord:function(){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                if(layout_instance.style.stopRecord.isLoading) return;
                layout_instance.style.stopRecord.isLoading = true;
                ajax.post('/agenda/stop/record/' + layout_instance.group.id + '/' + layout_instance.video.id, null, function(){
                    layout_instance.style.stopRecord.isLoading = false;
                    layout_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                });
            },

            reset:function(){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                if(layout_instance.style.reset.isLoading) return;
                layout_instance.style.reset.isLoading = true;
                var websiteDraw = $.parseJSON(layout_instance.video.websiteDraw);

                //重置布局
                layout_instance.setLayout({
                    column:websiteDraw.basic.column,
                    row:websiteDraw.basic.row,
                    cellspan:websiteDraw.cellspan,
                    editable:true
                });
                //恢复数据
                layout_instance.refreshData();

                layout_instance.style.reset.isLoading = false;
                layout_instance.$message({
                    type:'success',
                    message:'操作成功！'
                });

            },

            save:function(){
                var layout_instance = this;
                if(!layout_instance.config){
                    layout_instance.$message({
                        type:'warning',
                        message:'请先指定要操作的议程或方案！'
                    });
                    return;
                }
                if(!layout_instance.video){
                    layout_instance.$message({
                        type:'warning',
                        message:'还没有要操作的视频！'
                    });
                    return;
                }
                if(layout_instance.style.saveScheme.isLoading) return;
                layout_instance.style.saveScheme.isLoading = true;

                var done = function(){
                    layout_instance.style.saveScheme.isLoading = false;
                    layout_instance.$message({
                        type:'success',
                        message:'操作成功！'
                    });
                };

                //layout数据
                var $container = getLayoutContainer(layout_instance.$el);
                var tpl = $container['layout-auto']('generateTpl', false);
                var websiteDraw = {
                    basic:tpl.basic,
                    cellspan:tpl.cellspan
                };
                var position = tpl.layout;
                var dst = layout_instance.$refs.setForward.dst;
                var roleDst = layout_instance.$refs.setForward.roleDst;

                //发射事件
                layout_instance.$emit('save-layout', layout_instance.config, layout_instance.video, websiteDraw, position, dst, roleDst, done);
            }

        },
        mounted:function(){
            this.refreshLayout();
        }
    });

});