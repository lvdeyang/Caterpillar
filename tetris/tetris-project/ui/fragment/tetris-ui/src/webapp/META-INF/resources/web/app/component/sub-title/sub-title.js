/**
 * Created by lvdeyang on 2018/11/21 0021.
 */
define([
    'text!' + window.APPPATH + 'component/sub-title/sub-title.html',
    'restfull',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/sub-title/sub-title.css',
], function(tpl, ajax, Vue){

    var pluginName = 'mi-sub-title';

    Vue.component(pluginName, {
        template: tpl
    });

});