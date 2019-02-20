define([
    'text!' + window.APPPATH + 'component/bvc2-layout-table-destination/bvc2-layout-table-destination.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    var bvc2LayoutTableDestination = 'bvc2-layout-table-destination';

    Vue.component(bvc2LayoutTableDestination, {
        props:['data'],
        template:tpl
    });

});