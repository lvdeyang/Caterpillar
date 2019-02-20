define([
    'text!' + window.APPPATH + 'component/bvc2-dialog-set-forward/bvc2-dialog-set-forward.html',
    'vue',
    'element-ui',
    'bvc2-transfer-source-target',
    'bvc2-transfer-role-target'
], function(tpl, Vue){

    //组件名称
    var bvc2DialogSetForward = 'bvc2-dialog-set-forward';

    Vue.component(bvc2DialogSetForward, {
        props:['members', 'values', 'roles', 'roleValues'],
        template:tpl,
        data:function(){
            return {
                dst:[],
                roleDst:[],
                dialogVisible:false,
                currentTab:'device'
            }
        },
        methods:{
            changeSource:function(data){
                var instance = this;
                instance.dst.splice(0, instance.dst.length);
                for(var i=0;i<data.length;i++){
                    instance.dst.push(data[i]);
                }
            },
            changeRole:function(data){
                var instance = this;
                instance.roleDst.splice(0, instance.roleDst.length);
                for(var i=0;i<data.length;i++){
                    instance.roleDst.push(data[i]);
                }
            }
        }
    });

});