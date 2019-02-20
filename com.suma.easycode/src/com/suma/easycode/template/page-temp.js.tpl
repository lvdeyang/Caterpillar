define([
    'text!' + window.APPPATH + '${pageName}/page-${pageName}.html',
    'restfull',
    'config',
    'commons',
    'vue',
    'element-ui',
    'bvc2-header',
    'bvc2-system-nav-side',
    'bvc2-system-table-base'
], function(tpl, ajax, config, commons, Vue){

    var pageId = 'page-${pageName}';

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        ajax.get('/${pageName}/query', null, function(data){
            //转换数据格式
            var typeOptions = [];
            var typeArr = data.dicTypes;
            if(typeArr && typeArr.length>0){
                for(var i=0; i<typeArr.length; i++){
                	typeOptions.push({
                        label:typeArr[i],
                        value:typeArr[i]
                    });
                }
            }
            
            var $page = document.getElementById(pageId);
            $page.innerHTML = tpl;

            var v_tpl = new Vue({
                el:'#' + pageId + '-wrapper',
                data:{
                    header:commons.getHeader(1),
                    side:{
                        active:'0-9'
                    },
                    ${pageName}:{
                        buttonCreate:'新建',
                        buttonRemove:'删除',
                        columns:[
                        <#list properList as propers>
					       {
                            label:'${propers.proLabelName}',
                            prop:'${propers.proName}',
                            type:'simple'
                           },
								
						</#list>
                        ],
                        load:'${pageName}/load',
                        save:'${pageName}/save',
                        update:'${pageName}/update',
                        remove:'${pageName}/remove',
                        removebatch:'${pageName}/remove/all',
                        pk:'id'
                    }
                },
                methods:{

                }
            });

        });
    };

    var destroy = function(){

    };

    var tempList = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return tempList;
});