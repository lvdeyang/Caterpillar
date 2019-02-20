define([
    'text!' + window.APPPATH + 'component/bvc2-layout-table-source/bvc2-layout-table-source.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    var bvc2LayoutTableSource = 'bvc2-layout-table-source';

    Vue.component(bvc2LayoutTableSource, {
        props:['data'],
        template:tpl
    });

});