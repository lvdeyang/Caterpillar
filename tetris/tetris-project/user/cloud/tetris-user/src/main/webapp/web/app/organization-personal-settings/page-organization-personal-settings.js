/**
 * Created by lvdeyang on 2018/12/24 0024.
 */
define([
    'text!' + window.APPPATH + 'organization-personal-settings/page-organization-personal-settings.html',
    'config',
    'restfull',
    'jquery',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'mi-theme-dialog',
    'mi-upload-dialog',
    'css!' + window.APPPATH + 'organization-personal-settings/page-organization-personal-settings.css'
], function(tpl, config, ajax, $, context, commons, Vue){

    var pageId = 'page-organization-personal-settings';

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;

        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
                menus: context.getProp('menus'),
                user: context.getProp('user'),
                groups: context.getProp('groups'),
                basePath:window.BASEPATH,
                company:'',
                selected:{
                    theme:'',
                    name:'',
                    homeLink:'',
                    platformFullName:'',
                    platformShortName:'',
                    logoStyle:'',
                    logoShortName:'',
                    file:'',
                    previewUrl:''
                },
                dialog:{
                    upload:{
                        fileType:['image'],
                        multiple:false
                    }
                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                handleSelectTheme:function(){
                    var self = this;
                    self.$refs.miThemeDialog.open('/system/theme/load/with/except', [self.company.themeId]);
                },
                onThemeSelected:function(theme, startLoading, endLoading, close){
                    var self = this;
                    self.selected.theme = theme;
                    close();
                },
                fileSelected:function(files, done){
                    var self = this;
                    var file = files[0];
                    self.selected.file = file;
                    self.selected.previewUrl = URL.createObjectURL(file);
                    console.log(files[0]);
                    done();
                },
                handleUpload:function(){
                    var self = this;
                    self.$refs.uploadDialog.open();
                },
                saveSettings:function(){
                    var self = this;
                    var formData = new FormData();
                    formData.append('companyId', self.company.id);
                    if(self.selected.theme) formData.append('themeId', self.selected.theme.id);
                    if(self.selected.name) formData.append('name', self.selected.name);
                    if(self.selected.homeLink) formData.append('homeLink', self.selected.homeLink);
                    if(self.selected.platformFullName) formData.append('platformFullName', self.selected.platformFullName);
                    if(self.selected.platformShortName) formData.append('platformShortName', self.selected.platformShortName);
                    if(self.selected.logoStyle) formData.append('logoStyle', self.selected.logoStyle);
                    if(self.selected.logoShortName) formData.append('logoShortName', self.selected.logoShortName);
                    if(self.selected.file) formData.append('fileName', self.selected.file.name);
                    if(self.selected.file) formData.append('file', self.selected.file);
                    ajax.upload('/company/persional/settings', formData, function(data){
                        self.$message({
                            type:'success',
                            message:'设置成功！'
                        });
                        self.company = data;
                        self.selected.theme = '';
                        self.selected.name = '';
                        self.selected.homeLink = '';
                        self.selected.platformFullName = '';
                        self.selected.platformShortName = '';
                        self.selected.logoStyle = '';
                        self.selected.logoShortName = '';
                        self.selected.file = '';
                        self.selected.previewUrl = '';
                        window.location.reload();
                    });
                }
            },
            created:function(){
                var self = this;
                ajax.post('/company/subordinate', null, function(data){
                    self.company = data;
                });
            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;

});