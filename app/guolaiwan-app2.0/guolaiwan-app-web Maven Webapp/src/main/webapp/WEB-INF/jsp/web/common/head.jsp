<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>

<!--- header begin-->
    <header id="pc-header">
        <div class="BHeader">
            <div class="yNavIndex">
                <ul class="BHeaderl">
                	<li class="fltl">
            		您好，欢迎光临过来玩网！
            		</li>
                    <!-- <li style="display: none;"><a href="#" style="float: left;">嘻哈杂货铺</a> <a href="#" style="float: left;">退出</a> </li> -->
                    <div class="fltr">
                    <li>
	   					 <c:choose>
    						<c:when test="${userInfo == null}">
    							<a id="login" href="#" style="color: #ea4949;">请登录</a>
    							<li class="headerul">|</li>
    							<li><a href="<%=path %>/user/register">免费注册</a> </li>
    							<li class="headerul">|</li>
    						</c:when>
    						<c:otherwise>
    							<li><a id="userInfo" href="<%=path %>/user/order/list/1" style="color: #ea4949;">购物车</a></li>
    							<li class="headerul">|</li>
    							<li><a id="userInfo" href="<%=path %>/user/usercenter" style="color: #ea4949;">${userInfo.userPhone}</a></li>
    							<li class="headerul">|</li>
    							<li><a href="<%=path %>/user/doLogout" style="color: #000;">退出</a></li>
    						</c:otherwise>
    					</c:choose>
					</li>
                    </div>
                </ul>
            </div>
        </div>
        <div class="container clearfix">
            <div class="header-logo fl">
                <h1><a href="<%=path %>/index">
                    <img src="<%=path %>/webtheme/theme/icon/logo.png"></a> </h1>
            </div>
            <div class="head-form fl">
                <form class="clearfix">
                    <input type="text" class="search-text" accesskey="" id="key" autocomplete="off" placeholder="">
                    <button class="button" onclick="headSearch();return false;">搜索</button>
                </form>
                <!-- <div class="words-text clearfix">
                    <a href="#" class="red">1元秒爆</a>
                    <a href="#">低至五折</a>
                    <a href="#">农用物资</a>
                    <a href="#">买一赠一</a>
                    <a href="#">佳能相机</a>
                    <a href="#">稻香村月饼</a>
                    <a href="#">服装城</a>
                </div> -->
            </div>
          
            <div class="head-mountain"></div>
        </div>
        <div class="yHeader">
            <div class="yNavIndex">
                <div class="pullDown">
                    <h2 class="pullDownTitle">全部板块分类
                    </h2>
                    <ul class="pullDownList" hidden="hidden">
                   <c:forEach items="${modulars}" var="f">
                    	 <li class="menulihover">
                            <i class="listi1"></i>
                            	<a href="#">${f.modularName}</a>
                            <span></span>
                       	 </li>
                    </c:forEach>
                    <!-- 活动 -->
                    <c:forEach items="${activitys}" var="activity">
                    	 <li class="menulihover">
                            <i class="listi1"></i>
                            	<a href="#">${activity.name}</a>
                            <span></span>
                       	 </li>
                    </c:forEach>
                    </ul>
                    <div class="yMenuListCon" hidden="hidden">
						<c:forEach items="${modulars}" var="m">
						<div class="yMenuListConin">
           					<div class="yMenuLCinLisi fl">
                           		<ul>
                               	<li><a href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}"><i class="fr">${m.modularName}</i></a></li>
                           		</ul>
                       		</div>
                       		<div class="yMenuLCinList fl">
                           		<p>
                           		<c:forEach items="${m.modularClasses}" varStatus="i" var="mc">
                              	 	<a href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}&mc=${mc.classCode}" class="ecolor610">${mc.className}</a>
                          	 		</c:forEach>
                           		</p>
                       		</div>
                        </div>		
                        </c:forEach>
                        <!-- 活动 -->
                        <c:forEach items="${activitys}" var="activity"><a href="http://web/activity/productList?activity=uuid"></a>
                    	 <div class="yMenuListConin">
           					<div class="yMenuLCinLisi fl">
                           		<ul>
                               	<li><a href="<%=path %>/web/activity/productList?activity=${activity.uuid}"><i class="fr">${activity.name}</i></a></li>
                           		</ul>
                       		</div>
                        </div>	
                    	</c:forEach>
                    </div>
                </div>
                <ul class="yMenuIndex">
                	
                <li><a href="<%=path%>/index">首页</a></li>
        		<li><a href="<%=path%>/web/creating">攻略</a></li>
        		<li><a href="<%=path%>/web/creating">关于我们</a></li>
        		<li><a href="<%=path%>/web/distributor/distributorlist"> 分销商店铺</a></li>
        		<li><a href="<%=path%>/web/videoPic/list">我发布</a></li>
                </ul>
            </div>
        </div>
        	
    </header>
    <script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>
    
    <script type="text/javascript">
    var basePath = '<%=basePath%>';
    var path = '<%=path%>';
    	function headSearch(){
    		var key = $("#key").val();
    		if (key != "") {
    			location.href="<%=path%>/product/productSearch?pcName=" + encodeURI(encodeURI(key));
    		}
    	}
    	$(function(){
    		var oldurl = document.referrer;
    		var nowUrl = window.location.href;
    		nowUrl = nowUrl.replace("http://<%=weburl%>/","").replace(basePath,"").replace("&","!");
    		$("#login").attr("href",basePath+"user/login?rul="+path+"/"+nowUrl);
    	
    	})
    </script>
<!--

//-->
</script>
    <!-- header End -->