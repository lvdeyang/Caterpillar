<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>
<style>
.yNavIndex .yMenuIndex li{
   height:45px !important;
  line-height: 45px !important;
  text-align: center;
  margin:0 auto;
}
 .yNavIndex .yMenuIndex li a:hover{
color:#fff;
} 
.yNavIndex .yMenuIndex li a{
 height:45px !important;
 line-height: 45px !important;
 font-size: 16px !important;
  
}
.yNavIndex{
height:45px !important;
}
.yMenuListCon p a{
color:#fff !important;
font-weight: bold;
}
.pullDownList li:hover{
background: #2CAD4A !important;
color:#fff !important;
}
.pullDownList li{
background: #fff !important;
color:#000000;
padding-left: 15px !important;
}
.pullDownList{
background: #fff !important;

}
.layui-nav .layui-this:after, .layui-nav-bar, .layui-nav-tree .layui-nav-itemed:after{
background: #fff !important;
}

</style>
<!--- header begin-->
    <header id="pc-header" style="position: fixed;top:0;z-index:111;background: #E5E5E5;width:100%;">
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
        <div class="container clearfix" style="padding:10px 0;height:90px;">
            <div class="header-logo fl" style="text-align: center;">
                <a href="<%=path %>/index">
                    <img style="width:90px;margin-left:-130px;" src="<%=path %>/lib/images/logos.png"></a>
            </div>
            <div class="head-form fl" >
                <form class="clearfix" style="margin-top:30px;">
                    <input style="padding-left:20px;"  type="text" class="search-text" accesskey="" id="key" autocomplete="off" placeholder="搜你所选 选你所爱 ~">
                    <button style="" class="button" onclick="headSearch();return false;">搜索</button>
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
        <div class="yHeader" style="border:none;">
            <div class="yNavIndex">
                <div class="pullDown">
                    <h2 class="pullDownTitle" style="height:45px;line-height: 45px;">全部板块分类
                    </h2>
                      <ul class="pullDownList" hidden="hidden" style="height:480px;overflow: hidden;">
                   <c:forEach items="${modulars}" var="f">
                    	 <li class="menulihover">
                            <i class="listi1"></i>
                            	${f.modularName}
                            <span></span>
                       	 </li>
                    </c:forEach>
                    <!-- 活动 -->
                    <c:forEach items="${activitys}" var="activity">
                    	 <li class="menulihover">
                            <i class="listi1"></i>
                            	${activity.name}
                            <span></span>
                       	 </li>
                    </c:forEach>
                    </ul>  
                    <div class="yMenuListCon" hidden="hidden" style="height:480px;margin-top:10px;" >
						<c:forEach items="${modulars}" var="m">
						<div class="yMenuListConin" style="width:760px;height:500px;background: rgba(0,0,0,0.3);opacity: 1;color:#fff;">
           				<%-- 	<div class="yMenuLCinLisi fl">
                           		<ul>
                               	<li><a href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}"><i class="fr">${m.modularName}</i></a></li>
                           		</ul>
                       		</div> --%>
                       		<div class="yMenuLCinList fl"  >
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
                    	 <div class="yMenuListConin" style="width:760px;height:500px;background: rgba(0,0,0,0.3);opacity: 1;color:#fff;">
           				<%-- 	<div class="yMenuLCinLisi fl">
                           		<ul>
                               	<li><a href="<%=path %>/web/activity/productList?activity=${activity.uuid}"><i class="fr">${activity.name}</i></a></li>
                           		</ul>
                       		</div> --%>
                        </div>	
                    	</c:forEach>
                    </div>
                </div>
                <ul class="yMenuIndex layui-nav" style="line-height: 45px;height:45px;left:15%;">
                	
                <li class="layui-nav-item"><a href="<%=path%>/index">首页</a></li>
        		<%-- <li><a href="<%=path%>/web/creating">攻略</a></li> --%>
        		<li class="layui-nav-item"><a href="<%=path%>/web/creating">关于我们</a></li>
        		<li class="layui-nav-item"><a href="<%=path%>/web/distributor/distributorlist"> 分销商店铺</a></li>
        		<li class="layui-nav-item"><a href="<%=path%>/web/videoPic/list">我发布</a></li>
                </ul>
            </div>
        </div>
        	
    </header>
    <p style='height:191px;'></p>
    <script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
	<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
    layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
});
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