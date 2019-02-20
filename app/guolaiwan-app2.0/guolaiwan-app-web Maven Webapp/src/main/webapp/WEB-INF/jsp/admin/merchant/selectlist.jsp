<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">
</head>
<body>
	<div class="x-body">
		<div class="layui-inline">
  			商家：
  			<div class="layui-inline">
    			<input class="layui-input" name="mName" id="mName" autocomplete="off">
  			</div>
  			<button class="layui-btn" data-type="reload" onclick="select(this)" >搜索</button>
	    </div>	
			
			
			</xblock>
	<table class="layui-table" id="merchantList" lay-filter="merchantList"></table>
		

	
<script id="shopNameTpl" type="text/html">      
  <a href="productList?merchant={{ d.uuid }}">{{ d.shopName }}</a>
 <!--  <span class="noticed">{{ d.productCount }}</span> -->
</script>
<script type="text/html" id="shenheTpl">                                      
  {{#  if(d.shopAuditState === '草稿'||d.shopAuditState === '未通过'){ }}
    <span id="introduce" style="color: #F581B1;">{{ d.shopAuditState }}</span>
    {{#  }else if(d.shopAuditState === '待审核'){ }}
    <span id="introduce" style="color: #01AAED;">{{ d.shopAuditState }}</span>
  {{#  } else { }}
    {{ d.shopAuditState }}
  {{#  } }}
</script>
<script type="text/html" id="bankuaiTpl">
    {{ d.modularName }}&nbsp{{ d.modularClass }}               
</script>
<script type="text/html" id="zsgc">  
		<a title="选择" href="javascript:;" onclick="sel('{{ d.id }}','{{ d.shopName }}','{{ d.modularName }}','{{ d.modularCode }}','{{ d.modularClass }}','{{ d.modularClassId }}')" 
                           	style="text-decoration:none">
                            	<i>选择</i>
                        	</a>
</script> 
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           
            layui.use(['element','laypage', 'layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              //以上模块根据需要引入
              
               
              getMerchantList();

			  
			  //检索table表重新加载
			  active = {
        		reload: function(){
            		var mName = $('#mName');
            		table.reload('merList', {
                		page: {
          					curr: 1 //重新从第 1 页开始
        				}
                		,where: {
                    		mName: mName.val(),
                			}
            		});
            		layer.closeAll("loading");
        	  	}
    		  };
			  
			  
              //工具
               //分类删除
              table.on('tool(merchantList)',function(obj){                                  
                  var data = obj.data;
                  if(obj.event === 'detail'){
                      layer.msg('ID：'+ data.id + ' 的查看操作');
                  } else if(obj.event === 'del'){
                      layer.confirm('真的删除"'+obj.data.shopName+'"么', function(index){
                        layer.close(index);
                        layer.load();
                        console.log("msg");
                        $.ajax({
                          type:"post",
                          url:"del.do",
                          data:{"uuid":obj.data.uuid},
                          success:function(msg){
                            layer.closeAll("loading");
                            if(msg=="success"){
                              layer.msg("删除成功！");
                              obj.del();
                            }
                          }
                        })
                      });
                  } else if(obj.event === 'edit'){
                      layer.alert('编辑行：<br>'+ JSON.stringify(data))
                  } 
                }) 
              });
	         
	         
	         
			
            //批量删除提交
            function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function merchant_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function merchant_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            
            /*删除*/
            function merchant_del(obj,id,name){
                layer.confirm('确认要删除吗？',function(index){
                  layer.close(index);
                  layer.load();
                  //发异步删除数据
                  $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"uuid":id},
                    success:function(msg){
                  	 if(msg=="success"){
                        layer.closeAll("loading");
                  		  $(obj).parents("tr").remove();
                  		  layer.msg('已删除!',{icon:1,time:1000});
                  	 }
                    }
                  }) 
                });
            }
            
            //产品详情
		   function merchant_info(title,url,id,w,h) {
		   	x_admin_show(title,url+"?uuid="+id,w,h); 
		   }

       //获取商家列表
       function getMerchantList(){
        layer.load(2);
        table.render({                  /*3.引入table*/
         elem:'#merchantList'
         ,method:'post'
         ,url:'list.do'
         ,page:true
         ,limits: [10,30,50,100]
         ,limit: 10
         ,height:'full'
         ,id:'merList'
         ,cols: [[
          {type: 'checkbox'}
          ,{field: 'id', title: 'ID',sort: true,width:60} 
          ,{field: 'shopName', title: '商家名称',sort: true}  
          ,{field: 'shopAddress', title: '商户地址',sort: true} 
          ,{field: 'shopLinkperson', title: '联系人',width:80,sort: true} 
          ,{fixed: 'right',width:120,minWidth:100,templet:'#zsgc',unresize:true}
         ]]
         ,done:function(res, curr, count){
         	layer.closeAll("loading");
         }
        });
       }
		
		//检索
		function select(obj){
			layer.load(2);
			var type = $(obj).data('type');
    		active[type] ? active[type].call(this) : '';
		}
      
      var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
		function sel(mcid, mcname, mname, mcode, cname, ccode) {
			parent.$("#${mcuuid}").val(mcid);
			parent.$("#${mcname}").val(mcname);
			parent.$("input[name='modularName']").val(mname);
			parent.$("input[name='modularCode']").val(mcode);
			
			  if(mcode=='2021'){
		         parent.$(".proLine").show();
		      }else{
		         parent.$(".proLine").hide();
		      }
			
			parent.$("input[name='modularClass']").val(cname);
			parent.$("input[name='modularClassId']").val(ccode);
			parent.$("input[name='merMName']").val("");
			parent.$("input[name='merMId']").val("");
			parent.$("#${mchref}").attr("href", "javascript:openMap('选择分类','" + path + "/admin/merModular/comSel?merchant=" + mcid + "','600','400')");
			parent.layer.close();
		}
      
            </script>
</body>
</html>