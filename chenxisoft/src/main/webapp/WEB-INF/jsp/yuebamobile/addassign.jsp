<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
<title>注册</title>
<!-- 引入 FrozenUI -->
<link rel="stylesheet"
	href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css" />
<link rel="stylesheet"
	href="lib/css/font-awesome.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://ryanbay.cn/vipstyle/qui/2.0.0/demo/js/lib/zepto.min.js"></script>
<script src="http://i.gtimg.cn/vipstyle/frozenjs/1.0.1/frozen.js"></script>
<style>
   label{
     font-size:14px;
     width:105px;
   }
   input{
     font-size:14px;
     height:46px;
   }
   .ftitle{
     width:96%;
     height:40px;
     padding-left:10px;
     line-height:40px;
     color:#BA6985;
     
   }
   .ftitle a{
     display:inline-block;
     float:right;
     margin-right:20px;
     font-size:14px;
     color:#FFC0CB;
   }
   .addPhoto{
     margin-top:5px;
     margin-left:20px;
     width:50px;
     height:50px;
     line-height:50px;
     font-size:24px;
     color:#CCC;
     border:1px solid #CCC;
     text-align:center
   }
   .showPhoto{
     margin-top:5px;
     margin-left:10px;
     width:50px;
     height:50px;
     display:none;
   }
   .ui-form-item p{
     font-size:14px;
   }
   
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">签到
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b"  style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
        
    </header>
	
	<div class="ui-form ui-border-t" style="margin-top:45px;">
	        <div class="ftitle">
	                                   产妇记录
	        </div>

		        <div class="ui-form-item ui-border-b">
		            <label>
		                乳房情况                       
		            </label>
		            <input id="rufang" value="${assign.rufang}" type="text" placeholder="请输入"/>
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                恶露情况
		            </label>
		            <input id="elu" value="${assign.elu}" type="text" placeholder="请输入">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                褥汗情况
		            </label>
		            <input id="ruhan" value="${assign.ruhan}" type="text" placeholder="请输入">
		            
		        </div>
		    <div class="ftitle">
	                                   婴儿记录
	        </div>

		        <div class="ui-form-item ui-border-b">
		            <label>
		                脐带情况                       
		            </label>
		            <input id="qidai" value="${assign.qidai}" type="text" placeholder="请输入"/>
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                臀部情况
		            </label>
		            <input id="tunbu" value="${assign.tunbu}" type="text" placeholder="请输入">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                大便次数
		            </label>
		            <input id="dabiancishu" value="${assign.dabiancishu}" type="text"  placeholder="请输入">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                小便次数
		            </label>
		            <input id="xiaobiancishu" value="${assign.xiaobiancishu}" type="text" placeholder="请输入">
		            
		        </div>
		       
		        
		    <div class="ftitle">
	                                   产妇服务
	        </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              乳房护理
		          </p>
		          <label class="ui-switch">
		              <input id="rufanghuli" type="checkbox">
		          </label>
		     </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              产后操
		          </p>
		          <label class="ui-switch">
		              <input id="chanhoucao" type="checkbox">
		          </label>
		     </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              沐浴擦身
		          </p>
		          <label class="ui-switch">
		              <input id="muyucashen" type="checkbox">
		          </label>
		     </div>
		     
		     
		     <div class="ftitle">
	                                   婴儿服务
	        </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              婴儿洗澡
		          </p>
		          <label class="ui-switch">
		              <input id="yingerxizao" type="checkbox">
		          </label>
		     </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              婴儿体温
		          </p>
		          <label class="ui-switch">
		              <input id="yingertiwen" type="checkbox">
		          </label>
		     </div>
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              婴儿抚触
		          </p>
		          <label class="ui-switch">
		              <input id="yingerfuchu" type="checkbox">
		          </label>
		     </div>
		     
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              便后洗臀
		          </p>
		          <label class="ui-switch">
		              <input id="bianhouxitun" type="checkbox">
		          </label>
		     </div>
		     
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              消毒奶具
		          </p>
		          <label class="ui-switch">
		              <input id="xiaodunaiju" type="checkbox">
		          </label>
		     </div>
		     
		     
		  
		     
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              脐带处理
		          </p>
		          <label class="ui-switch">
		              <input id="qidaichuli" type="checkbox">
		          </label>
		     </div>
		     
		     <div class="ui-form-item ui-form-item-switch ui-border-b">
		          <p>
		              协助喂养
		          </p>
		          <label class="ui-switch">
		              <input id="xiezhuweiyang" type="checkbox">
		          </label>
		     </div>
		 
		 
	        
	        <div class="ui-btn-wrap" id="saveBtn" style="display:none;">
	            <button id="dosave" class="ui-btn-lg ui-btn-primary" style="background:#FFC0CB">
	                                    保存
	            </button>
	            
	        </div>
	        
	        <div id="loading" class="ui-loading-block show" style="display:none">
			    <div class="ui-loading-cnt">
			        <i class="ui-loading-bright"></i>
			        <p>正在上传，请不要关闭页面......</p>
			    </div>
			</div>
	        
	        
	</div>
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

	<script type="text/javascript">
	     $(function() {
	          window.BASEPATH = '<%=basePath%>';
	          
			  var parseAjaxResult = function(data){
					if(data.status !== 200){
						$.toptip('data.message', 'error');
						return -1;
					}else{
						return data.data;		
					}
			  };
	        //页面初始化的一系列工作
	        var canmodify=${canmodify};
	        if(canmodify==1){
	           $('#saveBtn').show();
	        }
	        
	        var assignId=${assignId};
	        
	        var rufanghuli=${assign.rufanghuli};
			var muyucashen=${assign.muyucashen};
			var chanhoucao=${assign.chanhoucao};
			
			var yingerxizao=${assign.yingerxizao};
			var yingertiwen=${assign.yingertiwen};
			var yingerfuchu=${assign.yingerfuchu};
			var qidaichuli=${assign.qidaichuli};
			var bianhouxitun=${assign.bianhouxitun};
			var xiaodunaiju=${assign.xiaodunaiju};
			var xiezhuweiyang=${assign.xiezhuweiyang};
	        
	        $("#rufanghuli").prop("checked",(rufanghuli==1?true:false));
	        $("#muyucashen").prop("checked",(muyucashen==1?true:false));
	        $("#chanhoucao").prop("checked",(chanhoucao==1?true:false));
	        $("#yingerxizao").prop("checked",(yingerxizao==1?true:false));
	        $("#yingertiwen").prop("checked",(yingertiwen==1?true:false));
	        $("#yingerfuchu").prop("checked",(yingerfuchu==1?true:false));
	        $("#qidaichuli").prop("checked",(qidaichuli==1?true:false));
	        $("#bianhouxitun").prop("checked",(bianhouxitun==1?true:false));
	        $("#xiaodunaiju").prop("checked",(xiaodunaiju==1?true:false));
	        $("#xiezhuweiyang").prop("checked",(xiezhuweiyang==1?true:false));

	        
	       
	        $(document).on('click','#dosave',function(){
	            $('#loading').show();
	            var params={};

	            params.orderId=${orderId};
	            params.assignId=${assignId};
				params.rufang=$('#rufang').val();
				params.elu=$('#elu').val();
				params.ruhan=$('#ruhan').val();
				
				params.qidai=$('#qidai').val();
				params.tunbu=$('#tunbu').val();
				params.dabiancishu=$('#dabiancishu').val();
				params.xiaobiancishu=$('#xiaobiancishu').val();
				
				if($("#rufanghuli").prop("checked") == true){
				   params.rufanghuli=1;
				}else{
				   params.rufanghuli=0;
				}
				
				if($("#muyucashen").prop("checked") == true){
				   params.muyucashen=1;
				}else{
				   params.muyucashen=0;
				}
				
				if($("#chanhoucao").prop("checked") == true){
				   params.chanhoucao=1;
				}else{
				   params.chanhoucao=0;
				}
				
				if($("#yingerxizao").prop("checked") == true){
				   params.yingerxizao=1;
				}else{
				   params.yingerxizao=0;
				}
				
				if($("#yingertiwen").prop("checked") == true){
				   params.yingertiwen=1;
				}else{
				   params.yingertiwen=0;
				}
				
				if($("#yingerfuchu").prop("checked") == true){
				   params.yingerfuchu=1;
				}else{
				   params.yingerfuchu=0;
				}
				
				if($("#qidaichuli").prop("checked") == true){
				   params.qidaichuli=1;
				}else{
				   params.qidaichuli=0;
				}
				
                if($("#bianhouxitun").prop("checked") == true){
				   params.bianhouxitun=1;
				}else{
				   params.bianhouxitun=0;
				}
			
			
			    if($("#xiaodunaiju").prop("checked") == true){
				   params.xiaodunaiju=1;
				}else{
				   params.xiaodunaiju=0;
				}
				
                if($("#xiezhuweiyang").prop("checked") == true){
				   params.xiezhuweiyang=1;
				}else{
				   params.xiezhuweiyang=0;
				}

                var _uri = window.BASEPATH + 'assign/mobile/dosave';
		        $.post(_uri, params, function(data){
			        location.href=window.BASEPATH + 'assign/mobile/wlist?orderId=${orderId}&t=' + new Date().getTime();
			        $('#loading').hide();
				}); 
				     
	        });
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
