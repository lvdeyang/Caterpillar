/**
 * Created by lvdeyang on 2018/12/26 0026.
 */
define([
    'text!' + window.APPPATH + 'org/page-hr-org.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'css!' + window.APPPATH + 'org/page-hr-org.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-hr-org';

    var instance = null;

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        instance = new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
                menus:context.getProp('menus'),
                user:context.getProp('user'),
                groups:context.getProp('groups'),
                types:[],
                tree:{
                    props:{
                        label: 'name',
                        children: 'subOrgs'
                    },
                    expandOnClickNode:false,
                    data:[],
                    current:''
                },
                table:{
                    data:[]
                },
                dialog:{
                    editTag:{
                        visible:false,
                        data:'',
                        name:'',
                        code:'',
                        remark:''
                    },
                    editUserTag:{
                    	visible:false,
                        data:'',
                        name:'',
                        code:'',
                        remark:''
                    }
                },
                loading:{
                    tree:false,
                    addRoot:false
                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                mouseEnter:function(){
                    var self = this;
                    self.showButton30=false;
                },
                mouseLeave: function(){
                    var self = this;
                    self.showButton30=true;
                },
                loadTagTree:function(){
                    var self = this;
                    self.tree.data.splice(0, self.tree.data.length);
                    ajax.post('/hr/org/list/tree', null, function(data){
                        self.tree.data.push({
                            id:-1,
                            uuid:'-1',
                            name:'组织列表',
                            icon:'icon-tag',
                            style:'font-size:15px; position:relative; top:1px; margin-right:1px;'
                        });
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                self.tree.data.push(data[i]);
                            }
                        };
                        self.currentNode(self.tree.data[0]);
                    });
                },
                treeNodeAllowDrag:function(node){
                    return node.data.id !== -1;
                },
                treeNodeAllowDrop:function(dragNode, dropNode, type) {
                    return type === 'inner' && dropNode.data.id !== -1;
                },
                treeNodeDrop:function(dragNode, dropNode, type){
                    var self = this;
                    self.loading.tree = true;
                   
                },
                addRootTreeNode:function(){
                    var self = this;
                    self.loading.tree = true;
                    self.loading.addRoot = true;
                    ajax.post('/hr/org/add/root', null, function(data, status){
                        self.loading.tree = false;
                        self.loading.addRoot = false;
                        if(status !== 200) return;
                        self.tree.data.push(data);
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                currentTreeNodeChange:function(data){
                    var self = this;
                    self.currentNode(data);
                },
                treeNodeTop:function(node, nodeData){
                    var self = this;
                    var parentData = node.parent.data;
                    
                },
                treeNodeUp: function(node, nodeData){
                    var self = this;
                    var parentData = node.data;
                   
                },
                treeNodeEdit:function(node, data){
                    var self = this;
                    self.dialog.editTag.data = data;
                    self.dialog.editTag.name = data.name;
                    self.dialog.editTag.visible = true;
                },
                treeNodeAppend:function(parentNode, parent){
                    var self = this;
                    self.loading.tree = true;
                    ajax.post('/hr/org/append', {
                        parentId:parent.id
                    }, function(data, status){
                        self.loading.tree = false;
                        if(status !== 200) return;
                        if(!parent.subOrgs){
                            Vue.set(parent, 'subOrgs', []);
                        }
                        parent.subOrgs.push(data);
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                },
                treeNodeDelete:function(node, nodeData){
                    var self = this;
                    var h = self.$createElement;
                    self.$msgbox({
                        title:'危险操作',
                        message:h('div', null, [
                            h('div', {class:'el-message-box__status el-icon-warning'}, null),
                            h('div', {class:'el-message-box__message'}, [
                                h('p', null, ['此操作将永久删除组织以及子组织，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                self.loading.tree = true;
                                ajax.post('/hr/org/remove/' + nodeData.id, null, function(data, status){
                                    self.loading.tree = false;
                                    instance.confirmButtonLoading = false;
                                    done();
                                    if(status !== 200) return;
                                    self.$refs.orgTree.remove(nodeData);
                                    if(nodeData.id === self.tree.current.id){
                                        self.currentNode(self.tree.data[0]);
                                    }
                                }, null, ajax.NO_ERROR_CATCH_CODE);
                            }else{
                                instance.confirmButtonLoading = false;
                                done();
                            }
                        }
                    }).catch(function(){});
                },
                handleEditTagClose:function(){
                    var self = this;
                    self.dialog.editTag.data = '';
                    self.dialog.editTag.name = '';
                    self.dialog.editTag.visible = false;
                },
                handleEditTagCommit:function(){
                    var self = this;
                    self.loading.tree = true;
                    ajax.post('/hr/org/update/' + self.dialog.editTag.data.id, {
                        name:self.dialog.editTag.name,
                        remark:self.dialog.editTag.remark
                    }, function(data, status){
                        self.loading.tree = false;
                        if(status !== 200) return;
                        self.dialog.editTag.data.name = data.name;
                        self.handleEditTagClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                addUser:function(){
                    var self = this;
                    self.dialog.editUserTag.visible = true;
                },
                handleEditUserTagClose:function(){
                    var self = this;
                    self.dialog.editUserTag.data = '';
                    self.dialog.editUserTag.name = '';
                    self.dialog.editUserTag.visible = false;
                },
                handleEditUserTagCommit:function(){
                    var self = this;
                    self.loading.tree = true;
                    ajax.post('/hr/user/update/', {
                        name:self.dialog.editUserTag.name,
                        code:self.dialog.editUserTag.code
                    }, function(data, status){
                        self.loading.tree = false;
                        if(status !== 200) return;
                        self.handleEditUserTagClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                currentNode:function(data){
                    var self = this;
                    if(!data || data.id == -1){
                        self.tree.current = '';
                        return;
                    }
                    self.tree.current = data;
                    self.$nextTick(function(){
                        self.$refs.orgTree.setCurrentKey(data.uuid);
                    });
                    self.loadArticles(data.id);
                },
                loadArticles:function(columnId){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    
                },
                rowDelete:function(scope){
                    var self = this;
                    var row = scope.row;
                    var h = self.$createElement;
                    self.$msgbox({
                        title:'危险操作',
                        message:h('div', null, [
                            h('div', {class:'el-message-box__status el-icon-warning'}, null),
                            h('div', {class:'el-message-box__message'}, [
                                h('p', null, ['此操作将下架该文章，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                
                            }else{
                                instance.confirmButtonLoading = false;
                                done();
                            }
                        }
                    }).catch(function(){});

                }
            },
            created:function(){
                var self = this;
                self.loadTagTree();
            },
            mounted:function(){

            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;

});