<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String weburl=WXContants.Website;
%>
<style>

</style>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/fastclick.js"></script>
<script type="text/javascript">
    
  
  

    $(function() {
  $("#img_b").click(function(){
     location.href=window.BASEPATH + 'cybersecurity/cybermine';
    })
     $("#img_a").click(function(){
     location.href=window.BASEPATH + 'cybersecurity/cyberhome';
    })
  });
		
		
</script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery.mmenu/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript" src="<%=basePath %>web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=basePath %>web/commons/date/date.ext.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/swiper.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jquery_json.js"></script>
<body>
 <div class="navbar" style="position:fixed;bottom:0px;margin:0;width:100%;height:auto;z-index:111111111;background:#fff;text-align: center;">
		   <div id="index" style="display: inline-block;width:49%;">
		   <img id="img_a" style="width:30px;height:30px;" src="lib/images/homes.png">
		   <p style="margin:0;font-size:13px;height:15px;">主页</p>
		   </div>
		   <div id="backet" style="display: inline-block;width:49%;">
		    <img id="img_b" style="width:30px;height:30px;" src="lib/images/mys.png">
		    <p style="margin:0;font-size:13px;height:15px;">我的</p>
		   </div>
		
	       </div>
</body>