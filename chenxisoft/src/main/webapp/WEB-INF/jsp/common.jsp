<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String weburl=WXContants.Website;
%>
<style>
  .current{
    color:#FFC0CB!important;
  }
  .ui-tab-nav li.current>p:before, .ui-tab-nav li.current>span:before{
    background-color:#FFC0CB!important;
  }

</style>

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
    	
    	 
    	   location.href=$(this).attr('data')+'?timestamp='+((new Date()).getTime()+Math.random());
    	
    	});
    	
    	function initHeader(){
    	     var html=[];
    	     html.push('<a href="home/mobile/index"><i style="font-size:14px;width:20px;line-height:50px;float:left"class="icon-home"></i></a>');
    	     html.push('<div style="width:140px;margin:0 auto">');
	         html.push('<image src="/chenxisoft/lib/images/logo.png" style="width:60px;height:38px;float:left;margin-top:5px;"/>');
	         html.push('<h1 style="font-size:14px;width:70px;float:left;margin-left:-15px;">小青月嫂</h1></div>');
	         html.push('<div id="tel" style="float:right;font-size:12px;">18617856189</div>');
	         $('header').append(html.join(''));
    	}
    	initHeader();
    	
    });
	
		
		
</script>
