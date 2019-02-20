/**
 * Created by lvdeyang on 2017/8/28.
 */

+function(){

    //支持动态传参
    var name, dependences, definition;
    if(arguments.length >= 3){
        name = arguments[0];
        dependences = arguments[1];
        definition = arguments[2];
    }else if(arguments.length === 2){
        dependences = arguments[0];
        definition = arguments[1];
    }else if(arguments.length === 1){
        definition = arguments[0];
    }else if(arguments.length < 1){
        return;
    }

    dependences = dependences || [];

    if(typeof define === 'function'){
        //amd 或 cmd 环境
        define(dependences, definition);
    }else{

        var exports = definition.apply(window);

        //直接扩展window对象
        if(exports && typeof exports==='object'){
            window[name] = exports;
        }
    }

}(function(){

    //去掉字符串首尾空格
    String.prototype.trim = function() {
        return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
    };

    //判断是否是空
    String.prototype.isNull = function(t){
        if(t===null || t==='' || typeof t==='undefined'){
            return true;
        }else {
            return false;
        }
    }

    //判断是否非空
    String.prototype.notNull = function(t){
        if(t===null || t==='' || typeof t==='undefined'){
            return false;
        }else {
            return true;
        }
    }

});