/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'director'

],function(Router){

    var Context = function(pageList){
        this.pageList = pageList || [];
        this.currentPage = null;
        this.$changeable = null;
    }

    Context.prototype.version = function(){
        return '1.0.0';
    }

    Context.prototype.register = function(page){
        //参数校验
        if(!page.id || typeof page.init!=='function')
            throw new Error('当前注测的不是标准页面');

        this.pageList.push(page);
    }

    Context.prototype.load = function(pageId){

        //error页面特殊处理
        if(pageId === 'error'){
            if(arguments.length === 1){
                //director跳转不做处理
                return;
            }else {
                //改变url hash
                var _url_old = window.location.href,
                    _hash_old = window.location.hash;
                window.location.href = _url_old.replace(_hash_old, '') + '#/error';
            }
        }

        var _pageList = this.pageList,
            _currentPage = this.currentPage,
            _page, i, _args = [];

        for(i=1; i<arguments.length; i++){
            _args.push(arguments[i]);
        }

        if(_currentPage){
            //销毁页面
            for(i=0; i<_pageList.length; i++){
                _page = _pageList[i];
                if(_page.id === _currentPage){

                    //清空内容之前
                    if(typeof _page.beforeClear === 'function') _page.beforeClear.apply(_page, []);

                    //自动清空内容
                    this.$changeable.empty();

                    //滚动条置顶
                    $('html, body').scrollTop(0);

                    //清空之后
                    if(typeof _page.afterClear === 'function') _page.afterClear.apply(_page, []);

                    break;
                }
            }
        }

        for(i=0; i<_pageList.length; i++){
            _page = _pageList[i];
            if(_page.id === pageId){
                _page.init.apply(_page, _args);
                this.currentPage = pageId;
                break;
            }
        }

    }

    Context.prototype.setContentDom = function($e){
        this.$changeable = $e;
    }

    Context.prototype.setContent = function($e){
        this.$changeable.append($e);
    }

    Context.prototype.setModel = function(model){
        this.model = model;
    }

    var _context = new Context([]);

    //创建路由
    var router = Router({
        '/:pageId': function(pageId){
            _context.load.apply(_context, [pageId]);
        },
        '/:pageId/:param1':function(pageId, param1){
            _context.load.apply(_context, [pageId, param1]);
        },
        '/:pageId/:param1/:param2':function(pageId, param1, param2){
            _context.load.apply(_context, [pageId, param1, param2]);
        },
        '/:pageId/:param1/:param2/:param3':function(pageId, param1, param2, param3){
            _context.load.apply(_context, [pageId, param1, param2, param3]);
        },
        '/:pageId/:param1/:param2/:param3/:param4':function(pageId, param1, param2, param3, param4){
            _context.load.apply(_context, [pageId, param1, param2, param3, param4]);
        }
    });
    router.init();

    return _context;

});