/**
 * Created by lvdeyang on 2018/11/23 0023.
 */
define([
    'text!' + window.APPPATH + 'component/dialog/folder/folder-dialog.html',
    'restfull',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/dialog/folder/folder-dialog.css',
], function(tpl, ajax, Vue){

    var pluginName = 'mi-folder-dialog';

    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                __buffer:null,
                dialog:{
                    visible:false,
                    loading:false
                },
                tree:{
                    props:{
                        label: 'name',
                        children: 'children',
                        isLeaf: 'leaf'
                    },
                    expandOnClickNode:false,
                    data:[]
                }
            }
        },
        methods:{
            //对话框关闭时初始化数据
            closed:function(){
                var self = this;
                self.tree.data.splice(0, self.tree.data.length);
                self.cache = null;
            },
            loadNode:function(node, resolve){

            },
            //渲染函数
            render:function(h, scope){
                var data = scope.data;
                return h('div', null, [
                    h('span', {class:'icon-folder-close'}, []),
                    h('span', null, data.name)
                ]);
            },
            //打开文件夹对话框
            open:function(uri, except, depth){
                var self = this;
                ajax.post(uri, {
                    except:except || null,
                    depth:depth || null
                }, function(data){
                    if(data && data.length>0){
                        for(var i=0; i<data.length; i++){
                            self.tree.data.push(data[i]);
                        }
                    }
                    self.dialog.visible = true;
                });
            },
            //缓存数据
            setBuffer:function(data){
                var self = this;
                self.__buffer = data;
            },
            //获取缓存数据
            getBuffer:function(){
                var self = this;
                return self.__buffer;
            },
            //文件夹选中
            handleOkButton:function(){
                var self = this;
                var currentNode = self.$refs.folderTree.getCurrentNode();
                if(!currentNode){
                    self.$message({
                        message: '您还没有选择文件夹',
                        type: 'warning'
                    });
                }
                self.$emit('selected-folder', currentNode, self.__buffer, function(){
                    self.dialog.loading = true;
                }, function(){
                    self.dialog.loading = false;
                }, function(){
                    self.dialog.visible = false;
                });
            }
        }
    });

});