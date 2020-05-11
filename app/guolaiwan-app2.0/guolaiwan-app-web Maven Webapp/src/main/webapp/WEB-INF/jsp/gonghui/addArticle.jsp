<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
    <link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>


   <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
   <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
   <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
</head>

<body class="layui-layout-body">
<div class="x-body">
    <form class="layui-form" action="">
	  <div class="layui-form-item">
	    <label class="layui-form-label">标题</label>
	    <div class="layui-input-block">
	      <input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">作者</label>
	    <div class="layui-input-block">
	      <input type="text" name="autor" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
			<label for="L_title" class="layui-form-label"> 海报 </label>
			<div class="layui-input-inline" style="width: 100px">
			    <img alt="" src="" id="articlepic" style=" height:100px;width:100px ">
				<input type="hidden" id="pic" name="pic" 
					lay-verify="title" autocomplete="off"   class="layui-input">
			</div>
			<div class="layui-input-inline">
				<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=pic&img=articlepic','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
				<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#articlepic','#pic')">删除图片</button>					
			</div>
	  </div>
	  
	  <div class="layui-form-item">
             <label for="content" class="layui-form-label">
                                                     产品介绍：
             </label>
             <div class="layui-input-block">
                 <textarea name="content"  placeholder="请输入内容" class="layui-input" id="content" style="height:400px"></textarea>
          </div>
      </div>
	  
      <div class="layui-form-item" style="margin-left:50px;">
           <button class="layui-btn" lay-filter="add" lay-submit>
                                                              保存
           </button>
      </div>
</form>
</div>
	<script>
		//JavaScript代码区域
		var um = UM.getEditor('content');
		layui.use(['form','layer'],function(){
		   var $ = layui.jquery;
		   var form = layui.form
              ,layer = layui.layer;

              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
          
                $.ajax({
                	  type:"post",
           			  url:"article/add.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }
                        //提示
                        else{
                        
                        }
                       }
                }) 
              
                return false;
            });
 
          
			
		});
		
		
		function openMap (title,url,w,h) {
           x_admin_show(title,url,w,h); 
        }
	</script>
</body>
</html>
