<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--- header begin-->
 <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
 <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <header id="pc-header">
        <div class="BHeader">
            <div class="yNavIndex">
                <ul class="BHeaderl" style="width:1200px">
                	<li class="fltl">
            		您好，欢迎光临过来玩网！
            		</li>
            		<div class="fltr">
            		<li>
	   					 <c:choose>
  						<c:when test="${userInfo == null}">
    							<a id="login" href="<%=path %>/user/login" style="color: #ea4949;">请登录</a>
    							<li class="headerul">|</li>
    							<li><a href="<%=path %>/user/register">免费注册</a> </li>
    							<li class="headerul">|</li>
    						</c:when>
    						<c:otherwise>
    							<a id="userInfo" href="<%=path %>/user/usercenter" style="color: #ea4949;">${userInfo.userPhone}</a>&nbsp;&nbsp;&nbsp;&nbsp;
    							<li class="headerul">|</li>
    							<a href="<%=path %>/user/doLogout" style="color: #000;">退出</a>
    						</c:otherwise>
    					</c:choose>
					</li>
					</div>
                </ul>
            </div>
        </div>
    </header>
