define([
    'context',
    'config',
    'jquery',
    'vue',
    'element-ui'
], function(context, config, $, Vue){

    var getApp = function(){
        return context.getProp('app');
    };

    var getUser = function(){
        return context.getProp('user');
    };

    var commons = {};

    //设置标题
    commons.setTitle = function(pageId){
        var base = config.page.title.base;
        var sub = config.page.title.sub[pageId];
        var separator = config.page.title.separator;
        var title = '';
        if(base) title += base;
        if(title && separator) title += separator;
        if(sub) title += sub;
        if(!title) title = '无标题'
        $('title').text(title);
    };

    //获取导航栏配置
    commons.getHeader = function(activeIndex){
        var links = [];
        var text = config.header.text;
        var href = config.header.href;
        var icon = config.header.icon;
        for(var i=0; i<text.length; i++){
            var link = {
                text:text[i],
                href:href[i],
                icon:icon[i]?icon[i]:null,
                isActive:activeIndex === i?true:false
            };
            if(activeIndex === i){
                link.isActive = true;
            }
            links.push(link);
        }
        return {
            links:links,
            user:getUser()
        };
    };

    //表格date类型插件返回值格式化
    commons.dateFormat = function(data){
        if(!data) return '';
        if(typeof data === 'string'){
            return data;
        }else{
            return data.format('yyyy-MM-dd');
        }
    };

    return commons;
});