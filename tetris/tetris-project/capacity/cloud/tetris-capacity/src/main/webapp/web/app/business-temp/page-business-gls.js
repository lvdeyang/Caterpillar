/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'business-temp/page-business-gls.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'business-temp/page-business-gls.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-business-gls';

    var init = function(p){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
            	options:[{
                    value: 0,
                    label: '字幕'
                }, {
                    value: 1,
                    label: '台标'
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
                    addgls:{
                    	type:0,
                        visible:false,
                        content:'',
                    	x:0,
                    	y:0,
                    	width:0,
                    	height:0,
                    	backgroundColor:'',
                    	fontColor:'',
                    	fontSize:0,
                    	rollSpead:0,
                    	fontFamily:'',
                    	trackType:'',
                    	logoPath:'',
                        loading:false
                    },
                    editgls:{
                        visible:false,
                        id:'',
                        type:0,
                        content:'',
                    	x:0,
                    	y:0,
                    	width:0,
                    	height:0,
                    	backgroundColor:'',
                    	fontColor:'',
                    	fontSize:0,
                    	rollSpead:0,
                    	fontFamily:'',
                    	trackType:'',
                    	logoPath:'',
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
                    self.dialog.editgls.id = row.id;
                    self.dialog.editgls.type= row.type;
                    self.dialog.editgls.content= row.content;
                    self.dialog.editgls.x= row.x;
                    self.dialog.editgls.y= row.y;
                    self.dialog.editgls.width= row.width;
                    self.dialog.editgls.height= row.height;
                    self.dialog.editgls.backgroundColor= row.backgroundColor;
                    self.dialog.editgls.fontColor= row.fontColor;
                    self.dialog.editgls.fontSize= row.fontSize;
                    self.dialog.editgls.rollSpead= row.rollSpead;
                    self.dialog.editgls.fontFamily= row.fontFamily;
                    self.dialog.editgls.trackType= row.trackType;
                    self.dialog.editgls.logoPath= row.logoPath;
                    
                    self.dialog.editgls.visible = true;
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
                                ajax.post('/capacity/temp/gls/remove/' + row.id, null, function(data, status){
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
                    self.loadgls();
                },

                handleCurrentChange:function(val) {
                    var self = this;
                    self.table.page.currentPage = parseInt(val);
                    //刷新数据
                    self.loadgls();
                },
                loadgls:function(){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    ajax.post('/capacity/temp/gls/list', {
                    	tempId:p.tempId,
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
                handleAddglsClose:function(){
                    var self = this;
                    self.dialog.addgls.content='';
                    self.dialog.addgls.type= 0;
                    self.dialog.addgls.x= 0;
                    self.dialog.addgls.y= 0;
                    self.dialog.addgls.width= 0;
                    self.dialog.addgls.height= 0;
                    self.dialog.addgls.backgroundColor= '';
                    self.dialog.addgls.fontColor= '';
                    self.dialog.addgls.fontSize= 0;
                    self.dialog.addgls.rollSpead= 0;
                    self.dialog.addgls.fontFamily= '';
                    self.dialog.addgls.trackType= '';
                    self.dialog.addgls.logoPath= '';
                    self.dialog.addgls.visible = false;
                },
                handleAddglsCommit:function(){
                    var self = this;
                    self.dialog.addgls.loading = true;
                    ajax.post('/capacity/temp/gls/add', {
                    	content: self.dialog.addgls.content,
                        x: self.dialog.addgls.x,
                        y: self.dialog.addgls.y,
                        width: self.dialog.addgls.width,
                        height: self.dialog.addgls.height,
                        backgroundColor: self.dialog.addgls.backgroundColor,
                        fontColor: self.dialog.addgls.fontColor,
                        fontSize: self.dialog.addgls.fontSize,
                        rollSpead: self.dialog.addgls.rollSpead,
                        fontFamily: self.dialog.addgls.fontFamily,
                        trackType: self.dialog.addgls.trackType,
                        logoPath: self.dialog.addgls.logoPath,
                        type:self.dialog.addgls.type,
                        tempId:p.tempId
                    }, function(data, status){
                        self.dialog.addgls.loading = false;
                        if(status !== 200) return;
                        self.table.data.push(data);
                        self.table.page.total += 1;
                        self.handleAddglsClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleEditglsClose:function(){
                    var self = this;
                    self.dialog.editgls.id = '';
                    self.dialog.editgls.content='';
                    self.dialog.editgls.type= 0;
                    self.dialog.editgls.x= 0;
                    self.dialog.editgls.y= 0;
                    self.dialog.editgls.width= 0;
                    self.dialog.editgls.height= 0;
                    self.dialog.editgls.backgroundColor= '';
                    self.dialog.editgls.fontColor= '';
                    self.dialog.editgls.fontSize= 0;
                    self.dialog.editgls.rollSpead= 0;
                    self.dialog.editgls.fontFamily= '';
                    self.dialog.editgls.trackType= '';
                    self.dialog.editgls.logoPath= '';
                    self.dialog.editgls.visible = false;
                },
                handleEditglsCommit:function(){
                    var self = this;
                    self.dialog.editgls.loading = true;
                    ajax.post('/capacity/temp/gls/edit/' + self.dialog.editgls.id, {
                    	content: self.dialog.editgls.content,
                        x: self.dialog.editgls.x,
                        y: self.dialog.editgls.y,
                        width: self.dialog.editgls.width,
                        height: self.dialog.editgls.height,
                        backgroundColor: self.dialog.editgls.backgroundColor,
                        fontColor: self.dialog.editgls.fontColor,
                        fontSize: self.dialog.editgls.fontSize,
                        rollSpead: self.dialog.editgls.rollSpead,
                        fontFamily: self.dialog.editgls.fontFamily,
                        trackType: self.dialog.editgls.trackType,
                        logoPath: self.dialog.editgls.logoPath,
                        type:self.dialog.editgls.type,
                        tempId:p.tempId
                    }, function(data, status){
                        self.dialog.editgls.loading = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.data.length; i++){
                            if(self.table.data[i].id === data.id){
                                self.table.data.splice(i, 1, data);
                                break;
                            }
                        }
                        self.handleEditglsClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                }
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
                self.loadgls(1);
            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId+'/:tempId',
        component:{
        	template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;

});