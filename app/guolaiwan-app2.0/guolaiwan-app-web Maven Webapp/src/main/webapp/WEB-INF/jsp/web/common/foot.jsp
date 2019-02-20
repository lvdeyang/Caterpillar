<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
	.one1{
		width:100%;
		height:288px;
		background:white;
		float:left;
        margin:0 auto;
	}
	.tow{
		
		width:100%;
	}
	.three{
		margin:0 auto;	
	}
	.juzhong{
		width:100%;
		height:289px;
		background:#FFFFFF;
		
	}
	.four{
		width:320px;
		height:288px;
		float:left;
		background:white;
		margin:0px;
	}
	.four2{
		text-align:center;
		font:bold 15px/20px "SinHei";
    }
	
	.qunimei{
		height:0px;
	
	}
	.fan{
		text-align:center;
		font:bold 15px/20px "SinHei";
	}
	.ppp{
		text-align:center;
		font:bold 15px/20px "SinHei";
	}
	.four4{
		width:330px;
		height:288px;
		float:left;
		background:white;
		margin-left:35px;
		text-align:center;
		padding:0px;
		}
	.tupian{
		width:180px;
		height:180px;
		text-align:center;
		float:left;
		margin:0px 40px 48px 60px;
	}
	.ziti{
		margin:0 auto;
		font-size:18px;
		text-algin:right;
		margin-top:30px;
		margin-left:110px;
	}
	.five{
		margin:0 auto;
		width:1350px;
	}	
	
</style>

    <div class="aui-footer-bot" style="background:#ffffff;">
        <div style="border-bottom: 1px solid #dedede"></div>
        <div class="time-lists aui-footer-pd time-lists-ls clearfix">
        	<c:forEach items="${classes}" var="c">	
            <div class="aui-footer-list clearfix">
                <h4>${c.className}</h4>
                <ul>
                <c:forEach items="${c.articles}" var="cz">
                    <li><a href="<%=path %>/article/articleInfo?i=${cz.uuid}"> ${cz.articleName} </a></li>
                
                </c:forEach>
                </ul>
            </div>
            </c:forEach> 
          
        </div>
      </div>
  
     <div class="tow">
     <div class="juzhong">
      <div class="five">
        <div class="four">
   		 <table class="ppp" style="width:200px;height:50px;margin:50px auto;" >
				<tr>
					<th>旅游查询</th>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">轮船索引</a></td>
					<td><a href="<%=path%>/web/creating">宾馆索引</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">汽车索引</a></td>
					<td><a href="<%=path%>/web/creating">旅游索引</a></td>
				<tr>
					<td><a href="<%=path%>/web/creating">攻略索引</a></td>
					<td><a href="<%=path%>/web/creating">火车票索引</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">汽车票索引</a></td>
					<td><a href="<%=path%>/web/creating">网站导航</a></td>				
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">智慧旅游</a></td>
					<td><a href="<%=path%>/web/creating">更多加盟</a></td>
				</tr>
			</table>
   			
   		
   		</div>
		<div class="four">
			<table class="ppp" style="width:200px;height:50px;margin:50px auto;" >
				<tr>
					<th>加盟合作</th>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">分销联盟</a></td>
					<td><a href="<%=path%>/web/creating">友情连接</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">广告业务</a></td>
					<td><a href="<%=path%>/web/creating">企业礼品采购</a></td>
				<tr>
					<td><a href="<%=path%>/web/creating">保险代理</a></td>
					<td><a href="<%=path%>/web/creating">代理合作</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">酒店加盟</a></td>
					<td><a href="<%=path%>/web/creating">目的地及景区合作</a></td>				
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">智慧旅游</a></td>
					<td><a href="<%=path%>/web/creating">更多加盟</a></td>
				</tr>
			</table>
		</div>
		<div class="four">
			<table class="ppp" style="width:200px;height:50px;margin:50px auto;" >
				<tr>
					<th>关于过来玩</th>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">关于过来玩</a></td>
					<td><a href="<%=path%>/web/creating">诚聘英才</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">联系我们</a></td>
					<td><a href="<%=path%>/web/creating">过来玩热点</a></td>
				<tr>
					<td><a href="<%=path%>/web/creating">旅游度假组织</a></td>
					<td><a href="<%=path%>/web/creating">企业公民</a></td>
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">用户协议</a></td>
					<td><a href="<%=path%>/web/creating">企业执照</a></td>				
				</tr>
				<tr>
					<td><a href="<%=path%>/web/creating">安全中心</a></td>
					<td><a href="<%=path%>/web/creating">携程信用卡</a></td>
				</tr>
			</table>
				
		</div>
		<div class="four4">
			<table class="ziti">
				<tr><td>APP下载</td></tr>
			</table>
			<img class="tupian"  src="<%=path%>/webtheme/theme/icon/logo2.png">
		</div>
	 </div>
	 </div>
	 </div>
	<div class="qunimei"></div>
	
 <div align="center"  style="background:#444444 ">
 <a href="javascript:void(0)" style="center;color:#CFCFCF" target="_blank">©遵化市博客旅游集散中心有限公司(guolaiwan.net)</a>
 <a href="<%=path  %>/webtheme/theme/foot/a1.png" style="center;color:#CFCFCF" target="_blank">营业执照</a></div>
 
