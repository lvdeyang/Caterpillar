/**
 * Created by lvdeyang on 2017/6/20.
 */
define(function(){

    var _init = _beforeClear = _afterClear = function(){}

    var Page = function(id, init, beforeClear, afterClear){
        this.id = id;
        this.init = init || _init;
        this.beforeClear = beforeClear || _beforeClear;
        this.afterClaer = afterClear || _afterClear;
    }

    Page.prototype.version = function(){
        return '1.0.0';
    }

    var _pageFactory = {
        create:function(options){
            return new Page(options.id, options.init, options.destroy);
        }
    };

    return _pageFactory;

});