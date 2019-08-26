/**
 * Created by lvdeyang on 2018/12/24 0024.
 */
define([
    'text!' + window.APPPATH + 'system-theme/page-system-theme.html',
    'config',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'css!' + window.APPPATH + 'system-theme/page-system-theme.css'
], function(tpl, config, ajax, context, commons, Vue){

    var pageId = 'page-system-theme';

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

                table:{
                    rows:[],
                    pageSize:50,
                    pageSizes:[50, 100, 200, 400],
                    currentPage:0,
                    total:0
                },
                dialog:{
                    createTheme:{
                        visible:false,
                        name:'',
                        url:'',
                        loading:false
                    },
                    editTheme:{
                        visible:false,
                        id:'',
                        name:'',
                        url:'',
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
                    return row.uuid;
                },
                load:function(currentPage){
                    var self = this;
                    self.table.rows.splice(0, self.table.rows.length);
                    ajax.post('/system/theme/load', {
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
                        self.table.currentPage = currentPage;
                        self.table.total = total;
                    });
                },
                handleSizeChange:function(pageSize){
                    var self = this;
                    self.table.pageSize = pageSize;
                    self.load(self.table.currentPage);
                },
                handleCurrentChange:function(currentPage){
                    var self = this;
                    self.load(currentPage);
                },
                handleCreate:function(){
                    var self = this;
                    self.dialog.createTheme.visible = true;
                },
                handleDelete:function(){

                },
                handleCreateThemeClose:function(){
                    var self = this;
                    self.dialog.createTheme.name = '';
                    self.dialog.createTheme.url = '';
                    self.dialog.createTheme.loading = false;
                    self.dialog.createTheme.visible = false;
                },
                handleCreateThemeSubmit:function(){
                    var self = this;
                    self.dialog.createTheme.loading = true;
                    ajax.post('/system/theme/add', {
                        name:self.dialog.createTheme.name,
                        url:self.dialog.createTheme.url
                    }, function(data, status){
                        self.dialog.createTheme.loading = false;
                        if(status !== 200) return;
                        self.table.rows.push(data);
                        self.table.total += 1;
                        self.handleCreateThemeClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEditThemeClose:function(){
                    var self = this;
                    self.dialog.editTheme.id = '';
                    self.dialog.editTheme.name = '';
                    self.dialog.editTheme.url = '';
                    self.dialog.editTheme.loading = false;
                    self.dialog.editTheme.visible = false;
                },
                handleEditThemeSubmit:function(){
                    var self = this;
                    self.dialog.editTheme.loading = true;
                    ajax.post('/system/theme/edit/' + self.dialog.editTheme.id, {
                        name:self.dialog.editTheme.name,
                        url:self.dialog.editTheme.url
                    }, function(data, status){
                        self.dialog.editTheme.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.rows.length; i++){
                            if(self.table.rows[i].id == data.id){
                                self.table.rows.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEditThemeClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleRowEdit:function(scope){
                    var self = this;
                    var row = scope.row;
                    self.dialog.editTheme.id = row.id;
                    self.dialog.editTheme.name = row.name;
                    self.dialog.editTheme.url = row.url;
                    self.dialog.editTheme.visible = true;
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
                                h('p', null, ['此操作将永久删除主题，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/system/theme/remove/' + row.id, null, function(response, status){
                                    instance.confirmButtonLoading = false;
                                    if(status !== 200) return;
                                    for(var i=0; i<self.table.rows.length; i++){
                                        if(self.table.rows[i].id === row.id){
                                            self.table.rows.splice(i, 1);
                                            break;
                                        }
                                    }
                                    self.table.total -= 1;
                                    if(self.table.total>0 && self.table.rows.length===0){
                                        if(self.table.currentPage === 1){
                                            self.load(self.table.currentPage);
                                        }else{
                                            self.load(self.table.currentPage-1);
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