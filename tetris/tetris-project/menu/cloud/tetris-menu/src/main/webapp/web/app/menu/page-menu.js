/**
 * Created by lvdeyang on 2018/12/24 0024.
 */
define([
    'text!' + window.APPPATH + 'menu/page-menu.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'mi-system-role-dialog',
    'css!' + window.APPPATH + 'menu/page-menu.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-menu';

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
                tree:{
                    props:{
                        label: 'title',
                        children: 'sub',
                        isLeaf: 'isLeaf'
                    },
                    expandOnClickNode:false,
                    data:[],
                    current:''
                },
                table:{
                    total:0,
                    pageSize:10,
                    currentPage:0,
                    data:[]
                },
                loading:{
                    menu:false,
                    addRoot:false,
                    table:false
                },
                form:{
                    menu:{
                        editable:false,
                        id:'',
                        uuid:'',
                        title:'',
                        link:'',
                        icon:'',
                        style:'',
                        isGroup:'',
                        parentId:'',
                        serial:''
                    }
                },
                dialog:{

                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                loadMenus:function(){
                    var self = this;
                    ajax.post('/menu/list/tree', null, function(data){
                        self.tree.data = data || [];
                        self.currentNode(data[0]);
                    });
                },
                render:function(h, scope){
                    var data = scope.data;
                    return h('div', {style:'font-size:14px;'}, [
                        h('span', {class:data.icon, style:'margin-right:5px;'}, []),
                        h('span', null, data.title)
                    ]);
                },
                currentTreeNodeChange:function(data){
                    var self = this;
                    self.initFormMenu();
                    self.tree.current = data;
                    self.form.menu = $.extend(true, self.form.menu, data);
                    self.form.menu.isGroup += '';
                    self.loadPermissionRoles(data.id, 1);
                },
                treeNodeEdit:function(){
                    var self = this;
                    self.form.menu.editable = true;
                },
                treeNodeCancel:function(){
                    var self = this;
                    self.form.menu.editable = false;
                },
                treeNodeSave:function(){
                    var self = this;
                    self.loading.menu = true;
                    ajax.post('/menu/save/' + self.form.menu.id, {
                        title:self.form.menu.title,
                        link:self.form.menu.link,
                        icon:self.form.menu.icon,
                        style:self.form.menu.style,
                        serial:self.form.menu.serial
                    }, function(data, status){
                        self.loading.menu = false;
                        if(status !== 200) return;
                        self.tree.current.title = data.title;
                        self.tree.current.link = data.link;
                        self.tree.current.icon = data.icon;
                        self.tree.current.style = data.style;
                        self.tree.current.serial = data.serial;
                        self.currentNode(data);
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                addRootTreeNode:function(){
                    var self = this;
                    self.loading.menu = true;
                    self.loading.addRoot = true;
                    ajax.post('/menu/add/root', null, function(data, status){
                        self.loading.menu = false;
                        self.loading.addRoot = false;
                        if(status !== 200) return;
                        self.tree.data.push(data);
                        self.currentNode(data);
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                treeNodeAppend:function(parentNode, parent){
                    var self = this;
                    self.loading.menu = true;
                    ajax.post('/menu/add/sub', {
                        parentId:parent.id
                    }, function(data, status){
                        self.loading.menu = false;
                        if(status !== 200) return;
                        self.$refs.menuTree.append(data, parentNode);
                        self.currentNode(data);
                        parent.isGroup = true;
                        parent.isLeaf = false;
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                treeNodeDelete:function(node, data){
                    var self = this;
                    self.loading.menu = true;
                    ajax.post('/menu/remove/' + data.id, null, function(menus, status){
                        self.loading.menu = false;
                        if(status !== 200) return;
                        self.$refs.menuTree.remove(data);
                        self.currentNode(self.tree.data[0]);
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                initFormMenu:function(){
                    var self = this;
                    self.form.menu.editable = false;
                    self.form.menu.id = '';
                    self.form.menu.uuid = '';
                    self.form.menu.title = '';
                    self.form.menu.link = '';
                    self.form.menu.icon = '';
                    self.form.menu.style = '';
                    self.form.menu.isGroup = '';
                    self.form.menu.parentId = '';
                    self.form.menu.serial = '';
                },
                currentNode:function(data){
                    if(!data) return;
                    var self = this;
                    self.$nextTick(function(){
                        self.$refs.menuTree.setCurrentKey(data.uuid);
                    });
                    self.initFormMenu();
                    self.tree.current = data;
                    self.form.menu = $.extend(true, self.form.menu, data);
                    self.form.menu.isGroup += '';
                    self.loadPermissionRoles(data.id, 1);
                },
                loadPermissionRoles:function(menuId, currentPage){
                    var self = this;
                    if(self.tree.current.isGroup) return;
                    self.table.data.splice(0, self.table.data.length);
                    self.loading.table = true;
                    ajax.post('/system/role/menu/permission/list', {
                        menuId:menuId,
                        pageSize:self.table.pageSize,
                        currentPage:currentPage
                    }, function(data, status){
                        setTimeout(function(){
                            self.loading.table = false;
                        }, 500);
                        if(status !== 200) return;
                        var total = data.total;
                        var rows = data.rows;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.table.data.push(rows[i]);
                            }
                        }
                        self.table.total = total;
                        self.table.currentPage = currentPage;
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                removePermission:function(scope){
                    var self = this;
                    var row = scope.row;
                    self.loading.table = true;
                    ajax.post('/system/role/menu/permission/unbind/' + row.id, null, function(data, status){
                        self.loading.table = false;
                        if(status !== 200) return;
                        for(var i=0; i<self.table.data.length; i++){
                            if(self.table.data[i].id === row.id){
                                self.table.data.splice(i, 1);
                                break;
                            }
                        }
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                bindingSystemRole:function(){
                    var self = this;
                    var existRoles = self.table.data;
                    if(existRoles && existRoles.length>0){
                        var existRoleIds = [];
                        for(var i=0; i<existRoles.length; i++){
                            existRoleIds.push(existRoles[i].roleId);
                        }
                        self.$refs.systemRoleDialog.open(existRoleIds);
                    }else{
                        self.$refs.systemRoleDialog.open();
                    }
                },
                selectedRoles:function(roles, buffer, startLoading, endLoading, done){
                    var self = this;
                    startLoading();
                    ajax.post('/system/role/menu/permission/bind/roles/' + self.tree.current.id, {
                        roles: $.toJSON(roles)
                    }, function(data, status){
                        endLoading();
                        if(status !== 200) return;
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                self.table.data.splice(0, 0, data[i]);
                            }
                        }
                        done();
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                }
            },
            created:function(){
                var self = this;
                //加载菜单
                self.loadMenus();
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