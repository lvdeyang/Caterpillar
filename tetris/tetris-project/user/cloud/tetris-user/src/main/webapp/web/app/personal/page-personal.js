/**
 * Created by sms on 2019/8/8.
 */
define([
    'text!' + window.APPPATH + 'personal/page-personal.html',
    'config',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'mi-frame',
    'mi-tag-dialog',
    'css!' + window.APPPATH + 'personal/page-personal.css'
], function (tpl, config, ajax, context, commons, Vue) {

    var pageId = 'page-personal';

    var init = function () {

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
                loading: false,
                tab: {
                    current: "个人信息"
                },
                userInfo: {},
                password: {
                    oldPassword: "",
                    newPassword: "",
                    repeat: ""
                }
            },
            computed: {},
            watch: {},
            methods: {
                load: function () {
                    var self = this;
                    ajax.post('/user/query', null, function (data, status) {
                        if (status == 200) {
                            self.userInfo = data;
                            self.user.mobile = data.mobile;
                            self.user.mail = data.mail;
                            self.user.tags = data.tags;
                        }
                    }, null, ajax.NO_ERROR_CATCH_CODE)
                },
                handleTabClick: function (tab, event) {
                    var self = this;
                    self.tab.current = tab.label;
                },
                handleTagEdit: function () {
                    var self = this;
                    self.$refs.tagDialog.open('/media/tag/feign/list/get', self.userInfo.tags);
                },
                selectedTags: function (buff, tags, startLoading, endLoading, close) {
                    var self = this;
                    startLoading();
                    buff.splice(0, buff.length);
                    for (var i = 0; i < tags.length; i++) {
                        buff.push(tags[i].name);
                    }
                    endLoading();
                    close();
                },
                handleTagRemove: function (tag, value) {
                    for (var i = 0; i < tag.length; i++) {
                        if (tag[i] === value) {
                            tag.splice(i, 1);
                            break;
                        }
                    }
                },
                handleResetInfo: function () {
                    var self = this;
                    self.load();
                },
                handleEditInfo: function () {
                    var self = this;
                    self.loading = true;
                    var questData = {
                        nickname: self.userInfo.nickname,
                        mobile: self.userInfo.mobile,
                        mail: self.userInfo.mail,
                        tags: (self.userInfo.tags.length > 0) ? self.userInfo.tags.join(',') : "",
                        editPassword: false
                    };
                    ajax.post('/user/edit/' + self.userInfo.id, questData, function(data, status){
                        if (status == 200){
                            self.userInfo = data;
                            self.user.nickname = data.nickname;
                            self.user.mobile = data.mobile;
                            self.user.mail = data.mail;
                            self.user.tags = data.tags;
                        }
                        self.loading = false;
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                },
                handleClearPassword: function () {
                    var self = this;
                    self.password.oldPassword = "";
                    self.password.newPassword = "";
                    self.password.repeat = "";
                },
                handleEditPassword: function () {
                    var self = this;
                    self.loading = true;
                    var questData = {
                        nickname: self.user.nickname,
                        mobile: self.user.mobile,
                        mail: self.user.mail,
                        tags: (self.user.tags.length > 0) ? self.user.tags.join(',') : "",
                        editPassword: true,
                        oldPassword: self.password.oldPassword,
                        newPassword: self.password.newPassword,
                        repeat: self.password.repeat
                    };
                    ajax.post('/user/edit/' + self.user.id, questData, function(data, status, message){
                        if (status == 200){
                            self.$message({
                                message: "修改成功",
                                type: 'success'
                            });
                            self.handleClearPassword();
                        }else{
                            self.$message({
                                message: message,
                                type: 'warning'
                            });
                        }
                        self.loading = false;
                    }, null, ajax.NO_ERROR_CATCH_CODE);
                }
            },
            created: function () {
                var self = this;
                self.load();
            }
        });

    };

    var destroy = function () {

    };

    var groupList = {
        path: '/' + pageId,
        component: {
            template: '<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init: init,
        destroy: destroy
    };

    return groupList;

});