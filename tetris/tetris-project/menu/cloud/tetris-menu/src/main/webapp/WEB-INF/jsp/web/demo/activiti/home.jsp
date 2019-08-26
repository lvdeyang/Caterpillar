<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作流示例</title>
<style type="text/css">
	table{margin-top:10px; border-collapse:collapse;}
	caption{border:1px solid #777; border-bottom:0; padding:5px 10px;}
	th, td {border: 1px solid #777; padding: 5px 10px;}
</style>
</head>
<body>
	<% 
	String username = request.getParameter("username");	
	if("employee".equals(username)){%>
		<select id="apply-time">
			<option value="0.5">30分钟</option>
			<option selected value="1">1小时</option>
			<option value="2">2小时</option>
			<option value="3">3小时</option>
		</select>
		<a id="do-apply" href="<%=basePath %>apply?username=<%=username %>&time=1">申请会议室</a>	
	<%} %>
	
	<%
		List<Map<String, String>> myApply = (List<Map<String, String>>)request.getAttribute("myApply");
		if(myApply!=null && myApply.size()>0){
	 %>
	 
	 	<table class="my-apply">
			<caption>我发起的流程</caption>
			<thead>
				<tr>
					<th>流程名称</th>
					<th>发起时间</th>
					<th>当前节点</th>
					<th>节点到达时间</th>
					<th>操作</th>	
				</tr>
			</thead>
			<tbody>
				<%
					
					for(Map<String, String> processInfo:myApply){
				 %>
					<tr>
						<td><%=processInfo.get("processName") %></td> 
						<td><%=processInfo.get("createTime") %></td> 
						<td><%=processInfo.get("taskName") %></td> 
						<td><%=processInfo.get("taskTime") %></td> 
						<td><a href="<%=basePath %>cancel?username=<%=username %>&processId=<%=processInfo.get("processId") %>">撤销流程</a></td>
				 	</tr>
				 <%} %>
		 	</tbody>
		 </table>
	 
	 <%} %>
	 
	
	 <%
		List<Map<String, String>> myTasks = (List<Map<String, String>>)request.getAttribute("myTasks");
		if(myTasks!=null && myTasks.size()>0){
	 %>
	 
	 	<table class="my-tasks">
			<caption>我的待办事项</caption>
			<thead>
				<tr>
					<th>流程名称</th>
					<th>发起时间</th>
					<th>发起人</th>
					<th>当前任务</th>
					<th>任务到达时间</th>
					<th>状态</th>
					<th>操作</th>	
				</tr>
			</thead>
			<tbody>
				<%
					for(Map<String, String> taskInfo:myTasks){
				 %>
					<tr>
						<td><%=taskInfo.get("processName") %></td> 
						<td><%=taskInfo.get("createTime") %></td> 
						<td><%=taskInfo.get("startUserId") %></td>
						<td><%=taskInfo.get("taskName") %></td> 
						<td><%=taskInfo.get("taskTime") %></td> 
						<td>
							<%if(taskInfo.get("startUserId").equals(username)) {%>
								驳回
							<%}%>
						</td>
						<td>
							<%if(taskInfo.get("startUserId").equals(username)) {%>
								<a href="<%=basePath %>agree?username=<%=username %>&taskId=<%=taskInfo.get("taskId") %>">再次提交</a>
								&nbsp;&nbsp;
								<a href="<%=basePath %>cancel?username=<%=username %>&processId=<%=taskInfo.get("processId") %>">撤销流程</a>
							<%}else { %>
								<a href="<%=basePath %>agree?username=<%=username %>&taskId=<%=taskInfo.get("taskId") %>">同意</a>
								&nbsp;&nbsp;
								<a href="<%=basePath %>reject?username=<%=username %>&taskId=<%=taskInfo.get("taskId") %>">驳回</a>
								&nbsp;&nbsp;
								<a href="<%=basePath %>refuse?username=<%=username %>&taskId=<%=taskInfo.get("taskId") %>">拒绝</a>
							<%} %>
						</td>
				 	</tr>
				 <%} %>
		 	</tbody>
		 </table>
	 
	 <%} %>
	 
	 <%
		List<Map<String, String>> historicApply = (List<Map<String, String>>)request.getAttribute("historicApply");
		if(historicApply!=null && historicApply.size()>0){
	 %>
	 
	 	<table class="historic-apply">
			<caption>历史申请记录</caption>
			<thead>
				<tr>
					<th>流程名称</th>
					<th>发起时间</th>
					<th>结束时间</th>
					<th>审批结果</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Map<String, String> historicProcessInstance:historicApply){
				 %>
					<tr>
						<td><%=historicProcessInstance.get("processName") %></td> 
						<td><%=historicProcessInstance.get("createTime") %></td> 
						<td><%=historicProcessInstance.get("endTime") %></td>
						<td><%=historicProcessInstance.get("status") %></td> 
				 	</tr>
				 <%} %>
		 	</tbody>
		 </table>
	 
	 <%} %>
	 
	 <%
		List<Map<String, String>> historicApproval = (List<Map<String, String>>)request.getAttribute("historicApproval");
		if(historicApproval!=null && historicApproval.size()>0){
	 %>
	 
	 	<table class="historic-approval">
			<caption>历史审批记录</caption>
			<thead>
				<tr>
					<th>流程名称</th>
					<th>发起时间</th>
					<th>结束时间</th>
					<th>节点名称</th>
					<th>节点到达时间</th>
					<th>审批结果</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Map<String, String> historicTaskInstance:historicApproval){
				 %>
					<tr>
						<td><%=historicTaskInstance.get("processName") %></td> 
						<td><%=historicTaskInstance.get("createTime") %></td> 
						<td><%=historicTaskInstance.get("endTime") %></td>
						<td><%=historicTaskInstance.get("taskName") %></td>
						<td><%=historicTaskInstance.get("taskTime") %></td>
						<td><%=historicTaskInstance.get("status") %></td> 
				 	</tr>
				 <%} %>
		 	</tbody>
		 </table>
	 
	 <%} %>
	
</body>
<script type="text/javascript" src="<%=basePath %>web/lib/frame/jQuery/jquery-2.2.3.js"></script>
<script type="text/javascript">
	$(function(){
		var $time = $('#apply-time');
		if($time[0]){
			var $link = $('#do-apply');
			var basePath = '<%=basePath %>' + 'apply?username=' + '<%=username %>' + '&time=';
			$time.on('change', function(){
				var _time = $time.val();
				$link.attr('href', basePath + _time);
			});
		}
	});
</script>
</html>