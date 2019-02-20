define([
    'text!' + window.APPPATH + 'component/bvc2-tab/bvc2-tab.html',
    'restfull',
    'vue'
], function(tpl, ajax, Vue){

    //组件名称
    var bvc2Tab = 'bvc2-tab';

    //默认的媒体名称
    var defaultMediaName = '新建的视频' + new Date().getTime();

    Vue.component(bvc2Tab, {
        props: ['group', 'config', 'video'],
        template: tpl,
        data:function(){
            return {
                newMediaName:defaultMediaName,
                random:new Date().getTime(),
                barOpacity:0,
                barLeft:0
            }
        },
        computed:{
            barStyle:function(){
                this.barOpacity = 0;
                this.barLeft = 0;
                for(var i=0; i<this.items.length; i++){
                    if(this.items[i].isActive === true){
                        this.barOpacity = 1;
                        this.barLeft = (i*100+35) + 'px';
                        break;
                    }
                }
                return 'opacity:'+this.barOpacity+'; left:'+this.barLeft+';';
            }
        },

        methods:{
            //标签状态
            itemClass:function(item){
                return item.__current?'bvc2-tab-item active':'bvc2-tab-item';
            },

            //添加标签
            addItem:function(e){
                var tab_instance = this;
                if(!tab_instance.config){
                    tab_instance.$message({
                        type:'warning',
                        message:'请先选择一个议程或者方案！'
                    });
                    return;
                }
                var h = tab_instance.$createElement;
                tab_instance.$msgbox({
                    title:'请输入视频名称',
                    message:h('input', {
                        class:{
                            'el-input__inner':true
                        },
                        attrs:{
                            placeholder:'请输入视频名称'
                        },
                        domProps:{
                            value:tab_instance.newMediaName
                        },
                        on: {
                            input: function (event) {
                                console.log(event.target.value);
                                //tab_instance.$emit('input', event.target.value);
                                tab_instance.newMediaName = event.target.value;
                            }
                        }
                    }, null),
                    showCancelButton: true,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            instance.confirmButtonLoading = true;
                            instance.confirmButtonText = '请稍后';

                            var _uri = '';
                            if(tab_instance.config.__businessType === 'agenda'){
                                _uri = '/agenda/add/video/' + tab_instance.config.id;
                            }else if(tab_instance.config.__businessType === 'scheme'){
                                _uri = '/scheme/add/video/' + tab_instance.config.id;
                            }

                            ajax.post(_uri, {
                                name:tab_instance.newMediaName
                            }, function(data){
                                instance.confirmButtonLoading = false;
                                instance.confirmButtonText = '确定';
                                if(!tab_instance.config.videos) Vue.set(tab_instance.config, 'videos', []);
                                tab_instance.config.videos.push(data);
                                tab_instance.newMediaName = defaultMediaName;
                                done();
                                tab_instance.clickItem(data);
                                tab_instance.$message({
                                    type:'success',
                                    message:'成功创建视频！'
                                });
                                //发射一个事件
                                tab_instance.$emit('video-create', e, data);
                            });
                        }else if(action === 'cancel'){
                            done();
                            tab_instance.$message({
                                type:'info',
                                message:'操作取消！'
                            });
                        }
                    }
                });
            },

            //删除标签
            removeItem:function(e){
                var tab_instance = this;
                var $close = e.target;
                var $item = $close.parentNode;
                var index = parseInt($item.getAttribute('data-index'));
                var item = tab_instance.items[index];
                tab_instance.$confirm('此操作将永久删除视频“'+item.text+'”, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    beforeClose:function(action, instance, done){
                        if(action === 'confirm'){
                            instance.confirmButtonLoading = true;
                            instance.confirmButtonText = '请稍后';
                            //TODO 删除一个媒体
                            setTimeout(function(){
                                instance.confirmButtonLoading = false;
                                instance.confirmButtonText = '确定';
                                tab_instance.items.splice(index, 1);
                                done();
                                tab_instance.$message({
                                    type:'success',
                                    message:'成功删除媒体！'
                                });
                                //发射一个事件
                                tab_instance.$emit('video-remove', e, {media:item, index:index});
                            }, 500);
                        }else if(action === 'cancel'){
                            done();
                            tab_instance.$message({
                                type:'info',
                                message:'操作取消！'
                            });
                        }
                    }
                });
            },

            //选中标签
            clickItem:function(video, e){
                var tab_instance = this;
                if(!video.__current){
                    for(var i=0; i<tab_instance.config.videos.length; i++){
                        if(tab_instance.config.videos[i] !== video){
                            Vue.set(tab_instance.config.videos[i], '__current', false);
                        }
                    }
                    Vue.set(video, '__current', true);
                    tab_instance.$emit('video-click', video, e);
                }
            }
        }
    });
    return Vue;
});