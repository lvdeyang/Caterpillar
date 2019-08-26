/**
 * Created by lvdeyang on 2018/12/10 0010.
 */
define([
    'text!' + window.APPPATH + 'component/dialog/user/user-dialog.html',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/dialog/user/user-dialog.css'
], function(tpl, ajax, $, Vue){

    var pluginName = 'mi-user-dialog';

    var ON_DIALOG_CLOSE = 'on-dialog-close';

    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                visible:false,
                uri:'',
                except:[],
                rows:[],
                currentPage:0,
                pageSize:50,
                total:0,
                loading:false,
                __buffer:'',
                selected:[]
            }
        },
        methods:{
            open:function(uri, except){
                var self = this;
                self.visible = true;
                self.uri = uri;
                if(except && except.length>0){
                    for(var i=0; i<except.length; i++){
                        self.except.push(except[i]);
                    }
                }
                self.load(self.currentPage + 1);
            },
            load:function(currentPage){
                var self = this;
                var uri = self.uri;
                self.rows.splice(0, self.rows.length);
                var params = {
                    pageSize:self.pageSize,
                    currentPage:currentPage
                };
                if(self.except && self.except.length>0){
                    params['except'] = $.toJSON(self.except);
                }
                ajax.post(uri, params, function(data){
                    var rows = data.rows;
                    var total = data.total;
                    for(var i=0; i<rows.length; i++){
                        self.rows.push(rows[i]);
                    }
                    self.currentPage = currentPage;
                    self.total = total;
                });
            },
            handleUserClose:function(){
                var self = this;
                self.visible = false;
                self.rows.splice(0, self.rows.length);
                self.currentPage = 0;
                self.total = 0;
                self.loading = false;
                self.__buffer = '';
                self.except = [];
                self.uri = '';
                self.selected.splice(0, self.selected.length);
            },
            handleUserSelectionChange:function(val){
                var self = this;
                if(val) self.selected = val;
            },
            handleCurrentChange:function(currentPage){
                var self = this;
                self.load(currentPage);
            },
            handleUserBindingOk:function(){
                var self = this;
                var selected = self.selected;
                if(!selected || selected.length<=0){
                    self.$message({
                        message:'您没有选择任何数据！',
                        type:'warning'
                    });
                    return;
                }
                var startLoading = function(){
                    self.loading = true;
                };
                var endLoading = function(){
                    self.loading = false;
                };
                var close = function(){
                    self.handleUserClose();
                };
                self.$emit(ON_DIALOG_CLOSE, selected, startLoading, endLoading, close);
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