define([], function(){

    var _default = {
        init:function(){},
        destroy:function(){}
    };

    var PageWrapper = function(id, page){
        this.id = id;
        this.$page = page;
        this.init = page.init || _default.init;
        this.destroy = page.destroy || _default.destroy;
        this.isActive = false;
        this.path = page.path;
        this.component = page.component;
    }

    return PageWrapper;
});