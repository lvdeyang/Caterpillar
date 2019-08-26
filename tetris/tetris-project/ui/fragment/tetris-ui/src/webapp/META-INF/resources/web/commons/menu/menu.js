/**
 * Created by lvdeyang on 2018/12/7 0007.
 */
define([
    'context'
], function(context){

    //解析模板url并设置值
    var setValue = function(url){
        while(true){
            var matched = url.match(/(?=\{context:)/);
            if(!matched) break;
            var end = 0;
            var placeholder = '';
            for(var i=matched.index; i<url.length; i++){
                placeholder += url[i]
                if(url[i] === '}'){
                    end = i;
                    break;
                }
            }
            var items = placeholder.replace('{context:', '')
                .replace('}', '')
                .split(';');
            var value = null;
            if(items.length === 1){
                value = context.getProp(items[0]);
            }else {
                value = context.formatValue(items[0], items[1]?items[1].replace('prop:', ''):'');
            }
            url = url.replace(new RegExp(placeholder,'g'), value);
        }
        return url;
    };

    //解析菜单链接模板
    var parseUrlTemplate = function(menus){
        if(menus && menus.length>0){
            for(var i=0; i<menus.length; i++){
                var menu = menus[i];
                //插入变量
                if(menu.link){
                    menu.link = setValue(menu.link);
                }
                if(menu.sub && menu.sub.length>0){
                    parseUrlTemplate(menu.sub);
                }
            }
        }
    };

    //获取当前菜单
    var getActiveUuid = function(menus){
        if(menus && menus.length>0){
            for(var i=0; i<menus.length; i++){
                var menu = menus[i];
                if(menu.active && menu.active===true){
                    return menu.uuid;
                }else{
                    var subActiveUuid = getActiveUuid(menu.sub);
                    if(subActiveUuid) return subActiveUuid;
                }
            }
        }
    };

    //设置主页
    var getActiveMenu = function(menus){
        if(menus && menus.length>0){
            for(var i=0; i<menus.length; i++){
                if(menus[i].active && menus[i].active===true){
                    return menus[i];
                }else{
                    if(menus[i].sub && menus[i].sub.length>0){
                        var target = getActiveMenu(menus[i].sub);
                        if(target) return target;
                    }
                }
            }
        }
    };

    //判断是否是hash
    var isHash = function(test){
        var reg = /^#\//;
        return test.match(reg);
    };

    //从hash中解析pageId
    var parsePageId = function(href){
        if(isHash(href)){
            href = href.replace('#/', '');
            return href.split('/')[0];
        }else{
            if(href.indexOf('#/') < 0){
                return href;
            }else{
                return href.split('#/')[1].split('/')[0];
            }
        }
    };

    return{
        parseUrlTemplate:parseUrlTemplate,
        getActiveUuid:getActiveUuid,
        getActiveMenu:getActiveMenu,
        isHash:isHash,
        parsePageId:parsePageId
    }

});