/**
 * Created by lzp on 2019/5/29.
 */
define([
    'text!' + window.APPPATH + 'business-role/page-business-role.html',
    'config',
    'restfull',
    'jquery',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'css!' + window.APPPATH + 'business-role/page-business-role.css'
], function (tpl, config, ajax, $, context, commons, Vue) {

    var pageId = 'page-business-role';

    var init = function () {

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
                menus: context.getProp('menus'),
                user: context.getProp('user'),
                groups: context.getProp('groups'),
                activeId: window.BASEPATH + 'index#/page-business-role',
                table: {
                    rows: [],
                    pageSize: 50,
                    pageSizes: [50, 100, 200, 400],
                    currentPage: 0,
                    total: 0
                },
                dialog:{
                    createBusinessRole:{
                        visible:false,
                        name:'',
                        loading:false
                    },
                    editBusinessRole:{
                        visible:false,
                        id:'',
                        name:'',
                        loading:false
                    }
                }
            },
            methods: {
                rowKey:function(row){
                    return 'role-' + row.id;
                },
                load:function(currentPage){
                    var self = this;
                    self.table.rows.splice(0, self.table.rows.length);
                    ajax.post('/business/role/list', {
                        currentPage:currentPage,
                        pageSize:self.table.pageSize
                    }, function(data){
                        var total = data.total;
                        var rows = data.rows;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.table.rows.push(rows[i]);
                            }
                            self.table.total = total;
                        }
                        self.table.currentPage = currentPage;
                    });
                },
                handleSizeChange:function(size){
                    var self = this;
                    self.table.pageSize = size;
                    self.load(1);
                },
                handleCurrentChange:function(currentPage){
                    var self = this;
                    self.load(currentPage);
                },
                handleCreate:function(){
                    var self = this;
                    self.dialog.createBusinessRole.visible = true;
                },
                handelDelete:function(){

                },
                handleRowEdit:function(scope){
                    var self = this;
                    var row = scope.row;
                    self.dialog.editBusinessRole.id = row.id;
                    self.dialog.editBusinessRole.name = row.name;
                    self.dialog.editBusinessRole.visible = true;
                },
                handleEditBusinessRoleClose:function(){
                    var self = this;
                    self.dialog.editBusinessRole.loading = false;
                    self.dialog.editBusinessRole.id = '';
                    self.dialog.editBusinessRole.name = '';
                    self.dialog.editBusinessRole.visible = false;
                },
                handleEditBusinessRoleSubmit:function(){
                    var self = this;
                    self.dialog.editBusinessRole.loading = true;
                    ajax.post('/business/role/edit', {
                        id:self.dialog.editBusinessRole.id,
                        name:self.dialog.editBusinessRole.name
                    }, function(data, status){
                        self.dialog.editBusinessRole.loading = false;
                        if(status !== 200){
                            return;
                        }
                        if(data){
                            for(var i=0; i<self.table.rows.length; i++){
                                if(self.table.rows[i].id == data.id){
                                    self.table.rows.splice(i, 1, data);
                                    break;
                                }
                            }
                        }
                        self.handleEditBusinessRoleClose();
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
                                h('p', null, ['此操作将永久删除该角色，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/business/role/remove/' + row.id, null, function(data, status){
                                    instance.confirmButtonLoading = false;
                                    if(status !== 200) return;
                                    for(var i=0; i<self.table.rows.length; i++){
                                        if(self.table.rows[i].id === row.id){
                                            self.table.rows.splice(i, 1);
                                            break;
                                        }
                                    }
                                    done();
                                    self.table.total = self.table.total - 1;
                                    if(self.table.rows.length===0 && self.table.currentPage>1){
                                        self.load(self.table.currentPage-1);
                                    }
                                }, null, ajax.NO_ERROR_CATCH_CODE);
                            }else{
                                instance.confirmButtonLoading = false;
                                done();
                            }
                        }
                    }).catch(function(){});
                },
                handleCreateBusinessRoleClose:function(){
                    var self = this;
                    self.dialog.createBusinessRole.visible = false;
                    self.dialog.createBusinessRole.name = '';
                    self.dialog.createBusinessRole.loading = false;
                },
                handleCreateBusinessRoleSubmit:function(){
                    var self = this;
                    self.dialog.createBusinessRole.loading = true;
                    ajax.post('/business/role/add', {
                        name:self.dialog.createBusinessRole.name
                    }, function(data, status){
                        self.dialog.createBusinessRole.loading = false;
                        if(status !== 200){
                            return;
                        }
                        self.table.rows.splice(0, 0, data);
                        self.table.total = self.table.total + 1;
                        self.handleCreateBusinessRoleClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                gotoBindUser:function(scope){
                    var self = this;
                    var row = scope.row;
                    window.location.hash = '#/page-bind-user/' + row.id + '/' + row.name + '/business';
                }
            },
            created: function () {
                var self = this;
                self.load(1);
            }
        });
    };

    var destroy = function () {

    };

    var groupList = {
        path: '/' + pageId,
        component: {
            template: '<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init: init,
        destroy: destroy
    };

    return groupList;
});