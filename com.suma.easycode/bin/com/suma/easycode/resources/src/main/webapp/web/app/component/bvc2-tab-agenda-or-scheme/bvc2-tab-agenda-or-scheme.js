define([
    'text!' + window.APPPATH + 'component/bvc2-tab-agenda-or-scheme/bvc2-tab-agenda-or-scheme.html',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'bvc2-table-agenda',
    'bvc2-table-scheme'
], function(tpl, ajax, $, Vue){

    //组件名称
    var bvc2TabAgendaOrScheme = 'bvc2-tab-agenda-or-scheme';

    Vue.component(bvc2TabAgendaOrScheme, {
        props: ['group'],
        template: tpl,
        data:function(){
            return {
                currentTab:'agenda'
            }
        },
        methods:{
            configAgenda:function(row){
                var row_copy = $.extend(true, {}, row);
                this.$emit('config-video', row_copy, 'agenda');
            },
            configScheme:function(row){
                var row_copy = $.extend(true, {}, row);
                this.$emit('config-video', row_copy, 'scheme');
            },
            runAgenda:function(row){
                var row_copy = $.extend(true, {}, row);
                this.$emit('run-agenda', row_copy);
            }
        }
    });

});
