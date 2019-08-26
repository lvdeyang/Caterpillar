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
    $(document).on('click',' #index',function(){ 
    location.href=window.BASEPATH +'pubnum/wallet';
   });
    $(document).on('click',' #backet',function(){ 
    location.href=window.BASEPATH +'business/basket/index?merchantId='+${applicationScope.merch_id};
   });
    $(document).on('click',' #order',function(){ 
    location.href=window.BASEPATH +'business/order/list?merchantId='+${applicationScope.merch_id};
   });
    
  });
		
		
</script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery.mmenu/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript" src="<%=basePath %>web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=basePath %>web/commons/date/date.ext.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/swiper.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jquery_json.js"></script>
<body>
 <div class="navbar" style="position:fixed;bottom:0px;margin:0;width:100%;height:auto;z-index:111111111;background:#EEEEEE;text-align: center;">
		   <div id="index" style="display: inline-block;width:32%;">
		   <img id="img_a" style="width:30px;height:30px;" src="lib/images/wallet.gif">
		   <p style="margin:0;font-size:13px;height:15px;">钱包</p>
		   </div>
		   <div id="backet" style="display: inline-block;width:32%;">
		    <img id="img_b" style="width:30px;height:30px;" src="lib/images/cart.gif">
		    <p style="margin:0;font-size:13px;height:15px;">购物车</p>
		   </div>
		   <div id="order" style="display: inline-block;width:32%;">
		    <img id="img_c" style="width:30px;height:30px;" src="lib/images/goods.gif">
		    <p style="margin:0;font-size:13px;height:15px;">订单</p>
		   </div>
	       </div>
</body>