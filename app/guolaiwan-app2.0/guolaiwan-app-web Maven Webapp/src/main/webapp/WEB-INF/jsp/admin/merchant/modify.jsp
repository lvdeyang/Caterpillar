<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
	<div class="x-body" style="height: 1800px;">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="uuid" value="${list.uuid}">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopName" 
						lay-verify="title" value="${list.shopName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginName" id="shopLoginName"
						lay-verify="title" value="${list.shopLoginName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录密码 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginPwd" 
						lay-verify="title" value="${list.shopLoginPwd}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 头像图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${sysConfig.webUrl}${list.shopHeading}" id="shopHeadingPic" style=" height:100px;width:100px ">
					<input type="hidden" name="shopHeading" id="shopHeading"
						value="${list.shopHeading}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline">
				
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopHeading&img=shopHeadingPic','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 企业资质图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
				<img alt="" src="${sysConfig.webUrl}${list.shopQualifications}" id="shopQualificationsPic" style="height: 100px; width: 100px"/>
					<input type="hidden" type="hidden" name="shopQualifications" id="shopQualifications"
						lay-verify="title" value="${list.shopQualifications}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopQualifications&img=shopQualificationsPic','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> QQ </label>
				<div class="layui-input-block">
					<input type="text" name="shopQQ" 
						lay-verify="title" value="${list.shopQQ}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系电话 </label>
				<div class="layui-input-block">
					<input type="text" name="shopTel" id="shopTel"
						lay-verify="title" value="${list.shopTel}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户地址 </label>
				<div class="layui-input-block">
					<input type="text" name="shopAddress" 
						lay-verify="title" value="${list.shopAddress}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 银行卡号 </label>
				<div class="layui-input-block">
					<input type="text" name="shopBankId" 
						lay-verify="title" value="${list.shopBankId}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 开户行 </label>
				<div class="layui-input-block">
					<input type="text" name="shopOpenBank" 
						lay-verify="title" value="${list.shopOpenBank}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系人 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLinkperson" 
						lay-verify="title" value="${list.shopLinkperson}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${sysConfig.webUrl}${list.shopPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" value="${list.shopPic}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline">
				
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 轮播图 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td >
						<img alt="" src="${sysConfig.webUrl}${img1}" style=" height:100px;width:100px " id="imgurl1" name="imgurl" >
						<input type="hidden" id="img1" name="img" value='${img1}'>
						<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img1&img=imgurl1','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl1','#img1')">删除图片</button>
					</td>		
					<td>
						<img alt="" src="${sysConfig.webUrl}${img2}" style=" height:100px;width:100px " id="imgurl2" name="imgurl">
						<input type="hidden" id="img2" name="img" value='${img2}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img2&img=imgurl2','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl2','#img2')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img3}" style=" height:100px;width:100px " id="imgurl3" name="imgurl">
						<input type="hidden" id="img3" name="img"" value='${img3}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img3&img=imgurl3','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl3','#img3')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img4}" style=" height:100px;width:100px " id="imgurl4" name="imgurl">
						<input type="hidden" id="img4" name="img" value='${img4}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img4&img=imgurl4','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl4','#img4')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img5}" style=" height:100px;width:100px " id="imgurl5" name="imgurl">
						<input type="hidden" id="img5" name="img" value='${img5}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl5','#img5')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img6}" style=" height:100px;width:100px " id="imgurl6" name="imgurl">
						<input type="hidden" id="img6" name="img" value='${img6}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img6&img=imgurl6','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl6','#img6')">删除图片</button>
					</td>
					</tr>
				</table>
					<input type="hidden" name="shopMpic" 
						 value="${list.shopMpic}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户简介 </label>
				<div class="layui-input-block">
					<textarea  id="shopIntroduction" name="shopIntroduction" style="height: 300px"
						 autocomplete="off" class="layui-input" >
						${list.shopIntroduction}</textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 坐标 </label>
				<div class="layui-input-inline">
					<input type="text" id="shopLongitude" name="shopLongitude" 
						lay-verify="title" value="${list.shopLongitude}" autocomplete="off"
						class="layui-input">
				</div>

				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline">
					<input type="text" id="shopLatitude" name="shopLatitude" 
						lay-verify="title" value="${list.shopLatitude}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline" style="width: 100px;">
				  <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a>
				 
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">板块名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularName" value="${list.modularName}"
						lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden"  name="modularCode" value="${list.modularCode}"
						 autocomplete="off" class="layui-input" >
						
				</div>
				<label class="layui-form-label">分类名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularClass" value="${list.modularClass}" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden" name="modularClassId" value="${list.modularClassId}"
						autocomplete="off" class="layui-input" >
				</div>
				
				 <div class="layui-input-inline" style="width: 200px;">
				  <a href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/modular/comSel','600','400')" class="layui-btn" >选择分类</a>
				  <a href="javascript:resetMo(0)" class="layui-btn" >清除分类</a>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">板块名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularName1" value="${list.modularName1}"
						lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden"  name="modularCode1" value="${list.modularCode1}"
						 autocomplete="off" class="layui-input" >
						
				</div>
				<label class="layui-form-label">分类名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularClass1" value="${list.modularClass1}" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden" name="modularClassId1" value="${list.modularClassId1}"
						autocomplete="off" class="layui-input" >
				</div>
				
				 <div class="layui-input-inline" style="width: 200px;">
				  <a href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/modular/comSel?index=1','600','400')" class="layui-btn" >选择分类</a>
				  <a href="javascript:resetMo(1)" class="layui-btn" >清除分类</a>
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">板块名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularName1" value="${list.modularName2}"
						lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden"  name="modularCode2" value="${list.modularCode2}"
						 autocomplete="off" class="layui-input" >
						
				</div>
				<label class="layui-form-label">分类名称</label>
				<div class="layui-input-inline">
					<input type="text" name="modularClass2" value="${list.modularClass2}" autocomplete="off" class="layui-input" readonly="readonly">
						<input type="hidden" name="modularClassId2" value="${list.modularClassId2}"
						autocomplete="off" class="layui-input" >
				</div>
				
				 <div class="layui-input-inline" style="width: 200px;">
				  <a href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/modular/comSel?index=2','600','400')" class="layui-btn" >选择分类</a>
				   <a href="javascript:resetMo(2)" class="layui-btn" >清除分类</a>
				</div>
			</div>
			
			
			<div class="layui-form-item">
			<label class="layui-form-label">商户业务：</label>
				<div class="layui-input-inline">
					<select name="business" id="business" required>
						<option value=""></option>
						<option value="BASIC">基本业务</option>
						<option value="CONVOYS">车队</option>
						<option value="RESTAURANT">饭店</option>
						<option value="SCENICSPOT">景点</option>
						
					</select>
				</div>
				</div>
				
			<div class="layui-form-item">
				<label class="layui-form-label">到店支付：</label>
				<div class="layui-input-inline">
					<select name="shopyd" id="shopyd" required>
						<option value=""></option>
						<option value="1">验单</option>
						<option value="0">不验单</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add1" lay-submit >
						提交审核
                    </button>
                    <button class="layui-btn layui-btn-danger" lay-filter="add2" lay-submit >
						存为草稿
                    </button>
                </div>
			</div>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
	<script>
			//实例化编辑器
		    var um = UM.getEditor('shopIntroduction');
    
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
                $("#business").val("${list.business}");
                $('#shopyd').val(${list.shopyd});
                layui.form.render('select');
              var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(add1)', function(data){
          	
              	data.field.shopAuditstates = "D";
              	var message = "提交待审核成功！";  
              	add(data,message);
             	return false;
              });
              //监听提交
              form.on('submit(add2)', function(data){
              
               	data.field.shopAuditstates = "C";
               	var message = "保存草稿成功！";  
               	add(data,message);
               	return false;
              });
              });
              
              
              //监听提交
              function add(data,message){
              	//联系人手机号用于创建一个用户
                //var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				//if(!myreg.test($("#shopLoginName").val())){ 
   				//	layer.msg('登录名为有效手机号码！',{icon:2}); 
    			//	return false; 
				//} 
				//多图
           		var proMpic="";
           		var strPic;
           		for(var i=1;i<7;i++){
           			var str = "#img"+i;
           			if($(str).val()!=''){
           				if(proMpic==''){
           					strPic = $(str).val();
           				}else{
           					strPic = $(str).val()+",";
           				}
           				proMpic = strPic+proMpic;
           				console.log()
           			}
           		}
           		data.field.shopMpic= proMpic;
				
              	 console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"update.do",
                      data:data.field,
                      success:function(msg){
                      console.log(msg);
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
                           });
                        }
                       }
                }) 
              
              }
              	
				
				
				
				
				 
             
                
             function resetMo(index){
               if(index==0){
                   $("input[name='modularName']").val('');
	               $("input[name='modularCode']").val('');
	               $("input[name='modularClassId']").val('');
	               $("input[name='modularClass']").val('');
               }else{
                   $("input[name='modularName"+index+"']").val('');
	               $("input[name='modularCode"+index+"']").val('');
	               $("input[name='modularClassId"+index+"']").val('');
	               $("input[name='modularClass"+index+"']").val('');
               }
               
            }
             
             
           //打开地图
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }  
            
            //打开分类
            function openClass (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
            
           //删除图片 
           function delpic(img,inp){
           		$(img).removeAttr("src");
           		$(inp).val("");           	
           }
            
             
        </script>

</body>

</html>
