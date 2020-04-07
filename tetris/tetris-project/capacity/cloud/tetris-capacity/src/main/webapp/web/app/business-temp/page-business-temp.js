/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'business-temp/page-business-temp.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'business-temp/page-business-temp.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-business-temp';

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
            	typeOptions:[{
            		label:'转码',
            		value:'transcode'
            	},{
            		label:'推流',
            		value:'push'
            	}],
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
                    addtemp:{
                        visible:false,
                        x:'',
                        y:'',
                        ratio:'',
                        frame:'',
                        rate:'',
                        name:'',
                        type:'',
                        loading:false
                    },
                    edittemp:{
                        visible:false,
                        id:'',
                        x:'',
                        y:'',
                        type:'',
                        ratio:'',
                        frame:'',
                        rate:'',
                        name:'',
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
                    self.dialog.edittemp.id = row.id;
                    self.dialog.edittemp.x = row.x;
                    self.dialog.edittemp.type = row.type;
                    self.dialog.edittemp.y = row.y;
                    self.dialog.edittemp.ratio = row.ratio;
                    self.dialog.edittemp.rate = row.rate;
                    self.dialog.edittemp.frame = row.frame;
                    self.dialog.edittemp.name = row.name;
                    self.dialog.edittemp.visible = true;
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
                                ajax.post('/capacity/temp/remove/' + row.id, null, function(data, status){
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
                    self.loadtemp();
                },
                handleEditGls:function(scope){
                	window.location.hash = '#/page-business-gls/' + scope.row.id;
                },
                handleCurrentChange:function(val) {
                    var self = this;
                    self.table.page.currentPage = parseInt(val);
                    //刷新数据
                    self.loadtemp();
                },
                loadtemp:function(){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    ajax.post('/capacity/temp/list', {
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
                handleAddtempClose:function(){
                    var self = this;
                    self.dialog.addtemp.x = '';
                    self.dialog.addtemp.y = '';
                    self.dialog.addtemp.ratio = '';
                    self.dialog.addtemp.rate = '';
                    self.dialog.addtemp.frame = '';
                    self.dialog.addtemp.type = '';
                    self.dialog.addtemp.name = '';
                    self.dialog.addtemp.visible = false;
                },
                handleAddtempCommit:function(){
                    var self = this;
                    self.dialog.addtemp.loading = true;
                    ajax.post('/capacity/temp/add', {
                    	x:self.dialog.addtemp.x,
                        y:self.dialog.addtemp.y,
                        ratio:self.dialog.addtemp.ratio,
                        rate:self.dialog.addtemp.rate,
                        frame:self.dialog.addtemp.frame,
                        type:self.dialog.addtemp.type,
                        name:self.dialog.addtemp.name
                    }, function(data, status){
                        self.dialog.addtemp.loading = false;
                        if(status !== 200) return;
                        self.table.data.push(data);
                        self.table.page.total += 1;
                        self.handleAddtempClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEdittempClose:function(){
                    var self = this;
                    self.dialog.edittemp.id = '';
                    self.dialog.edittemp.x = '';
                    self.dialog.edittemp.y = '';
                    self.dialog.edittemp.ratio = '';
                    self.dialog.edittemp.rate = '';
                    self.dialog.edittemp.frame = '';
                    self.dialog.edittemp.name = '';
                    self.dialog.edittemp.type = '';
                    self.dialog.edittemp.visible = false;
                },
                handleEdittempCommit:function(){
                    var self = this;
                    self.dialog.edittemp.loading = true;
                    ajax.post('/capacity/temp/edit/' + self.dialog.edittemp.id, {
                    	x:self.dialog.edittemp.x,
	                    y:self.dialog.edittemp.y,
	                    ratio:self.dialog.edittemp.ratio,
	                    rate:self.dialog.edittemp.rate,
	                    frame:self.dialog.edittemp.frame,
	                    type:self.dialog.edittemp.type,
	                    name:self.dialog.edittemp.name
                    }, function(data, status){
                        self.dialog.edittemp.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.data.length; i++){
                            if(self.table.data[i].id === data.id){
                                self.table.data.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEdittempClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                }
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
                self.loadtemp(1);
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