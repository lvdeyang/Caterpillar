<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
body{
background: #E5E5E5 !important;
}
.feilei{
display: none !important;
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
	<title>二级分类</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">


 </head>
 <body>

<!--- header begin-->

<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"><a href="#">货架</a> &gt; <a href="#">二级货架</a></div></div>

<div class="containers clearfix">
    <!-- <div class="fl">
        <div class="pc-sisters">
            <div class="pc-s-title"><h2>商品TOP排行</h2></div>
            <div>
                <ul>
                    <li>
                        <div class="pc-s-line"><a href="#"><img src="theme/img/pd/hot2.png" width="188"></a> </div>
                        <div class="pc-s-link"><a href="#">小米 4 2GB内存版 白色 移动4G手机不锈钢金属边框、 5英</a> </div>
                        <div class="pc-s-lins"><p class="reds">￥1000.00</p><p class="blue">已售出：3000+</p></div>
                    </li>
                </ul>
            </div>
        </div>
    </div> -->
    <div class="pc-info fr">
        <div class="pc-term">
            <dl class="pc-term-dl clearfix">
                <dt>模块：</dt>
                <div style="float: left;width:90%">
                <c:forEach items="${modulars}" var="m">
                <c:choose>
                <c:when test="${m.modularCode=='01112'}">
                <dd><a class="mk${m.modularCode}" href="productList?m=${m.modularCode}">${m.modularName}</a></dd>
                </c:when>
                <c:otherwise>
                <dd><a class="mk${m.modularCode}" href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}">${m.modularName}</a></dd>
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
                <dd><a class="cl${mc.classCode}" href="productList?m=${m.modularCode}&mc=${mc.classCode}">${mc.className}</a></dd>
                </c:forEach>
                </c:if>
                </c:forEach>
                </div>
            </dl>
            <div class="pc-line"></div>
            <div class="pc-search clearfix">
                <div class="fl pc-search-in">
                    <input type="text" class="pc-search-w" id="pc-name" value="${pcName}">
                    <input type="text" class="pc-search-s" id="pc-price-min" placeholder="￥" value="${pcPriceMin}">
                    <input type="text" class="pc-search-s" id="pc-price-max"placeholder="￥" value="${pcPriceMax}">
                    <a href="javascript:search()" class="pc-search-a" >搜索</a>
                </div>
            <!--     <div class="fr pc-with">
                    相关搜索： <a href="#">黑糖</a><em>|</em><a href="#">姜茶</a><em>|</em><a href="#">红印黑糖</a><em>|</em><a href="#">黑糖话梅</a><em>|</em><a href="#">黑糖姜母</a><em>|</em><a href="#">茶黑糖饼</a><em>|</em><a href="#">干黑糖</a><em>|</em><a href="#">沙琪玛</a>
                </div> -->
            </div>
        </div>
        <div class="pc-term">
            <div class="clearfix pc-search-p">
                <div class="fl pc-search-e"><a href="#" class="cur">销量</a><a href="#">价格</a><a href="#">评价</a><a href="#">上架时间</a></div>
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
            	<c:forEach items="${productList}" var="pl">
            		<li>
                    	<a href="productInfo?info=${pl.uuid}"> <img width="209px" height="140px" src="${sysConfig.webUrl}${pl.productShowPic}"></a>
                    	<p class="head-name"><a href="productInfo?info=${pl.uuid}">${pl.productName}</a> </p>
                    	<p><span class="price">￥${pl.productPrice}</span></p>
                    	<p class="head-futi clearfix"><span class="fl">好评度：90% </span> <span class="fr" style="float:right;">${pl.productSaleNum}人购买</span></p>
                    	<!-- <p class="clearfix"><span class="label-default fl">抢购</span> <a href="#" class="fr pc-search-c">收藏</a> </p> -->
                	</li>
            	</c:forEach>
            </ul>
            <div class="clearfix">
           ${pages}
               <!--  <div class="fr pc-search-g">
                    <a class="fl pc-search-f" href="#">上一页</a>
                    <a href="#" class="current">1</a>
                    <a href="javascript:;">2</a>
                    <a href="javascript:;">3</a>
                    <a href="javascript:;">4</a>
                    <a href="javascript:;">5</a>
                    <a href="javascript:;">6</a>
                    <span class="pc-search-di">…</span>
                    <a href="javascript:;">7</a>
                    <span class="pc-search-di">…</span>
                    <a title="使用方向键右键也可翻到下一页哦！" class="pc-search-n" href="javascript:;" onClick="SEARCH.page(3, true)">下一页</a>
                    <span class="pc-search-y">
                        <em>  共20页    到第</em>
                        <input type="text" id='pagecurr' class="pc-search-j" placeholder="1">
                        <em>页</em>
                        <a href="#" class="confirm">确定</a>
                    </span>

                </div> -->
            </div>
        </div>
        <div class="pc-search-re clearfix">
            <!-- <dl>
                <dt>重新搜索</dt>
                <dd>
                    <input type="text" value="三星"  id="key-re-search" class="text">
                    <input type="button" value="搜&nbsp;索" id="btn-re-search" class="button">
                </dd>
            </dl> -->
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
	console.log("${param}");
	
})
function search(){
	var kw=$("#pc-name").val();
	var pcPriceMin=$("#pc-price-min").val();
	var pcPriceMax=$("#pc-price-max").val();
	var str = "productList?m="+mc
	if(cc!=""){
		str = str+"&mc="+cc;
	}
	if(kw!=""){
		str = str+"&pcName="+kw;
	}
	if(pcPriceMin!=""){
		str = str+"&pcPriceMin="+pcPriceMin;
	}
	if(pcPriceMax!=""){
		str = str+"&pcPriceMax="+pcPriceMax;
	}
	window.location.href=str;
}

</script>
</body>
</html>
