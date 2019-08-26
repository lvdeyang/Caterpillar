/**
 * Created by lvdeyang on 2018/11/26 0026.
 */
define([
    'text!' + window.APPPATH + 'component/view/task/task-view.html',
    'restfull',
    'context',
    'jquery',
    'vue',
    'TweenLite',
    'element-ui',
    'css!' + window.APPPATH + 'component/view/task/task-view.css',
], function(tpl, ajax, context, $, Vue, TweenLite){

    var pluginName = 'mi-task-view';

    var metadata = {
        key:'minimize-task',
        type:'single',
        icon:'icon-tasks',
        click:'task-show',
        style:'position:relative; top:3px; font-size:30px;'
    };

    //任务停止事件
    var TASK_CANCEL = 'task-cancel';

    //任务暂停事件
    var TASK_PAUSE = 'task-pause';

    //任务重启事件
    var TASK_RESTART = 'task-restart';

    //最小化状态
    var MINIMIZE_STATE = 'minimize-state';

    //对话框关闭
    var AFTER_CLOSE = 'after-close';

    /**
     * {
     *      icon:'icon-picture',
     *      style:'position:relative; left:7px;',
     *      name:'xxxxxxxx图片.jpg',
     *      progress:40,
     *      uploadStatus:'UPLOADING|ERROR',
     *      restart:false
     *}
     */
    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                visible:false,
                minimize:false,
                size:'',
                tasks:[]
            }
        },
        methods:{
            //判断对话框是否已经打开
            isVisible:function(){
                var self = this;
                return self.visible;
            },
            //打开任务列表
            open:function(urlOrData){
                var self = this;
                if(self.visible){
                    if($.isArray(urlOrData)){
                        for(var i=0; i<urlOrData.length; i++){
                            self.tasks.push(urlOrData[i]);
                        }
                    }
                    if(self.minimize) self.minimize = false;
                    return;
                }
                if(typeof urlOrData === 'string'){
                    ajax.post(urlOrData, null, function(data){
                        var materialUploader = context.getProp('materialUploader');
                        if(data && data.length>0){
                            for(var i=0; i<data.length; i++){
                                if(!materialUploader || !materialUploader.isReady('uuid', data[i].uuid)){
                                    data[i].restart = true;
                                }
                                self.tasks.push(data[i]);
                            }
                        }
                        self.visible = true;
                    });
                }else if($.isArray(urlOrData)){
                    for(var i=0; i<urlOrData.length; i++){
                        self.tasks.push(urlOrData[i]);
                    }
                    self.visible = true;
                }
            },
            //关闭任务列表
            close:function(){
                var self = this;
                self.visible = false;
                self.tasks.splice(0, self.tasks.length);
                self.$emit(AFTER_CLOSE, metadata);
            },
            //进入最小化状态
            goIntoMinimizeState:function(){
                var self = this;
                self.minimize = true;
                self.$emit(MINIMIZE_STATE, metadata, self.exitMinimizeState);
            },
            //退出最小化状态
            exitMinimizeState:function(){
                var self = this;
                self.minimize = false;
                return metadata;
            },
            //设置上传进度
            progress:function(key, value, progress){
                var self = this;
                for(var i=0; i<self.tasks.length; i++){
                    var task = self.tasks[i];
                    if(task[key] === value){
                        TweenLite.to(task, 0.5, {progress:progress});
                        return;
                    }
                }
            },
            //上传完成
            success:function(key, value){
                var self = this;
                for(var i=0; i<self.tasks.length; i++){
                    var task = self.tasks[i];
                    if(task[key] === value){
                        self.tasks.splice(i, 1);
                        return;
                    }
                }
            },
            //上传错误
            error:function(key, value){
                var self = this;
                for(var i=0; i<self.tasks.length; i++){
                    var task = self.tasks[i];
                    if(task[key] === value){
                        Vue.set(task, 'uploadStatus', 'ERROR');
                        return;
                    }
                }
            },
            //四舍五入进度
            fixedProgress:function(progress){
                if(!progress) progress = 0;
                return parseInt(progress.toFixed(0));
            },
            //上传任务删除
            handleDelete:function(row){
                var self = this;
                if(!row.loading) row.loading = {};
                row.loading.__delete = true;
                var done = function(){
                   self.cancel(row.uuid);
                };
                self.$emit(TASK_CANCEL, row, done);
            },
            //关闭任务
            cancel:function(uuid){
                var self = this;
                var tasks = self.tasks;
                for(var i=0; i<tasks.length; i++){
                    var task = tasks[i];
                    if(task.uuid === uuid){
                        tasks.splice(i, 1);
                        return;
                    }
                }
            },
            //暂停上传任务
            handlePause:function(row){
                var self = this;
                self.$emit(TASK_PAUSE, row);
            },
            //暂停上传任务
            pause:function(uuid){
                var self = this;
                var tasks = self.tasks;
                for(var i=0; i<tasks.length; i++){
                    var task = tasks[i];
                    if(task.uuid === uuid){
                        Vue.set(task, 'restart', true);
                        return;
                    }
                }
            },
            //重启任务
            handleRestart:function(row){
                var self = this;
                self.$emit(TASK_RESTART, row);
            },
            //重启任务
            restart:function(uuid){
                var self = this;
                var tasks = self.tasks;
                for(var i=0; i<tasks.length; i++){
                    var task = tasks[i];
                    if(task.uuid === uuid){
                        Vue.set(task, 'restart', false);
                        return;
                    }
                }
            }
        },
        created:function(){
            var self = this;
            metadata.instance = self;
        }
    });

});