<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String weburl=WXContants.Website;
%>


<script type="text/javascript">
    


    $(function() {
    	function initMenu(num){
    	   var menuHtml=[];
		   menuHtml.push('<div class="ui-tab " style="position:fixed;bottom:0px;top:auto;">');
		   menuHtml.push('    <ul class="ui-tab-nav ui-border-b ">');
		   menuHtml.push('        <li data="home/mobile/index" class="bottomMenu '+(num==1?'current':'')+'"><span>首页</span></li>');
		   menuHtml.push('        <li data="cartest/index" class="bottomMenu '+(num==2?'current':'')+'"><span>育儿</span></li>');
		   menuHtml.push('        <li data="person/index" class="bottomMenu '+(num==3?'current':'')+'"><span>个人</span></li>');
		   menuHtml.push('    </ul>');
		   
		   menuHtml.push('</div>');
		   $('body').append(menuHtml.join(''));
    	
    	}
 
    	
    	var href=location.href;
	    if(href.indexOf('home/mobile/index')!=-1){
	       initMenu(1);
	    }else if(href.indexOf('person/index')!=-1){
	       initMenu(3);
	    }else if(href.indexOf('cartest/index')!=-1){
	       initMenu(2);
	    }
    	
    	$(document).on('click','.bottomMenu',function(){
    	
    	 
    	   location.href=$(this).attr('data');
    	
    	});
    });
	
		
		
</script>
