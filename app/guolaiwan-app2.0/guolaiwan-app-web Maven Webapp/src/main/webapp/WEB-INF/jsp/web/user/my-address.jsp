<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
 <head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<meta name="renderer" content="webkit">
    <meta content="歪秀购物, 购物, 大家电, 手机" name="keywords">
    <meta content="歪秀购物，购物商城。" name="description">
	<title>会员中心</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/member.css">
	<script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
     <script>
         $(function(){

             $("#H-table li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over").hide();
                         $(".H-over:eq(" + _index + ")").show();
                     }
                 })(i));
             });
             $("#H-table1 li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over1").hide();
                         $(".H-over1:eq(" + _index + ")").show();
                     }
                 })(i));
             });
         });
         
         //设置默认地址
             function setd(obj,uuid,userId,info){
             	$.ajax({
             		type:"post",
             		url:basePath+"user/address/setd.do",
             		data:{"uuid":uuid,"userId":userId},
             		success:function(msg){
             			console.log(msg);
             			if(msg=="success"){
             				alert("设置默认地址成功！\n默认地址为："+info)
             				$(".setde").removeAttr("hidden");
             				$(".not").prop("hidden","hidden");
             				$(obj).prop("hidden","hidden");
             				$(obj).siblings(".not").removeAttr("hidden");
             			}
             		}
             		
             	}) 
             	
             }
     </script>

 </head>
 <body>
<div class="box">
		<div class="hint">
			<div class="hint-in1">
				<div class="hint2">添加收货地址</div>
				<div class="hint3"></div>
			</div>


			<div class="pc-label">
				<label><i class="reds ">*</i>收货人:</label><input type="text"
					id="addConName" placeholder="请您填写收货人姓名">
			</div>
			<div id="sjld"
				style="margin-top:10px; padding-left:40px; position:relative;"
				class="clearfix">

				<div class="clearfix" style="padding-bottom:5px;">
					<i class="reds fl">*</i>
					<p class="fl">所在地区:</p>
				</div>

				<!-- distpicker -->
				<div data-toggle="distpicker" class="pc-select">
					<select id="province"></select> <select id="city"></select> <select id="district"></select>
				</div>
				<input id="sfdq_num" type="hidden" value="" /> <input id="csdq_num"
					type="hidden" value="" /> <input id="sfdq_tj" type="hidden"
					value="" /> <input id="csdq_tj" type="hidden" value="" /> <input
					id="qydq_tj" type="hidden" value="" />
			</div>

			<div class="pc-label">
				<label><i class="reds ">*</i>详细地址:</label><input type="text"
					id="addConAddress" style="width:400px;" placeholder="请您填写收货人详细地址">
			</div>
			<div class="pc-label">
				<label><i class="reds ">*</i>手机号码:</label><input type="text"
					id="addConPhone" style="width:400px;" placeholder="请您填写收货人手机号码">
			</div>
			<div class="pc-label">
				<label>邮箱:</label><input type="text" id="addConEmail"
					style="width:400px;" placeholder="请您填写邮箱地址">
			</div>
			<input type="hidden" id="auuid">
			<a href="javascript:saveOrUpdate();" class="hint-in3" >保存收货地址</a>
		</div>

	</div>

<!--- header begin-->
<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"><a href="#">首页</a> &gt; <a href="#">会员中心 </a> &gt; <a href="#">地址管理</a></div></div>

<!-- 商城快讯 begin -->
<section id="member">
    <div class="member-center clearfix">
<!-- 左侧菜单 b -->
        	<c:import url="${path}/user/memberLeft"></c:import>
<!-- 左侧菜单 e --> 
        <div class="member-right fr">
            <div class="member-head">
                <div class="member-heels fl"><h2>地址管理</h2></div>
            </div>
            
            <div class="member-border">
                <div class="member-newly"><a class="fr pc-order-add btn1" href="#"><b>新增收货地址</b></a></div>
                <div class="member-sites">
                    <ul>
                    
                      <c:forEach items="${addresses}" var="address">
                        <li class="clearfix">
                        	<input type="hidden" value="${address.id}" name="addressId" >
                            <div class="user-info1 fl clearfix">
                                <div class="user-info">
                                    <span class="info1">收货人：</span>
                                    <span class="info2">${address.consigneeName}</span>
                                </div>
                                <div class="user-info">
                                    <span class="info1">所在地区：</span>
                                    <span class="info2">${address.province}${address.city}${address.district}</span>
                                </div>
                                <div class="user-info">
                                    <span class="info1">地址：</span>
                                    <span class="info2">${address.consigneeAddress}</span>
                                </div>
                                <div class="user-info">
                                    <span class="info1">手机：</span>
                                    <span class="info2">${address.consigneePhone}</span>
                                </div>
                               
                                <div class="user-info">
                                    <span class="info1">电子邮箱：</span>
                                    <span class="info2">${address.consigneeEmail}</span>
                                </div>
                            </div>

                            <div class="pc-event">
                            	<c:choose>
                            		<c:when test="${address.defaultAddress == 1}">
                            			<a class="not" href="#">默认地址</a><a hidden="hidden" href="#" class="setde" onclick=setd(this,'${address.uuid}','${address.userId}','${address.province}${address.city}${address.district}${address.consigneeAddress}')>设为默认地址</a>
                            		</c:when>
                            		<c:otherwise>
                            		 	<a class="not" hidden="hidden" href="#">默认地址</a><a href="#" class="setde" onclick=setd(this,'${address.uuid}','${address.userId}','${address.province}${address.city}${address.district}${address.consigneeAddress}')>设为默认地址</a>
                            		</c:otherwise>
                            	</c:choose>
                               
                                <a class="btn1" href="javascript:edit(this,'${address.uuid}')">编辑 </a>
                                <a href="javascript:delAdd(this,'${address.uuid}')">删除</a>
                            </div>
                        </li>
					  </c:forEach>
                        
                    </ul>
                </div>
                
            </div>
        </div>
    </div>
</section>
<!-- 商城快讯 End -->

<!--- footer begin-->
<c:import url="${path}/web/foot"></c:import>
<!-- footer End -->
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/distpicker.min.js"></script>
<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
var basePath = '<%=basePath %>';
$(document).ready(function($){

             $(".btn1").click(function(event){
                 $(".hint").css({"display":"block"});
                 $(".box").css({"display":"block"});
             });

			$(".hint3").click(function(event) {
                 $(this).parent().parent().css({"display":"none"});
                 $(".box").css({"display":"none"});
             });
			

             /* $(".hint-in3").click(function(event) {
            	//address对象、
            	var address = new Object();
            	address.addConName = $("#addConName").val();
            	address.addConAddress = $("#addConAddress").val();
            	address.addConPhone = $("#addConPhone").val();
            	address.addConEmail = $("#addConEmail").val();
            	address.userId = ${user.id};
             	var daddConarea ="";
             	 $(".pc-select select").each(function(index,item){
             	 		daddConarea+=$(this).val();
             	 		
             	 });
             	 address.addConarea = daddConarea;
             	 console.log(address);
             	 $.ajax({
                	  type:"post",
           			  url:basePath+"user/address/add.do",
                      data:address,
                      success:function(msg){
                      	window.location.reload();
                      }
                })
                 
                 $(".hint").css({"display":"none"});
                 $(".box").css({"display":"none"});
                 
             }); */
});

/* function deleteAddress(addressId) {
	$.ajax({
	   type: "POST",
	   url: "delete",
	   data: {"id":addressId},
	   success: function(msg){
	     window.location.reload();
	   }
	});
} */

//删除地址
function delAdd(obj,uuid){
	if (confirm("确定要删除吗？")) {
		$.ajax({
			type:"post",
			url:basePath+"user/address/del.do",
			data:{"uuid":uuid},
			success:function(msg){
				console.log(msg);
				window.location.reload();
				alert("删除成功！");
			}
		})
	}	
}
//编辑地址
function edit(obj,uuid){
	var address;
	console.log("1");
	$.ajax({
		type:"post",
		url:basePath+"user/address/getOne.do",
		data:{"uuid":uuid},
		success:function(msg){
			address = msg.address;
			console.log(address);
			$(".hint").css({"display":"block"});
   		$(".box").css({"display":"block"});
   		$("#addConName").val(address.consigneeName);
   		$("#addConAddress").val(address.consigneeAddress);  
   		$("#addConPhone").val(address.consigneePhone);
   		$("#addConEmail").val(address.consigneeEmail); 
   		$("#province").val(address.province);
   		$("#province").trigger("change")
   		$("#city").val(address.city); 
   		$("#city").trigger("change")
   		$("#district").val(address.district);
   		$("#auuid").val(address.uuid);
		}
	})
}

//保存按钮
function saveOrUpdate(){
   	//address对象
   	var address = new Object();
   	address.addConName = $("#addConName").val();
   	address.addConAddress = $("#addConAddress").val();
   	address.addConPhone = $("#addConPhone").val();
   	address.addConEmail = $("#addConEmail").val();
   	address.uuid = $("#auuid").val();//uuid是
   	address.userId = ${user.id};
    	var addConarea = new Array(3); 
    	 $(".pc-select select").each(function(index,item){
    	 		addConarea[index]=$(this).val();
    	 });
    	address.province = addConarea[0];
   		address.city = addConarea[1];
   		address.district =addConarea[2];
       console.log(address);
    	 $.ajax({
       	  type:"post",
  			  url:basePath+"user/address/saveOrUpdate.do",
             data:address,
             success:function(msg){
             	if(msg=="success"){
             		//关闭，刷新                
       			$(".hint").css({"display":"none"});
        		$(".box").css({"display":"none"});
        		window.location.reload();
        		alert("保存成功！");	
             	}else if(msg=="error"){
             		alert("保存失败！最多有5个地址！");
             		return false;
             	}else{
             		alert("服务器正忙！");
             		return false;
             	}
             }
       })

        
    }

</script>

</body>
</html>