/**
 * Created by lvdeyang on 2018/12/4 0004.
 */
define([
    'text!' + window.APPPATH + 'component/lightbox/lightbox.html',
    'jquery',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/lightbox/lightbox.css',
], function(tpl, $, Vue){

    var pluginName = 'mi-lightbox';

    var IMAGE = 'image';

    var VIDEO = 'video';

    var AUDIO = 'audio';

    var TXT = 'txt';

    var ON_TXT_SAVE = 'on-txt-save';

    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                visible:false,
                loading:false,
                beforeClose:false,
                image:{
                    ready:false,
                    src:''
                },
                video:{
                    ready:false,
                    src:''
                },
                audio:{
                    ready:false,
                    src:''
                },
                txt:{
                    ready:false,
                    src:'',
                    buffer:'',
                    saveLoading:false
                }
            }
        },
        computed:{
            imageWidth:function(){
                var self = this;
                return self.beforeClose?'20%':'70%';
            }
        },
        methods:{
            preview:function(url, type, buffer){
                var self = this;
                self.beforeClose = false;
                self.visible = true;
                if(type === IMAGE){
                    self.loading = true;
                    self.image.src = url;
                    setTimeout(function(){
                        self.imageLoadReady();
                    }, 1000);
                }else if(type ===VIDEO){
                    self.video.src = url;
                    self.video.ready = true;
                }else if(type === AUDIO){
                    self.video.src = url;
                    self.video.ready = true;
                }else if(type === TXT){
                    self.txt.src = url;
                    self.txt.ready = true;
                    self.txt.buffer = buffer;
                }
            },
            imageLoadReady:function(){
                var self = this;
                self.loading = false;
                self.image.ready = true;
            },
            exit:function(){
                var self = this;
                self.beforeClose = true;
                setTimeout(function(){
                    self.video.ready = false;
                    self.video.src = '';
                    self.audio.ready = false;
                    self.audio.src = '';
                    self.txt.ready = false;
                    self.txt.src = '';
                    self.txt.buffer = '';
                    if( self.image.ready){
                        self.image.ready = false;
                        setTimeout(function(){
                            self.image.src = '';
                            self.loading = false;
                            self.visible = false;
                        }, 1000);
                    }else{
                        self.image.ready = false;
                        self.image.src = '';
                        self.loading = false;
                        self.visible = false;
                    }
                }, 0);
            },
            saveTxt:function(){
                var self = this;
                var txt = self.txt.src;
                var buffer = self.txt.buffer;
                self.txt.saveLoading = true;
                var done = function(){
                    self.txt.saveLoading = false;
                };
                self.$emit(ON_TXT_SAVE, txt, buffer, done);
            }
        }
    });

});