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

    var storage = {

        setItem:function(key, val){
            if(localStorage){
                localStorage.setItem(key, val);
                return;
            }
            this.cookie.setCookie(key, val, 'd30');
        },

        getItem:function(key){
            if(localStorage){
                return localStorage.getItem(key);
            }
            return this.cookie.getCookie(key);
        },

        removeItem:function(key){
            if(localStorage){
                localStorage.removeItem(key);
                return;
            }
            this.cookie.delCookie(key);
        },

        cookie:{

            setCookie:function(name,value,time){
                var strsec = this.getsec(time);
                var exp = new Date();
                exp.setTime(exp.getTime() + strsec*1);
                document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
            },

            getCookie:function(name){
                var arr,
                    reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
                if(arr=document.cookie.match(reg)){
                    return unescape(arr[2]);
                }else{
                    return null;
                }
            },

            delCookie:function(name){
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = this.getCookie(name);
                if(cval!=null)  document.cookie= name + "="+cval+";expires="+exp.toGMTString();
            },

            getsec:function(str){
                var str1=str.substring(1,str.length)*1;
                var str2=str.substring(0,1);
                if (str2=="s")
                {
                    return str1*1000;
                }
                else if (str2=="h")
                {
                    return str1*60*60*1000;
                }
                else if (str2=="d")
                {
                    return str1*24*60*60*1000;
                }
            }

        }

    }

    return storage;

});