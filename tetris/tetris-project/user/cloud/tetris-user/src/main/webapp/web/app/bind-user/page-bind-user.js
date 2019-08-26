/**
 * Created by lvdeyang on 2018/12/24 0024.
 */
define([
    'text!' + window.APPPATH + 'bind-user/page-bind-user.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'mi-user-dialog',
    'css!' + window.APPPATH + 'bind-user/page-bind-user.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-bind-user';

    var init = function(p){

        var roleId = p.id;
        var roleName = p.name;
        var type = p.type;

        var activeId = '';
        if(type === 'system'){
            activeId = window.BASEPATH + 'index#/page-system-role';
        }else if(type === 'business'){
            activeId = window.BASEPATH + 'index#/page-business-role';
        }

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
                roleName:roleName,
                activeId:activeId,
                table:{
                    rows:[],
                    pageSize:50,
                    pageSizes:[50, 100, 200, 400],
                    currentPage:0,
                    total:0
                },
                dialog:{

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
                handleCreate:function(){
                    var self = this;
                    var rows = self.table.rows;
                    var exceptIds = [];
                    for(var i=0; i<rows.length; i++){
                        exceptIds.push(rows[i].userId);
                    }
                    if(type === 'system'){
                        self.$refs.userDialog.open('/user/list/with/except', exceptIds);
                    }else if(type === 'business'){
                        self.$refs.userDialog.open('/user/list/company/user/with/except', exceptIds);
                    }
                },
                selectedUsers:function(users, startLoading, endLoading, done){
                    var self = this;
                    var userIds = [];
                    for(var i=0; i<users.length; i++){
                        userIds.push(users[i].id);
                    }
                    startLoading();
                    if(type === 'system'){
                        ajax.post('/user/system/role/permission/bind/user', {
                            roleId:roleId,
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
                    }else if(type === 'business'){
                        ajax.post('/user/business/role/permission/bind/user', {
                            roleId:roleId,
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
                    }
                },
                handleDelete:function(){

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
                                h('p', null, ['此操作将永久删除绑定该角色，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                if(type === 'system'){
                                    ajax.post('/user/system/role/permission/unbind/' + row.id, null, function(data, status){
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
                                }else if(type === 'business'){
                                    ajax.post('/user/business/role/permission/unbind/' + row.id, null, function(data, status){
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
                                }
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
                    if(type === 'system'){
                        ajax.post('/user/system/role/permission/list/by/roleId', {
                            roleId:roleId,
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
                    }else if(type === 'business'){
                        ajax.post('/user/business/role/permission/list/by/roleId', {
                            roleId:roleId,
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
                }
            },
            created:function(){
                var self = this;
                self.load(1);
            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId + '/:id/:name/:type',
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;

});