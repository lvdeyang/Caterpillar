define([
    'text!' + window.APPPATH + 'component/bvc2-table-agenda/bvc2-table-agenda.html',
    'restfull',
    'vue',
    'element-ui',
    'bvc2-dialog-set-audio'
], function(tpl, ajax, Vue){

    var bvc2TableAgenda = 'bvc2-table-agenda';

    Vue.component(bvc2TableAgenda, {
        props:['group'],
        template:tpl,
        data:function(){
            return {
                rows:[],
                total:0,
                pageSize:10,
                currentPage:1,
                form:{
                    dialogVisible:false,
                    labelWidth:'80px',
                    name:'',
                    audioOperation:'',
                    remark:''
                },
                audioStyle:{
                    row:'',
                    members:[],
                    values:[],
                    titleProp:"设置议程音频",
                    audioSingleVisible:false,
                    saveAudio:{
                        text:"完成",
                        isLoading:false
                    }
                }
            }
        },
        methods:{
            load:function(){
                var table_instance = this;
                ajax.get('/agenda/load/' + table_instance.group.id, {
                    pageSize:table_instance.pageSize,
                    currentPage:table_instance.currentPage
                }, function(data){
                    if(table_instance.rows.length > 0) table_instance.rows.splice(0, table_instance.rows.length);
                    if(data.rows && data.rows.length>0){
                        for(var i=0; i<data.rows.length; i++){
                            table_instance.rows.push(data.rows[i]);
                        }
                    }
                    table_instance.total = parseInt(data.total);
                });
            },
            addAgenda:function(){
                var table_instance = this;
                table_instance.form.dialogVisible = true;
            },
            saveAgenda:function(){
                var table_instance = this;
                ajax.post('/agenda/save/' + table_instance.group.id, {
                    name:table_instance.form.name,
                    remark:table_instance.form.remark,
                    audioOperation:table_instance.form.audioOperation
                }, function(data){
                    table_instance.rows.push(data);
                    table_instance.form.dialogVisible = false;
                });
            },
            removeAgenda:function(row){
                var table_instance = this;
                ajax.remove('/agenda/remove/' + row.id, null, function(){
                    for(var i=0; i<table_instance.rows.length; i++){
                        if(table_instance.rows[i] === row){
                            table_instance.rows.splice(i, 1);
                            break;
                        }
                    }
                });
            },
            configAgenda:function(row){
                this.$emit('config-agenda', row);
            },
            runAgenda:function(row){
                this.$emit('run-agenda', row);
            },
            agendaAudio:function(row){
                var table_instance = this;
                var _members = table_instance.audioStyle.members,
                    _values = table_instance.audioStyle.values;

                if(row.audioOperation === "自定义"){
                    ajax.post('/agenda/query/audio/' + table_instance.group.id + '/' + row.id, null, function(data){
                        _members.splice(0, _members.length);
                        _values.splice(0, _values.length);

                        table_instance.audioStyle.row = row;

                        if(data.bundles){
                            for(var i=0;i<data.bundles.length;i++){
                                _members.push(data.bundles[i]);
                            }
                        }

                        if(data.audios){
                            for(var j=0;j<data.audios.length;j++){
                                _values.push(data.audios[j].bundleId);
                            }
                        }

                        table_instance.audioStyle.titleProp = '设置议程：' + row.name + ' 音频';
                        table_instance.audioStyle.audioSingleVisible = true;
                    });
                }
            },
            saveAudio:function(){
                var tab_instance = this,
                    _agendaId = tab_instance.audioStyle.row.id;
                var _audios = tab_instance.$refs.transferSingleAudio.getSelect();

                var audioList = {
                    audioList: $.toJSON(_audios)
                };

                tab_instance.audioStyle.saveAudio.isLoading = true;
                tab_instance.audioStyle.saveAudio.text = "";

                ajax.post('/agenda/update/audio/'+ _agendaId, audioList, function(data){
                    tab_instance.$message({
                        type:'success',
                        message:'成功创建议程音频！'
                    });
                    tab_instance.audioStyle.saveAudio.isLoading = false;
                    tab_instance.audioStyle.saveAudio.text = "完成";
                    tab_instance.audioStyle.audioSingleVisible = false;
                })

            }
        },
        mounted:function(){
            var table_instance = this;
            table_instance.currentPage = 1;
            table_instance.load();
        }
    });

});