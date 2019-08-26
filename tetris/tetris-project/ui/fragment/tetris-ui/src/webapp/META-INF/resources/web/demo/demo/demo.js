define([
    'text!' + window.APPPATH + 'component/demo/tpl-demo.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    Vue.component('my-table', {
        props: ['items'],
        template: tpl
    });

});