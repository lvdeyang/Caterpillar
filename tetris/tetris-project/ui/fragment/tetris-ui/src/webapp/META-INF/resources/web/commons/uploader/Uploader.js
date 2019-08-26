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

}([
    'jquery',
    'restfull'
], function($, ajax){

    //默认分片50M
    var __blockSize = 1024*1024*50;

    //空队列循环间隔
    var __loopInterval = 2000;

    //递归调用延迟
    var __recursionTimeOut = 1000;

    //上传进度事件function(file, progress, data)
    var ON_UPLOAD_PROGRESS = 'onUploadProgress';

    //停止上传事件function(file)
    var ON_UPLOAD_CANCELED = 'onUpLoadCanceled';

    //暂停上传事件function(file)
    var ON_UPLOAD_PAUSED = 'onUploadPaused';

    //上传完成事件function(file, data)
    var ON_UPLOAD_SUCCESS = 'onUploadSuccess';

    //上传出错事件function(file, data)
    var ON_UPLOAD_ERROR = 'onUploadError';

    //上传文件
    var uploading = function(){
        var self = this;
        //启动监控
        if(self.fileQueue.length <= 0){
            self.loopInterval = setInterval(function(){
                if(self.fileQueue.length > 0){
                    clearInterval(self.loopInterval);
                    self.loopInterval = -1;
                    uploading.apply(self);
                }
            }, self.loopInterval);
            return;
        }
        var target = self.fileQueue[0];
        if(target.needCancel){
            self.fileQueue.splice(0, 1);
            if(typeof self[ON_UPLOAD_CANCELED] === 'function')self[ON_UPLOAD_CANCELED].apply(self.getContext() || self, [target]);
            setTimeout(function(){
                uploading.apply(self);
            }, self.recursionTimeOut);
            return;
        }
        if(target.isPause){
            self.fileQueue.splice(0, 1);
            self.pauseQueue.push(target);
            if(typeof self[ON_UPLOAD_PAUSED]==='function') self[ON_UPLOAD_PAUSED].apply(self.getContext() || self, [target]);
            setTimeout(function(){
                uploading.apply(self);
            }, self.recursionTimeOut);
            return;
        }
        var uuid = target.uuid;
        var beginOffset = target.offset;
        var file = target.file;
        var totalSize = file.size;
        var currentBlock = beginOffset + self.blockSize;
        var endOffset = currentBlock>totalSize?totalSize:currentBlock;
        var block = file.slice(beginOffset, endOffset);
        var formData = new FormData();
        formData.append('uuid', uuid);
        formData.append('name', encodeURI(file.name));
        formData.append('lastModified', file.lastModified);
        formData.append('beginOffset', beginOffset);
        formData.append('endOffset', endOffset);
        formData.append('blockSize', endOffset - beginOffset);
        formData.append('size', file.size);
        formData.append('type', file.type);
        formData.append('block', block);
        ajax.upload(self.url, formData, function(data, status){
            //上传异常
            if(status !== 200){
                //上传错误--跳过下一个文件
                self.fileQueue.splice(0, 1);
                if(typeof self[ON_UPLOAD_ERROR] === 'function') self[ON_UPLOAD_ERROR].apply(self.getContext() || self, [target, data]);
            }else{
                target.offset = endOffset;
                if(endOffset >= totalSize){
                    //已经传完--跳过下一个文件
                    self.fileQueue.splice(0, 1);
                    //发射事件
                    if(typeof self[ON_UPLOAD_PROGRESS] === 'function') self[ON_UPLOAD_PROGRESS].apply(self.getContext() || self, [target, parseInt((target.offset/totalSize)*100), data]);
                    setTimeout(function(){
                        if(typeof self[ON_UPLOAD_SUCCESS] === 'function') self[ON_UPLOAD_SUCCESS].apply(self.getContext() || self, [target, data]);
                    }, 1000);
                }else{
                    //发射事件
                    if(typeof self[ON_UPLOAD_PROGRESS] === 'function') self[ON_UPLOAD_PROGRESS].apply(self.getContext() || self, [target, parseInt((target.offset/totalSize)*100), data]);
                }
            }
            setTimeout(function(){
                uploading.apply(self);
            }, self.recursionTimeOut);
        }, ajax.TOTAL_CATCH_CODE);
    };

    function Uploader(url, files, blockSize, loopInterval, recursionTimeOut){
        this.url = url;
        this.fileQueue = files || [];
        this.pauseQueue = [];
        this.blockSize = blockSize || __blockSize;
        this.isRun = false;
        this.loopInterval = isNaN(loopInterval)?__loopInterval:loopInterval;
        this.recursionTimeOut = isNaN(recursionTimeOut)?__recursionTimeOut:recursionTimeOut;
        this.loopInterval = -1;
    }

    //添加上传文件
    Uploader.prototype.push = function(files){
        if(!files) return;
        if(!$.isArray){
            files = [files];
        }
        for(var i=0; i<files.length; i++){
            this.fileQueue.push(files[i]);
        }
    };

    //判断任务是否在上传队列中
    Uploader.prototype.isReady = function(key, value){
        for(var i=0; i<this.fileQueue.length; i++){
            if(this.fileQueue[i][key] === value){
                return true;
            }
        }
        return false;
    };

    //判断任务是否在暂停队列中
    Uploader.prototype.isPause = function(key, value){
        for(var i=0; i<this.pauseQueue.length; i++){
            if(this.pauseQueue[i][key] === value){
                return this;
            }
        }
        return false;
    };

    //判断任务是否在队列中
    Uploader.prototype.contains = function(key, value){
        if(this.isReady(key, value)){
            return true;
        }else{
            return this.isPause(key, value);
        }
    };

    //启动上传机制
    Uploader.prototype.run = function(){
        var self = this;
        if(self.isRun) return;
        uploading.apply(self);
    };

    //设置事件上下文环境
    Uploader.prototype.setContext = function(context){
        this.context = context;
    };

    //获取事件上下文环境
    Uploader.prototype.getContext = function(){
        return this.context;
    };

    /**
     * 1.如果files在对列中不存在（已经上传完成），发射ON_UPLOAD_CANCELED事件
     * 2.如果找到了，位置为0（上传中）,为file做标记
     * 3.如果找到了，位置不为0（待上传），从队列中删除，发射ON_UPLOAD_CANCELED事件
     */
    Uploader.prototype.cancel = function(files){
        if(!files) return;
        var self = this;
        if(!$.isArray(files)){
            files = [files];
        }
        for(var i=0; i<files.length; i++){
            var file = files[i];
            var uuid = typeof file === 'string'?file:file.uuid;
            var finded = false;
            for(var j=0; j<self.fileQueue.length; j++){
                var scopeFile = self.fileQueue[j];
                if(scopeFile.uuid === uuid){
                    if(j === 0){
                        scopeFile.needCancel = true;
                    }else{
                        self.fileQueue.splice(j, 1);
                        if(typeof self[ON_UPLOAD_CANCELED] === 'function') self[ON_UPLOAD_CANCELED].apply(self.getContext() || self, [scopeFile]);
                    }
                    finded = true;
                    break;
                }
            }
            if(!finded){
                for(var j=0; j<self.pauseQueue.length; j++){
                    if(self.pauseQueue[j].uuid === uuid){
                        if(typeof self[ON_UPLOAD_CANCELED]==='function') self[ON_UPLOAD_CANCELED].apply(self.getContext() || self, [self.pauseQueue[j]]);
                        finded = true;
                        break;
                    }
                }
            }
            if(!finded && typeof self[ON_UPLOAD_CANCELED]==='function') self[ON_UPLOAD_CANCELED].apply(self.getContext() || self, [file]);
        }
    };

    //暂停上传机制
    Uploader.prototype.pause = function(key, value){
        var self = this;
        for(var i=0; i<self.fileQueue.length; i++){
            if(self.fileQueue[i][key] === value){
                if(i === 0){
                    self.fileQueue[i].isPause = true;
                }else{
                    var file = self.fileQueue[i];
                    self.fileQueue.splice(i, 1);
                    self.pauseQueue.push(file);
                    if(typeof self[ON_UPLOAD_PAUSED]==='function') self[ON_UPLOAD_PAUSED].apply(self.getContext() || self, [file]);
                }
                return;
            }
        }
        for(var i=0; i<self.pauseQueue.length; i++){
            if(self.pauseQueue[i][key] === value){
                if(typeof self[ON_UPLOAD_PAUSED]==='function') self[ON_UPLOAD_PAUSED].apply(self.getContext() || self, [self.pauseQueue[i]]);
                return;
            }
        }
    };

    //重新开始
    Uploader.prototype.restart = function(key, value){
        var self = this;
        for(var i=0; i<self.pauseQueue.length; i++){
            if(self.pauseQueue[i][key] === value){
                var file = self.pauseQueue[i];
                file.isPause = false;
                self.pauseQueue.splice(i, 1);
                self.fileQueue.push(file);
                return;
            }
        }
    };

    //销毁上传工具
    Uploader.prototype.destroy = function(){
        var self = this;
        self.cancel(self.fileQueue);
    };

    return Uploader;
});