/**
 * Created by lvdeyang on 2017/8/3.
 */

//amd [|cmd] 定义
define(function(){

});

//amd [|cmd] 定义
define([

    'xxx',

], function(xxx){

});

//兼容 amd [|cmd] 以及普通环境
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

        //这个地方需要手动加入依赖
        dependences = deps;

        var exports = definition.apply(window, dependences);

        //直接扩展window对象
        if(exports && typeof exports==='object'){
            window[name] = exports;
        }
    }

}([
    'deps'
], function(deps){



});