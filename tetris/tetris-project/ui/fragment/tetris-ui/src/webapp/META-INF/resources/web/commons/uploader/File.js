/**
 * Created by lvdeyang on 2018/11/28 0028.
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

    function File(uuid, offset, file){
        this.uuid = uuid;
        this.offset = offset;
        this.file = file;
        this.needCancel = false;
    };

    File.prototype.formatSize = function(size){
        if(size){
            return formatSize(size);
        }else{
            return formatSize(this.file.size);
        }
    };

    var formatSize = function(size){
        if(!size || isNaN(size)) return '-';
        var unit = 'B';
        if(size <= 1024){
            return size + unit;
        }else if(size>1024 && size<=Math.pow(1024, 2)){
            unit = 'KB';
            return (parseInt(size*100/1024)/100)+unit;
        }else if(size>Math.pow(1024, 2) && size<=Math.pow(1024, 3)){
            unit = 'MB';
            return (parseInt(size*100/Math.pow(1024, 2))/100)+unit;
        }else if(size>Math.pow(1024, 3) && size<=Math.pow(1024, 4)){
            unit = 'GB';
            return (parseInt(size*100/Math.pow(1024, 3))/100)+unit;
        }
    };

    return File;
});
