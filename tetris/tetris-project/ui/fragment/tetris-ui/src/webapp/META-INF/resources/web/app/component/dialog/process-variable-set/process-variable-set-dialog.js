/**
 * Created by lvdeyang on 2018/12/10 0010.
 */
define([
    'text!' + window.APPPATH + 'component/dialog/process-variable-set/process-variable-set-dialog.html',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/dialog/process-variable-set/process-variable-set-dialog.css'
], function(tpl, ajax, $, Vue){

    var pluginName = 'mi-process-variable-set-dialog';

    var ON_DIALOG_CLOSE = 'on-dialog-close';

    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                visible:false,
                variable:'',
                type:'',
                radio:[],
                variableTypes:[
                    {label:'文本', value:'b'},
                    {label:'长文本', value:'t'},
                    {label:'枚举', value:'e'},
                    {label:'图片', value:'i'},
                    {label:'视频', value:'v'},
                    {label:'音频', value:'a'}],
                dialog:{
                    addReference:{
                        visible:false,
                        label:'',
                        value:''
                    }
                },
                __buffer:''
            }
        },
        methods:{
            open:function(variable){
                var self = this;
                self.variable = variable;
                if(variable.type){
                    self.type = variable.type;
                }
                if(variable.radio && variable.radio.length>0){
                    for(var i=0; i<variable.radio.length; i++){
                        self.radio.push(variable.radio[i]);
                    }
                }
                self.visible = true;
            },
            handleProcessVariableSetClose:function(){
                var self = this;
                self.visible = false;
                self.variable = '';
                self.type = '';
                self.radio.splice(0, self.radio.length);
                self.__buffer = '';
            },
            handleProcessVariableSetOk:function(){
                var self = this;
                if(!self.type){
                    self.$message({
                        message:'变量必须要设置类型！',
                        type:'warning'
                    });
                    return;
                }
                if(self.type==='e' && self.radio.length<=0){
                    self.$message({
                        message:'枚举类型必须添加映射！',
                        type:'warning'
                    });
                    return;
                }
                for(var i=0; i<self.variableTypes.length; i++){
                    if(self.variableTypes[i].value === self.type){
                        self.variable.typeName = self.variableTypes[i].label;
                        self.variable.type = self.variableTypes[i].value;
                    }
                }
                self.variable.radio = self.variable.radio || [];
                self.variable.radio.splice(0, self.variable.radio.length);
                for(var i=0; i<self.radio.length; i++){
                    self.variable.radio.push(self.radio[i]);
                }
                var close = function(){
                    self.handleProcessVariableSetClose();
                };
                self.$emit(ON_DIALOG_CLOSE,  self.variable, close);
            },
            handleRowAdd:function(){
                var self = this;
                self.dialog.addReference.visible = true;
            },
            handleRowDelete:function(scope){
                var self = this;
                var row = scope.row;
                for(var i=0; i<self.radio.length; i++){
                    if(self.radio[i] === row){
                        self.radio.splice(i, 1);
                        break;
                    }
                }
            },
            handleAddReferenceClose:function(){
                var self = this;
                self.dialog.addReference.label = '';
                self.dialog.addReference.value = '';
                self.dialog.addReference.visible = false;
            },
            handleAddReferenceCommit:function(){
                var self = this;
                if(!self.dialog.addReference.label){
                    self.$message({
                        message:'标签不能为空！',
                        type:'warning'
                    });
                    return;
                }
                if(!self.dialog.addReference.value){
                    self.$message({
                        message:'值不能为空！',
                        type:'warning'
                    });
                    return;
                }
                self.radio.push({
                    label:self.dialog.addReference.label,
                    value:self.dialog.addReference.value
                });
                self.handleAddReferenceClose();
            },
            setBuffer:function(buffer){
                var self = this;
                self.__buffer = buffer;
            },
            getBuffer:function(){
                var self = this;
                return self.__buffer;
            }
        }
    });

});