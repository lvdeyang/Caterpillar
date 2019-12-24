/**
 * Created by ldy on 2019/3/2 0024.
 */
define([
    'text!' + window.APPPATH + 'set/page-hr-attend.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'set/page-hr-attend.css'
], function(tpl, config, $, ajax, context, commons, Vue){

    var pageId = 'page-hr-attend';

    var init = function(p){
    	

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
                table:{
                    data:[],
                    page:{
                        currentPage:1,
                        sizes:[50, 100, 200, 200],
                        size:20,
                        total:0
                    }
                }
            },
            computed:{

            },
            watch:{

            },
            methods:{
                handleSizeChange:function(val) {
                    var self = this;
                    self.table.page.size = parseInt(val);
                    //刷新数据
                    self.loadattend();
                },
                loadattend:function(){
                    var self = this;
                    self.table.data.splice(0, self.table.data.length);
                    ajax.post('/hr/attend/list', {
                        currentPage:self.table.page.currentPage,
                        pageSize:self.table.page.size,
                        workerNo:p.workerNo
                    }, function(data){
                        var rows = data.rows;
                        var total = data.total;
                        if(rows && rows.length>0){
                            for(var i=0; i<rows.length; i++){
                                self.table.data.push(rows[i]);
                            }
                        }
                        self.table.page.total = total;
                    });
                }
            },
            created:function(){
                var self = this;
            },
            mounted:function(){
                var self = this;
                self.loadattend(1);
            }
        });

    };

    var destroy = function(){

    };

    var groupList = {
        path:'/' + pageId+"/:workerNo",
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return groupList;

});