define([
    'text!' + window.APPPATH + 'component/bvc2-system-table-base/bvc2-system-table-base.html',
    'restfull',
    'vue',
    'element-ui',
    'date'
], function(tpl, ajax, Vue){

    //组件名称
    var bvc2SystemTableBase = 'bvc2-system-table-base';
    console.log(bvc2SystemTableBase);


    //id序列
    var key_suffix = 0;

    var tableSelector = '.el-table__body-wrapper';

    //列状态
    var __rowStatusCreate = 'create';
    var __rowStatusEdit = 'edit';
    var __rowStatusNormal = 'normal';
    var __rowStatusRemove = 'remove';

    //数据类型
    var __columnTypeSimple = 'Simple';
    var __columnTypeDate = 'date';
    var __columnTypeDateTime = 'dateTime';
    var __columnTypeColor = 'color';
    var __columnTypeSelect = 'select';
    var __columnTypeMultiple = 'multiple';
    var __columnTypeEntities = 'entity';

    //私有事件
    var __prefix = '_before';
    var __suffix = '_after';
    var __rowAddSave = 'row-add-save';
    var __rowAddCancel = 'row-add-cancel';
    var __rowEditSave = 'row-edit-save';
    var __rowEditCancel = 'row-edit-cancel';
    var __rowEdit = 'row-edit';
    var __rowRemove = 'row-remove';
    var __removeAll = 'remove-all';

    //page-layout私有事件
    var __rowChanged = 'row-changed';
    var __htmlCellEdit = 'html-cell-edit';

    //page-group-list私有事件
    var __rowCreate = 'row-create';
    var __rowClick = 'row-click';
    var __rowCancel = 'row-cancel';

    //发射事件方法
    var __emit = function(_self, eName, data, e, done){
        if(typeof _self.$listeners[eName] === 'function'){
            if(typeof done === 'function'){
                _self.$emit(eName, data, done, e);
            }else{
                _self.$emit(eName, data, e);
            }
        }else{
            if(typeof done === 'function'){
                done();
            }
        }
    };

    Vue.component(bvc2SystemTableBase, {
        props:[
            'columns',
            'load',
            'save',
            'update',
            'remove',
            'removebatch',
            'pk',
            'options',
            'buttoncreate',
            'buttonremove',
            'search',
            'breadcrumb',
            'highlight'],
        template:tpl,
        data:function(){
            return {
                tableSelector:tableSelector,
                buttons:{
                    create:[{
                        label:'保存数据',
                        icon:'icon-save',
                        click:__rowAddSave
                    },{
                        label:'取消保存',
                        icon:'el-icon-close',
                        click:__rowAddCancel
                    }],
                    edit:[{
                        label:'保存修改',
                        icon:'icon-save',
                        click:__rowEditSave
                    },{
                        label:'取消修改',
                        icon:'el-icon-close',
                        click:__rowEditCancel
                    }],
                    normal:[{
                        label:'编辑数据',
                        icon:'el-icon-edit',
                        click:__rowEdit
                    },{
                        label:'删除数据',
                        icon:'el-icon-delete',
                        click:__rowRemove
                    }]
                },
                rows:[],
                selectedRow:[],
                total:0,
                pageSize:100,
                currentPage:1,
                dialog:{
                    entityEditorVisible:false,
                    title:'选择数据',
                    data:[],
                    column:{
                        entity:{
                            table:{
                                columns:[],
                                width:''
                            }
                        }
                    },
                    row:'',
                    selected:[]
                }

            }
        },
        computed:{
            optionsWidth:function(){
                var length1 = this.buttons.create?this.buttons.create.length:0;
                var length2 = this.buttons.edit?this.buttons.edit.length:0;
                var length3 = this.buttons.normal?this.buttons.normal.length:0;
                return Math.max(length1, length2, length3)*50;
            }
        },
        methods: {

            /***********
             * 功能api
             ***********/

            //id生成器
            keyGenerator:function(){
                key_suffix+=1;
                return new Date().getTime() + '__' + key_suffix;
            },

            /***********
             * 处理行选中
             ***********/

            toggleSelection: function (rows) {
                if(true){
                }else{
                    this.$refs.multipleTable.clearSelection();
                }
            },

            handleSelectionChange:function (val) {
                this.selectedRow = val;
            },

            /**************
             * 处理行点击
             **************/
            handleRowClick:function(row, e, column){
                var table_instance = this;

                if(row.id > 0){
                    __emit(table_instance, __rowClick, row, e);
                }
            },

            //加载数据
            loadData:function(){
                var table_instance = this;
                ajax.get(table_instance.load, {
                    pageSize:table_instance.pageSize,
                    currentPage:table_instance.currentPage
                }, function(data){
                    data.total = data.total || 0;
                    if(data.rows && data.rows.length>0){
                        table_instance.rows.splice(0, table_instance.rows.length);
                        table_instance.total = data.total;
                        for(var i=0; i<data.rows.length; i++){
                            table_instance.rows.push(data.rows[i]);
                        }
                    }
                    if(typeof success === 'function') success.apply(table_instance, data.rows);

                    //数据行发生变化
                    __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);

                });
            },

            /***********
             * 处理分页
             ***********/

            handleSizeChange:function(val) {
                var table_instance = this;
                table_instance.pageSize = parseInt(val);
                //刷新数据
                table_instance.loadData();
            },

            handleCurrentChange:function(val) {
                var table_instance = this;
                table_instance.currentPage = parseInt(val);
                //刷新数据
                table_instance.loadData();
            },

            /***********
             * 批量按钮
             ***********/

            //新建数据
            create:function(){

                var table_instance = this;

                __emit(table_instance, __rowCreate);

                var newRow = {__rowStatus:__rowStatusCreate};
                newRow[table_instance.pk] = -1;
                for(var i=0; i<table_instance.columns.length; i++){
                    if(table_instance.columns[i].type !== 'html'){
                        newRow[table_instance.columns[i].prop] = '';
                        if(table_instance.columns[i].hidden) newRow[table_instance.columns[i].hidden] = '';
                    }
                }
                table_instance.rows.unshift(newRow);
                //数据行发生变化
                __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);

            },

            //批量删除
            removeAll:function(e){
                var table_instance = this;
                if(table_instance.selectedRow && table_instance.selectedRow.length>0){
                    table_instance.$confirm('您将要删除“'+table_instance.selectedRow.length+'”条数据, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        beforeClose:function(action, instance, d){
                            if(action === 'confirm'){
                                var done = function(){
                                    instance.confirmButtonLoading = true;
                                    instance.confirmButtonText = '请稍后';
                                    var pks = [];
                                    for(var i=0; i<table_instance.selectedRow.length; i++){
                                        pks.push(table_instance.selectedRow[i][table_instance.pk]);
                                    }
                                    ajax.remove(table_instance.removebatch, {
                                        ids:pks
                                    }, function(data){
                                        instance.confirmButtonLoading = false;
                                        instance.confirmButtonText = '确定';
                                        //删除行
                                        for(var i=0; i<table_instance.selectedRow.length; i++){
                                            for(var j=0; j<table_instance.rows.length; j++){
                                                if(table_instance.selectedRow[i] === table_instance.rows[j]){
                                                    table_instance.rows.splice(j, 1);
                                                    break;
                                                }
                                            }
                                        }
                                        //处理分页
                                        table_instance.total = table_instance.total - table_instance.selectedRow.length;
                                        //清空选中
                                        table_instance.selectedRow = [];
                                        d();

                                        //数据行发生变化
                                        __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);

                                        //删除之后
                                        __emit(table_instance, __removeAll + __suffix, table_instance.selectedRow, e);
                                    });
                                };
                                //删除之前
                                __emit(table_instance, __removeAll + __prefix, table_instance.selectedRow, e, done);
                            }else if(action === 'cancel'){
                                d();
                                table_instance.$message({
                                    type:'info',
                                    message:'操作取消！'
                                });
                            }
                        }
                    });
                }
            },

            /***********
             * 行操作
             ***********/

            //行操作
            rowButtonClick:function(e, scope, eName){
                var table_instance = this;
                if(eName === __rowAddSave){
                    table_instance.rowAddSave(e, scope, eName);
                }else if(eName === __rowAddCancel){
                    table_instance.rowAddCancel(e, scope, eName);
                }else if(eName === __rowEditSave){
                    table_instance.rowEditSave(e, scope, eName);
                }else if(eName === __rowEditCancel){
                    table_instance.rowEditCancel(e, scope, eName);
                }else if(eName === __rowEdit){
                    table_instance.rowEdit(e, scope, eName);
                }else if(eName === __rowRemove){
                    table_instance.rowRemove(e, scope, eName);
                }else{
                    //发射事件
                    __emit(table_instance, eName, scope.row, e);
                }
            },

            //新增行
            rowAddSave:function(e, scope, eName){
                var table_instance = this;
                //由前可能会改变数据格式
                var done = function(data){
                    ajax.post(table_instance.save, (data || scope.row), function(data){
                        scope.row[table_instance.pk] = data[table_instance.pk];
                        Vue.set(scope.row, '__rowStatus', __rowStatusNormal);
                        //处理分页
                        table_instance.total = table_instance.total + 1;

                        //数据行发生变化
                        __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);

                        setTimeout(function(){
                            __emit(table_instance, __rowClick, scope.row, e);
                        }, 0);

                        //添加后
                        __emit(table_instance, eName + __suffix, scope.row, e);
                    });
                };
                //添加前
                __emit(table_instance, eName + __prefix, scope.row, e, done);
            },

            //取消新增
            rowAddCancel:function(e, scope, eName){
                var table_instance = this;
                __emit(table_instance, __rowCancel);
                var done = function(){
                    table_instance.rows.splice(0, 1);
                    //数据行发生变化
                    __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);
                };
                __emit(table_instance, eName, scope.row, e, done);
            },

            //保存编辑
            rowEditSave:function(e, scope, eName){
                var table_instance = this;
                var done = function(data){
                    var url = table_instance.update + '/' + scope.row[table_instance.pk];
                    ajax.post(url, (data || scope.row), function(data){
                        Vue.set(scope.row, '__rowStatus', __rowStatusNormal);

                        setTimeout(function(){
                            __emit(table_instance, __rowClick, scope.row, e);
                        }, 0);

                        //保存后
                        __emit(table_instance, eName + __suffix, scope.row, e);
                    });
                };
                //保存前
                __emit(table_instance, eName + __prefix, scope.row, e, done);
            },

            //取消编辑
            rowEditCancel:function(e, scope, eName){
                var table_instance = this;
                var done = function(){
                    Vue.set(scope.row, '__rowStatus', __rowStatusNormal);
                };
                __emit(table_instance, eName, scope.row, e, done);
            },

            //编辑行
            rowEdit:function(e, scope, eName){
                var table_instance = this;
                var done = function(){
                    Vue.set(scope.row, '__rowStatus', __rowStatusEdit);
                };
                __emit(table_instance, eName, scope.row, e, done);
            },

            //删除行
            rowRemove:function(e, scope, eName){
                var table_instance = this;
                table_instance.$confirm('此操作将删除"'+scope.row.name+'"设备组，是否继续？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, d){
                        if(action === 'confirm'){
                            Vue.set(scope.row, '__rowStatus', __rowStatusRemove);
                            var done = function(){
                                instance.confirmButtonLoading = true;
                                instance.confirmButtonText = '执行中...';
                                var url = table_instance.remove + '/' + scope.row[table_instance.pk];
                                ajax.remove(url, null, function(data){

                                    instance.confirmButtonLoading = false;
                                    instance.confirmButtonText = '确定';
                                    for(var i=0; i<table_instance.rows.length; i++){
                                        if(table_instance.rows[i] === scope.row){
                                            table_instance.rows.splice(i, 1);
                                            break;
                                        }
                                    }
                                    d();

                                    //处理分页
                                    table_instance.total = table_instance.total - 1;
                                    //数据行发生变化
                                    __emit(table_instance, __rowChanged, table_instance.rows, tableSelector);
                                    //清除设备列表
                                    __emit(table_instance, __rowCancel);
                                    //删除后
                                    __emit(table_instance, eName + __suffix, scope.row, e);
                                });
                            };
                            //删除前
                            __emit(table_instance, eName + __prefix, scope.row, e, done);
                        }else if(action === 'cancel'){
                            d();
                            table_instance.$message({
                                type:'info',
                                message:'操作取消！'
                            });
                        }
                    }

                });

            },

            //单选框事件
            columnSelectChange:function(row, column){
                var table_instance = this;
                //配置hidden的时候更新prop
                if(column.hidden){
                    var options = column.options;
                    for(var i=0; i<options.length; i++){
                        if(options[i].value === row[column.hidden]){
                            row[column.prop] = options[i].label;
                        }
                    }
                }
                if(typeof column.change === 'function'){
                    column.change(row, column, table_instance);
                }
            },

            //html单元格编辑按钮点击事件
            htmlCellEdit:function(row, column, e){
                //发射事件
                __emit(this, __htmlCellEdit, {
                    row:row,
                    column:column
                }, e);
            },

            //多选实体类单元格按钮点击事件
            entityCellEdit:function(row, column, e){
                var table_instance = this;
                var load = column.entity.table.load;
                ajax.get(load, null, function(data){
                    if(data && data.length>0){
                        table_instance.dialog.data.splice(0, table_instance.dialog.data.length);
                        for(var i=0; i<data.length; i++){
                            table_instance.dialog.data.push(data[i]);
                        }
                        table_instance.dialog.title = column.entity.table.title;
                        table_instance.dialog.entityEditorVisible = true;
                        table_instance.dialog.column = column;
                        table_instance.dialog.row = row;

                        //这里需要组件先渲染
                        setTimeout(function(){
                            var prop = row[column.prop];
                            if(prop && prop.length>0){
                                for(var i=0; i<prop.length; i++){
                                    for(var j=0; j<data.length; j++){
                                        if(prop[i][column.entity.value] == data[j][column.entity.table.pk]){
                                            table_instance.$refs.dialogTable.toggleRowSelection(data[j]);
                                            break;
                                        }
                                    }
                                }
                            }else {
                                table_instance.$refs.dialogTable.clearSelection();
                            }
                        }, 0);
                    }
                });
            },

            //处理对话框表格选择
            handleDialogTableSelectionChange:function(val){
                this.dialog.selected = val;
            },

            //处理对话框表格数据选中
            handleDialogOk:function(e){
                var table_instance = this;
                var selected = table_instance.dialog.selected || [];
                var column = table_instance.dialog.column;
                var row = table_instance.dialog.row;

                if(!row[column.prop]) Vue.set(row, column.prop, []);

                var prop = row[column.prop];

                //增量对比化对比
                var delArr = [];
                var addArr = [];

                for(var i=0; i<prop.length; i++){
                    var finded = false;
                    for(var j=0; j<selected.length; j++){
                        if(prop[i][column.entity.value] == selected[j][column.entity.table.pk]){
                            finded = true;
                            break;
                        }
                    }
                    if(!finded) delArr.push(prop[i][column.entity.value]);
                }

                for(var i=0; i<selected.length; i++){
                    var finded = false;
                    for(var j=0; j<prop.length; j++){
                        if(selected[i][column.entity.table.pk] == prop[j][column.entity.value]){
                            finded =true;
                            break;
                        }
                    }
                    if(!finded) {
                        var add = {};
                        add[column.entity.value] = selected[i][column.entity.table.pk];
                        add[column.entity.label] = selected[i][column.entity.table.label];
                        addArr.push(add);
                    }
                }

                //删除
                for(var i=0; i<delArr.length; i++){
                    for(var j=0; j<prop.length; j++){
                        if(prop[j][column.entity.value] == delArr[i]){
                            prop.splice(j, 1);
                            break;
                        }
                    }
                }

                //增加
                for(var i=0; i<addArr.length; i++){
                    prop.push(addArr[i]);
                }

                table_instance.dialog.entityEditorVisible = false;

            }

        },

        //渲染完成
        mounted:function(){
            var table_instance = this;
            //合并按钮
            if(table_instance.options && table_instance.options.length>0){
                var arr = table_instance.buttons.normal;
                var arr0 = table_instance.options;
                var indexBegin = 1;
                for(var i=0; i<arr0.length; i++){
                    arr.splice(indexBegin+i, 0, arr0[i]);
                }
            }
            //初始化数据
            table_instance.loadData();
        }
    });

});