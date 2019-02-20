<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<meta name="renderer" content="webkit">
<title>提交订单</title>

<link rel="shortcut icon" type="image/x-icon"
	href="<%=path %>/webtheme/theme/icon/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/base.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/home.css">
<script type="text/javascript"
	src="<%=path%>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/webtheme/theme/js/date.js"></script>
<!-- layui -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/lib/layui/css/layui.css" media="all">
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
//加载layui的弹出层
layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
          });
/*
pc-frams pc-frame 不要用这两个样式帮着支付
*/
var basePath = '<%=basePath%>';
var isCommited =false;
var t = '${token}';
var productPrice;
var orderType ="${type}";
if(orderType == "MERCHANT"){
  	productPrice="${product.productPrice}"
}else{
	productPrice = "${distributorProduct.sellPrice}";
}

var pcount = '${count}';
var productType = '${product.productType}';

$(function(){

  (function(a){
     a.fn.hoverClass=function(b){
         var a=this;
         a.each(function(c){
             a.eq(c).hover(function(){
                 $(this).addClass(b)
             },function(){
                 $(this).removeClass(b)
             })
         });
         return a
     };
 })(jQuery); 
		
			console.log(t);
         
         	  $("#pc-nav").hoverClass("current");
             
             
             /* //选择支付方式
             $(".fl div").click(function(){
             	$(this).siblings().removeClass("pc-frams").addClass("pc-frame");
             	$(this).addClass("pc-frams");
             }) */
             
            //选择地址 
            $(".pc-address a").click(function(){
            	$(".mailAdd").removeAttr("checked");
             	$(this).children("input.mailAdd").prop("checked","checked"); 
             	/* $(".fll div").removeClass("pc-frams").removeClass("pc-frame").addClass("pc-null"); */
             	$("#maillogo").remove();
            	$(this).parent("div").siblings(".pc-null").html("<a href='#' id='maillogo'>邮寄至：</a>");
            	/* $(this).parent("div").siblings(".pc-null").addClass("pc-frams").addClass("pc-frame") */
            })
            
            //预定时间
            var date = new Date();
            $(".Wdate").val(addDate(date, 1));
            
            //产品数量的增加和减少
            $("#cut").click(function(){
            	if(pcount==1){
            		$("#cut").attr("display","display");
            		return false;
            	}else{
            		pcount--;
            		$("#pcount").val(pcount);
            		$("#pcount2").html(pcount);
					orderAllMoney = productPrice*pcount//总价
            	 	payMoney = orderAllMoney - yun - jmoney;//支付金额
            	 	orderAllMoney = getFormatPrice(orderAllMoney)
            	 	payMoney = getFormatPrice(payMoney)
            	 	$("#orderAllMoney").html(orderAllMoney);
            	 	$(".payMoney").html(payMoney);         	
            	}
            	
            })
            $("#add").click(function(){
            	var stock = $("#stock").html();
            	if(pcount==stock){
            		$("#add").attr("display","display");
            		return false;
            	}else{
            		pcount++;
            		$("#pcount").val(pcount);
            		$("#pcount2").html(pcount);
            	 	orderAllMoney = productPrice*pcount//总价
            	 	payMoney = orderAllMoney - yun - jmoney;//支付金额
            	 	orderAllMoney = getFormatPrice(orderAllMoney)
            	 	payMoney = getFormatPrice(payMoney)
            	 	$("#orderAllMoney").html(orderAllMoney);
            	 	$(".payMoney").html(payMoney);
            	 	//总价 
            	}
            })	
         		
         	 //地址弹出层	
             $("#adbin1").click(function(event){
                 $("#adhint").css({"display":"block"});
                 $("#adbox").css({"display":"block"});
             });
             
             //关闭地址弹出层
             $("#adkill").click(function(event) {
                 $(this).parent().parent().css({"display":"none"});
                 $("#adbox").css({"display":"none"});
             });
             
             
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
         });
         
         //删除地址
         function delAdd(obj,uuid){
         	layer.confirm("是否要删除此地址！",
         		{btn:['删了吧','不删了']},
         		function(){
         			$.ajax({
         			type:"post",
         			url:basePath+"user/address/del.do",
         			data:{"uuid":uuid},
         			success:function(msg){
         				console.log(msg);
         				layer.msg('删除成功！', {icon: 1,time:1500},function(){
         					window.location.reload();
         					});
         				}
         			})
         	
         	
         	});
         	
         }
         //编辑地址
         function edit(obj,uuid){
         	var address;
         	$.ajax({
         		type:"post",
         		url:basePath+"user/address/getOne.do",
         		data:{"uuid":uuid},
         		success:function(msg){
         			address = msg.address;
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
         		//校验
         		if($("#addConName").val()==''){
         			layer.msg("姓名不能为空！",{time:1500});
         			return false;
         		} 
         		if($("#addConAddress").val().length<6||$("#addConAddress").val()==''){
         			layer.msg("详细地址长度最少6位！",{time:1500});
         			return false;
         		}
         		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test($("#addConPhone").val())){ 
   					layer.msg('请输入有效的手机号码！',{time:1500}); 
    				return false; 
				} 
         		if($("#addConEmail").val()==''){
         			layer.msg("邮箱不能为空！",{time:1500});
         			return false; 
         		}
         		if($("#province").val()==''){
         			layer.msg("省不能为空！",{time:1500});
         			return false; 
         		}
         		if($("#city").val()==''){
         			layer.msg("市不能为空！",{time:1500});
         			return false; 
         		}
         		if($("#district").val()==''){
         			layer.msg("区不能为空！",{time:1500});
         			return false; 
         		}
         		/* $("#addConPhone") 123 15 17 18
         		 */
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
                      		layer.msg("保存成功！",{icon:1,time:1500},function(){
                      			//关闭，刷新                
                				$("#hint").css({"display":"none"});
                 				$("#box").css({"display":"none"});
                 				window.location.reload();
                      		});
                      		
                      	}else if(msg=="error"){
                      		layer.msg("保存失败！最多有5个地址！",{time:1500});
                      		return false;
                      	}else{
                      		layer.msg("服务器正忙！",{time:1500});
                      		return false;
                      	}
                      }
                })
             }
             
             //设置默认地址
             function setd(obj,uuid,userId,info){
             	$.ajax({
             		type:"post",
             		url:basePath+"user/address/setd.do",
             		data:{"uuid":uuid,"userId":userId},
             		success:function(msg){
             			console.log(msg);
             			if(msg=="success"){
             				layer.msg("默认地址为："+info,{time:1500});
             				$(".setde").removeAttr("hidden");
             				$(".not").prop("hidden","hidden");
             				$(obj).prop("hidden","hidden");
             				$(obj).siblings(".not").removeAttr("hidden");
             			}
             		}
             		
             	}) 
             	
             }
             //设置价格格式
             function getFormatPrice(price){
				price  = Math.ceil(price*100)/100;
				var xsd = price.toString().split(".");
 				if(xsd.length==1){
 					price=price.toString()+".00";
					return price;
				}if(xsd.length>1){
					if(xsd[1].length<2){
						price=price.toString()+"0";
					}
					return price;
				}
			}
			
			//校验表单
			function dosubmit(){
				var mailAddressc = $('input:radio[name="mailAddress"]:checked').val();//选择地址
				var mailAddress = $('input:radio[name="mailAddress"]').val();//收货地址
				console.log(mailAddress);
				productType
				if(mailAddress==undefined&&productType==1){
					layer.msg("火星人，填个收货地址吧！",{time:1500},function(){
						$("#adhint").css({"display":"block"});
                 		$("#adbox").css({"display":"block"});
					});
                 	return false;
                 	
				}else{
					if(mailAddressc==undefined&&productType==1){
						layer.msg("客官，选个邮寄地址吧 ！T-T",{time:1500});
						return false;
					}
				}
				
				//防止提交按钮重复提交
				if(isCommited==false){
					isCommited = true;
				}else{
					
					return false;
				}
			}
		
			
     </script>



</head>
<body>
	<div class="box" id="adbox">
		<div class="hint" id="adhint">
			<div class="hint-in1">
				<div class="hint2">添加收货地址</div>
				<div class="hint3" id="adkill"></div>
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
	<header id="pc-header">
		<div class="BHeader">
			<div class="yNavIndex">
				<ul class="BHeaderl">
					<li><a href="#">订单查询</a></li>
					<li class="headerul">|</li>
					<li><a href="#">我的收藏</a></li>
					<li class="headerul">|</li>
					<li id="pc-nav" class="menu"><a href="#" class="tit">我的商城</a>
						<div class="subnav">
							<a href="#">我的山城</a> <a href="#">我的山城</a> <a href="#">我的山城</a>
						</div></li>
					<li class="headerul">|</li>
					<li><a href="#" class="M-iphone">手机悦商城</a></li>
				</ul>
			</div>
		</div>
		<div class="container clearfix">
			<div class="header-logo fl" style="width:212px;">
				<h1>
					<a href="<%=path %>/index"><img src="<%=path %>/webtheme/theme/icon/logo.png"></a>
				</h1>
			</div>
			<div class="pc-order-titlei fl">
				<h2>填写订单</h2>
			</div>
			<div class="pc-step-title fl">
				<ul>
					<li class="cur2"><a href="#">1 . 产品订购</a></li>
					<li class="cur"><a href="#">2 . 填写核对订单</a></li>
					<li><a href="#">3 . 订单支付</a></li>
				</ul>
			</div>
		</div>

	</header>
	<!-- header End -->


	<!-- 订单提交成功 begin-->
	<section>
	  <form action="createOrder.do" method="post" id="ordersub" onsubmit="return dosubmit()">
	  <input type="hidden" name="token" value="${token}">
	  <input type="hidden" name="type" value="${type}">
		<div class="containers">
		<c:choose>
		<c:when test="${product.productType==1}">
			<div class="pc-space">
				<div class="pc-order-title clearfix">
					<h3 class="fl">用户信息</h3>
					<a href="#" class="fr pc-order-add btn1" id="adbin1">新增收货地址</a>
				</div>
				<div class="pc-border">
					<div class="pc-order-text clearfix">
						<ul class=" clearfix">
							<c:if test="${not empty addressd.consigneeName}">
							<li class="clearfix fll">
								<div class="fll pc-null">
									<a href="#" id="maillogo">邮寄至：</a>
								</div>
								<div class="fll pc-address">
									<a href="#"><input name="mailAddress" type="radio" value="${addressd.uuid}" checked="checked" class="mailAdd"/>
									<span>${addressd.consigneeName} </span> <span>${addressd.consigneePhone}
									</span> <span>${addressd.province}${addressd.city}${addressd.district}${addressd.consigneeAddress}</span>
									</a>
								</div>
							</li>
							<li class="fr">
								<div class="pc-click">
									<a class="not" href="#">默认地址</a><a hidden="hidden" href="#" class="setde" onclick=setd(this,'${addressd.uuid}','${addressd.userId}','${addressd.province}${addressd.city}${addressd.district}${addressd.consigneeAddress}')>设为默认地址</a> <a href="javascript:edit(this,'${addressd.uuid}')">编辑</a> <a
										href="javascript:delAdd(this,'${addressd.uuid}')">删除</a>
								</div>
							</li>
							</c:if>
							<c:forEach items="${addressn}" var="address">
								<li class="clearfix fll">
									<div class="fll pc-null"></div>
									<div class="fll pc-address">
										<a href="#"><input name="mailAddress" type="radio" value="${address.uuid}" class="mailAdd"/>
										<span>${address.consigneeName} </span> <span>${address.consigneePhone}
										</span> <span>${address.province}${address.city}${address.district}${address.consigneeAddress}</span>
										</a>
									</div>
								</li>
								<li class="fr">
									<div class="pc-click">
										<a class="not" hidden="hidden" href="#">默认地址</a><a href="#" class="setde" onclick=setd(this,'${address.uuid}','${address.userId}','${address.province}${address.city}${address.district}${address.consigneeAddress}')>设为默认地址</a> <a href="javascript:edit(this,'${address.uuid}')">编辑</a> <a
											href="javascript:delAdd(this,'${address.uuid}')">删除</a>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
			<input type="hidden" name="puuid" value="${product.uuid}">

			<div class="pc-space">
				<div class="pc-order-title clearfix">
					<h3 class="fl">预定日期</h3>
				</div>
				<div class="pc-border">
					<div class="pc-order-text clearfix">
						<div class="pc-written">
							<p>
								预定日期：&nbsp&nbsp&nbsp&nbsp<input style="width:130px" name="orderBookDate" class="Wdate" type="text" onClick="WdatePicker()">
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="pc-space clearfix">
				<div>
					<div class="pc-order-title clearfix">
						<h3 class="fl">商品信息</h3>
					</div>
					<div class="pc-border">
						<div class="pc-order-text clearfix">
							<c:choose>
							<c:when test="${type == 'MERCHANT'}">
								<div class="pc-wares-t">
									<h4>商家： ${merchant.shopName}</h4>
								</div>
								<div class="fl pc-wares">
									<a href="#"><img width="100%" height="100%"
										src="${sysConfig.webUrl}${product.productShowPic}"></a>
								</div>
								<div class="fl pc-wares-w1">
									<span class="fl">${product.productSubtitle}</span>
								</div>
								<div class="fl pc-wares-s">
									<span class="reds">￥${product.productPrice}</span><span>
									<input type="button" style="width:20px" id="cut" value="-">
									<input type="text" style="width:40px; text-align:center" id="pcount" name="pcount" readonly="readonly" value="${count}">
									<input type="button" style="width:20px" id="add" value="+">个</span><span>有货（库存<i id="stock">${product.productStock}</i>个）</span>
								</div>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="dpuuid" value="${distributorProduct.uuid}">
								<div class="pc-wares-t">
									<h4>经销商： ${distributor.shopName}</h4>
								</div>
								<div class="fl pc-wares">
									<a href="#"><img width="100%" height="100%"
										src="${sysConfig.webUrl}${product.productShowPic}"></a>
								</div>
								<div class="fl pc-wares-w1">
									<span class="fl">${product.productSubtitle}</span>
								</div>
								<div class="fl pc-wares-s">
									<span class="reds">￥${distributorProduct.sellPrice}</span><span>
									<input type="button" style="width:20px" id="cut" value="-">
									<input type="text" style="width:40px; text-align:center" id="pcount" name="pcount" readonly="readonly" value="${count}">
									<input type="button" style="width:20px" id="add" value="+">个</span><span>有货（库存<i id="stock">${product.productStock}</i>个）</span>
								</div>
							</c:otherwise>
							</c:choose>
							
							<div class="clearfix pc-wares-p">
								
							</div>
							<div class="pc-written">
								<p>订单留言：</p>
							</div>
							<div>
								<textarea name="orderRemark" id="orderRemark" style="width:100%;height:100px" rows="" cols=""></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="clearfix">
				<div class="fr pc-list-t">
					<ul>
						<li><span><b id="pcount2">${count}</b> 件商品，总商品金额：</span> <em>￥<a id="orderAllMoney">${orderAllMoney}</a></em></li>
						<li><span>减额：</span> <em>￥<a id="jmoney">${jmoney}</a></em></li>
						<li><span>运费：</span> <em>￥<a id="yun">${yun}</a></em></li>
						<li><span>应付总额：</span> <em>￥<a class="payMoney">${payMoney}</a></em></li>
					</ul>
				</div>
			</div>
			<div class="pc-space-n"></div>
			<div class="clearfix">
				<div class="fr pc-space-j">
					<span>应付总额：<strong>￥<i class="payMoney">${payMoney}</i></strong></span>
					<button class="pc-sub">提交订单</button>
				</div>
			</div>
		</div>
	  </form>
	</section>
	<!-- 订单提交成功 End-->


	<!--- footer begin-->
	<c:import url="${path}/web/foot"></c:import>
	<!-- footer End -->

	<script type="text/javascript"
		src="<%=request.getContextPath() %>/webtheme/theme/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/webtheme/theme/js/distpicker.min.js"></script>
	<script type="text/javascript">
     	
 </script>

</body>
</html>
