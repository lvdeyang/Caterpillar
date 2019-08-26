/**
 * Created by lvdeyang on 2018/11/23 0023.
 */
define([
    'text!' + window.APPPATH + 'component/dialog/mims/image/mims-image.html',
    'restfull',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/dialog/mims/image/mims-image.css',
], function(tpl, ajax, Vue){

    var pluginName = 'mi-image-dialog';

    Vue.component(pluginName, {
        template: tpl,
        data:function(){
            return {
                __buffer:null,
                dialog:{
                    visible:false,
                    loading:false
                },
                breadCrumb:[],
                data:[],
                current:''
            }
        },
        methods:{
            //对话框关闭时初始化数据
            closed:function(){
                var self = this;
                self.data.splice(0, self.data.length);
                self.breadCrumb.splice(0, self.breadCrumb.length);
                self.__buffer = null;
                self.current = '';
                self.dialog.visible = false;
            },
            //打开文件夹对话框
            open:function(){
                var self = this;
                self.load(null);
                self.dialog.visible = true;
            },
            load:function(folderId){
                var self = this;
                self.dialog.loading = true;
                ajax.post('/media/picture/feign/load', {
                    folderId:folderId
                }, function(data, status){
                    setTimeout(function(){
                        self.dialog.loading = false;
                        self.data.splice(0, self.data.length);
                        self.breadCrumb.splice(0, self.breadCrumb.length);
                        self.current = '';
                        if(status !== 200) return;
                        var rows = data.rows;
                        var breadCrumb = data.breadCrumb;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                rows[i].__select = false;
                                self.data.push(rows[i]);
                            }
                        }
                        var items = [breadCrumb];
                        var current = breadCrumb;
                        while(current.next){
                            current = current.next;
                            items.push(current);
                        }
                        for(var i=0; i<items.length; i++){
                            self.breadCrumb.push(items[i]);
                        }
                    }, 500);
                }, null, ajax.NO_ERROR_CATCH_CODE);
            },
            //缓存数据
            setBuffer:function(data){
                var self = this;
                self.__buffer = data;
            },
            //获取缓存数据
            getBuffer:function(){
                var self = this;
                return self.__buffer;
            },
            //文件夹选中
            handleOkButton:function(){
                var self = this;
                self.$emit('selected-image', self.current.previewUrl, self.__buffer, function(){
                    self.dialog.loading = true;
                }, function(){
                    self.dialog.loading = false;
                }, function(){
                    self.dialog.visible = false;
                });
            },
            pictureItemClass:function(item, index){
                var c = 'picture-item';
                if(item.__select){
                    c += ' select'
                }
                if((index+1) % 9 === 0){
                    c += ' last';
                }
                return c;
            },
            pictureSelect:function(picture){
                var self = this;
                for(var i=0; i<self.data.length; i++){
                    if(self.data[i].id === picture.id){
                        self.data[i].__select = true;
                        self.current = self.data[i];
                    }else{
                        self.data[i].__select = false;
                    }
                }
            }
        }
    });

});