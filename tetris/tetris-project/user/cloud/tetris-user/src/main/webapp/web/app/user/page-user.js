/**
 * Created by lvdeyang on 2018/12/24 0024.
 */
define([
    'text!' + window.APPPATH + 'user/page-user.html',
    'config',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'css!' + window.APPPATH + 'user/page-user.css'
], function(tpl, config, ajax, context, commons, Vue){

    var pageId = 'page-user';

    var init = function(){

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
                classifies:[],
                table:{
                    rows:[],
                    pageSize:50,
                    pageSizes:[50, 100, 200, 400],
                    currentPage:0,
                    total:0
                },
                dialog:{
                    company:{
                        visible:false,
                        pageSize:10,
                        total:0,
                        currentPage:0,
                        rows:[],
                        current:''
                    },
                    createUser:{
                        visible:false,
                        nickname:'',
                        username:'',
                        password:'',
                        repeat:'',
                        mobile:'',
                        mail:'',
                        classify:'普通用户',
                        company:{
                            create:1,
                            id:'',
                            name:''
                        },
                        loading:false
                    },
                    editUser:{
                        visible:false,
                        editPassword:false,
                        id:'',
                        nickname:'',
                        mobile:'',
                        mail:'',
                        oldPassword:'',
                        newPassword:'',
                        repeat:'',
                        loading:false
                    }
                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                rowKey:function(row){
                    return 'user-' + row.uuid;
                },
                gotoBindSystemRole:function(scope){
                    var slef = this;
                    var row = scope.row;
                    window.location.hash = '#/page-bind-system-role/' + row.id + '/' + row.nickname + '/system';
                },
                handleCreate:function(){
                    var self = this;
                    self.dialog.createUser.visible = true;
                },
                handleCompanyClose:function(){
                    var self = this;
                    self.dialog.company.visible = false;
                    self.dialog.company.total = 0;
                    self.dialog.company.currentPage = 0;
                    self.dialog.company.rows.splice(0, self.dialog.company.rows.length);
                    self.dialog.company.current = '';
                },
                selectCompany:function(){
                    var self = this;
                    self.dialog.company.visible = true;
                    self.loadCompany(1);
                },
                companyKey:function(row){
                    return 'company-' + row.uuid;
                },
                handleCompanyCurrentChange:function(currentPage){
                    var self = this;
                    self.loadCompany(currentPage);
                },
                loadCompany:function(currentPage){
                    var self = this;
                    self.dialog.company.rows.splice(0, self.dialog.company.rows.length);
                    ajax.post('/company/list', {
                        currentPage:currentPage,
                        pageSize:self.dialog.company.pageSize
                    }, function(data){
                        var total = data.total;
                        var rows = data.rows;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.dialog.company.rows.push(rows[i]);
                            }
                        }
                        self.dialog.company.currentPage = currentPage;
                        self.dialog.company.total = total;
                    });
                },
                companyChange:function(row){
                    var self = this;
                    self.dialog.company.current = row;
                },
                companySelected:function(){
                    var self = this;
                    if(!self.dialog.company.current){
                        self.$message({
                            type:'warning',
                            message:'您还没有选择一个公司！'
                        });
                    }else{
                        self.dialog.createUser.company.id = self.dialog.company.current.id;
                        self.dialog.createUser.company.name = self.dialog.company.current.name;
                        self.handleCompanyClose();
                    }
                },
                companyActionChange:function(label){
                    var self = this;
                    if(label == 1){
                        self.dialog.createUser.company.id = '';
                        self.dialog.createUser.company.name = '';
                    }
                },
                handleCreateUserClose:function(){
                    var self = this;
                    self.dialog.createUser.nickname = '';
                    self.dialog.createUser.username = '';
                    self.dialog.createUser.password = '';
                    self.dialog.createUser.repeat = '';
                    self.dialog.createUser.mobile = '';
                    self.dialog.createUser.mail = '';
                    self.dialog.createUser.classify = '普通用户';
                    self.dialog.createUser.company.create = 1;
                    self.dialog.createUser.company.id = '';
                    self.dialog.createUser.company.name = '';
                    self.dialog.createUser.visible = false;
                },
                handleCreateUserSubmit:function(){
                    var self = this;
                    self.dialog.createUser.loading = true;
                    var params = {
                        nickname:self.dialog.createUser.nickname,
                        username:self.dialog.createUser.username,
                        password:self.dialog.createUser.password,
                        repeat:self.dialog.createUser.repeat,
                        mobile:self.dialog.createUser.mobile,
                        mail:self.dialog.createUser.mail,
                        classify:self.dialog.createUser.classify
                    };
                    if(self.dialog.createUser.company.create == 1){
                        params['companyName'] = self.dialog.createUser.company.name;
                    }else if(self.dialog.createUser.company.create == 0){
                        params['companyId'] = self.dialog.createUser.company.id;
                    }
                    ajax.post('/user/add', params, function(data, status){
                        self.dialog.createUser.loading = false;
                        if(status !== 200) return;
                        self.table.rows.push(data);
                        self.handleCreateUserClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleDelete:function(){

                },
                handleRowEdit:function(scope){
                    var self = this;
                    var row = scope.row;
                    self.dialog.editUser.id = row.id;
                    self.dialog.editUser.nickname = row.nickname;
                    self.dialog.editUser.mobile = row.mobile;
                    self.dialog.editUser.mail = row.mail;
                    self.dialog.editUser.visible = true;
                },
                handleEditUserClose:function(){
                    var self = this;
                    self.dialog.editUser.id = '';
                    self.dialog.editUser.nickname = '';
                    self.dialog.editUser.mobile = '';
                    self.dialog.editUser.mail = '';
                    self.dialog.editUser.editPassword = '';
                    self.dialog.editUser.oldPassword = '';
                    self.dialog.editUser.newPassword = '';
                    self.dialog.editUser.repeat = '';
                    self.dialog.editUser.visible = false;
                },
                handleEditUserSubmit:function(){
                    var self = this;
                    self.dialog.editUser.loading = true;
                    ajax.post('/user/edit/' + self.dialog.editUser.id, {
                        nickname:self.dialog.editUser.nickname,
                        mobile:self.dialog.editUser.mobile,
                        mail:self.dialog.editUser.mail,
                        editPassword:self.dialog.editUser.editPassword,
                        oldPassword:self.dialog.editUser.oldPassword,
                        newPassword:self.dialog.editUser.newPassword,
                        repeat:self.dialog.editUser.repeat
                    }, function(data, status){
                        self.dialog.editUser.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.rows.length; i++){
                            if(self.table.rows[i].id === self.dialog.editUser.id){
                                self.table.rows.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEditUserClose();
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
                                h('p', null, ['此操作将永久删除该用户，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/user/delete/' + row.id, null, function(data, status){
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
                },
                handleSizeChange:function(size){
                    var self = this;
                    self.table.pageSize = size;
                    self.load(self.table.currentPage);
                },
                handleCurrentChange:function(currentPage){
                    var self = this;
                    self.load(currentPage);
                },
                load:function(currentPage){
                    var self = this;
                    self.table.rows.splice(0, self.table.rows.length);
                    ajax.post('/user/list', {
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
                }
            },
            created:function(){
                var self = this;
                ajax.post('/user/query/types', null, function(data){
                    for(var i=0; i<data.length; i++){
                        self.classifies.push(data[i]);
                    }
                });
                self.load(1);
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