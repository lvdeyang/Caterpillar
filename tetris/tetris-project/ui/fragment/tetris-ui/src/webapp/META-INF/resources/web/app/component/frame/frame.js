/**
 * Created by lvdeyang on 2018/11/16 0016.
 */
define([
    'text!' + window.APPPATH + 'component/frame/frame.html',
    'restfull',
    'context',
    'menu',
    'vue',
    'element-ui',
    'mi-sub-title',
    'css!' + window.APPPATH + 'component/frame/frame.css'
], function(tpl, ajax, context, menuUtil, Vue){

    var pluginName = 'mi-frame';

    /**
     * 动态参数说明：
     *  1.menus:菜单项
     *  {
     *      uuid:菜单唯一标识
     *      title:菜单名称
     *      link:菜单链接（绝对路径或者hash）
     *      icon:菜单图标
     *      style:菜单图标微调
     *      active:true/false 是否是当前选中的菜单
     *      sub:[]子菜单，插件最多支持三级菜单
     *  }
     */
    Vue.component(pluginName, {
        props:['menus', 'user', 'groups', 'active-id'],
        template: tpl,
        data:function(){
            return {
                logo:{

                    logoUrl:'',
                    logoStyle:'',
                    logoShortName:'',
                    platformFullName:'',
                    platformShortName:''

                    /*img:window.BASEPATH + 'web/app/icons/logo/bhlogo.jpg',
                    /!*collapsed0:'suma',
                    title:'新媒体应急广播CMS系统',
                    collapsed1:'mims'*!/
                    collapsed0:'BH',
                    title:'新媒体应急广播CMS系统',
                    collapsed1:'CMS'*/
                },
                isCollapsed:false,
                numberOfMessage:0,
                userGroupShow:false,
                active:'0',
                footer:{
                    company:{
                        //name:'数码视讯科技股份有限公司',
                        //link:'http://www.sumavision.com/',
                        name:'北京市博汇科技股份有限公司',
                        link:'http://www.bohui.com.cn/',
                        time:'2018-2060'
                    },
                    minimize:[
                        /*{
                            key:'2',
                            type:'multiple',
                            icon:'el-icon-message',
                            style:'font-size:36px; position:relative; top:5px; left:1px;',
                            click:'message-show',
                            summary:[{}, {}, {}]
                        },{
                            key:'1',
                            type:'single',
                            icon:'icon-tasks',
                            click:'task-show',
                            style:'position:relative; top:3px; font-size:30px;'
                        }*/
                    ]
                }
            }
        },
        methods:{
            sideMenuToggle:function(){
                if(this.isCollapsed){
                    this.isCollapsed = false;
                }else{
                    this.isCollapsed = true;
                }
            },
            userGroupToggle:function(){
                if(this.userGroupShow){
                    this.userGroupShow = false;
                }else{
                    this.userGroupShow = true;
                }
            },
            goto:function(menu){
                var self = this;
                var app = context.getProp('app');
                app.loading = true;
                app.$nextTick(function(){
                    var menus = self.menus;
                    if(menu.link){
                        if(menuUtil.isHash(menu.link)){
                            window.location.hash = menu.link;
                        }else{
                            window.location.href = menu.link;
                        }
                    }else{
                        //不跳转只做样式变换
                        self.active = menu.uuid;
                    }
                    app.loading = false;
                });
            },
            addMinimize:function(metadata){
                var self = this;
                var minimize = self.footer.minimize;
                var exist = false;
                for(var i=0; i<minimize.length; i++){
                    if(minimize[i].key === metadata.key){
                        exist = true;
                        break;
                    }
                }
                if(!exist) self.footer.minimize.splice(0, 0, metadata);
            },
            removeMinimize:function(metadata){
                var self = this;
                var minimize = self.footer.minimize;
                for(var i=0; i<minimize.length; i++){
                    if(minimize[i].key === metadata.key){
                        minimize.splice(i, 1);
                        return;
                    }
                }
            },
            minimizeClick:function(item){
                var self = this;
                var minimize = self.footer.minimize;
                var done = function(){
                    for(var i=0; i<minimize.length; i++){
                        if(minimize[i] === item){
                            minimize.splice(i, 1);
                        }
                    }
                }
                if(item.type === 'single'){
                    self.$emit(item.click, item, done);
                }else if(item.type === 'multiple'){

                }
            },
            menuSelect:function(index, indexPath){
                var self = this;
                if(index === '2-1'){
                    //个人中心
                    var token = window.TOKEN;
                    window.location.href = '/user/index/personal/' + token;
                }else if(index === '2-2'){
                    //注销登录
                    self.logout();
                }
            },
            logout:function(){
                var self = this;
                var h = self.$createElement;
                self.$msgbox({
                    title:'提示',
                    message:h('div', null, [
                        h('div', {class:'el-message-box__status el-icon-warning'}, null),
                        h('div', {class:'el-message-box__message'}, [
                            h('p', null, ['是否要退出登录?'])
                        ])
                    ]),
                    type:'wraning',
                    showCancelButton: true,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    beforeClose:function(action, instance, done){
                        instance.confirmButtonLoading = true;
                        if(action === 'confirm'){
                            ajax.post('/do/logout', null, function(data, status){
                                instance.confirmButtonLoading = false;
                                if(status !== 200) return;
                                done();
                                window.location.href = '/web/app/login/login.html';
                            }, null, ajax.NO_ERROR_CATCH_CODE);
                        }else{
                            instance.confirmButtonLoading = false;
                            done();
                        }
                    }
                }).catch(function(){});
            }
        },
        created:function(){
            var self = this;
            var menus = self.menus;
            var activeId = self.activeId;
            //设置当前菜单
            prepareMenu(menus, activeId);
            //初始化菜单的选中状态
            var activeUuid = menuUtil.getActiveUuid(menus);
            if(activeUuid){
                self.active = activeUuid;
            }
            //初始化组织机构
            var groups = self.groups;
            var numbersOfTotalMessage = setGroupInfo(groups);
            if(numbersOfTotalMessage > 0) self.numberOfMessage = numbersOfTotalMessage;

            var user = self.user;
            self.logo.logoUrl = user.logo;
            self.logo.logoStyle = user.logoStyle;
            self.logo.logoShortName = user.logoShortName;
            self.logo.platformFullName = user.platformFullName;
            self.logo.platformShortName = user.platformShortName;
        }
    });

    //设置当前菜单
    var prepareMenu = function(menus, activeId){
        if(menus && menus.length>0){
            for(var i=0; i<menus.length; i++){
                var menu = menus[i];
                if(menu.link && menuUtil.isHash(menu.link)){
                    if((activeId || menuUtil.parsePageId(window.location.hash)) === menuUtil.parsePageId(menu.link)){
                        menu.active = true;
                    }else{
                        menu.active = false;
                    }
                }else if(menu.link){
                    if(menu.link.indexOf('#/') >= 0){
                        if(activeId){
                            if(menuUtil.parsePageId(activeId) === menuUtil.parsePageId(menu.link)){
                                menu.active = true;
                            }else{
                                menu.active = false;
                            }
                        }else{
                            if(menuUtil.parsePageId(window.location.href) === menuUtil.parsePageId(menu.link)){
                                menu.active = true;
                            }else{
                                menu.active = false;
                            }
                        }
                    }else{
                        if(menu.link === (activeId || window.location.href)){
                            menu.active = true;
                        }else{
                            menu.active = false;
                        }
                    }
                }
                if(menu.sub && menu.sub.length>0){
                    prepareMenu(menu.sub, activeId);
                }
            }
        }
    };

    //统计组织机构信息
    var setGroupInfo = function(groups){
        var numbersOfTotalMessage = 0;
        if(groups && groups.length>0){
            for(var i=0; i<groups.length; i++){
                var group = groups[i];
                group.numbersOfOnline = 0;
                group.numbersObTotal = 0;
                if(group.users && group.users.length){
                    for(var j=0; j<group.users.length; j++){
                        group.numbersObTotal += 1;
                        var user = group.users[j];
                        if(user.status !== '离线'){
                            group.numbersOfOnline += 1;
                        }
                        if(!isNaN(user.numbersOfMessage)){
                            numbersOfTotalMessage += user.numbersOfMessage;
                        }
                    }
                }
            }
        }
        return numbersOfTotalMessage;
    };

});