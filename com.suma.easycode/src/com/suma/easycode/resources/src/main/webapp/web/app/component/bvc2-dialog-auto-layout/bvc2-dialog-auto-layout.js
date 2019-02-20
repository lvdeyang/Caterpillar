define([
    'text!' + window.APPPATH + 'component/bvc2-dialog-auto-layout/bvc2-dialog-auto-layout.html',
    'jquery.layout.auto',
    'vue',
    'element-ui'
], function(tpl, $, Vue){

    //组件名称
    var bvc2DialogAutoLayout = 'bvc2-dialog-auto-layout';

    var getLayoutContainer = function(el){
        return $(el).find('.bvc2-dialog-auto-layout-editor');
    };

    //私有事件
    var __layoutSelected = 'layout-selected';

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

    Vue.component(bvc2DialogAutoLayout, {
        template:tpl,
        data:function(){
            return {
                layout:'',
                show:false,
                target:'',
                options:{
                    columnOptions:[
                        {
                            value:1,
                            label:1
                        },{
                            value:2,
                            label:2
                        },{
                            value:3,
                            label:3
                        },{
                            value:4,
                            label:4
                        }
                    ],
                    rowOptions:[
                        {
                            value:1,
                            label:1
                        },{
                            value:2,
                            label:2
                        },{
                            value:3,
                            label:3
                        },{
                            value:4,
                            label:4
                        }
                    ],
                }
            }
        },
        watch:{
            layout:function(){
                var instance = this;
                setTimeout(function(){
                    instance.refreshLayout();
                }, 0);
            },
            show:function(val){
                if(!val) this.target = null;
            }
        },
        methods:{
            refreshLayout:function(){
                var $container = getLayoutContainer(this.$el);
                var options = this.layout;
                $container['layout-auto']('create', {
                    cell:{
                        column:options.column,
                        row:options.row
                    },
                    cellspan:options.cellspan,
                    theme:'dark',
                    name:options.name,
                    editable:options.editable,
                    drop:function(e){
                        var $cell = $(this);
                    }
                });
            },

            columnChange:function(column){
                var layout_instance = this;
                //获取设置前的值
                var $container = getLayoutContainer(layout_instance.$el);
                var oldColumn = $container['layout-auto']('queryColumn');
                layout_instance.$confirm('此操作将清空屏幕上的配置，并且设置为：' + column + '行' + layout_instance.layout.row + '列, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            var newLayout = $.extend(true, {}, layout_instance.layout);
                            newLayout.column = column;
                            newLayout.cellspan = [];
                            layout_instance.layout = newLayout;
                        }else if(action === 'cancel'){
                            layout_instance.layout.column = oldColumn;
                        }
                        done();
                    }
                });
            },

            rowChange:function(row){
                var layout_instance = this;
                //获取设置前的值
                var $container = getLayoutContainer(layout_instance.$el);
                var oldRow = $container['layout-auto']('queryRow');
                layout_instance.$confirm('此操作将清空屏幕上的配置，并且设置为：' + layout_instance.layout.column + '行' + row + '列, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            var newLayout = $.extend(true, {}, layout_instance.layout);
                            newLayout.row = row;
                            newLayout.cellspan = [];
                            layout_instance.layout = newLayout;
                        }else{
                            layout_instance.layout.row = oldRow;
                        }
                        done();
                    }
                });
            },

            open:function(){
                var layout_instance = this;
                setTimeout(function(){
                    layout_instance.refreshLayout();
                });
            },

            layoutOk:function(e){
                var dialog_instance = this;
                var done = function(){
                    dialog_instance.show = false;
                }
                var $editor = getLayoutContainer(this.$el);
                var tpl = $editor['layout-auto']('generateTpl');
                __emit(dialog_instance, __layoutSelected, {
                    tpl:tpl,
                    target:dialog_instance.target
                }, e, done);
            }
        }
    });

});