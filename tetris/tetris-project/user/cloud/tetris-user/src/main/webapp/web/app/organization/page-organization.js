define([
    'text!' + window.APPPATH + 'organization/page-organization.html',
    'config',
    'restfull',
    'jquery',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'mi-sub-title',
    'mi-user-dialog'
], function(tpl, config, ajax, $, context, commons, Vue){

    var pageId = 'page-organization';

    var init = function(p){

        var companyId = p.id;
        var companyName = p.name;

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el:'#' + pageId + '-wrapper',
            data:{
                menus:context.getProp('menus'),
                user:context.getProp('user'),
                groups:context.getProp('groups'),
                companyName:companyName,
                activeId:window.BASEPATH + 'index#/page-company',
                tree:{
                    loading:false,
                    props:{
                        label: 'name',
                        children: 'sub'
                    },
                    expandOnClickNode:false,
                    data:[],
                    current:''
                },
                table:{
                    rows:[],
                    total:0,
                    pageSize:10,
                    currentPage:0
                },
                dialog:{
                    createOrganization:{
                        visible:false,
                        parent:'',
                        name:'',
                        loading:false
                    },
                    editOrganization:{
                        visible:false,
                        data:'',
                        id:'',
                        name:'',
                        loading:false
                    }
                }
            },
            methods:{
                loadOrganization:function(){
                    var self = this;
                    ajax.post('/organization/query/tree', {
                        companyId:companyId
                    }, function(data){
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                self.tree.data.push(data[i]);
                            }
                        }
                    });
                },
                currentTreeNodeChange:function(data, node){
                    var self = this;
                    self.tree.current = data;
                    self.currentNode(data);
                },
                treeNodeAppend:function(node, data){
                    var self = this;
                    self.dialog.createOrganization.parent = node;
                    self.dialog.createOrganization.visible = true;
                },
                treeNodeEdit:function(node, data){
                    var self = this;
                    self.dialog.editOrganization.data = data;
                    self.dialog.editOrganization.id = data.id;
                    self.dialog.editOrganization.name = data.name;
                    self.dialog.editOrganization.visible = true;
                },
                treeNodeDelete:function(node, data){
                    var self = this;
                    var h = self.$createElement;
                    self.$msgbox({
                        title:'危险操作',
                        message:h('div', null, [
                            h('div', {class:'el-message-box__status el-icon-warning'}, null),
                            h('div', {class:'el-message-box__message'}, [
                                h('p', null, ['此操作将永久删除部门信息，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/organization/delete/' + data.id, null, function(response, status){
                                    instance.confirmButtonLoading = false;
                                    if(status !== 200) return;
                                    self.$refs.organizationTree.remove(data);
                                    done();
                                }, null, ajax.NO_ERROR_CATCH_CODE);
                            }else{
                                instance.confirmButtonLoading = false;
                                done();
                            }
                        }
                    }).catch(function(){});
                },
                handleCreateOrganization:function(){
                    var self = this;
                    self.dialog.createOrganization.parent = '';
                    self.dialog.createOrganization.visible = true;
                },
                handleCreateOrganizationClose:function(){
                    var self = this;
                    self.dialog.createOrganization.visible = false;
                    self.dialog.createOrganization.parent = '';
                    self.dialog.createOrganization.name = '';
                    self.dialog.createOrganization.loading = false;
                },
                handleCreateOrganizationSubmit:function(){
                    var self = this;
                    self.dialog.createOrganization.loading = true;
                    ajax.post('/organization/add', {
                        companyId:companyId,
                        parentId:self.dialog.createOrganization.parent?self.dialog.createOrganization.parent.data.id:null,
                        name:self.dialog.createOrganization.name
                    }, function(data, status){
                        self.dialog.createOrganization.loading = false;
                        if(status !== 200) return;
                        if(self.dialog.createOrganization.parent){
                            self.$refs.organizationTree.append(data, self.dialog.createOrganization.parent);
                        }else{
                            self.tree.data.push(data);
                        }
                        self.handleCreateOrganizationClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEditOrganizationClose:function(){
                    var self = this;
                    self.dialog.editOrganization.visible = false;
                    self.dialog.editOrganization.data = '';
                    self.dialog.editOrganization.id = '';
                    self.dialog.editOrganization.name = '';
                    self.dialog.editOrganization.loading = false;
                },
                handleEditOrganizationSubmit:function(){
                    var self = this;
                    self.dialog.editOrganization.loading = true;
                    ajax.post('/organization/edit/' + self.dialog.editOrganization.id, {
                        name:self.dialog.editOrganization.name
                    }, function(data, status){
                        self.dialog.editOrganization.loading = false;
                        if(status !== 200) return;
                        self.dialog.editOrganization.data.name = data.name;
                        self.handleEditOrganizationClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                },
                currentNode:function(data){
                    if(!data) return;
                    var self = this;
                    self.$nextTick(function(){
                        self.$refs.organizationTree.setCurrentKey(data.uuid);
                    });
                    self.tree.current = data;
                    self.loadUsers(data.id, 1);
                },
                rowKey:function(row){
                    return 'organization-user-permission-' + row.uuid;
                },
                loadUsers:function(organizationId, currentPage){
                    var self = this;
                    self.table.rows.splice(0, self.table.rows.length);
                    ajax.post('/organization/user/permission/list', {
                        organizationId:organizationId,
                        currentPage:currentPage,
                        pageSize:self.table.pageSize
                    }, function(data){
                        var total = data.total;
                        var rows = data.rows;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.table.rows.push(rows[i]);
                            }
                        }
                        self.table.total = total;
                        self.table.currentPage = currentPage;
                    });
                },
                bindUsers:function(){
                    var self = this;
                    var rows = self.table.rows;
                    var exceptIds = [];
                    for(var i=0; i<rows.length; i++){
                        exceptIds.push(rows[i].userId);
                    }
                    self.$refs.userDialog.open('/user/list/by/'+companyId+'/with/except', exceptIds);
                },
                unbindUsers:function(){

                },
                handleCurrentChange:function(currentPage){
                    var self = this;
                    self.selectedUsers(self.tree.current.id, currentPage);
                },
                selectedUsers:function(users, buff, startLoading, endLoading, done){
                    var self = this;
                    var userIds = [];
                    for(var i=0; i<users.length; i++){
                        userIds.push(users[i].id);
                    }
                    startLoading();
                    ajax.post('/organization/user/permission/bind', {
                        organizationId:self.tree.current.id,
                        userIds: $.toJSON(userIds)
                    }, function(data, status){
                        endLoading();
                        if(status !== 200) return;
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                self.table.rows.push(data[i]);
                            }
                        }
                        done();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleRowDelete:function(scope){
                    var self = this;
                    var row = scope.row;

                    var h = self.$createElement;
                    self.$msgbox({
                        title:'危险操作',
                        message:h('div', null, [
                            h('div', {class:'el-message-box__status el-icon-warning'}, null),
                            h('div', {class:'el-message-box__message'}, [
                                h('p', null, ['此操作将永久将用户移除部门，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/organization/user/permission/unbind/' + row.id, null, function(response, status){
                                    instance.confirmButtonLoading = false;
                                    if(status !== 200) return;
                                    for(var i=0; i<self.table.rows.length; i++){
                                        if(self.table.rows[i].id === row.id){
                                            self.table.rows.splice(i, 1);
                                            break;
                                        }
                                    }
                                    done();
                                }, null, ajax.NO_ERROR_CATCH_CODE);
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
                self.loadOrganization();
            }
        });
    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId + '/:id/:name',
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;
});