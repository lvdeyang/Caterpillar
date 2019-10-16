<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
.wei:hover{background: rgba(0,0,0,0) !important;
 transition: all 2s !important;
}
.search-text{
height:36px !important;
}
</style>
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
	<title>商家列表</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">


 </head>
 <body>
<c:import url="${path}/web/top"></c:import>
<div class="containers"><div class="pc-nav-item"></div></div>
<div class="containers clearfix">
    <div class="pc-info fr">
        <div class="pc-term">
            <dl class="pc-term-dl clearfix">
                <dt>模块：</dt>
                <div style="float: left;width:90%">
                <c:forEach items="${modulars}" var="m">
                <c:choose>
                <c:when test="${m.modularCode=='01112'}">
                <dd><a class="mk${m.modularCode}" href="<%=path %>/product/productList?m=${m.modularCode}">${m.modularName}</a></dd>
                </c:when>
                <c:otherwise>
                <dd><a class="mk${m.modularCode}" href="merchantList?m=${m.modularCode}">${m.modularName}</a></dd>
                </c:otherwise>
                </c:choose>
                </c:forEach>
                </div>
            </dl>
            <dl class="pc-term-dl clearfix">
                <dt>分类：</dt>
                <div style="float: left;width:90%">
                <c:forEach items="${modulars}" var="m">
                <c:if test="${mc==m.modularCode}">
                <c:forEach items="${m.modularClasses}" var="mc">
                <dd><a class="cl${mc.classCode}" href="merchantList?m=${m.modularCode}&mc=${mc.classCode}">${mc.className}</a></dd>
                </c:forEach>
                </c:if>
                </c:forEach>
                </div>
            </dl>
            <div class="pc-line"></div>
            <div class="pc-search clearfix">
                <div class="fl pc-search-in">
                    <input type="text" class="pc-search-w" id="pc-name" value="${merName}">
                    <a href="javascript:search()" class="pc-search-a" >搜索</a>
                </div>
            </div>
        </div>
        <div class="pc-term">
            <div class="clearfix pc-search-p">
                <div class="fl pc-search-e"><!-- <a href="#" class="cur">销量</a> --><a href="#">价格</a><a href="#">评价</a><a href="#">上架时间</a></div>
                <div class="fr pc-search-v">
                    <ul>
                        <li><input type="checkbox"><a href="#">有货</a> </li>
                        <li><input type="checkbox"><a href="#">限时抢购</a> </li>
                        <li><input type="checkbox"><a href="#">满减大促</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="time-border-list pc-search-list clearfix">
            <ul class="clearfix" id="productList">
            	<c:forEach items="${merchants}" var="merchant">
            		<li>
                    	<p style="position: relative;"><a class="wei" style="height:140px;width:209px;background:rgba(0,0,0,0.6);display: inline-block;position: absolute;z-index:11;top: 0;left: 0;bottom: 0;right: 0;border-radius:4px;overflow: hidden;" href="<%=basePath%>/web/merchant/productList?merchant=${merchant.uuid}"> </a><img style="border-radius:4px;height:140px;width:209px;z-index:1;" width="209px" height="140px" src="${sysConfig.webUrl}${merchant.shopPic}"></p>
                    	<p class="head-name" style="width:209px;height:30px;line-height: 30px;padding:0;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;"><a href="<%=basePath%>/web/merchant/productList?merchant=${merchant.uuid}">${merchant.shopName}</a> </p>
                    	<p class="head-futi clearfix"><span class="fl" style="color:#2BAD4A;">好评度：93% </span> <%-- <span class="fr" style="float: right;color:#E4393C;">${pl.productSaleNum}人购买</span> --%></p>
                    	<%-- <p><span class="price">￥${merchant.productPrice}</span></p>
                    	<p class="head-futi clearfix"><span class="fl">好评度：90% </span> <span class="fr">${merchant.productSaleNum}人购买</span></p>
                    	 --%><!-- <p class="clearfix"><span class="label-default fl">抢购</span> <a href="#" class="fr pc-search-c">收藏</a> </p> -->
                	</li>
            	</c:forEach>
            </ul>
            <div class="clearfix">
            	${pages}
            </div>
        </div>
        <div class="pc-search-re clearfix">
        </div>
    </div>
</div>

<c:import url="${path}/web/foot"></c:import>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>

<script type="text/javascript">
//成员变量
	var	mc ;
	var cc ;
	var basePath="<%=basePath%>";
	
$(function(){
	mc = "${mc}";
	cc = "${cc}";
	$(".mk${mc}").css("background","#5FAAFE");
	$(".cl${cc}").css("background","#5FAAFE");
	
	$(".mka").text($(".mk${mc}").text());
	$(".fla").text($(".cl${cc}").text());
	console.log("${param}");
	
})
function search(){
	var kw=$("#pc-name").val();
	var str = "merchantList?m="+mc	if(cc!=""){
		str = str+"&mc="+cc;
	}

	if(kw!=""){
		str = str+"&merName="+kw;
	}
	window.location.href=str;
}

</script>
</body>
</html>
