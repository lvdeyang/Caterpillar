/**
 * Created by lvdeyang on 2017/8/3.
 */

//amd [|cmd] ����
define(function(){

});

//amd [|cmd] ����
define([

    'xxx',

], function(xxx){

});

//���� amd [|cmd] �Լ���ͨ����
+function(){

    //֧�ֶ�̬����
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
        //amd �� cmd ����
        define(dependences, definition);
    }else{

        //����ط���Ҫ�ֶ���������
        dependences = deps;

        var exports = definition.apply(window, dependences);

        //ֱ����չwindow����
        if(exports && typeof exports==='object'){
            window[name] = exports;
        }
    }

}([
    'deps'
], function(deps){



});