/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'device/page-hr-device.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'device/page-hr-device.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-hr-device';

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
                    data:[],
                    page:{
                        currentPage:1,
                        sizes:[20, 50, 100, 500, 1000],
                        size:20,
                        total:0
                    }
                },
                dialog:{
                    adddevice:{
                        visible:false,
                        name:'',
                        ip:'',
                        loading:false
                    },
                    editdevice:{
                        visible:false,
                        id:'',
                        name:'',
                        ip:'',
                        loading:false
                    }
                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                rowEdit:function(scope){
                    var self = this;
                    var row = scope.row;
                    self.dialog.editdevice.id = row.id;
                    self.dialog.editdevice.name = row.name;
                    self.dialog.editdevice.ip = row.ip;
                    self.dialog.editdevice.visible = true;
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
                                h('p', null, ['此操作将永久删除排班，且不可恢复，是否继续?'])
                            ])
                        ]),
                        type:'wraning',
                        showCancelButton: true,
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        beforeClose:function(action, instance, done){
                            instance.confirmButtonLoading = true;
                            if(action === 'confirm'){
                                ajax.post('/hr/device/remove/' + row.id, null, function(data, status){
                                    instance.confirmButtonLoading = false;
                                    done();
                                    if(status !== 200) return;
                                    for(var i=0; i<self.table.data.length; i++){
                                        if(self.table.data[i].id === row.id){
                                            self.table.data.splice(i ,1);
                                            break;
                                        }
                                    }
                                    self.table.page.total -= 1;
                                }, null, ajax.NO_ERROR_CATCH_CODE);
                            }else{
                                instance.confirmButtonLoading = false;
                                done();
                            }
                        }
                    }).catch(function(){});
                },
                handleSizeChange:function(val) {
                    var self = this;
                    self.table.page.size = parseInt(val);
                    //刷新数据
                    self.loaddevice();
                },

                handleCurrentChange:function(val) {
                    var self = this;
                    self.table.page.currentPage = parseInt(val);
                    //刷新数据
                    self.loaddevice();
                },
                loaddevice:function(){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    ajax.post('/hr/device/list', {
                        currentPage:self.table.page.currentPage,
                        pageSize:self.table.page.size
                    }, function(data){
                        var rows = data.rows;
                        var total = data.total;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.table.data.push(rows[i]);
                            }
                        }
                        self.table.page.total = total;
                    });
                },
                handleAdddeviceClose:function(){
                    var self = this;
                    self.dialog.adddevice.name = '';
                    self.dialog.adddevice.ip = '';
                    self.dialog.adddevice.visible = false;
                },
                handleAdddeviceCommit:function(){
                    var self = this;
                    self.dialog.adddevice.loading = true;
                    ajax.post('/hr/device/add', {
                        name:self.dialog.adddevice.name,
                        ip:self.dialog.adddevice.ip
                    }, function(data, status){
                        self.dialog.adddevice.loading = false;
                        if(status !== 200) return;
                        self.table.data.push(data);
                        self.table.page.total += 1;
                        self.handleAdddeviceClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEditdeviceClose:function(){
                    var self = this;
                    self.dialog.editdevice.id = '';
                    self.dialog.editdevice.name = '';
                    self.dialog.editdevice.ip = '';
                    self.dialog.editdevice.visible = false;
                },
                handleEditdeviceCommit:function(){
                    var self = this;
                    self.dialog.editdevice.loading = true;
                    ajax.post('/hr/device/edit/' + self.dialog.editdevice.id, {
                        name:self.dialog.editdevice.name,
                        ip:self.dialog.editdevice.ip,
                    }, function(data, status){
                        self.dialog.editdevice.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.data.length; i++){
                            if(self.table.data[i].id === data.id){
                                self.table.data.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEditdeviceClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                }
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
                self.loaddevice(1);
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