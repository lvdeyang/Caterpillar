define([
    'vue',
    'vue-router',
    'config',
    'page-wrapper',
    'context'
], function(Vue, VueRouter, config, PageWrapper, context){

    //把组件安装一下
    //VueRouter.install(Vue);

    var loadHistory = [];

    //从path中解析pageId
    var parsePageId = function(path){
        var pageId = path.split('/')[1];
        return pageId;
    };

    //判断页面是否已经被加载
    var isLoaded = function(pageId){
        for(var i=0; i<loadHistory.length; i++){
            if(loadHistory[i].id === pageId){
                return true;
            }
        }
        return false;
    };

    //根据id获取当前页面
    var getPage = function(pageId){
        for(var i=0; i<loadHistory.length; i++){
            if(loadHistory[i].id === pageId){
                return loadHistory[i];
            }
        }
        return null;
    };

    var router = new VueRouter({
        routes:[]
    });

    //定义一下path格式 /pageId/:param/:param/:param
    router.beforeEach(function(to, from, next){
        var pageId = parsePageId(to.path);
        if(!pageId){
            next(false);
        }else{
            next();
            if(isLoaded(pageId)){
                next(true);
            }else{
                require([pageId], function(page){
                    var pageWrapper = new PageWrapper(pageId, page);
                    loadHistory.push(pageWrapper);
                    router.addRoutes([{path:pageWrapper.path, component:pageWrapper.component}]);
                });
            }
        }
    });

    router.afterEach(function(to, from, next){
        setTimeout(function(){
            var fromId = parsePageId(from.path);
            var fromPage = getPage(fromId);
            var toId = parsePageId(to.path);
            var toPage = getPage(toId);
            if(fromPage || toPage){
                var app = context.getProp('app');
                if(!app.loading) app.loading = true;
                //销毁前一个页面
                if(fromPage && fromPage.isActive){
                    fromPage.destroy.apply(fromPage.$page);
                    fromPage.isActive = false;
                }
                //这里做一个小小的延迟，要不然页面效果看起来不好
                setTimeout(function(){
                    //初始化下一个页面
                    if(toPage && !toPage.isActive){
                        toPage.init.apply(toPage.$page, [to.params]);
                        toPage.isActive = true;
                    }
                    app.loading = false;
                }, config.router.loading.timeout);
            }
        });
    });
    return router;
});
