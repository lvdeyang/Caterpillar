/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'sche/page-hr-sche.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'sche/page-hr-sche.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-hr-sche';

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
                    addSche:{
                        visible:false,
                        name:'',
                        start:'',
                        end:'',
                        loading:false
                    },
                    editSche:{
                        visible:false,
                        id:'',
                        name:'',
                        start:'',
                        end:'',
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
                    self.dialog.editSche.id = row.id;
                    self.dialog.editSche.name = row.name;
                    //self.dialog.editSche.start = Date.parse(row.start);
                    //self.dialog.editSche.end = Date.parse(row.end);
                    self.dialog.editSche.visible = true;
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
                                ajax.post('/hr/sche/remove/' + row.id, null, function(data, status){
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
                    self.loadSche();
                },

                handleCurrentChange:function(val) {
                    var self = this;
                    self.table.page.currentPage = parseInt(val);
                    //刷新数据
                    self.loadSche();
                },
                loadSche:function(){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    ajax.post('/hr/sche/list', {
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
                handleAddScheClose:function(){
                    var self = this;
                    self.dialog.addSche.name = '';
                    self.dialog.addSche.start = '';
                    self.dialog.addSche.end = '';
                    self.dialog.addSche.visible = false;
                },
                handleAddScheCommit:function(){
                    var self = this;
                    self.dialog.addSche.loading = true;
                    ajax.post('/hr/sche/add', {
                        name:self.dialog.addSche.name,
                        start:self.dialog.addSche.start,
                        end:self.dialog.addSche.end
                    }, function(data, status){
                        self.dialog.addSche.loading = false;
                        if(status !== 200) return;
                        self.table.data.push(data);
                        self.table.page.total += 1;
                        self.handleAddScheClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEditScheClose:function(){
                    var self = this;
                    self.dialog.editSche.id = '';
                    self.dialog.editSche.name = '';
                    self.dialog.editSche.start = '';
                    self.dialog.editSche.end = '';
                    self.dialog.editSche.visible = false;
                },
                handleEditScheCommit:function(){
                    var self = this;
                    self.dialog.editSche.loading = true;
                    ajax.post('/hr/sche/edit/' + self.dialog.editSche.id, {
                        name:self.dialog.editSche.name,
                        start:self.dialog.editSche.start,
                        end:self.dialog.editSche.end
                    }, function(data, status){
                        self.dialog.editSche.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.data.length; i++){
                            if(self.table.data[i].id === data.id){
                                self.table.data.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEditScheClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                }
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
                self.loadSche(1);
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