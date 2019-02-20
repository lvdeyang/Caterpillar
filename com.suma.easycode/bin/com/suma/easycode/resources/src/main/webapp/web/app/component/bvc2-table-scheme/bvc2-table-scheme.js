define([
    'text!' + window.APPPATH + 'component/bvc2-table-scheme/bvc2-table-scheme.html',
    'restfull',
    'vue',
    'element-ui'
], function(tpl, ajax, Vue){

    var bvc2TableScheme = 'bvc2-table-scheme';

    Vue.component(bvc2TableScheme, {
        props:['group'],
        template: tpl,
        data:function(){
            return {
                rows:[],
                total:0,
                pageSize:10,
                currentPage:1,
                form:{
                    dialogVisible:false,
                    labelWidth:'80px',
                    name:'',
                    remark:''
                }
            }
        },
        methods:{
            load:function(){
                var table_instance = this;
                ajax.get('/scheme/load/' + table_instance.group.id, {
                    pageSize:table_instance.pageSize,
                    currentPage:table_instance.currentPage
                }, function(data){
                    if(table_instance.rows.length > 0) table_instance.rows.splice(0, table_instance.rows.length);
                    if(data.rows && data.rows.length>0){
                        for(var i=0; i<data.rows.length; i++){
                            table_instance.rows.push(data.rows[i]);
                        }
                    }
                    table_instance.total = parseInt(data.total);
                });
            },
            addScheme:function(){
                var table_instance = this;
                table_instance.form.dialogVisible = true;
            },
            saveScheme:function(){
                var table_instance = this;
                ajax.post('/scheme/save/' + table_instance.group.id, {
                    name:table_instance.form.name,
                    remark:table_instance.form.remark
                }, function(data){
                    table_instance.rows.push(data);
                    table_instance.form.dialogVisible = false;
                });
            },
            removeScheme:function(row){
                var table_instance = this;
                ajax.remove('/scheme/remove/' + row.id, null, function(){
                    for(var i=0; i<table_instance.rows.length; i++){
                        if(table_instance.rows[i] === row){
                            table_instance.rows.splice(i, 1);
                            break;
                        }
                    }
                });
            },
            configScheme:function(row){
                this.$emit('config-scheme', row);
            }
        },
        mounted:function(){
            var table_instance = this;
            table_instance.currentPage = 1;
            table_instance.load();
        }
    });

});