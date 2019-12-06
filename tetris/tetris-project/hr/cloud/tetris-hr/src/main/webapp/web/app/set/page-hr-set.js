/**
 * Created by lvdeyang on 2018/12/26 0026.
 */
define([
    'text!' + window.APPPATH + 'set/page-hr-set.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'css!' + window.APPPATH + 'set/page-hr-set.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-hr-set';

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
                days:[],
                types:[],
                schelist:[],
                selectMoth:'',
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
                loading:{
                    tree:false,
                    addRoot:false
                },
                dialog:{
                    addSet:{
                        visible:false,
                        sche:'',
                        start:'',
                        end:'',
                        userId:'',
                        loading:false
                    }
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
                    ajax.post('/hr/set/list/tree', null, function(data){
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
                currentTreeNodeChange:function(data){
                    var self = this;
                    self.currentNode(data);
                },
                currentNode:function(data){
                    var self = this;
                    if(!data || data.id == -1){
                        self.tree.current = '';
                        return;
                    }
                    self.tree.current = data;
                    self.$nextTick(function(){
                        self.$refs.setTree.setCurrentKey(data.uuid);
                    });
                    self.loadUsers(data.id);
                },
                rowSet:function(scope){
                	var self=this;
                	self.dialog.addSet.visible = true;
                	self.dialog.addSet.userId=scope.row.id;
                },
                loadUsers:function(orgId){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    self.days.splice(0,self.days.length)
                    ajax.post('/hr/set/list/user/'+orgId, {
                    	year:2019,
                    	month:12
                    }, function(data){
                        if(data.userMaps && data.userMaps.length>0){
                            for(var i=0; i<data.userMaps.length; i++){
                                self.table.data.push(data.userMaps[i]);
                            }
                        }
                        if(data.daysMaps && data.daysMaps.length>0){
                            for(var i=0; i<data.daysMaps.length; i++){
                                self.days.push(data.daysMaps[i]);
                            }
                        };
                    });
                },
                handleAddSetClose:function(){
                    var self = this;
                    self.dialog.addSet.sche = '';
                    self.dialog.addSet.start = '';
                    self.dialog.addSet.end = '';
                    self.dialog.addSet.visible = false;
                },
                handleAddSetCommit:function(){
                    var self = this;
                    self.dialog.addSet.loading = true;
                    ajax.post('/hr/set/set', {
                    	sche:self.dialog.addSet.sche,
                        start:self.dialog.addSet.start,
                        end:self.dialog.addSet.end,
                        userId:self.dialog.addSet.userId
                    }, function(data, status){
                        self.dialog.addSet.loading = false;
                        if(status !== 200) return;
                        self.loadUsers(self.tree.current.id);
                        self.handleAddSetClose();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                initScheSel:function(){
                	var self=this;
                	self.schelist.splice(0,self.schelist.length)
                    ajax.post('/hr/sche/alllist',null, function(data){
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                self.schelist.push(data[i]);
                            }
                        }
                       
                    });
                	
                }
            },
            created:function(){
                var self = this;
                self.loadTagTree();
                self.initScheSel();
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