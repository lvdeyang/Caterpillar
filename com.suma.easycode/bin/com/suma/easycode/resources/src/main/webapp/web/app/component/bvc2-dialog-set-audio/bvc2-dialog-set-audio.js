define([
    'text!' + window.APPPATH + 'component/bvc2-dialog-set-audio/bvc2-dialog-set-audio.html',
    'restfull',
    'vue',
    'element-ui',
    'bvc2-transfer-audio-target'
], function(tpl, ajax, Vue){

    //组件名称
    var bvc2DialogSetAudio = 'bvc2-dialog-set-audio';

    Vue.component(bvc2DialogSetAudio, {
        props:['members', 'values', 'group'],
        template:tpl,
        data:function(){
            return {
                audioVisible:false,
                style:{
                    saveAudio:{
                        text:"完成",
                        isLoading:false
                    }
                }
            }
        },
        methods:{
            saveAudio:function(){
                var dialog_instance = this;

                var audioList = dialog_instance.$refs.transferAudio.getSelect();

                var _uri = '/device/group/control/add/audio/' + dialog_instance.group.id;

                dialog_instance.style.saveAudio.isLoading = true;
                dialog_instance.style.saveAudio.text = "";

                ajax.post(_uri, {
                    audioList: $.toJSON(audioList)
                }, function(data){

                    dialog_instance.$message({
                        type:'success',
                        message:'成功创建音频！'
                    });
                    dialog_instance.style.saveAudio.isLoading = false;
                    dialog_instance.style.saveAudio.text = "完成";
                    dialog_instance.audioVisible = false;

                });

            }
        }
    });

});
